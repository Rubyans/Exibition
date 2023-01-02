package app.controllerFront.filters;
import javax.servlet.*;
import java.io.IOException;

public class FilterEncoding implements Filter { //the class is used to filter data in one encoding

    private String encoding;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException { //called by the web container to indicate to a filter that it is being placed into service.
        encoding=filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //called by the container each time a request/response pair is passed through the chain due to a client request for a resource at the end of the chain.
        request.setCharacterEncoding(encoding);
        chain.doFilter(request,response);
    }
}
