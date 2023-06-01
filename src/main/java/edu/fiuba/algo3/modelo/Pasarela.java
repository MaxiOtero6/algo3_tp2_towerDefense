package edu.fiuba.algo3.modelo;

import java.util.LinkedList;

public abstract class Pasarela {
    
    private LinkedList<Enemigo> enemigos;

    public abstract void avanzarTurno();

    public LinkedList<Enemigo> obtenerEnemigos()
    {
        return this.enemigos;
    }
}
