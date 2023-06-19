package edu.fiuba.algo3.modelo.Enemigos.Ruta;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Parser.CreadorCaminoL;

public class RutaL implements Ruta {
    public Camino generarCamino(Posicion posicion)
    {
        return new Camino(CreadorCaminoL.crearCaminoL());
    }
}
