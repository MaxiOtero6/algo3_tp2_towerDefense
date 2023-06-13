package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import edu.fiuba.algo3.modelo.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.fasterxml.jackson.databind.*;

import edu.fiuba.algo3.modelo.Parser.CreadorEnemigos;
import edu.fiuba.algo3.modelo.Parser.CreadorMapa;
import edu.fiuba.algo3.modelo.Parser.ParserJSON;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorrePlateada;
import edu.fiuba.algo3.modelo.Enemigos.*;
import edu.fiuba.algo3.modelo.Errores.GanarPartidaError;
import edu.fiuba.algo3.modelo.Errores.PerderPartidaError;
import edu.fiuba.algo3.modelo.Parcelas.*;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.*;
import edu.fiuba.algo3.modelo.Parcelas.Tierra.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class Tests2 {

    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }
    @Test
    public void test13ElFormatoDelJsonEnemigosEsValido()
    {
        JsonNode lecturaJson;        
        String jsonSimulado = "[{\"turno\": 1,\"enemigos\": {\"hormiga\": 1,\"arana\": 0}},	{\"turno\": 2,\"enemigos\": {\"hormiga\": 1,\"arana\": 1}},{\"turno\": 3,\"enemigos\": {\"hormiga\": 2,\"arana\": 1}},{\"turno\": 4,\"enemigos\": {\"hormiga\": 0,\"arana\": 1}},	{\"turno\": 5,\"enemigos\": {\"hormiga\": 1,\"arana\": 1}},	{\"turno\": 6,\"enemigos\": {\"hormiga\": 1,\"arana\": 2}},	{\"turno\": 7,\"enemigos\": {\"hormiga\": 0,\"arana\": 1}},	{\"turno\": 8,\"enemigos\": {\"hormiga\": 1,\"arana\": 0}},	{\"turno\": 9,\"enemigos\": {\"hormiga\": 1,\"arana\": 0}},	{\"turno\": 10,\"enemigos\": {\"hormiga\": 2,\"arana\": 0}},	{\"turno\": 11,\"enemigos\": {\"hormiga\": 0,\"arana\": 1}},	{\"turno\": 12,\"enemigos\": {\"hormiga\": 1,\"arana\": 2}}]";
        JsonNode lecturaEsperada = null;   
        
        try
        {
            lecturaEsperada = (new ObjectMapper()).readTree(jsonSimulado);
        }
        catch (IOException e) {}

        lecturaJson = ParserJSON.leerJSON("src/main/resources/json/enemigos.json");

        assertNotEquals(null, lecturaEsperada);
        assertNotEquals(null, lecturaJson);
        assertEquals(lecturaEsperada, lecturaJson);

    }

    @Test
    public void test14ElFormatoDelJsonMapaEsValido()
    {
        JsonNode lecturaJson;
        String jsonSimulado = "{\"Mapa\": {\"1\":[\"Rocoso\",\"Pasarela\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Rocoso\",\"Rocoso\",\"Rocoso\",\"Rocoso\",\"Rocoso\"],\"2\":[\"Tierra\",\"Pasarela\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Rocoso\",\"Rocoso\",\"Rocoso\",\"Rocoso\",\"Rocoso\"],\"3\":[\"Tierra\",\"Pasarela\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Rocoso\",\"Rocoso\",\"Rocoso\",\"Rocoso\",\"Rocoso\"],\"4\":[\"Tierra\",\"Pasarela\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Rocoso\",\"Rocoso\",\"Rocoso\",\"Rocoso\",\"Rocoso\"],\"5\":[\"Tierra\",\"Pasarela\",\"Rocoso\",\"Rocoso\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\"],\"6\":[\"Tierra\",\"Pasarela\",\"Rocoso\",\"Rocoso\",\"Tierra\",\"Tierra\",\"Tierra\",\"Rocoso\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\"],\"7\":[\"Tierra\",\"Pasarela\",\"Pasarela\",\"Pasarela\",\"Pasarela\",\"Pasarela\",\"Pasarela\",\"Pasarela\",\"Pasarela\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\"],\"8\":[\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Pasarela\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\"],\"9\":[\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Pasarela\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\"],\"10\":[\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Pasarela\",\"Rocoso\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\"],\"11\":[\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Pasarela\",\"Pasarela\",\"Pasarela\",\"Pasarela\",\"Pasarela\",\"Pasarela\",\"Pasarela\"],\"12\":[\"Rocoso\",\"Rocoso\",\"Rocoso\",\"Rocoso\",\"Rocoso\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\"],\"13\":[\"Rocoso\",\"Rocoso\",\"Rocoso\",\"Rocoso\",\"Rocoso\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\"],\"14\":[\"Rocoso\",\"Rocoso\",\"Rocoso\",\"Rocoso\",\"Rocoso\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\"],\"15\":[\"Rocoso\",\"Rocoso\",\"Rocoso\",\"Rocoso\",\"Rocoso\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\",\"Tierra\"]}}";
        JsonNode lecturaEsperada = null;
        
        try
        {
            lecturaEsperada = (new ObjectMapper()).readTree(jsonSimulado);
        }
        catch (IOException e) {}

        lecturaJson = ParserJSON.leerJSON("src/main/resources/json/mapa.json");

        assertNotEquals(null, lecturaEsperada);
        assertNotEquals(null, lecturaJson);
        assertEquals(lecturaEsperada, lecturaJson);
    }

    @Test
    public void test15LaConversionDelJsonEnemigosEsLaIndicada()
    {
        LinkedList<LinkedList<Enemigo>> enemigos = new LinkedList<>();
        LinkedList<LinkedList<Enemigo>> enemigosEsperados = new LinkedList<>();
        Enemigo[][] enemigosPorTurno = {{new Hormiga(null,null)}, {new Hormiga(null,null), new Arania(null,null)}, {new Hormiga(null,null), new Hormiga(null,null), new Arania(null,null)}, {new Arania(null,null)}, {new Hormiga(null,null), new Arania(null,null)}, {new Hormiga(null,null), new Arania(null,null), new Arania(null,null)}, {new Arania(null,null)}, {new Hormiga(null,null)}, {new Hormiga(null,null)}, {new Hormiga(null,null), new Hormiga(null,null)}, {new Arania(null,null)}, {new Hormiga(null,null), new Arania(null,null), new Arania(null,null)}};
        
        for (Enemigo[] turno : enemigosPorTurno) {    
            enemigosEsperados.add(new LinkedList<>(Arrays.asList(turno)));
        }

        for (int i = 0; i < enemigosPorTurno.length; i++) {
            enemigos.add(new LinkedList<>(CreadorEnemigos.crearEnemigos(i, null,null)));
        }

        assertEquals(enemigosEsperados, enemigos);
    }

    @Test
    public void test16LaConversionDelJsonMapaEsLaIndicada()
    {
        List<List<Parcela>> mapa;
        List<List<Parcela>> mapaEsperado = new LinkedList<>();
        Parcela[][] filasMapa = {
            {new Rocoso(0,0),new Pasarela(1,0),new Tierra(2,0),new Tierra(3,0),new Tierra(4,0),new Tierra(5,0),new Tierra(6,0),new Tierra(7,0),new Tierra(8,0),new Tierra(9,0),new Rocoso(10,0),new Rocoso(11,0),new Rocoso(12,0),new Rocoso(13,0),new Rocoso(14,0)},
            {new Tierra(0,1),new Pasarela(1,1),new Tierra(2,1),new Tierra(3,1),new Tierra(4,1),new Tierra(5,1),new Tierra(6,1),new Tierra(7,1),new Tierra(8,1),new Tierra(9,1),new Rocoso(10,1),new Rocoso(11,1),new Rocoso(12,1),new Rocoso(13,1),new Rocoso(14,1)},
            {new Tierra(0,2),new Pasarela(1,2),new Tierra(2,2),new Tierra(3,2),new Tierra(4,2),new Tierra(5,2),new Tierra(6,2),new Tierra(7,2),new Tierra(8,2),new Tierra(9,2),new Rocoso(10,2),new Rocoso(11,2),new Rocoso(12,2),new Rocoso(13,2),new Rocoso(14,2)},
            {new Tierra(0,3),new Pasarela(1,3),new Tierra(2,3),new Tierra(3,3),new Tierra(4,3),new Tierra(5,3),new Tierra(6,3),new Tierra(7,3),new Tierra(8,3),new Tierra(9,3),new Rocoso(10,3),new Rocoso(11,3),new Rocoso(12,3),new Rocoso(13,3),new Rocoso(14,3)},
            {new Tierra(0,4),new Pasarela(1,4),new Rocoso(2,4),new Rocoso(3,4),new Tierra(4,4),new Tierra(5,4),new Tierra(6,4),new Tierra(7,4),new Tierra(8,4),new Tierra(9,4),new Tierra(10,4),new Tierra(11,4),new Tierra(12,4),new Tierra(13,4),new Tierra(14,4)},
            {new Tierra(0,5),new Pasarela(1,5),new Rocoso(2,5),new Rocoso(3,5),new Tierra(4,5),new Tierra(5,5),new Tierra(6,5),new Rocoso(7,5),new Tierra(8,5),new Tierra(9,5),new Tierra(10,5),new Tierra(11,5),new Tierra(12,5),new Tierra(13,5),new Tierra(14,5)},
            {new Tierra(0,6),new Pasarela(1,6),new Pasarela(2,6),new Pasarela(3,6),new Pasarela(4,6),new Pasarela(5,6),new Pasarela(6,6),new Pasarela(7,6),new Pasarela(8,6),new Tierra(9,6),new Tierra(10,6),new Tierra(11,6),new Tierra(12,6),new Tierra(13,6),new Tierra(14,6)},
            {new Tierra(0,7),new Tierra(1,7),new Tierra(2,7),new Tierra(3,7),new Tierra(4,7),new Tierra(5,7),new Tierra(6,7),new Tierra(7,7),new Pasarela(8,7),new Tierra(9,7),new Tierra(10,7),new Tierra(11,7),new Tierra(12,7),new Tierra(13,7),new Tierra(14,7)},
            {new Tierra(0,8),new Tierra(1,8),new Tierra(2,8),new Tierra(3,8),new Tierra(4,8),new Tierra(5,8),new Tierra(6,8),new Tierra(7,8),new Pasarela(8,8),new Tierra(9,8),new Tierra(10,8),new Tierra(11,8),new Tierra(12,8),new Tierra(13,8),new Tierra(14,8)},
            {new Tierra(0,9),new Tierra(1,9),new Tierra(2,9),new Tierra(3,9),new Tierra(4,9),new Tierra(5,9),new Tierra(6,9),new Tierra(7,9),new Pasarela(8,9),new Rocoso(9,9),new Tierra(10,9),new Tierra(11,9),new Tierra(12,9),new Tierra(13,9),new Tierra(14,9)},
            {new Tierra(0,10),new Tierra(1,10),new Tierra(2,10),new Tierra(3,10),new Tierra(4,10),new Tierra(5,10),new Tierra(6,10),new Tierra(7,10),new Pasarela(8,10),new Pasarela(9,10),new Pasarela(10,10),new Pasarela(11,10),new Pasarela(12,10),new Pasarela(13,10),new Pasarela(14,10)},
            {new Rocoso(0,11),new Rocoso(1,11),new Rocoso(2,11),new Rocoso(3,11),new Rocoso(4,11),new Tierra(5,11),new Tierra(6,11),new Tierra(7,11),new Tierra(8,11),new Tierra(9,11),new Tierra(10,11),new Tierra(11,11),new Tierra(12,11),new Tierra(13,11),new Tierra(14,11)},
            {new Rocoso(0,12),new Rocoso(1,12),new Rocoso(2,12),new Rocoso(3,12),new Rocoso(4,12),new Tierra(5,12),new Tierra(6,12),new Tierra(7,12),new Tierra(8,12),new Tierra(9,12),new Tierra(10,12),new Tierra(11,12),new Tierra(12,12),new Tierra(13,12),new Tierra(14,12)},
            {new Rocoso(0,13),new Rocoso(1,13),new Rocoso(2,13),new Rocoso(3,13),new Rocoso(4,13),new Tierra(5,13),new Tierra(6,13),new Tierra(7,13),new Tierra(8,13),new Tierra(9,13),new Tierra(10,13),new Tierra(11,13),new Tierra(12,13),new Tierra(13,13),new Tierra(14,13)},
            {new Rocoso(0,14),new Rocoso(1,14),new Rocoso(2,14),new Rocoso(3,14),new Rocoso(4,14),new Tierra(5,14),new Tierra(6,14),new Tierra(7,14),new Tierra(8,14),new Tierra(9,14),new Tierra(10,14),new Tierra(11,14),new Tierra(12,14),new Tierra(13,14),new Tierra(14,14)}
            };

        for (Parcela[] fila : filasMapa) {    
            mapaEsperado.add(new LinkedList<>(Arrays.asList(fila)));
        }

        mapa = CreadorMapa.crearMapa(null);

        assertEquals(mapaEsperado, mapa);
    }

    @Test
    public void test17ElJuegoSeCreaAcordeALosJson()
    {
        List<List<Parcela>> mapa;
        List<List<Parcela>> mapaEsperado = new LinkedList<>();
        List<Parcela> pasarelas = new LinkedList<>();
        
        Parcela[][] filasMapa = {
            {new Rocoso(0,0),new Pasarela(1,0),new Tierra(2,0),new Tierra(3,0),new Tierra(4,0),new Tierra(5,0),new Tierra(6,0),new Tierra(7,0),new Tierra(8,0),new Tierra(9,0),new Rocoso(10,0),new Rocoso(11,0),new Rocoso(12,0),new Rocoso(13,0),new Rocoso(14,0)},
            {new Tierra(0,1),new Pasarela(1,1),new Tierra(2,1),new Tierra(3,1),new Tierra(4,1),new Tierra(5,1),new Tierra(6,1),new Tierra(7,1),new Tierra(8,1),new Tierra(9,1),new Rocoso(10,1),new Rocoso(11,1),new Rocoso(12,1),new Rocoso(13,1),new Rocoso(14,1)},
            {new Tierra(0,2),new Pasarela(1,2),new Tierra(2,2),new Tierra(3,2),new Tierra(4,2),new Tierra(5,2),new Tierra(6,2),new Tierra(7,2),new Tierra(8,2),new Tierra(9,2),new Rocoso(10,2),new Rocoso(11,2),new Rocoso(12,2),new Rocoso(13,2),new Rocoso(14,2)},
            {new Tierra(0,3),new Pasarela(1,3),new Tierra(2,3),new Tierra(3,3),new Tierra(4,3),new Tierra(5,3),new Tierra(6,3),new Tierra(7,3),new Tierra(8,3),new Tierra(9,3),new Rocoso(10,3),new Rocoso(11,3),new Rocoso(12,3),new Rocoso(13,3),new Rocoso(14,3)},
            {new Tierra(0,4),new Pasarela(1,4),new Rocoso(2,4),new Rocoso(3,4),new Tierra(4,4),new Tierra(5,4),new Tierra(6,4),new Tierra(7,4),new Tierra(8,4),new Tierra(9,4),new Tierra(10,4),new Tierra(11,4),new Tierra(12,4),new Tierra(13,4),new Tierra(14,4)},
            {new Tierra(0,5),new Pasarela(1,5),new Rocoso(2,5),new Rocoso(3,5),new Tierra(4,5),new Tierra(5,5),new Tierra(6,5),new Rocoso(7,5),new Tierra(8,5),new Tierra(9,5),new Tierra(10,5),new Tierra(11,5),new Tierra(12,5),new Tierra(13,5),new Tierra(14,5)},
            {new Tierra(0,6),new Pasarela(1,6),new Pasarela(2,6),new Pasarela(3,6),new Pasarela(4,6),new Pasarela(5,6),new Pasarela(6,6),new Pasarela(7,6),new Pasarela(8,6),new Tierra(9,6),new Tierra(10,6),new Tierra(11,6),new Tierra(12,6),new Tierra(13,6),new Tierra(14,6)},
            {new Tierra(0,7),new Tierra(1,7),new Tierra(2,7),new Tierra(3,7),new Tierra(4,7),new Tierra(5,7),new Tierra(6,7),new Tierra(7,7),new Pasarela(8,7),new Tierra(9,7),new Tierra(10,7),new Tierra(11,7),new Tierra(12,7),new Tierra(13,7),new Tierra(14,7)},
            {new Tierra(0,8),new Tierra(1,8),new Tierra(2,8),new Tierra(3,8),new Tierra(4,8),new Tierra(5,8),new Tierra(6,8),new Tierra(7,8),new Pasarela(8,8),new Tierra(9,8),new Tierra(10,8),new Tierra(11,8),new Tierra(12,8),new Tierra(13,8),new Tierra(14,8)},
            {new Tierra(0,9),new Tierra(1,9),new Tierra(2,9),new Tierra(3,9),new Tierra(4,9),new Tierra(5,9),new Tierra(6,9),new Tierra(7,9),new Pasarela(8,9),new Rocoso(9,9),new Tierra(10,9),new Tierra(11,9),new Tierra(12,9),new Tierra(13,9),new Tierra(14,9)},
            {new Tierra(0,10),new Tierra(1,10),new Tierra(2,10),new Tierra(3,10),new Tierra(4,10),new Tierra(5,10),new Tierra(6,10),new Tierra(7,10),new Pasarela(8,10),new Pasarela(9,10),new Pasarela(10,10),new Pasarela(11,10),new Pasarela(12,10),new Pasarela(13,10),new Pasarela(14,10)},
            {new Rocoso(0,11),new Rocoso(1,11),new Rocoso(2,11),new Rocoso(3,11),new Rocoso(4,11),new Tierra(5,11),new Tierra(6,11),new Tierra(7,11),new Tierra(8,11),new Tierra(9,11),new Tierra(10,11),new Tierra(11,11),new Tierra(12,11),new Tierra(13,11),new Tierra(14,11)},
            {new Rocoso(0,12),new Rocoso(1,12),new Rocoso(2,12),new Rocoso(3,12),new Rocoso(4,12),new Tierra(5,12),new Tierra(6,12),new Tierra(7,12),new Tierra(8,12),new Tierra(9,12),new Tierra(10,12),new Tierra(11,12),new Tierra(12,12),new Tierra(13,12),new Tierra(14,12)},
            {new Rocoso(0,13),new Rocoso(1,13),new Rocoso(2,13),new Rocoso(3,13),new Rocoso(4,13),new Tierra(5,13),new Tierra(6,13),new Tierra(7,13),new Tierra(8,13),new Tierra(9,13),new Tierra(10,13),new Tierra(11,13),new Tierra(12,13),new Tierra(13,13),new Tierra(14,13)},
            {new Rocoso(0,14),new Rocoso(1,14),new Rocoso(2,14),new Rocoso(3,14),new Rocoso(4,14),new Tierra(5,14),new Tierra(6,14),new Tierra(7,14),new Tierra(8,14),new Tierra(9,14),new Tierra(10,14),new Tierra(11,14),new Tierra(12,14),new Tierra(13,14),new Tierra(14,14)}
            };

        for (Parcela[] fila : filasMapa) {    
            mapaEsperado.add(new LinkedList<>(Arrays.asList(fila)));
        }
        Partida partidaEsperada = new Partida(mapaEsperado, pasarelas);

        mapa = CreadorMapa.crearMapa(pasarelas);
        Partida partida = new Partida(mapa, pasarelas);
        
        assertEquals(partidaEsperada, partida);
    }

    @Test
    public void test18ElJugadorGanaLaPartidaSimulada()
    {
        Jugador jugador = new Jugador(1000000, 1000000);
        List<Parcela> camino = new LinkedList<>();
        Partida partida = new Partida(CreadorMapa.crearMapa(camino), camino, jugador);
        partida.construir(new TorrePlateada(), 0,1);
        partida.construir(new TorrePlateada(), 0,2);
        partida.construir(new TorrePlateada(), 0,3);
        partida.construir(new TorrePlateada(), 0,4);
        partida.construir(new TorrePlateada(), 0,5);
        partida.construir(new TorreBlanca(), 2,0);
        partida.construir(new TorreBlanca(), 2,1);
        partida.construir(new TorreBlanca(), 2,2);
        partida.construir(new TorreBlanca(), 2,3);

        assertThrows(GanarPartidaError.class, () -> partida.iniciar());
    }

    @Test
    public void test19ElJugadorPierdeLaPartidaSimulada()
    {
        List<Parcela> camino = new LinkedList<>();
        Partida partida = new Partida(CreadorMapa.crearMapa(camino), camino);
        assertThrows(PerderPartidaError.class, () -> partida.iniciar());
    }

    @Test
    public void test20VerificacionConsoleLog()
    {
        Logger mockedLogger = mock(Logger.class);
        SingleLogger.inicializar(mockedLogger);

        Jugador jugador = new Jugador();
        jugador.recibirDanio(5, "Arania");

        verify(mockedLogger, times(1)).info("Arania llega a la meta, produce 5 de daño al jugador");
    }
}
