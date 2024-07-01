/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.klijent;

import domain.Klijent;
import java.util.List;
import operation.AbstractSO;

/**
 *
 * @author Filip
 */
public class NadjiKlijente extends AbstractSO {
    
    private List<Klijent> klijenti;

    @Override
    protected void preconditions(Object param) throws Exception {
        if (param == null || !(param instanceof Klijent)) {
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        klijenti = repository.filter((Klijent) param);
    }

    public List<Klijent> getKandidati() {
        return klijenti;
    }
}
