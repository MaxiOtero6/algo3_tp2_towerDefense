package edu.fiuba.algo3.modelo.Parcelas;

import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;

public class Largada extends Parcela {
    
    public Largada(int coordenadaX, int coordenadaY)
    {
        super(coordenadaX, coordenadaY);
    }
    
    @Override
    public void aparecerEnemigos(List<Enemigo> enemigos)
    {
        for (Enemigo enemigo : enemigos) 
        {
            this.agregarEnemigo(enemigo);
        }
    }

    @Override
    public void construir(Defensa defensa)
    {
        throw new TerrenoDeConstruccionInvalidoError();
    }
}
