package edu.fiuba.algo3.unitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Creditos;
import edu.fiuba.algo3.modelo.SingleLogger;

public class CreditosTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01SeAgregaCreditosCorrectamente()
    {
        Creditos creditos = new Creditos(100);
        Creditos creditosEsperado = new Creditos(110);
        
        creditos.agregarCreditos(10);

        assertEquals(creditosEsperado, creditos);
    }

    @Test
    public void test02SeGastaCreditosCorrectamente()
    {
        Creditos creditos = new Creditos(100);
        Creditos creditosEsperado = new Creditos(90);
        
        creditos.gastarCreditos(10);

        assertEquals(creditosEsperado, creditos);
    }
}
