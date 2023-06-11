package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Enemigos.Volador.EsVolador;

public class Lechuza extends Enemigo {
    
    public Lechuza(Jugador jugador, Camino camino) {
        super(5,0,0,5, jugador, camino);
        this.setVolador(new EsVolador());
    }

    @Override
    public void morir() {}
}
