/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isi.died.tp.modelo;

import java.util.Date;

/**
 *
 * @author mdominguez
 */
public class Libro extends MaterialCapacitacion{
    private Double costo;
    private String isbn;
    private Integer paginas;
    
    public Libro() {
    }    

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public Date getFechaPublicacion(){
        return this.fechaPublicacion;
    }
        
    public void publicar(Date fechaEspecifica){
        this.estado = EstadoPromocion.LANZAMIENTO;
        this.fechaPublicacion = fechaEspecifica;
    }
    
    @Override
    public Double precio(){        
        Double costoAux=0.0;
        switch(this.estado){
            case REGULAR:{
                costoAux = (this.costo + (this.paginas / 150)*0.03*this.costo);
                break;
            }
            case ACCESO_TEMPRANO:{
                costoAux = ((this.costo + (this.paginas / 150)*0.03*this.costo)*0.9);
                break;
            }
            case LANZAMIENTO:{
                costoAux = ((this.costo + (this.paginas / 150)*0.03*this.costo)*1.2);
                break;
            }
            case OFERTA:{
                costoAux = (this.costo * 0.8);
                break;
            }
        }
        return costoAux;
    }
          
    @Override
    public void liquidar() {
        this.estado = EstadoPromocion.OFERTA;
    }

    public Libro(String titulo, Double costo, String isbn, Integer paginas) {
        super(titulo);
        this.costo = costo;
        this.isbn = isbn;
        this.paginas = paginas;
        this.estado = EstadoPromocion.ACCESO_TEMPRANO;
    }
    
    @Override
    public void suscibir(){
        super.suscibir();
        if (super.suscripciones()>=2) {
            this.estado = EstadoPromocion.REGULAR;
        }
    }

    @Override
    public boolean equals(Object o){
        return(o instanceof Libro && super.equals(o));
    }

    @Override
    public void publicar(String f){
        super.publicar(f);
        this.estado = EstadoPromocion.LANZAMIENTO;
    }
    
    
}