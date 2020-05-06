package service.Main;

import service.Algorithm.scanAlgorithm;
import service.CreateJson.scanJson;
import service.CreateJson.scanJsonOutput;
import service.Graph.graph;
import service.ReadFile.readGraph;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class scanFileJson {

    public static void main(String[] args) throws IOException {

        String fileName = "D:\\Projects\\jalal_Software\\ScanSoftware\\src\\main\\java\\Output_Files\\output.json";
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
        scanJson out = sc.executeScanAlgorithm();

        /*
         ** Write the Output in the File as a JSON Format
         */
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true));

        scanJsonOutput jso = new scanJsonOutput(out);

        Gson gs = new Gson();
        String json = gs.toJson(jso);
        bw.write(json + System.getProperty("line.separator"));
        bw.write(" " + System.getProperty("line.separator"));

        bw.close();

        System.out.println("Sehen Sie die Datei in Output_Files Folder");
    }
}