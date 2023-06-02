package edu.fiuba.algo3.modelo.Parcelas;

import edu.fiuba.algo3.modelo.Defensas.*;

public interface Parcela {

    public void avanzarTurno();

    public void construir(Defensa defensa) throws Exception;
}
