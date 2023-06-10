package edu.fiuba.algo3.modelo.Defensas.Torres;

import java.util.Comparator;
import java.util.List;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Enemigos.*;

public class TorrePlateada extends Torre {
    public TorrePlateada(){
        super(20, 5, 2, 2);
    }

    @Override
    public Enemigo hallarEnemigoMasCercano(List<Enemigo> enemigos)
    {
        Enemigo enemigoMasCercano = enemigos.stream()
            .filter(enemigo -> enemigo.esVisible())
            .filter(enemigo -> enemigo.estaVivo())
            .filter(enemigo -> enemigo.calcDistancia(this.posicion) <= this.rango)
            .min(Comparator.comparingDouble(enemigo -> enemigo.calcDistancia(this.posicion)))
            .orElse(new NoEnemigo());

        return enemigoMasCercano;
    }
    
}