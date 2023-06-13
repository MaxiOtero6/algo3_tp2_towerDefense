package edu.fiuba.algo3.unitTest.Enemigos;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Enemigos.Topo;
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
}
