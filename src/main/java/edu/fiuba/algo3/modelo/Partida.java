package edu.fiuba.algo3.modelo;

import java.util.List;

import edu.fiuba.algo3.modelo.Defensas.Torres.*;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import edu.fiuba.algo3.modelo.Errores.GanarPartidaError;
import edu.fiuba.algo3.modelo.Errores.PerderPartidaError;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Pasarela;
import org.apache.logging.log4j.LogManager;

public class Partida {

    private Turno turno;
    private Mapa mapa;
    private Jugador jugador;

    public Partida(List<List<Parcela>> parcelas, List<Parcela> camino) 
    {
        this.jugador = new Jugador();
        this.mapa = new Mapa(parcelas);
        this.turno = new Turno(camino, jugador);
        SingleLogger.inicializar(LogManager.getLogger());
    }

    public Partida(List<List<Parcela>> parcelas, List<Parcela> camino, Jugador jugador) 
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
            SingleLogger.obtenerLogger().imprimirLog("Jugador gana la partida");
        }
        catch (PerderPartidaError p)
        {
            SingleLogger.obtenerLogger().imprimirLog("Jugador pierde la partida");
        }
    }

    public void construirTorre(Torre torre, int coordenadaX, int coordenadaY)
    {
        torre.gastarCreditos(this.jugador);
        this.mapa.construir(torre, coordenadaX, coordenadaY);
        this.turno.aniadirTorre(torre);
    }

    public void construirTrampa(TrampaArenosa trampa, int coordenadaX, int coordenadaY)
    {
        trampa.gastarCreditos(this.jugador);
        this.mapa.construir(trampa, coordenadaX, coordenadaY);
        this.turno.aniadirTrampa(trampa);
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
