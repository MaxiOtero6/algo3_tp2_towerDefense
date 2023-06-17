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
import edu.fiuba.algo3.modelo.Enemigos.Lechuza;
import edu.fiuba.algo3.modelo.Enemigos.Volador.EsVolador;
import edu.fiuba.algo3.modelo.Enemigos.Volador.NoEsVolador;
import edu.fiuba.algo3.modelo.Enemigos.Volador.Volador;

public class VoladorTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01SiUnEnemigoEsVoladorYLaDefensaEsUnaTorreRespondeDeLaFormaIndicada()
    {
        Defensa defensa = mock(TorreBlanca.class);
        Volador vol = new EsVolador();
        int respuestaEsperada = 0;

        int respuesta = vol.volador(defensa);

        assertEquals(respuestaEsperada, respuesta);
    }

    @Test
    public void test02SiUnEnemigoEsVoladorYLaDefensaEsUnaTrampaRespondeDeLaFormaIndicada()
    {
        Defensa defensa = mock(TrampaArenosa.class);
        Volador vol = new EsVolador();
        int respuestaEsperada = 1000;

        int respuesta = vol.volador(defensa);

        assertEquals(respuestaEsperada, respuesta);
    }

    @Test
    public void test03SiUnEnemigoNoEsVoladorRespondeDeLaFormaIndicada()
    {
        Defensa defensa = mock(Defensa.class);
        Volador vol = new NoEsVolador();
        int respuestaEsperada = 0;

        int respuesta = vol.volador(defensa);

        assertEquals(respuestaEsperada, respuesta);
    }
}
