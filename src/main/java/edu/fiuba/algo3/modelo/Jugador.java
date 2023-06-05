package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.Enemigos.Enemigo;
import edu.fiuba.algo3.modelo.Errores.PerderPartidaError;

public class Jugador {
    private Creditos creditos;
    private int vida;
    private String nombre;
    private static Jugador jugador;

    private Jugador()
    {
        this.creditos = new Creditos();
        this.vida = 20;
    }

    public int obtenerCreditos()
    {
        return this.creditos.obtenerCreditos();
    }

    public int obtenerVida()
    {
        return this.vida;
    }

    public void agregarCreditos(int creditos)
    {
        this.creditos.agregarCreditos(creditos);
    }

    public void eliminarCreditos(int creditos)
    {
        this.creditos.eliminarCreditos(creditos);
    }

    public void recibirDanio(int danio)
    {
        this.vida -= danio;
        if (vida <= 0){
            throw new PerderPartidaError();
        }
    }

    public static Jugador obtenerJugador()
    {
        if (jugador == null)
        {
            jugador = new Jugador();
        }
        return jugador;
    }
    
    public boolean comprobarCreditos(int creditosAGastar){
        return creditos.comprobarCreditos(creditosAGastar);
    }

    public void restaurarVida(){
        this.vida = 20;
    }

}
