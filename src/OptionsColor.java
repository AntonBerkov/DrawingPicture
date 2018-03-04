import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class OptionsColor {
    public void options(GraphicsContext gc, GraphicsContext gc2){
        Stage stage = new Stage();
        stage.setTitle("Настройка цвета");
        Pane pane = new Pane();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setValue(Color.BLACK);
        colorPicker.setOnAction(event -> {
         gc.setStroke(colorPicker.getValue());
         gc2.setStroke(colorPicker.getValue());
            stage.close();
        });
        pane.getChildren().add(colorPicker);
        stage.show();

    }
}
