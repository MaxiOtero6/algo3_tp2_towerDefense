package edu.fiuba.algo3.modelo.Enemigos;
import java.util.Random;

public class Arania extends Enemigo {
    
    private static int araniasMuertas = 0;
    public Arania()
    {
        super(2,2,0,2);
        this.creditos = this.randCreditos();
    }

    private int randCreditos()
    {
        Random rand = new Random();
        return rand.nextInt(11); //Genera int random en el intervalo [0,10]
    }
}
