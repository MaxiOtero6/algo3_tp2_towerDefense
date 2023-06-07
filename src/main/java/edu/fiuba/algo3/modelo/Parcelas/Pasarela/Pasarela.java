package edu.fiuba.algo3.modelo.Parcelas.Pasarela;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;
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

    public void agregarEnemigo(Enemigo enemigo)
    {
        enemigo.setearPosicion(posicion);
        this.enemigos.add(enemigo);
    }

    protected void eliminarEnemigos()
    {
        while (enemigos.size() != 0) {
            Enemigo enemigo = enemigos.getFirst();
            enemigo.mover();
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
    public void construir(Defensa defensa) throws Exception
    {
        throw new TerrenoDeConstruccionInvalidoError();
    }


    public Posicion getPosicion()
    {
        return this.posicion;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) {return true;}
        if (o instanceof Pasarela)
        {
            Pasarela pasarela = (Pasarela)o;
            return (this.posicion.equals(pasarela.getPosicion()));
        }
        return false;
    }

    public boolean verificarSiEstaElEnemigo(Enemigo enemigo)
    {
        return enemigos.contains(enemigo);
    }

    public boolean tieneEnemigos(){
        return !enemigos.isEmpty();
    }

}
