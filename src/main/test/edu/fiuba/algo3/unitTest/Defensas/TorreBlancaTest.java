package edu.fiuba.algo3.unitTest.Defensas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;
import edu.fiuba.algo3.modelo.Enemigos.Lechuza;

public class TorreBlancaTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01AlAtacarLaTorreElEnemigoRecibeDanio()
    {
        Defensa defensa = new TorreBlanca();
        Posicion posicion = new Posicion(0, 0);
        Enemigo enemigoMock = mock(Hormiga.class);
        enemigoMock.setearPosicion(posicion);
        LinkedList<Enemigo> enemigos = new LinkedList<>(); enemigos.add(enemigoMock);
        doReturn(0.0).when(enemigoMock).calcDistancia(posicion, defensa);

        defensa.setEnemigos(enemigos);
        defensa.atacar();
        verify(enemigoMock, times(1)).recibirDanio(1, defensa.getClass().getSimpleName());
    }

    @Test
    public void test02TorreBlancaTardeEnCrearseLoEsperado() {
        TorreBlanca torreBlanca = new TorreBlanca();
        TorreBlanca spy = spy(torreBlanca);
        LinkedList<Enemigo> enemigos = new LinkedList<>();
        Enemigo enemigo = new Lechuza(null,null); 
        enemigo.setearPosicion(new Posicion(0, 0));
        enemigos.add(enemigo);
        spy.setEnemigos(enemigos);
        spy.setearPosicion(new Posicion(1,1));

        spy.avanzarTurno();
        verify(spy, never()).atacar();
        spy.avanzarTurno();
        verify(spy, never()).atacar();
        spy.avanzarTurno();
        verify(spy, times(1)).atacar();
    }

    @Test
    public void test03TorreBlancaGastaCreditosDelJugadorCorrectamente()
    {
        Jugador jugadorMock = mock(Jugador.class);
        Defensa defensa = new TorreBlanca();
        defensa.gastarCreditos(jugadorMock);
        verify(jugadorMock, times(1)).gastarCreditos(10);
    }

    @Test
    public void test04AmbosTorreBlancaSonIguales()
    {
        Defensa defensa1 = new TorreBlanca();
        Defensa defensa2 = new TorreBlanca();
        assertEquals(defensa1, defensa2);
        assertEquals(defensa1, defensa1);
    }

    @Test
    public void test05AmbosTorreBlancaNoSonIguales()
    {
        Jugador jugadorMock = mock(Jugador.class);
        doNothing().when(jugadorMock).agregarCreditos(anyInt());
        Defensa defensa1 = new TorreBlanca();
        Defensa defensa2 = new TorreBlanca();
        String defensa3 = "TorreBlanca";
        
        Posicion posicion1 = new Posicion(0, 0);
        Posicion posicion2 = new Posicion(1,1);
        
        assertEquals(defensa1, defensa2);
        
        defensa2.setearPosicion(posicion2);
        defensa1.setearPosicion(posicion1);
        assertNotEquals(defensa1, defensa2);
        
        defensa1.setearPosicion(posicion2);
        assertEquals(defensa1, defensa2);
        
        defensa2.destruir();
        assertNotEquals(defensa1, defensa2);
        
        assertNotEquals(defensa1, defensa3);
    }
}
