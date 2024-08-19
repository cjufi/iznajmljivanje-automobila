package operation.automobil;

import domain.Automobil;
import java.util.List;
import operation.AbstractSO;

/**
 *
 * @author Filip
 */
public class UcitajListuAutomobila extends AbstractSO {
    
    List<Automobil> automobili;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Automobil)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        automobili = repository.vratiSve((Automobil) param);
    }

    public List<Automobil> getAutomobili(){
        return automobili;
    }
}
