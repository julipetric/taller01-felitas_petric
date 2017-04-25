/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isi.died.tp.ordenamiento;

/**
 *
 * @author mdominguez
 */
public class OrdenarMergeSort extends OrdenadorService{

    void merge(ArregloDied arreglo, Integer izq, Integer med, Integer der){
        Integer n1, n2;
        n1 = med - izq + 1;
        n2 = der - med;
        ArregloDied tempIzq = new ArregloDied(n1);
        ArregloDied tempDer = new ArregloDied(n2);
        for(Integer i = 0; i<n1; i++){
            tempIzq.agregarEnPosicion(i, arreglo.get(izq + i));
        }
        for(Integer j = 0 ; j<n2; j++){
            tempDer.agregarEnPosicion(j, arreglo.get(j + med + 1));
        }
        Integer i=0, j=0;
        Integer k=izq;
        while(i < n1 && j <n2){
            if(tempIzq.get(i).valorOrdenamiento()<=tempDer.get(j).valorOrdenamiento()){
                arreglo.agregarEnPosicion(k, tempIzq.get(i));
                i++;
                ArregloDied.comparaciones++;
            }
            else{
                arreglo.agregarEnPosicion(k, tempDer.get(j));
                j++;
                ArregloDied.comparaciones++;
            }
            k++;
        }
        while(i < n1){
            arreglo.agregarEnPosicion(k, tempIzq.get(i));
            i++;
            k++;
        }
        while(j < n2){
            arreglo.agregarEnPosicion(k, tempDer.get(j));
            j++;
            k++;
        }
    }
    
    void sort(ArregloDied arreglo, Integer izq, Integer der){
        if(izq < der){
            Integer med = (izq + der)/2;
            sort(arreglo, izq, med);
            sort(arreglo, med + 1, der);
            merge(arreglo, izq, med, der);
        }
    }
    
    @Override
    public ArregloDied ordenar(ArregloDied arreglo) {
        arregloOrdenado = arreglo.clonar();
        arregloOrdenado.inicializarContadores();
        sort(arregloOrdenado, 0, arregloOrdenado.tamanio()-1);
        return arregloOrdenado;
    }
    
}
