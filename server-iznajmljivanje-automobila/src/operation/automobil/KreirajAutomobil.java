package operation.automobil;

import domain.Automobil;
import operation.AbstractSO;

/**
 *
 * @author Filip
 */
public class KreirajAutomobil extends AbstractSO {

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Automobil)) {
            throw new Exception("Invalid parameter");
        }        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Automobil a = (Automobil) param;
        repository.dodaj(a);
    }
}
