package operation.automobil;

import domain.AbstractDomainObject;
import domain.Automobil;
import operation.AbstractSO;

/**
 *
 * @author Filip
 */
public class NadjiAutomobil extends AbstractSO {
    
    Automobil automobil = null;

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Automobil)) {
            throw new Exception("Invalid parameter");
        }  
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        automobil = (Automobil) repository.nadji((Automobil) param);
    }

    public Automobil getAutomobil() {
        return automobil;
    }
}
