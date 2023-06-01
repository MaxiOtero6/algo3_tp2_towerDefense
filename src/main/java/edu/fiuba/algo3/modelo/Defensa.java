package edu.fiuba.algo3.modelo;

// Verificar que cada defensa tarde en construirse lo que dice que tarda y que recién están
// “operativas” cuando ya se terminaron de construir.

public abstract class Defensa {
       private int coste;
       private int rango;
       private int danio;
       private int progresoConstruccion;
       Estado estado;
    public Defensa(int coste, int rango, int danio, int progresoConstruccion){
        this.coste = coste;
        this.rango = rango;
        this.danio = danio;
        this.progresoConstruccion = progresoConstruccion;
        this.estado = new EstadoDesactivado(this);
    }

    public boolean progresarConstruccion(){
        this.progresoConstruccion -= 1;
        return this.progresoConstruccion == 0;
    }

    public void cambiarEstado(Estado estado){
        this.estado = estado;
    }


}