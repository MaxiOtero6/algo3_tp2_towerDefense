package edu.fiuba.algo3.unitTest.Enemigos;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Enemigos.Topo;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Largada;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Pasarela;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public class TopoTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01ElTopoAtacoAlJugadorConSuCorrespondienteDanioEnTurnoPar()
    {
        Jugador jugadorMock = mock(Jugador.class);
        Topo enemigo = new Topo(jugadorMock, null);
        enemigo.setNumeroTurno(0);
        enemigo.atacar();
        verify(jugadorMock, times(1)).recibirDanio(2, enemigo.getClass().getSimpleName());
    }

    @Test
    public void test01ElTopoAtacoAlJugadorConSuCorrespondienteDanioEnTurnoImpar()
    {
        Jugador jugadorMock = mock(Jugador.class);
        Topo enemigo = new Topo(jugadorMock, null);
        enemigo.setNumeroTurno(1);
        enemigo.atacar();
        verify(jugadorMock, times(1)).recibirDanio(5, enemigo.getClass().getSimpleName());
    }

    @Test
    public void test02ElTopoAlRecibirDanioMortalEntregaCreditos()
    {
        Jugador jugadorMock = mock(Jugador.class);
        Enemigo enemigo = new Topo(jugadorMock, null);
        enemigo.recibirDanio(1, "Test");
        assertTrue(!enemigo.estaVivo());
        verify(jugadorMock, times(1)).agregarCreditos(anyInt());
    }

    @Test
    public void test03ElTopoAceleraCorrectamente()
    {
        LinkedList<Parcela> parcelas = new LinkedList<>();
        Camino camino = new Camino(parcelas);
        Enemigo enemigo = new Topo(null, camino);
        LinkedList<Enemigo> enemigos = new LinkedList<>();
        enemigos.add(enemigo);

        Parcela parcela1 = new Pasarela(100,100);
        Parcela parcela2 = new Pasarela(200,200);
        Parcela parcela3 = new Pasarela(300,300);
        Parcela spy1 = spy(parcela1); Parcela spy2 = spy(parcela2); Parcela spy3 = spy(parcela3);
        
        parcelas.add(new Largada(0,0)); parcelas.add(spy1);
        camino.aparecerEnemigos(enemigos);

        enemigo.mover();
        verify(spy1, times(1)).agregarEnemigo(enemigo);

        for (int i = 0; i < 5; i++) {
            parcelas.add(new Pasarela(i+1,i+1));
            enemigo.mover();
        }

        parcelas.add(spy2);
        enemigo.mover();
        verify(spy2, times(1)).agregarEnemigo(enemigo);

        for (int i = 0; i < 5; i++) {
            parcelas.add(new Pasarela(i+6,i+6));
            enemigo.mover();
        }

        parcelas.add(new Pasarela(11,11)); parcelas.add(spy3);
        enemigo.mover();
        verify(spy3, times(1)).agregarEnemigo(enemigo);
    }
}
