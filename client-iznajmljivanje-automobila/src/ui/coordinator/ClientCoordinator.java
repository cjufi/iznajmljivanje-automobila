package ui.coordinator;

import java.util.HashMap;
import java.util.Map;
import ui.controller.AutomobilController;
import ui.controller.KlijentController;
import ui.controller.LoginController;
import ui.controller.MainController;
import ui.controller.PretragaAutomobilaController;
import ui.controller.PretragaKlijentaController;
import ui.controller.PretragaUverenjaController;
import ui.controller.StavkaController;
import ui.controller.UverenjeController;
import ui.forms.AutomobilForm;
import ui.forms.KlijentForm;
import ui.forms.LoginForm;
import ui.forms.MainForm;
import ui.forms.PretragaAutomobilaForm;
import ui.forms.PretragaKlijentaForm;
import ui.forms.PretragaUverenjaForm;
import ui.forms.StavkaAutaForm;
import ui.forms.UverenjeForm;
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
    
    public void openAddAutomobilForm() {
        AutomobilController automobilController = new AutomobilController(new AutomobilForm(mainController.getFrmMain(), true));
        automobilController.openForm(FormMode.ADD);
    }
    
    public void openPretragaAutomobilForm() {
        PretragaAutomobilaController pretragaAutomobilaController = new PretragaAutomobilaController(new PretragaAutomobilaForm(mainController.getFrmMain(), true));
        pretragaAutomobilaController.openForm();
    }

    public void openEditAutomobilForm(PretragaAutomobilaForm frmPretragaAutomobil) {
        AutomobilController automobilController = new AutomobilController(new AutomobilForm(frmPretragaAutomobil, true));
        automobilController.openForm(FormMode.EDIT);
    }
    
    public void openAddUverenjeForm() {
        UverenjeController uverenjeController = new UverenjeController(new UverenjeForm(mainController.getFrmMain(), true));
        uverenjeController.openForm(FormMode.ADD);
    }

    public void openPretragaUverenjaForm() {
        PretragaUverenjaController pretragaUverenjaController = new PretragaUverenjaController(new PretragaUverenjaForm(mainController.getFrmMain(), true));
        pretragaUverenjaController.openForm();
    }

    public void openEditUverenjeForm(PretragaUverenjaForm frmPretragaUverenja) {
        UverenjeController uverenjeController = new UverenjeController(new UverenjeForm(frmPretragaUverenja, true));
        uverenjeController.openForm(FormMode.EDIT);
    }
    
    public void openAddStavkuForm() {
        StavkaController stavkaController = new StavkaController(new StavkaAutaForm(mainController.getFrmMain(), true));
        stavkaController.openForm(FormMode.ADD);
    }
}
