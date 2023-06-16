package edu.fiuba.algo3.unitTest.Enemigos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Enemigos.Topo;
import edu.fiuba.algo3.modelo.Parcelas.Largada;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public class TopoTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01ElTopoAtacoAlJugadorConSuCorrespondienteDanioEnTurnoPar()
    {
        Jugador jugadorMock = mock(Jugador.class);
        Topo enemigo = new Topo(jugadorMock, null);
        enemigo.setNumeroTurno(0);
        enemigo.atacar();
        verify(jugadorMock, times(1)).recibirDanio(2, enemigo.getClass().getSimpleName());
    }

    @Test
    public void test01ElTopoAtacoAlJugadorConSuCorrespondienteDanioEnTurnoImpar()
    {
        Jugador jugadorMock = mock(Jugador.class);
        Topo enemigo = new Topo(jugadorMock, null);
        enemigo.setNumeroTurno(1);
        enemigo.atacar();
        verify(jugadorMock, times(1)).recibirDanio(5, enemigo.getClass().getSimpleName());
    }

    @Test
    public void test02ElTopoAlRecibirDanioMortalEntregaCreditos()
    {
        Jugador jugadorMock = mock(Jugador.class);
        Enemigo enemigo = new Topo(jugadorMock, null);
        enemigo.recibirDanio(1, "Test");
        assertTrue(!enemigo.estaVivo());
        verify(jugadorMock, times(1)).agregarCreditos(anyInt());
    }

    @Test
    public void test03ElTopoAceleraCorrectamente()
    {
        LinkedList<Parcela> parcelas = new LinkedList<>();
        Camino camino = new Camino(parcelas);
        Enemigo enemigo = new Topo(null, camino);
        LinkedList<Enemigo> enemigos = new LinkedList<>();
        enemigos.add(enemigo);

        Parcela parcela1 = new Pasarela(100,100);
        Parcela parcela2 = new Pasarela(200,200);
        Parcela parcela3 = new Pasarela(300,300);
        Parcela spy1 = spy(parcela1); Parcela spy2 = spy(parcela2); Parcela spy3 = spy(parcela3);
        
        parcelas.add(new Largada(0,0)); parcelas.add(spy1);
        camino.aparecerEnemigos(enemigos);

        enemigo.mover();
        verify(spy1, times(1)).agregarEnemigo(enemigo);

        for (int i = 0; i < 5; i++) {
            parcelas.add(new Pasarela(i+1,i+1));
            enemigo.mover();
        }

        parcelas.add(spy2);
        enemigo.mover();
        verify(spy2, times(1)).agregarEnemigo(enemigo);

        for (int i = 0; i < 5; i++) {
            parcelas.add(new Pasarela(i+6,i+6));
            enemigo.mover();
        }

        parcelas.add(new Pasarela(11,11)); parcelas.add(spy3);
        enemigo.mover();
        verify(spy3, times(1)).agregarEnemigo(enemigo);
    }

    @Test
    public void test04UnTopoNoEstaVivaSiRecibeUnoDeDanio()
    {
        Jugador jugador = mock(Jugador.class);
        doNothing().when(jugador).agregarCreditos(anyInt());
        Enemigo enemigo = new Topo(jugador,null);
        assertTrue(enemigo.estaVivo());
        enemigo.recibirDanio(1, "Test04Topo");
        assertFalse(enemigo.estaVivo());
    }

    @Test
    public void test05UnTopoMuertaNoPuedeMoverse()
    {
        Camino caminoMock = mock(Camino.class);
        Jugador jugadorMock = mock(Jugador.class);
        doNothing().when(jugadorMock).agregarCreditos(anyInt());
        Posicion posicion = new Posicion(0,0);
        Enemigo enemigo = new Topo(jugadorMock, caminoMock);
        enemigo.setearPosicion(posicion);
        
        enemigo.recibirDanio(1, "Test05Topo");
        assertFalse(enemigo.estaVivo());
        enemigo.mover();
        verify(caminoMock, never()).moverEnemigo(1, posicion, enemigo);
    }

    @Test
    public void test06UnTopoRalentizadoQuedaInmovilUnTurno()
    {
        Camino caminoMock = mock(Camino.class);
        Enemigo enemigo = new Topo(null, caminoMock);
        Posicion posicion = new Posicion(0,0);
        enemigo.setearPosicion(posicion);
        
        enemigo.ralentizar();
        enemigo.mover();

        verify(caminoMock, times(1)).moverEnemigo(0, posicion, enemigo);
    
        enemigo.mover();
        verify(caminoMock, times(1)).moverEnemigo(1, posicion, enemigo);
    }

    @Test
    public void test07AmbosTopoSonIguales()
    {
        Enemigo enemigo1 = new Topo(null,null);
        Enemigo enemigo2 = new Topo(null,null);
        assertEquals(enemigo1, enemigo2);
        assertEquals(enemigo1, enemigo1);
    }

    @Test
    public void test08AmbosTopoNoSonIguales()
    {
        Jugador jugadorMock = mock(Jugador.class);
        doNothing().when(jugadorMock).agregarCreditos(anyInt());
        Enemigo enemigo1 = new Topo(jugadorMock,null);
        Enemigo enemigo2 = new Topo(jugadorMock,null);
        String enemigo3 = "Topo";
        
        Posicion posicion1 = new Posicion(0, 0);
        Posicion posicion2 = new Posicion(1,1);
        
        assertEquals(enemigo1, enemigo2);
        
        enemigo2.setearPosicion(posicion2);
        enemigo1.setearPosicion(posicion1);
        assertNotEquals(enemigo1, enemigo2);
        
        enemigo1.setearPosicion(posicion2);
        assertEquals(enemigo1, enemigo2);
        
        enemigo2.recibirDanio(1, "Test08Topo");
        assertNotEquals(enemigo1, enemigo2);
        
        assertNotEquals(enemigo1, enemigo3);
    }
}
