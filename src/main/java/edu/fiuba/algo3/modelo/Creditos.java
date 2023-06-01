package edu.fiuba.algo3.modelo;

public class Creditos {

    private int creditos;

    public Creditos(){
        this.creditos = 100;
    }

    public int obtenerCreditos()
    {
        return this.creditos;
    }
    public boolean comprobarCreditos(int creditosAGastar){
        if (this.creditos < creditosAGastar){
            return false;
        }
        else {
            creditos -= creditosAGastar;
            return true;
        }
    }

}