package exceptions;

import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Gennady on 10.11.2016.
 */
@XmlRootElement
public class ErrorMessage {


    public ErrorMessage() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorMessage(Exception ex)
    {
        if (ex instanceof ClientRequestException)
        {
            status = Response.Status.BAD_REQUEST.getStatusCode();
        }
        else
        {
            status= Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        }
        message = ex.getMessage();
    }



    @XmlElement(name = "status")
    private int status;

    @XmlElement(name = "message")
    private String message;
}
