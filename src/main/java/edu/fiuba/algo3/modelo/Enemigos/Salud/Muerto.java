package edu.fiuba.algo3.modelo.Enemigos.Salud;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public class Muerto implements SaludEnemigo{
    @Override
    public void comprobarSalud(LinkedList<Enemigo> enemigos, Enemigo enemigo)
    {
        enemigos.remove(enemigo);
        enemigo.eliminar();
    }

    @Override
    public int incrementarDistancia(){return 10000;}
}
