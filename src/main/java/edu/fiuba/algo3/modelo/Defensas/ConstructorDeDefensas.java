package edu.fiuba.algo3.modelo.Defensas;

import edu.fiuba.algo3.modelo.Jugador;

public class ConstructorDeDefensas {
    private int costeBlanca = 10;
    private int costePlateada = 20;

    public Defensa construir(String torre) {
        Jugador jugador = Jugador.obtenerJugador();
        if (torre.equalsIgnoreCase("plateada")) {
            if (jugador.comprobarCreditos(costePlateada)) {
                return new DefensaPlateada(null);
            }
        } else if (torre.equalsIgnoreCase("blanca")) {
            if (jugador.comprobarCreditos(costeBlanca)) {
                return new DefensaBlanca(null);
            }
        }
        return null;
    }
}

