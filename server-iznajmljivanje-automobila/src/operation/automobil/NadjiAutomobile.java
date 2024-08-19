/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.automobil;

import domain.Automobil;
import java.util.List;
import operation.AbstractSO;

/**
 *
 * @author Filip
 */
public class NadjiAutomobile extends AbstractSO {
    
    private List<Automobil> automobili;

    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Automobil)) {
            throw new Exception("Invalid parameter");
        }  
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        automobili = repository.filter((Automobil) param);
    }

    public List<Automobil> getAutomobili() {
        return automobili;
    }
}
