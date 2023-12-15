package org.company.com.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainAppView extends Application {

    private static final String TITLE = "Credit Card Module";
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 768;


    private static final String FXML_PATH_HOME = "/views/home-page.fxml";


    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;

        window.setTitle(TITLE);
        window.setWidth(WIDTH);
        window.setHeight(HEIGHT);
        Pane root = new FXMLLoader(getClass().getResource(FXML_PATH_HOME)).load();
        Scene scene = new Scene(root);
        // Only Activate this if you have a second screen connected
//        Screen screen = Screen.getPrimary();
//
//        ObservableList<Screen> screens = Screen.getScreens();
//
//        Screen secondScreen = screens.size() > 1 ? screens.get(1) : screen;
//
//        Rectangle2D bounds = secondScreen.getVisualBounds();
//
//        window.setX(bounds.getMinX() + 100);
//        window.setY(bounds.getMinY() + 100);

        window.setScene(scene);
        window.show();
    }

}
