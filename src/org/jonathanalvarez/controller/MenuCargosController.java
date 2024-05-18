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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
 * @author HP
 */
public class MenuCargosController implements Initializable {
    private Main stage;
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultset = null;
    
    @FXML
    TextField tfNombreCargo, tfCargoId;
    
    @FXML
    TextArea taDescripcion;
    
    @FXML
    TableColumn colCargoId, colNombre, colDescripcion;
    
    @FXML
    TableView tblCargos;
    
    @FXML
    Button btnAgregar, btnRegresar, btnEliminar, btnEditar, btnBuscar;
    
    @FXML
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnAgregar){
            agregarCargo();
            vaciarCampos();
            cargarLista();
        }else if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnEditar){
            CargoDTO.getCargoDTO().setCargo((Cargo)tblCargos.getSelectionModel().getSelectedItem());
            stage.formCargosView();
        }else if(event.getSource() == btnEliminar){
            int carId = ((Cargo)tblCargos.getSelectionModel().getSelectedItem()).getCargoId();
            eliminarCargo(carId);
            cargarLista();
        }else if (event.getSource() == btnBuscar){
            tblCargos.getItems().clear();
            if(tfCargoId.getText().equals("")){
                cargarLista();
            }else{
                tblCargos.getItems().add(buscarCargo());
                colCargoId.setCellValueFactory(new PropertyValueFactory<Cargo, Integer>("cargoId"));
                colNombre.setCellValueFactory(new PropertyValueFactory<Cargo, String>("nombreCargo"));
                colDescripcion.setCellValueFactory(new PropertyValueFactory<Cargo, String>("descripcionCargo"));
            }
        }
        
    }
    
    public ObservableList<Cargo> listarCargos(){
        ArrayList<Cargo> cargos = new ArrayList<>();
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarCargo()";
            statement = conexion.prepareStatement(sql);
            resultset = statement.executeQuery();
            
            while(resultset.next()){
                int cargoId = resultset.getInt("cargoId");
                String nombreCargo = resultset.getString("nombreCargo");
                String descripcion = resultset.getString("descripcionCargo");
                
                cargos.add(new Cargo(cargoId, nombreCargo, descripcion));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultset != null){
                    resultset.close();
                }
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
        return FXCollections.observableList(cargos);
    }
    
    public void cargarLista(){
        tblCargos.setItems(listarCargos());
        colCargoId.setCellValueFactory(new PropertyValueFactory<Cargo, Integer>("cargoId"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Cargo, String>("nombreCargo"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<Cargo, String>("descripcionCargo"));
    }
    
     public void agregarCargo(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarCargo(?, ?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombreCargo.getText());
            statement.setString(2, taDescripcion.getText());
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
                e.printStackTrace();
            }
        }
    }
     
     
    public void vaciarCampos(){
        tfNombreCargo.clear();
        taDescripcion.clear();
        
    }
     
    public void eliminarCargo(int carId){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_eliminarCargo(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, carId);
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
    
    public Cargo buscarCargo(){
        Cargo cargo = null;
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_buscarCargo(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1 ,Integer.parseInt(tfCargoId.getText()));
            resultset = statement.executeQuery();
            
            if(resultset.next()){
                int cargoId = resultset.getInt("cargoId");
                String nombreCargo = resultset.getString("nombreCargo");
                String descripcionCargo = resultset.getString("descripcionCargo");
                
                cargo = (new Cargo(cargoId, nombreCargo, descripcionCargo));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultset != null){
                    resultset.close();
                }
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
        
        return cargo;
    }  
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }
      
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarLista();
    }  
    
}
