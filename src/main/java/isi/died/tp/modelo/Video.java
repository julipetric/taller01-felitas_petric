/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isi.died.tp.modelo;

/**
 *
 * @author Juli
 */
public class Video extends MaterialCapacitacion {
    protected Integer duracion;
    protected Double costo;

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Video(Integer duracion, Double costo, String titulo) {
        super(titulo);
        this.duracion = duracion;
        this.costo = costo;
        this.estado = EstadoPromocion.REGULAR;
    }

    public Video() {
    }

    @Override
    public void liquidar() {
        this.estado = EstadoPromocion.OFERTA;
    }
    
    @Override
    public Double precio(){        
        Double costoAux=0.0;
        switch(this.estado){
            case REGULAR:{
                costoAux = this.duracion*this.costo;
                break;
            }
            case OFERTA:{
                costoAux = this.duracion*this.costo*0.5;
                break;
            }
        }
        return costoAux;
    }
    
    @Override
    public boolean equals(Object o){
        return(o instanceof Video && super.equals(o));
    }

    @Override
    public void publicar(String f){
        super.publicar(f);
        this.estado = EstadoPromocion.REGULAR;
    }
        
}
