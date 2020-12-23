/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Animal;
import Modelo.Bloqueo;
import Modelo.ManejadorDatos;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author Walter Santacruz
 */
public class Simulador {
    private Scene escena;
    private VBox root;
    private GridPane grid;
    private Button avanzar;
    private Button automatico;
    ManejadorDatos manejador = ManejadorDatos.getInstance();
    VBox[][] matriz = new VBox[manejador.getDimension()][manejador.getDimension()];

    public Simulador() {
        cargarConfiguracion();
         agregarAnimales();
    }
    
    private void cargarConfiguracion(){
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        VBox.setMargin(root, new Insets(10,10,10,10));
        root.setPadding(new Insets(10,10,30,10));
        
        
        grid = new GridPane();
        grid.setGridLinesVisible(true);
        for(int i = 0 ; i < manejador.getDimension() ; i ++){
            for(int j = 0 ; j < manejador.getDimension() ; j++){
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
        
        HBox hb = new HBox();
        hb.setAlignment(Pos.CENTER);
        hb.setSpacing(20);
        avanzar = new Button("Avanzar");
        automatico = new Button("Automatico");
        hb.getChildren().addAll(avanzar,automatico);
        
        
        root.getChildren().addAll(grid,hb);
        
        escena = new Scene(root,800,800);
    }

    public Scene getEscena() {
        return escena;
    }

    public void setEscena(Scene escena) {
        this.escena = escena;
    }
    
    
    private void agregarAnimales(){
        Queue<Animal> cola = new LinkedList<>();
        Queue<Bloqueo> colaBloqueo = new LinkedList<>();
        ManejadorDatos.getAnimales().forEach(_item -> {
            for(int i = 0 ; i < manejador.getIndividuos();i++){
                Animal a = new Animal(_item.getTipo());
                a.getAlimentacion().setRangoAlimentacion(_item.getAlimentacion().getRangoAlimentacion());
                a.getVida().setEsperanza(_item.getVida().getEsperanza());
                a.getReproduccion().setTiempoReproduccion(_item.getReproduccion().getTiempoReproduccion());
                cola.add(a);
            }
        });
        for(int i = 0 ; i < manejador.getIndividuos() ; i ++){
            colaBloqueo.add(new Bloqueo());
        }
        while(!cola.isEmpty()){
            Animal a = cola.poll();
            while(a != null){
                Random r = new Random();
                int fila = r.nextInt(manejador.getDimension());
                int columna = r.nextInt(manejador.getDimension());
                if(matriz[fila][columna].getChildren().isEmpty()){               
                    matriz[fila][columna].getChildren().add(a.getSprite());
                    a = null;            
                }

                
            }
            
        }
        while(!colaBloqueo.isEmpty()){
            Bloqueo b = colaBloqueo.poll();
            while(b != null){
                Random r = new Random();
                int fila = r.nextInt(manejador.getDimension());
                int columna = r.nextInt(manejador.getDimension());
                if(matriz[fila][columna].getChildren().isEmpty()){               
                    matriz[fila][columna].getChildren().add(b.getSprite());
                    b = null;            
                }
            }
        }

    }
    
}
