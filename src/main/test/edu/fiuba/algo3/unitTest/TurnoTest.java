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
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Turno;
import edu.fiuba.algo3.modelo.Defensas.Torres.Torre;
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
    public void test01ElJugadorPierdeLaPartidaSiNoHayEnemigos()
    {
        Enemigo enemigoMock = mock(Enemigo.class);
        doNothing().when(enemigoMock).mover();
        doReturn(false).when(enemigoMock).estaVivo();
        List<Enemigo> lista = new LinkedList<>();
        lista.add(enemigoMock);
        
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
        
        Enemigo enemigoMock = mock(Enemigo.class);
        doNothing().when(enemigoMock).mover();
        doReturn(true).when(enemigoMock).estaVivo();
        List<Enemigo> lista = new LinkedList<>();
        lista.add(enemigoMock);
        
        Camino caminoMock = mock(Camino.class);
        doNothing().when(caminoMock).aparecerEnemigos(lista);
        
        MockedStatic<CreadorEnemigos> creadorEnemigoMock = mockStatic(CreadorEnemigos.class);
        when(CreadorEnemigos.crearEnemigos(0, null, caminoMock)).thenReturn(lista);
        
        Turno turno = new Turno(caminoMock, null);
        turno.avanzarTurno(0); //Aparecen los enemigos
        
        Torre torreMock = mock(TorreBlanca.class);
        doNothing().when(torreMock).avanzarTurno();
        turno.aniadirTorre(torreMock);

        TrampaArenosa trampaMock = mock(TrampaArenosa.class);
        doNothing().when(trampaMock).avanzarTurno();
        turno.aniadirTrampa(trampaMock);

        turno.avanzarTurno(0);
        verify(enemigoMock, atLeastOnce()).mover();
        verify(torreMock, atLeastOnce()).avanzarTurno();
        verify(trampaMock, atLeastOnce()).avanzarTurno();
        creadorEnemigoMock.close();
    }

    @Test
    public void test03SiUnaTorreSeDestruyeEnUnTurnoSeEliminaDeLaListaDeTorres()
    {
        Enemigo enemigoMock = mock(Enemigo.class);
        doNothing().when(enemigoMock).mover();
        doReturn(true).when(enemigoMock).estaVivo();
        List<Enemigo> lista = new LinkedList<>();
        lista.add(enemigoMock);
        
        Camino caminoMock = mock(Camino.class);
        doNothing().when(caminoMock).aparecerEnemigos(lista);
        
        MockedStatic<CreadorEnemigos> creadorEnemigoMock = mockStatic(CreadorEnemigos.class);
        when(CreadorEnemigos.crearEnemigos(0, null, caminoMock)).thenReturn(lista);
        
        
        Torre torre = new TorreBlanca();
        LinkedList<Torre> torres = new LinkedList<>();
        LinkedList<Torre> spy1 = spy(torres);
        
        TrampaArenosa trampa = new TrampaArenosa();
        LinkedList<TrampaArenosa> trampas = new LinkedList<>();
        LinkedList<TrampaArenosa> spy2 = spy(trampas);
        
        Turno turno = new Turno(caminoMock, null, spy1, spy2);
        turno.aniadirTorre(torre);
        turno.aniadirTrampa(trampa);

        torre.destruir();
        trampa.destruir();
        
        turno.avanzarTurno(0);
        verify(spy1, atMost(1)).remove(torre);
        verify(spy2, atMost(1)).remove(trampa);
        creadorEnemigoMock.close();
    }

    @Test
    public void test03AmbosTurnoSonIguales()
    {
        Camino camino = null;
        Turno turno1 = new Turno(camino,null);
        Turno turno2 = new Turno(camino,null);
        assertEquals(turno1, turno2);
        assertEquals(turno1, turno1);
    }

    @Test
    public void test03AmbosTurnoNoSonIguales()
    {
        LinkedList<Parcela> parcelas = new LinkedList<>();
        parcelas.add(new Largada(0,0));
        Camino camino = new Camino(parcelas);

        int turno3 = 100;
        Torre torre = new TorreBlanca();
        TrampaArenosa trampaArenosa = new TrampaArenosa();
        torre.setearPosicion(new Posicion(0, 0));
        trampaArenosa.setearPosicion(new Posicion(3, 3));
        Turno turno1 = new Turno(camino, null);
        Turno turno2 = new Turno(camino, null);

        turno1.avanzarTurno(0);
        assertNotEquals(turno1, turno2);
        
        turno2.avanzarTurno(0);
        assertEquals(turno1, turno2);

        turno1.aniadirTorre(torre);
        assertNotEquals(turno1, turno2);
        turno2.aniadirTorre(torre);
        assertEquals(turno1, turno2);
        
        turno1.aniadirTrampa(trampaArenosa);
        assertNotEquals(turno1, turno2);
        turno2.aniadirTrampa(trampaArenosa);
        assertEquals(turno1, turno2);

        assertNotEquals(turno1, turno3);
    }
}
