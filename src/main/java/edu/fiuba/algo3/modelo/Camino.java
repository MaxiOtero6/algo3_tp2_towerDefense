package edu.fiuba.algo3.modelo;

import java.util.LinkedList;

import edu.fiuba.algo3.modelo.Parcelas.Pasarela.*;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public class Camino {
    private LinkedList<Pasarela> pasarelas;
    private static Camino camino;

    private Camino()
    {
        this.pasarelas = new LinkedList<Pasarela>();
    }

    public void agregarPasarela(Pasarela pasarela)
    {
        pasarelas.add(pasarela);
    }

    public void moverEnemigo(int velocidad, Posicion posicion, Enemigo enemigo)
    {
        int iterador = 0;
        while (!pasarelas.get(iterador).compararPosicion(posicion))
        {
            iterador++;
        }

        int indiceDestino = iterador + velocidad;
        if (indiceDestino > pasarelas.size() - 1) { indiceDestino = pasarelas.size() - 1; }

        Pasarela pasarelaFinal = pasarelas.get(iterador + velocidad);

        pasarelaFinal.agregarEnemigo(enemigo);
    }

    public static Camino obtenerCamino()
    {
        if (camino == null)
        {
            camino = new Camino();
        }
        return camino;
    }

    public boolean tieneEnemigos(){
        boolean tieneEnemigos = false;
        for (Pasarela pasarela : pasarelas){
            if (pasarela.tieneEnemigos()){
                tieneEnemigos = true;
            }
        }
        return tieneEnemigos;
    }

    public void borrarPasarelas(){
        this.pasarelas = new LinkedList<Pasarela>();
    }

}
