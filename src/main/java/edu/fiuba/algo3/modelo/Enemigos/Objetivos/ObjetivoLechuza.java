package edu.fiuba.algo3.modelo.Enemigos.Objetivos;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Defensas.Torres.NoTorre;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;

public class ObjetivoLechuza {
    public TorreBlanca hallarObjetivo(LinkedList<TorreBlanca> torres)
    {
        if (torres != null) {return torres.getFirst();}
        return (new NoTorre());
    }
}
