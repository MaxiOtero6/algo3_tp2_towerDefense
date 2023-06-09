package edu.fiuba.algo3.modelo.Parcelas.Pasarela;

import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public class Largada extends Pasarela {
    
    public Largada(int coordenadaX, int coordenadaY)
    {
        super(coordenadaX, coordenadaY);
    }
    
    @Override
    public void aparecerEnemigos(List<Enemigo> enemigos)
    {
        this.enemigos = new LinkedList<>(enemigos);
        for (Enemigo enemigo : this.enemigos) 
        {
            enemigo.setearPosicion(this.posicion);
        }
    }

}
