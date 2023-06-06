package edu.fiuba.algo3.modelo.Parcelas.Pasarela;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public class Meta extends Pasarela{
    
    public Meta(Posicion posicion){
        super(posicion);
    }

    @Override
    public void avanzarTurno(){
        for (Enemigo enemigo : enemigos){
            this.daniarJugador(enemigo);
        }
    }

    public void daniarJugador(Enemigo enemigo){
        enemigo.atacar();
    }
}
