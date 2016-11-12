package exceptions;

/**
 * Created by Splinner on 09.11.2016.
 */


import com.fasterxml.jackson.core.JsonProcessingException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class AppExceptionMapper implements ExceptionMapper<ClientRequestException> {

    public Response toResponse(ClientRequestException ex) {
        System.out.println("In mapper");
        ErrorMessage er = new ErrorMessage(ex);

        return Response.status(er.getStatus()).entity(er).type(MediaType.APPLICATION_JSON).
                build();

    }

}
