package edu.fiuba.algo3.modelo.Parcelas.Tierra;

import edu.fiuba.algo3.modelo.Defensas.*;

public class EsConstruible implements Construible{
    
    @Override
    public void construir(Defensa defensa, Tierra tierra)
    {
        tierra.colocarDefensa(defensa);
        tierra.setConstruible(new NoEsConstruible());
    }

}
