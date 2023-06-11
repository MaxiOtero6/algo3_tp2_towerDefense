package edu.fiuba.algo3.modelo.Parcelas.Pasarela;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public class Meta extends Pasarela{
    
    public Meta(int coordenadaX, int coordenadaY)
    {
        super(coordenadaX, coordenadaY);
    }

    @Override
    public void avanzarTurno(){
        for (Enemigo enemigo : enemigos){
            this.daniarJugador(enemigo);
        }
        eliminarEnemigos();
    }

    public void daniarJugador(Enemigo enemigo){
        enemigo.atacar();
    }

    @Override
    protected void eliminarEnemigos()
    {
        while (enemigos.size() != 0) {
            enemigos.removeFirst();
        }
    }
}
