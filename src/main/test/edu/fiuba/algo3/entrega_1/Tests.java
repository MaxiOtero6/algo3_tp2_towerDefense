package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Enemigos.*;
import edu.fiuba.algo3.modelo.Parcelas.*;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestMethodOrder.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingDeque;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class Tests {
    @Test
    public void test01JugadorSeCreaCon100CreditosY20Vida() {
        Jugador jugador = Jugador.obtenerJugador();
        assertEquals(100, jugador.obtenerCreditos());
        assertEquals(20, jugador.obtenerVida());
    }

    //Verificar que cada defensa tarde en construirse lo que dice que tarda y que recién están
//“operativas” cuando ya se terminaron de construir.
    @Test
    public void test02TorrePlateadaTardeEnCrearseLoEsperado() {
        DefensaPlateada defensaPlateada = new DefensaPlateada(null);
        defensaPlateada.avanzarTurno();
        assertFalse(defensaPlateada.chequearProgreso());
        defensaPlateada.avanzarTurno();
        assertTrue(defensaPlateada.chequearProgreso());


    }
    @Test
    public void test02TorreBlancaTardeEnCrearseLoEsperado() {
        DefensaBlanca defensaBlanca = new DefensaBlanca(null);
        defensaBlanca.avanzarTurno();
        assertTrue(defensaBlanca.chequearProgreso());
    }

    @Test
    public void test03ElJugadorCuentaConLosCreditosParaConstruirLaTorre() {
        Partida partida = new Partida();
        String respuesta1 = partida.construir("Plateada");
        assertEquals(respuesta1, "Defensa construida exitosamnte");
        partida.construir("Plateada");
        partida.construir("Plateada");
        partida.construir("Plateada");
        partida.construir("Plateada");
        String respuesta2 = partida.construir("Plateada");
        assertEquals(respuesta2,"No se pudo construir la defensa");

    }

    @Test
    public void test04SePuedeConstruirSobreTierra()
    {

    }
    
    @Test
    public void test04NoSePuedeConstruirSobreTierraConUnaDefensa()
    {
        //assertThrows()
    }

    @Test
    public void test04NoSePuedeConstruirSobreParcela()
    {
        //assertThrows()
    }
    
    @Test
    public void test04NoSePuedeConstruirSobreRocoso()
    {
        //assertThrows()
    }

    @Test
    public void test05LasDefensasAtacanEnElRangoEsperado()
    {
        LinkedList<Enemigo> enemigos = new LinkedList<Enemigo>();
        enemigos.add(new Hormiga(new Posicion(0,0)));
        Enemigo enemigoEsperado = new Hormiga(new Posicion(1, 1));
        enemigos.add(enemigoEsperado);

        Defensa defensa = new DefensaBlanca(new Posicion(2,2));

        Enemigo enemigoObtenido = defensa.hallarEnemigoMasCercano(enemigos);

        assertEquals(enemigoEsperado, enemigoObtenido);
    }

    @Test
    public void test05LasDefensasNoAtacanFueraDelRango()
    {
        LinkedList<Enemigo> enemigos = new LinkedList<Enemigo>();
        enemigos.add(new Hormiga(new Posicion(10,10)));
        enemigos.add(new Hormiga(new Posicion(11, 11)));

        Defensa defensa = new DefensaBlanca(new Posicion(2,2));
        Enemigo enemigoEsperado = null;

        Enemigo enemigoObtenido = defensa.hallarEnemigoMasCercano(enemigos);

        assertEquals(enemigoEsperado, enemigoObtenido);
    }

    @Test
    public void test06LasUnidadesEnemigasSonDaniadasAcordeAlAtaqueRecibido()
    {
        Arania arania = new Arania(null);
        int danioDelAtaque = 1;
        int energiaEsperadaArania = 1;

        Hormiga hormiga = new Hormiga(null);
        int energiaEsperadaHormiga = 0;

        arania.recibirDanio(danioDelAtaque);
        hormiga.recibirDanio(danioDelAtaque);

        assertEquals(arania.obtenerEnergia(), energiaEsperadaArania);
        assertEquals(hormiga.obtenerEnergia(), energiaEsperadaHormiga);
    }

    @Test
    public void test07LasUnidadesEnemigasSoloSeMuevenSobreLaParcelaAutorizada()
    {

    }

    @Test
    public void test08ElJugadorCobraAlDestruirUnEnemigo()
    {
        
    }

    @Test
    public void test09AlPasarUnTurnoLasUnidadesEnemigasSeMuevenSegunSusCapacidades()
    {

    }

    @Test
    public void test10AlEliminarATodosLosEnemigosElJugadorGanaLaPartida()
    {

    }

    @Test
    public void test11AlNoQuedarUnidadesEnemigasSinHaberlasEliminadoTodasElJugadorConVidaPositivaEsteGanaLaPartida()
    {

    }

    @Test
    public void test12SiElJugadorPierdeTodaLaVidaPierdeElJuego()
    {

    }
}