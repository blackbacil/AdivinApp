package dad.javafx.AdivinApp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {
	private Label adivinLabel;
	private Button adivinButton;
	private TextField introduceText;
	private int valorEntero = (int) Math.floor(Math.random()*(100-1+1)+1);
	private int cont=0;

	@Override
	public void start(Stage primaryStage) throws Exception {
		introduceText = new TextField();
		introduceText.setPrefColumnCount(5);
		introduceText.setPromptText("0");
		introduceText.setMaxWidth(150);
		
		adivinLabel = new Label();
		adivinLabel.setText("Introduce un numero del 1 al 100");
		
		adivinButton = new Button();
		adivinButton.setText("Comprobar");
		adivinButton.setOnAction(e -> onAdivinButtonAction(e));
		adivinButton.setDefaultButton(true);
	
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(introduceText, adivinLabel, adivinButton );

		Scene escena = new Scene(root, 320, 200);
		
		primaryStage.setScene(escena);
		primaryStage.setTitle("Adivina el numero.");
		primaryStage.show();
	}


	private void onAdivinButtonAction(ActionEvent e) {
		try {
			int numero = Integer.parseInt(introduceText.getText());
			if(numero<101||numero>0) {
				if(numero==valorEntero) {
					cont+=1;
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("AdivinApp");
					alert.setHeaderText("Has ganado!!!");
					alert.setContentText(String.format("Solo has nesecitado %d intentos \n\n Vuelve a jugar y hazlo mejor.",cont ));
					
					valorEntero = (int) Math.floor(Math.random()*(100-1+1)+1);
					cont=0;
		
					alert.showAndWait();
				}else {
					if(numero<valorEntero) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("AdivinApp");
						alert.setHeaderText("Has fallado!!!");
						alert.setContentText(String.format("El numero a adivinar es mayor a %d \n\n Vuelve a intentarlo.",numero ));
						cont+=1;
		
						alert.showAndWait();
					}else {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("AdivinApp");
						alert.setHeaderText("Has fallado!!!");
						alert.setContentText(String.format("El numero a adivinar es menor a %d \n\n Vuelve a intentarlo.",numero ));
						cont+=1;
		
						alert.showAndWait();
					}
				}
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("AdivinApp");
				alert.setHeaderText("Error");
				alert.setContentText("el numero introducido no es valido.");

				alert.showAndWait();
			}
		}catch (NumberFormatException ex ) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("AdivinApp");
			alert.setHeaderText("Error");
			alert.setContentText("el numero introducido no es valido.");

			alert.showAndWait();
	   	}
	
	}



	public static void main(String[] args) {
		launch(args);

	}

}
