/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entity.Hijo;
import Entity.Persona;
import Excepciones.ExcepcionEjemplo1;
import java.io.IOException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Hp-14
 */
@Local
public interface PersonaFacadeLocal {

    void create(Persona persona);

    void edit(Persona persona);

    void remove(Persona persona);

    Persona find(Object id);

    List<Persona> findAll();

    List<Persona> findRange(int[] range);

    int count();
    
    public void requiredTransaccional(Persona p1, Hijo p2, Hijo p3)throws ExcepcionEjemplo1;
    
    public void requiresNewTransaccional(Persona p1, Hijo p2, Hijo p3)throws ExcepcionEjemplo1;
    
    public void mandatoryTransaccional(Persona p1,Hijo p2, Hijo p3)throws ExcepcionEjemplo1;
    
    public void personalizadaConRollback(Persona p1,Hijo h1)throws ExcepcionEjemplo1;
    
    public void noHaceRollBack(Persona p1)throws IOException;
}
