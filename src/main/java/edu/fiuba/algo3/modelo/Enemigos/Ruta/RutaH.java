package edu.fiuba.algo3.modelo.Enemigos.Ruta;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Parser.CreadorCaminoH;

public class RutaH implements Ruta{
    public Camino generarCamino(Posicion posicion)
    {
        return new Camino(CreadorCaminoH.crearCaminoH(posicion));
    }
}
