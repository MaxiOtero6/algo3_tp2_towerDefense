package edu.fiuba.algo3.modelo;

public class Posicion {
    private int coordenadaX; 
    private int coordenadaY;
    
    public Posicion(int coordenadaX, int coordenadaY)
    {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }
    
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
            Math.pow(Math.abs(pos2.coordenadaX - pos1.coordenadaX), 2) 
            + Math.pow(Math.abs(pos2.coordenadaY - pos1.coordenadaY), 2)
        );
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o == this) {return true;}
        if (o instanceof Posicion)
        {
            Posicion posicion = (Posicion)o;
            return (this.coordenadaX == posicion.coordenadaX && this.coordenadaY == posicion.coordenadaY);
        }
        return false;
    }

    public int diferenciaEnX(int coordenadaX)
    {
        return (this.coordenadaX - coordenadaX);
    }

    public int diferenciaEnY(int coordenadaY)
    {
        return (this.coordenadaY - coordenadaY);
    }

}
