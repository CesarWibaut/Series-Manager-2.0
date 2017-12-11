package series;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ModifView {

	public ModifView(int i, Model m) {
		Text text = new Text("Nom : ");
		TextField nom = new TextField();
		Text text2 = new Text("   NbEpisodes : ");
		TextField nbEp = new TextField();
		Text text3 = new Text("   Duree : ");
		TextField duree = new TextField();
		Text text4 = new Text("   Vus : ");
		TextField vus = new TextField();
		Button b = new Button("Submit");
		HBox hbox = new HBox(text, nom, text2, nbEp, text3, duree, text4, vus, b);
		Scene scene = new Scene(hbox);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		
		b.setOnAction(e->{
			if(i==0) {
				if(nom.getText() != null && nbEp.getText() !=null && duree.getText()!=null && vus.getText()!=null) {
					if(nbEp.getText().matches("[0-9]*") && duree.getText().matches("[0-9]*") && vus.getText().matches("[0-9]*") ) {
						
					}
				}
			}
			
			
			
			
		});
	}

}
