package edu.fiuba.algo3.unitTest.Defensas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
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
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;
import edu.fiuba.algo3.modelo.Enemigos.Topo;
import edu.fiuba.algo3.modelo.Enemigos.Subterraneo.NoEsSubterraneo;

public class TrampaArenosaTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01AlAtacarLaTrampaElEnemigoEsRalentizado()
    {
        Enemigo enemigoMock = mock(Hormiga.class);
        LinkedList<Enemigo> enemigos = new LinkedList<>(); enemigos.add(enemigoMock);
        Posicion posicion = new Posicion(0,0);
        when(enemigoMock.volador()).thenReturn(false);
        when(enemigoMock.estaVivo()).thenReturn(true);
        doReturn(0.0).when(enemigoMock).calcDistancia(posicion);

        Defensa defensa = new TrampaArenosa();
        defensa.setEnemigos(enemigos);
        defensa.atacar();
        verify(enemigoMock, times(1)).ralentizar();
    }

    @Test
    public void test02AlAtacarLaTrampaElTopoEsReveladoYRalentizado()
    {
        Enemigo enemigoMock = mock(Topo.class);
        LinkedList<Enemigo> enemigos = new LinkedList<>(); enemigos.add(enemigoMock);
        Posicion posicion = new Posicion(0,0);
        when(enemigoMock.volador()).thenReturn(false);
        when(enemigoMock.estaVivo()).thenReturn(true);
        doReturn(0.0).when(enemigoMock).calcDistancia(posicion);

        Defensa defensa = new TrampaArenosa();
        defensa.setEnemigos(enemigos);
        defensa.atacar();
        verify(enemigoMock, times(1)).ralentizar();
        verify(enemigoMock, times(1)).setSubterraneo(any(NoEsSubterraneo.class));
    }

    @Test
    public void test03TrampaArenosaTardaEnDestruirseLoEsperado() {
        Enemigo enemigoMock = mock(Hormiga.class);
        LinkedList<Enemigo> enemigos = new LinkedList<>(); enemigos.add(enemigoMock);
        Posicion posicion = new Posicion(0,0);
        when(enemigoMock.volador()).thenReturn(false);
        when(enemigoMock.estaVivo()).thenReturn(true);
        doReturn(0.0).when(enemigoMock).calcDistancia(posicion);

        TrampaArenosa trampaArenosa = new TrampaArenosa();
        trampaArenosa.setEnemigos(enemigos);

        for (int i = 0; i < 2; i++) {
            trampaArenosa.avanzarTurno();
            assertFalse(trampaArenosa.estaDestruida());    
        }

        trampaArenosa.avanzarTurno(); //Ataca por 3ra vez y se destruye
        assertTrue(trampaArenosa.estaDestruida());    
        
    }

    @Test
    public void test04TrampaArenosaGastaCreditosDelJugadorCorrectamente()
    {
        Jugador jugadorMock = mock(Jugador.class);
        Defensa defensa = new TrampaArenosa();
        defensa.gastarCreditos(jugadorMock);
        verify(jugadorMock, times(1)).gastarCreditos(25);
    }

    @Test
    public void test05AmbosTrampaArenosaSonIguales()
    {
        Defensa defensa1 = new TrampaArenosa();
        Defensa defensa2 = new TrampaArenosa();
        assertEquals(defensa1, defensa2);
        assertEquals(defensa1, defensa1);
    }

    @Test
    public void test06AmbosTrampaArenosaNoSonIguales()
    {
        Jugador jugadorMock = mock(Jugador.class);
        doNothing().when(jugadorMock).agregarCreditos(anyInt());
        Defensa defensa1 = new TrampaArenosa();
        Defensa defensa2 = new TrampaArenosa();
        String defensa3 = "TrampaArenosa";
        
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
