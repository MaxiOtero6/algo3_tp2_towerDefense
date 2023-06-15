package edu.fiuba.algo3.unitTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.SingleLogger;

public class PosicionTest {
    @BeforeEach
    public void setup(){
        SingleLogger.inicializar(LogManager.getLogger());
    }

    @Test
    public void test01LaDistanciaCalculadaEsCorrecta()
    {
        Random rand = new Random();
        int x1 = rand.nextInt(); int x2 = rand.nextInt(); int y1 = rand.nextInt(); int y2 = rand.nextInt();

        Posicion posicion1 = new Posicion(x1, y1);
        Posicion posicion2 = new Posicion(x2, y2);

        double distanciaEsperada = Math.sqrt(
            Math.pow(Math.abs(x2 - x1), 2) 
            + Math.pow(Math.abs(y2 - y1), 2)
        );

        double distanciaCalculada = posicion1.calcDistancia(posicion2);

        assertEquals(distanciaEsperada, distanciaCalculada);
    }

    @Test
    public void test02LaDiferenciaEnXEsLaIndicada()
    {
        Random rand = new Random();
        int x1 = rand.nextInt(); int x2 = rand.nextInt();

        Posicion posicion1 = new Posicion(x1, 0);

        int diferenciaEsperada = Math.abs(x2 - x1); 

        int diferenciaCalculada = Math.abs(posicion1.diferenciaEnX(x2));
        assertEquals(diferenciaEsperada, diferenciaCalculada);
    }

    @Test
    public void test03LaDiferenciaEnYEsLaIndicada()
    {
        Random rand = new Random();
        int y1 = rand.nextInt(); int y2 = rand.nextInt();

        Posicion posicion1 = new Posicion(0,y1);

        int diferenciaEsperada = Math.abs(y2 - y1); 

        int diferenciaCalculada = Math.abs(posicion1.diferenciaEnY(y2));
        assertEquals(diferenciaEsperada, diferenciaCalculada);
    }

    @Test
    public void test04AmbosPosicionSonIguales()
    {
        Posicion posicion1 = new Posicion(0,0);
        Posicion posicion2 = new Posicion(0,0);
        assertEquals(posicion1, posicion2);
        assertEquals(posicion1, posicion1);
    }

    @Test
    public void test04AmbosPosicionNoSonIguales()
    {
        Posicion posicion1 = new Posicion(0,0);
        Posicion posicion2 = new Posicion(1,1);
        int posicion3 = 0;
        assertNotEquals(posicion1, posicion2);
        assertNotEquals(posicion1, posicion3);
    }
}
