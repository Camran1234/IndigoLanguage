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
import com.mycompany.formats.Result;
import com.mycompany.formsafe.SafeWriter;
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
@WebServlet(name = "CreateFile", urlPatterns = {"/CreateFile"})
public class CreateFile extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ArrayList<BlockParameter> block = new ArrayList<>();
            dBIndigo db = new dBIndigo(block);
            Stream<String> indigoCode = request.getReader().lines();
            String text = indigoCode.collect(Collectors.joining());
            String[] spliText = text.split("\\|");
            String path = spliText[1];
            String formName = spliText[0];
            Form form=null;
            
            for(Form newForm:db.getForms()){
                if(newForm.getId().equals(formName)){
                    form = newForm;
                }
            }
            
            if(form!=null){
                ArrayList<Component> components = db.collectComponents(formName);
                ArrayList<Result> results = db.collectResults(formName);
                ArrayList<Form> formList = new ArrayList<>();
                formList.add(form);
                SafeWriter safeWriter = new SafeWriter();
                String code = safeWriter.WriteFormAsString(formList, components, results);
                block.add(new BlockParameter("File",code));
                out.write(code);
            }
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
