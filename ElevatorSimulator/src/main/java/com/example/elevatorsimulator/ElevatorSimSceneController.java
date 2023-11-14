package com.example.elevatorsimulator;
import javafx.animation.Animation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;import com.example.elevatorsimulator.SimulationSettings;

public class ElevatorSimSceneController implements Initializable{
//FMML-----------------------------------------------------------------------------------------
    @FXML
    private Text run_simulationCounterText;
    @FXML
    private Timeline timeline;
    @FXML
    private ScrollPane floorsContainer;
    @FXML
    private VBox vBoxElevators;
    @FXML
    private VBox VBoxfloor;
    @FXML
    public ToggleButton playButton;
//variables-----------------------------------------------------------------------------------------
    int i = 0;
    private int Run_simulation;
    private int Floors;
    private int Number_of_Elevators;
    public HelloController helloController;
    public ElevatorSimSceneController elevatorSimSceneController;
    public SimulationSettings simulationSettings;
    public ElevatorSimulation elevatorsimulation;
    private ElevatorSimulation elevatorSimulation;

    //set and get methods-----------------------------------------------------------------------------------------
    public void setRun_simulation(int a) {
        this.Run_simulation = a;
    }
    public void setHelloController(HelloController helloController) {
        this.helloController = helloController;

        initializeTimeline();
    }
    public void setElevatorSimSceneController(ElevatorSimSceneController elevatorSimSceneController) {
        this.elevatorSimSceneController = elevatorSimSceneController;
        Floors = helloController.getfloors();;
    }
    public void setFloors(int Floors) {
        this.Floors = Floors;
    }
    public void setSimulationSettings(SimulationSettings simulationSettings) {
        this.simulationSettings = simulationSettings;
    }
    public void setElevatorSimulation(ElevatorSimulation elevatorSimulation){
        this.elevatorSimulation = elevatorSimulation;
    }
//initializeTimeline (to get helloController working)-------------------------------------------------------------------------
    public void initializeTimeline() {
        Number_of_Elevators = helloController.getNumber_of_elevators();
        Run_simulation = helloController.getRunSimulation();
        Floors = helloController.getfloors();
        VBoxfloor = new VBox();
        floorsContainer.setContent(VBoxfloor);
        for (int a = 0; a < Floors; a++) {
            createFloor(a);
        }
        ArrayList<AddPassenger> add_passengerList = simulationSettings.getAddPassengerList();
        if (add_passengerList != null && !add_passengerList.isEmpty()) {
            for (AddPassenger passenger : add_passengerList) {
                System.out.println("Passenger ID: " + passenger.getPassengerID());
                System.out.println("startFloor: " + passenger.getstartFloor());
            }
        } else {
            System.out.println("add_passengerList is empty or null.");
        }



        run_simulationCounterText.setText(String.valueOf(i));
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            i++;
            run_simulationCounterText.setText(String.valueOf(i));
        }));
        timeline.setCycleCount(Run_simulation);
        timeline.play();
        System.out.println("Timeline played");
    }
    @FXML
    public void PauseTimeline() {
        if (playButton.getText().contains("II")) {
            timeline.play();
            playButton.setText("▶");
            System.out.println("Timeline resumed");
        } else if (playButton.getText().contains("▶")){
            timeline.pause();
            playButton.setText("II");
            System.out.println("Timeline paused");
        }
    }
    //Floor
    private void createFloor(int floorIndex) {
        HBox hBoxFloor = createFloorPane(floorIndex);
        VBoxfloor.getChildren().add(hBoxFloor);
        System.out.println("Floor " + (floorIndex + 1) + " created.");
        for (int e = 0; e < Number_of_Elevators ; e++) {
            int quarter = Number_of_Elevators / 4;
            if (e < quarter) {
                createElevator(hBoxFloor, e, floorIndex, "StandardElevator");
            } else if (e < 2 * quarter) {
                createElevator(hBoxFloor, e, floorIndex, "ExpressElevator");
            } else if (e < 3 * quarter) {
                createElevator(hBoxFloor, e, floorIndex, "FreightElevator");
            } else if (Number_of_Elevators % 2 == 1){
                createElevator(hBoxFloor, e, floorIndex, "StandardElevator");
            } else if (e <= Number_of_Elevators) {
                createElevator(hBoxFloor, e, floorIndex, "GlassElevator");
            }
        }
    }



    private HBox createFloorPane(int floorIndex) {
        HBox hBoxFloor = new HBox();

        hBoxFloor.setAlignment(Pos.BOTTOM_CENTER);
        hBoxFloor.setStyle("-fx-background-color: grey; -fx-border-style: solid; -fx-border-width: 1px; -fx-border-color:#ADD8E6; ");
        hBoxFloor.setPrefWidth(1200);
        hBoxFloor.setPrefHeight(200);

        return hBoxFloor;
    }
    //Elevator
    private ToggleButton createElevatorToggleButton(int elevatorIndex, boolean firstfloor) {
        ToggleButton elevatorButton = new ToggleButton();;
        if (firstfloor == false){
            Image elevatorImage = new Image("C:\\Users\\jyi95\\Downloads\\elevatorEmpty.png");
            ImageView imageView = new ImageView(elevatorImage);
            imageView.setFitWidth(100);
            imageView.setFitHeight(140);
            elevatorButton = new ToggleButton("", imageView);
            StackPane.setAlignment(imageView, Pos.BOTTOM_CENTER);
            elevatorButton.setStyle("-fx-background-color: lightgray; -fx-border-style: solid; -fx-border-width: 1px; -fx-border-color:#000; ");
            HBox.setMargin(elevatorButton, new Insets(0, 10, 0, 0));
        }
        else if (firstfloor == true){
            Image elevatorImageclosed = new Image("C:\\Users\\jyi95\\Downloads\\elevatorClosed.png");
            ImageView imageViewclosed  = new ImageView(elevatorImageclosed);
            imageViewclosed.setFitWidth(100);
            imageViewclosed.setFitHeight(140);
            elevatorButton = new ToggleButton("", imageViewclosed);
            StackPane.setAlignment(imageViewclosed, Pos.BOTTOM_CENTER);
            elevatorButton.setStyle("-fx-background-color: lightgray; -fx-border-style: solid; -fx-border-width: 1px; -fx-border-color:#000; ");
            HBox.setMargin(elevatorButton, new Insets(0, 10, 0, 0));
        }
        return elevatorButton;
    }
    private void createElevator(HBox hBoxFloor, int elevatorIndex, int floorNum, String elevatortype) {
        boolean firstfloor = false;
        int currentfloorNum = Floors - floorNum;
        if (currentfloorNum == 1){
            firstfloor = true;
        }
        Label elevatorLabel = new Label(elevatortype);
        elevatorLabel.setAlignment(Pos.CENTER);
        ToggleButton elevatorButton = createElevatorToggleButton(elevatorIndex, firstfloor);
        VBox elevatorVBox = new VBox();
        elevatorVBox.setAlignment(Pos.CENTER);
        elevatorVBox.getChildren().addAll(elevatorLabel, elevatorButton);
        hBoxFloor.getChildren().add(elevatorVBox);
        //System.out.println("Elevator " + (elevatorIndex + 1) + " on floor " + currentfloorNum + " is created.");
        elevatorButton.setOnAction(event -> {
            boolean isElevatorOnFloor = elevatorButton.isSelected();
            System.out.println("Elevator " + (elevatorIndex + 1) + " is on floor: " + currentfloorNum + ", " + isElevatorOnFloor);
        });
    }
    private HBox createPersonBox(int passengerID) {
        HBox personBox = new HBox();

        // Set up the appearance of the person box
        personBox.setAlignment(Pos.CENTER);
        personBox.setStyle("-fx-background-color: #FFD700; -fx-border-style: solid; -fx-border-width: 1px; -fx-border-color: #000;");
        personBox.setPrefSize(40, 40);

        // You can add more content to the person box, such as a label displaying the passengerID
        Label label = new Label(String.valueOf(passengerID));
        personBox.getChildren().add(label);

        return personBox;
    }

    private void addPersonToFloor(HBox personBox, int floorNum) {
        VBox floorContainer = (VBox) VBoxfloor.getChildren().get(floorNum - 1);
        floorContainer.getChildren().add(personBox);
        System.out.println("Person with ID " + personBox.getChildren().get(0) + " added to floor " + floorNum);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
