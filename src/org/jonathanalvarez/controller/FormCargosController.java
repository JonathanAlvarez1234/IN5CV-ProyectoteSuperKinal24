/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jonathanalvarez.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.jonathanalvarez.dao.Conexion;
import org.jonathanalvarez.dto.CargoDTO;
import org.jonathanalvarez.model.Cargo;
import org.jonathanalvarez.system.Main;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FormCargosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultset = null;
    
    @FXML
    TextField tfNombreCargo, tfCargoId;
    
    @FXML
    TextArea taDescripcion;
    
    @FXML
    Button btnCancelar, btnAgregar;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(CargoDTO.getCargoDTO().getCargo() != null){
            cargarDatos(CargoDTO.getCargoDTO().getCargo());
        }
    }    
    
    public void editarCargo(){
         try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_editarCargo(?, ?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfCargoId.getText()));
            statement.setString(2, tfNombreCargo.getText());
            statement.setString(3, taDescripcion.getText());
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(conexion != null){
                conexion.close();
                }
                if(statement != null){
                    statement.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
    public void cargarDatos(Cargo  cargo){
    tfCargoId.setText(Integer.toString(cargo.getCargoId()));
    tfNombreCargo.setText(cargo.getNombreCargo());
    taDescripcion.setText(cargo.getDescripcionCargo());
    }
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnCancelar){
            stage.menuCargosView();
        }else if(event.getSource() == btnAgregar){
            editarCargo();
            CargoDTO.getCargoDTO().setCargo(null);
            stage.menuCargosView();
        }
    }
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
    
}
