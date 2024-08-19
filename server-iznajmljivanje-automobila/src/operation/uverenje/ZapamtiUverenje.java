package operation.uverenje;

import domain.Uverenje;
import operation.AbstractSO;

/**
 *
 * @author Filip
 */
public class ZapamtiUverenje extends AbstractSO {

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Uverenje)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Uverenje uverenje = (Uverenje) param;
        repository.promeni(uverenje);
    }
}
