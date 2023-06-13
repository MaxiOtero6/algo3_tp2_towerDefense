package edu.fiuba.algo3.unitTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Defensas.Torres.Torre;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parcelas.Rocoso;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Largada;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Meta;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Parcelas.Tierra.Tierra;

public class MapaTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01NoSePuedeConstruirEnRocoso()
    {
        Torre defensa = new TorreBlanca();
        Rocoso rocosoMock = mock(Rocoso.class);
        doThrow(new TerrenoDeConstruccionInvalidoError()).when(rocosoMock).construir(defensa);
        List<List<Parcela>> lista = new LinkedList<>();
        List<Parcela> lista2 = new LinkedList<>();
        lista2.add(rocosoMock);
        lista.add(lista2);
        Mapa mapa = new Mapa(lista);
        assertThrows(TerrenoDeConstruccionInvalidoError.class, () -> mapa.construir(defensa, 0, 0));
    }

    @Test
    public void test01SePuedeConstruirEnTierra()
    {
        Torre defensa = new TorreBlanca();
        Tierra tierraMock = mock(Tierra.class);
        doNothing().when(tierraMock).construir(defensa);
        List<List<Parcela>> lista = new LinkedList<>();
        List<Parcela> lista2 = new LinkedList<>();
        lista2.add(tierraMock);
        lista.add(lista2);
        Mapa mapa = new Mapa(lista);
        assertDoesNotThrow(() -> mapa.construir(defensa, 0, 0));
    }

    @Test
    public void test01NoSePuedeConstruirEnTierraOcupada()
    {
        Torre defensa = new TorreBlanca();
        Tierra tierraMock = mock(Tierra.class);
        doThrow(new TerrenoDeConstruccionInvalidoError()).when(tierraMock).construir(defensa);
        List<List<Parcela>> lista = new LinkedList<>();
        List<Parcela> lista2 = new LinkedList<>();
        lista2.add(tierraMock);
        lista.add(lista2);
        Mapa mapa = new Mapa(lista);
        assertThrows(TerrenoDeConstruccionInvalidoError.class, () -> mapa.construir(defensa, 0, 0));
    }

    @Test
    public void test01SePuedeConstruirEnPasarela()
    {
        Torre defensa = new TorreBlanca();
        Pasarela pasarelaMock = mock(Pasarela.class);
        doNothing().when(pasarelaMock).construir(defensa);
        List<List<Parcela>> lista = new LinkedList<>();
        List<Parcela> lista2 = new LinkedList<>();
        lista2.add(pasarelaMock);
        lista.add(lista2);
        Mapa mapa = new Mapa(lista);
        assertDoesNotThrow(() -> mapa.construir(defensa, 0, 0));
    }

    @Test
    public void test01NoSePuedeConstruirEnPasarelaOcupada()
    {
        Torre defensa = new TorreBlanca();
        Pasarela pasarelaMock = mock(Pasarela.class);
        doThrow(new TerrenoDeConstruccionInvalidoError()).when(pasarelaMock).construir(defensa);
        List<List<Parcela>> lista = new LinkedList<>();
        List<Parcela> lista2 = new LinkedList<>();
        lista2.add(pasarelaMock);
        lista.add(lista2);
        Mapa mapa = new Mapa(lista);
        assertThrows(TerrenoDeConstruccionInvalidoError.class, () -> mapa.construir(defensa, 0, 0));
    }

    @Test
    public void test01NoSePuedeConstruirEnMeta()
    {
        Torre defensa = new TorreBlanca();
        Meta metaMock = mock(Meta.class);
        doThrow(new TerrenoDeConstruccionInvalidoError()).when(metaMock).construir(defensa);
        List<List<Parcela>> lista = new LinkedList<>();
        List<Parcela> lista2 = new LinkedList<>();
        lista2.add(metaMock);
        lista.add(lista2);
        Mapa mapa = new Mapa(lista);
        assertThrows(TerrenoDeConstruccionInvalidoError.class, () -> mapa.construir(defensa, 0, 0));
    }

    @Test
    public void test01NoSePuedeConstruirEnLargada()
    {
        Torre defensa = new TorreBlanca();
        Largada largadaMock = mock(Largada.class);
        doThrow(new TerrenoDeConstruccionInvalidoError()).when(largadaMock).construir(defensa);
        List<List<Parcela>> lista = new LinkedList<>();
        List<Parcela> lista2 = new LinkedList<>();
        lista2.add(largadaMock);
        lista.add(lista2);
        Mapa mapa = new Mapa(lista);
        assertThrows(TerrenoDeConstruccionInvalidoError.class, () -> mapa.construir(defensa, 0, 0));
    }

    @Test
    public void test04AmbosMapasSonIguales()
    {
        List<List<Parcela>> lista1 = new LinkedList<>();
        List<Parcela> subLista = new LinkedList<>();
        subLista.add(new Pasarela(0,0));
        lista1.add(subLista);
        List<List<Parcela>> lista2 = new LinkedList<>();
        lista2.add(subLista);

        Mapa mapa1 = new Mapa(lista1);
        Mapa mapa2 = new Mapa(lista2);
        assertEquals(mapa1, mapa2);
        assertEquals(mapa1, mapa1);
    }

    @Test
    public void test04AmbosMapasNoSonIguales()
    {
        List<List<Parcela>> lista1 = new LinkedList<>();
        List<Parcela> subLista = new LinkedList<>();
        subLista.add(new Pasarela(0,0));
        lista1.add(subLista);
        List<List<Parcela>> lista2 = new LinkedList<>();
        List<Parcela> subLista2 = new LinkedList<>();
        subLista2.add(new Rocoso(0,0));
        lista2.add(subLista2);

        Mapa mapa1 = new Mapa(lista1);
        Mapa mapa2 = new Mapa(lista2);
        assertNotEquals(mapa1, mapa2);
        assertNotEquals(mapa1, lista1);
    }

}
