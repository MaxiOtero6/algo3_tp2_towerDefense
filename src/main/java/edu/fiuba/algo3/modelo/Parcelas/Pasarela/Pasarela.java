package edu.fiuba.algo3.modelo.Parcelas.Pasarela;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;


public class Pasarela implements Parcela {
    
    protected LinkedList<Enemigo> enemigos;
    private Posicion posicion;

    public Pasarela(Posicion posicion)
    {
        this.enemigos = new LinkedList<Enemigo>();
        this.posicion = posicion;
    }

    public LinkedList<Enemigo> obtenerEnemigos()
    {
        return this.enemigos;
    }

    public void agregarEnemigo(Enemigo enemigo)
    {
        this.enemigos.add(enemigo);
        enemigo.setearPosicion(posicion);
    }

    private void eliminarEnemigos()
    {
        while (enemigos.size() != 0) {
            Enemigo enemigo = enemigos.getFirst();
            enemigo.mover();
            enemigos.removeFirst();
        }
    }

    public boolean compararPosicion(Posicion posicion)
    {
        return this.posicion.igual(posicion);
    }
    
    public void avanzarTurno()
    {
        eliminarEnemigos();
    }
    
    @Override
    public void construir(Defensa defensa) throws Exception
    {
        throw new Exception("No se puede construir en esta parcela!");
    }

    public boolean verificarSiEstaElEnemigo(Enemigo enemigo)
    {
        return enemigos.contains(enemigo);
    }
}
