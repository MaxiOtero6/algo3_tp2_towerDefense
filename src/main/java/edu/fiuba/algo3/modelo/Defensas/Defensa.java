package edu.fiuba.algo3.modelo.Defensas;

import java.util.List;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Enemigos.*;

public abstract class Defensa {
    
    private int coste;
    protected int rango;
    private int danio;
    protected Posicion posicion;
    private int progresoConstruccion;
    private Estado estado;
    protected List<Enemigo> enemigos;
    private Jugador jugador;


    public Defensa(int coste, int rango, int danio, int progresoConstruccion)
    {
        this.coste = coste;
        this.rango = rango;
        this.danio = danio;
        this.progresoConstruccion = progresoConstruccion;
        this.estado = new EstadoDesactivado();
    }

    public void setJugador(Jugador jugador) 
    {
        this.jugador = jugador;
    }

    public void setEnemigos(List<Enemigo> enemigos)
    {
        this.enemigos = enemigos;
    }

    public void setearPosicion(Posicion posicion)
    {
        this.posicion = posicion;
    }

    public void avanzarTurno(){
        this.estado.avanzarTurno(this);
    }

    public boolean progresarConstruccion(){
        this.progresoConstruccion -= 1;
        return this.chequearProgreso();
    }

    public boolean chequearProgreso(){
        return this.progresoConstruccion == 0;
    }

    public void cambiarEstado(){
        this.estado = new EstadoActivado();
    }

    public abstract Enemigo hallarEnemigoMasCercano(List<Enemigo> enemigos);

    public void atacar()
    {
        Enemigo enemigoMasCercano = this.hallarEnemigoMasCercano(this.enemigos);
        enemigoMasCercano.recibirDanio(this.danio);
    }

    public void gastarCreditos()
    {
        this.jugador.gastarCreditos(this.coste);
    }
}