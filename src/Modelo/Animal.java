/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Animal {
    private String tipo;
    private int valor;
    private ImageView sprite;
    private Vida vida;
    private Reproduccion reproduccion;
    private Alimentacion alimentacion;
    private boolean mover;
    public Animal(String tipo) {
        this.tipo = tipo;
        this.sprite =new ImageView( new Image("/Assets/" + tipo + ".png"));
        this.vida = new Vida();
        this.reproduccion = new Reproduccion();
        this.alimentacion = new Alimentacion();
        this.mover = false;
        if(tipo.equalsIgnoreCase("orca")){
            this.valor = 5;
        }
        else if(tipo.equalsIgnoreCase("foca")){
            this.valor = 4;
        }
        else if(tipo.equalsIgnoreCase("pinguino")){
            this.valor = 3;
        }
        else if(tipo.equalsIgnoreCase("pez")){
            this.valor = 2;
        }
        else if(tipo.equalsIgnoreCase("krill")){
            this.valor = 1;
        }
        else if(tipo.equalsIgnoreCase("plankton")){
            this.valor = 0;
        }
        else{
            this.valor = 999;
        }
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
        return "tipo=" + tipo + "\nvida=" + vida + "\nreproduccion=" + reproduccion + "\nalimentacion=" + alimentacion + "\nvalor=" + valor;
    }
    
    public void ciclo(){
        vida.ciclo();
        alimentacion.ciclo();
        reproduccion.ciclo();
    }
    
    public void morir(){
        this.tipo = "cadaver";
        this.vida.morir();
        this.vida.setVivo(false);
        this.valor = -1;
        this.sprite = new ImageView( new Image("/Assets/" + "cadaver" + ".png"));
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean isMover() {
        return mover;
    }

    public void setMover(boolean mover) {
        this.mover = mover;
    }
    

}

