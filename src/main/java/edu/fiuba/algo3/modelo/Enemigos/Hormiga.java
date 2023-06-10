package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Camino;

public class Hormiga extends Enemigo {
    
    private static int hormigasMuertas = 0;
    public Hormiga(Jugador jugador)
    {
        super(1,1,1,1, jugador);
        if (hormigasMuertas >= 10) {this.creditos = 2;}
    }

    @Override
    public void morir()
    {
        enemigosMuertos++;
        hormigasMuertas++;
        this.otorgarCreditos();
    }
}
