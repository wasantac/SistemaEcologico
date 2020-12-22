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
    private ImageView sprite;
    private int tiempoVida;
    private int esperanza;
    private int tiempoReproduccion;
    private int relojBiologico;

    public Animal() {
        tiempoVida = 0;
        esperanza = 60;
        tiempoReproduccion = 2;
        relojBiologico = 0;
        sprite = null;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
    }

    public int getTiempoVida() {
        return tiempoVida;
    }

    public void setTiempoVida(int tiempoVida) {
        this.tiempoVida = tiempoVida;
    }

    public int getEsperanza() {
        return esperanza;
    }

    public void setEsperanza(int esperanza) {
        this.esperanza = esperanza;
    }

    public int getTiempoReproduccion() {
        return tiempoReproduccion;
    }

    public void setTiempoReproduccion(int tiempoReproduccion) {
        this.tiempoReproduccion = tiempoReproduccion;
    }

    public int getRelojBiologico() {
        return relojBiologico;
    }

    public void setRelojBiologico(int relojBiologico) {
        this.relojBiologico = relojBiologico;
    }
    
}
