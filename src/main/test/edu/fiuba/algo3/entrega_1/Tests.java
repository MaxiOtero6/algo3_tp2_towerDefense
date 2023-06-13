package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Objetivos.ObjetivoTorre;
import edu.fiuba.algo3.modelo.Defensas.Torres.*;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import edu.fiuba.algo3.modelo.Enemigos.*;
import edu.fiuba.algo3.modelo.Errores.CreditosInsuficientesError;
import edu.fiuba.algo3.modelo.Errores.DefensaEnTerrenoErroneoError;
import edu.fiuba.algo3.modelo.Errores.GanarPartidaError;
import edu.fiuba.algo3.modelo.Errores.PerderPartidaError;
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;
import edu.fiuba.algo3.modelo.Parcelas.*;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.*;
import edu.fiuba.algo3.modelo.Parcelas.Tierra.*;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class Tests {

    private final int PRUEBA_SIN_ENEMIGOS = -1;

    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01JugadorSeCreaCon100CreditosY20Vida() {
        Jugador jugador = new Jugador();
        Jugador jugadorEsperado = new Jugador(20, 100);
        assertEquals(jugadorEsperado, jugador);
    }

//Verificar que cada torre tarde en construirse lo que dice que tarda y que recién están
//“operativas” cuando ya se terminaron de construir.
    @Test
    public void test02TorrePlateadaTardeEnCrearseLoEsperado() {
        TorrePlateada torrePlateada = new TorrePlateada();
        torrePlateada.avanzarTurno();
        assertFalse(torrePlateada.chequearProgreso());
        torrePlateada.avanzarTurno();
        assertTrue(torrePlateada.chequearProgreso());


    }
    @Test
    public void test02TorreBlancaTardeEnCrearseLoEsperado() {
        TorreBlanca torreBlanca = new TorreBlanca();
        torreBlanca.avanzarTurno();
        assertTrue(torreBlanca.chequearProgreso());

    }

    @Test
    public void test03ElJugadorCuentaConLosCreditosParaConstruirLaTorre() {
        Jugador jugador = new Jugador();
        Torre torre = new TorrePlateada();
        torre.gastarCreditos(jugador);
        torre.gastarCreditos(jugador);
        torre.gastarCreditos(jugador);
        torre.gastarCreditos(jugador);
        torre.gastarCreditos(jugador);
        assertThrows(CreditosInsuficientesError.class, () -> torre.gastarCreditos(jugador));
        jugador.agregarCreditos(100);
    }

    @Test
    public void test04SePuedeConstruirSobreTierra()
    {
        Tierra tierra = new Tierra(0,0);
        Torre torre = new TorreBlanca();

        assertDoesNotThrow(() -> tierra.construir(torre));
    }

    @Test
    public void test04NoSePuedeConstruirSobreTierraConUnaTorre()
    {
        Tierra tierra = new Tierra(0,0);
        Torre torre = new TorreBlanca();

        assertDoesNotThrow(() -> tierra.construir(torre));
        assertThrows(TerrenoDeConstruccionInvalidoError.class,() -> tierra.construir(torre));
    }

    @Test
    public void test04NoSePuedeConstruirUnaTrampaSobreTierra()
    {
        Tierra tierra = new Tierra(0,0);
        TrampaArenosa trampa = new TrampaArenosa();

        assertThrows(DefensaEnTerrenoErroneoError.class,() -> tierra.construir(trampa));
    }

    @Test
    public void test04NoSePuedeConstruirUnaTorreSobreAlgunaPasarela()
    {
        Pasarela pasarela = new Pasarela(0,0);
        Torre torre = new TorreBlanca();

        assertThrows(DefensaEnTerrenoErroneoError.class, () -> pasarela.construir(torre));
    }

    @Test
    public void test04NoSePuedeConstruirSobreUnaPasarelaConUnaTrampa()
    {
        Pasarela pasarela = new Pasarela(0,0);
        TrampaArenosa trampa = new TrampaArenosa();

        assertDoesNotThrow(() -> pasarela.construir(trampa));
        assertThrows(TerrenoDeConstruccionInvalidoError.class,() -> pasarela.construir(trampa));
    }

    @Test
    public void test04SePuedeConstruirUnaTrampaSobreAlgunaPasarela()
    {
        Pasarela pasarela = new Pasarela(0,0);
        Defensa trampa = new TrampaArenosa();

        assertDoesNotThrow(() -> pasarela.construir(trampa));
    }

    @Test
    public void test04NoSePuedeConstruirUnaTorreSobreLaMeta()
    {
        Meta pasarela = new Meta(0,0);
        Torre torre = new TorreBlanca();

        assertThrows(TerrenoDeConstruccionInvalidoError.class, () -> pasarela.construir(torre));
    }

    @Test
    public void test04NoSePuedeConstruirUnaTrampaSobreLaMeta()
    {
        Meta pasarela = new Meta(0,0);
        TrampaArenosa trampa = new TrampaArenosa();

        assertThrows(TerrenoDeConstruccionInvalidoError.class, () -> pasarela.construir(trampa));
    }

    @Test
    public void test04NoSePuedeConstruirUnaTorreSobreLaLargada()
    {
        Largada pasarela = new Largada(0,0);
        Torre torre = new TorreBlanca();

        assertThrows(TerrenoDeConstruccionInvalidoError.class, () -> pasarela.construir(torre));
    }

    @Test
    public void test04NoSePuedeConstruirUnaTrampaSobreLaLargada()
    {
        Largada pasarela = new Largada(0,0);
        TrampaArenosa trampa = new TrampaArenosa();

        assertThrows(TerrenoDeConstruccionInvalidoError.class, () -> pasarela.construir(trampa));
    }

    @Test
    public void test04NoSePuedeConstruirUnaTorreSobreRocoso()
    {
        Rocoso rocoso = new Rocoso(0,0);
        Torre torre = new TorreBlanca();

        assertThrows(TerrenoDeConstruccionInvalidoError.class, () -> rocoso.construir(torre));
    }

    @Test
    public void test04NoSePuedeConstruirUnaTrampaSobreRocoso()
    {
        Rocoso rocoso = new Rocoso(0,0);
        TrampaArenosa trampa = new TrampaArenosa();

        assertThrows(TerrenoDeConstruccionInvalidoError.class, () -> rocoso.construir(trampa));
    }

    @Test
    public void test05LasTorresAtacanEnElRangoEsperado()
    {
        LinkedList<Enemigo> enemigos = new LinkedList<Enemigo>();
        Enemigo enemigo1 = new Hormiga(null,null);
        enemigo1.setearPosicion(new Posicion(0,0));
        enemigos.add(enemigo1);
        Enemigo enemigoEsperado = new Hormiga(null,null);
        enemigoEsperado.setearPosicion(new Posicion(1, 1));
        enemigos.add(enemigoEsperado);
        Posicion posicionTorre = new Posicion(2, 2);
        int rangoTorre = 3;

        ObjetivoTorre objetivo = new ObjetivoTorre();
        Enemigo enemigoObtenido = objetivo.hallarObjetivo(posicionTorre, enemigos, rangoTorre);

        assertEquals(enemigoEsperado, enemigoObtenido);
    }

    @Test
    public void test05LasTorresNoAtacanFueraDelRango()
    {
        LinkedList<Enemigo> enemigos = new LinkedList<Enemigo>();
        Enemigo enemigo1 = new Hormiga(null,null);
        enemigo1.setearPosicion(new Posicion(0,0));
        enemigos.add(enemigo1);
        Enemigo enemigoEsperado = new NoEnemigo();
        enemigos.add(enemigoEsperado);
        Posicion posicionTorre = new Posicion(11, 11);
        int rangoTorre = 3;

        ObjetivoTorre objetivo = new ObjetivoTorre();
        Enemigo enemigoObtenido = objetivo.hallarObjetivo(posicionTorre, enemigos, rangoTorre);

        assertEquals(enemigoEsperado, enemigoObtenido);
    }

    @Test
    public void test06LasUnidadesEnemigasSonDaniadasAcordeAlAtaqueRecibido()
    {
        Jugador jugador = new Jugador();
        Jugador jugadorEsperado = new Jugador(20, 101);
        Arania arania = new Arania(jugador,null);
        int danioDelAtaque = 1;

        Hormiga hormiga = new Hormiga(jugador,null);

        arania.recibirDanio(danioDelAtaque, "Prueba");
        hormiga.recibirDanio(danioDelAtaque, "Prueba");

        //La hormiga debe haber muerto por lo cual le suma al jugador 1 creditos, mientras que la arania no
        assertEquals(jugadorEsperado, jugador);
    }

    @Test
    public void test07LasUnidadesEnemigasSoloSeMuevenSobreLaParcelaAutorizada()
    {
        Rocoso rocoso = new Rocoso(0, 0);
        Tierra tierra = new Tierra(1,1);
        Pasarela pasarela = new Pasarela(2,2);
        Largada largada = new Largada(3,3);
        Meta meta = new Meta(4,4);

        assertDoesNotThrow(() -> rocoso.agregarEnemigo(new NoEnemigo()));
        assertDoesNotThrow(() -> tierra.agregarEnemigo(new NoEnemigo()));
        assertDoesNotThrow(() -> pasarela.agregarEnemigo(new NoEnemigo()));
        assertDoesNotThrow(() -> largada.agregarEnemigo(new NoEnemigo()));
        assertDoesNotThrow(() -> meta.agregarEnemigo(new NoEnemigo()));

    }

    @Test
    public void test08ElJugadorCobraAlDestruirUnEnemigoHormiga()
    {
        Jugador jugador = new Jugador();
        Jugador jugadorEsperado = new Jugador(20,101);
        Enemigo hormiga = new Hormiga(jugador,null);
        hormiga.recibirDanio(1, "Prueba");
        assertEquals(jugadorEsperado, jugador);
    }

    @Test
    public void test08ElJugadorCobraAlDestruirUnEnemigoArania()
    {
        Jugador jugador = new Jugador();
        int creditosEsperadosMin = 100;
        int creditosEsperadosMax = 110;

        Enemigo arania = new Arania(jugador,null);
        arania.recibirDanio(2, "Prueba");
        assertTrue(jugador.obtenerCreditos() >= creditosEsperadosMin);
        assertTrue(jugador.obtenerCreditos() <= creditosEsperadosMax);

    }


    @Test
    public void test09AlPasarUnTurnoLasUnidadesEnemigasSeMuevenSegunSusCapacidades()
    {
        Pasarela pasarelaInicial = new Pasarela(0, 0);
        Pasarela pasarelaIntermedia = new Pasarela(1, 1);
        Pasarela pasarelaFinal = new Pasarela(2, 2);
        List<Parcela> pasarelas = new LinkedList<>(Arrays.asList(pasarelaInicial, pasarelaIntermedia, pasarelaFinal));

        Camino camino = new Camino(pasarelas);

        Enemigo hormiga = new Hormiga(null,camino);
        Enemigo arania = new Arania(null,camino);

        pasarelaInicial.agregarEnemigo(hormiga);
        pasarelaInicial.agregarEnemigo(arania);
        
        hormiga.mover();
        arania.mover();

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
        assertThrows(GanarPartidaError.class, () -> turno.avanzarTurno(PRUEBA_SIN_ENEMIGOS));
    }

    @Test
    public void test12SiElJugadorPierdeTodaLaVidaPierdeElJuego(){
        Jugador jugador = new Jugador();
        assertThrows(PerderPartidaError.class, () -> jugador.recibirDanio(100, "Test 12"));
    }
}