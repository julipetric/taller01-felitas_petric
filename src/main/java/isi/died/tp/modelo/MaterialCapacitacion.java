/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isi.died.tp.modelo;

import isi.died.tp.ordenamiento.Ordenable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

/**
 *
 * @author mdominguez
 */


public abstract class MaterialCapacitacion implements Ordenable, Comparable<MaterialCapacitacion>{
    protected String titulo;
    protected EstadoPromocion estado;
    protected Date fechaPublicacion;
    private Integer suscripciones;

    public MaterialCapacitacion() {
        this.suscripciones=0;
    }       

    public MaterialCapacitacion(String titulo) {
        this();
        this.titulo = titulo;
    } 
       
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }   
    
    protected Integer suscripciones(){
        return this.suscripciones;
    }
   
    public void publicar(){
        this.estado = EstadoPromocion.LANZAMIENTO;
        this.fechaPublicacion = new Date();
    }
    
    public abstract Double precio();

    public abstract void liquidar();
    
    public void suscibir(){
        this.suscripciones++;
    }
    
    public final void cancelarSuscripcion(){
        this.suscripciones--;
    }
   
    @Override
    public Long valorOrdenamiento() {
        return Long.valueOf(numerarString(this.titulo)+""+formatoPrecio(this.precio()));
    }
        
    private Long formatoPrecio(Double precio){
        Long precioEntero = Math.round(precio);
        Long x = precioEntero%10000 ;
        Long formato = 10000+ x;
        return formato;
    }

    private Long numerarString(String arg){
        String origen = arg.toUpperCase();
        String resultado = "";
        char unChar ;
        for(int i =0;i<4;i++){
            Integer aux;
            if(i>origen.length()-1) aux = 37;
            else {
                unChar = origen.charAt(i);
                if(unChar>='A' && unChar <='Z'){ aux = unChar-54;
                }
                else{ aux = 38; }
            }
            resultado +=aux;
        }
        return Long.valueOf(resultado);
    }
    
    @Override
    public boolean equals(Object o){
        return(o instanceof MaterialCapacitacion && 
                (this.getTitulo().equalsIgnoreCase(((MaterialCapacitacion)o).getTitulo())));
    }      
    
    public void publicar(String f){
        SimpleDateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
        Date fecha;
        try{
            fecha = df.parse(f);
            Calendar c = Calendar.getInstance();
            c.setTime(fecha);
            Calendar actual = Calendar.getInstance();
            actual.getTime();
            Integer dia = c.get(Calendar.DAY_OF_WEEK);
            if(!(dia.equals(Calendar.SUNDAY) || dia.equals(Calendar.SATURDAY)) &&
                    ((actual.getTimeInMillis() - c.getTimeInMillis()) < (long)45*24*60*60*1000)){
                this.estado = EstadoPromocion.LANZAMIENTO;
                this.fechaPublicacion = fecha;
            }
            else{
                this.publicar();
            }
        }catch(ParseException e){
            System.out.println("Error de parseo ********  "+e);
            this.publicar();            
        }
        
    }
    
    final String fechaPublicacion(){
        SimpleDateFormat df = new SimpleDateFormat ("dd/MM/yyyy");
        return df.format(this.fechaPublicacion);
    }
        
    
    //RESOLVER PROBLEMA GUACHIN
    @Override
    public int compareTo(MaterialCapacitacion m2){
//        if(this.getTitulo().equalsIgnoreCase(m2.getTitulo())){
//            return (this.fechaPublicacion.after(m2.fechaPublicacion));
//        }
//        else return (this.numerarString(this.getTitulo()) < m2.numerarString(m2.getTitulo()));
        return 1;
    }
    
   }
