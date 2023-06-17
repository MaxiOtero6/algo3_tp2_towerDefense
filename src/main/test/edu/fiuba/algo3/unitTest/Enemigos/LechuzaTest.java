package edu.fiuba.algo3.unitTest.Enemigos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Lechuza;
import edu.fiuba.algo3.modelo.Errores.EnemigoNoRalentizableError;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parser.CreadorCaminoH;
import edu.fiuba.algo3.modelo.Parser.CreadorCaminoL;

public class LechuzaTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01LaLechuzaNoDebeAtacarAlJugador()
    {
        Jugador jugadorMock = mock(Jugador.class);
        Lechuza enemigo = new Lechuza(jugadorMock, null);
        LinkedList<Defensa> torres = new LinkedList<>();
        torres.add(new TorreBlanca());
        enemigo.setDefensas(torres);
        enemigo.atacar();
        verify(jugadorMock, times(0)).recibirDanio(0, enemigo.getClass().getSimpleName());
    }

    @Test
    public void test02LaLechuzaDestruyeLaTorre()
    {
        Jugador jugadorMock = mock(Jugador.class);
        Lechuza enemigo = new Lechuza(jugadorMock, null);
        LinkedList<Defensa> torres = new LinkedList<>();
        TorreBlanca torre = new TorreBlanca();
        TorreBlanca spy = Mockito.spy(torre);
        torres.add(spy);
        enemigo.setDefensas(torres);
        enemigo.atacar();
        verify(jugadorMock, times(0)).recibirDanio(0, enemigo.getClass().getSimpleName());
        verify(spy, times(1)).destruir();
    }

    @Test
    public void test03LaLechuzaAlRecibirDanioMortalEntregaCreditos()
    {
        Jugador jugadorMock = mock(Jugador.class);
        Enemigo enemigo = new Lechuza(jugadorMock, null);
        enemigo.recibirDanio(5, "Test");
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

    @Test
    public void test06UnaLechuzaNoEstaVivaSiRecibeCincoDeDanio()
    {
        Jugador jugadorMock = mock(Jugador.class);
        doNothing().when(jugadorMock).agregarCreditos(anyInt());
        Enemigo enemigo = new Lechuza(jugadorMock,null);
        enemigo.recibirDanio(5, "Test04Lechuza");
        verify(jugadorMock, times(1)).agregarCreditos(anyInt());
    }

    @Test
    public void test07UnaLechuzaMuertaNoPuedeMoverse()
    {
        Camino caminoMock = mock(Camino.class);
        Jugador jugadorMock = mock(Jugador.class);
        doNothing().when(jugadorMock).agregarCreditos(anyInt());
        Posicion posicion = new Posicion(0,0);
        Enemigo enemigo = new Lechuza(jugadorMock, caminoMock);
        enemigo.setearPosicion(posicion);
        LinkedList<Enemigo> enemigos = new LinkedList<>(); enemigos.add(enemigo);

        enemigo.recibirDanio(5, "Test07Lechuza");
        verify(jugadorMock, times(1)).agregarCreditos(anyInt());
        enemigo.avanzarTurno(enemigos);
        verify(caminoMock, never()).moverEnemigo(5, posicion, enemigo);
    }

    @Test
    public void test08UnaLechuzaNoSePuedeRalentizar()
    {
        Enemigo enemigo = new Lechuza(null, null);
        
        assertThrows(EnemigoNoRalentizableError.class, enemigo::ralentizar);
    }

    @Test
    public void test09AmbosLechuzaSonIguales()
    {
        Enemigo enemigo1 = new Lechuza(null,null);
        Enemigo enemigo2 = new Lechuza(null,null);
        assertEquals(enemigo1, enemigo2);
        assertEquals(enemigo1, enemigo1);
    }

    @Test
    public void test10AmbosLechuzaNoSonIguales()
    {
        Jugador jugadorMock = mock(Jugador.class);
        doNothing().when(jugadorMock).agregarCreditos(anyInt());
        Enemigo enemigo1 = new Lechuza(jugadorMock,null);
        Enemigo enemigo2 = new Lechuza(jugadorMock,null);
        String enemigo3 = "Lechuza";
        
        Posicion posicion1 = new Posicion(0, 0);
        Posicion posicion2 = new Posicion(1,1);
        
        assertEquals(enemigo1, enemigo2);
        
        enemigo2.setearPosicion(posicion2);
        enemigo1.setearPosicion(posicion1);
        assertNotEquals(enemigo1, enemigo2);
        
        enemigo1.setearPosicion(posicion2);
        assertEquals(enemigo1, enemigo2);
        
        enemigo2.recibirDanio(1, "Test10Lechuza");
        assertNotEquals(enemigo1, enemigo2);
        
        assertNotEquals(enemigo1, enemigo3);
    }
}
