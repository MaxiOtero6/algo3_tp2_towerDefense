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
            Math.pow(Math.abs(pos2.obtenerX() - pos1.obtenerX()), 2) 
            + Math.pow(Math.abs(pos2.obtenerY() - pos1.obtenerY()), 2)
        );
    }

    public boolean igual(Posicion posicion)
    {
        return posicion.igualCoordenadas(coordenadaX, coordenadaY);
    }

    private boolean igualCoordenadas(int coordenadasX, int coordenadasY)
    {
        return (coordenadasX == coordenadaX & coordenadasY == coordenadaY);
    }
    
    @Override
    public boolean equals(Object o)
    {
        if (o == this) {return true;}
        if (o.getClass() == this.getClass())
        {
            Posicion posicion = (Posicion)o;
            return (this.coordenadaX == posicion.obtenerX() && this.coordenadaY == posicion.obtenerY());
        }
        return false;
    }
}
