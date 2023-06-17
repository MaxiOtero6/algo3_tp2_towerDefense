package edu.fiuba.algo3.unitTest.Enemigos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Topo;
import edu.fiuba.algo3.modelo.Enemigos.Subterraneo.EsSubterraneo;
import edu.fiuba.algo3.modelo.Enemigos.Subterraneo.NoEsSubterraneo;
import edu.fiuba.algo3.modelo.Enemigos.Subterraneo.Subterraneo;

public class SubterraneoTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01SiUnEnemigoEsSubterraneoYLaDefensaEsUnaTorreRespondeDeLaFormaIndicada()
    {
        Defensa defensa = mock(TorreBlanca.class);
        Subterraneo sub = new EsSubterraneo();
        int respuestaEsperada = 1000;

        int respuesta = sub.subterraneo(defensa);

        assertEquals(respuestaEsperada, respuesta);
    }

    @Test
    public void test02SiUnEnemigoEsSubterraneoYLaDefensaEsUnaTrampaRespondeDeLaFormaIndicada()
    {
        Defensa defensa = mock(TrampaArenosa.class);
        Subterraneo sub = new EsSubterraneo();
        int respuestaEsperada = 0;

        int respuesta = sub.subterraneo(defensa);

        assertEquals(respuestaEsperada, respuesta);
    }

    @Test
    public void test03SiUnEnemigoNoEsSubterraneoRespondeDeLaFormaIndicada()
    {
        Defensa defensa = mock(Defensa.class);
        Subterraneo sub = new NoEsSubterraneo();
        int respuestaEsperada = 0;

        int respuesta = sub.subterraneo(defensa);

        assertEquals(respuestaEsperada, respuesta);
    }
}
