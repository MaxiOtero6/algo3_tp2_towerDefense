package edu.fiuba.algo3.unitTest.Parcelas;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Parcelas.Construible.EsConstruible;

public class EsConstruibleTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01AlConstruirEnUnaParcelaConstruibleNoArrojaUnaExcepcion()
    {  
        TorreBlanca defensa = new TorreBlanca();
        EsConstruible construible = new EsConstruible();
        assertDoesNotThrow(() -> construible.construir(defensa, new Posicion(0, 0)));
    }
}
