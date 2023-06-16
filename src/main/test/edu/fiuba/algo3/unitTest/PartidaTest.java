package edu.fiuba.algo3.unitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Partida;
import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorrePlateada;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parcelas.Rocoso;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Parser.CreadorMapa;

public class PartidaTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01SeLeInformaPorConsolaAlJugadorAlGanarLaPartida()
    {
        
        Jugador jugador = new Jugador(1000000, 1000000);
        List<Parcela> camino = new LinkedList<>();
        Partida partida = new Partida(CreadorMapa.crearMapa(camino), camino, jugador);
        partida.construirTorre(new TorrePlateada(), 0,1);
        partida.construirTorre(new TorrePlateada(), 0,2);
        partida.construirTorre(new TorrePlateada(), 0,3);
        partida.construirTorre(new TorrePlateada(), 0,4);
        partida.construirTorre(new TorrePlateada(), 0,5);
        partida.construirTorre(new TorreBlanca(), 2,0);
        partida.construirTorre(new TorreBlanca(), 2,1);
        partida.construirTorre(new TorreBlanca(), 2,2);
        partida.construirTorre(new TorreBlanca(), 2,3);
        partida.construirTrampa(new TrampaArenosa(), 1,6);
        partida.construirTrampa(new TrampaArenosa(), 2,6);
        partida.construirTrampa(new TrampaArenosa(), 3,6);
        
        Logger mockedLogger = mock(Logger.class);
        SingleLogger.inicializar(mockedLogger);
        partida.iniciarJuego();
        verify(mockedLogger, times(1)).info("Jugador gana la partida");
        Hormiga.resetContador();
    }

    @Test
    public void test02SeLeInformaPorConsolaAlUsuarioAlPerderUnaPartida()
    {
        
        List<Parcela> camino = new LinkedList<>();
        Partida partida = new Partida(CreadorMapa.crearMapa(camino), camino);
        
        Logger mockedLogger = mock(Logger.class);
        SingleLogger.inicializar(mockedLogger);
        partida.iniciarJuego();
        verify(mockedLogger, times(1)).info("Jugador pierde la partida");
    }

    @Test
    public void test03AmbosMapasSonIguales()
    {
        List<List<Parcela>> lista1 = new LinkedList<>();
        List<Parcela> subLista = new LinkedList<>();
        subLista.add(new Pasarela(0,0));
        lista1.add(subLista);

        Partida partida1 = new Partida(lista1, subLista);
        Partida partida2 = new Partida(lista1, subLista);

        assertEquals(partida1, partida2);
        assertEquals(partida1, partida1);
    }

    @Test
    public void test04AmbosMapasNoSonIguales()
    {
        List<List<Parcela>> lista1 = new LinkedList<>();
        List<Parcela> subLista = new LinkedList<>();
        subLista.add(new Pasarela(0,0));
        lista1.add(subLista);
        List<List<Parcela>> lista2 = new LinkedList<>();
        List<Parcela> subLista2 = new LinkedList<>();
        subLista2.add(new Rocoso(0,0));
        lista2.add(subLista2);

        Partida partida1 = new Partida(lista1, subLista);
        Partida partida2 = new Partida(lista1, subLista);
        partida2.construirTrampa(new TrampaArenosa(), 0, 0);

        assertNotEquals(partida1, partida2);

        partida2 = new Partida(lista2, subLista2);

        assertNotEquals(partida1, partida2);
        assertNotEquals(partida1, lista1);
    }
}
