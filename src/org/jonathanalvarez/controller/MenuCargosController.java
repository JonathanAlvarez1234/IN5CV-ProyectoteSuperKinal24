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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jonathanalvarez.dao.Conexion;
import org.jonathanalvarez.dto.CargoDTO;
import org.jonathanalvarez.model.Cargo;
import org.jonathanalvarez.system.Main;

/**
 * FXML Controller class
 *
 * @author informatica
 */
public class MenuCargosController implements Initializable {
    private Main stage;
    
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    
    @FXML
    TableView tblCargos;
    @FXML
    TableColumn colCargoId, colNombreCargo,colDescripcion, TableView;
    @FXML
    Button btnAgregar, btnEditar, btnRegresar, btnEliminar;
    @FXML
    TextField tfCargoId, tfNombreCargo;
    @FXML
    TextArea taDescripcion;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnAgregar){
            stage.formClientesView(1);
        } else if(event.getSource()== btnEditar){
            CargoDTO.getCargoDTO().setCargo((Cargo)tblCargos.getSelectionModel().getSelectedItem());
            stage.formClientesView(2);
        }else if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnEliminar){
            int cliId = ((Cargo)tblCargos.getSelectionModel().getSelectedItem()).getCargoId();
            eliminarCliente(cliId);
            cargarLista();
        }else if(event.getSource() == btnBuscar){
            tblClientes.getItems().clear();
            if(tfClienteId.getText().equals("")){
                cargarLista();
            }else{
                tblCargo.getItems().add(buscarCargo());
                colCargoId.setCellValueFactory(new PropertyValueFactory<Cargo, Integer>("clienteId"));
                colNombreCargo.setCellValueFactory(new PropertyValueFactory<Cargo, String>("nombreCargo"));
                colDescripcion.setCellValueFactory(new PropertyValueFactory<Cargo, String>("descripcionCargo"));
            }
        }
    }
    
    public void cargarDatos(Cargo cargo){
        tfCargoId.setText(Integer.toString(cargo.getCargoId()));
        tfNombreCargo.setText(cargo.getNombreCargo());
        
    }
    
    public void agregarCliente(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarCargo(?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombre.getText());
            statement.setString(2, tfApellido.getText());
            statement.setString(3, tfTelefono.getText());
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(statement != null){
                    statement.close();
                }
                if(conexion != null){
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
    }
    
}
