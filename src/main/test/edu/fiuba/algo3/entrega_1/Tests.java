package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Enemigos.*;
import edu.fiuba.algo3.modelo.Errores.CreditosInsuficientesError;
import edu.fiuba.algo3.modelo.Errores.GanarPartidaError;
import edu.fiuba.algo3.modelo.Errores.PerderPartidaError;
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;
import edu.fiuba.algo3.modelo.Parcelas.*;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.*;
import edu.fiuba.algo3.modelo.Parcelas.Tierra.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class Tests {

    private final int PRUEBA_SIN_ENEMIGOS = -1;

    @Test
    public void test01JugadorSeCreaCon100CreditosY20Vida() {
        Jugador jugador = new Jugador();
        Jugador jugadorEsperado = new Jugador(20, 100);
        assertEquals(jugadorEsperado, jugador);
    }

//Verificar que cada defensa tarde en construirse lo que dice que tarda y que recién están
//“operativas” cuando ya se terminaron de construir.
    @Test
    public void test02TorrePlateadaTardeEnCrearseLoEsperado() {
        DefensaPlateada defensaPlateada = new DefensaPlateada();
        defensaPlateada.avanzarTurno();
        assertFalse(defensaPlateada.chequearProgreso());
        defensaPlateada.avanzarTurno();
        assertTrue(defensaPlateada.chequearProgreso());


    }
    @Test
    public void test02TorreBlancaTardeEnCrearseLoEsperado() {
        DefensaBlanca defensaBlanca = new DefensaBlanca();
        defensaBlanca.avanzarTurno();
        assertTrue(defensaBlanca.chequearProgreso());

    }

    @Test
    public void test03ElJugadorCuentaConLosCreditosParaConstruirLaTorre() {
        Jugador jugador = new Jugador();
        Defensa defensa = new DefensaPlateada(jugador);
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
        Tierra tierra = new Tierra(0,0);
        Defensa defensa = new DefensaBlanca();

        assertDoesNotThrow(() -> tierra.construir(defensa));
    }

    @Test
    public void test04NoSePuedeConstruirSobreTierraConUnaDefensa()
    {
        Tierra tierra = new Tierra(0,0);
        Defensa defensa = new DefensaBlanca();

        assertDoesNotThrow(() -> tierra.construir(defensa));
        assertThrows(TerrenoDeConstruccionInvalidoError.class,() -> tierra.construir(defensa));
    }

    @Test
    public void test04NoSePuedeConstruirSobreAlgunaPasarela()
    {
        Pasarela pasarela = new Pasarela(0,0);
        Defensa defensa = new DefensaBlanca();

        assertThrows(TerrenoDeConstruccionInvalidoError.class, () -> pasarela.construir(defensa));
    }

    @Test
    public void test04NoSePuedeConstruirSobreLaMeta()
    {
        Pasarela pasarela = new Meta(0,0);
        Defensa defensa = new DefensaBlanca();

        assertThrows(TerrenoDeConstruccionInvalidoError.class, () -> pasarela.construir(defensa));
    }

    @Test
    public void test04NoSePuedeConstruirSobreLaLargada()
    {
        Pasarela pasarela = new Largada(0,0);
        Defensa defensa = new DefensaBlanca();

        assertThrows(TerrenoDeConstruccionInvalidoError.class, () -> pasarela.construir(defensa));
    }

    @Test
    public void test04NoSePuedeConstruirSobreRocoso()
    {
        Rocoso rocoso = new Rocoso(0,0);
        Defensa defensa = new DefensaBlanca();

        assertThrows(TerrenoDeConstruccionInvalidoError.class, () -> rocoso.construir(defensa));
    }

    @Test
    public void test05LasDefensasAtacanEnElRangoEsperado()
    {
        LinkedList<Enemigo> enemigos = new LinkedList<Enemigo>();
        Enemigo enemigo1 = new Hormiga(null);
        enemigo1.setearPosicion(new Posicion(0,0));
        enemigos.add(enemigo1);
        Enemigo enemigoEsperado = new Hormiga(null);
        enemigoEsperado.setearPosicion(new Posicion(1, 1));
        enemigos.add(enemigoEsperado);

        Defensa defensa = new DefensaBlanca();
        defensa.setearPosicion(new Posicion(2,2));

        Enemigo enemigoObtenido = defensa.hallarEnemigoMasCercano(enemigos);

        assertEquals(enemigoEsperado, enemigoObtenido);
    }

    @Test
    public void test05LasDefensasNoAtacanFueraDelRango()
    {
        LinkedList<Enemigo> enemigos = new LinkedList<Enemigo>();
        Enemigo enemigo1 = new Hormiga(null);
        enemigo1.setearPosicion(new Posicion(10,10));
        enemigos.add(enemigo1);
        Enemigo enemigo2 = new Hormiga(null);
        enemigo2.setearPosicion(new Posicion(11, 11));
        enemigos.add(enemigo2);

        Defensa defensa = new DefensaBlanca();
        defensa.setearPosicion(new Posicion(2,2));

        Enemigo enemigoEsperado = new NoEnemigo();

        Enemigo enemigoObtenido = defensa.hallarEnemigoMasCercano(enemigos);

        assertEquals(enemigoEsperado, enemigoObtenido);
    }

    @Test
    public void test06LasUnidadesEnemigasSonDaniadasAcordeAlAtaqueRecibido()
    {
        Jugador jugador = new Jugador();
        Arania arania = new Arania(jugador);
        int danioDelAtaque = 1;
        int energiaEsperadaArania = 1;

        Hormiga hormiga = new Hormiga(jugador);
        int energiaEsperadaHormiga = 0;

        arania.recibirDanio(danioDelAtaque);
        hormiga.recibirDanio(danioDelAtaque);

        assertEquals(arania.obtenerEnergia(), energiaEsperadaArania);
        assertEquals(hormiga.obtenerEnergia(), energiaEsperadaHormiga);
    }

    @Test
    public void test07LasUnidadesEnemigasSoloSeMuevenSobreLaParcelaAutorizada()
    {
        Pasarela pasarela = new Pasarela(0, 0);
        Enemigo hormiga = new Hormiga(null);
        Rocoso rocoso = new Rocoso(1, 0);
        Tierra tierra = new Tierra(2, 0);

        assertDoesNotThrow(() -> pasarela.agregarEnemigo(hormiga));
        assertThrows(Exception.class, () -> rocoso.agregarEnemigo(hormiga));
        assertThrows(Exception.class, () -> tierra.agregarEnemigo(hormiga));
    }

    @Test
    public void test08ElJugadorCobraAlDestruirUnEnemigoHormiga()
    {
        Jugador jugador = new Jugador();
        Jugador jugadorEsperado = new Jugador(20,101);
        Enemigo hormiga = new Hormiga(jugador);
        hormiga.recibirDanio(1);
        assertEquals(jugadorEsperado, jugador);
    }

    @Test
    public void test08ElJugadorCobraAlDestruirUnEnemigoArania()
    {
        Jugador jugador = new Jugador();
        int creditosEsperadosMin = 101;
        int creditosEsperadosMax = 110;

        Enemigo arania = new Arania(jugador);
        arania.recibirDanio(2);
        assertTrue(jugador.obtenerCreditos() >= creditosEsperadosMin);
        assertTrue(jugador.obtenerCreditos() <= creditosEsperadosMax);

    }


    @Test
    public void test09AlPasarUnTurnoLasUnidadesEnemigasSeMuevenSegunSusCapacidades()
    {
        Pasarela pasarelaInicial = new Pasarela(0, 0);
        Pasarela pasarelaIntermedia = new Pasarela(1, 1);
        Pasarela pasarelaFinal = new Pasarela(2, 2);
        List<Pasarela> pasarelas = new LinkedList<>(Arrays.asList(pasarelaInicial, pasarelaIntermedia, pasarelaFinal));

        Camino camino = new Camino(pasarelas);

        Enemigo hormiga = new Hormiga(null, camino);
        Enemigo arania = new Arania(null, camino);

        pasarelaInicial.agregarEnemigo(hormiga);
        pasarelaInicial.agregarEnemigo(arania);
        
        pasarelaInicial.avanzarTurno();

        assertTrue(pasarelaIntermedia.verificarSiEstaElEnemigo(hormiga));
        assertTrue(pasarelaFinal.verificarSiEstaElEnemigo(arania));
    }

    @Test
    public void test10AlEliminarATodosLosEnemigosElJugadorGanaLaPartida() {
        Turno turno = new Turno(new LinkedList<>(Arrays.asList(new Pasarela(0,0))), new Jugador());
        assertThrows(GanarPartidaError.class, () -> turno.avanzarTurno(PRUEBA_SIN_ENEMIGOS));
    }

    @Test
    public void test11AlNoQuedarUnidadesEnemigasSinHaberlasEliminadoTodasElJugadorConVidaPositivaEsteGanaLaPartida()
    {
        Jugador jugador = new Jugador();
        Turno turno = new Turno(new LinkedList<>(Arrays.asList(new Pasarela(0,0))), jugador);
        Meta meta = new Meta(0,0);
        meta.agregarEnemigo(new Arania(jugador));
        assertAll(() -> meta.avanzarTurno()); //Meta realmente no requiere de camino
        assertThrows(GanarPartidaError.class, () -> turno.avanzarTurno(PRUEBA_SIN_ENEMIGOS));
    }

    @Test
    public void test12SiElJugadorPierdeTodaLaVidaPierdeElJuego(){
        Jugador jugador = new Jugador();
        Meta meta = new Meta(0,0);
        for (int i = 0; i < 9; i++){
            meta.agregarEnemigo(new Arania(jugador));
        }
        assertAll(() -> meta.avanzarTurno());
        meta.agregarEnemigo(new Arania(jugador));
        assertThrows(PerderPartidaError.class, () -> meta.avanzarTurno());
    }
}