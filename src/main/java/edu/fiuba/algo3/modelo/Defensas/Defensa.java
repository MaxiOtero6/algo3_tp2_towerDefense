package edu.fiuba.algo3.modelo.Defensas;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Enemigos.*;
// Verificar que cada defensa tarde en construirse lo que dice que tarda y que recién están
// “operativas” cuando ya se terminaron de construir.



public abstract class Defensa {
    
    private int coste;
    protected int rango;
    private int danio;
    protected Posicion posicion;
    private int progresoConstruccion;
    Estado estado;

    public Defensa(int coste, int rango, int danio, int progresoConstruccion, Posicion posicion){
        this.coste = coste;
        this.rango = rango;
        this.danio = danio;
        this.progresoConstruccion = progresoConstruccion;
        this.estado = new EstadoDesactivado(this);
        this.posicion = posicion;
    }

    public void avanzarTurno(){
        this.estado.avanzarTurno();
    }

    public boolean progresarConstruccion(){
        this.progresoConstruccion -= 1;
        return this.progresoConstruccion == 0;
    }

    public void cambiarEstado(){
        this.estado = new EstadoActivado(this);
    }
    public boolean chequearProgreso(){
        return this.progresoConstruccion == 0;
    }

    public abstract Enemigo hallarEnemigoMasCercano(LinkedList<Enemigo> enemigos);

    public void atacar(Enemigo enemigo){
        enemigo.recibirDanio(this.danio);
    }

    public void gastarCreditos(){
        Jugador jugador = Jugador.obtenerJugador();
        jugador.gastarCreditos(this.coste);
    }
}