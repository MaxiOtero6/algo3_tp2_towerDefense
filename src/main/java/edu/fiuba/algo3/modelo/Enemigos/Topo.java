package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Enemigos.Subterraneo.EsSubterraneo;
import edu.fiuba.algo3.modelo.Enemigos.Subterraneo.NoEsSubterraneo;

public class Topo extends Enemigo {
    
    private int movimientosRealizados = 0;
    private static int toposMuertos = 0;
    private int numeroTurno;

    public Topo(Jugador jugador, Camino camino)
    {
        super(1,2,3,1, jugador, camino);
        this.setSubterraneo(new EsSubterraneo());
    }

    @Override
    public void atacar()
    {
        super.atacar(2 + 3 * (numeroTurno % 2));
        // 2 si numeroTurno es par
        // 5 si numeroTurno es impar
    }

    public void setNumeroTurno(int numeroTurno)
    {
        this.numeroTurno = numeroTurno;
    }

    @Override
    public void mover()
    {
        this.numeroTurno++;
        super.mover();
        acelerar();
    }

    private void acelerar()
    {
        movimientosRealizados++;
        if(movimientosRealizados == 5) {this.velocidad++; this.recargarVelocidad();}
        else if(movimientosRealizados == 10) {this.velocidad++; this.recargarVelocidad();}
    }

    @Override
    protected void morir()
    {
        toposMuertos++;
        enemigosMuertos++;
    }

    @Override
    public void ralentizar()
    {
        this.subterraneo = new NoEsSubterraneo();
        super.ralentizar();
    }

    public boolean esSubterraneo(){
        return this.subterraneo.esSubterraneo();
    }
}
