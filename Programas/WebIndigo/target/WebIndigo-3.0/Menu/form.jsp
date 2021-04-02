<%-- 
    Document   : form
    Created on : Mar 29, 2021, 4:00:07 AM
    Author     : camran1234
--%>

<%@page import="com.mycompany.formats.Component"%>
<%@page import="com.mycompany.formats.Form"%>
<%@page import="java.util.ArrayList"%>
<%@page import="WebSocket.dBIndigo"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    String formId = request.getParameter("formId");
    dBIndigo db = new dBIndigo(response.getWriter());
    ArrayList<Form> forms = db.getForms();
    Form form= null;
    boolean isCreator=false;
    for(Form auxForm:forms){
        if(auxForm.getId().equals(formId)){
            form = auxForm;
            break;
        }
    }
    if(form!=null){
        String actualUser = (String)session.getAttribute("user");
        if(actualUser==null){
            actualUser="Guest";
        }
        
        if(form.getUserCreator().equals(actualUser)){
            isCreator = true;
        }    
        String color = "White";
        String topicForm = form.getTopic();
        if(topicForm.equalsIgnoreCase("Cyan")){
            color = "Cyan";
        }else if(topicForm.equalsIgnoreCase("White")){
            color = "White";
        }else if(topicForm.equalsIgnoreCase("Red")){
            color = "Red";
        }else if(topicForm.equalsIgnoreCase("Blue")){
            color = "Blue";
        }else if(topicForm.equalsIgnoreCase("Dark")){
            color = "Grey";
        }else{
            color = topicForm;
        }
        ArrayList<Component> components = db.collectComponents(formId);
    %>
<style>
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
    #viewNice{
        opacity: 0.8;
    }
    .card {
        margin: 0 auto; /* Added */
        float: none; /* Added */
        margin-bottom: 10px; /* Added */
}
    .square {
    height: 500px;
    width: 500px;
    }
    img {
    max-width: 100%;
    max-height: 100%;
}
#textLi{
        display: block;
        text-align: center;
        font-family: Verdana;
        font-size: 15px;
        color: white;
    }
</style>
<script>
    function copyLink() {
        var inputc = document.body.appendChild(document.createElement("input"));
        inputc.value = window.location.href;
        inputc.focus();
        inputc.select();
        document.execCommand('copy');
        inputc.parentNode.removeChild(inputc);
    }
 </script>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Pragma" content="no-cache">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <title>Formulario Indigo</title>
    </head>
    <body onunload="" style='background-color:<%=color%>'>
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark" id='nav1'>
            <a class="navbar-brand" href="./userforms.jsp" id='text'><img src ="../Resources/flag.png" height="100" width="100"   />Formulario Indigo</a>
            <li id="textLi"> <%=actualUser%> </li>
        </nav>
        <h3><%=form.getTittle()%></h5>
        <h5><%=form.getName()%></h4>
        
        <form method="post" action="../uploadData" enctype="multipart/form-data">
            <input type="hidden" name="data#######" value=<%=formId%>>
            <div id='viewNice' class="card bg-light mb-3" style="max-width: 40rem;">
                    <%for(Component component:components) {
                        String align="left";
                        String required = "";
                        
                        if(component.getRequired().equalsIgnoreCase("SI")){
                            required = "required";
                        }
                        
                        if(component.getAlign().equalsIgnoreCase("CENTRO")){
                            align="center";
                        }else if(component.getAlign().equalsIgnoreCase("IZQUIERDA")){
                            align="left";
                        }else if(component.getAlign().equalsIgnoreCase("DERECHA")){
                            align="right";
                        }else if(component.getAlign().equalsIgnoreCase("JUSTIFICAR")){
                            align="left";
                        }
                        %>
                    <div class="card-body">
                        <%
                        if(component.getClassName().equalsIgnoreCase("CAMPO_TEXTO")){
                            %>
                            <div class="card-header"><%=component.getVisibleText()%></div>
                            <div style="text-align:<%=align%>">
                           <input  type="text" id="<%=component.getCampName()%>;" <%=required%> name="<%=component.getCampName()%>" />
                            </div> 
                           <%
                        }else if(component.getClassName().equalsIgnoreCase("AREA_TEXTO")){
                            %>
                            <div class="card-header"><%=component.getVisibleText()%></div>
                            <div style="text-align:<%=align%>">
                            <textarea  rows="<%=component.getRows()%>" cols="<%=component.getCols()%>" name="<%=component.getCampName()%>" <%=required%>></textarea>
                            </div>
                            <%
                        }else if(component.getClassName().equalsIgnoreCase("CHECKBOX")){
                            %>
                            <div  class="card-header"><%=component.getVisibleText()%> </div>
                            <div style="text-align:<%=align%>">
                            <%
                                ArrayList<String> options = component.getOptionsAsList();
                                for(String text:options){
                                    %>
                                    <input type="checkbox" name="<%=component.getCampName()%>" value="text" <%=required%>><%=text%><br>
                                    <%
                                }
                            %>            
                            </div>
                            <%
                        }else if(component.getClassName().equalsIgnoreCase("RADIO")){
                            %>
                            <div class="card-header"><%=component.getVisibleText()%> </div>
                            <div style="text-align:<%=align%>">
                            <%
                                ArrayList<String> options = component.getOptionsAsList();
                                for(String text:options){
                                    %>
                                    <input  type="radio" name="<%=component.getCampName()%>" value="text" <%=required%>><%=text%> <br>
                                    <%
                                }
                            %>  
                            </div>
                            <%
                        }else if(component.getClassName().equalsIgnoreCase("FICHERO")){
                            %>
                            <div class="card-header"><%=component.getVisibleText()%></div>
                            <div style="text-align:<%=align%>">
                           <input  type="file" name="<%=component.getCampName()%> <%=required%>"/>
                            </div> 
                            <%
                        }else if(component.getClassName().equalsIgnoreCase("IMAGEN")){
                            %>
                            <div class="card-header"><%=component.getVisibleText()%> </div>
                            <div style="text-align:<%=align%>" class="square">
                            <img src="<%=component.getUrl()%>" alt="NO SE PUEDE CARGAR LA IMAGEN" ">
                            </div>
                            <%
                        }else if(component.getClassName().equalsIgnoreCase("COMBO")){
                            %>
                           
                            <div class="card-header"><%=component.getVisibleText()%> </div>
                            <div style="text-align:<%=align%>">
                            <select name="<%=component.getCampName()%>" <%=required%>>
                            <%
                                ArrayList<String> options = component.getOptionsAsList();
                                for(String text:options){
                                    %>
                                      <option><%=text%></option>
                                    <%
                                }
                            %>
                            </select>
                            </div>
                            <%
                        }else if(component.getClassName().equalsIgnoreCase("BOTON")){
                            if(form.getUserCreator().equalsIgnoreCase(actualUser)){
                            %> 
                            <div style="text-align:<%=align%>">
                            <button onclick="copyLink()" class="btn btn-outline-dark">Copiar Link</button>
                            </div>
                            <%
                            }else{
                            %>
                            <div style="text-align:<%=align%>">
                                <input style="text-align:<%=align%>" type='submit' class="btn btn-outline-success" value="<%=component.getVisibleText()%>" >
                            </div>
                            <%
                            }
                            %>
                            <%
                        }
                        %>
                    </div>
                        <%
                    }%>
            </div>
        </form>
        <%}else{%>
        <h5>ERROR 404 FORMULARIO NO ENCONTRADO</h5>
        <%}%>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
    </body> 
</html>