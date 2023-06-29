package edu.fiuba.algo3.modelo.Enemigos.Subterraneo;

import edu.fiuba.algo3.modelo.Defensas.Defensa;

public class NoEsSubterraneo implements Subterraneo{
    @Override
    public int incrementarDistancia(Defensa defensa) {
        return 0;    
    }

    //Getter vista
    public boolean esSubterraneo(){
        return false;
    }
}
