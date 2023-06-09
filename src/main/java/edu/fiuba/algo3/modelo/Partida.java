package edu.fiuba.algo3.modelo;

import java.util.List;

import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Errores.GanarPartidaError;
import edu.fiuba.algo3.modelo.Errores.PerderPartidaError;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Pasarela;

public class Partida {

    private Turno turno;
    private Mapa mapa;
    private Jugador jugador;

    public Partida(List<List<Parcela>> parcelas, List<Pasarela> camino) 
    {
        this.jugador = new Jugador();
        this.mapa = new Mapa(parcelas);
        this.turno = new Turno(camino, jugador);
    }

    public Partida(List<List<Parcela>> parcelas, List<Pasarela> camino, Jugador jugador) 
    {
        this.jugador = jugador;
        this.mapa = new Mapa(parcelas);
        this.turno = new Turno(camino, jugador);
    }

    public void iniciar()
    {
        int i = 0;
        int j = 0;
        while (j < 500)
        {
            turno.avanzarTurno(i);
            i++;
            if (i == 12) {i = 0;}
            j++;
        }
    }

    public void iniciarJuego()
    {
        try
        {
            iniciar();
        }
        catch (GanarPartidaError g)
        {

        }
        catch (PerderPartidaError p)
        {

        }
    }

    public void construir(Defensa defensa, int coordenadaX, int coordenadaY)
    {
        defensa.setJugador(this.jugador);
        defensa.gastarCreditos();
        this.mapa.construir(defensa, coordenadaX, coordenadaY);
        this.turno.aniadirDefensa(defensa);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) {return true;}
        if (o instanceof Partida)
        {
            Partida partida = (Partida)o;
            return (this.mapa.equals(partida.mapa) && this.turno.equals(partida.turno));
        }
        return false;
    }

}
