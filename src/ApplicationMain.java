import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class ApplicationMain extends Application{
    private static String text;
    private final double wpm = 0.0;
    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("PDF Speed Reader");
        final Button uploadDoc = new Button();
        final FileChooser fileChooser = new FileChooser();
        final Slider slider = new Slider(100, 1000, 400);
        final Label sliderCaption = new Label("Set Maximum Words Per Minute: ");

        uploadDoc.setText("Upload a PDF");
        uploadDoc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File file = fileChooser.showOpenDialog(primaryStage);
                if(file != null){
                    try {
                        getText(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        final GridPane inputPane = new GridPane();

        GridPane.setConstraints(uploadDoc, 10, 39);
        GridPane.setConstraints(slider, 40, 39);
        GridPane.setConstraints(sliderCaption, 40, 38);
        inputPane.setHgap(6);
        inputPane.setVgap(5);
        inputPane.getChildren().addAll(uploadDoc, slider, sliderCaption);

        final Text displayed = new Text();
        displayed.setFont(new Font(20));


        final GridPane outputPane = new GridPane();

        GridPane.setConstraints(outputPane, 0, 0);
        outputPane.setHgap(10);
        outputPane.setVgap(10);
        outputPane.getChildren().addAll(displayed);

        final Pane root = new VBox(12);
        root.getChildren().addAll(outputPane, inputPane);
        root.setPadding(new Insets(12,12,12,12));

        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();
    }
    private static void getText(File pdfFile) throws IOException {
        PDDocument doc = PDDocument.load(pdfFile);
        text = new PDFTextStripper().getText(doc);
    }
}
