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
        this.posicion = new Posicion(0, 0);

    }

    public void otorgarCreditos()
    {
        Jugador.obtenerJugador().agregarCreditos(this.creditos);
    }

    public void atacar()
    {
        Jugador.obtenerJugador().recibirDanio(this.danio);
    }

    public abstract void recibirDanio(int danioRecibido);

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
        Camino camino = Camino.obtenerCamino();
        camino.moverEnemigo(velocidad, posicion, this);
    }

    // public void avanzar()
    // {

    // }
    @Override
    public boolean equals(Object enemigo)
    {
        if (enemigo == this) {return true;}
        if (enemigo.getClass() == this.getClass())
        {
            Enemigo enem = (Enemigo)enemigo;
            return (this.posicion.equals(enem.obtenerPosicion()));
        }
        return false;
    }
}
