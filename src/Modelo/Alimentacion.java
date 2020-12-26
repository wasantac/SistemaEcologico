/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Random;

public class Alimentacion {

    private int[] rangoAlimentacion;
    private int reloj;

    public Alimentacion() {
        rangoAlimentacion = new int[2];
        setRangos();
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

    public void ciclo() {
        reloj++;
    }

    public boolean hambre() {
        return rangoAlimentacion[0] <= reloj && reloj <= rangoAlimentacion[1];
    }

    @Override
    public String toString() {
        return "rangoAlimentacion={" + rangoAlimentacion[0] + "," + rangoAlimentacion[1] + "}" + ", reloj=" + reloj;
    }

    private void setRangos() {
        Random r = new Random();
        int numero1 = r.nextInt(15);
        int numero2 = r.nextInt(15);
        if (numero1 > numero2) {
            this.rangoAlimentacion[1] = numero1;
            this.rangoAlimentacion[0] = numero2;
        } else if (numero1 == numero2) {
            setRangos();
        } else {
            this.rangoAlimentacion[0] = numero1;
            this.rangoAlimentacion[1] = numero2;
        }
    }

}
