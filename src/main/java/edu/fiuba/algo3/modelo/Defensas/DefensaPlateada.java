package edu.fiuba.algo3.modelo.Defensas;

import java.util.Comparator;
import java.util.List;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Enemigos.*;

public class DefensaPlateada extends Defensa {
    public DefensaPlateada(List<Enemigo> enemigos){
        super(20, 5, 2, 2, enemigos);
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