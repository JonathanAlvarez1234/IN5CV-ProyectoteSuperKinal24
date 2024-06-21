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
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.jonathanalvarez.dao.Conexion;
import org.jonathanalvarez.model.Cargo;
import org.jonathanalvarez.model.Empleado;
import org.jonathanalvarez.system.Main;
import org.jonathanalvarez.utilis.SuperKinalAlert;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class MenuEmpleadosController implements Initializable {
    private Main stage;
    private int op;
    private static Connection conexion = null;
    private static PreparedStatement statement = null;
    private static ResultSet resultSet = null;
    
    
    @FXML
    TableView tblEmpleados;
    @FXML
    TableColumn colEmpleadoId, colNombre, colApellido, colSueldo, colHoraEntrada, colHoraSalida, colCargo, colEncargado;
    @FXML
    TextField tfEmpleadoId, tfNombre, tfApellido, tfSueldo, tfHoraEntrada, tfHoraSalida;;
    @FXML
    ComboBox cmbEncargado, cmbCargo;
    @FXML
    Button btnGuardar, btnVaciar, btnRegresar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbCargo.setItems(listarCargos());
        cmbEncargado.setItems(listarEmpleado());
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnRegresar) {
            if (op == 2) {
                stage.menuPrincipalView();

            } else {
                stage.formUsuarioView();
            }

        } else if (event.getSource() == btnVaciar) {
            vaciarCampos();
        } else if (event.getSource() == btnGuardar) {
            if (tfEmpleadoId.getText().equals("")) {
                if (op == 3) {
                    agregarEmpleado();
                    cmbEncargado.setItems(listarEmpleado());
                    cargarDatos();
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(401);
                    stage.formUsuarioView();
                } else {
                    agregarEmpleado();
                    cmbEncargado.setItems(listarEmpleado());
                    cargarDatos();
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(401);
                }
            } else {
                if (!tfHoraEntrada.getText().isEmpty() && !tfHoraSalida.getText().isEmpty() && !tfSueldo.getText().isEmpty() && cmbEncargado.getSelectionModel().getSelectedItem() != null && cmbCargo.getSelectionModel().getSelectedItem() != null) {
                    if (SuperKinalAlert.getInstance().mostrarAlertaConfirmacion(106).get() == ButtonType.OK) {
                        editarEmpleado();
                        cargarDatos();
                    }
                } else {
                    SuperKinalAlert.getInstance().mostrarAlertaInfo(400);
                }
            }
        }
    }

    public void cargarDatosEditar() {
        Empleado e = (Empleado) tblEmpleados.getSelectionModel().getSelectedItem();
        if (e != null) {
            tfEmpleadoId.setText(Integer.toString(e.getEmpleadoId()));
            tfNombre.setText(e.getNombre());
            tfApellido.setText(e.getApellido());
            tfSueldo.setText(Double.toString(e.getSueldo()));
            tfHoraEntrada.setText(e.getHoraEntrada().toString());
            tfHoraSalida.setText(e.getHoraSalida().toString());
            cmbEncargado.getSelectionModel().select(obtenerIndexEncargado());
            cmbCargo.getSelectionModel().select(obtenerIndexCargo());
        }
    }

    public int obtenerIndexEncargado() {
        int index = 0;
        for (int i = 0; i < cmbEncargado.getItems().size(); i++) {
            String encargadoCmb = cmbEncargado.getItems().get(i).toString();
            String encargadoTbl = ((Empleado) tblEmpleados.getSelectionModel().getSelectedItem()).getEncargado();
            if (encargadoCmb.equals(encargadoTbl)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public int obtenerIndexCargo() {
        int index = 0;
        for (int i = 0; i < cmbCargo.getItems().size(); i++) {
            String cargoCmb = cmbEncargado.getItems().get(i).toString();
            String cargoTbl = ((Empleado) tblEmpleados.getSelectionModel().getSelectedItem()).getCargo();
            if (cargoCmb.equals(cargoTbl)) {
                index = 1;
                break;
            }
        }
        return index;
    }

    public void cargarDatos() {
        tblEmpleados.setItems(listarEmpleado());
        colEmpleadoId.setCellValueFactory(new PropertyValueFactory<Empleado, Integer>("EmpleadoId"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Empleado, String>("Nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Empleado, String>("Apellido"));
        colSueldo.setCellValueFactory(new PropertyValueFactory<Empleado, Double>("Sueldo"));
        colHoraEntrada.setCellValueFactory(new PropertyValueFactory<Empleado, Time>("HoraEntrada"));
        colHoraSalida.setCellValueFactory(new PropertyValueFactory<Empleado, Time>("HoraSalida"));
        colCargo.setCellValueFactory(new PropertyValueFactory<Empleado, String>("Cargo"));
        colEncargado.setCellValueFactory(new PropertyValueFactory<Empleado, String>("Encargado"));
        tblEmpleados.getSortOrder().add(colEmpleadoId);
    }

    public ObservableList<Empleado> listarEmpleado() {
        ArrayList<Empleado> empleados = new ArrayList<>();

        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_ListarEmpleado()";
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

    public void agregarEmpleado() {
        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_agregarEmpleado(?,?,?,?,?,?,?)";

            statement = conexion.prepareStatement(sql);
            statement.setString(1, tfNombre.getText());
            statement.setString(2, tfApellido.getText());
            statement.setDouble(3, Double.parseDouble(tfSueldo.getText()));
            LocalTime horaEntrada = LocalTime.parse(tfHoraEntrada.getText());
            Time horaEntradaSQL = Time.valueOf(horaEntrada);
            statement.setTime(4, horaEntradaSQL);
            LocalTime horaSalida = LocalTime.parse(tfHoraSalida.getText());
            Time horaSalidaSQL = Time.valueOf(horaSalida);
            statement.setTime(5, horaSalidaSQL);
            statement.setInt(6, ((Cargo) cmbCargo.getSelectionModel().getSelectedItem()).getCargoId());
            Empleado encargadoSeleccionado = (Empleado) cmbEncargado.getSelectionModel().getSelectedItem();
            if (encargadoSeleccionado != null) {
                statement.setInt(7, encargadoSeleccionado.getEmpleadoId());
            } else {
                statement.setNull(7, java.sql.Types.INTEGER);
            }
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
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

    public void editarEmpleado() {
        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_editarEmpleado(?,?,?,?,?,?,?,?)";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(tfEmpleadoId.getText()));
            statement.setString(2, tfNombre.getText());
            statement.setString(3, tfApellido.getText());
            statement.setDouble(4, Double.parseDouble(tfSueldo.getText()));
            LocalTime horaEntrada = LocalTime.parse(tfHoraEntrada.getText());
            Time horaEntradaSQL = Time.valueOf(horaEntrada);
            statement.setTime(5, horaEntradaSQL);
            LocalTime horaSalida = LocalTime.parse(tfHoraSalida.getText());
            Time horaSalidaSQL = Time.valueOf(horaSalida);
            statement.setTime(6, horaSalidaSQL);
            statement.setInt(7, ((Cargo) cmbCargo.getSelectionModel().getSelectedItem()).getCargoId());
            Empleado encargadoSeleccionado = (Empleado) cmbEncargado.getSelectionModel().getSelectedItem();
            if (encargadoSeleccionado != null) {
                statement.setInt(8, encargadoSeleccionado.getEmpleadoId());
            } else {
                statement.setNull(8, java.sql.Types.INTEGER);
            }
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
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

    public ObservableList<Cargo> listarCargos() {
        ArrayList<Cargo> cargos = new ArrayList<>();

        try {
            conexion = Conexion.getInstance().obtenerConexion();
            String sql = "call sp_listarCargo()";
            statement = conexion.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int cargoId = resultSet.getInt("cargoId");
                String nombreCargo = resultSet.getString("nombreCargo");
                String descripcionCargo = resultSet.getString("descripcionCargo");
                cargos.add(new Cargo(cargoId, nombreCargo, descripcionCargo));
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
        return FXCollections.observableList(cargos);
    }

    public void vaciarCampos() {
        tfNombre.clear();
        tfApellido.clear();
        tfHoraEntrada.clear();
        tfHoraSalida.clear();
        tfEmpleadoId.clear();
        tfSueldo.clear();
        cmbEncargado.getSelectionModel().clearSelection();
        cmbCargo.getSelectionModel().clearSelection();
    }

    public Main getStage() {
        return stage;
    }

    public void setStage(Main stage) {
        this.stage = stage;
    }

    public int getOp() {
        return op;
    }

    public void setOp(int op) {
        this.op = op;
    }
    
}
