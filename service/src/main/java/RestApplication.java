import exceptions.AppExceptionMapper;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by Splinner on 09.11.2016.
 */
public class RestApplication extends ResourceConfig
{

    /**
     * Register JAX-RS application components.
     */
    public RestApplication()
    {
        register(AppExceptionMapper.class);
//        register(com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider.class);
    }
}