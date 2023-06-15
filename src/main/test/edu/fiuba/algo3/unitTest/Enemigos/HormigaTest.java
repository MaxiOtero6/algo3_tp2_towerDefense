package edu.fiuba.algo3.unitTest.Enemigos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;

public class HormigaTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01LaHormigaAtacoAlJugadorConSuCorrespondienteDanio()
    {
        Jugador jugadorMock = mock(Jugador.class);
        Enemigo enemigo = new Hormiga(jugadorMock, null);
        enemigo.atacar();
        verify(jugadorMock, times(1)).recibirDanio(1, enemigo.getClass().getSimpleName());
    }

    @Test
    public void test02LaHormigaAlRecibirDanioMortalEntregaCreditos()
    {
        Jugador jugadorMock = mock(Jugador.class);
        Enemigo enemigo = new Hormiga(jugadorMock, null);
        enemigo.recibirDanio(1, "Test");
        assertTrue(!enemigo.estaVivo());
        verify(jugadorMock, times(1)).agregarCreditos(anyInt());
        Hormiga.resetContador();
    }

    @Test
    public void test03LaHormigaNumeroOnceMuertaDaDosCreditos()
    {
        Jugador jugador = new Jugador();
        Jugador jugadorEsperado = new Jugador(20,112);
        Hormiga hormiga;
        for (int i = 0; i < 10; i++) {
            hormiga = new Hormiga(jugador,null);
            hormiga.recibirDanio(1, "Test03");
        }
        hormiga = new Hormiga(jugador,null);
        hormiga.recibirDanio(1, "Test03");
        assertEquals(jugadorEsperado, jugador);
        Hormiga.resetContador();
    }

    @Test
    public void test04UnaHormigaNoEstaVivaSiRecibeUnoDeDanio()
    {
        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).agregarCreditos(anyInt());
        Enemigo enemigo = new Hormiga(jugador,null);
        assertTrue(enemigo.estaVivo());
        enemigo.recibirDanio(1, "Test04Hormiga");
        assertFalse(enemigo.estaVivo());
    }

    @Test
    public void test05UnaHormigaMuertaNoPuedeMoverse()
    {
        Camino caminoMock = mock(Camino.class);
        Jugador jugadorMock = mock(Jugador.class);
        doNothing().when(jugadorMock).agregarCreditos(anyInt());
        Posicion posicion = new Posicion(0,0);
        Enemigo enemigo = new Hormiga(jugadorMock, caminoMock);
        enemigo.setearPosicion(posicion);
        
        enemigo.recibirDanio(1, "Test05Hormiga");
        assertFalse(enemigo.estaVivo());
        enemigo.mover();
        verify(caminoMock, never()).moverEnemigo(1, posicion, enemigo);
    }

    @Test
    public void test06UnaHormigaRalentizadaQuedaInmovilUnTurno()
    {
        Camino caminoMock = mock(Camino.class);
        Enemigo enemigo = new Hormiga(null, caminoMock);
        Posicion posicion = new Posicion(0,0);
        enemigo.setearPosicion(posicion);
        
        enemigo.ralentizar();
        enemigo.mover();

        verify(caminoMock, times(1)).moverEnemigo(0, posicion, enemigo);
        
        enemigo.mover();
        verify(caminoMock, times(1)).moverEnemigo(1, posicion, enemigo);
    }

    @Test
    public void test07AmbosHormigaSonIguales()
    {
        Enemigo enemigo1 = new Hormiga(null,null);
        Enemigo enemigo2 = new Hormiga(null,null);
        assertEquals(enemigo1, enemigo2);
        assertEquals(enemigo1, enemigo1);
    }

    @Test
    public void test08AmbosHormigaNoSonIguales()
    {
        Jugador jugadorMock = mock(Jugador.class);
        doNothing().when(jugadorMock).agregarCreditos(anyInt());
        Enemigo enemigo1 = new Hormiga(jugadorMock,null);
        Enemigo enemigo2 = new Hormiga(jugadorMock,null);
        String enemigo3 = "Hormiga";
        
        Posicion posicion1 = new Posicion(0, 0);
        Posicion posicion2 = new Posicion(1,1);
        
        assertEquals(enemigo1, enemigo2);
        
        enemigo2.setearPosicion(posicion2);
        enemigo1.setearPosicion(posicion1);
        assertNotEquals(enemigo1, enemigo2);
        
        enemigo1.setearPosicion(posicion2);
        assertEquals(enemigo1, enemigo2);
        
        enemigo2.recibirDanio(1, "Test08Hormiga");
        assertNotEquals(enemigo1, enemigo2);
        
        assertNotEquals(enemigo1, enemigo3);
    }

}
