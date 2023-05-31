package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Jugador;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Tests {
    @Test
    public void test01JugadorSeCreaCon100CreditosY20Vida(){
        Jugador jugador = new Jugador();
        assertEquals(100, jugador.obtenerCreditos());
        assertEquals(20, jugador.obtenerVida());
    }
}
