package operation.uverenje;

import domain.Uverenje;
import operation.AbstractSO;

/**
 *
 * @author Filip
 */
public class KreirajUverenje extends AbstractSO {
    
    private Uverenje sacuvanoUverenje;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Uverenje)) {
            throw new Exception("Invalid parameter");
        }        
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Uverenje uverenje = (Uverenje) param;
        sacuvanoUverenje = (Uverenje) repository.dodajIVrati(uverenje);
    }
    
    public Uverenje getUverenje() {
        return sacuvanoUverenje;
    }
}
