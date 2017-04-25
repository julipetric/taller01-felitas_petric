/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isi.died.tp.modelo;

import java.util.ArrayList;
import java.util.*;

/**
 *
 * @author Juli
 */
public class Portal2 extends Portal{
    private ArrayList<MaterialCapacitacion> lista;

    public Portal2() {
        lista = new ArrayList<>();
    }

    public Portal2(ArrayList<MaterialCapacitacion> biblioteca) {
        lista = new ArrayList<>();
        lista = biblioteca;        
    }
    
    public void agregar(MaterialCapacitacion m){
        lista.add(m);
        super.agregarMaterial(this.lista.size()-1, m);
        
    }
    
    public ArrayList<MaterialCapacitacion> listar(){
//        System.out.println("Hola");
//        Collections.sort(this.lista, new Comparator<MarerialCapacitacion>);
//        this.lista=(ArrayList)lista;
//        System.out.println(this.lista);
        return lista;
    }
    
    
}
