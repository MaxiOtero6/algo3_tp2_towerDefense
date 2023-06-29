package edu.fiuba.algo3.unitTest.Defensas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Defensas.Objetivos.ObjetivoTrampa;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;
import edu.fiuba.algo3.modelo.Enemigos.NoEnemigo;

public class ObjetivoTrampaTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01SiUnEnemigoEstaEnRangoEsObjetivo()
    {
        Enemigo enemigoMock = mock(Hormiga.class);
        LinkedList<Enemigo> enemigos = new LinkedList<>(); enemigos.add(enemigoMock);
        Posicion posicion1 = new Posicion(0, 0);
        Posicion posicion2 = new Posicion(3, 3);

        List<Enemigo> enemigoEsperado = new LinkedList<>();
        enemigoEsperado.add(enemigoMock);

        Enemigo enemigotemp = new Hormiga(null,null);
        enemigotemp.setearPosicion(posicion2);
        enemigos.add(enemigotemp);

        doReturn(0.0).when(enemigoMock).calcDistancia(any(), any(TrampaArenosa.class));

        ObjetivoTrampa objetivo = new ObjetivoTrampa();
        List<Enemigo> enemigoObtenido = objetivo.hallarObjetivo(posicion1, enemigos, new TrampaArenosa());

        assertEquals(enemigoEsperado, enemigoObtenido);
    }

    @Test
    public void test02SiNingunEnemigoCumpleLosRequisitosSeGeneraUnFalsoObjetivo()
    {
        Enemigo enemigoMock = mock(Hormiga.class);
        LinkedList<Enemigo> enemigos = new LinkedList<>(); enemigos.add(enemigoMock);
        Posicion posicion1 = new Posicion(0, 0);

        Enemigo enemigotemp = new NoEnemigo();
        List<Enemigo> enemigoEsperado = new LinkedList<>();
        enemigoEsperado.add(enemigotemp);
        doReturn(1.0).when(enemigoMock).calcDistancia(any(), any(TrampaArenosa.class));

        ObjetivoTrampa objetivo = new ObjetivoTrampa();
        List<Enemigo> enemigoObtenido = objetivo.hallarObjetivo(posicion1, enemigos, new TrampaArenosa());

        assertEquals(enemigoEsperado, enemigoObtenido);
    }

}
