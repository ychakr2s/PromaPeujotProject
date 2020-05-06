package service.Algorithm;

import service.CreateJson.scanJson;
import service.Graph.graph;
import service.abstractAlgorithm.abstractScan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class scanAlgorithm implements abstractScan {

    /*
     ** The Constructor of the Class (Algorithm Scan) and their Attributes
     ** In the Constructor will initialise the Attributes for the Object
     */
    float epsilon;
    float mu;
    graph gr;
    boolean unclassified[];

    // This Variable contains all Clusters
    HashSet[] outputCluster;

    ArrayList<Integer> clusterID;
    int clusterSum;
    // This Variable will be used for Generate the ClusterID.
    int id;

    ArrayList<Integer> hubs;
    ArrayList<Integer> outliers;

    double timeComplexity;

    public scanAlgorithm(graph gr, float eps, float mu) {

        this.epsilon = eps;
        this.mu = mu;
        this.gr = gr;
        this.unclassified = new boolean[gr.getNumVertices()];
        this.clusterID = new ArrayList<>(gr.getNumVertices());
        this.clusterSum = 0;
        this.outputCluster = new HashSet[gr.getNumVertices()];
        this.id = 0;
        for (int i = 0; i < gr.getNumVertices(); i++) {
            this.clusterID.add(id);
            this.outputCluster[i] = new HashSet();
        }
        this.hubs = new ArrayList<>();
        this.outliers = new ArrayList<>();
        this.timeComplexity = 0;
    }

    /*
     ** Setter and Getter Methods for the above Attributes
     */
    public void setEpsilon(float epsilon) {
        this.epsilon = epsilon;
    }

    public float getEpsilon() {
        return epsilon;
    }

    public void setMu(float mu) {
        this.mu = mu;
    }

    public float getMu() {
        return mu;
    }

    public ArrayList<Integer> getClusterID() {
        return clusterID;
    }

    public void setClusterID(ArrayList<Integer> clusterID) {
        this.clusterID = clusterID;
    }

    public int getClusterSum() {
        return clusterSum;
    }

    public void setClusterSum(int clusterSum) {
        this.clusterSum = clusterSum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashSet[] getOutputCluster() {
        return outputCluster;
    }

    public void setOutputCluster(HashSet[] outputCluster) {
        this.outputCluster = outputCluster;
    }

    public ArrayList<Integer> getHubs() {
        return hubs;
    }

    public void setHubs(ArrayList<Integer> hubs) {
        this.hubs = hubs;
    }

    public ArrayList<Integer> getOutliers() {
        return outliers;
    }

    public void setOutliers(ArrayList<Integer> outliers) {
        this.outliers = outliers;
    }

    public double getTimeComplexity() {
        return timeComplexity;
    }

    public void setTimeComplexity(double timeComplexity) {
        this.timeComplexity = timeComplexity;
    }

    /*
     ** unclassified all Vertices
     */
    public void setUnclassified() {
        this.unclassified = new boolean[gr.getNumVertices()];
    }

    public boolean[] getUnclassified() {
        return this.unclassified;
    }

    /*
     ** This Method compute the Number of a Set and give it back
     */
    public int getSetNumber(Set<Integer> set) {
        return set.size();
    }

    /*
     ** This method returns the Neighbors of the Vertex v without v
     */
    public HashSet<Integer> getNeighbors(int v) {
        return gr.getNeighbor(v);
    }

    /*
     ** This method computes the Structural Similarity.
     ** If the Number of the Neighbors are 0, the Method returns -1. That is mean it is wrong
     */
    public double structuralSimilarity(int v1, int v2) {

        double numerator = gr.getIntersectionNorm(v1, v2); // Nenner
        double denominator = gr.getNormNeighborSet(v1) * gr.getNormNeighborSet(v2); // Zaehler

        if (denominator > 0) {
            return numerator / Math.sqrt(denominator);
        } else
            return -1;
    }

    /*
     ** Set of nodes in the ϵ-neighborhood of node v.
     ** This method returns the epsilon Neighbor Set.
     */
    public HashSet<Integer> getEpsilonNeighbor(int v) {
        HashSet<Integer> neighbor = gr.neighborhood(v);
        HashSet<Integer> epsNeighbor = new HashSet<>();

        for (int v2 : neighbor) {
            if (structuralSimilarity(v, v2) > getEpsilon()) {
                epsNeighbor.add(v2);
            }
        }

        return epsNeighbor;
    }

    /*
     ** This Method checks whether a Vertex is Core or not
     */
    public boolean isCore(int v) {
        return getSetNumber(getEpsilonNeighbor(v)) >= getMu();
    }

    /*
     ** The method checks whether is a Vertex is (dirReach) Direct Structure Reachability.
     */
    public boolean dirREACH(int v, int w) {
        return isCore(v) && getEpsilonNeighbor(v).contains(w);
    }

    /*
     ** This method returns the List of Direct Structure Reachability regarding the intended Vertex.
     */
    public ArrayList<Integer> DirREACHList(int v, ArrayList<Integer> vertices) {
        ArrayList<Integer> reachable = new ArrayList<>();
        for (int u : vertices) {
            if (dirREACH(v, u))
                reachable.add(u);
        }
        return reachable;
    }

    /*
     ** This method increments the ClusterID
     */
    public int generateNewClusterID() {
        return ++this.id;
    }

    /*
     ** This method returns only the local Clusters.
     ** It deletes the repetitions of Items and empty fields in the List. It retains only the used Clusters.
     */
    public HashSet[] getRefinedClusters(HashSet[] out) {

        int j = 0;
        for (int i = 0; i < gr.getNumVertices(); i++) {
            if (!out[i].isEmpty()) {
                j++;
            }
        }
        HashSet[] localCluster = new HashSet[j];

        for (int i = 0; i < j; i++) {
            localCluster[i] = new HashSet();
        }
        for (int i = 0; i < j; i++) {
            localCluster[i] = out[i];
        }

        return localCluster;
    }

    /*
     ** This method forms a General Cluster
     */
    public ArrayList<Integer> generalCluster(HashSet[] out) {
        HashSet[] localCluster = new HashSet[1];
        ArrayList<Integer> localCluste = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
            localCluster[i] = new HashSet();
        }
        for (int i = 0; i < out.length; i++) {
            localCluste.addAll(out[i]);
        }
        return localCluste;
    }

    /*
     ** This method checks whether a Vertex is a Core or not.
     */
    public boolean isHub(int v) {
        ArrayList<Integer> neighbor = new ArrayList<>(getNeighbors(v));
        int j = 0;

        for (int nei : neighbor) {
            for (int i = 0; i < neighbor.size(); i++) {
                if (!clusterID.get(nei).equals(clusterID.get(neighbor.get(i))) && (j != 0)) {
                    return true;
                }
                j++;
            }
        }
        return false;
    }

    /**
     * * This method implement the Steps of the Algorithm Scan
     **/
    @Override
    public scanJson executeScanAlgorithm() {
        // This Variable used to compute the Complexity time.
        double start = System.currentTimeMillis();

        HashSet<Integer> locCluster = new HashSet<>();
        ArrayList<Integer> vertices = gr.getVertices();
        ArrayList<Integer> nonMember = new ArrayList<>();
        boolean[] unclassfied = getUnclassified();
        int i = 0;
        int k = 12;
        for (int w = 0; w < vertices.size(); w++) {
            int v = vertices.get(k);
            if (!unclassfied[v]) { // checks if v is classified
                // check whether v is a core
                if (isCore(v)) {
                    // generate new clusterID;
                    clusterID.set(vertices.indexOf(v), generateNewClusterID());

                    locCluster.add(v);

                    // insert all x ∈ Nε (v) into queue Q;
                    Set<Integer> Q = new HashSet<>(getEpsilonNeighbor(v));

                    while (!Q.isEmpty()) {
                        int y = Q.stream().findFirst().get();
                        ArrayList<Integer> R = DirREACHList(y, vertices);
                        for (int x : R) {
                            if (!unclassfied[vertices.indexOf(x)] || nonMember.contains(x)) {
                                clusterID.set(vertices.indexOf(x), id);
                                locCluster.add(x);
                                if (nonMember.contains(x)) {
                                    nonMember.remove(nonMember.indexOf(x));
                                }
                            }
                            if (!unclassfied[vertices.indexOf(x)]) {
                                unclassfied[vertices.indexOf(x)] = true;
                                Q.add(x);
                            }
                        }
                        Q.remove(y);
                    }

                    outputCluster[i].addAll(locCluster);
                    locCluster.clear();
                    i++;
                } else {
                    nonMember.add(v);
                }
            }
            k = w;
        }

        for (int n : nonMember) {
            if (isHub(n)) {
                this.hubs.add(n);
            } else
                this.outliers.add(n);
        }
        double time = (System.currentTimeMillis() - start);
        /*
         ** In order to get the Time Complexity in second you can divide time in 1000 (/ 1000)
         */
        setTimeComplexity(time);

        return new scanJson("Scan Algorithm", getOutliers(), getHubs(), getRefinedClusters(getOutputCluster()), getRefinedClusters(getOutputCluster()).length,
                generalCluster(getOutputCluster()), getTimeComplexity());
    }

    public String toString() {

        StringBuilder string = new StringBuilder();
        string.append("+++++++++ This is the representation of the SCAN Algorithm: +++++++++ \n");

        string.append("outliers: ").append(getOutliers()).append("\n").append("hubs: ").append(getHubs()).append("\n");
        string.append("Clusters: ");

        for (HashSet pCrawl : getRefinedClusters(getOutputCluster())) {
            string.append(" -> ").append(pCrawl);
        }
        string.append("\n").append("numberCluster: ").append(getRefinedClusters(getOutputCluster()).length).append("\n");
        string.append("generalCluster: ").append(generalCluster(getOutputCluster())).append("\n");
        string.append("timeComplexity: ").append(getTimeComplexity()).append(" ms");

        return string.toString();
    }
}