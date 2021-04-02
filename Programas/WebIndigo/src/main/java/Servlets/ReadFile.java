/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import WebSocket.dBIndigo;
import com.mycompany.formats.BlockParameter;
import com.mycompany.formats.Component;
import com.mycompany.formats.Form;
import com.mycompany.formats.Parameter;
import com.mycompany.formats.Result;
import com.mycompany.formsafe.SafeReader;
import com.mycompany.handlers.ComponentCommands;
import com.mycompany.handlers.FormCommands;
import com.mycompany.handlers.ResultCommands;
import com.mycompany.handlers.UserCommands;
import com.mycompany.sqform.PackageResult;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author camran1234
 */
@WebServlet(name = "ReadFile", urlPatterns = {"/ReadFile"})
public class ReadFile extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ArrayList<BlockParameter> block = new ArrayList<>();
        ArrayList<Parameter> parameter = new ArrayList<>();
        try (PrintWriter out = response.getWriter()) {
            //Getting code of the form
            Stream<String> indigoCode = request.getReader().lines();
            String form = indigoCode.collect(Collectors.joining("\n"));
            //Reading the text
            parameter.add(new Parameter("Text","Verificando Archivo\n"));
            SafeReader reader = new SafeReader();
            reader.readFormsAsString(block, form);
            //Getting the parsed Commands
            FormCommands formCommands = reader.getFormCommands();
            ComponentCommands componentCommands = reader.getComponentCommands();
            UserCommands userCommands = new UserCommands();
            ResultCommands resultCommands = reader.getResultCommands();
            //We stablish the data that already exist in the database
            dBIndigo dbIndigo = new dBIndigo(block);
                //add the new Data
                //We upload the new Data
            parameter.add(new Parameter("Text","Cagando Archivos\n"));
            dbIndigo.newRequest(formCommands, userCommands,componentCommands, block);
            parameter.add(new Parameter("Text","Actualizando Archivos\n"));
            if(!reader.getErrorCommands().haveErrors()){
                if(resultCommands.getResults()!=null){
                dbIndigo.uploadNewDate(resultCommands.getResults());
                }else{
                    dbIndigo.uploadNewDate(null);
                }
            }
            parameter.add(new Parameter("Text","Fin Archivo\n"));
            parameter.add(new Parameter("Text","Se Agrego correctamente el archivo\n"));
            block.add(new BlockParameter(parameter));
            PackageResult result = new PackageResult();
            result.printInfoAsBlock(block, out);
            out.flush();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
