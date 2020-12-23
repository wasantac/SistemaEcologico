/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Walter Santacruz
 */
public class Animal {
    private String tipo;
    private ImageView sprite;
    private Vida vida;
    private Reproduccion reproduccion;
    private Alimentacion alimentacion;
    public Animal(String tipo) {
        this.tipo = tipo;
        this.sprite =new ImageView( new Image("/Assets/" + tipo + ".png"));
        this.vida = new Vida();
        this.reproduccion = new Reproduccion();
        this.alimentacion = new Alimentacion();
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
    }

    public Vida getVida() {
        return vida;
    }

    public void setVida(Vida vida) {
        this.vida = vida;
    }

    public Reproduccion getReproduccion() {
        return reproduccion;
    }

    public void setReproduccion(Reproduccion reproduccion) {
        this.reproduccion = reproduccion;
    }

    public Alimentacion getAlimentacion() {
        return alimentacion;
    }

    public void setAlimentacion(Alimentacion alimentacion) {
        this.alimentacion = alimentacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "tipo=" + tipo + "\nvida=" + vida + "\nreproduccion=" + reproduccion + "\nalimentacion=" + alimentacion;
    }
    
    public void ciclo(){
        vida.ciclo();
        alimentacion.ciclo();
        reproduccion.ciclo();
    }
    

}

