package edu.fiuba.algo3.modelo.Defensas;

import java.util.Comparator;
import java.util.List;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Enemigos.*;

public class DefensaPlateada extends Defensa {
    public DefensaPlateada(Posicion posicion, List<Enemigo> enemigos){
        super(20, 5, 2, 2, posicion, enemigos);
    }

    @Override
    public Enemigo hallarEnemigoMasCercano(List<Enemigo> enemigos)
    {
        Enemigo enemigoMasCercano = enemigos.stream()
            .filter(enemigo -> Posicion.calcDistancia(this.posicion, enemigo.obtenerPosicion()) < this.rango)
            .min(Comparator.comparingDouble(enemigo -> Posicion.calcDistancia(this.posicion, enemigo.obtenerPosicion())))
            .orElse(null);

        return enemigoMasCercano;
    }
    
}