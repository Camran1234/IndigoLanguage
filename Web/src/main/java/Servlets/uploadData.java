/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import WebSocket.dBIndigo;
import com.mycompany.formats.Component;
import com.mycompany.formats.Result;
import com.mycompany.formsafe.SafeReader;
import com.mycompany.formsafe.SafeWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author camran1234
 */
@WebServlet(name = "uploadData", urlPatterns = {"/uploadData"})
public class uploadData extends HttpServlet {

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
        String formId = (String)request.getAttribute("data#######");
        String user = (String)request.getSession().getAttribute("user");
        dBIndigo db = new dBIndigo(response.getWriter());
        
        ArrayList<Component> components =db.collectComponents(formId);
        ArrayList<String> nameCamps = new ArrayList<>();
        ArrayList<String> nameClass = new ArrayList<>();
        int index=0;
        if(user==null){
            user="Guest";
        }
        final long serialVersionUID = 1L;
        final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
        final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
        final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
        final String UPLOAD_DIRECTORY = formId;
        int fileIndex=0;
        // checks if the request actually contains upload file
        ArrayList<Result> results = new ArrayList<>(); 
        String path=null;
        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
 
        ServletFileUpload upload = new ServletFileUpload(factory);
         
        // sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);
         
        // sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);
 
        // constructs the directory path to store upload file
        // this path is relative to application's directory
        String uploadPath = getServletContext().getRealPath("file")
                + File.separator + UPLOAD_DIRECTORY;
         
        // creates the directory if it does not exist
        File uploadDir = new File(uploadPath);
        if (uploadDir.exists() == false) {
            uploadDir.mkdirs();
        }
        // redireccionamos a la pagina correspondiente segun el atributo establecido en la sesion
        try {
            // parses the request's content to extract file data
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    // processes only fields that are not form fields
                    //Para conseguir la path del archivo subido
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        path = filePath;
                        if(path !=null){
                            System.out.println("Agregando: "+"FICHERO");
                            System.out.println(item.getFieldName());
                            int file=0;
                            String campName="";
                            for(Component component:components){
                                if(component.getClassName().equalsIgnoreCase("FICHERO")){
                                    if(file==fileIndex-1){
                                        campName=component.getCampName();
                                    }else{
                                        file++;
                                    }
                                }
                            }
                            request.getSession().setAttribute("urlArchivo", path);
                            Result result = new Result(formId,campName, user, path);
                            results.add(result);
                            index++;
                        }
                        File storeFile = new File(filePath);
                        // saves the file on disk
                        if(storeFile.isFile())
                            item.write(storeFile);
                    } else if (item.isFormField()) {
                        String nombreInput = item.getFieldName();
                        String valorInput = item.getString();
                        if(nombreInput.equalsIgnoreCase("data#######")){
                            System.out.println("Entro aqui");
                            formId = valorInput;
                            components =db.collectComponents(formId);
                            for(Component component:components){
                                if(component.getClassName().equalsIgnoreCase("FICHERO")){
                                    fileIndex++;
                                }
                            }
                        }
                        System.out.println("Nombre: "+nombreInput);
                        System.out.println("Valor: "+valorInput);
                        for(Component component:components){
                            System.out.println("1");
                            if(component.getCampName().equals(nombreInput)){
                                System.out.println("2");
                                Result result;
                                switch(component.getClassName()){
                                    case "CAMPO_TEXTO":
                                        System.out.println("Agregando: "+component.getClassName());
                                        result = new Result(formId, nombreInput, user, valorInput);
                                        results.add(result);
                                        break;
                                        case "AREA_TEXTO":
                                            System.out.println("Agregando: "+component.getClassName());
                                        result = new Result(formId, nombreInput, user, valorInput);
                                        results.add(result);
                                        break;
                                        case "CHECKBOX":
                                            System.out.println("Agregando: "+component.getClassName());
                                        result = new Result(formId, nombreInput, user, valorInput);
                                        results.add(result);
                                        break;
                                        case "RADIO":
                                            System.out.println("Agregando: "+component.getClassName());
                                        result = new Result(formId, nombreInput, user, valorInput);
                                        results.add(result);
                                        break;
                                        case "COMBO":
                                            System.out.println("Agregando: "+component.getClassName());
                                        result = new Result(formId, nombreInput, user, valorInput);
                                        results.add(result);
                                        break;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Error en uploadData: "+ex.getMessage());
            response.getWriter().write("ERROR: "+ex.getMessage());
            ex.printStackTrace();
        }
        
        db.uploadNewDate(results);
        response.sendRedirect("./index.jsp");
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
