/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebSocket;

import com.mycompany.formats.*;
import com.mycompany.formsafe.SafeReader;
import com.mycompany.formsafe.SafeWriter;
import com.mycompany.handlers.ComponentCommands;
import com.mycompany.handlers.FormCommands;
import com.mycompany.handlers.ResultCommands;
import com.mycompany.handlers.UserCommands;
import com.mycompany.indigo.Analysis;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author camran1234
 */
public class dBIndigo {
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Form> forms = new ArrayList<>();
    ArrayList<Component> components = new ArrayList<>();
    private SafeReader safeReader = new SafeReader();
    
    
    
    
    public dBIndigo(PrintWriter out){
        setExistingParameters(out);
    }
    
    public dBIndigo (ArrayList<BlockParameter> block){
        setExistingParameters(block);
    }
    
    public ArrayList<Form> getForms(){
        if(forms!=null){
            return forms;
        }else{
            return null;
        }
    }
    
    /**
     * Return a list of forms of a specify user
     * @param user
     * @return 
     */
    public ArrayList<Form> collectForms(String user){
        ArrayList<Form> aux = new ArrayList<>();
        for(Form form:forms){
            if(form.getUserCreator().equals(user)){
                aux.add(form);
            }
        }
        return aux;
        
    }
    
    public ArrayList<Component> getComponents(){
        return components;
    }
    
    /**
     * Return the components of a specify form
     * @param user
     * @return 
     */
    public ArrayList<Component> collectComponents(String formId){
        ArrayList<Component> aux = new ArrayList<>();
        for(Component component:components){
            if(component.getFormName().equals(formId)){
                aux.add(component);
            }
        }
        return aux;
    }
    
    public ArrayList<Result> collectResults(String formId){
        ArrayList<Result> aux = new ArrayList<>();
        ArrayList<Result> results = safeReader.getResultCommands().getResults();
        for(Result result:results){
            if(result.getIdForm().equals(formId)){
                aux.add(result);
            }
        }   
        return aux;
    }
    
     private void setExistingParameters(ArrayList<BlockParameter> block){
        try {
            checkIfExist();
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
            ex.printStackTrace();
        }
        safeReader.readData(block);
        users = safeReader.getUsers();
        forms = safeReader.getForms();
        components = safeReader.getComponents();
     }
    
    /**
     * Method to get the db parameters
     * @param out 
     */
    private void setExistingParameters(PrintWriter out){
        try {
            checkIfExist();
        } catch (IOException ex) {
            System.out.println("Error: "+ex.getMessage());
            ex.printStackTrace();
        }
        safeReader.readData(out);
        users = safeReader.getUsers();
        forms = safeReader.getForms();
        components = safeReader.getComponents();
        
    }
    
    private void checkIfExist() throws IOException{
        /*Resources/form.sqf
                Resources/user.sqf*/
        //Check if the Resources folder Exists
        File file = new File("Resources");
        if(!file.exists() && !file.isDirectory()){
            file.mkdir();
        }
        //check if form.sqf exists
        file = new File("Resources/form.sqf");
        if(!file.exists()){
            file.createNewFile();
        }
        file = new File("Resources/user.sqf");
        if(!file.exists()){
            file.createNewFile();
        }
    }
    
