package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteManager {
	
	private static WriteManager manager = null;
	
	private WriteManager (){
	}
	
	public static WriteManager getInstance(){
		if(manager == null){
			manager = new WriteManager();
		}
		return manager;
	}

	public void write(String path, String content) {
		
		File file = createControlFile(path);
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
			bw.write(content);
			bw.newLine();
			bw.close();
		} catch (IOException ioe) {
			// TODO here the exception must be handled to save the data to a pre-defined temp file.
			System.err.println(ioe);
		}
		System.out.println(content);
	}
	
	public File createControlFile(String path){
		File file = new File(path);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file;
	}
	
}
