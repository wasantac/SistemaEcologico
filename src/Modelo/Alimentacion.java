/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;


public class Alimentacion {
    private int[] rangoAlimentacion;
    private int reloj;

    public Alimentacion() {
        rangoAlimentacion = new int[2];
        rangoAlimentacion[0] = 10;
        rangoAlimentacion[1] = 15;
        reloj = 0;
    }

    public int[] getRangoAlimentacion() {
        return rangoAlimentacion;
    }

    public void setRangoAlimentacion(int[] rangoAlimentacion) {
        this.rangoAlimentacion = rangoAlimentacion;
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
    
    public boolean hambre(){
        return rangoAlimentacion[0] <= reloj &&  reloj <= rangoAlimentacion[1];
    }

    @Override
    public String toString() {
        return "rangoAlimentacion={" + rangoAlimentacion[0] + "," + rangoAlimentacion[1] + "}" + ", reloj=" + reloj;
    }
    
}
