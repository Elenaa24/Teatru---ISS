package controller;

import domain.Reservation;
import domain.Seat;
import domain.Show;
import domain.Spectator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.Service;

import java.util.List;
import java.util.stream.Collectors;

public class MainPageClientController {
    //public TableColumn<Reservation, Show> showColumn;
    public TableColumn<Reservation, Seat> rowColumn1;
    //public TableColumn<Seat, Integer> nrColumn1;
    public TableView<Reservation> reservationsTable;
    @FXML
    private TableView<Seat> seatsTable;
    @FXML
    private TableColumn<Seat, Integer> rowColumn;
    @FXML
    private TableColumn<Seat, Integer> nrColumn;
    @FXML
    private TableColumn<Show, String> nameColumn;
    @FXML
    private TableColumn<Show, String> locationColumn;
    @FXML
    private TableColumn<Show, String> dateColumn;
    @FXML
    private TableColumn<Show, String> descriptionColumn;
    @FXML
    private TableView<Show> showsTable;

    ObservableList<Seat> observableSeatList = FXCollections.observableArrayList();
    ObservableList<Show> observableShowList = FXCollections.observableArrayList();
    ObservableList<Reservation> observableReservationList = FXCollections.observableArrayList();

    private Service service;
    private Stage mainPage;
    private Spectator spectator;

    public MainPageClientController() {
    }

    public void openMyRes(Event event) {
        observableReservationList.clear();
        List<Reservation> reservationList = service.getReservation(spectator);
        reservationList.forEach(System.out::println);
        observableReservationList.addAll(reservationList);
        reservationsTable.setItems(observableReservationList);
    }

    @FXML
    public void initialize() {
        rowColumn.setCellValueFactory(new PropertyValueFactory<Seat,Integer>("row"));
        nrColumn.setCellValueFactory(new PropertyValueFactory<Seat,Integer>("nr"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Show,String>("name"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<Show,String>("location"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<Show, String>("dateToString"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<Show, String>("description"));
        //showColumn.setCellValueFactory(new PropertyValueFactory<Reservation, Show>("show"));
        //nrColumn1.setCellValueFactory(new PropertyValueFactory<Seat,Integer>("nr"));
        rowColumn1.setCellValueFactory(new PropertyValueFactory<Reservation,Seat>("seat"));
        SpinnerValueFactory.IntegerSpinnerValueFactory spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100);

    }
    public void setService(Service service, Spectator spectator)  {
        this.service = service;
        observableShowList.addAll(service.getShows().stream().map(show -> {show.setDateToString(); return show;}).collect(Collectors.toList()));
        showsTable.setItems(observableShowList);
        this.spectator = spectator;
    }
    public void setPrimaryPage(Stage primaryPage){
        this.mainPage = primaryPage;
    }


    public void selectedShow(MouseEvent mouseEvent) {
        observableSeatList.clear();
        Show selectedShow = showsTable.getSelectionModel().getSelectedItem();
        observableSeatList.addAll(service.getSeatsList(selectedShow).stream().filter(seat -> seat.getStatus().equals("free")).collect(Collectors.toList()));
        seatsTable.setItems(observableSeatList);
    }

    public void book(MouseEvent mouseEvent) {
        Seat selectedSeat = seatsTable.getSelectionModel().getSelectedItem();
        service.saveReservation(selectedSeat,spectator);
        observableSeatList.clear();
        Show selectedShow = showsTable.getSelectionModel().getSelectedItem();
        observableSeatList.addAll(service.getSeatsList(selectedShow).stream().filter(seat -> seat.getStatus().equals("free")).collect(Collectors.toList()));
        seatsTable.setItems(observableSeatList);


    }

    public void cancelReservation(MouseEvent mouseEvent) {
        Reservation reservation = reservationsTable.getSelectionModel().getSelectedItem();
        service.deleteReservation(reservation);
        observableReservationList.remove(reservation);
        reservationsTable.setItems(observableReservationList);
    }
}
