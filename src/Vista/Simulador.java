/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Animal;
import Modelo.ManejadorDatos;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Simulador {

    private Scene escena;
    private VBox root;
    private GridPane grid;
    private Button avanzar;
    private Button automatico;
    private Button reiniciar;
    private Button salir;
    private Button pausa;
    private Label ciclosLBL;
    private boolean end;
    ManejadorDatos manejador = ManejadorDatos.getInstance();
    VBox[][] matriz = new VBox[manejador.getDimension()][manejador.getDimension()];
    Animal[][] animales = new Animal[manejador.getDimension()][manejador.getDimension()];
    private int ciclos;
    private ArrayList<ToolAnimal> toolAnimal = new ArrayList<>();
    private ArrayList<Thread> hilos = new ArrayList<>();

    public Simulador() {
        end = false;
        this.ciclos = manejador.getCiclos();
        cargarConfiguracion();
        agregarAnimales();
        funcionalidad();
    }

    private void cargarConfiguracion() {
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        VBox.setMargin(root, new Insets(10, 10, 10, 10));
        root.setPadding(new Insets(10, 10, 30, 10));

        grid = new GridPane();
        grid.setGridLinesVisible(true);
        llenarGrid();
        ciclosLBL = new Label("Ciclos: " + String.valueOf(manejador.getCiclos()));
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(20);
        avanzar = new Button("Avanzar");
        automatico = new Button("Automatico");
        pausa = new Button("Pausa");
        reiniciar = new Button("Reiniciar");
        salir = new Button("Salir");
        hb.getChildren().addAll(avanzar, automatico, pausa,reiniciar, salir);
        GridPane.setHgrow(grid, Priority.ALWAYS);
        GridPane.setVgrow(grid, Priority.ALWAYS);
        root.getChildren().addAll(grid, ciclosLBL, hb);

        escena = new Scene(root, 800, 800);
    }

    public Scene getEscena() {
        return escena;
    }

    public void setEscena(Scene escena) {
        this.escena = escena;
    }

    private void agregarAnimales() {
        Queue<Animal> cola = new LinkedList<>();
        ManejadorDatos.getAnimales().forEach(_item -> {
            for (int i = 0; i < manejador.getIndividuos(); i++) {
                Animal a = new Animal(_item.getTipo());
                a.getAlimentacion().setRangoAlimentacion(_item.getAlimentacion().getRangoAlimentacion());
                a.getVida().setEsperanza(_item.getVida().getEsperanza());
                a.getReproduccion().setTiempoReproduccion(_item.getReproduccion().getTiempoReproduccion());
                cola.add(a);
            }
        });
        for (int i = 0; i < manejador.getIndividuos(); i++) {
            Animal roca = new Animal("roca");
            roca.getVida().setVivo(false);
            cola.add(roca);
        }
        while (!cola.isEmpty()) {
            Animal a = cola.poll();
            while (a != null) {
                Random r = new Random();
                int fila = r.nextInt(manejador.getDimension());
                int columna = r.nextInt(manejador.getDimension());
                if (matriz[fila][columna].getChildren().isEmpty()) {
                    matriz[fila][columna].getChildren().add(a.getSprite());
                    animales[fila][columna] = a;
                    a = null;
                }

            }

        }

    }

    private void funcionalidad() {

        avanzar.setOnAction(e -> {
            
            if (ciclos <= 0) {

                ciclosLBL.setText("Ciclos: 0");
            } else {
                mover();
                for (ToolAnimal a : toolAnimal) {
                    a.getTool().setText(a.getA().toString());
                }
                ciclosLBL.setText("Ciclos: " + String.valueOf(--ciclos));
            }

        });
        automatico.setOnAction(e -> {
            automatico.setDisable(true);
            end = true;
            Thread thread = new Thread(() -> {
                while (ciclos > 0) {
                    if(!end){
                        break;
                    }
                    try {
                        Platform.runLater(() -> {
                            avanzar.fire();

                        });
                        Thread.sleep(100);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(Simulador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if(ciclos == 0){
                    automatico.setDisable(true);
                    avanzar.setDisable(true);
                    pausa.setDisable(true);
                }

            });
            hilos.add(thread);
            thread.start();
        });
        reiniciar.setOnAction(e -> {
            end = true;
            Stage stage = (Stage) reiniciar.getScene().getWindow();
            Simulador s = new Simulador();
            stage.setScene(s.getEscena());

        });

        salir.setOnAction(e -> {
            System.exit(0);
        });
        
        pausa.setOnAction(e ->{
            end = false;
            automatico.setDisable(false);
        });
        
        for (int i = 0; i < manejador.getDimension(); i++) {
            for (int j = 0; j < manejador.getDimension(); j++) {
                if (animales[i][j] != null) {
                    if (animales[i][j].getVida().isVivo()) {
                        Animal a = animales[i][j];
                        Tooltip tool = new Tooltip();
                        tool.setText(a.toString());

                        Tooltip.install(a.getSprite(), tool);
                        a.getSprite().setOnMouseEntered(e -> {
                            tool.show(root.getScene().getWindow(), e.getScreenX() + 50, e.getScreenY());
                        });
                        a.getSprite().setOnMouseExited(e -> {
                            tool.hide();
                        });
                        toolAnimal.add(new ToolAnimal(tool, a));
                    }

                }
            }
        }
    }

    private void comer(int i, int j, int fila, int columna) {
        Animal cazador = animales[i][j];
        Animal presa = animales[fila][columna];
        if (cazador.getValor() > presa.getValor() && cazador.getAlimentacion().hambre()) {
            if (presa.getTipo().equalsIgnoreCase("cadaver")) { //Condicion para que solo krill y plankton coman
                if (cazador.getValor() <= 2) {
                    cazador.getAlimentacion().setReloj(0);
                    animales[fila][columna] = cazador;
                    animales[i][j] = null;
                }

            } else {
                cazador.getAlimentacion().setReloj(0);
                animales[fila][columna] = cazador;
                animales[i][j] = null;
            }

        }
    }

    private void mover() {
        for (int i = 0; i < manejador.getDimension(); i++) {
            for (int j = 0; j < manejador.getDimension(); j++) {
                if (animales[i][j] != null) {
                    if (animales[i][j].getVida().isVivo()) {
                        Animal a = animales[i][j];
                        Random r = new Random();
                        int fila = i;
                        int columna = j;

                        if (!a.isMover()) {

                            boolean moved = false;
                            while (!moved) {
                                moved = selectBlock(i, j, fila, columna, a);
                            }

                        }
                        a.setMover(true);

                    }

                }
            }
        }
        grid.getChildren().removeAll(grid.getChildren());
        for (int i = 0; i < manejador.getDimension(); i++) {
            for (int j = 0; j < manejador.getDimension(); j++) {
                grid.add(matriz[i][j], i, j);
                matriz[i][j].getChildren().clear();
            }
        }
        for (int i = 0; i < manejador.getDimension(); i++) {
            for (int j = 0; j < manejador.getDimension(); j++) {
                if (animales[i][j] != null) {
                    if (animales[i][j].getTipo().equalsIgnoreCase("roca")) {
                        matriz[i][j].getChildren().add(animales[i][j].getSprite());
                        animales[i][j].setMover(true);
                    } else {
                        boolean condicionMuerte = animales[i][j].getVida().getTiempoVida() >= animales[i][j].getVida().getEsperanza()
                                || animales[i][j].getAlimentacion().getReloj() > animales[i][j].getAlimentacion().getRangoAlimentacion()[1];
                        if (condicionMuerte) {
                            animales[i][j].morir();
                            matriz[i][j].getChildren().add(animales[i][j].getSprite());
                            animales[i][j].setMover(false);
                        } else {
                            matriz[i][j].getChildren().add(animales[i][j].getSprite());
                            animales[i][j].ciclo();
                            animales[i][j].setMover(false);
                        }
                    }
                }

            }
        }

    }

    private void llenarGrid() {
        for (int i = 0; i < manejador.getDimension(); i++) {
            for (int j = 0; j < manejador.getDimension(); j++) {
                VBox v = new VBox();
                v.setAlignment(Pos.CENTER);
                VBox.setVgrow(v, Priority.ALWAYS);
                v.setPrefHeight(100);
                v.setPrefWidth(100);
                v.setStyle("-fx-background-color:blue;-fx-border-color: black");
                matriz[i][j] = v;
                grid.add(v, i, j);
            }
        }
    }

    private boolean selectBlock(int i, int j, int fila, int columna, Animal a) {
        Random r = new Random();
        switch (r.nextInt(4)) {
            case 0: {
                try {
                    --fila;
                    if (animales[fila][columna] == null) {
                        animales[fila][columna] = a;
                        animales[i][j] = null;
                    } else {
                        comer(i, j, fila, columna);
                    }

                } catch (ArrayIndexOutOfBoundsException ex) {
                    return false;
                }

                break;
            }
            case 1: {
                try {
                    ++columna;
                    if (animales[fila][columna] == null) {
                        animales[fila][columna] = a;
                        animales[i][j] = null;
                    } else {
                        comer(i, j, fila, columna);
                    }

                } catch (ArrayIndexOutOfBoundsException ex) {
                    return false;
                }
                break;

            }
            case 2: {
                try {
                    ++fila;
                    if (animales[fila][columna] == null) {
                        animales[fila][columna] = a;
                        animales[i][j] = null;
                    } else {
                        comer(i, j, fila, columna);
                    }

                } catch (ArrayIndexOutOfBoundsException ex) {
                    return false;
                }
                break;

            }
            case 3: {
                try {
                    --columna;
                    if (animales[fila][columna] == null) {
                        animales[fila][columna] = a;
                        animales[i][j] = null;
                    } else {
                        comer(i, j, fila, columna);
                    }

                } catch (ArrayIndexOutOfBoundsException ex) {
                    return false;
                }
                break;

            }
            default: {
                System.out.println("error");
                break;
            }
        }
        //Reproduccion
        if (animales[fila][columna].getReproduccion().getReloj() >= animales[fila][columna].getReproduccion().getTiempoReproduccion()) {
            Animal nacido = new Animal(animales[fila][columna].getTipo());
            animales[fila][columna].getReproduccion().setReloj(0);
            nacido.getAlimentacion().setRangoAlimentacion(animales[fila][columna].getAlimentacion().getRangoAlimentacion());
            nacido.getVida().setEsperanza(animales[fila][columna].getVida().getEsperanza());
            nacido.getReproduccion().setTiempoReproduccion(animales[fila][columna].getReproduccion().getTiempoReproduccion());
            Tooltip tool = new Tooltip();
            tool.setText(a.toString());

            Tooltip.install(a.getSprite(), tool);
            nacido.getSprite().setOnMouseEntered(e -> {
                tool.show(root.getScene().getWindow(), e.getScreenX() + 50, e.getScreenY());
            });
            nacido.getSprite().setOnMouseExited(e -> {
                tool.hide();
            });
            toolAnimal.add(new ToolAnimal(tool, nacido));
            animales[i][j] = nacido;
        }
        return true;
    }

}

class ToolAnimal {

    private Tooltip tool;
    private Animal a;

    public ToolAnimal(Tooltip tool, Animal a) {
        this.tool = tool;
        this.a = a;
    }

    public Tooltip getTool() {
        return tool;
    }

    public void setTool(Tooltip tool) {
        this.tool = tool;
    }

    public Animal getA() {
        return a;
    }

    public void setA(Animal a) {
        this.a = a;
    }

}
