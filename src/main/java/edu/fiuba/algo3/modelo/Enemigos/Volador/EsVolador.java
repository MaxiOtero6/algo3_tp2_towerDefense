package edu.fiuba.algo3.modelo.Enemigos.Volador;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;

public class EsVolador implements Volador{
    @Override
    public int volador(Defensa defensa)
    {
        if (defensa instanceof TrampaArenosa) {return 1000;}
        return 0;
    }
}
