package edu.fiuba.algo3.modelo.Parcelas;

import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public interface Parcela {

    public void avanzarTurno();

    public void construir(Defensa defensa) throws Exception;

    public void agregarEnemigo(Enemigo enemigo) throws Exception;
}
