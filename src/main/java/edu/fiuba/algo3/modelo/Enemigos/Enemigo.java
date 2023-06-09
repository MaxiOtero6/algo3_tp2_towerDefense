package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;

public abstract class Enemigo {
    private Camino camino;
    protected int energia;
    private int danio;
    protected int creditos;
    private int velocidad;
    protected static int enemigosMuertos = 0;
    private Posicion posicion;
    private Jugador jugador;

    public Enemigo(int energia, int danio, int creditos, int velocidad, Jugador jugador, Camino camino){
        this.energia = energia;
        this.danio = danio;
        this.creditos = creditos;
        this.velocidad = velocidad;
        this.posicion = null;
        this.jugador = jugador;
        this.camino = camino;
    }

    public Enemigo(int energia, int danio, int creditos, int velocidad, Jugador jugador){
        this.energia = energia;
        this.danio = danio;
        this.creditos = creditos;
        this.velocidad = velocidad;
        this.posicion = null;
        this.jugador = jugador;
        this.camino = null;
    }

    public void otorgarCreditos()
    {
        this.jugador.agregarCreditos(this.creditos);
    }

    public void atacar()
    {
        this.jugador.recibirDanio(this.danio);
    }

    public void recibirDanio(int danioRecibido)
    {
        this.energia -= danioRecibido;
        if (!estaVivo())
        {
            this.morir();
        }
    }

    public boolean estaVivo()
    {
        return this.energia > 0;
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
            if (this.estaVivo())
            {
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
            if (this.posicion == null && enem.posicion == null) {return true;}
            return (this.posicion.equals(enem.posicion));
        }
        return false;
    }

    public double calcDistancia(Posicion posicion)
    {
        return Posicion.calcDistancia(posicion, this.posicion);
    }
}
