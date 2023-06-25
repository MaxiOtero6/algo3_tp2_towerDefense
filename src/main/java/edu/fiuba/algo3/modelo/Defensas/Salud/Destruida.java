package edu.fiuba.algo3.modelo.Defensas.Salud;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Defensas.Defensa;

public class Destruida implements SaludDefensa {
    @Override
    public void avanzarTurno(LinkedList<Defensa> defensas, Defensa defensa) 
    {
        defensas.remove(defensa);
    }

    @Override
    public void avanzarEntreTurno(Defensa defensa) {}
}
