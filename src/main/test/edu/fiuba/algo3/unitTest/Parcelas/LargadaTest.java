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
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela.Largada;

public class LargadaTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01AlConstruirEnLargadaUnaTorreDebeTirarExcepcion()
    {   
        Largada parcela = new Largada(0,0);
        Torre defensa = new TorreBlanca();
        assertThrows(TerrenoDeConstruccionInvalidoError.class, () -> parcela.construir(defensa));
    }

    @Test
    public void test01AlConstruirEnLargadaUnaTrampaDebeTirarExcepcion()
    {
        Largada parcela = new Largada(0,0);
        TrampaArenosa defensa = new TrampaArenosa();
        assertThrows(TerrenoDeConstruccionInvalidoError.class, () -> parcela.construir(defensa)); 
    }

    @Test
    public void test02AlAparecerEnemigosEnLargadaNoDebeTirarUnaExcepcion()
    {
        Largada parcela = new Largada(0, 0);
        Enemigo enemigo = new Hormiga(null,null);
        List<Enemigo> lista = new LinkedList<>();
        lista.add(enemigo);
        assertDoesNotThrow(() -> parcela.aparecerEnemigos(lista));
    }
}
