/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Random;


public class Reproduccion {
    private int tiempoReproduccion;
    private int reloj;

    public Reproduccion() {
        setReproduccion();
        reloj = 0;
    }

    public int getTiempoReproduccion() {
        return tiempoReproduccion;
    }

    public void setTiempoReproduccion(int tiempoReproduccion) {
        this.tiempoReproduccion = tiempoReproduccion;
    }

    public int getReloj() {
        return reloj;
    }

    public void setReloj(int reloj) {
        this.reloj = reloj;
    }
    public void ciclo(){
        reloj++;
    }

    @Override
    public String toString() {
        return  "tiempoReproduccion=" + tiempoReproduccion + ", reloj=" + reloj;
    }
    
    private void setReproduccion(){
        Random r = new Random();
        int numero = r.nextInt(20);
        if(numero >= 10){
            this.tiempoReproduccion = numero;
        }
        else{
            setReproduccion();
        }
    }

}
