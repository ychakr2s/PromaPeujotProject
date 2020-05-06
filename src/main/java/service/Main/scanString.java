package service.Main;

import service.Algorithm.scanAlgorithm;
import service.Graph.graph;
import service.ReadFile.readGraph;

public class scanString {
    public static void main(String[] args) {

        String filename = "D:\\Projects\\jalal_Software\\ScanSoftware\\src\\main\\java\\Input_Files\\testGraph.txt";

        readGraph rd = new readGraph();
        graph gr = rd.selfGenerated(filename);

        // print the Graph
        System.out.println(gr.toString());

        scanAlgorithm sc = new scanAlgorithm(gr, 0.6f, 3f);

        // Execute the Algorithm Scan
        sc.executeScanAlgorithm();

        System.out.println(sc.toString());
    }
}