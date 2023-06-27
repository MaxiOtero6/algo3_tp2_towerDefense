package edu.fiuba.algo3.modelo.Enemigos;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Enemigos.Salud.Muerto;
import edu.fiuba.algo3.modelo.Enemigos.Salud.SaludEnemigo;
import edu.fiuba.algo3.modelo.Enemigos.Salud.Vivo;
import edu.fiuba.algo3.modelo.Enemigos.Subterraneo.*;
import edu.fiuba.algo3.modelo.Enemigos.Volador.*;
import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Defensas.Defensa;

public abstract class Enemigo {
    protected int energia;
    protected int danio;
    protected int creditos;
    protected int velocidad;
    protected static int enemigosMuertos = 0;
    protected Posicion posicion;
    private Jugador jugador;
    protected Subterraneo subterraneo;
    private double multiplicadorVelocidad;
    private Volador volador;
    protected Camino camino;
    protected SaludEnemigo salud;
    protected int velocidadRestante;

    public Enemigo(int energia, int danio, int creditos, int velocidad, Jugador jugador, Camino camino){
        this.energia = energia;
        this.danio = danio;
        this.creditos = creditos;
        this.velocidad = velocidad;
        this.velocidadRestante = velocidad + 1;
        this.posicion = null;
        this.jugador = jugador;
        this.camino = camino;
        this.subterraneo = new NoEsSubterraneo();
        this.multiplicadorVelocidad = 1;
        this.volador = new NoEsVolador();
        this.salud = new Vivo();
    }

    public void setSubterraneo(Subterraneo subterraneo)
    {
        this.subterraneo = subterraneo;
    }

    public void setVolador(Volador volador)
    {
        this.volador = volador;
    }

    private void otorgarCreditos()
    {
        this.jugador.agregarCreditos(this.creditos);
    }

    public void atacar()
    {
        this.atacar(this.danio);
    }

    protected void atacar(int danioAtaque)
    {
        String tipoEnemigo = this.getClass().getSimpleName();
        this.jugador.recibirDanio(danioAtaque, tipoEnemigo);
        this.salud = new Muerto();
        this.velocidadRestante = 0;
    }

    public void recibirDanio(int danioRecibido, String tipoTorre)
    {
        this.energia -= danioRecibido;
        if (this.energia <= 0)
        {
            this.otorgarCreditos();
            this.morir();
            this.salud = new Muerto();
        }
        String tipoEnemigo = this.getClass().getSimpleName();
        String coordenadas;
        try {
            coordenadas = posicion.imprimirPosicion();
        }catch (NullPointerException e){
            coordenadas = "null";
    }
        SingleLogger.obtenerLogger().imprimirLog(String.format(
                "%s ataca a %s en la posicion %s", tipoTorre, tipoEnemigo, coordenadas));
    }

    protected abstract void morir(); 

    public void setearPosicion(Posicion posicion)
    {
        this.posicion = posicion;
    }

    public void comprobarSalud(LinkedList<Enemigo> enemigos)
    {
        this.salud.comprobarSalud(enemigos, this);
    }

    public void mover()
    {
        this.velocidadRestante -= 1;
        this.camino.moverEnemigo((int)(this.velocidadRestante * this.multiplicadorVelocidad), this.posicion, this);
    }

    public void eliminar()
    {
        this.camino.eliminarEnemigo(posicion, this);
    }

    @Override
    public boolean equals(Object enemigo)
    {
        if (enemigo == this) {return true;}
        if (enemigo.getClass() == this.getClass())
        {
            Enemigo enem = (Enemigo)enemigo;
            if (this.posicion == null && enem.posicion == null) {return true;}
            return (this.posicion.equals(enem.posicion) && this.energia == enem.energia);
        }
        return false;
    }

    public double calcDistancia(Posicion posicion, Defensa defensa)
    {
        return (
                this.posicion.calcDistancia(posicion) 
                + volador.incrementarDistancia(defensa) 
                + subterraneo.incrementarDistancia(defensa)
                + salud.incrementarDistancia()
                );
    }

    public void ralentizar()
    {
        this.multiplicadorVelocidad = 0.5;
    }

    //getter posicion

    public Posicion obtenerPosicion()
    {
        return this.posicion;
    }

    public void recargarVelocidad()
    {
        this.velocidadRestante = velocidad + 1;
        this.multiplicadorVelocidad = 1;
    }

    public void setDefensas(LinkedList<Defensa> defensas){}

}
