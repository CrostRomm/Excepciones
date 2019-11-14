
package Excepciones;

import javax.ejb.ApplicationException;

/**
 *
 * @author Hp-14
 */
@ApplicationException(rollback=true)
public class ExcepcionEjemplo1 extends Exception{
    public ExcepcionEjemplo1(String msg) {
        super(msg);
    }
}
