package edu.fiuba.algo3.modelo;

public class Posicion {
    private int coordenadaX; 
    private int coordenadaY;
    
    public int obtenerX()
    {
        return this.coordenadaX;
    }
    
    public int obtenerY()
    {
        return this.coordenadaY;
    }

    public static double calcDistancia(Posicion pos1, Posicion pos2)
    {
        return 
        Math.sqrt(
            Math.pow(Math.abs(pos2.obtenerX() - pos1.obtenerX()), 2) 
            + Math.pow(Math.abs(pos2.obtenerY() - pos1.obtenerY()), 2)
        );
    }
}
