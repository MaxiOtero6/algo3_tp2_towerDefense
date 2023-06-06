package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Defensas.*;

public class Partida {

    private Turno turno;
    private Jugador jugador;
    private Mapa mapa;



    public Partida() {
        this.jugador = Jugador.obtenerJugador();;
    }
}
