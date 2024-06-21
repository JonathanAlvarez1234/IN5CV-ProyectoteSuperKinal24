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
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.jonathanalvarez.dao.Conexion;
import org.jonathanalvarez.model.Empleado;
import org.jonathanalvarez.model.NivelAcceso;
import org.jonathanalvarez.system.Main;
import org.jonathanalvarez.utilis.PasswordUtils;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class FormUsuarioController implements Initializable {

    @FXML
    TextField tfUsuario, tfPass;
    @FXML
    ComboBox cmbEmpleados, cmbNivel;
    @FXML
    Button btnRegresar, btnRegistrar, btnEmpleados;
    private Main stage;
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbEmpleados.setItems(listarEmpleado());
        cmbNivel.setItems(listarNivelesAcceso());
    }    
    
    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == btnRegistrar){
            agregarUsuario();
            stage.loginView();
        }else if(event.getSource() == btnEmpleados){
            stage.menuEmpleadosView();
        }else if(event.getSource() == btnRegresar){
            stage.loginView();
        }
    }
    public ObservableList<Empleado> listarEmpleado() {
        ArrayList<Empleado> empleados = new ArrayList<>();

        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarEmpleado()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int empleadoId = resultSet.getInt("empleadoId");
                String nombre = resultSet.getString("nombreEmpleado");
                String apellido = resultSet.getString("apellidoEmpleado");
                Double sueldo = resultSet.getDouble("Sueldo");
                Time horaE = resultSet.getTime("horaEntrada");
                Time horaS = resultSet.getTime("horaSalida");
                String cargo = resultSet.getString("Cargo");
                String encargado = resultSet.getString("Encargado");
                empleados.add(new Empleado(empleadoId, nombre, apellido, sueldo, horaE, horaS, cargo, encargado));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
        }
        return FXCollections.observableList(empleados);
    }
    
    public ObservableList<NivelAcceso>listarNivelesAcceso(){
        ArrayList <NivelAcceso>nivelesAcceso = new ArrayList<>();
        
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarNivelAcceso()";
            statement = conexion.prepareStatement(sql);
            resultSet  = statement.executeQuery();
            while(resultSet.next()){
                int nivelAccesoId = resultSet.getInt("NivelAccesoId");
                String nivelAcceso = resultSet.getString("NivelAcceso");
                nivelesAcceso.add(new NivelAcceso(nivelAccesoId, nivelAcceso));
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try{
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return FXCollections.observableArrayList(nivelesAcceso);
    }
    
    public void agregarUsuario(){
        try{
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarUsuario(?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfUsuario.getText());
            statement.setString(2, PasswordUtils.getInstance().encryptedPassword(tfPass.getText()));
            statement.setInt(3, ((NivelAcceso)cmbNivel.getSelectionModel().getSelectedItem()).getNivelAccesoId());
            statement.setInt(4, ((Empleado)cmbEmpleados.getSelectionModel().getSelectedItem()).getEmpleadoId());
            statement.execute();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }finally{
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());

            }
        }
    }
    
    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }  
    
}
