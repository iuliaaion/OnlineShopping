package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.Callable;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        ProductRepository productRepository = new ProductRepository();
        productRepository.addFromFile("src/products.txt");

        for(int i=1;i<=2;i++)
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("sample.fxml"));
            Controller controller = new Controller(productRepository);

            loader.setController(controller);
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Client" + i);
            stage.setScene(new Scene(root,600,400));
            stage.show();
        }

        /*
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

         */
    }


    public static void main(String[] args) {
        launch(args);
    }
}
