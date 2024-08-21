package operation.klijent;

import domain.Klijent;
import java.util.List;
import operation.AbstractSO;

/**
 *
 * @author Filip
 */
public class UcitajListuKlijenata extends AbstractSO {
    
    List<Klijent> klijenti;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Klijent)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        klijenti = repository.vratiSve((Klijent) param);
    }

    public List<Klijent> getKlijenti(){
        return klijenti;
    }
}
