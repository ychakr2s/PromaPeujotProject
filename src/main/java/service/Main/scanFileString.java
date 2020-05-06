package service.Main;

import service.Algorithm.scanAlgorithm;
import service.Graph.graph;
import service.ReadFile.readGraph;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class scanFileString {
    public static void main(String[] args) throws IOException {

        String fileName = "D:\\Projects\\jalal_Software\\ScanSoftware\\src\\main\\java\\Output_Files\\output.txt";
        try {
            if (Files.exists(Paths.get(fileName))) {
                Files.delete(Paths.get(fileName));
                Files.createFile(Paths.get(fileName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String filename = "D:\\Projects\\jalal_Software\\ScanSoftware\\src\\main\\java\\Input_Files\\testGraph.txt";

        readGraph rd = new readGraph();
        graph gr = rd.selfGenerated(filename);

        scanAlgorithm sc = new scanAlgorithm(gr, 0.6f, 3f);

        // Execute the Algorithm Scan
        sc.executeScanAlgorithm();

        /*
         ** Write the Output in the File as a String Format
         */
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));

        String output = sc.toString();
        bw.write(output + System.getProperty("line.separator"));
        bw.close();

        System.out.println("Sehen Sie die Datei in Output_Files Folder");
    }
}
