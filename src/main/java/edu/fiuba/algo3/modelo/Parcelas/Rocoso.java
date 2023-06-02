package edu.fiuba.algo3.modelo.Parcelas;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public class Rocoso implements Parcela {
    
    private Posicion posicion;

    public Rocoso(Posicion posicion)
    {
        this.posicion = posicion;
    }


    @Override
    public void avanzarTurno()
    {

    }

    @Override
    public void construir(Defensa defensa) throws Exception
    {
        throw new Exception("No se puede construir en esta parcela!");
    }

    public void agregarEnemigo(Enemigo enemigo) throws Exception
    {
        throw new Exception("No se puede agregar un enemigo en esta parcela!");
    }
}
