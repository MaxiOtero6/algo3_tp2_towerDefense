package edu.fiuba.algo3.modelo.Enemigos.Ruta;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Posicion;

public interface Ruta {
    public Camino generarCamino(Posicion posicion);
}
