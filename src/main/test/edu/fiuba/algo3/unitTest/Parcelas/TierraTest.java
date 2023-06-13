package edu.fiuba.algo3.unitTest.Parcelas;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Defensas.Torres.Torre;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Enemigos.Hormiga;
import edu.fiuba.algo3.modelo.Errores.DefensaEnTerrenoErroneoError;
import edu.fiuba.algo3.modelo.Errores.SpawnNoEnLargadaError;
import edu.fiuba.algo3.modelo.Parcelas.Tierra.Tierra;

public class TierraTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01AlConstruirEnTierraUnaTorreNoDebeTirarExcepcion()
    {   
        Tierra parcela = new Tierra(0,0);
        TrampaArenosa defensa = new TrampaArenosa();
        assertThrows(DefensaEnTerrenoErroneoError.class, () -> parcela.construir(defensa));
    }

    @Test
    public void test01AlConstruirEnTierraUnaTrampaDebeTirarUnaExcepcion()
    {
        Tierra parcela = new Tierra(0,0);
        Torre defensa = new TorreBlanca();
        assertDoesNotThrow(() -> parcela.construir(defensa));    
    }

    @Test
    public void test02AlAparecerEnemigosEnTierraDebeTirarUnaExcepcion()
    {
        Tierra parcela = new Tierra(0, 0);
        Enemigo enemigo = new Hormiga(null,null);
        List<Enemigo> lista = new LinkedList<>();
        lista.add(enemigo);
        assertThrows(SpawnNoEnLargadaError.class, () -> parcela.aparecerEnemigos(lista));
    }
}