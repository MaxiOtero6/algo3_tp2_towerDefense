package edu.fiuba.algo3.vista;

import javafx.scene.control.Slider;
import java.util.List;

import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;

public class ControladorSonidos {
    
    private Slider sliderMusica;
    private Slider sliderSonidos;
    private MediaPlayer musica;
    private List<AudioClip> listaDeSonidos;
    public ControladorSonidos( MediaPlayer musica, List<AudioClip> sonidos) {
        this.musica = musica;
        this.listaDeSonidos = sonidos;
    }

    public void actualizarSliders(){
        sliderMusica.setValue(musica.getVolume() * 100);
        AudioClip aux = (AudioClip)listaDeSonidos.get(0);
        sliderSonidos.setValue(aux.getVolume() * 100);
    }

    public List<AudioClip> devolverSonidos(){
        return listaDeSonidos;
    }

    public void setSliderMusica(Slider sliderMusica){
        this.sliderMusica = sliderMusica;
    }

    public void setSliderSonidos(Slider sliderSonidos){
        this.sliderSonidos = sliderSonidos;
    }
}
