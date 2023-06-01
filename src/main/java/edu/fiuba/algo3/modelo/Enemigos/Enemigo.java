package edu.fiuba.algo3.modelo.Enemigos;

public class Enemigo {
    private int energia;
    private int danio;
    protected int creditos;
    private int velocidad;
    protected static int enemigosMuertos = 0;

    public Enemigo(int energia, int danio, int creditos, int velocidad){
        this.energia = energia;
        this.danio = danio;
        this.creditos = creditos;
        this.velocidad = velocidad;
    }

    // public int otorgarCreditos()
    // {
        
    // }

    // public void atacar()
    // {
        
    // }

    // public void recibirDanio(int danioRecibido)
    // {

    // }

    // public void avanzar()
    // {

    // }
}
