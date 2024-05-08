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
    TableColumn colCargoId, colNombreCargo,colDescripcionCargo, TableView;
    @FXML
    Button btnAgregar, btnEditar, btnRegresar, btnEliminar, btnBuscar;
    @FXML
    TextField tfCargoId;
    
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
            stage.formCargosView(1);
        } else if(event.getSource()== btnEditar){
            CargoDTO.getCargoDTO().setCargo((Cargo)tblCargos.getSelectionModel().getSelectedItem());
            stage.formCargosView(2);
        }else if(event.getSource() == btnRegresar){
            stage.menuPrincipalView();
        }else if(event.getSource() == btnEliminar){
            int cliId = ((Cargo)tblCargos.getSelectionModel().getSelectedItem()).getCargoId();
            eliminarCliente(cliId);
            cargarLista();
        }else if(event.getSource() == btnBuscar){
            tblCargos.getItems().clear();
            if(tfCargoId.getText().equals("")){
                cargarLista();
            }else{
                tblCargos.getItems().add(buscarCargo());
                colCargoId.setCellValueFactory(new PropertyValueFactory<Cargo, Integer>("cargoId"));
                colNombreCargo.setCellValueFactory(new PropertyValueFactory<Cargo, String>("nombreCargo"));
                colDescripcionCargo.setCellValueFactory(new PropertyValueFactory<Cargo, String>("descripcionCargo"));
            }
        }
    }
    
    public void cargarLista(){
        tblCargos.setItems(listarCargos());
        colCargoId.setCellValueFactory(new PropertyValueFactory<Cargo, Integer>("cargoId"));
        colNombreCargo.setCellValueFactory(new PropertyValueFactory<Cargo, String>("nombreCargo"));
        colDescripcionCargo.setCellValueFactory(new PropertyValueFactory<Cargo, String>("descripcionCargo"));
    }
    
    public ObservableList<Cargo> listarCargos(){
        ArrayList<Cargo> cargos = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarCargos()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            
            while(resultSet.next()){
                int cargoId = resultSet.getInt("cargoId");
                String nombreCargo = resultSet.getString("nombreCargo");
                String descripcionCargo = resultSet.getString("descripcionCargo");
                
                cargos.add(new Cargo(cargoId, nombreCargo, descripcionCargo));
            }
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
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
    
    public void eliminarCliente(int cliId){
        try{
                conexion = Conexion.getInstance().obtenerConexion();
                String sql = "call sp_eliminarCliente(?)";
                statement = conexion.prepareStatement(sql);
                statement.setInt(1, cliId);
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
            String sql = "call sp_buscarCliente(?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfCargoId.getText()));
            resultSet = statement.executeQuery();
            
            if(resultSet.next()){
                    int cargoId = resultSet.getInt("cargoId");
                    String nombreCargo = resultSet.getString("nombreCargo");
                    String descripcionCargo = resultSet.getString("descripcionCargo");
                    
                    cargo = (new Cargo(cargoId, nombreCargo, descripcionCargo));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if(resultSet != null){
                    resultSet.close();
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
    
}
