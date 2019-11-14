/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Interfaces.PersonaFacadeLocal;
import Default.AbstractFacade;
import Entity.Hijo;
import Entity.Hijo2;
import Entity.Persona;
import Entity.Persona2;
import Excepciones.ExcepcionEjemplo;
import Excepciones.ExcepcionEjemplo1;
import Interfaces.Persona2FacadeLocal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ApplicationException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hp-14
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> implements PersonaFacadeLocal {
    /**
     * EJB
     */
    @EJB
    Persona2FacadeLocal padre2Ejb;
    /**
     * Unidad de persistencia
     */
    @PersistenceContext(unitName = "Excepciones")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }
    /**
     * required transaccional
     * @param p1 
     * @param p2 
     * @param p3 
     * @throws Excepciones.ExcepcionEjemplo1 
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void requiredTransaccional(Persona p1, Hijo p2, Hijo p3)throws ExcepcionEjemplo1{
        try{    
            em.persist(p1);
            Persona2 padre = new Persona2(p2.getPadre().getId(),p2.getPadre().getDocumento(),p2.getPadre().getNombre());
            Persona2 padre2 = new Persona2(p3.getPadre().getId(),p3.getPadre().getDocumento(),p3.getPadre().getNombre());
            Hijo2 p4 = new Hijo2(p2.getIdh(),p2.getDocumento(),p2.getNombre(),padre);
            Hijo2 p5 = new Hijo2(p3.getIdh(),p3.getDocumento(),p3.getNombre(),padre2);
            padre2Ejb.required2Transaccional(p4, p5);
        }catch(NumberFormatException e){
            throw new ExcepcionEjemplo1("Algo salio mal, el error que se capturo fue al intentar usar una letra o palabra en un campo de numeros");
        } catch (ExcepcionEjemplo ex) {
            throw new ExcepcionEjemplo1("Error presentado en el EJB2 tal vez quiera revisar el codigo");
        }
    }
    /**
     * requires new transaccional
     * @param p1 
     * @param p2 
     * @param p3 
     * @throws Excepciones.ExcepcionEjemplo1 
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void requiresNewTransaccional(Persona p1, Hijo p2, Hijo p3)throws ExcepcionEjemplo1{
        try{    
            em.persist(p1);
            Persona2 padre = new Persona2(p2.getPadre().getId(),p2.getPadre().getDocumento(),p2.getPadre().getNombre());
            Persona2 padre2 = new Persona2(p3.getPadre().getId(),p3.getPadre().getDocumento(),p3.getPadre().getNombre());
            Hijo2 p4 = new Hijo2(p2.getIdh(),p2.getDocumento(),p2.getNombre(),padre);
            Hijo2 p5 = new Hijo2(p3.getIdh(),p3.getDocumento(),p3.getNombre(),padre2);
            padre2Ejb.requiresNew2Transaccional(p4, p5);
        }catch(NumberFormatException e){
            throw new ExcepcionEjemplo1("Algo salio mal, el error que se capturo fue al intentar usar una letra o palabra en un campo de numeros");
        } catch (ExcepcionEjemplo ex) {
            throw new ExcepcionEjemplo1("Error presentado en el EJB2 tal vez quiera revisar el codigo");
        }
    }
    /**
     * mandatory transaccional
     * @param p1 
     * @param p2 
     * @param p3 
     * @throws Excepciones.ExcepcionEjemplo1 
     */
    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void mandatoryTransaccional(Persona p1,Hijo p2, Hijo p3)throws ExcepcionEjemplo1{
        try{    
            em.persist(p1);
            Persona2 padre = new Persona2(p2.getPadre().getId(),p2.getPadre().getDocumento(),p2.getPadre().getNombre());
            Persona2 padre2 = new Persona2(p3.getPadre().getId(),p3.getPadre().getDocumento(),p3.getPadre().getNombre());
            Hijo2 p4 = new Hijo2(p2.getIdh(),p2.getDocumento(),p2.getNombre(),padre);
            Hijo2 p5 = new Hijo2(p3.getIdh(),p3.getDocumento(),p3.getNombre(),padre2);
            padre2Ejb.madatory2Transaccional(p4, p5);
        }catch(NumberFormatException e){
            throw new ExcepcionEjemplo1("Algo salio mal, el error que se capturo fue al intentar usar una letra o palabra en un campo de numeros");
        } catch (ExcepcionEjemplo ex) {
            throw new ExcepcionEjemplo1("Error presentado en el EJB2 tal vez quiera revisar el codigo");
        }
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void personalizadaConRollback(Persona p1, Hijo h1) throws ExcepcionEjemplo1 {
        Hijo2 h2 = null;
        em.persist(p1);
        try {
            padre2Ejb.persona2(h2);
        } catch (ExcepcionEjemplo ex) {
            throw new ExcepcionEjemplo1("NO se puede registrar este usuario ERROR");
        }
        
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void noHaceRollBack(Persona p1) throws IOException {
        em.persist(p1);
        throw new IOException("No hace rollback");
    }
}
