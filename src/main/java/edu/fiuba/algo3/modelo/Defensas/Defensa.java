package edu.fiuba.algo3.modelo.Defensas;

import java.util.List;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Defensas.Salud.Destruida;
import edu.fiuba.algo3.modelo.Defensas.Salud.Operativa;
import edu.fiuba.algo3.modelo.Defensas.Salud.Salud;
import edu.fiuba.algo3.modelo.Enemigos.*;

public abstract class Defensa {
    
    private int coste;
    protected Posicion posicion;
    protected List<Enemigo> enemigos;
    private Salud salud;

    public Defensa(int coste)
    {
        this.coste = coste;
        this.salud = new Operativa();
    }

    public boolean estaDestruida()
    {
        return this.salud.salud();
    }

    public void destruir()
    {
        this.salud = new Destruida();
    }

    public abstract void avanzarTurno();

    public abstract void atacar();

    public void gastarCreditos(Jugador jugador)
    {
        jugador.gastarCreditos(this.coste);
    }

    public void setEnemigos(List<Enemigo> enemigos)
    {
        this.enemigos = enemigos;
    }

    public void setearPosicion(Posicion posicion)
    {
        this.posicion = posicion;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) {return true;}
        if (o.getClass() == this.getClass())
        {
            Defensa defensa = (Defensa)o;
            if (this.posicion == null && defensa.posicion == null) {return true;}
            return (this.posicion.equals(defensa.posicion) && this.estaDestruida() == defensa.estaDestruida());
        }
        return false;
    }
}
