package edu.fiuba.algo3.unitTest.Enemigos;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Defensas.Torres.Torre;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Lechuza;
import edu.fiuba.algo3.modelo.Enemigos.Objetivos.ObjetivoLechuza;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parser.CreadorCaminoH;
import edu.fiuba.algo3.modelo.Parser.CreadorCaminoL;
import edu.fiuba.algo3.modelo.Parser.CreadorEnemigos;

public class LechuzaTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01LaLechuzaNoDebeAtacarAlJugador()
    {
        Jugador jugadorMock = mock(Jugador.class);
        Enemigo enemigo = new Lechuza(jugadorMock, null);
        LinkedList<Torre> torres = new LinkedList<>();
        torres.add(new TorreBlanca());
        ObjetivoLechuza.setTorres(torres);
        enemigo.atacar();
        verify(jugadorMock, times(0)).recibirDanio(0, enemigo.getClass().getSimpleName());
    }

    @Test
    public void test02LaLechuzaDestruyeLaTorre()
    {
        Jugador jugadorMock = mock(Jugador.class);
        Enemigo enemigo = new Lechuza(jugadorMock, null);
        LinkedList<Torre> torres = new LinkedList<>();
        Torre torre = new TorreBlanca();
        torres.add(torre);
        ObjetivoLechuza.setTorres(torres);
        enemigo.atacar();
        verify(jugadorMock, times(0)).recibirDanio(0, enemigo.getClass().getSimpleName());
        assertTrue(torre.estaDestruida());
    }

    @Test
    public void test03LaLechuzaAlRecibirDanioMortalEntregaCreditos()
    {
        Jugador jugadorMock = mock(Jugador.class);
        Enemigo enemigo = new Lechuza(jugadorMock, null);
        enemigo.recibirDanio(5, "Test");
        assertTrue(!enemigo.estaVivo());
        verify(jugadorMock, times(1)).agregarCreditos(anyInt());
    }

    @Test
    public void test04LaLechuzaAlTenerMitadDeVidaCambiaSuCamino()
    {
        MockedStatic<CreadorCaminoH> caminoHMock = mockStatic(CreadorCaminoH.class);
        MockedStatic<CreadorCaminoL> caminoLMock = mockStatic(CreadorCaminoL.class);
        Parcela parcelaMock = mock(Parcela.class);
        LinkedList<Parcela> lista = new LinkedList<>();
        lista.add(parcelaMock);
        caminoHMock.when(() -> CreadorCaminoH.crearCaminoH(null)).thenReturn(lista);
        caminoLMock.when(() -> CreadorCaminoL.crearCaminoL()).thenReturn(lista);
        
        Enemigo enemigo = new Lechuza(null);

        enemigo.recibirDanio(3, "Test04Lechuza");
        enemigo.mover();
        caminoHMock.verify(() -> CreadorCaminoH.crearCaminoH(null), times(1));
        caminoHMock.close();
        caminoLMock.close();
    }

    @Test
    public void test05LaLechuzaAlNoTenerMitadDeVidaNoCambiaSuCamino()
    {
        MockedStatic<CreadorCaminoH> caminoHMock = mockStatic(CreadorCaminoH.class);
        MockedStatic<CreadorCaminoL> caminoLMock = mockStatic(CreadorCaminoL.class);
        Parcela parcelaMock = mock(Parcela.class);
        LinkedList<Parcela> lista = new LinkedList<>();
        lista.add(parcelaMock);
        caminoHMock.when(() -> CreadorCaminoH.crearCaminoH(null)).thenReturn(lista);
        caminoLMock.when(() -> CreadorCaminoL.crearCaminoL()).thenReturn(lista);
        
        Enemigo enemigo = new Lechuza(null);

        enemigo.mover();
        caminoHMock.verify(() -> CreadorCaminoH.crearCaminoH(null), never());
        caminoHMock.close();
        caminoLMock.close();
    }
}
