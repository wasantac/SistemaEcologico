/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.ManejadorDatos;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Walter Santacruz
 */
public class VentanaConfiguracion {
    private Scene escena;
    private VBox root;
    private TextField dimension;
    private TextField ciclos;
    private TextField nIndividuos;
    private ArrayList<TextField> datos;
    
    
    public VentanaConfiguracion() {
        datos = new ArrayList<>();
        crearConfiguracion();
    }

    public Scene getEscena() {
        return escena;
    }

    public void setEscena(Scene escena) {
        this.escena = escena;
    }

    public VBox getRoot() {
        return root;
    }

    public void setRoot(VBox root) {
        this.root = root;
    }
    
    private void crearConfiguracion(){
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(20);
        VBox.setMargin(root, new Insets(10,10,10,10));
        root.setPadding(new Insets(10,10,30,10));
        Label titulo = new Label("CONFIGURACION");
        titulo.setStyle("-fx-font-size: 30px; -fx-font-weight:bold");
        
        VBox contenido = new VBox();
        contenido.setSpacing(20);
        VBox.setVgrow(contenido, Priority.ALWAYS);
        
        // Dimension
        HBox dimensiones = new HBox();
        dimensiones.setAlignment(Pos.CENTER);
        dimensiones.setSpacing(10);
        Label dimensionesLBL = new Label("Dimensiones: ");
        dimension = new TextField();
        dimension.setPromptText("Ingrese el tamaño de la matriz...");
        dimension.setPrefWidth(250);
        dimensiones.getChildren().addAll(dimensionesLBL,dimension);
        //
        //Ciclos
        HBox ciclosHB = new HBox();
        ciclosHB.setAlignment(Pos.CENTER);
        ciclosHB.setSpacing(10);
        Label ciclosLBL = new Label("Numero de Ciclos: ");
        ciclos = new TextField();
        ciclos.setPrefWidth(250);
        ciclos.setPromptText("Ingrese el numero de ciclos...");
        ciclosHB.getChildren().addAll(ciclosLBL,ciclos);
        //
        //Numero Individuos
        HBox individuosHB = new HBox();
        individuosHB.setAlignment(Pos.CENTER);
        individuosHB.setSpacing(10);
        Label indLBL = new Label("Numero de Individuos");
        nIndividuos = new TextField();
        nIndividuos.setPrefWidth(250);
        nIndividuos.setPromptText("Ingrese el numero de individuos...");
        individuosHB.getChildren().addAll(indLBL,nIndividuos);
        //
        //Opciones Avanzadas
        GridPane gp = opcionesAvanzadas();
        //
        // Boton
        Button boton = new Button("Aceptar");
        boton.setPrefWidth(150);
        boton.setOnAction(e -> {
            Stage stage = (Stage)this.getEscena().getWindow();
        });
        
        //
        
        contenido.getChildren().addAll(dimensiones,ciclosHB,individuosHB,gp);
        
        root.getChildren().add(titulo);
        root.getChildren().add(contenido);
        root.getChildren().add(boton);
        escena = new Scene(root,600,600);
    }
    
    private GridPane opcionesAvanzadas(){
        ManejadorDatos.getInstance();
        GridPane gd = new GridPane();
        gd.setAlignment(Pos.CENTER);
        gd.setHgap(10);
        gd.setVgap(10);
        int contador = 0;
        for(int i = 0; i < 3 ; i++){
            for(int j = 0 ; j < 2; j++){
                VBox caja = new VBox();
                ImageView im = ManejadorDatos.getImagenes().get(contador);
                TextField txt = new TextField();
                caja.getChildren().addAll(im,txt);
                datos.add(txt);
                gd.add(caja, i, j);
                contador++;
            }
        }
        return gd;
    }
    
}
