package edu.fiuba.algo3.unitTest.Enemigos;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    }

    @Test
    public void test03LaHormigaNumeroOnceMuertaDaDosCreditos()
    {
        Hormiga.resetContador();
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
    }
}
