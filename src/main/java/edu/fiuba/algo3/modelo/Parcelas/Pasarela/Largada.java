package edu.fiuba.algo3.modelo.Parcelas.Pasarela;

import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;

public class Largada extends Parcela {
    
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

    @Override
    public void construir(Defensa defensa)
    {
        throw new TerrenoDeConstruccionInvalidoError();
    }
}
