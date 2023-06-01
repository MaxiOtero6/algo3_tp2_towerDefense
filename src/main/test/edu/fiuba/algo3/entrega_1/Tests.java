package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Enemigos.*;
import edu.fiuba.algo3.modelo.Parcelas.*;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


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
    public void test03ElJugadorCuentaConLosCreditosParaConstruirLaTorre()
    {

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

    }

    @Test
    public void test05LasDefensasNoAtacanFueraDelRango()
    {

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
    public void testo11AlNoQuedarUnidadesEnemigasSinHaberlasEliminadoTodasElJugadorConVidaPositivaEsteGanaLaPartida()
    {

    }

    @Test
    public void test12SiElJugadorPierdeTodaLaVidaPierdeElJuego()
    {

    }
}