package edu.fiuba.algo3.unitTest.Enemigos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Topo;
import edu.fiuba.algo3.modelo.Enemigos.Subterraneo.EsSubterraneo;
import edu.fiuba.algo3.modelo.Enemigos.Subterraneo.NoEsSubterraneo;

public class SubterraneoTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01SiUnEnemigoEsSubterraneoRespondeDeLaFormaIndicada()
    {
        Enemigo enemigo = new Topo(null,null);
        enemigo.setSubterraneo(new EsSubterraneo());
        boolean respuestaEsperada = true;

        boolean respuesta = enemigo.subterraneo();

        assertEquals(respuestaEsperada, respuesta);
    }

    @Test
    public void test02SiUnEnemigoNoEsSubterraneoRespondeDeLaFormaIndicada()
    {
        Enemigo enemigo = new Topo(null,null);
        enemigo.setSubterraneo(new NoEsSubterraneo());
        boolean respuestaEsperada = false;

        boolean respuesta = enemigo.subterraneo();

        assertEquals(respuestaEsperada, respuesta);
    }
}
