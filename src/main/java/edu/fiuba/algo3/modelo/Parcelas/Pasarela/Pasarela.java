package edu.fiuba.algo3.modelo.Parcelas.Pasarela;

import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Errores.SpawnNoEnLargadaError;
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;
import edu.fiuba.algo3.modelo.Camino;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Defensas.Torres.*;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;

public class Pasarela implements Parcela {
    
    protected LinkedList<Enemigo> enemigos;
    protected Posicion posicion;

    public Pasarela(int coordenadaX, int coordenadaY)
    {
        this.enemigos = new LinkedList<Enemigo>();
        this.posicion = new Posicion(coordenadaX, coordenadaY);
    }

    @Override
    public void agregarEnemigo(Enemigo enemigo)
    {
        enemigo.setearPosicion(posicion);
        this.enemigos.add(enemigo);
    }

    protected void eliminarEnemigos()
    {
        while (enemigos.size() != 0) {
            Enemigo enemigo = enemigos.getFirst();
            if (enemigo.estaVivo())
            {
                enemigo.mover();
            }
            enemigos.removeFirst();
        }
    }

    public boolean compararPosicion(Posicion posicion)
    {
        return this.posicion.equals(posicion);
    }
    
    public void avanzarTurno()
    {
        eliminarEnemigos();
    }
    
    @Override
    public void construir(Torre torre) throws TerrenoDeConstruccionInvalidoError
    {
        throw new TerrenoDeConstruccionInvalidoError();
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) {return true;}
        if (o instanceof Pasarela)
        {
            Pasarela pasarela = (Pasarela)o;
            return (this.posicion.equals(pasarela.posicion));
        }
        return false;
    }

    public boolean verificarSiEstaElEnemigo(Enemigo enemigo)
    {
        return enemigos.contains(enemigo);
    }

    public void aparecerEnemigos(List<Enemigo> enemigos) throws SpawnNoEnLargadaError
    {
        throw new SpawnNoEnLargadaError();
    }
}
