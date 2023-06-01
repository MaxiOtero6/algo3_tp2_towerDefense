package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Jugador;

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

    public void otorgarCreditos()
    {
        Jugador.obtenerJugador().agregarCreditos(this.creditos);
    }

    public void atacar()
    {
        Jugador.obtenerJugador().recibirDanio(this.danio);
    }

    public void recibirDanio(int danioRecibido)
    {
        this.energia -= danioRecibido;
    }

    public int obtenerEnergia()
    {
        return this.energia;
    }

    // public void avanzar()
    // {

    // }
}
