package edu.fiuba.algo3.modelo.Defensas.Objetivos;

import java.util.Comparator;
import java.util.List;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.NoEnemigo;

public class ObjetivoTorre {
    public Enemigo hallarObjetivo(Posicion posicionTorre, List<Enemigo> enemigos, int rango)
    {
        Enemigo enemigoMasCercano = enemigos.stream()
            .filter(enemigo -> !enemigo.subterraneo())
            .filter(enemigo -> enemigo.estaVivo())
            .filter(enemigo -> enemigo.calcDistancia(posicionTorre) <= rango)
            .min(Comparator.comparingDouble(enemigo -> enemigo.calcDistancia(posicionTorre)))
            .orElse(new NoEnemigo());

        return enemigoMasCercano;
    }
}
