/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Random;


public class Vida {
    private int tiempoVida;
    private int esperanza;
    private boolean vivo;

    public Vida() {
        vivo = true;
        tiempoVida = 0;
        setVida();
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

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
    
    public void ciclo(){
        tiempoVida++;
    }
    
    public void morir(){
        vivo = false;
    }

    @Override
    public String toString() {
        return "tiempoVida=" + tiempoVida + ", esperanza=" + esperanza + ", vivo=" + vivo ;
    }
    private void setVida(){
        Random r = new Random();
        int numero = r.nextInt(60);
        if(numero > 40){
            this.esperanza = numero;
        }
        else{
            setVida();
        }
    }
    
}
