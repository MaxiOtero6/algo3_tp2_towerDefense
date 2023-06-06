package edu.fiuba.algo3.modelo;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;
import edu.fiuba.algo3.modelo.Errores.GanarPartidaError;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Pasarela;

public class Turno {

    private LinkedList<Defensa> defensas;
    private LinkedList<Enemigo> enemigos;
    private LinkedList<Pasarela> pasarelas;


    public Turno(){
        this.enemigos = new LinkedList<>();
    }
    public void avanzarTurno() {
        avanzarEnemigos();

    }
    private void avanzarEnemigos(){
        if (!(Camino.obtenerCamino().tieneEnemigos()) && Jugador.obtenerJugador().obtenerVida() > 0){
            throw new GanarPartidaError();
        }
    }

}
