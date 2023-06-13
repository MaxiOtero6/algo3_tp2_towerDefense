package edu.fiuba.algo3.unitTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Partida;
import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Errores.PerderPartidaError;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parser.CreadorMapa;

public class TurnoTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01ElJugadorPierdeLaPartidaSiNoHayEnemigos()
    {
        List<Parcela> camino = new LinkedList<>();
        Partida partida = new Partida(CreadorMapa.crearMapa(camino), camino);
        assertThrows(PerderPartidaError.class, () -> partida.iniciar());
    }
}
