package edu.fiuba.algo3.modelo.Enemigos.Salud;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public interface SaludEnemigo {
    public void avanzarTurno(LinkedList<Enemigo> enemigos, Enemigo enemigo);
}
