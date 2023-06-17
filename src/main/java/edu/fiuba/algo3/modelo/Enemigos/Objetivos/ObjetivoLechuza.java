package edu.fiuba.algo3.modelo.Enemigos.Objetivos;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Torres.NoTorre;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;

public class ObjetivoLechuza {
    public Defensa hallarObjetivo(LinkedList<Defensa> defensas)
    {
        int i = 0;
        while (defensas != null && defensas.size() > i) 
        {
            if (defensas.get(i) instanceof TorreBlanca) {return defensas.get(i);}
            i++;
        }
        return (new NoTorre());
    }
}
