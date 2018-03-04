
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.*;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DrawingPicture {
    public int i =0;
    public double v = 0.0;
    public void draw(double point){
        GridPane gridPane = new GridPane();
        gridPane.setPrefSize(405,400);
        gridPane.setLayoutX(350);
        gridPane.setLayoutY(0);
        Button color = new Button("Цвет");
        color.setMinSize(100,40);
        Button save = new Button("Сохранить");
        save.setMinSize(100,40);
        Button exit = new Button("Выход");
        exit.setMinSize(100,40);
        Button pencil = new Button("Карандаш");
        pencil.setMinSize(100,40);
        Button drawLine = new Button("Линия");
        drawLine.setMinSize(100,40);
        Button remove = new Button("Ластик");
        remove.setMinSize(100,40);
        gridPane.addColumn(0, color);
        gridPane.addColumn(0,save);
        gridPane.addColumn(0,exit);
        gridPane.addColumn(1,pencil);
        gridPane.addColumn(1,drawLine);
        gridPane.addColumn(1,remove);

        double v1=point/10;
        v= 300.0*v1;

        Pane simplePane = new Pane();
        simplePane.setMinSize(300,300);


        Stage stage = new Stage();
        Canvas canvas = new Canvas();
        Canvas canvas1 = new Canvas();
        Canvas canvas2 = new Canvas();
        canvas.setWidth(300);
        canvas.setHeight(300);
        canvas1.setWidth(300);
        canvas1.setHeight(300);

        canvas2.setWidth(300);
        canvas2.setHeight(300);
        canvas2.setLayoutX(0);
        canvas2.setLayoutY(350);






        Pane pane = new Pane();
        Scene scene = new Scene(pane, 800, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        GraphicsContext gc1 = canvas1.getGraphicsContext2D();
        GraphicsContext gc2 = canvas2.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(point);
        gc1.setStroke(Color.GRAY);
        gc1.setLineWidth(1);
        gc2.setStroke(Color.BLACK);
        gc2.setLineWidth(point);
        double s = 5/point;
        gc2.scale(s,s);



        for (int a = 0; a <v; a+=point) {
            double x1 ;
            x1 = a;
            gc1.moveTo(x1, 0);
            gc1.lineTo(x1, v);
            gc1.stroke();
        }

        for (int b = 0; b < v; b+=point) {
            double y1 ;
                y1 = b ;

            gc1.moveTo(0, y1);
            gc1.lineTo(v, y1);
            gc1.stroke();
        }


        pencil.setOnAction(event -> {

            pencil(scene,gc,gc2);
        });

        drawLine.setOnAction(event ->{
            drawLine(scene,gc,gc2);
        });

        color.setOnAction(event -> {
            OptionsColor oc = new OptionsColor();
            oc.options(gc,gc2);
        });
        remove.setOnAction(event1 -> {
            remove(scene,gc,gc2);
        });


        exit.setOnAction(event -> {
            stage.close();
        });

        save.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(stage);
            if(file != null){
                try {
                    WritableImage writableImage = new WritableImage(300, 300);
                    SnapshotParameters sp = new SnapshotParameters();
                    sp.setFill(Color.TRANSPARENT);
                    ImageIO.write(SwingFXUtils.fromFXImage(canvas2.snapshot(sp,writableImage),null), "png", file);
                } catch (IOException ex) {
                    Logger.getLogger(DrawingPicture.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        stage.close();
        });
        simplePane.getChildren().addAll(canvas,canvas1);
        pane.getChildren().addAll(canvas2,gridPane,simplePane);
        pane.setCursor(Cursor.CROSSHAIR);
        stage.setScene(scene);
        stage.setTitle("Рисование картинки");
        stage.show();
    }

    public void drawLine(Scene scene, GraphicsContext gc, GraphicsContext gc2){
        scene.setOnMousePressed(event -> {

            gc.beginPath();
            gc2.beginPath();
            gc.lineTo(event.getSceneX(), event.getSceneY());
            gc2.lineTo(event.getSceneX(),event.getSceneY());
            gc.stroke();
            gc2.stroke();
        });

        scene.setOnMouseDragged(event -> {

        });
        scene.setOnMouseReleased(event -> {
            gc.lineTo(event.getSceneX(), event.getSceneY());
            gc2.lineTo(event.getSceneX(),event.getSceneY());
            gc.stroke();
            gc2.stroke();
        });
    }

    public void remove(Scene scene, GraphicsContext gc, GraphicsContext gc2){
        scene.setOnMousePressed(event -> {
            gc.beginPath();
            gc2.beginPath();
            gc.clearRect(event.getSceneX(),event.getSceneY(),20,20);
            gc2.clearRect(event.getSceneX(),event.getSceneY(),20,20);

        });

        scene.setOnMouseDragged(event -> {
            gc.clearRect(event.getSceneX(),event.getSceneY(),20,20 );
            gc2.clearRect(event.getSceneX(),event.getSceneY(),20,20 );
        });
        scene.setOnMouseReleased(event -> {

        });
    }

    public void pencil(Scene scene, GraphicsContext gc, GraphicsContext gc2){
        scene.setOnMousePressed(event -> {

            gc.beginPath();
            gc2.beginPath();
            gc.lineTo(event.getSceneX(), event.getSceneY());
           gc2.lineTo(event.getSceneX(), event.getSceneY());
            gc.stroke();
            gc2.stroke();
            i++;
        });

        scene.setOnMouseDragged(event -> {
            gc.lineTo(event.getSceneX(), event.getSceneY());
            gc2.lineTo(event.getSceneX(), event.getSceneY());
            gc.stroke();
            gc2.stroke();

        });
    }


}
