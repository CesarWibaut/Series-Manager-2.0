package series;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Parser {
	String csvFile ;

	BufferedReader br = null;
	String line = "";
	String csvSplitBy = ",";

	public ArrayList<Serie> getSeries() throws Exception{
		ArrayList<Serie> list = new ArrayList<Serie>();
		BufferedReader reader = new BufferedReader(new FileReader("src\\resources\\series.csv"));
		Scanner scanner = null;
		int index = -1;
		while((line=reader.readLine())!=null) {
			Serie s = new Serie();
			scanner = new Scanner(line);
			scanner.useDelimiter(csvSplitBy);
			while(scanner.hasNext()) {
				index++;
				String data = scanner.next();
				switch(index) {
				case 0 : {
					s.setNom(data);
					break;
				}
				case 1 : {
					s.setNbEpisodes(Integer.valueOf(data));
					break;
				}
				case 2 : {
					s.setDureeAvg(Integer.valueOf(data));
					break;
				}
				case 3 : {
					s.setNbVus(Integer.valueOf(data));
					break;
				}
				
				}
				
			}
			index = -1;
			if(s.getNom() != null)
				list.add(s);
			
			
		}
		reader.close();
		return list;
	}
}