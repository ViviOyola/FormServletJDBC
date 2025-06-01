<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Login con JSP</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            .container {
                display: flex;
                justify-content: space-around;
                padding: 20px;
            }
            .login-form {
                width: 300px;
                margin: 20px;
                padding: 20px;
                border: 1px solid #ccc;
                border-radius: 5px;
            }
            .form-group {
                margin-bottom: 15px;
            }
            .form-group label {
                display: block;
                margin-bottom: 5px;
            }
            .form-group input {
                width: 100%;
                padding: 8px;
                border: 1px solid #ddd;
                border-radius: 4px;
            }
            .submit-btn {
                width: 100%;
                padding: 10px;
                background-color: #4CAF50;
                color: white;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }
            .submit-btn:hover {
                background-color: #45a049;
            }
            .form-title {
                text-align: center;
                color: #333;
            }
            .time-info {
                text-align: center;
                margin-bottom: 20px;
                padding: 10px;
                background-color: #f8f9fa;
                border-radius: 4px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <%-- Mostrar la fecha y hora actual usando JSP --%>
            <div class="time-info">
                <p>Fecha y hora actual: <%= new java.util.Date() %></p>
                <p>Servidor: <%= application.getServerInfo() %></p>
            </div>
        </div>
        
        <div class="container">
            <div class="login-form">
                <h2 class="form-title">Login usando GET</h2>
                <%-- Usar JSP Expression Language para mostrar mensajes de error si existen --%>
                <c:if test="${not empty param.error}">
                    <div style="color: red; margin-bottom: 15px;">
                        ${param.error}
                    </div>
                </c:if>
                <form action="LoginServlet" method="GET">
                    <div class="form-group">
                        <label for="username">Usuario:</label>
                        <%-- Mantener el valor del usuario si ya se intentó un login --%>
                        <input type="text" id="username" name="username" 
                               value="${param.username}" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Contraseña:</label>
                        <input type="password" id="password" name="password" required>
                    </div>
                    <button type="submit" class="submit-btn">Ingresar con GET</button>
                </form>
            </div>

            <div class="login-form">
                <h2 class="form-title">Login usando POST</h2>
                <form action="LoginServletPost" method="POST">
                    <div class="form-group">
                        <label for="username_post">Usuario:</label>
                        <input type="text" id="username_post" name="username" 
                               value="${param.username}" required>
                    </div>
                    <div class="form-group">
                        <label for="password_post">Contraseña:</label>
                        <input type="password" id="password_post" name="password" required>
                    </div>
                    <button type="submit" class="submit-btn">Ingresar con POST</button>
                </form>
            </div>
        </div>
        
        <%-- Footer con información del JSP --%>
        <footer style="text-align: center; margin-top: 20px; padding: 10px; background-color: #f8f9fa;">
            <p>
                <%-- Usando scriptlet para mostrar información del sistema --%>
                <% 
                    String userAgent = request.getHeader("User-Agent");
                    out.println("Navegador del cliente: " + userAgent);
                %>
            </p>
        </footer>
    </body>
</html> 