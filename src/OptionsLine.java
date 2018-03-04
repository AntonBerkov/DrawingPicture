import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class OptionsLine {
    public double point =10;
    public void line(){
        Stage stage = new Stage();
        stage.setTitle("Выберите размер пикселя");
        GridPane pane = new GridPane();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        Slider slider = new Slider();
        slider.setMin(10);
        slider.setMax(30);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        Label label = new Label("10.0");
        Button ok = new Button("OK");
        slider.valueProperty().addListener(event->{
            point = slider.getValue();
            String value = String.format("%.1f", point);
            label.setText(value);

        });
        ok.setOnAction(event -> {
            DrawingPicture drawingPicture = new DrawingPicture();
            drawingPicture.draw(point);
            stage.close();
        });
        pane.addRow(0,label);
        pane.addRow(1,slider);
        pane.addRow(2,ok);
        stage.show();
    }
}
