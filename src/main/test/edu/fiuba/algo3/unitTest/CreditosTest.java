package edu.fiuba.algo3.unitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

    @Test
    public void test03AmbosCreditosSonIguales()
    {
        Creditos creditos1 = new Creditos();
        Creditos creditos2 = new Creditos();
        assertEquals(creditos1, creditos2);
        assertEquals(creditos1, creditos1);
    }

    @Test
    public void test03AmbosCreditosNoSonIguales()
    {
        Creditos creditos1 = new Creditos();
        Creditos creditos2 = new Creditos(101);
        int creditos3 = 100;
        assertNotEquals(creditos1, creditos2);
        assertNotEquals(creditos1, creditos3);
    }
}
