package edu.fiuba.algo3.modelo.Defensas.Salud;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Defensas.Defensa;

public class Operativa implements Salud
{
    public void avanzarTurno(LinkedList<Defensa> defensas, Defensa defensa) 
    {
        defensa.avanzarTurno();
    }
}
