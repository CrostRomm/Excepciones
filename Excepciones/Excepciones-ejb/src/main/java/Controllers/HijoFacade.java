/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Interfaces.HijoFacadeLocal;
import Default.AbstractFacade;
import Entity.Hijo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hp-14
 */
@Stateless
public class HijoFacade extends AbstractFacade<Hijo> implements HijoFacadeLocal {

    @PersistenceContext(unitName = "Excepciones")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HijoFacade() {
        super(Hijo.class);
    }
    
}
