package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Jugador;

public class Lechuza extends Enemigo {
    
    public Lechuza(Jugador jugador) {
        super(5,0,0,5, true, jugador);
    }

    @Override
    public void morir() {}
}
