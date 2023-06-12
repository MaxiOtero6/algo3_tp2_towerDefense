package edu.fiuba.algo3.modelo.Parcelas;

import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Torres.*;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Errores.AgregarEnemigoError;
import edu.fiuba.algo3.modelo.Errores.SpawnNoEnLargadaError;
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;
import edu.fiuba.algo3.modelo.Parcelas.Tierra.Tierra;

public abstract class Parcela {

    protected Posicion posicion;
    protected LinkedList<Enemigo> enemigos;

    public Parcela(int coordenadaX, int coordenadaY)
    {
        this.posicion = new Posicion(coordenadaX, coordenadaY);
        this.enemigos = new LinkedList<>();
    }

    public abstract void construir(Defensa defensa);

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
    public boolean equals(Object o)
    {
        if (o == this) {return true;}
        if (o instanceof Parcela)
        {
            Parcela parcela = (Parcela)o;
            return (this.posicion.equals(parcela.posicion));
        }
        return false;
    }

    public void aparecerEnemigos(List<Enemigo> enemigos)
    {
        throw new SpawnNoEnLargadaError();
    }

    public boolean verificarSiEstaElEnemigo(Enemigo enemigo)
    {
        return this.enemigos.contains(enemigo);
    }
}
