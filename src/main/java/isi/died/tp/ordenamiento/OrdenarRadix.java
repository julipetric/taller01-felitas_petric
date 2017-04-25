/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isi.died.tp.ordenamiento;

import java.util.ArrayList;

/**
 *
 * @author mdominguez
 */
public class OrdenarRadix extends OrdenadorService{
    
    static Long maxDigitos(ArregloDied arreglo){
        Long max = arreglo.get(0).valorOrdenamiento();
        for (Integer i = 1; i < arreglo.tamanio(); i++) {
            if (arreglo.get(i).valorOrdenamiento() > max) {
                max = arreglo.get(i).valorOrdenamiento();
            }
        }
        return max;        
    }
    
    @Override
    public ArregloDied ordenar(ArregloDied arreglo) {
        arregloOrdenado = arreglo.clonar();
        arregloOrdenado.inicializarContadores();
//        ORDENAR POR "Urnas"
        Long exp;
        Long max = maxDigitos(arregloOrdenado);
        ArrayList<ColaDied> urnas = new ArrayList<>(10);
        for(int i = 0; i<10; i++){
           urnas.add(i, new ColaDied(arregloOrdenado.tamanio())); 
        }        
        for(exp = 1l; max/exp > 0; exp*=10){
            Integer contador=0;
            for(int j = 0; j < arregloOrdenado.tamanio(); j++) {
                Long dl;
                dl = ((arregloOrdenado.datos[j].valorOrdenamiento()/exp))%10;
                urnas.get(dl.intValue()).enqueue(arregloOrdenado.datos[j]);
                ArregloDied.comparaciones++;
            }
            for(int r = 0; r < 10; r++){
                while(!(urnas.get(r).isEmpty())){
                    arregloOrdenado.agregarEnPosicion(contador, urnas.get(r).dequeue());
                    contador++;
                }
            }
        }
        return arregloOrdenado;
    }

    
}
