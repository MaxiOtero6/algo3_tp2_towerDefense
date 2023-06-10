package edu.fiuba.algo3.modelo.Enemigos;

public class NoEnemigo extends Enemigo {

    public NoEnemigo() {
        super(0,0,0,0, true, null);
    }

    @Override
    public void morir() {}
    
}
