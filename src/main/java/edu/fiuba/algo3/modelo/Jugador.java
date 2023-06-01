package edu.fiuba.algo3.modelo;

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

    public static Jugador obtenerJugador()
    {
        if (jugador == null)
        {
            jugador = new Jugador();
        }
        return jugador;
    }
}
