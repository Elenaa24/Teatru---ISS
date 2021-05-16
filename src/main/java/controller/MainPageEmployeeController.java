package controller;

import domain.Employee;
import domain.Reservation;
import domain.Seat;
import domain.Show;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.Service;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class MainPageEmployeeController {
    @FXML
    private ListView reservationList;
    @FXML
    private TableColumn<Show, String> nameColumn;
    @FXML
    private TableView<Show> showsTable;
    @FXML
    private TableColumn<Show, String> locationColumn;
    @FXML
    private TableColumn<Show, String> dateColumn;
    @FXML
    private TableColumn<Show, String> descriptionColumn;
    @FXML
    private TextField namefield;
    @FXML
    private TextField locationField;
    @FXML
    private TextField descriptionField;
    @FXML
    private DatePicker datafield;

    private ObservableList<Show> showObservableList = FXCollections.observableArrayList();
    private ObservableList<Reservation> reservationObservableList = FXCollections.observableArrayList();

    private Stage primaryPage;
    private Service service;
    private Employee employee;

    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Show,String>("name"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Show,String>("location"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Show, String>("dateToString"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Show, String>("description"));
    }
    public void setService(Service service) {
        this.service = service;
        setShowsTable();
    }

    public void setShowsTable(){
        showObservableList.clear();
        showObservableList.addAll(service.getShows().stream().map(show -> {show.setDateToString(); return show;}).collect(Collectors.toList()));
        showsTable.setItems(showObservableList);
    }

    public void setReservationList(){
        reservationObservableList.clear();
        reservationObservableList.addAll(service.getAllReservation());
        reservationList.setItems(reservationObservableList);
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
        setReservationList();
    }

    public void setPrimaryPage(Stage primaryPage) {
        this.primaryPage = primaryPage;
    }

    public void addButtonClicked(MouseEvent mouseEvent) {
        String name = namefield.getText();
        String location = locationField.getText();
        String description = descriptionField.getText();
        LocalDate localDate = datafield.getValue();
        LocalDateTime localDateTime2 = localDate.atTime(20,00);
        Show newShow = new Show(name,location,localDateTime2,description);
        service.saveShow(newShow);
        setShowsTable();
    }

    public void deleteButtonClicked(ActionEvent actionEvent) {
        Show show = showsTable.getSelectionModel().getSelectedItem();
        if(show == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "You need to select one show!");
            alert.show();
        }
        else{
            service.deleteShow(show);
            setShowsTable();
        }
    }
}
