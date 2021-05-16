import controller.LoginController;
import controller.MainPageClientController;
import domain.Spectator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import repository.implementations.*;
import repository.interfaces.*;
import service.Service;

public class MainFX extends Application {



    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Spectator spectator = new Spectator("spectator","spectator","spectator");
        spectator.setId(61);
        EmployeeRepository employeeRepo = new EmployeeRepo();
        ReservationRepository reservationRepository = new ReservationRepo();
        ShowRepository showRepository = new ShowRepo();
        SpectatorRepository spectatorRepository = new SpectatorRepo();
        SeatRepository seatRepository = new SeatRepo();
        Service service = new Service(employeeRepo,reservationRepository,showRepository,spectatorRepository, seatRepository);
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(MainFX.class.getResource("/Login.fxml"));
        AnchorPane root=loader.load();
        LoginController ctrl=loader.getController();
        ctrl.setService(service);
        ctrl.setPrimaryPage(primaryStage);
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("Login");
        primaryStage.show();

    }
}

