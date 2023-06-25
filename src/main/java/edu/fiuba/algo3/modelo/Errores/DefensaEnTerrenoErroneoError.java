package edu.fiuba.algo3.modelo.Errores;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;

public class DefensaEnTerrenoErroneoError extends RuntimeException{
    public DefensaEnTerrenoErroneoError(){
        super("No se puede construir este tipo de defensa en esta parcela!");
    }

    public static void comprobarTierra(Parcela parcela, Defensa defensa)
    {
        if (defensa instanceof TrampaArenosa) {throw new DefensaEnTerrenoErroneoError();}
    }

    public static void comprobarPasarela(Parcela parcela, Defensa defensa)
    {
        if (defensa instanceof TorreBlanca) {throw new DefensaEnTerrenoErroneoError();}
    }
}
