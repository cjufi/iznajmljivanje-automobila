package operation.servis;

import domain.Servis;
import java.util.List;
import operation.AbstractSO;

/**
 *
 * @author Filip
 */
public class UcitajListuServisa extends AbstractSO {

    List<Servis> servisi;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Servis)) {
            throw new Exception("Invalid parameter");
        }    
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        servisi = repository.vratiSve((Servis) param);
    }

    public List<Servis> getServisi() {
        return servisi;
    }
}
