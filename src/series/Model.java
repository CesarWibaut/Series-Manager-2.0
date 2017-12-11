package series;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {

	private ArrayList<Serie> series;
	private int nbEpisodes = 0, dureeAvg = 0, nbVus = 0;
	private String tpsRestant = "0 jours 0 h 0 m";

	public Serie getSerie(String s) {
		for(Serie se : series) {
			if(se.getNom().equals(s)) {
				return se;
			}
		}
		return null;
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

	}

	public void init() throws Exception {
		Parser p = new Parser();
		setSeries(p.getSeries());
		setChanged();
		notifyObservers("oui");
	}

	public ArrayList<Serie> getSeries() {
		return series;
	}

	public void setSeries(ArrayList<Serie> series) {
		this.series = series;
	}

	public ArrayList<String> getNoms() {
		ArrayList<String> l = new ArrayList<String>();
		for (Serie s : series) {
			l.add(s.getNom());
		}
		l.add("Total");
		return l;
	}

	public void click(String selectedItem) {
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
	
	
	private void selectTotal() {
		 nbEpisodes=0;
		 dureeAvg=0;
		 nbVus=0;
		 tpsRestant= "0 jours 0 h 0 m";
		 int tpsTotal=0;
		 for(Serie s : series) {
			 nbEpisodes += s.getNbEpisodes();
			 nbVus+= s.getNbVus();
			 tpsTotal += s.getDureeRestanteInt();
		 }
		 if(nbEpisodes-nbVus != 0)
			 dureeAvg = tpsTotal/(nbEpisodes-nbVus);
		 Serie tps = new Serie("ptdr",1,tpsTotal,0);
		 tpsRestant=tps.getDureeRestanteString();
		 setChanged();
		 notifyObservers();
		
	}

	public void add(String text, String text2, String text3, String text4) {
		Serie tmp = new Serie(text, Integer.valueOf(text2), Integer.valueOf(text3),Integer.valueOf(text4));
		series.add(tmp);
		setChanged();
		notifyObservers("oui");
	}

	public void remove(String selectedItem) {
		for(Serie s : series) {
			if(s.getNom().equals(selectedItem)) {
				series.remove(s);
				break;
			}
		}
		setChanged();
		notifyObservers("oui");
		
	}

	public void edit(String selectedItem) {
		// TODO Auto-generated method stub
		
	}
	public void edit(String text, String text2, String text3, String text4) {
		for(Serie s : series) {
			if(s.getNom().equals(text)) {
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
}
