package edu.fiuba.algo3.unitTest.Enemigos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Enemigos.Arania;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public class AraniaTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01LaAraniaAtacoAlJugadorConSuCorrespondienteDanio()
    {
        Jugador jugadorMock = mock(Jugador.class);
        Enemigo enemigo = new Arania(jugadorMock, null);
        enemigo.atacar();
        verify(jugadorMock, times(1)).recibirDanio(2, enemigo.getClass().getSimpleName());
    }

    @Test
    public void test02LaAraniaAlRecibirDanioMortalEntregaCreditos()
    {
        Jugador jugadorMock = mock(Jugador.class);
        Enemigo enemigo = new Arania(jugadorMock, null);
        enemigo.recibirDanio(2, "Test");
        verify(jugadorMock, times(1)).agregarCreditos(anyInt());
    }

    @Test
    public void test03UnaAraniaNoEstaVivaSiRecibeDosDeDanio()
    {
        Jugador jugadorMock = mock(Jugador.class);
        doNothing().when(jugadorMock).agregarCreditos(anyInt());
        Enemigo enemigo = new Arania(jugadorMock,null);
        verify(jugadorMock, never()).agregarCreditos(anyInt());
        enemigo.recibirDanio(2, "Test04Arania");
        verify(jugadorMock, times(1)).agregarCreditos(anyInt());
    }

    @Test
    public void test05UnaAraniaRalentizadaTieneUnoDeVelocidadUnTurno()
    {
        Camino caminoMock = mock(Camino.class);
        Enemigo enemigo = new Arania(null, caminoMock);
        Posicion posicion = new Posicion(0,0);
        enemigo.setearPosicion(posicion);
        
        enemigo.ralentizar();
        enemigo.mover();
        enemigo.recargarVelocidad();

        verify(caminoMock, times(1)).moverEnemigo(1, posicion, enemigo);
    
        enemigo.mover();
        verify(caminoMock, times(1)).moverEnemigo(2, posicion, enemigo);
    }

    @Test
    public void test06AmbosAraniaSonIguales()
    {
        Enemigo enemigo1 = new Arania(null,null);
        Enemigo enemigo2 = new Arania(null,null);
        assertEquals(enemigo1, enemigo2);
        assertEquals(enemigo1, enemigo1);
    }

    @Test
    public void test07AmbosAraniaNoSonIguales()
    {
        Jugador jugadorMock = mock(Jugador.class);
        doNothing().when(jugadorMock).agregarCreditos(anyInt());
        Enemigo enemigo1 = new Arania(jugadorMock,null);
        Enemigo enemigo2 = new Arania(jugadorMock,null);
        String enemigo3 = "Arania";
        
        Posicion posicion1 = new Posicion(0, 0);
        Posicion posicion2 = new Posicion(1,1);
        
        assertEquals(enemigo1, enemigo2);
        
        enemigo2.setearPosicion(posicion2);
        enemigo1.setearPosicion(posicion1);
        assertNotEquals(enemigo1, enemigo2);
        
        enemigo1.setearPosicion(posicion2);
        assertEquals(enemigo1, enemigo2);
        
        enemigo2.recibirDanio(1, "Test07Arania");
        assertNotEquals(enemigo1, enemigo2);
        
        assertNotEquals(enemigo1, enemigo3);
    }
}
