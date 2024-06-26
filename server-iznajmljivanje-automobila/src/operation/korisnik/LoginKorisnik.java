/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operation.korisnik;
import domain.AbstractDomainObject;
import domain.Korisnik;
import operation.AbstractSO;

/**
 *
 * @author Filip
 */
public class LoginKorisnik extends AbstractSO {

    private AbstractDomainObject ado;
    
    @Override
    protected void preconditions(Object param) throws Exception {
        if(param == null || !(param instanceof Korisnik)){
            throw new Exception("Invalid parameter");
        }
    }

    @Override
    protected void executeOperation(Object param) throws Exception {
        Korisnik k = (Korisnik) param;
        System.out.println(k);
        ado = (AbstractDomainObject) repository.uloguj(k);
    }
    
    public AbstractDomainObject getAbstractDomainObject() {
        return ado;
        }    
    }
