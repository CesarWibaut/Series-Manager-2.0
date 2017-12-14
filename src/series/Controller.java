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
		new ModifView(m);
		
	}

	public void remove(String selectedItem) {
		m.remove(selectedItem);
		
	}

	public void edit(String selectedItem) {
		new ModifView(m,selectedItem);
		
	}

	public void close() throws Exception {
		m.close();
		
	}

	public void search(String text) {
		m.search(text);
		
	}

}
