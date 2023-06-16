package edu.fiuba.algo3.unitTest.Enemigos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.SingleLogger;
import edu.fiuba.algo3.modelo.Defensas.Torres.NoTorre;
import edu.fiuba.algo3.modelo.Defensas.Torres.Torre;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Enemigos.Objetivos.ObjetivoLechuza;

public class ObjetivoLechuzaTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01ElObjetivoDeUnaLechuzaEsLaPrimeraTorreConstruidaPorElUsuario()
    {
        Torre torre1 = new TorreBlanca();
        Torre torre2 = new TorreBlanca();

        LinkedList<Torre> torres = new LinkedList<>();
        torres.add(torre1); torres.add(torre2);

        ObjetivoLechuza objetivo = new ObjetivoLechuza();
        Torre torreEsperada = torre1;

        Torre torreObtenida = objetivo.hallarObjetivo(torres);

        assertEquals(torreEsperada, torreObtenida);
    }

    @Test
    public void test01ElObjetivoDeUnaLechuzaSiNoHayTorresEs()
    {
        ObjetivoLechuza objetivo = new ObjetivoLechuza();
        Torre torreEsperada = new NoTorre();
        Torre torreObtenida = objetivo.hallarObjetivo(null);


        assertEquals(torreEsperada, torreObtenida);
    }
}
