/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ManejadorDatos {
   
    private static ArrayList<ImageView> imagenes  = new ArrayList<>();
    private static String[] especies = {"orca","foca","pinguino","pez","krill","plankton"};
    private static ArrayList<Animal> animales = new ArrayList<>();
    private int dimension =10;
    private int ciclos = 60;
    private int individuos = 5;
    
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
        for(int i = 0 ; i < 6; i++){
            animales.add(new Animal(especies[i]));
        }
    }

    public static String[] getEspecies() {
        return especies;
    }

    public static void setEspecies(String[] especies) {
        ManejadorDatos.especies = especies;
    }

    public static ArrayList<Animal> getAnimales() {
        return animales;
    }

    public static void setAnimales(ArrayList<Animal> animales) {
        ManejadorDatos.animales = animales;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getCiclos() {
        return ciclos;
    }

    public void setCiclos(int ciclos) {
        this.ciclos = ciclos;
    }

    public int getIndividuos() {
        return individuos;
    }

    public void setIndividuos(int individuos) {
        this.individuos = individuos;
    }
    
    
}
