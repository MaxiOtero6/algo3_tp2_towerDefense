package edu.fiuba.algo3.modelo.Parcelas.Pasarela;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Errores.TerrenoDeConstruccionInvalidoError;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;

public class Meta extends Parcela{
    
    public Meta(int coordenadaX, int coordenadaY)
    {
        super(coordenadaX, coordenadaY);
    }

    @Override
    public void eliminarEnemigo(Enemigo enemigo)
    {
        enemigos.remove(enemigo);
    }

    @Override
    public void agregarEnemigo(Enemigo enemigo)
    {
        enemigo.atacar();
    }

    @Override
    public void construir(Defensa defensa)
    {
        throw new TerrenoDeConstruccionInvalidoError();
    }
}
