<%-- 
    Document   : menu
    Created on : Mar 27, 2021, 6:10:02 PM
    Author     : camran1234
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    session.removeAttribute("user");
    session.removeAttribute("login");
    String answer = (String)session.getAttribute("answer");
    session.removeAttribute("answer");
    %>
<style>
    body {
        background-image: url("./Resources/fondo.jpg");
    }
    navbar-brand {
        width:130px;
        height:130px;
    }
    .container{
        margin: 50px;
        border-style: solid;
        border-width: 2px;
        height: 400px;
        width: 100px;
        position: relative;
          background: rgb(60, 60, 60);
        opacity: 0.8;
        
    }
    #formsText{
        display: block;
        text-align: center;
        font-family: Verdana;
        font-size: 30px;
        font-weight: bolder;
        color: white;
        text-decoration: underline;
    }
    
    #TittleText{
        font-family: Verdana;
        font-size: 25px;
        font-weight: normal;
        color: white;
    }
    #componentA{align-content: center;}
    #nav1{  
        opacity: 0.8;     
    }
    #text{
        font-size: 40px
    }
    #textError{
        font-family: Verdana;
        font-size: 25px;
        color: red;
    }
    
</style>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Pragma" content="no-cache">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Menu Indigo</title>
    </head>
    <body onunload="">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark" id='nav1'>
            <a class="navbar-brand" href="#" id='text'><img src ="./Resources/flag.png" height="100" width="100"   />Servidor Indigo</a>
        </nav>
        <br><br><br><br>
         
        <form method="post" action="./LoginServer">
            <div class="container">
                <div class="row justify-content-center">                    
                    <div class="form-group col-md-4 col-md-offset-4 align-center ">                
                        <label id="formsText">Acceder Sesión</label><br>                    
                        <span></span>                    
                        <label id="TittleText">Usuario</label>                    
                        <input type="text" name = "user" placeholder="Ingrese su codigo de usuario" size="50%" class="form-control" required/>                    
                        <label id="TittleText">Contraseña</label>                    
                        <input type="password" name = "password" placeholder="Ingrese su contraseña" size="50%" class="form-control" required/><br>                   
                        <input id="componentA"type="submit"  class="ButtonOptions"  value="Iniciar Sesión" >                    
                        <%
                            if(answer!=null){
                            %>
                            <label id="textError"><%=answer%></label>
                        <%
                            }
                            %>
                    </div>    
                </div> 
            </div>
        </form>
            
        
        
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body> 
</html>
