package edu.fiuba.algo3.modelo.Parcelas;

import edu.fiuba.algo3.modelo.Defensas.*;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Errores.AgregarEnemigoError;
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;

public interface Parcela {

    public void construir(Defensa defensa) throws TerrenoDeConstruccionInvalidoError;

    public void agregarEnemigo(Enemigo enemigo) throws AgregarEnemigoError;
}
