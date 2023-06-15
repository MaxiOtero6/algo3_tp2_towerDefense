package edu.fiuba.algo3.unitTest;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import edu.fiuba.algo3.modelo.SingleLogger;

public class PartidaTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }
}
