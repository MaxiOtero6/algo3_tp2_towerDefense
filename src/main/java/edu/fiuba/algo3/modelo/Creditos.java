package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Errores.CreditosInsuficientesError;

public class Creditos {

    private int creditos;

    public Creditos(){
        this.creditos = 100;
    }

    public int obtenerCreditos()
    {
        return this.creditos;
    }

    public boolean gastarCreditos(int creditosAGastar){
        if (this.creditos < creditosAGastar){
            throw new CreditosInsuficientesError();
        }
        else {
            creditos -= creditosAGastar;
            return true;
        }
    }

    public void agregarCreditos(int creditos)
    {
        this.creditos += creditos;
    }

    public void eliminarCreditos(int creditos)
    {
        this.creditos -= creditos;
    }

}