package edu.fiuba.algo3.modelo.Parcelas.Pasarela;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;


public class Pasarela implements Parcela {
    
    private LinkedList<Enemigo> enemigos;

    
    public LinkedList<Enemigo> obtenerEnemigos()
    {
        return this.enemigos;
    }

    public void setEnemigos(LinkedList<Enemigo> enemigos)
    {
        this.enemigos = enemigos;
    }
    
    public void avanzarTurno()
    {
        
    }
    
    @Override
    public void construir(Defensa defensa) throws Exception
    {
        throw new Exception("No se puede construir en esta parcela!");
    }

}
