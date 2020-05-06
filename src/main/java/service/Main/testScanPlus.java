package service.Main;

import service.Algorithm.plusPlus;
import service.Graph.graph;
import service.ReadFile.readGraph;

public class testScanPlus {

    public static void main(String[] args) {
//
        String filename = "D:\\Projects\\jalal_Software\\ScanSoftware\\src\\main\\java\\Input_Files\\testGraph.txt";

        readGraph rd = new readGraph();
        graph gr = rd.selfGenerated(filename);
//        System.out.println(gr.toString());
////        System.out.println(gr.getNeighbor(0));
//        HashSet<Integer> U = new HashSet<>();
//        U.add(0);
//        U.add(1);
//        U.add(2);
//        U.add(4);
//        U.add(5);
//        gr.deleteItems(U);
//        System.out.println(gr.toString());
//
//        System.out.println(gr.getExistsVertices());
        ;

//        Queue<Integer> U = new LinkedList<>();
//        ArrayList<Integer> vt = new ArrayList<>();
//        vt.add(0);
//        vt.add(1);
//        vt.add(2);
//        vt.add(4);
//        vt.add(6);
//        U.addAll(gr.getVertices());
//        for (int i: U){
//            System.out.println(i);
//        }
//        System.out.println();
//        System.out.println(" U " + U);
//        U.removeAll(vt);
//        System.out.println("2U "+U + " " + U.size());

        plusPlus spl = new plusPlus(gr, 0.6f, 3);
//        Queue<Integer> dtar = new LinkedList<>();
//        dtar.add(2);
//        dtar.add(4);
//        dtar.add(5);
//        System.out.println(dtar + " DTAR   " + dtar.poll() + "  " + dtar);

//
//        spl.conTar(dtar);
        spl.executeScanAlgorithm();
//        System.out.println(spl.executeScanAlgorithm());
//        spl.executeScanAlgorithm();

    }
}
