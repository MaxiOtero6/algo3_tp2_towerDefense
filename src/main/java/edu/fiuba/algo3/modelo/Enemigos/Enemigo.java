package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;

public class Enemigo {
    private int energia;
    private int danio;
    protected int creditos;
    private int velocidad;
    protected static int enemigosMuertos = 0;
    private Posicion posicion;

    public Enemigo(int energia, int danio, int creditos, int velocidad, Posicion posicion){
        this.energia = energia;
        this.danio = danio;
        this.creditos = creditos;
        this.velocidad = velocidad;
        this.posicion = posicion;
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

    public Posicion obtenerPosicion()
    {
        return this.posicion;
    }

    // public void avanzar()
    // {

    // }
}
