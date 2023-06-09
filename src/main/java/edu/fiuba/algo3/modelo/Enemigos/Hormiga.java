package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Jugador;
import edu.fiuba.algo3.modelo.Camino;

public class Hormiga extends Enemigo {
    
    private static int hormigasMuertas = 0;
    public Hormiga(Jugador jugador, Camino camino)
    {
        super(1,1,1,1, jugador, camino);
        if (hormigasMuertas >= 10) {this.creditos = 2;}
    }

    @Override
    protected void morir()
    {
        enemigosMuertos++;
        hormigasMuertas++;
    }

    public static void resetContador()
    {
        hormigasMuertas = 0;
    }
}
