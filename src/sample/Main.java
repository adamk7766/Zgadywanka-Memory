package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application {

    int counter = 0;
    List<Button> buttonList = new ArrayList<>();
    int lastIndex;
    int imageId;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Zgadywanka nowa");
        FlowPane flow = new FlowPane();
        flow.setVgap(8);
        flow.setHgap(4);

        ArrayList<Integer> list = new ArrayList<Integer>(20);
        for(int i = 1; i <= 10; i++) {
            list.add(i);
        }
        for(int i = 11; i <= 20; i++){
            list.add(i-10);
        }

        for (int i = 1; i <= 20; i++) {
            Button button = new Button("");
            Random rand = new Random();
            int index = rand.nextInt(list.size());
            imageId = list.remove(index);
            button.setPrefHeight(120);
            button.setPrefWidth(120);
            button.setId(String.valueOf(imageId));
            buttonList.add(button);
            button.setOnAction(event -> {
                counter++;
                Button clickedButton = (Button) event.getSource();
                Image image = new Image(getClass().getResourceAsStream("/" + clickedButton.getId() + ".png"));
                clickedButton.setGraphic(new ImageView(image));
                if (counter % 2 == 0) {
                    if (buttonList.get(lastIndex).getId().equals(clickedButton.getId()) && (lastIndex != (buttonList.indexOf(clickedButton)))) {
                        clickedButton.setDisable(true);
                        buttonList.get(lastIndex).setDisable(true);
                    } else {
                        buttonList.get(lastIndex).setGraphic(null);
                        counter = 1;
                    }
                }
                lastIndex = buttonList.indexOf(clickedButton);


                System.out.println("Last index: " + lastIndex);

            });

            flow.getChildren().add(button);

        }
        Button newButton = new Button("Nowa Gra");
        newButton.setPrefHeight(20);
        newButton.setPrefWidth(120);
        newButton.setVisible(true);
        flow.getChildren().add(newButton);
        newButton.setOnAction(event -> {
            newGame();
        });


        primaryStage.setScene(new Scene(flow, 620, 550));
        primaryStage.show();
    }

    public void newGame() {

        ArrayList<Integer> list = new ArrayList<Integer>(20);
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        for (int i = 11; i <= 20; i++) {
            list.add(i - 10);
        }

        for(Button x : buttonList)  {
            Random rand = new Random();
            int index = rand.nextInt(list.size());
            imageId = list.remove(index);
            x.setId(String.valueOf(imageId));
            x.setDisable(false);
            x.setGraphic(null);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
