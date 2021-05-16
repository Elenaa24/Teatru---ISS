package controller;

import domain.Employee;
import domain.Spectator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    private Stage primaryStage;
    private Service service;

    public void setService(Service service){
        this.service = service;
    }

    public void setPrimaryPage(Stage primaryPage){
        this.primaryStage = primaryPage;
    }

    public void loginPress(MouseEvent mouseEvent) throws IOException {
        String username1 = username.getText();
        String password1 = password.getText();
        Employee employee = service.loginEmployee(username1,password1);
        if(employee == null){
            Spectator spectator = service.loginSpectator(username1,password1);
            if(spectator == null){
                Alert alert = new Alert(Alert.AlertType.ERROR,"Username or password is incorrect!");
                alert.show();
                this.password.setText("");
                this.username.setText("");
            }
            else{
                FXMLLoader loader=new FXMLLoader();
                loader.setLocation(LoginController.class.getResource("/MainPageClient.fxml"));
                TabPane root=loader.load();
                MainPageClientController ctrl=loader.getController();
                ctrl.setService(service,spectator);
                ctrl.setPrimaryPage(primaryStage);
                primaryStage.setScene(new Scene(root, 700, 500));
                primaryStage.setTitle("Client Page");
                primaryStage.show();
            }
        }
        else{
            FXMLLoader loader=new FXMLLoader();
            loader.setLocation(LoginController.class.getResource("/MainPageEmployee.fxml"));
            TabPane root=loader.load();
            MainPageEmployeeController ctrl=loader.getController();
            ctrl.setService(service);
            ctrl.setEmployee(employee);
            ctrl.setPrimaryPage(primaryStage);
            primaryStage.setScene(new Scene(root, 700, 500));
            primaryStage.setTitle("Employee Page");
            primaryStage.show();
        }
    }
}
