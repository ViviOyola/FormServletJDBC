package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServletPost", urlPatterns = {"/LoginServletPost"})
public class LoginServletPost extends HttpServlet {
    
    private static final String VALID_USERNAME = "viviana";
    private static final String VALID_PASSWORD = "1234";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado de Autenticación (POST)</title>");
            out.println("<style>");
            out.println(".message { max-width: 400px; margin: 50px auto; padding: 20px; text-align: center; border-radius: 5px; }");
            out.println(".success { background-color: #dff0d8; border: 1px solid #d6e9c6; color: #3c763d; }");
            out.println(".error { background-color: #f2dede; border: 1px solid #ebccd1; color: #a94442; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            
            if (VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)) {
                out.println("<div class='message success'>");
                out.println("<h2>Autenticado correctamente (POST)</h2>");
                out.println("<p>¡Bienvenido/a " + username + "!</p>");
            } else {
                out.println("<div class='message error'>");
                out.println("<h2>Error de autenticación</h2>");
                out.println("<p>Usuario o contraseña incorrectos</p>");
            }
            
            out.println("<p><a href='index.html'>Volver al inicio</a></p>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
} 