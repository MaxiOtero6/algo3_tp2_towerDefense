package edu.fiuba.algo3.modelo.Parcelas.Pasarela;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public abstract class Pasarela {
    
    private LinkedList<Enemigo> enemigos;

    public abstract void avanzarTurno();

    public LinkedList<Enemigo> obtenerEnemigos()
    {
        return this.enemigos;
    }
}
