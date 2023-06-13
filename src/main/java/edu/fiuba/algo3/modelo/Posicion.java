package edu.fiuba.algo3.modelo;

public class Posicion {
    private int coordenadaX; 
    private int coordenadaY;
    
    public Posicion(int coordenadaX, int coordenadaY)
    {
        this.coordenadaX = coordenadaX;
        this.coordenadaY = coordenadaY;
    }

    public double calcDistancia(Posicion pos)
    {
        return 
        Math.sqrt(
            Math.pow(Math.abs(pos.coordenadaX - this.coordenadaX), 2) 
            + Math.pow(Math.abs(pos.coordenadaY - this.coordenadaY), 2)
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

    public String imprimirPosicion(){
        return String.format("(%d,%d)", coordenadaX, coordenadaY);
    }

}
