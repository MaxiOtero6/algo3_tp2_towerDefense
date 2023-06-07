package edu.fiuba.algo3.modelo.Defensas;

import java.util.Comparator;
import java.util.List;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Enemigos.*;

public class DefensaBlanca extends Defensa{
    public DefensaBlanca(Posicion posicion, List<Enemigo> enemigos){
        super(10, 3, 1, 1, posicion, enemigos);
    }

    @Override
    public Enemigo hallarEnemigoMasCercano(List<Enemigo> enemigos)
    {
        Enemigo enemigoMasCercano = enemigos.stream()
            .filter(enemigo -> enemigo.calcDistancia(this.posicion) < this.rango)
            .min(Comparator.comparingDouble(enemigo -> enemigo.calcDistancia(this.posicion)))
            .orElse(null);

        return enemigoMasCercano;
    }


}
