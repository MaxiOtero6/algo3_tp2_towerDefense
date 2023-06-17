package edu.fiuba.algo3.modelo.Enemigos.Volador;

import edu.fiuba.algo3.modelo.Defensas.Defensa;

public class NoEsVolador implements Volador{
    @Override
    public int volador(Defensa defensa)
    {
        return 0;
    }
}
