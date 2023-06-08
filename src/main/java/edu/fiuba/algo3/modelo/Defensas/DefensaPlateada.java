package edu.fiuba.algo3.modelo.Defensas;

import java.util.Comparator;
import java.util.List;

import edu.fiuba.algo3.modelo.Enemigos.*;

public class DefensaPlateada extends Defensa {
    public DefensaPlateada(){
        super(20, 5, 2, 2);
    }

    @Override
    public Enemigo hallarEnemigoMasCercano(List<Enemigo> enemigos)
    {
        Enemigo enemigoMasCercano = enemigos.stream()
            .filter(enemigo -> enemigo.calcDistancia(this.posicion) <= this.rango && enemigo.estaVivo())
            .min(Comparator.comparingDouble(enemigo -> enemigo.calcDistancia(this.posicion)))
            .orElse(null);

        return enemigoMasCercano;
    }
    
}