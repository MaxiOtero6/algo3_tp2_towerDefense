package edu.fiuba.algo3.controlador;

import edu.fiuba.algo3.modelo.Defensas.Defensa;
import edu.fiuba.algo3.modelo.Defensas.Torres.NoTorre;
import edu.fiuba.algo3.modelo.Defensas.Torres.TorreBlanca;
import edu.fiuba.algo3.modelo.Defensas.Trampas.TrampaArenosa;
import edu.fiuba.algo3.modelo.Parcelas.Parcela;
import edu.fiuba.algo3.modelo.Parcelas.Pasarela;
import edu.fiuba.algo3.modelo.Parcelas.Tierra;
import edu.fiuba.algo3.vista.VistaDefensas;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class CasillaMapaEventHandler implements EventHandler<ActionEvent> {

    private Parcela parcela;
    private Defensa defensa;
    
    public CasillaMapaEventHandler(Parcela parcela, Defensa defensa){
        this.parcela = parcela;
        this.defensa = defensa;
    }
    @Override
    public void handle(ActionEvent actionEvent){
       // if(!(defensa instanceof NoTorre)){

            //sound
//            if((defensa instanceof TorreBlanca) && parcela instanceof Tierra){
//                sonidoPonerTorre.play();
//            }
//            if(defensa instanceof TrampaArenosa && parcela instanceof Pasarela){
//                sonidoPonerTrampa.play();
//            }
//            try {
//                partida.construirDefensa(defensa, coordenadaX, coordenaday);
//                VistaDefensas vistaDefensa = new VistaDefensas(root, defensa);
//                vistaDefensa.dibujar();
//                listaVistaDefensas.add(vistaDefensa);
//                ejecutarBotonSkipTurno(stagePrincipal);
//                activarBotones();
//
//                defensa = new NoTorre();
//            } catch (NullPointerException e){
//                sonidoError.play();
  //          }

 //       }
  }
}
