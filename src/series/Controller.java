package series;

public class Controller {
	
	private Model m;
	
	public Controller(Model m) {
		this.m=m;
	}

	public void listClicked(String selectedItem) {
		m.click(selectedItem);
		
	}

	public void add() {
		new ModifView(0,m);
		
	}

}
