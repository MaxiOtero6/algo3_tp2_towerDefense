package edu.fiuba.algo3.modelo;

import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Errores.GanarPartidaError;
import edu.fiuba.algo3.modelo.Errores.PerderPartidaError;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parser.CreadorMapa;

import org.apache.logging.log4j.LogManager;

public class Partida {

    private Turno turno;
    private Mapa mapa;

    public Partida() 
    {
        List<Parcela> camino = new LinkedList<>();
        Jugador jugador = new Jugador();
        this.mapa = new Mapa(CreadorMapa.crearMapa(camino));
        this.turno = new Turno(camino, jugador);
        SingleLogger.inicializar(LogManager.getLogger());
    }

    public Partida(List<List<Parcela>> parcelas, List<Parcela> camino, Jugador jugador) 
    {
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

    public void avanzarTurno(int i)
    {
        turno.avanzarTurno(i);
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

    public void construirDefensa(Defensa defensa, int coordenadaX, int coordenadaY)
    {
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

    public Mapa obtenerMapa()
    {
        return this.mapa;
    }

}
