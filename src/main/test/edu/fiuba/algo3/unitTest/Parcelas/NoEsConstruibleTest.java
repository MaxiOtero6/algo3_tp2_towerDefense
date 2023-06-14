package edu.fiuba.algo3.unitTest.Parcelas;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Defensas.Torres.Torre;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;
import edu.fiuba.algo3.modelo.Parcelas.Tierra.EsConstruible;
import edu.fiuba.algo3.modelo.Parcelas.Tierra.NoEsConstruible;

public class NoEsConstruibleTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01AlConstruirEnUnaParcelaNoConstruibleArrojaUnaExcepcion()
    {  
        Torre defensa = new TorreBlanca();
        NoEsConstruible construible = new NoEsConstruible();
        assertThrows(TerrenoDeConstruccionInvalidoError.class, () -> construible.construir(defensa, new Posicion(0, 0)));
    }
}
