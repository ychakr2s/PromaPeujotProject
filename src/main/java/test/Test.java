package test;

import service.Algorithm.scanAlgorithm;
import service.Graph.graph;
import service.ReadFile.readGraph;

/*
 ** This Class tests the Algorithm according the given Results from the Literature
 ** (SCAN++:Efficient Algorithm for Finding Clusters, Hubs and Outlierts on Large-scale Graphs) Seite 1181.
 ** I found that the results on my Implementation corresponds the results of the Paper.
 */

public class Test {

    public static void main(String[] args) {
        /*
         ** This Step get the Graph-file which is intended to implement the Algorithm on it
         */
        String filename = "D:\\Projects\\scanSoftware\\src\\main\\java\\Input_Files\\testGraph.txt";

        readGraph rd = new readGraph();
        graph gr = rd.initialize_Graph(filename);

        // print the Graph
        System.out.println(gr.toString());

        scanAlgorithm sc = new scanAlgorithm(gr, 0.6f, 3f);

        // Execute the Algorithm Scan
        sc.executeScanAlgorithm();

        /*
         ** Output of the Implementation
         */
        System.out.println(sc.toString());
    }

}
