package edu.fiuba.algo3.unitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Turno;
import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Errores.GanarPartidaError;
import edu.fiuba.algo3.modelo.Parcelas.Largada;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parser.CreadorEnemigos;

public class TurnoTest {

    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01ElJugadorGanaLaPartidaSiNoHayEnemigos()
    {
        List<Enemigo> lista = new LinkedList<>();
        
        Camino caminoMock = mock(Camino.class);
        doNothing().when(caminoMock).aparecerEnemigos(lista);

        MockedStatic<CreadorEnemigos> creadorEnemigoMock = mockStatic(CreadorEnemigos.class);
        when(CreadorEnemigos.crearEnemigos(0, null, caminoMock)).thenReturn(lista);

        Turno turno = new Turno(caminoMock, null);
        assertThrows(GanarPartidaError.class, () -> turno.avanzarTurno(0));
        creadorEnemigoMock.close();
    }

    @Test
    public void test02EnUnTurnoSimuladoOcurrenLasAccionesIndicadas()
    {
        Jugador jugadorMock = mock(Jugador.class);
        Enemigo enemigoMock = mock(Enemigo.class);
        doNothing().when(enemigoMock).mover();
        List<Enemigo> lista = new LinkedList<>();
        lista.add(enemigoMock);
        
        Camino caminoMock = mock(Camino.class);
        doNothing().when(caminoMock).aparecerEnemigos(lista);
        
        MockedStatic<CreadorEnemigos> creadorEnemigoMock = mockStatic(CreadorEnemigos.class);
        when(CreadorEnemigos.crearEnemigos(0, jugadorMock, caminoMock)).thenReturn(lista);
        
        Turno turno = new Turno(caminoMock, jugadorMock);
        turno.avanzarTurno(0); //Aparecen los enemigos
        
        TorreBlanca torreMock = mock(TorreBlanca.class);
        doNothing().when(torreMock).avanzarTurno();
        turno.aniadirDefensa(torreMock);

        TrampaArenosa trampaMock = mock(TrampaArenosa.class);
        doNothing().when(trampaMock).avanzarTurno();
        turno.aniadirDefensa(trampaMock);

        turno.avanzarTurno(0);
        verify(enemigoMock, atLeastOnce()).avanzarTurno(any());
        verify(torreMock, atLeastOnce()).avanzarTurno(any());
        verify(trampaMock, atLeastOnce()).avanzarTurno(any());
        creadorEnemigoMock.close();
    }

    @Test
    public void test03SiUnaTorreSeDestruyeEnUnTurnoSeEliminaDeLaListaDeTorres()
    {
        Jugador jugadorMock = mock(Jugador.class);
        Enemigo enemigoMock = mock(Enemigo.class);
        doNothing().when(enemigoMock).mover();
        List<Enemigo> lista = new LinkedList<>();
        lista.add(enemigoMock);
        
        Camino caminoMock = mock(Camino.class);
        doNothing().when(caminoMock).aparecerEnemigos(lista);
        
        MockedStatic<CreadorEnemigos> creadorEnemigoMock = mockStatic(CreadorEnemigos.class);
        when(CreadorEnemigos.crearEnemigos(0, jugadorMock, caminoMock)).thenReturn(lista);
        
        
        TorreBlanca torre = new TorreBlanca();
        LinkedList<Defensa> torres = new LinkedList<>();
        LinkedList<Defensa> spy1 = spy(torres);
        TrampaArenosa trampa = new TrampaArenosa();
        
        Turno turno = new Turno(caminoMock, jugadorMock, spy1);
        turno.aniadirDefensa(torre);
        turno.aniadirDefensa(trampa);

        torre.destruir();
        trampa.destruir();
        
        turno.avanzarTurno(0);
        verify(spy1, atMost(1)).remove(torre);
        verify(spy1, atMost(1)).remove(trampa);
        creadorEnemigoMock.close();
    }

    @Test
    public void test04AmbosTurnoSonIguales()
    {
        Camino camino = null;
        Turno turno1 = new Turno(camino,null);
        Turno turno2 = new Turno(camino,null);
        assertEquals(turno1, turno2);
        assertEquals(turno1, turno1);
    }

    @Test
    public void test05AmbosTurnoNoSonIguales()
    {
        Jugador jugadorMock = mock(Jugador.class);
        LinkedList<Parcela> parcelas = new LinkedList<>();
        parcelas.add(new Largada(0,0));
        Camino camino = new Camino(parcelas);

        int turno3 = 100;
        TorreBlanca torre = new TorreBlanca();
        TrampaArenosa trampaArenosa = new TrampaArenosa();
        torre.setearPosicion(new Posicion(0, 0));
        trampaArenosa.setearPosicion(new Posicion(3, 3));
        Turno turno1 = new Turno(camino, jugadorMock);
        Turno turno2 = new Turno(camino, jugadorMock);

        turno1.avanzarTurno(0);
        assertNotEquals(turno1, turno2);
        
        turno2.avanzarTurno(0);
        assertEquals(turno1, turno2);

        turno1.aniadirDefensa(torre);
        assertNotEquals(turno1, turno2);
        turno2.aniadirDefensa(torre);
        assertEquals(turno1, turno2);
        
        turno1.aniadirDefensa(trampaArenosa);
        assertNotEquals(turno1, turno2);
        turno2.aniadirDefensa(trampaArenosa);
        assertEquals(turno1, turno2);

        assertNotEquals(turno1, turno3);
    }
}
