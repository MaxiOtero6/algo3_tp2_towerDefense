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
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;
import edu.fiuba.algo3.modelo.Enemigos.Topo;

public class ObjetivoTorreTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01SiUnEnemigoNoEsSubterraneoEstaVivoYEstaEnRangoEsObjetivo()
    {
        Enemigo enemigoMock = mock(Topo.class);
        LinkedList<Enemigo> enemigos = new LinkedList<>(); enemigos.add(enemigoMock);
        Posicion posicion1 = new Posicion(0, 0);
        Posicion posicion2 = new Posicion(3, 3);

        Enemigo enemigotemp = new Hormiga(null,null);
        enemigotemp.setearPosicion(posicion2);
        enemigos.add(enemigotemp);

        when(enemigoMock.subterraneo()).thenReturn(false);
        when(enemigoMock.estaVivo()).thenReturn(true);
        doReturn(1.0).when(enemigoMock).calcDistancia(any());

        ObjetivoTorre objetivo = new ObjetivoTorre();
        Enemigo enemigoObtenido = objetivo.hallarObjetivo(posicion1, enemigos, 3);

        assertEquals(enemigoMock, enemigoObtenido);
    }

    @Test
    public void test02SiUnEnemigoEsSubterraneoNoEsObjetivo()
    {
        Enemigo enemigoMock = mock(Topo.class);
        LinkedList<Enemigo> enemigos = new LinkedList<>(); enemigos.add(enemigoMock);
        Posicion posicion1 = new Posicion(0, 0);
        Posicion posicion2 = new Posicion(3, 0);
        
        Enemigo enemigotemp = new Hormiga(null,null);
        enemigotemp.setearPosicion(posicion2);
        enemigos.add(enemigotemp);

        when(enemigoMock.subterraneo()).thenReturn(true);
        when(enemigoMock.estaVivo()).thenReturn(true);
        doReturn(1.0).when(enemigoMock).calcDistancia(any());

        ObjetivoTorre objetivo = new ObjetivoTorre();
        Enemigo enemigoObtenido = objetivo.hallarObjetivo(posicion1, enemigos, 3);

        assertEquals(enemigotemp, enemigoObtenido);
    }

    @Test
    public void test03ElEnemigoMasCercanoEsElObjetivo()
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
        Enemigo enemigoObtenido = objetivo.hallarObjetivo(posicion1, enemigos, 3);

        assertEquals(enemigo1, enemigoObtenido);
    }

    @Test
    public void test04SiUnEnemigoNoEsSubterraneoPeroNoEstaVivoNoEsObjetivo()
    {
        Enemigo enemigoMock = mock(Topo.class);
        LinkedList<Enemigo> enemigos = new LinkedList<>(); enemigos.add(enemigoMock);
        Posicion posicion1 = new Posicion(0, 0);
        Posicion posicion2 = new Posicion(3, 0);
        
        Enemigo enemigotemp = new Hormiga(null,null);
        enemigotemp.setearPosicion(posicion2);
        enemigos.add(enemigotemp);

        when(enemigoMock.subterraneo()).thenReturn(false);
        when(enemigoMock.estaVivo()).thenReturn(false);
        doReturn(1.0).when(enemigoMock).calcDistancia(any());

        ObjetivoTorre objetivo = new ObjetivoTorre();
        Enemigo enemigoObtenido = objetivo.hallarObjetivo(posicion1, enemigos, 3);

        assertEquals(enemigotemp, enemigoObtenido);
    }

    @Test
    public void test05SiUnEnemigoNoEsSubterraneoEstaVivoPeroNoEstaEnRangoNoEsObjetivo()
    {
        Enemigo enemigoMock = mock(Topo.class);
        LinkedList<Enemigo> enemigos = new LinkedList<>(); enemigos.add(enemigoMock);
        Posicion posicion1 = new Posicion(0, 0);
        Posicion posicion2 = new Posicion(3, 0);
        
        Enemigo enemigotemp = new Hormiga(null,null);
        enemigotemp.setearPosicion(posicion2);
        enemigos.add(enemigotemp);

        when(enemigoMock.subterraneo()).thenReturn(false);
        when(enemigoMock.estaVivo()).thenReturn(false);
        doReturn(1000.0).when(enemigoMock).calcDistancia(any());

        ObjetivoTorre objetivo = new ObjetivoTorre();
        Enemigo enemigoObtenido = objetivo.hallarObjetivo(posicion1, enemigos, 3);

        assertEquals(enemigotemp, enemigoObtenido);
    }
}
