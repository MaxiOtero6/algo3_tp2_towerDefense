package edu.fiuba.algo3.modelo.Defensas.Salud;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Defensas.Defensa;

public interface SaludDefensa
{
    public void avanzarTurno(LinkedList<Defensa> defensas, Defensa defensa);
}
