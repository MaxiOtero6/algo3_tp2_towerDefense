package edu.fiuba.algo3.modelo.Enemigos;
import java.util.Random;

import edu.fiuba.algo3.modelo.Jugador;

public class Arania extends Enemigo {
    
    private static int araniasMuertas = 0;
    public Arania(Jugador jugador)
    {
        super(2,2,0,2, jugador);
        this.creditos = randCreditos();
    }

    private int randCreditos()
    {
        Random rand = new Random();
        return rand.nextInt(11); //Genera int random en el intervalo [0,10]
    }

    @Override
    public void morir()
    {
        enemigosMuertos++;
        araniasMuertas++;
        this.otorgarCreditos();
    }
}
