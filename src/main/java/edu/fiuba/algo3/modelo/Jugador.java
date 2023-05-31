package edu.fiuba.algo3.modelo;

public class Jugador {
    private int creditos;
    private int vida;
    public Jugador(){
        this.creditos = 100;
        this.vida = 20;
    }
    public int obtenerCreditos(){
        return this.creditos;
    }
    public int obtenerVida(){
        return this.vida;
    }
}