    /**
     * Sign up for online, only return true or false
     * @param user
     * @param password
     * @return 
     */
    public boolean singUpOnline(String user, String password){
        for(User user1:users){
            if(user1.getUser().equals(user) && user1.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * Try to signUp
     * @param user
     * @param password
     * @return 
     */
    public boolean singUp(String user, String password, ArrayList<BlockParameter> out){
        for(User user1:users){
            if(user1.getUser().equals(user) && user1.getPassword().equals(password)){
                System.out.println("Logeado Como");
                new Analysis().setLoggedUser(user);
                out.add(new BlockParameter("Text","Logeado como Usuario:"+user+"\n"));
                return true;
            }
        }
        return false;
    }
    
    public void newRequest(FormCommands formCommands, UserCommands userCommands, ComponentCommands componentCommands, ArrayList<BlockParameter> out){
        try {
            
            ArrayList<User> newUsers=userCommands.getUserList();
            ArrayList<User> modifyUsers=userCommands.getModifyList();
            ArrayList<String> deleteUsers=userCommands.getDeleteList();
            ArrayList<Form> newForms=formCommands.getCreateList();
            ArrayList<Form> modifyForms=formCommands.getModifyList();
            ArrayList<String> deleteForms=formCommands.getDeleteList();
            ArrayList<Component> newComponents = componentCommands.getNewComponents();
            ArrayList<Component> modifyComponents = componentCommands.getModifyComponents();
            ArrayList<String> deleteComponents = componentCommands.getDeleteList();
            ArrayList<String> formDeleteComponents = componentCommands.getFormDeleteList();
            ArrayList<Parameter> parametersWarning = new ArrayList<>();
            ArrayList<Parameter> parametersText = new ArrayList<>();
            //Adding the components
            for(Component component:newComponents){
                boolean founded=false;
                for(Component existComponent:components){
                    String componentId = component.getId();
                    String componentForm = component.getFormName();
                    if(existComponent.getId().equals(componentId) && existComponent.getFormName().equals(componentForm)){
                        founded=true;
                        break;
                    }
                }
                if(founded){
                    parametersWarning.add(new Parameter("Warning","Ya existe el Componente: "+component.getId()+", en formulario: "+component.getFormName()+", no se puede agregar, cambiar id o formulario"));
                }else{
                    components.add(component);
                }
            }
            
            
            //Modify Component
            for(Component requestComponent:modifyComponents){
                boolean founded=false;
                for(Component component:components){
                    String oldId = requestComponent.getId();
                    String oldForm = requestComponent.getFormName();
                    String newId = component.getId();
                    String newForm = component.getFormName();
                    if(oldId.equals(newId) && oldForm.equals(newForm)){
                        //We found the Component
                        founded=true;
                        component.setMode(true);
                        if(requestComponent.getClassName()!=null)
                        component.setClassName(requestComponent.getClassName());
                        if(requestComponent.getAlign()!=null)
                        component.setAlign(requestComponent.getAlign());
                        if(requestComponent.getCampName()!=null)
                        component.setCampName(requestComponent.getCampName());
                        if(requestComponent.getClassName()!=null)
                        component.setClassName(requestComponent.getClassName());
                        if(requestComponent.getFormName()!=null)
                        component.setFormName(requestComponent.getFormName());
                        if(requestComponent.getVisibleText()!=null)
                        component.setVisibleText(requestComponent.getVisibleText());
                        if(requestComponent.getRequired()!=null)
                        component.setRequired(requestComponent.getRequired());
                        if(requestComponent.getOptions()!=null)
                        component.setOptions(requestComponent.getOptions());
                        if(component.getIndex()!=null){
                            try {
                                //Index required for the modification
                                int pot = Integer.parseInt(component.getIndex());
                                int indexComponent;
                                components.remove(component);
                                ArrayList<Component> aux = new ArrayList<>();
                                //get List
                                for(Component newComponent:components){
                                    if(newComponent.getFormName().equals(component.getFormName())){
                                        aux.add(newComponent);
                                        components.remove(newComponent);
                                    }
                                }
                                //Index of the component
                                indexComponent = aux.indexOf(component);
                                if(pot<=aux.size() && pot>=0){
                                    //Remove the already existing component
                                    aux.remove(component);
                                    //Push the component
                                    if(pot<indexComponent){
                                        aux.add(pot-1, component);
                                    }else if(pot >= indexComponent){
                                        aux.add(pot,component);
                                    }
                                    //Print again
                                    for(Component newComponent:aux){
                                        components.add(newComponent);
                                    }
                                }else{
                                    throw new Exception("Index out of bounds in component modify index request");
                                }
                                } catch (Exception e) {
                                    System.out.println("Error:"+e.getMessage());
                                    e.printStackTrace();
                                }
                        }
                        component.setMode(false); 
                        break;
                    }
                }
                //If we haven't found the user
                if(!founded){
                    parametersWarning.add(new Parameter("Warning","No se encontro ningun Componente: "+requestComponent.getId()+" en formulario: "+requestComponent.getFormName()+" para modificarlo, ignorando..."));
                }
            }
            
            //Delete Component
            for(int indexDelete=0; indexDelete<deleteComponents.size(); indexDelete++){
                boolean founded=false;
                String idDelete = deleteComponents.get(indexDelete);
                String idForm = formDeleteComponents.get(indexDelete);
                for(Component component:components){
                    if(idDelete.equals(component.getId()) && idForm.equals(component.getFormName())){
                        founded=true;
                        components.remove(component);
                        parametersText.add(new Parameter("Text: ","Eliminando componente: "+idDelete+", formulario "+idForm));
                    }
                }
                if(!founded){
                    parametersWarning.add(new Parameter("Warning","No se encontro ningun Componente: "+idDelete+" de Formulario: "+idForm+" para eliminarlo, ignorando..."));
                }
            }
            
            //Adding the new Users
            for(User user:newUsers){
                boolean founded=false;
                for(User existUser:users){
                    //Condition to prevent repeated ID
                    String userUser = user.getUser();
                    if(existUser.getUser().equalsIgnoreCase(userUser)){
                       founded=true; 
                       break;
                    }
                }
                if(founded){
                    parametersWarning.add(new Parameter("Warning","Ya existe el Usuario: "+user.getUser()+", no se puede agregar"));
                }else{
                    users.add(user);
                }
            }
            
            //Modifying Users that already exist
            for(User requestUser:modifyUsers){
                boolean founded=false;
                for(User user:users){
                    String oldUser = user.getUser();
                    String checkUser = requestUser.getPastUser();
                    if(oldUser.equalsIgnoreCase(checkUser)){
                        //We found the user
                        founded=true;
                        user.setMode(true);
                        user.setUser(requestUser.getNewUser());
                        user.setPassword(requestUser.getPassword());
                        user.setDate(requestUser.getDate());
                        user.setMode(false);
                    }
                }
                //If we haven't found the user
                if(!founded){
                    parametersWarning.add(new Parameter("Warning","No se encontro ningun USUARIO: "+requestUser.getPastUser()+" para modificarlo, ignorando..."));
                }
            }
            //Deleting users
            for(String idDelete:deleteUsers){
                boolean founded=false;
                for(User user:users){
                    if(user.getUser().equals(idDelete)){
                        founded=true;
                        users.remove(user);
                    }
                }
                if(!founded){
                    parametersWarning.add(new Parameter("Warning","No se encontro ningun USUARIO: "+idDelete+" para eliminarlo, ignorando..."));
                }
            }

            //Adding the new Forms
            for(Form form:newForms){
                boolean founded = false;
                for(Form existForm:forms){
                    String idForm = existForm.getId();
                    if(idForm.equalsIgnoreCase(form.getId())){
                       founded=true; 
                       break;
                    }
                }
                if(founded){
                    parametersWarning.add(new Parameter("Warning"," No se pudo agregar el formulario "+form.getId()+", ya existe"));
                }else{
                    if(form.getUserCreator()==null){
                        if(new Analysis().getLoggedUser()!=null){
                            form.setUser(new Analysis().getLoggedUser());
                            forms.add(form);
                        }else{
                            parametersWarning.add(new Parameter("Warning","No se pudo agregar el formulario, porque el sistema no sabe a quien le pertenece el formulario\n"
                                    + ", Iniciar Sesion para crear formulario"));
                        }
                    }else{
                        forms.add(form);
                    }
                }
            }
            //Modifying Forms that already exist
            for(Form requestForm:modifyForms){
                boolean founded=false;
                for(Form form:forms){
                    if(form.getId().equalsIgnoreCase(requestForm.getId())){
                        founded=true;
                        form.setMode(true);
                        form.setName(requestForm.getName());
                        form.setTittle(requestForm.getTittle());
                        form.setTopic(requestForm.getTopic());
                        form.setDate(requestForm.getDate());
                        form.setMode(false);
                    }
                }
                if(!founded){
                    parametersWarning.add(new Parameter("Warning","No se encontro el formulario con id: "+requestForm.getId()+", no se pudo modificar..."));
                }
            }

            //Delete Forms
            for(String idDelete:deleteForms){
                boolean founded = false;
                for(Form form:forms){
                    if(form.getId().equalsIgnoreCase(idDelete)){
                        founded=true;
                        forms.remove(form);
                    }
                }
                if(!founded){
                    parametersWarning.add(new Parameter("Warning","  No se pudo eliminar el formulario con id:"+idDelete+", no se encuentra en el sistema"));
                }
            }
            
            if(parametersWarning.size()>0){
                out.add(new BlockParameter(parametersWarning));
            }
            if(parametersText.size()>0){
                out.add(new BlockParameter(parametersText));
            }
            
        } catch (Exception e) {
            System.out.println("Error en dbIndigo: "+e.getMessage());
            e.printStackTrace();
        }
        
    }
    
    

    public ArrayList<Result> getResults(){
        return safeReader.getResultCommands().getResults();
    }
    
    /**
     * Upload the new Data getted, and update the files 
     */
    public void uploadNewDate(ArrayList<Result> newResults) {
        try {
            SafeWriter safeWriter = new SafeWriter();
            safeWriter.WriteUser(users);
            ResultCommands auxResult = safeReader.getResultCommands();
            ArrayList<Result> results;
            if(auxResult!=null){
                results = auxResult.getResults();
            }else{
                results = new ArrayList<>();
            }
            boolean addRepeated=false;
            if(newResults!=null){
                for(Result addResult:newResults){
                    addRepeated=false;
                    for(Result result:results){
                        if(addResult.getIdForm().equalsIgnoreCase(result.getIdForm()) && addResult.getNameCamp().equalsIgnoreCase(result.getNameCamp())){
                            addRepeated=true;
                            result.addAnswers(addResult.getAnswers());
                            break;
                        }
                    }
                    if(!addRepeated){
                        results.add(addResult);
                    }
                }
            }
            System.out.println("Actualizando datos");
            if(!safeReader.haveErrors()){
                if(forms!=null || components!=null || results!=null){            
                    safeWriter.WriteForm(forms,components, results);    
                }
            }
        } catch (Exception e) {
            System.out.println("Error en dbIndigo, uploadNewDate: "+e.getMessage());
            e.printStackTrace();
        }
    }
    
}
