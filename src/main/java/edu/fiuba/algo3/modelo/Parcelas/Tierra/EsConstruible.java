package edu.fiuba.algo3.modelo.Parcelas.Tierra;

import edu.fiuba.algo3.modelo.Defensas.*;

public class EsConstruible implements Construible{
    
    @Override
    public Defensa construir(Defensa defensa) throws Exception
    {
        return defensa;
    }

}
