package service.Main;

import service.Algorithm.scanAlgorithm;
import service.ReadFile.readGraph;
import com.google.gson.Gson;
import service.Graph.graph;
import service.CreateJson.scanJsonOutput;

public class scanJsonTest {
    public static void main(String[] args) {
        String filename = "D:\\Projects\\jalal_Software\\ScanSoftware\\src\\main\\java\\Input_Files\\testGraph.txt";

        readGraph rd = new readGraph();
        graph gr = rd.selfGenerated(filename);
        System.out.println(gr.toString());

        scanAlgorithm sc = new scanAlgorithm(gr, 0.6f, 3f);

        scanJsonOutput jso = new scanJsonOutput(sc.executeScanAlgorithm());

        Gson gs = new Gson();
        String json = gs.toJson(jso);

        System.out.println(json);
    }
}
