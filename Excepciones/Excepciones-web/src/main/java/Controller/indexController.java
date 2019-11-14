
package Controller;

import Entity.Hijo;
import Entity.Persona;
import Excepciones.ExcepcionEjemplo1;
import Interfaces.HijoFacadeLocal;
import Interfaces.PersonaFacadeLocal;
import POJO.HijoPOJO;
import POJO.PadrePOJO;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Hp-14
 */
@ManagedBean(name="exp")
@ViewScoped
public class indexController implements Serializable{
    /**
     * EJB 1
     */
    @EJB
    private PersonaFacadeLocal padreEjb;
    /**
     * EJB 1
     */
    @EJB
    private HijoFacadeLocal hijoEjb;
    /**
     * Lista de padres
     */
    private List<Persona> listaPadre;
    /**
     * Lista de hijos
     */
    private List<Hijo> listaHijo; 
    /**
     * Padre pojo
     */
    private PadrePOJO pojoP;
    /**
     * Hijo pojo
     */
    private HijoPOJO pojoH;
    /**
     * Postconstruc inicia variables
     */
    private int idh;
    private int doc;
    private String nombre;
    private int dad;
    private String error;
    @PostConstruct
    public void init(){
        listaPadre = new ArrayList();
        listaHijo = new ArrayList();
        pojoH = new HijoPOJO();
        pojoP = new PadrePOJO();
    }
    /**
     * Constructor
     */
    public indexController() {
    }
    /**
     * Get lista padre
     * @return 
     */
    public List<Persona> getListaPadre() {
        return listaPadre;
    }
    /**
     * Set lista padre
     * @param listaPadre 
     */
    public void setListaPadre(List<Persona> listaPadre) {
        this.listaPadre = listaPadre;
    }
    /**
     * Get lista hijo
     * @return 
     */
    public List<Hijo> getListaHijo() {
        return listaHijo;
    }
    /**
     * Set lista hijo
     * @param listaHijo 
     */
    public void setListaHijo(List<Hijo> listaHijo) {
        this.listaHijo = listaHijo;
    }
    /**
     * Get pojo padre
     * @return 
     */
    public PadrePOJO getPojoP() {
        return pojoP;
    }
    /**
     * Set pojo padre
     * @param pojoP 
     */
    public void setPojoP(PadrePOJO pojoP) {
        this.pojoP = pojoP;
    }
    /**
     * Get pojo hijo
     * @return 
     */
    public HijoPOJO getPojoH() {
        return pojoH;
    }
    /**
     * Set pojo hijo
     * @param pojoH 
     */
    public void setPojoH(HijoPOJO pojoH) {
        this.pojoH = pojoH;
    }
    /**
     * Get EJB
     * @return 
     */
    public PersonaFacadeLocal getPadreEjb() {
        return padreEjb;
    }
    /**
     * Set EJB
     * @param padreEjb 
     */
    public void setPadreEjb(PersonaFacadeLocal padreEjb) {
        this.padreEjb = padreEjb;
    }
    /**
     * Get EJB
     * @return 
    */
    public HijoFacadeLocal getHijoEjb() {
        return hijoEjb;
    }
    /**
     * Set EJB
     * @param hijoEjb 
     */
    public void setHijoEjb(HijoFacadeLocal hijoEjb) {
        this.hijoEjb = hijoEjb;
    }
    /**
     * Listar Padre
     * @return lista de padres
     */
    public List<Persona> pintarPadre(){
        listaPadre = padreEjb.findAll();
        return listaPadre; 
    }
    /**
     * Listar hijo
     * @return lista de hijos
     */
    public List<Hijo> pintarHijo(){
        listaHijo = hijoEjb.findAll();
        return listaHijo; 
    }

    public int getIdh() {
        return idh;
    }
    

    public void setIdh(int idh) {
        this.idh = idh;
    }

    public int getDoc() {
        return doc;
    }

    public void setDoc(int doc) {
        this.doc = doc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDad() {
        return dad;
    }

    public void setDad(int dad) {
        this.dad = dad;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    public void Registrar() throws ExcepcionEjemplo1{
        try {
        Persona p1 = new Persona(pojoP.getIdp(), pojoP.getDocumento(), pojoP.getNombre());
        Persona padre = padreEjb.find(pojoH.getIdh());
        Persona padre2 = padreEjb.find(idh);
        Hijo h1 = new Hijo(pojoH.getIdh(), pojoH.getDocumento(),pojoH.getNombre(),padre);
        Hijo h2 = new Hijo(idh,doc,nombre,padre2);
        padreEjb.requiredTransaccional(p1, h1, h2);
        List<Persona> lista = padreEjb.findAll();
        Persona error1 = lista.get(18);
        }catch(NumberFormatException ex){
            throw new ExcepcionEjemplo1("Se ha reportado un error al capturar caracteres en lugar de numeros en el documento o el identificador");
        }catch (NullPointerException n) {
            throw new ExcepcionEjemplo1("Se intento obtener el sujeto numero 18 el cual no existe");
        }catch(EJBException ejb){
            pojoP.setNombre(ejb.getClass().getName());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"¡ATENCIÓN!", "Se ha reportado un error al capturar caracteres en lugar de numeros en el documento o el identificador, el nombre del error es: "+ejb.getClass().getName()+", adicional un: "+ejb.getCause()));
        }
    }
    public void personalizadaUno() throws ExcepcionEjemplo1{
        try {
            Persona p1 = new Persona(0,null);
            Hijo h1 = null;
            padreEjb.personalizadaConRollback(p1,h1);
        } catch (ExcepcionEjemplo1 e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"¡ATENCIÓN!", e.getClass().getName()+" especificamente: "+e.getCause()));
        }
    }
    public void sinRollBack() throws IOException{
        try {
            Persona p1 = new Persona(0,null);
            padreEjb.noHaceRollBack(p1);
        } catch (IOException e) {
            throw e;
        }
    }
}
