package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Model;
import model.Serie;

public class ModifView {

	public ModifView(Model m) {
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

		b.setOnAction(e -> {
			add(m, nom, nbEp, duree, vus, stage);

		});
	}

	public ModifView(Model m, String selectedItem) {
		Serie s = m.getSerie(selectedItem);
		Text text = new Text("Nom : ");
		TextField nom = new TextField(s.getNom());
		nom.setEditable(false);
		Text text2 = new Text("   NbEpisodes : ");
		TextField nbEp = new TextField(s.getNbEpisodes() + "");
		Text text3 = new Text("   Duree : ");
		TextField duree = new TextField(s.getDureeAvg() + "");
		Text text4 = new Text("   Vus : ");
		TextField vus = new TextField(s.getNbVus() + "");
		Button b = new Button("Submit");
		HBox hbox = new HBox(text, nom, text2, nbEp, text3, duree, text4, vus, b);
		Scene scene = new Scene(hbox);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();

		b.setOnAction(e -> {
			edit(m, nom, nbEp, duree, vus, stage);

		});
	}

	private void edit(Model m, TextField nom, TextField nbEp, TextField duree, TextField vus, Stage stage) {
		if (!nbEp.getText().equals("") && !duree.getText().equals("") && !vus.getText().equals("")) {
			if (nbEp.getText().matches("[0-9]*") && duree.getText().matches("[0-9]*")
					&& vus.getText().matches("[0-9]*")) {
				if (Integer.valueOf(nbEp.getText()) >= Integer.valueOf(vus.getText())) {
					m.edit(nom.getText(), nbEp.getText(), duree.getText(), vus.getText());
					stage.close();
				} else {
					error("Nombre d'épisode supérieur au nombre d'épisodes vus");
				}
			} else {
				error("Données incorrectes");
			}
		} else {
			error("Entrer toutes les données");
		}

	}

	private void add(Model m, TextField nom, TextField nbEp, TextField duree, TextField vus, Stage stage) {
		if (!nom.getText().equals("") && !nbEp.getText().equals("") && !duree.getText().equals("")
				&& !vus.getText().equals("")) {
			if (nbEp.getText().matches("[0-9]*") && duree.getText().matches("[0-9]*")
					&& vus.getText().matches("[0-9]*")) {
				if (Integer.valueOf(nbEp.getText()) >= Integer.valueOf(vus.getText())) {
					m.add(nom.getText(), nbEp.getText(), duree.getText(), vus.getText());
					stage.close();
				} else {
					error("Nombre d'épisode supérieur au nombre d'épisodes vus");
				}
			} else {
				error("Données incorrectes");
			}
		} else {
			error("Entrer toutes les données");
		}
	}

	

	private void error(String s) {
		Text text = new Text(s);
		text.setFont(Font.font(25));
		BorderPane bPane = new BorderPane();
		Button ok = new Button("   OK   ");
		
		bPane.setTop(text);
		bPane.setCenter(ok);
		BorderPane.setMargin(ok, new Insets(10,0,0,0));
		bPane.setPadding(new Insets(5,5,5,5));
		Scene scene = new Scene(bPane);
		Stage tmp = new Stage();
		tmp.setScene(scene);
		tmp.setTitle("Error");
		tmp.setResizable(false);
		tmp.setFullScreen(false);
		tmp.show();
		
		ok.setOnAction(e->{
			tmp.close();
		});

	}

}
