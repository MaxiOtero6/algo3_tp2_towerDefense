package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;

public abstract class Enemigo {
    protected int energia;
    private int danio;
    protected int creditos;
    private int velocidad;
    protected static int enemigosMuertos = 0;
    private Posicion posicion;

    public Enemigo(int energia, int danio, int creditos, int velocidad){
        this.energia = energia;
        this.danio = danio;
        this.creditos = creditos;
        this.velocidad = velocidad;
        this.posicion = null;
    }

    public void otorgarCreditos()
    {
        Jugador.obtenerJugador().agregarCreditos(this.creditos);
    }

    public void atacar()
    {
        Jugador.obtenerJugador().recibirDanio(this.danio);
    }

    public void recibirDanio(int danioRecibido)
    {
        this.energia -= danioRecibido;
        if (this.energia <= 0)
        {
            this.morir();
        }
    }

    public abstract void morir(); 

    public int obtenerEnergia()
    {
        return this.energia;
    }

    public Posicion obtenerPosicion()
    {
        return this.posicion;
    }

    public void setearPosicion(Posicion posicion)
    {
        this.posicion = posicion;
    }

    public void mover()
    {
        if (this.posicion != null)
        {
            if (this.energia > 0)
            {
                Camino camino = Camino.obtenerCamino();
                camino.moverEnemigo(velocidad, posicion, this);
            }
        }
        else
        {
            //Log no se puede mover un enemigo q no esta en una posicion
        }
    }

    @Override
    public boolean equals(Object enemigo)
    {
        if (enemigo == this) {return true;}
        if (enemigo instanceof Enemigo)
        {
            Enemigo enem = (Enemigo)enemigo;
            if (this.posicion == null && enem.obtenerPosicion() == null) {return true;}
            return (this.posicion.equals(enem.obtenerPosicion()));
        }
        return false;
    }

    public double calcDistancia(Posicion posicion)
    {
        return Posicion.calcDistancia(posicion, this.posicion);
    }
}
