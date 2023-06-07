package edu.fiuba.algo3.modelo.Parcelas.Pasarela;

import java.util.List;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Errores.SpawnNoEnLargadaError;

public class Meta extends Pasarela{
    
    public Meta(Posicion posicion){
        super(posicion);
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

    @Override
    public void aparecerEnemigos(List<Enemigo> enemigos) throws SpawnNoEnLargadaError
    {
        throw new SpawnNoEnLargadaError();
    }
}
