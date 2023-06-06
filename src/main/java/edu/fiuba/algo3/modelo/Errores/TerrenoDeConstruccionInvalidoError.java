package edu.fiuba.algo3.modelo.Errores;

public class TerrenoDeConstruccionInvalidoError extends RuntimeException {
    public TerrenoDeConstruccionInvalidoError(){
        super("No se puede construir en esta parcela!");
    }
}
