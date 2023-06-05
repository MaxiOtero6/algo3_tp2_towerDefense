package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Enemigos.*;
import edu.fiuba.algo3.modelo.Errores.CreditosInsuficientesError;
import edu.fiuba.algo3.modelo.Errores.GanarPartidaError;
import edu.fiuba.algo3.modelo.Errores.PerderPartidaError;
import edu.fiuba.algo3.modelo.Parcelas.*;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.*;
import edu.fiuba.algo3.modelo.Parcelas.Tierra.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.TestMethodOrder.*;
import org.junit.jupiter.api.function.Executable;

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
        Jugador jugador = Jugador.obtenerJugador();
       Defensa defensa = new DefensaPlateada(new Posicion(0,0));
        defensa.gastarCreditos();
        defensa.gastarCreditos();
        defensa.gastarCreditos();
        defensa.gastarCreditos();
        defensa.gastarCreditos();
       assertThrows(CreditosInsuficientesError.class, defensa::gastarCreditos);
       jugador.agregarCreditos(100);
    }

    @Test
    public void test04SePuedeConstruirSobreTierra()
    {
        Tierra tierra = new Tierra(null);
        Defensa defensa = new DefensaBlanca(null);

        assertDoesNotThrow(() -> tierra.construir(defensa));
    }

    @Test
    public void test04NoSePuedeConstruirSobreTierraConUnaDefensa()
    {
        Tierra tierra = new Tierra(null);
        Defensa defensa = new DefensaBlanca(null);

        assertDoesNotThrow(() -> tierra.construir(defensa));
        assertThrows(Exception.class,() -> tierra.construir(defensa));
    }

    @Test
    public void test04NoSePuedeConstruirSobreAlgunaPasarela()
    {
        Pasarela pasarela = new Pasarela(null);
        Defensa defensa = new DefensaBlanca(null);

        assertThrows(Exception.class, () -> pasarela.construir(defensa));
    }

    @Test
    public void test04NoSePuedeConstruirSobreLaMeta()
    {
        Pasarela pasarela = new Meta(null);
        Defensa defensa = new DefensaBlanca(null);

        assertThrows(Exception.class, () -> pasarela.construir(defensa));
    }

    @Test
    public void test04NoSePuedeConstruirSobreLaLargada()
    {
        Pasarela pasarela = new Largada(null);
        Defensa defensa = new DefensaBlanca(null);

        assertThrows(Exception.class, () -> pasarela.construir(defensa));
    }

    @Test
    public void test04NoSePuedeConstruirSobreRocoso()
    {
        Rocoso rocoso = new Rocoso();
        Defensa defensa = new DefensaBlanca(null);

        assertThrows(Exception.class, () -> rocoso.construir(defensa));
    }

    @Test
    public void test05LasDefensasAtacanEnElRangoEsperado()
    {
        LinkedList<Enemigo> enemigos = new LinkedList<Enemigo>();
        Enemigo enemigo1 = new Hormiga();
        enemigo1.setearPosicion(new Posicion(0,0));
        enemigos.add(enemigo1);
        Enemigo enemigoEsperado = new Hormiga();
        enemigoEsperado.setearPosicion(new Posicion(1, 1));
        enemigos.add(enemigoEsperado);

        Defensa defensa = new DefensaBlanca(new Posicion(2,2));

        Enemigo enemigoObtenido = defensa.hallarEnemigoMasCercano(enemigos);

        assertEquals(enemigoEsperado, enemigoObtenido);
    }

    @Test
    public void test05LasDefensasNoAtacanFueraDelRango()
    {
        LinkedList<Enemigo> enemigos = new LinkedList<Enemigo>();
        Enemigo enemigo1 = new Hormiga();
        enemigo1.setearPosicion(new Posicion(10,10));
        enemigos.add(enemigo1);
        Enemigo enemigo2 = new Hormiga();
        enemigo2.setearPosicion(new Posicion(11, 11));
        enemigos.add(enemigo2);

        Defensa defensa = new DefensaBlanca(new Posicion(2,2));
        Enemigo enemigoEsperado = null;

        Enemigo enemigoObtenido = defensa.hallarEnemigoMasCercano(enemigos);

        assertEquals(enemigoEsperado, enemigoObtenido);
    }

    @Test
    public void test06LasUnidadesEnemigasSonDaniadasAcordeAlAtaqueRecibido()
    {
        Arania arania = new Arania();
        int danioDelAtaque = 1;
        int energiaEsperadaArania = 1;

        Hormiga hormiga = new Hormiga();
        int energiaEsperadaHormiga = 0;

        arania.recibirDanio(danioDelAtaque);
        hormiga.recibirDanio(danioDelAtaque);
        Jugador.obtenerJugador().eliminarCreditos(1);

        assertEquals(arania.obtenerEnergia(), energiaEsperadaArania);
        assertEquals(hormiga.obtenerEnergia(), energiaEsperadaHormiga);
    }

    @Test
    public void test07LasUnidadesEnemigasSoloSeMuevenSobreLaParcelaAutorizada()
    {
        Pasarela pasarela = new Pasarela(new Posicion(0, 0));
        Enemigo hormiga = new Hormiga();
        Rocoso rocoso = new Rocoso();
        Tierra tierra = new Tierra(null);

        assertDoesNotThrow(() -> pasarela.agregarEnemigo(hormiga));
        assertThrows(Exception.class, () -> rocoso.agregarEnemigo(hormiga));
        assertThrows(Exception.class, () -> tierra.agregarEnemigo(hormiga));
    }

    @Test
    public void test08ElJugadorCobraAlDestruirUnEnemigo0Hormiga()
    {
        Jugador jugador = Jugador.obtenerJugador();
        int creditosEsperados = 101;
        Enemigo hormiga = new Hormiga();
        Defensa defensa = new DefensaBlanca(null);
        defensa.atacar(hormiga);
        assertEquals(creditosEsperados, jugador.obtenerCreditos());
        Jugador.obtenerJugador().eliminarCreditos(1);

    }

    @Test
    public void test08ElJugadorCobraAlDestruirUnEnemigoArania()
    {
        Jugador jugador = Jugador.obtenerJugador();
        int creditosInicial = jugador.obtenerCreditos();
        int creditosEsperados = jugador.obtenerCreditos() + 10;

        Enemigo arania = new Arania();
        Defensa defensa = new DefensaPlateada(null);

        defensa.atacar(arania);
        boolean creditosValidos = ((jugador.obtenerCreditos() >= creditosInicial) && (jugador.obtenerCreditos() <= creditosEsperados));
        jugador.agregarCreditos(100);
        jugador.eliminarCreditos(creditosEsperados);
        assertTrue(creditosValidos);

    }


    @Test
    public void test09AlPasarUnTurnoLasUnidadesEnemigasSeMuevenSegunSusCapacidades()
    {
        Pasarela pasarelaInicial = new Pasarela(new Posicion(0, 0));
        Pasarela pasarelaIntermedia = new Pasarela(new Posicion(1, 1));
        Pasarela pasarelaFinal = new Pasarela(new Posicion(2, 2));

        Camino camino = Camino.obtenerCamino();
        camino.agregarPasarela(pasarelaInicial);
        camino.agregarPasarela(pasarelaIntermedia);
        camino.agregarPasarela(pasarelaFinal);

        Enemigo hormiga = new Hormiga();
        Enemigo arania = new Arania();


        try{pasarelaInicial.agregarEnemigo(hormiga);}
        catch (Exception e){}
        try{pasarelaInicial.agregarEnemigo(arania);}
        catch (Exception e){}
        pasarelaInicial.avanzarTurno();

        assertTrue(pasarelaIntermedia.verificarSiEstaElEnemigo(hormiga));
        assertTrue(pasarelaFinal.verificarSiEstaElEnemigo(arania));
    }

    @Test
    public void test10AlEliminarATodosLosEnemigosElJugadorGanaLaPartida() {
        Camino.obtenerCamino().borrarPasarelas();
        Jugador.obtenerJugador().restaurarVida();
        Turno turno = new Turno();
        assertThrows(GanarPartidaError.class, turno::avanzarTurno);
    }

    @Test
    public void test11AlNoQuedarUnidadesEnemigasSinHaberlasEliminadoTodasElJugadorConVidaPositivaEsteGanaLaPartida()
    {
        Camino.obtenerCamino().borrarPasarelas();
        Jugador.obtenerJugador().restaurarVida();
        Turno turno = new Turno();
        Meta meta = new Meta(new Posicion(0,0));
        meta.agregarEnemigo(new Arania());
        assertAll(meta::avanzarTurno);
        assertThrows(GanarPartidaError.class, turno::avanzarTurno);
    }

    @Test
    public void test12SiElJugadorPierdeTodaLaVidaPierdeElJuego(){
        Camino.obtenerCamino().borrarPasarelas();
        Jugador.obtenerJugador().restaurarVida();
        Meta meta = new Meta(new Posicion(0,0));
        for (int i = 0; i < 9; i++){
            meta.agregarEnemigo(new Arania());
        }
        assertAll(meta::avanzarTurno);
        assertThrows(PerderPartidaError.class, meta::avanzarTurno);
    }
}