package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Enemigos.Subterraneo.EsSubterraneo;

public class Topo extends Enemigo {
    
    private int movimientosRealizados = 0;
    private static int toposMuertos = 0;

    public Topo(Jugador jugador, Camino camino)
    {
        super(1,2,0,1, jugador, camino);
        this.setSubterraneo(new EsSubterraneo());
    }

    public void atacar()
    {
        if(movimientosRealizados % 2 == 0) {
            super.atacar();
        } else {
            this.danio = 5;
            super.atacar();
        }
    }

    public void acelerar()
    {
        movimientosRealizados++;
        if(movimientosRealizados == 5) {this.velocidad++;}
        else if(movimientosRealizados == 10) {this.velocidad++;}
    }

    @Override
    public void morir()
    {
        toposMuertos++;
        enemigosMuertos++;
    }
}
