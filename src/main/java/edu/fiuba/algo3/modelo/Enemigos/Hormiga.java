package edu.fiuba.algo3.modelo.Enemigos;

public class Hormiga extends Enemigo {
    
    private static int hormigasMuertas = 0;
    public Hormiga()
    {
        super(1,1,1,1);
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
