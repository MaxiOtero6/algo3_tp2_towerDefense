package edu.fiuba.algo3.modelo.Defensas.Objetivos;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.NoEnemigo;

public class ObjetivoTrampa {
    public List<Enemigo> hallarObjetivo(Posicion posicionDefensa, List<Enemigo> enemigos, Defensa defensa)
    {
        Enemigo[] noEnemigo = {new NoEnemigo()};
        List<Enemigo> noEnemigoList = Arrays.asList(noEnemigo);

        List<Enemigo> enemigosEnParcela = enemigos.stream()
            .filter(enemigo -> enemigo.calcDistancia(posicionDefensa, defensa) == 0)
            .collect(Collectors.toList());
        
        if (enemigosEnParcela.size() == 0) {return noEnemigoList;}
        return enemigosEnParcela;
    }
}
