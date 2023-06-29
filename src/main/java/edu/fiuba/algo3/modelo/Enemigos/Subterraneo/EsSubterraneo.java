package edu.fiuba.algo3.modelo.Enemigos.Subterraneo;

import edu.fiuba.algo3.modelo.Defensas.Defensa;

public class EsSubterraneo implements Subterraneo{
    @Override
    public int incrementarDistancia(Defensa defensa) {
        return defensa.incrementarDistanciaS();   
    }

    //Getter vista
    public boolean esSubterraneo(){
        return true;
    }
}
