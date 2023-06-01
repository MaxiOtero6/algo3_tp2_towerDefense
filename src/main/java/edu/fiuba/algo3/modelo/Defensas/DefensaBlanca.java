package edu.fiuba.algo3.modelo.Defensas;

import java.util.Comparator;
import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Enemigos.*;

public class DefensaBlanca extends Defensa{
    public DefensaBlanca(Posicion posicion){
        super(10, 3, 1, 1, posicion);
    }

    @Override
    public Enemigo hallarEnemigoMasCercano(LinkedList<Enemigo> enemigos)
    {
        Enemigo enemigoMasCercano = enemigos.stream()
            .filter(enemigo -> Posicion.calcDistancia(this.posicion, enemigo.obtenerPosicion()) < this.rango)
            .min(Comparator.comparingDouble(enemigo -> Posicion.calcDistancia(this.posicion, enemigo.obtenerPosicion())))
            .orElse(null);

        return enemigoMasCercano;
    }


}
