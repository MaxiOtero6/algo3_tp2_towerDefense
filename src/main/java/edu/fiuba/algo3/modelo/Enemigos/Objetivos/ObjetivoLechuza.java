package edu.fiuba.algo3.modelo.Enemigos.Objetivos;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Defensas.Torres.NoTorre;
import edu.fiuba.algo3.modelo.Defensas.Torres.Torre;

public class ObjetivoLechuza {
    public Torre hallarObjetivo(LinkedList<Torre> torres)
    {
        if (torres != null) {return torres.getFirst();}
        return (new NoTorre());
    }
}
