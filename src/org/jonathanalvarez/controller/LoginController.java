/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jonathanalvarez.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.jonathanalvarez.system.Main;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class LoginController implements Initializable {
    private Main stage;
    
    @FXML
    TextField tfUsuario;
    @FXML
    PasswordField tfPass;
    @FXML
    Button btnIniciar, btnRegistro;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
    
}
