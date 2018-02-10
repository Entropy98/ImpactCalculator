import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class ApplicationMain extends Application{
    private static String text;
    private static double wpm = 0.0;
    private static Label displayedText;
    private static String displayed = "Alice";
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
                        setText(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                setWPM(newValue.doubleValue());
            }
        });

        final GridPane inputPane = new GridPane();

        GridPane.setConstraints(uploadDoc, 0, 17);
        GridPane.setConstraints(slider, 40, 17);
        GridPane.setConstraints(sliderCaption, 40, 16);
        inputPane.setHgap(6);
        inputPane.setVgap(5);
        inputPane.getChildren().addAll(uploadDoc, slider, sliderCaption);

        displayedText = new Label();
        displayedText.setFont(new Font(80));
        displayedText.setText("Alice");

        Platform.runLater(() -> displayedText.setText(displayed));

        final GridPane outputPane = new GridPane();

        outputPane.setAlignment(Pos.CENTER);
        outputPane.setHgap(10);
        outputPane.setVgap(10);
        outputPane.getChildren().addAll(displayedText);

        GridPane mainPain = new GridPane();

        GridPane.setConstraints(outputPane, 0, 7);
        GridPane.setConstraints(inputPane, 0, 9);
        mainPain.setAlignment(Pos.CENTER);
        mainPain.setVgap(8);
        mainPain.getChildren().addAll(outputPane, inputPane);

        final Pane root = new VBox(12);
        root.getChildren().addAll(mainPain);
        root.setPadding(new Insets(12,12,12,12));

        primaryStage.setScene(new Scene(root, 600, 340));
        primaryStage.show();
    }

    private static void setText(File pdfFile) throws IOException {
        PDDocument doc = PDDocument.load(pdfFile);
        text = new PDFTextStripper().getText(doc);
    }

    private static void setWPM(double val){
        wpm = val;
    }

    public static void setDisplayed(String s){
        displayed = s;
    }

    public static String getText(){
        return text;
    }

    public static double getWPM(){
        return wpm;
    }
}
