package edu.fiuba.algo3.unitTest.Parser;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;

import edu.fiuba.algo3.modelo.SingleLogger;

public class CreadorMapaTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }
}
