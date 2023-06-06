package edu.fiuba.algo3.modelo.Parcelas;

import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public class Rocoso implements Parcela {
    
    private Posicion posicion;

    public Rocoso(Posicion posicion)
    {
        this.posicion = posicion;
    }

    public Posicion obtenerPosicion()
    {
        return this.posicion;
    }

    @Override
    public void avanzarTurno()
    {

    }

    @Override
    public void construir(Defensa defensa) throws Exception
    {
        throw new TerrenoDeConstruccionInvalidoError();
    }

    public void agregarEnemigo(Enemigo enemigo) throws Exception
    {
        throw new Exception("No se puede agregar un enemigo en esta parcela!");
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) {return true;}
        if (o instanceof Rocoso)
        {
            Rocoso rocoso = (Rocoso)o;
            return (this.posicion.equals(rocoso.obtenerPosicion()));
        }
        return false;
    }
}
