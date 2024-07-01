/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.coordinator;

import java.util.HashMap;
import java.util.Map;
import ui.controller.KlijentController;
import ui.controller.LoginController;
import ui.controller.MainController;
import ui.controller.PretragaKlijentaController;
import ui.forms.KlijentForm;
import ui.forms.LoginForm;
import ui.forms.MainForm;
import ui.forms.PretragaKlijentaForm;
import ui.forms.util.FormMode;

/**
 *
 * @author Filip
 */
public class ClientCoordinator {
    
    private static ClientCoordinator instance;
    private final MainController mainController;
    private final Map<String, Object> params;
    
    public ClientCoordinator() {
        mainController = new MainController(new MainForm());
        params = new HashMap<>();
    }
    
    public static ClientCoordinator getInstance(){
        if(instance==null){
            instance= new ClientCoordinator();
        }
        return instance;
    }

    public void addParam(String name, Object key) {
        params.put(name, key);
    }

    public Object getParam(String name) {
        return params.get(name);
    }

    public MainController getMainController() {
        return mainController;
    }
    
    public void openLoginForm() {
        LoginController loginContoller = new LoginController(new LoginForm());
        loginContoller.openForm();
    }
 
    public void openMainForm() {
        mainController.openForm();
    }
    
    public void openAddKlijentForm() {
        KlijentController klijentController = new KlijentController(new KlijentForm(mainController.getFrmMain(), true));
        klijentController.openForm(FormMode.ADD);
    }
    
    public void openPretragaKlijentForm() {
        PretragaKlijentaController pretragaKlijentaController = new PretragaKlijentaController(new PretragaKlijentaForm(mainController.getFrmMain(), true));
        pretragaKlijentaController.openForm();
    }

    public void openEditKlijentForm(PretragaKlijentaForm frmPretragaKlijenta) {
        KlijentController klijentController = new KlijentController(new KlijentForm(frmPretragaKlijenta, true));
        klijentController.openForm(FormMode.EDIT);
    }
}
