package edu.fiuba.algo3.unitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.SingleLogger;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class JugadorTest {

    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01ElJugadorAgregaCreditosCorrectamente()
    {
        Jugador jugador = new Jugador();
        Jugador jugadorEsperado = new Jugador(20,110);
        
        jugador.agregarCreditos(10);

        assertEquals(jugadorEsperado, jugador);
    }

    @Test
    public void test02ElJugadorGastaCreditosCorrectamente()
    {
        Jugador jugador = new Jugador();
        Jugador jugadorEsperado = new Jugador(20,90);
        
        jugador.gastarCreditos(10);

        assertEquals(jugadorEsperado, jugador);
    }

    @Test
    public void test03ElJugadorRecibeDanioCorrectamente()
    {
        Jugador jugador = new Jugador();
        Jugador jugadorEsperado = new Jugador(10,100);
        
        jugador.recibirDanio(10, "Test03 jugador");

        assertEquals(jugadorEsperado, jugador);
    }
}
