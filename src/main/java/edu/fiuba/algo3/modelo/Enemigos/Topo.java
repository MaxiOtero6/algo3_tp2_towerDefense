package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Camino;

public class Topo extends Enemigo {
    
    private static int movimientosRealizados = 0;
    public Topo(Jugador jugador)
    {
        super(1,2,0,1, true, jugador);
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

    public int obtenerVelocidad()
    {
        if(movimientosRealizados == 5) {this.velocidad = 2;}
        if(movimientosRealizados == 10) {this.velocidad = 3;}
        movimientosRealizados ++;
        return this.velocidad;
    }

    @Override
    public void morir()
    {
        enemigosMuertos++;
    }
}
