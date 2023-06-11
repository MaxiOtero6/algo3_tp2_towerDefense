package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Enemigos.Volador.EsVolador;

public class Lechuza extends Enemigo {
    
    public Lechuza(Jugador jugador) {
        super(5,0,0,5, true, jugador);
        this.setVolador(new EsVolador());
    }

    @Override
    public void morir() {}
}
