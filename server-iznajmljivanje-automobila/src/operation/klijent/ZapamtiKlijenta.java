package operation.klijent;

import domain.Klijent;
import operation.AbstractSO;

/**
 *
 * @author Filip
 */
public class ZapamtiKlijenta extends AbstractSO {
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Klijent)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Klijent k = (Klijent) param;
        repository.promeni(k);
    }
}
