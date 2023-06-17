package edu.fiuba.algo3.modelo.Enemigos.Subterraneo;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;

public class EsSubterraneo implements Subterraneo{
    @Override
    public int subterraneo(Defensa defensa) {
        if (defensa instanceof TorreBlanca) {return 1000;}
        return 0;    
    }
}
