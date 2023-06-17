package edu.fiuba.algo3.modelo.Defensas.Salud;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Defensas.Defensa;

public interface Salud 
{
    public void avanzarTurno(LinkedList<Defensa> defensas, Defensa defensa);
}
