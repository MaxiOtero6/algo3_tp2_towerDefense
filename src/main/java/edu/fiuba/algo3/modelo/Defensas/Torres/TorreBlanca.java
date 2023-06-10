package edu.fiuba.algo3.modelo.Defensas.Torres;

import java.util.Comparator;
import java.util.List;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Enemigos.*;

public class TorreBlanca extends Torre{
    public TorreBlanca(){
        super(10, 3, 1, 1);
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
