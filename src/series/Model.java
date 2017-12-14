package series;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Observable;

public class Model extends Observable {

	private ArrayList<Serie> series;
	private int nbEpisodes = 0, dureeAvg = 0, nbVus = 0;
	private String tpsRestant = "0 jours 0 h 0 m";
	private ArrayList<String> list;

	public Serie getSerie(String s) {
		for (Serie se : series) {
			if (se.getNom().equals(s)) {
				return se;
			}
		}
		return null;
	}

	public ArrayList<String> getList() {
		return list;
	}

	public String getTpsRestant() {
		return tpsRestant;
	}

	public void setTpsRestant(String tpsRestant) {
		this.tpsRestant = tpsRestant;
	}

	public int getNbEpisodes() {
		return nbEpisodes;
	}

	public void setNbEpisodes(int nbEpisodes) {
		this.nbEpisodes = nbEpisodes;
	}

	public int getDureeAvg() {
		return dureeAvg;
	}

	public void setDureeAvg(int dureeAvg) {
		this.dureeAvg = dureeAvg;
	}

	public int getNbVus() {
		return nbVus;
	}

	public void setNbVus(int nbVus) {
		this.nbVus = nbVus;
	}

	public Model() {
		list = new ArrayList<String>();
	}

	public void init() throws Exception {
		Parser p = new Parser();
		setSeries(p.getSeries());
		search("");
		setChanged();
		notifyObservers("oui");
	}

	public ArrayList<Serie> getSeries() {
		return series;
	}

	public void setSeries(ArrayList<Serie> series) {
		this.series = series;
	}

	public void getNoms() {

		list.clear();
		for (Serie s : series) {
			list.add(s.getNom());
		}
		sort();
		list.add("Total");
	}

	private void sort() {
		class StringComparator implements Comparator<String> {
			public int compare(String obj1, String obj2) {
				return obj1.compareToIgnoreCase(obj2);
			}
		}
		list.sort(new StringComparator());

	}

	public void click(String selectedItem) {
		if (selectedItem != null) {
			if (selectedItem.equals("Total")) {
				selectTotal();
			} else {
				for (Serie s : series) {
					if (s.getNom().equals(selectedItem)) {
						setNbEpisodes(s.getNbEpisodes());
						setDureeAvg(s.getDureeAvg());
						setNbVus(s.getNbVus());
						setTpsRestant(s.getDureeRestanteString());
						setChanged();
						notifyObservers();
						break;
					}
				}
			}
		}
	}

	private void selectTotal() {
		nbEpisodes = 0;
		dureeAvg = 0;
		nbVus = 0;
		tpsRestant = "0 jours 0 h 0 m";
		int tpsTotal = 0;
		for (Serie s : series) {
			nbEpisodes += s.getNbEpisodes();
			nbVus += s.getNbVus();
			tpsTotal += s.getDureeRestanteInt();
		}
		if (nbEpisodes - nbVus != 0)
			dureeAvg = tpsTotal / (nbEpisodes - nbVus);
		Serie tps = new Serie("ptdr", 1, tpsTotal, 0);
		tpsRestant = tps.getDureeRestanteString();
		setChanged();
		notifyObservers();

	}

	public void add(String text, String text2, String text3, String text4) {
		Serie tmp = new Serie(text, Integer.valueOf(text2), Integer.valueOf(text3), Integer.valueOf(text4));
		series.add(tmp);
		setChanged();
		notifyObservers("oui");
	}

	public void remove(String selectedItem) {
		for (Serie s : series) {
			if (s.getNom().equals(selectedItem)) {
				series.remove(s);
				break;
			}
		}
		setChanged();
		notifyObservers("oui");

	}

	public void edit(String text, String text2, String text3, String text4) {
		for (Serie s : series) {
			if (s.getNom().equals(text)) {
				s.setNbEpisodes(Integer.valueOf(text2));
				setNbEpisodes(s.getNbEpisodes());
				s.setDureeAvg(Integer.valueOf(text3));
				setDureeAvg(s.getDureeAvg());
				s.setNbVus(Integer.valueOf(text4));
				setNbVus(s.getNbVus());
				setTpsRestant(s.getDureeRestanteString());
				break;
			}
		}
		setChanged();
		notifyObservers();

	}

	public void close() throws Exception {
		Parser p = new Parser();
		p.close(series);

	}

	public void search(String text) {
		if (text != null) {
			list.clear();
			if (text.length() == 0)
				getNoms();
			else {
				for (Serie s : series) {
					if (s.getNom().length() >= text.length()
							&& s.getNom().substring(0, text.length()).toLowerCase().equals(text.toLowerCase())) {
						list.add(s.getNom());
					}
				}
				sort();
			}
			
			
			setChanged();
			notifyObservers("oui");
		}

	}
}
