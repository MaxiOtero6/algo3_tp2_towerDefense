package edu.fiuba.algo3.unitTest.Defensas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Defensas.Objetivos.ObjetivoTorre;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;
import edu.fiuba.algo3.modelo.Enemigos.NoEnemigo;
import edu.fiuba.algo3.modelo.Enemigos.Topo;

public class ObjetivoTorreTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01SiUnEnemigoNoEsSubterraneoYEstaEnRangoEsObjetivo()
    {
        Enemigo enemigoMock = mock(Topo.class);
        LinkedList<Enemigo> enemigos = new LinkedList<>(); enemigos.add(enemigoMock);
        Posicion posicion1 = new Posicion(0, 0);
        Posicion posicion2 = new Posicion(3, 3);

        Enemigo enemigotemp = new Hormiga(null,null);
        enemigotemp.setearPosicion(posicion2);
        enemigos.add(enemigotemp);

        doReturn(1.0).when(enemigoMock).calcDistancia(any(), any(TorreBlanca.class));

        ObjetivoTorre objetivo = new ObjetivoTorre();
        Enemigo enemigoObtenido = objetivo.hallarObjetivo(posicion1, enemigos, 3, new TorreBlanca());

        assertEquals(enemigoMock, enemigoObtenido);
    }

    @Test
    public void test02ElEnemigoMasCercanoEsElObjetivo()
    {
        LinkedList<Enemigo> enemigos = new LinkedList<>();
        Posicion posicion1 = new Posicion(0, 0);
        Posicion posicion2 = new Posicion(3, 0);
        
        Enemigo enemigo1 = new Hormiga(null,null);
        enemigo1.setearPosicion(posicion1);
        enemigos.add(enemigo1);

        Enemigo enemigo2 = new Hormiga(null,null);
        enemigo2.setearPosicion(posicion2);
        enemigos.add(enemigo2);

        ObjetivoTorre objetivo = new ObjetivoTorre();
        Enemigo enemigoObtenido = objetivo.hallarObjetivo(posicion1, enemigos, 3, new TorreBlanca());

        assertEquals(enemigo1, enemigoObtenido);
    }

    @Test
    public void test03SiNingunEnemigoCumpleLosRequisitosSeGeneraUnFalsoObjetivo()
    {
        Enemigo enemigoMock = mock(Hormiga.class);
        LinkedList<Enemigo> enemigos = new LinkedList<>(); enemigos.add(enemigoMock);
        Posicion posicion1 = new Posicion(0, 0);

        Enemigo enemigotemp = new NoEnemigo();
        
        doReturn(100.0).when(enemigoMock).calcDistancia(any(), any(TorreBlanca.class));

        ObjetivoTorre objetivo = new ObjetivoTorre();
        Enemigo enemigoObtenido = objetivo.hallarObjetivo(posicion1, enemigos, 3, new TorreBlanca());

        assertEquals(enemigotemp, enemigoObtenido);
    }
}
