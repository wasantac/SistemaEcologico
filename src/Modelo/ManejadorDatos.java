/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Walter Santacruz
 */
public class ManejadorDatos {
    private static ArrayList<ImageView> imagenes  = new ArrayList<>();;
    private ManejadorDatos() { 
        AgregarDatos();
    }
    
    public static ManejadorDatos getInstance() {
        return ManejadorDatosHolder.INSTANCE;
    }
    
    private static class ManejadorDatosHolder {

        private static final ManejadorDatos INSTANCE = new ManejadorDatos();
    }

    public static ArrayList<ImageView> getImagenes() {
        return imagenes;
    }

    public static void setImagenes(ArrayList<ImageView> imagenes) {
        ManejadorDatos.imagenes = imagenes;
    }
    
    private static void AgregarDatos(){
        imagenes.add(new ImageView(new Image("/Assets/orca.png")));
        imagenes.add(new ImageView(new Image("/Assets/foca.png")));
        imagenes.add(new ImageView(new Image("/Assets/pinguino.png")));
        imagenes.add(new ImageView(new Image("/Assets/pez.png")));
        imagenes.add(new ImageView(new Image("/Assets/krill.png")));
        imagenes.add(new ImageView(new Image("/Assets/plankton.png")));
    }
    
    
}
