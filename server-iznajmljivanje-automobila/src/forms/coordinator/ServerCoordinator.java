/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forms.coordinator;

import forms.KonfiguracijaBazeForm;
import forms.MainForm;
import forms.controller.KonfiguracijaBazeController;
import forms.controller.MainController;

/**
 *
 * @author Filip
 */
public class ServerCoordinator {
    
    private static ServerCoordinator instance;
    private final MainController mainController;

    private ServerCoordinator() {
        mainController = new MainController(new MainForm());
    }

    public static ServerCoordinator getInstance() {
        if (instance == null) {
            instance = new ServerCoordinator();
        }
        return instance;
    }

    public void openSettingsForm() {
        KonfiguracijaBazeController konfiguracijaBazeController = new KonfiguracijaBazeController(new KonfiguracijaBazeForm(mainController.getFrmMain(), true));
        konfiguracijaBazeController.openForm();
    }

    public void openMainForm() {
        mainController.openForm();
    }

    public MainController getMainController() {
        return mainController;
    }
}
