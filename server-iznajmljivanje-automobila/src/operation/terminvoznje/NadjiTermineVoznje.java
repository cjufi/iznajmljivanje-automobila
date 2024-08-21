package operation.terminvoznje;

import domain.TerminVoznje;
import java.util.List;
import operation.AbstractSO;

/**
 *
 * @author Filip
 */
public class NadjiTermineVoznje extends AbstractSO {
    
    List<TerminVoznje> termini;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof TerminVoznje)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        termini = repository.filter(param);
    }

    public List<TerminVoznje> getTermineVoznje() {
        return termini;
    }   
}
