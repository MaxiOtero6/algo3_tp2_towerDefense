package edu.fiuba.algo3.modelo.Errores;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorrePlateada;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela;
import edu.fiuba.algo3.modelo.Parcelas.Tierra;

public class DefensaEnTerrenoErroneoError extends RuntimeException {
    public DefensaEnTerrenoErroneoError() {
        super("No se puede construir este tipo de defensa en esta parcela!");
    }

    public static void comprobarTerreno(Parcela parcela, Defensa defensa) {
        if (parcela instanceof Tierra) {
            if (defensa instanceof TrampaArenosa) {
                throw new DefensaEnTerrenoErroneoError();
            }
        } else if (parcela instanceof Pasarela) {
            if (defensa instanceof TorreBlanca || defensa instanceof TorrePlateada) {
                throw new DefensaEnTerrenoErroneoError();
            }
        }
    }
}

