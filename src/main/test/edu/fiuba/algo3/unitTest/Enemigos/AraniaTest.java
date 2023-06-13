package edu.fiuba.algo3.unitTest.Enemigos;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Jugador;
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
        assertTrue(!enemigo.estaVivo());
        verify(jugadorMock, times(1)).agregarCreditos(anyInt());
    }
}
