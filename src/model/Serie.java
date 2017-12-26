package model;

public class Serie {
	private String nom;
	private int nbEpisodes;
	private int dureeAvg;
	private int nbVus;
	
	public Serie(String nom, int nbEpisodes, int dureeAvg, int nbVus) {
		super();
		this.nom = nom;
		this.nbEpisodes = nbEpisodes;
		this.dureeAvg = dureeAvg;
		this.nbVus = nbVus;
	}
	
	public Serie() {}
	
	public void setNom(String nom) {
		this.nom = nom;
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

	public String getNom() {
		return nom;
	}
	
	public int getDureeRestanteInt() {
		return (nbEpisodes - nbVus)*dureeAvg;
	}
	
	public String getDureeRestanteString() {
		String jours = (getDureeRestanteInt()/60)/24 + "";
		String heures = (getDureeRestanteInt()/60)%24 + "";
		String minutes = (getDureeRestanteInt())%60 + "";
		
		return jours + " jours " + heures + " h " + minutes +" m";
	}
	
	public String toString() {
		return nom;
	}
}
