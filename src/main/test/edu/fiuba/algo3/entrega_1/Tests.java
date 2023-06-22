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
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.spy;

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

    @Test
    public void test02TorrePlateadaTardeEnCrearseLoEsperado() {
        TorrePlateada torrePlateada = new TorrePlateada();
        TorreBlanca spy = spy(torrePlateada);
        LinkedList<Enemigo> enemigos = new LinkedList<>();
        Enemigo enemigo = new Lechuza(null,null); 
        enemigo.setearPosicion(new Posicion(0, 0));
        enemigos.add(enemigo);
        spy.setEnemigos(enemigos);
        spy.setearPosicion(new Posicion(1,1));

        spy.avanzarTurno();
        verify(spy, never()).atacar();
        spy.avanzarTurno();
        verify(spy, never()).atacar();
        spy.avanzarTurno();
        verify(spy, never()).atacar();
        spy.avanzarTurno();
        verify(spy, times(1)).atacar();
    }
    @Test
    public void test02TorreBlancaTardeEnCrearseLoEsperado() {
        TorreBlanca torreBlanca = new TorreBlanca();
        TorreBlanca spy = spy(torreBlanca);
        LinkedList<Enemigo> enemigos = new LinkedList<>();
        Enemigo enemigo = new Lechuza(null,null); 
        enemigo.setearPosicion(new Posicion(0, 0));
        enemigos.add(enemigo);
        spy.setEnemigos(enemigos);
        spy.setearPosicion(new Posicion(1,1));

        spy.avanzarTurno();
        verify(spy, never()).atacar();
        spy.avanzarTurno();
        verify(spy, never()).atacar();
        spy.avanzarTurno();
        verify(spy, times(1)).atacar();

    }

    @Test
    public void test03ElJugadorCuentaConLosCreditosParaConstruirLaTorre() {
        Jugador jugador = new Jugador();
        TorreBlanca torre = new TorrePlateada();
        torre.gastarCreditos(jugador);
        torre.gastarCreditos(jugador);
        torre.gastarCreditos(jugador);
        torre.gastarCreditos(jugador);
        torre.gastarCreditos(jugador);
        assertThrows(CreditosInsuficientesError.class, () -> torre.gastarCreditos(jugador));
    }

    @Test
    public void test04SePuedeConstruirSobreTierra()
    {
        Tierra tierra = new Tierra(0,0);
        TorreBlanca torre = new TorreBlanca();

        assertDoesNotThrow(() -> tierra.construir(torre));
    }

    @Test
    public void test04NoSePuedeConstruirSobreTierraConUnaTorre()
    {
        Tierra tierra = new Tierra(0,0);
        TorreBlanca torre = new TorreBlanca();

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
    public void test04NoSePuedeConstruirUnaTorreBlancaSobreAlgunaPasarela()
    {
        Pasarela pasarela = new Pasarela(0,0);
        TorreBlanca torre = new TorreBlanca();

        assertThrows(DefensaEnTerrenoErroneoError.class, () -> pasarela.construir(torre));
    }

    @Test
    public void test04NoSePuedeConstruirUnaTorrePlateadaSobreAlgunaPasarela()
    {
        Pasarela pasarela = new Pasarela(0,0);
        TorreBlanca torre = new TorrePlateada();

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
        TorreBlanca torre = new TorreBlanca();

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
        TorreBlanca torre = new TorreBlanca();

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
        TorreBlanca torre = new TorreBlanca();

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
        Enemigo enemigoObtenido = objetivo.hallarObjetivo(posicionTorre, enemigos, rangoTorre, new TorreBlanca());

        assertEquals(enemigoEsperado, enemigoObtenido);
        Hormiga.resetContador();
    }

    @Test
    public void test05LasTorresNoAtacanFueraDelRango()
    {
        LinkedList<Enemigo> enemigos = new LinkedList<Enemigo>();
        Enemigo enemigo1 = new Hormiga(null,null);
        enemigo1.setearPosicion(new Posicion(0,0));
        enemigos.add(enemigo1);
        Enemigo enemigoEsperado = new NoEnemigo();
        Posicion posicionTorre = new Posicion(11, 11);
        int rangoTorre = 3;

        ObjetivoTorre objetivo = new ObjetivoTorre();
        Enemigo enemigoObtenido = objetivo.hallarObjetivo(posicionTorre, enemigos, rangoTorre, new TorreBlanca());

        assertEquals(enemigoEsperado, enemigoObtenido);
        Hormiga.resetContador();
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
        Hormiga.resetContador();
    }

    @Test
    public void test07LasUnidadesEnemigasSoloSeMuevenSobreLaParcelaAutorizada()
    {
        Parcela parcela1 = new Largada(0,0);
        Parcela parcela2 = new Pasarela(1, 1);
        Parcela parcela3 = new Pasarela(2,2);
        Parcela parcela4 = new Pasarela(3, 3);

        Parcela spy1 = spy(parcela1);
        Parcela spy2 = spy(parcela2);
        Parcela spy3 = spy(parcela3);
        Parcela spy4 = spy(parcela4);

        LinkedList<Parcela> parcelas = new LinkedList<>();
        parcelas.add(spy1); parcelas.add(spy2); parcelas.add(spy4);
        Camino camino = new Camino(parcelas);

        LinkedList<Enemigo> enemigos = new LinkedList<>(); 
        Enemigo enemigo = new Hormiga(null, camino);
        enemigos.add(enemigo);

        camino.aparecerEnemigos(enemigos);
        verify(spy1, times(1)).agregarEnemigo(enemigo);

        enemigo.mover();
        verify(spy2, times(1)).agregarEnemigo(enemigo);

        enemigo.mover();
        verify(spy4, times(1)).agregarEnemigo(enemigo);
        verify(spy3, never()).agregarEnemigo(enemigo);
    }

    @Test
    public void test08ElJugadorCobraAlDestruirUnEnemigoHormiga()
    {
        Jugador jugador = new Jugador();
        Jugador jugadorEsperado = new Jugador(20,101);
        Enemigo hormiga = new Hormiga(jugador,null);
        hormiga.recibirDanio(1, "Prueba");
        assertEquals(jugadorEsperado, jugador);
        Hormiga.resetContador();
    }

    @Test
    public void test08ElJugadorCobraAlDestruirUnEnemigoArania()
    {
        Jugador jugador = mock(Jugador.class);

        Enemigo arania = new Arania(jugador,null);
        arania.recibirDanio(2, "Prueba");
        verify(jugador, times(1)).agregarCreditos(anyInt());
    }


    @Test
    public void test09AlPasarUnTurnoLasUnidadesEnemigasSeMuevenSegunSusCapacidades()
    {
        Pasarela pasarelaInicial = new Pasarela(0, 0);
        Pasarela pasarelaIntermedia = new Pasarela(1, 1);
        Pasarela spy1 = spy(pasarelaIntermedia);
        Pasarela pasarelaFinal = new Pasarela(2, 2);
        Pasarela spy2 = spy(pasarelaFinal);
        List<Parcela> pasarelas = new LinkedList<>(Arrays.asList(pasarelaInicial, spy1, spy2));

        Camino camino = new Camino(pasarelas);

        Enemigo hormiga = new Hormiga(null,camino);
        Enemigo arania = new Arania(null,camino);

        pasarelaInicial.agregarEnemigo(hormiga);
        pasarelaInicial.agregarEnemigo(arania);
        
        hormiga.mover();
        arania.mover();

        verify(spy1, times(1)).agregarEnemigo(hormiga);
        verify(spy2, times(1)).agregarEnemigo(arania);
    }

    @Test
    public void test10AlEliminarATodosLosEnemigosElJugadorGanaLaPartida() {
        Turno turno = new Turno(new LinkedList<>(Arrays.asList(new Largada(0,0))), new Jugador());
        assertThrows(GanarPartidaError.class, () -> turno.avanzarTurno(PRUEBA_SIN_ENEMIGOS));
    }

    @Test
    public void test11AlNoQuedarUnidadesEnemigasSinHaberlasEliminadoTodasElJugadorConVidaPositivaEsteGanaLaPartida()
    {
        Jugador jugador = new Jugador();
        Largada largada = new Largada(0,0);
        Meta meta = new Meta(1,1);
        Turno turno = new Turno(new LinkedList<>(Arrays.asList(largada, meta)), jugador);
        turno.avanzarTurno(0);
        assertThrows(GanarPartidaError.class, () -> turno.avanzarTurno(PRUEBA_SIN_ENEMIGOS));
    }

    @Test
    public void test12SiElJugadorPierdeTodaLaVidaPierdeElJuego(){
        Jugador jugador = new Jugador();
        assertThrows(PerderPartidaError.class, () -> jugador.recibirDanio(100, "Test 12"));
    }
}