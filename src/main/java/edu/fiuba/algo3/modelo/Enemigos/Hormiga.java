package edu.fiuba.algo3.modelo.Enemigos;

import edu.fiuba.algo3.modelo.Posicion;

public class Hormiga extends Enemigo {
    
    private static int hormigasMuertas = 0;
    public Hormiga()
    {
        super(1,1,1,1);
        if (hormigasMuertas >= 10) {this.creditos = 2;}
    }

    @Override
    public void recibirDanio(int danioRecibido)
    {
        this.energia -= danioRecibido;
        if (this.energia <= 0)
        {
            hormigasMuertas++;
            this.otorgarCreditos();
        }
    }
}
