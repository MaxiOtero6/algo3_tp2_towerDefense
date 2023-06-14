package edu.fiuba.algo3.unitTest.Enemigos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Lechuza;
import edu.fiuba.algo3.modelo.Enemigos.Volador.EsVolador;
import edu.fiuba.algo3.modelo.Enemigos.Volador.NoEsVolador;

public class VoladorTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01SiUnEnemigoEsVoladorRespondeDeLaFormaIndicada()
    {
        Enemigo enemigo = new Lechuza(null,null);
        enemigo.setVolador(new EsVolador());
        boolean respuestaEsperada = true;

        boolean respuesta = enemigo.volador();

        assertEquals(respuestaEsperada, respuesta);
    }

    @Test
    public void test02SiUnEnemigoNoEsVoladorRespondeDeLaFormaIndicada()
    {
        Enemigo enemigo = new Lechuza(null,null);
        enemigo.setVolador(new NoEsVolador());
        boolean respuestaEsperada = false;

        boolean respuesta = enemigo.volador();

        assertEquals(respuestaEsperada, respuesta);
    }
}
