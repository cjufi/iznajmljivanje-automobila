package operation.stavkaautomobila;

import domain.StavkaAutomobila;
import operation.AbstractSO;

/**
 *
 * @author Filip
 */
public class KreirajStavkuAutomobila extends AbstractSO {

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof StavkaAutomobila)) {
            throw new Exception("Invalid parameter");
        }      
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        StavkaAutomobila sa = (StavkaAutomobila) param;
        repository.dodaj(sa);
    }
}
