package communication;

import java.io.Serializable;

/**
 *
 * @author Filip
 */
public class Response implements Serializable {
    
    private Object result;
    private Exception exception;
    private boolean uspesno;
    private String poruka;
    
    public Response() {
    }

    public Response(Object result, Exception exception, boolean uspesno) {
        this.result = result;
        this.exception = exception;
        this.uspesno = uspesno;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public boolean isUspesno() {
        return uspesno;
    }

    public void setUspesno(boolean uspesno) {
        this.uspesno = uspesno;
    }

    public String getPoruka() {
        return poruka;
    }

    public void setPoruka(String poruka) {
        this.poruka = poruka;
    }
}
