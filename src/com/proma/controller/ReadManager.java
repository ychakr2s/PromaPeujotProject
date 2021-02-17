package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadManager {

	
	public String readLastEntry(String path) {
		String lastEntry = null;
		ArrayList<String> inhalt = readAllEntries(path);
		if (!inhalt.isEmpty()){
			lastEntry = inhalt.get((inhalt.size())-1).toString();
		}
		return lastEntry;
	}
	
	public ArrayList<String> readAllEntries(String path) {
		ArrayList<String> inhalt = new ArrayList<>();
			try (BufferedReader br = new BufferedReader(new FileReader(path))) {
				String zeile;
				while ((zeile = br.readLine()) != null) {
					inhalt.add(zeile);
				}
			} catch (FileNotFoundException fe) {
				System.err.println(fe);
				// TODO here the exception must be handled to get the data from a pre-defined temp file.
				System.exit(-1);
			} catch (IOException ioe) {
				System.err.println(ioe);
			}
//			System.out.println(inhalt.get((inhalt.size())-1).toString());
		return inhalt;
	}
}
