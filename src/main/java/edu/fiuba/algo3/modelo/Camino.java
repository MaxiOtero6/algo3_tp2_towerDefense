package edu.fiuba.algo3.modelo;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Parcelas.Pasarela.*;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public class Camino {
    private LinkedList<Pasarela> pasarelas;
    private static Camino camino;

    public void agregarPasarela(Pasarela pasarela){
        pasarelas.add(pasarela);
    }

    public void moverEnemigo(int velocidad, Posicion posicion, Enemigo enemigo)
    {
        boolean corte = true;
        int iterador = 0;
        while (corte)
        {
            Pasarela pasarelaInicial = pasarelas.get(iterador);
            if (pasarelaInicial.compararPosicion(posicion)){
                Pasarela pasarelaFinal = pasarelas.get(iterador + velocidad);
                try {pasarelaFinal.agregarEnemigo(enemigo);}
                catch (Exception e){}
                corte = false;
            }
            iterador++;
        }
    }

    public static Camino obtenerCamino()
    {
        return camino;
    }
}
