package edu.fiuba.algo3.modelo.Parcelas.Tierra;

import edu.fiuba.algo3.modelo.Defensas.*;

public class NoEsConstruible implements Construible {

    @Override //Crear Exception
    public void construir(Defensa defensa, Tierra parcela)
    {
        System.out.println("No se puede construir en esta parcela!");
    }
    
}
