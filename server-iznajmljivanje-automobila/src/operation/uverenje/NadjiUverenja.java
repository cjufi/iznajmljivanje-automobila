package operation.uverenje;

import domain.Uverenje;
import java.util.List;
import operation.AbstractSO;

/**
 *
 * @author Filip
 */
public class NadjiUverenja extends AbstractSO {
    
    private List<Uverenje> uverenja;

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Uverenje)) {
            throw new Exception("Invalid parameter");
        }  
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        uverenja = repository.filter((Uverenje) param);
    }

    public List<Uverenje> getUverenja() {
        return uverenja;
    }
    
}
