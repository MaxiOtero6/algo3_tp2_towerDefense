package edu.fiuba.algo3.modelo;

import java.util.LinkedList;
import java.util.List;

import edu.fiuba.algo3.modelo.Parcelas.Pasarela.*;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public class Camino {
    private LinkedList<Pasarela> pasarelas;
    private static Camino camino;

    private Camino()
    {
        this.pasarelas = new LinkedList<Pasarela>();
    }

    public void agregarPasarelas(List<Pasarela> pasarelas)
    {
        this.pasarelas = new LinkedList<>(pasarelas);
    }
    
    /** Itera desde la meta hacia la largada para evitar perder informacion
     * Busca la pasarela que requiere, al indice de la misma se suma la velocidad del
     * enemigo y mueve al enemigo a tal pasarela.
     * @param velocidad
     * @param posicion
     * @param enemigo
     */
    public void moverEnemigo(int velocidad, Posicion posicion, Enemigo enemigo)
    {
        int iterador = pasarelas.size() - 1;
        while (!pasarelas.get(iterador).compararPosicion(posicion) && iterador >= 0)
        {
            iterador--;
        }
        
        int indiceDestino = iterador + velocidad;
        if (indiceDestino < 0) { indiceDestino = 0; }

        Pasarela pasarelaFinal = pasarelas.get(indiceDestino);

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

    public void aparecerEnemigos(List<Enemigo> enemigos)
    {
        ((Largada)pasarelas.get(0)).aparecerEnemigos(enemigos);
    }

}
