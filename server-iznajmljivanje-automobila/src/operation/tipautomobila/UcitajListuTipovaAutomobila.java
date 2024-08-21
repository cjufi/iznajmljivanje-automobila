package operation.tipautomobila;

import domain.TipAutomobila;
import java.util.List;
import operation.AbstractSO;

/**
 *
 * @author Filip
 */
public class UcitajListuTipovaAutomobila extends AbstractSO {
    
    List<TipAutomobila> tipoviAutomobila;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof TipAutomobila)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        tipoviAutomobila = repository.vratiSve((TipAutomobila) param);
    }

    public List<TipAutomobila> getTipoviAutomobila() {
        return tipoviAutomobila;
    }
}
