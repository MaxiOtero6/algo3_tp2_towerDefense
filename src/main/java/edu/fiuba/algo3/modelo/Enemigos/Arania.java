package edu.fiuba.algo3.modelo.Enemigos;
import java.util.Random;

public class Arania extends Enemigo {
    
    private static int hormigasMuertas = 0;
    public Arania()
    {
        super(1,1,1,1);
        if (hormigasMuertas >= 10) {this.creditos = this.randCreditos();}
    }

    private int randCreditos()
    {
        Random rand = new Random();
        return rand.nextInt(11); //Genera int random en el intervalo [0,10]
    }
}
