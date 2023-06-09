package edu.fiuba.algo3.modelo.Defensas;

import java.util.Comparator;
import java.util.List;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Enemigos.*;

public class DefensaBlanca extends Defensa{
    public DefensaBlanca(){
        super(10, 3, 1, 1);
    }

    public DefensaBlanca(Jugador jugador){
        super(10, 3, 1, 1);
        this.setJugador(jugador);
    }    

    @Override
    public Enemigo hallarEnemigoMasCercano(List<Enemigo> enemigos)
    {
        Enemigo enemigoMasCercano = enemigos.stream()
            .filter(enemigo -> enemigo.estaVivo())
            .filter(enemigo -> enemigo.calcDistancia(this.posicion) <= this.rango)
            .min(Comparator.comparingDouble(enemigo -> enemigo.calcDistancia(this.posicion)))
            .orElse(new NoEnemigo());

        return enemigoMasCercano;
    }


}
