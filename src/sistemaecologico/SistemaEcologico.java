/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemaecologico;

import Vista.VentanaConfiguracion;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Walter Santacruz
 */
public class SistemaEcologico extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        VentanaConfiguracion ventana = new VentanaConfiguracion();
       
        
        Scene scene = ventana.getEscena();
        
        primaryStage.setTitle("Configuracion");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
