package edu.fiuba.algo3.modelo.Enemigos.Objetivos;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Defensas.Torres.NoTorre;
import edu.fiuba.algo3.modelo.Defensas.Torres.Torre;

public class ObjetivoLechuza {
    private static LinkedList<Torre> torres;

    public static void setTorres(LinkedList<Torre> _torres_)
    {
        torres = _torres_;
    }

    public static Torre hallarObjetivo()
    {
        if (torres != null) {return torres.getFirst();}
        return (new NoTorre());
    }
}
