package edu.fiuba.algo3.modelo;

public class Partida {
    
    private Turno turno;
    private Jugador jugador;
    private Mapa mapa;

    public Partida()
    {
        this.jugador = Jugador.obtenerJugador();
    }
}
