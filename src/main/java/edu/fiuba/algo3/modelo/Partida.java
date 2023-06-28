package edu.fiuba.algo3.modelo;

import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Errores.DefensaEnTerrenoErroneoError;
import edu.fiuba.algo3.modelo.Errores.GanarPartidaError;
import edu.fiuba.algo3.modelo.Errores.PerderPartidaError;
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;
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
        int j = 0;
        while (j < 500)
        {
            turno.avanzarTurno();
            j++;
        }
    }

    public void avanzarTurno()
    {
        try
        {
            turno.avanzarTurno();
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
        try{
            this.mapa.construir(defensa, coordenadaX, coordenadaY);
            this.turno.aniadirDefensa(defensa);
        }catch (TerrenoDeConstruccionInvalidoError e){
            SingleLogger.obtenerLogger().imprimirLog("Terreno de construccion invalido seleccionado");
        }catch (DefensaEnTerrenoErroneoError e){
            SingleLogger.obtenerLogger().imprimirLog("Esa defensa no se puede construir en este terreno");
        }
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

    public List<List<Parcela>> obtenerMapa()
    {
        return this.mapa.obtenerMapa();
    }

    public LinkedList<Enemigo> obtenerEnemigos()
    {
        return this.turno.obtenerEnemigos();
    }

    public Jugador obtenerJugador(){
        return this.turno.obtenerJugador();
    }

    //obtener turno
    public Turno obtenerTurno()
    {
        return this.turno;
    }

}
