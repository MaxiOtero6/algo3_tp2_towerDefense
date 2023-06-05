package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Defensas.ConstructorDeDefensas;
import edu.fiuba.algo3.modelo.Defensas.*;

public class Partida {

    private Turno turno;
    private Jugador jugador;
    private Mapa mapa;

    private ConstructorDeDefensas constructorDeDefensas;

    public Partida() {
        this.jugador = Jugador.obtenerJugador();
        this.constructorDeDefensas = new ConstructorDeDefensas();
    }
}
