package operation.uverenje;

import domain.Automobil;
import domain.Uverenje;
import operation.AbstractSO;

/**
 *
 * @author Filip
 */
public class NadjiUverenje extends AbstractSO {
    
    Uverenje uverenje = null;

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Uverenje)) {
            throw new Exception("Invalid parameter");
        }  
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        uverenje = (Uverenje) repository.nadji((Uverenje) param);
    }

    public Uverenje getUverenje() {
        return uverenje;
    }
}
