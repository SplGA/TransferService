import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import rest.TransferService;

/**
 * Created by Splinner on 08.11.2016.
 */
public class JettyServer
{


    private static Server server;

    public static void main(String[] args) throws Exception
    {
        try
        {
            startServer();
            server.join();
        }
        finally
        {
            stopServer();
        }

    }

    public static void startServer() throws Exception
    {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server = new Server(8181);
        server.setHandler(context);
        ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", TransferService.class.getCanonicalName());
        jerseyServlet.setInitParameter("javax.ws.rs.Application", RestApplication.class.getCanonicalName());

        server.start();
    }

    public static void stopServer() throws Exception
    {
        server.stop();
    }

}
