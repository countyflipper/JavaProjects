package JavaFX2_KeyEvent;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;

public class ColorChanger extends Application {    

  private Rectangle rectangle = new Rectangle(50, 50, 200, 100); 
  private Label redLabel, greenLabel, blueLabel;
  
  public static void main(String[] args) {
      launch();
  }

  public void start(Stage stage) {

  	Slider slider = new Slider(0, 1, 0);     
  	slider.valueProperty().addListener( e -> GetRGBColors(slider) );

  	rectangle.setStroke(Color.BLACK);
  	
      redLabel = new Label(String.format(" Red = %1.0f", 0.0));
      greenLabel = new Label(String.format(" Green = %1.0f", 0.0));
      blueLabel = new Label(String.format(" Blue = %1.0f", 0.0));   	
      
      VBox vBox = new VBox();
      vBox.setSpacing(5); 
      //vBox.setStyle("-fx-background-color: LIGHTBLUE");
      VBox.setMargin(rectangle, new Insets(25, 100,25, 100));  
      VBox.setMargin(slider, new Insets(0, 5, 5, 5)); 
      VBox.setMargin(redLabel, new Insets(0, 5, 0, 5));  
      VBox.setMargin(blueLabel, new Insets(0, 5, 0, 5)); 
      VBox.setMargin(greenLabel, new Insets(0, 5, 0, 5)); 
      
      ObservableList list = vBox.getChildren(); 
      
      //Adding all the nodes to the observable list 
      list.addAll(rectangle, slider, redLabel,blueLabel,greenLabel); 
      
      //Preparing the scene
      Scene scene = new Scene(vBox,400, 300);
      stage.setTitle("Homework 7 - Stefan DeRosa");
      stage.setScene(scene);
      stage.setResizable(false);
      stage.show();

  } // end start();

        
  private void GetRGBColors(Slider m_slider) {

      Color color = Color.color(m_slider.getValue(), m_slider.getValue(), m_slider.getValue());
      rectangle.setFill(Color.rgb((int)(255*color.getRed()), (int)(255*color.getGreen()), (int)(255*color.getBlue())));
      
      redLabel.setText(String.format(" Red = %1.0f", 255*color.getRed()) );
      greenLabel.setText(String.format(" Green = %1.0f", 255*color.getGreen()));
      blueLabel.setText(String.format(" Blue = %1.0f", 255*color.getBlue()));

  }    

}  // end class 


