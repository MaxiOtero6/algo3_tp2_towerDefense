package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Enemigos.Subterraneo.*;
import edu.fiuba.algo3.modelo.Enemigos.Volador.*;

public abstract class Enemigo {
    protected int energia;
    protected int danio;
    protected int creditos;
    protected int velocidad;
    protected static int enemigosMuertos = 0;
    private Posicion posicion;
    private Jugador jugador;
    private Subterraneo subterraneo;
    private double multiplicadorVelocidad;
    private Volador volador;

    public Enemigo(int energia, int danio, int creditos, int velocidad, Jugador jugador){
        this.energia = energia;
        this.danio = danio;
        this.creditos = creditos;
        this.velocidad = velocidad;
        this.posicion = null;
        this.jugador = jugador;
        this.subterraneo = new NoEsSubterraneo();
        this.multiplicadorVelocidad = 1;
        this.volador = new NoEsVolador();
    }

    public void setSubterraneo(Subterraneo subterraneo)
    {
        this.subterraneo = subterraneo;
    }

    public boolean subterraneo()
    {
        return this.subterraneo.subterraneo();
    }

    public void setVolador(Volador volador)
    {
        this.volador = volador;
    }

    public boolean volador()
    {
        return this.volador.volador();
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

    public void mover(Camino camino)
    {
        if (this.posicion != null)
        {
            if (this.estaVivo())
            {
                int velocidadActual = this.obtenerVelocidad();
                camino.moverEnemigo((int)(velocidadActual * multiplicadorVelocidad), posicion, this);
                this.multiplicadorVelocidad = 1;
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

    public int obtenerVelocidad()
    {
        return velocidad;
    }

    public void ralentizar()
    {
        this.multiplicadorVelocidad = 0.5;
    }
}
