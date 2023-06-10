package edu.fiuba.algo3.modelo.Defensas;

import java.util.List;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Enemigos.*;

public abstract class Defensa {
    
    private int coste;
    protected Posicion posicion;
    protected List<Enemigo> enemigos;

    public Defensa(int coste)
    {
        this.coste = coste;
    }

    public abstract void avanzarTurno();

    public abstract void atacar();

    public void gastarCreditos(Jugador jugador)
    {
        jugador.gastarCreditos(this.coste);
    }

    public void setEnemigos(List<Enemigo> enemigos)
    {
        this.enemigos = enemigos;
    }

    public void setearPosicion(Posicion posicion)
    {
        this.posicion = posicion;
    }
}
