package operation.klijent;

import domain.Klijent;
import operation.AbstractSO;

/**
 *
 * @author Filip
 */
public class NadjiKlijenta extends AbstractSO {
    
    Klijent klijent = null;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Klijent)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        klijent = (Klijent) param;
        repository.nadji(klijent);
    }

    public Klijent getKlijent() {
        return klijent;
    }
}
