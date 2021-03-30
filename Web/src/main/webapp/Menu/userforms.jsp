<%-- 
    Document   : userforms
    Created on : Mar 29, 2021, 2:04:32 AM
    Author     : camran1234
--%>

<%@page import="WebSocket.dBIndigo"%>
<%@page import="com.mycompany.formats.Form"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if((session.getAttribute("login")==null) || !((String) session.getAttribute("login")).equals("true")){
        response.sendRedirect("../index.jsp");
    }   
    ArrayList<Form> forms = new ArrayList();
    dBIndigo db = new dBIndigo(response.getWriter());
    forms = db.collectForms((String)session.getAttribute("user"));
    %>
<style>
    body {
        background-image: url("../Resources/fondo2.jpg");
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
        background: rgb(246, 255, 246);
        opacity: 0.7;
        
    }
    #formsText{
        display: block;
        text-align: center;
        font-family: Verdana;
        font-size: 30px;
        font-weight: bolder;
        color: black;
        text-decoration: underline;
    }
    
    #TittleText{
        font-family: Verdana;
        font-size: 25px;
        font-weight: normal;
        color: black;
    }
    #componentA{align-content: center;}
    #nav1{  
        opacity: 0.8;     
    }
    #text{
        font-size: 40px
    }
    #viewNice{
        opacity: 0.8;
    }
    #textLi{
        display: block;
        text-align: center;
        font-family: Verdana;
        font-size: 15px;
        color: black;
    }
    .card {
        margin: 0 auto; /* Added */
        float: none; /* Added */
        margin-bottom: 10px; /* Added */
}
</style>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Pragma" content="no-cache">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Forms User</title>
    </head>
    <body onunload="">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark" id='nav1'>
            <a class="navbar-brand" href="../LogOutServer" id='text'><img src ="../Resources/flag.png" height="100" width="100"   />Servidor Indigo</a>
        </nav>
        <br><br><br><br>
        
        <div id='viewNice' class="card text-white bg-primary mb-3" style="max-width: 35rem;">
            <div class="card-header">Tus Formularios</div>
            <div class="card-body">
                <div class="list-group">
                <% if(forms.size()==0){%>
                
                    <li class="list-group-item list-group-item-action list-group-item-warning">Sin Formularios</li>
                <% }%>
              <%
                  for(int index=0; index<forms.size();index++){
                  %>
                    <form method='GET' action='./form.jsp'>
                        <li id='textLi' class="list-group-item list-group-item-info" name="formId" value="<%=forms.get(index).createFormMessage()%>"><%=forms.get(index).createFormMessage()%></li>
                        <input type="hidden" name="formId" value=<%=forms.get(index).getId()%>>
                        <input type="submit" value="Ver">
                    </form>
                  <%
                   }
                      %>
                 </div>
            </div>
        </div>
        
            
        
        
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body> 
</html>
