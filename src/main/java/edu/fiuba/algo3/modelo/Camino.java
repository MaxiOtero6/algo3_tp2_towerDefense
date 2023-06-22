package edu.fiuba.algo3.modelo;

import java.util.List;

import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Enemigos.Enemigo;

public class Camino {
    private List<Parcela> parcelas;

    public Camino(List<Parcela> parcelas)
    {
        this.parcelas = parcelas;
    }
    
    /* Busca la pasarela que requiere, al indice de la misma se suma la velocidad del
     * enemigo y mueve al enemigo a tal pasarela.
     * @param velocidad
     * @param posicion
     * @param enemigo
     */
    public void moverEnemigo(int velocidad, Posicion posicion, Enemigo enemigo)
    {
        if (0 < velocidad) 
        { 
            int iterador = indiceParcela(posicion);
            parcelas.get(iterador).eliminarEnemigo(enemigo);
        
            if (iterador + 1 > parcelas.size() - 1) {velocidad = 0; iterador--;}
            parcelas.get(iterador + 1).agregarEnemigo(enemigo);
        
            enemigo.mover(); 
        }

        enemigo.recargarVelocidad();
    }

    private int indiceParcela(Posicion posicion)
    {
        int iterador = 0;
        while (iterador < parcelas.size() - 1 && !parcelas.get(iterador).compararPosicion(posicion))
        {
            iterador++;
        }
        return iterador;
    }

    public void aparecerEnemigos(List<Enemigo> enemigos)
    {
        (parcelas.get(0)).aparecerEnemigos(enemigos);
    }

    public void eliminarEnemigo(Posicion posicion, Enemigo enemigo)
    {
        int iterador = indiceParcela(posicion);
        parcelas.get(iterador).eliminarEnemigo(enemigo);
    }

}
