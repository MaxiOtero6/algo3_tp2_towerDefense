package edu.fiuba.algo3.modelo.Parcelas;

import edu.fiuba.algo3.modelo.Errores.AgregarEnemigoError;
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Torres.*;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public class Rocoso implements Parcela {
    
    private Posicion posicion;

    public Rocoso(int coordenadaX, int coordenadaY)
    {
        this.posicion = new Posicion(coordenadaX, coordenadaY);
    }

    @Override
    public void construir(Torre torre) throws TerrenoDeConstruccionInvalidoError
    {
        throw new TerrenoDeConstruccionInvalidoError();
    }

    @Override
    public void agregarEnemigo(Enemigo enemigo) throws AgregarEnemigoError
    {
        throw new AgregarEnemigoError();
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) {return true;}
        if (o instanceof Rocoso)
        {
            Rocoso rocoso = (Rocoso)o;
            return (this.posicion.equals(rocoso.posicion));
        }
        return false;
    }
}
