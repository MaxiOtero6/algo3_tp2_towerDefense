package edu.fiuba.algo3.modelo.Enemigos;

public class NoEnemigo extends Enemigo {

    public NoEnemigo() {
        super(0,0,0,0, null, null);
    }

    @Override
    protected void morir() {}

    @Override
    public void recibirDanio(int danioRecibido, String tipoTorre){}
    
}
