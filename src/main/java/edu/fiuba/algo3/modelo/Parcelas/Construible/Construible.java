package edu.fiuba.algo3.modelo.Parcelas.Construible;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Defensas.Defensa;

public interface Construible {
    
    public Defensa construir(Defensa defensa, Posicion posicion);

}
