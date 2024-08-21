package domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Filip
 */
public interface AbstractDomainObject extends Serializable {
    
    public String getTableName();
    
    public List<AbstractDomainObject> getList(ResultSet rs) throws Exception;
    
    public String getAttributeNames();
    
    public String getUnknownValues();
    
    public void prepareStatement(PreparedStatement ps, AbstractDomainObject ado) throws Exception;
    
    public String getUpdateQuery();
    
    public String getID(AbstractDomainObject ado);
    
    public String getOrderCondition();
    
    public AbstractDomainObject getResult(ResultSet rs)throws Exception;
    
    public String getCondition(AbstractDomainObject ado);
    
    void setID(Long id);
}
