package POJO;
/**
 * @author Nathaly Salazar
 * @author Cristian Romero
 */
public class PadrePOJO {
    private int idp;
    private int documento;
    private String nombre;
    private String nombre2;

    public PadrePOJO() {
    }

    public PadrePOJO(int documento, String nombre) {
        this.documento = documento;
        this.nombre = nombre;
    }

    public PadrePOJO(int idp, int documento, String nombre2) {
        this.idp = idp;
        this.documento = documento;
        this.nombre2 = nombre2;
    }
    
    public int getIdp() {
        return idp;
    }

    public void setIdp(int idp) {
        this.idp = idp;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre2() {
        return nombre2;
    }

    public void setNombre2(String nombre2) {
        this.nombre2 = nombre2;
    }
    
    
}
