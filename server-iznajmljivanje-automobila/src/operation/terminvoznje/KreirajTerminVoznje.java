package operation.terminvoznje;

import domain.TerminVoznje;
import operation.AbstractSO;

/**
 *
 * @author Filip
 */
public class KreirajTerminVoznje extends AbstractSO {
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof TerminVoznje)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        TerminVoznje tv = (TerminVoznje) param;
        repository.dodaj(tv);
    }
}
