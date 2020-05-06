package service.CreateJson;

import java.util.ArrayList;
import java.util.HashSet;

public class scanJson {
    String name;
    ArrayList<Integer> outliers;
    ArrayList<Integer> hubs;
    HashSet[] Clusters;
    int numberCluster;
    ArrayList<Integer> generalCluster;
    double timeComplexity;

    public scanJson(String name, ArrayList<Integer> outliers, ArrayList<Integer> hubs, HashSet[] outputCluster, int num, ArrayList<Integer> generalCluster, double timeComplexity) {
        this.name = name;
        this.outliers = outliers;
        this.hubs = hubs;
        this.Clusters = outputCluster;
        this.numberCluster = num;
        this.generalCluster = generalCluster;
        this.timeComplexity = timeComplexity;
    }
}
