package series;


import java.util.Observable;
import java.util.Observer;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View extends Stage implements Observer {
	private ListView<String> lView;
	private Text nb1;
	private Text nb3;
	private Text nb5;
	private Text nb7;

	private Model m;
	private Stage tmp;
	public View(Model m) throws Exception {
		this.m = m;
		m.addObserver(this);
		Controller c = new Controller(m);
		HBox hbox = new HBox();
		lView = new ListView<String>();

		VBox vbox = new VBox();
		HBox hbox1 = new HBox();
		HBox hbox2 = new HBox();
		HBox hbox3 = new HBox();
		HBox hbox4 = new HBox();

		Text nb = new Text("Nombre d'épisodes : ");
		nb1 = new Text("0");
		Text nb2 = new Text("Durée moyenne : ");
		nb3 = new Text("0");
		Text nb4 = new Text("Episodes vus : ");
		nb5 = new Text("0");
		Text nb6 = new Text("Temps restant : ");
		nb7 = new Text("0");

		nb.setFont(Font.font(25));
		nb1.setFont(Font.font(25));
		nb2.setFont(Font.font(25));
		nb3.setFont(Font.font(25));
		nb4.setFont(Font.font(25));
		nb5.setFont(Font.font(25));
		nb6.setFont(Font.font(25));
		nb7.setFont(Font.font(25));

		hbox1.getChildren().addAll(nb, nb1);
		hbox2.getChildren().addAll(nb2, nb3);
		hbox3.getChildren().addAll(nb4, nb5);
		hbox4.getChildren().addAll(nb6, nb7);

		Button add = new Button("ADD");
		Button edit = new Button("EDIT");
		Button rm = new Button("REMOVE");
		HBox hbox5 = new HBox(add, edit, rm);

		vbox.getChildren().addAll(hbox1, hbox2, hbox3, hbox4, hbox5);

		hbox5.setPadding(new Insets(200, 0, 0, 10));

		hbox.getChildren().addAll(lView, vbox);

		this.setWidth(600);

		vbox.setPadding(new Insets(0, 0, 0, 10));

		Scene scene = new Scene(hbox);
		this.setTitle("Series Manager");
		this.setScene(scene);

		this.getIcons().add(new Image(View.class.getResourceAsStream("images.png")));

		m.init();

		show();

		lView.setOnMouseClicked(e -> {
			c.listClicked(lView.getSelectionModel().getSelectedItem());
		});

		add.setOnAction(e -> {
			c.add();
		});

		rm.setOnAction(e -> {
			if (!lView.getSelectionModel().getSelectedItem().equals("Total"))
				c.remove(lView.getSelectionModel().getSelectedItem());
			lView.getSelectionModel().selectLast();
		});
		
		edit.setOnAction(e->{
			if (!lView.getSelectionModel().getSelectedItem().equals("Total"))
				c.edit(lView.getSelectionModel().getSelectedItem());
		});
		
		setOnCloseRequest(e->{
			afficheEnregistrement();
			try {
				c.close();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			fermeEnregistrement();
		});
	}

	private void fermeEnregistrement() {
		tmp.close();
		
	}

	private void afficheEnregistrement() {
		Text text = new Text("Sauvegarde...");
		text.setFont(Font.font(25));
		HBox hbox = new HBox(text);
		hbox.setPadding(new Insets(20,20,20,20));
		Scene scene = new Scene(hbox);
		tmp = new Stage();
		tmp.setScene(scene);
		tmp.show();
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg1 != null) {
			lViewReload();
		}
		setTexts();
	}

	private void setTexts() {
		nb1.setText(m.getNbEpisodes() + "");
		nb3.setText(m.getDureeAvg() + "");
		nb5.setText(m.getNbVus() + "");
		nb7.setText(m.getTpsRestant());
	}

	private void lViewReload() {
		lView.getItems().clear();
		lView.getItems().addAll(FXCollections.observableArrayList(m.getNoms()));

	}

}
