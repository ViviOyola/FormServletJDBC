package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DatabaseConnection;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        boolean isAuthenticated = false;
        String errorMessage = "";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            conn = DatabaseConnection.getConnection();
            
            // Preparar la consulta SQL
            String sql = "SELECT * FROM usuario WHERE nombre = ? AND password = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            // Ejecutar la consulta
            rs = pstmt.executeQuery();
            
            // Verificar si se encontró un usuario
            isAuthenticated = rs.next();
            
        } catch (SQLException e) {
            errorMessage = "Error de base de datos: " + e.getMessage();
        } finally {
            // Cerrar recursos en orden inverso
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                DatabaseConnection.closeConnection(conn);
            } catch (SQLException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado de Autenticación</title>");
            out.println("<style>");
            out.println(".message { max-width: 400px; margin: 50px auto; padding: 20px; text-align: center; border-radius: 5px; }");
            out.println(".success { background-color: #dff0d8; border: 1px solid #d6e9c6; color: #3c763d; }");
            out.println(".error { background-color: #f2dede; border: 1px solid #ebccd1; color: #a94442; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            
            if (isAuthenticated) {
                out.println("<div class='message success'>");
                out.println("<h2>Autenticado correctamente</h2>");
                out.println("<p>¡Bienvenido/a " + username + "!</p>");
            } else {
                out.println("<div class='message error'>");
                out.println("<h2>Error de autenticación</h2>");
                if (!errorMessage.isEmpty()) {
                    out.println("<p>" + errorMessage + "</p>");
                } else {
                    out.println("<p>Usuario o contraseña incorrectos</p>");
                }
            }
            
            out.println("<p><a href='index.html'>Volver al inicio</a></p>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }
} 