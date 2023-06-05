package edu.fiuba.algo3.entrega_2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.fasterxml.jackson.databind.*;

import edu.fiuba.algo3.modelo.Parser.CreadorEnemigos;
import edu.fiuba.algo3.modelo.Parser.CreadorMapa;
import edu.fiuba.algo3.modelo.Parser.ParserJSON;
import edu.fiuba.algo3.modelo.Mapa;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Enemigos.*;
import edu.fiuba.algo3.modelo.Parcelas.*;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.*;
import edu.fiuba.algo3.modelo.Parcelas.Tierra.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class Tests2 {
    
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
        Enemigo[][] enemigosPorTurno = {{new Hormiga()}, {new Hormiga(), new Arania()}, {new Hormiga(), new Hormiga(), new Arania()}, {new Arania()}, {new Hormiga(), new Arania()}, {new Hormiga(), new Arania(), new Arania()}, {new Arania()}, {new Hormiga()}, {new Hormiga()}, {new Hormiga(), new Hormiga()}, {new Arania()}, {new Hormiga(), new Arania(), new Arania()}};
        
        for (Enemigo[] turno : enemigosPorTurno) {    
            enemigosEsperados.add(new LinkedList<>(Arrays.asList(turno)));
        }

        for (int i = 0; i < enemigosPorTurno.length; i++) {
            enemigos.add(new LinkedList<>(CreadorEnemigos.crearEnemigos(i)));
        }

        assertEquals(enemigosEsperados, enemigos);
    }

    @Test
    public void test16LaConversionDelJsonMapaEsLaIndicada()
    {
        // Mapa mapa;
        // Mapa mapaEsperado;
        // List<List<Parcela>> mapaSimulado = new LinkedList<>();
        // Parcela[][] filasMapa = {
        //     {new Tierra(new Posicion(0,0)),new Pasarela(new Posicion(1,0)),new Tierra(new Posicion(2,0)),new Tierra(new Posicion(3,0)),new Tierra(new Posicion(4,0)),new Tierra(new Posicion(5,0)),new Tierra(new Posicion(6,0)),new Tierra(new Posicion(7,0)),new Tierra(new Posicion(8,0)),new Tierra(new Posicion(9,0)),new Rocoso(new Posicion(10,0)),new Rocoso(new Posicion(11,0)),new Rocoso(new Posicion(12,0)),new Rocoso(new Posicion(13,0)),new Rocoso(new Posicion(14,0))},
        //     {new Tierra(new Posicion(0,1)),new Pasarela(new Posicion(1,1)),new Tierra(new Posicion(2,1)),new Tierra(new Posicion(3,1)),new Tierra(new Posicion(4,1)),new Tierra(new Posicion(5,1)),new Tierra(new Posicion(6,1)),new Tierra(new Posicion(7,1)),new Tierra(new Posicion(8,1)),new Tierra(new Posicion(9,1)),new Rocoso(new Posicion(10,1)),new Rocoso(new Posicion(11,1)),new Rocoso(new Posicion(12,1)),new Rocoso(new Posicion(13,1)),new Rocoso(new Posicion(14,1))},
        //     {new Rocoso(new Posicion(0,2)),new Pasarela(new Posicion(1,2)),new Tierra(new Posicion(2,2)),new Tierra(new Posicion(3,2)),new Tierra(new Posicion(4,2)),new Tierra(new Posicion(5,2)),new Tierra(new Posicion(6,2)),new Tierra(new Posicion(7,2)),new Tierra(new Posicion(8,2)),new Tierra(new Posicion(9,2)),new Rocoso(new Posicion(10,2)),new Rocoso(new Posicion(11,2)),new Rocoso(new Posicion(12,2)),new Rocoso(new Posicion(13,2)),new Rocoso(new Posicion(14,2))},
        //     {new Tierra(new Posicion(0,3)),new Pasarela(new Posicion(1,3)),new Tierra(new Posicion(2,3)),new Tierra(new Posicion(3,3)),new Tierra(new Posicion(4,3)),new Tierra(new Posicion(5,3)),new Tierra(new Posicion(6,3)),new Tierra(new Posicion(7,3)),new Tierra(new Posicion(8,3)),new Tierra(new Posicion(9,3)),new Rocoso(new Posicion(10,3)),new Rocoso(new Posicion(11,3)),new Rocoso(new Posicion(12,3)),new Rocoso(new Posicion(13,3)),new Rocoso(new Posicion(14,3))},
        //     {new Tierra(new Posicion(0,4)),new Pasarela(new Posicion(1,4)),new Rocoso(new Posicion(2,4)),new Rocoso(new Posicion(3,4)),new Tierra(new Posicion(4,4)),new Tierra(new Posicion(5,4)),new Tierra(new Posicion(6,4)),new Tierra(new Posicion(7,4)),new Tierra(new Posicion(8,4)),new Tierra(new Posicion(9,4)),new Tierra(new Posicion(10,4)),new Tierra(new Posicion(11,4)),new Tierra(new Posicion(12,4)),new Tierra(new Posicion(13,4)),new Tierra(new Posicion(14,4))},
        //     {new Tierra(new Posicion(0,5)),new Pasarela(new Posicion(1,5)),new Rocoso(new Posicion(2,5)),new Rocoso(new Posicion(3,5)),new Tierra(new Posicion(4,5)),new Tierra(new Posicion(5,5)),new Tierra(new Posicion(6,5)),new Rocoso(new Posicion(7,5)),new Tierra(new Posicion(8,5)),new Tierra(new Posicion(9,5)),new Tierra(new Posicion(10,5)),new Tierra(new Posicion(11,5)),new Tierra(new Posicion(12,5)),new Tierra(new Posicion(13,5)),new Tierra(new Posicion(14,5))},
        //     {new Tierra(new Posicion(0,6)),new Pasarela(new Posicion(1,6)),new Pasarela(new Posicion(2,6)),new Pasarela(new Posicion(3,6)),new Pasarela(new Posicion(4,6)),new Pasarela(new Posicion(5,6)),new Pasarela(new Posicion(6,6)),new Pasarela(new Posicion(7,6)),new Pasarela(new Posicion(8,6)),new Tierra(new Posicion(9,6)),new Tierra(new Posicion(10,6)),new Tierra(new Posicion(11,6)),new Tierra(new Posicion(12,6)),new Tierra(new Posicion(13,6)),new Tierra(new Posicion(14,6))},
        //     {new Tierra(new Posicion(0,7)),new Tierra(new Posicion(1,7)),new Tierra(new Posicion(2,7)),new Tierra(new Posicion(3,7)),new Tierra(new Posicion(4,7)),new Tierra(new Posicion(5,7)),new Tierra(new Posicion(6,7)),new Tierra(new Posicion(7,7)),new Pasarela(new Posicion(8,7)),new Tierra(new Posicion(9,7)),new Tierra(new Posicion(10,7)),new Tierra(new Posicion(11,7)),new Tierra(new Posicion(12,7)),new Tierra(new Posicion(13,7)),new Tierra(new Posicion(14,7))},
        //     {new Tierra(new Posicion(0,8)),new Tierra(new Posicion(1,8)),new Tierra(new Posicion(2,8)),new Tierra(new Posicion(3,8)),new Tierra(new Posicion(4,8)),new Tierra(new Posicion(5,8)),new Tierra(new Posicion(6,8)),new Tierra(new Posicion(7,8)),new Pasarela(new Posicion(8,8)),new Tierra(new Posicion(9,8)),new Tierra(new Posicion(10,8)),new Tierra(new Posicion(11,8)),new Tierra(new Posicion(12,8)),new Tierra(new Posicion(13,8)),new Tierra(new Posicion(14,8))},
        //     {new Tierra(new Posicion(0,9)),new Tierra(new Posicion(1,9)),new Tierra(new Posicion(2,9)),new Tierra(new Posicion(3,9)),new Tierra(new Posicion(4,9)),new Tierra(new Posicion(5,9)),new Tierra(new Posicion(6,9)),new Tierra(new Posicion(7,9)),new Pasarela(new Posicion(8,9)),new Rocoso(new Posicion(9,9)),new Tierra(new Posicion(10,9)),new Tierra(new Posicion(11,9)),new Tierra(new Posicion(12,9)),new Tierra(new Posicion(13,9)),new Tierra(new Posicion(14,9))},
        //     {new Tierra(new Posicion(0,10)),new Tierra(new Posicion(1,10)),new Tierra(new Posicion(2,10)),new Tierra(new Posicion(3,10)),new Tierra(new Posicion(4,10)),new Tierra(new Posicion(5,10)),new Tierra(new Posicion(6,10)),new Tierra(new Posicion(7,10)),new Pasarela(new Posicion(8,10)),new Pasarela(new Posicion(9,10)),new Pasarela(new Posicion(10,10)),new Pasarela(new Posicion(11,10)),new Pasarela(new Posicion(12,10)),new Pasarela(new Posicion(13,10)),new Pasarela(new Posicion(14,10))},
        //     {new Rocoso(new Posicion(0,11)),new Rocoso(new Posicion(1,11)),new Rocoso(new Posicion(2,11)),new Rocoso(new Posicion(3,11)),new Rocoso(new Posicion(4,11)),new Tierra(new Posicion(5,11)),new Tierra(new Posicion(6,11)),new Tierra(new Posicion(7,11)),new Tierra(new Posicion(8,11)),new Tierra(new Posicion(9,11)),new Tierra(new Posicion(10,11)),new Tierra(new Posicion(11,11)),new Tierra(new Posicion(12,11)),new Tierra(new Posicion(13,11)),new Tierra(new Posicion(14,11))},
        //     {new Rocoso(new Posicion(0,12)),new Rocoso(new Posicion(1,12)),new Rocoso(new Posicion(2,12)),new Rocoso(new Posicion(3,12)),new Rocoso(new Posicion(4,12)),new Tierra(new Posicion(5,12)),new Tierra(new Posicion(6,12)),new Tierra(new Posicion(7,12)),new Tierra(new Posicion(8,12)),new Tierra(new Posicion(9,12)),new Tierra(new Posicion(10,12)),new Tierra(new Posicion(11,12)),new Tierra(new Posicion(12,12)),new Tierra(new Posicion(13,12)),new Tierra(new Posicion(14,12))},
        //     {new Rocoso(new Posicion(0,13)),new Rocoso(new Posicion(1,13)),new Rocoso(new Posicion(2,13)),new Rocoso(new Posicion(3,13)),new Rocoso(new Posicion(4,13)),new Tierra(new Posicion(5,13)),new Tierra(new Posicion(6,13)),new Tierra(new Posicion(7,13)),new Tierra(new Posicion(8,13)),new Tierra(new Posicion(9,13)),new Tierra(new Posicion(10,13)),new Tierra(new Posicion(11,13)),new Tierra(new Posicion(12,13)),new Tierra(new Posicion(13,13)),new Tierra(new Posicion(14,13))},
        //     {new Rocoso(new Posicion(0,14)),new Rocoso(new Posicion(1,14)),new Rocoso(new Posicion(2,14)),new Rocoso(new Posicion(3,14)),new Rocoso(new Posicion(4,14)),new Tierra(new Posicion(5,14)),new Tierra(new Posicion(6,14)),new Tierra(new Posicion(7,14)),new Tierra(new Posicion(8,14)),new Tierra(new Posicion(9,14)),new Tierra(new Posicion(10,14)),new Tierra(new Posicion(11,14)),new Tierra(new Posicion(12,14)),new Tierra(new Posicion(13,14)),new Tierra(new Posicion(14,14))}
        //     };

        // for (Parcela[] fila : filasMapa) {    
        //     mapaSimulado.add(new LinkedList<>(Arrays.asList(fila)));
        // }
        // mapaEsperado = new Mapa(mapaSimulado);

        // mapa = CreadorMapa.crearMapa(null);

        // for (int i = 0; i < filasMapa.length; i++) {
        //     for (int j = 0; j < filasMapa.length; j++) {
        //         Enemigo a = mapaEsperado.get(i).get(j);
        //         Enemigo b = mapa.get(i).get(j);
        //         assertEquals(a.getClass(), b.getClass());
        //     }
        // }
    }

    @Test
    public void test17ElJuegoSeCreaAcordeALosJson()
    {
    }

    @Test
    public void test18ElJugadorGanaLaPartidaSimulada()
    {

    }

    @Test
    public void test19ElJugadorPierdeLaPartidaSimulada()
    {

    }

    @Test
    public void test20VerificacionConsoleLog()
    {

    }
}
