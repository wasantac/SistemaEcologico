/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


public class Vida {
    private int tiempoVida;
    private int esperanza;
    private boolean vivo;

    public Vida() {
        vivo = true;
        tiempoVida = 0;
        esperanza = 60;
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
    
}
