package service.Algorithm;


import service.Graph.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class scanPlusPlus {
    /*
     ** The Constructor of the Class (Algorithm Scan) and their Attributes
     ** In the Constructor will initialise the Attributes for the Object
     */
    float epsilon;
    float mu;
    graph gr;
    boolean unvisited[];
    ArrayList<Integer> convergedTAR;

    ArrayList<Integer> labelCp;

    // This Variable contains all Clusters
    HashSet[] outputCluster;

    ArrayList<Integer> localCluster;
    int clusterSum;
    // This Variable will be used for Generate the ClusterID.
    int id;

    ArrayList<Integer> hubs;
    ArrayList<Integer> outliers;

    double timeComplexity;

    public scanPlusPlus(graph gr, float eps, int mu) {

        this.epsilon = eps;
        this.mu = mu;
        this.gr = gr;
        this.unvisited = new boolean[gr.getNumVertices()];
        this.convergedTAR = new ArrayList<>();
        this.localCluster = new ArrayList<>();
        this.clusterSum = 0;
        this.outputCluster = new HashSet[gr.getNumVertices()];
        this.id = 0;
        this.labelCp = new ArrayList<>();
        for (int i = 0; i < gr.getNumVertices(); i++) {
//            this.clusterID.add(id);
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

//    public ArrayList<Integer> getClusterID() {
//        return clusterID;
//    }
//
//    public void setClusterID(ArrayList<Integer> clusterID) {
//        this.clusterID = clusterID;
//    }

    public int getClusterSum() {
        return clusterSum;
    }

    public void setClusterSum(int clusterSum) {
        this.clusterSum = clusterSum;
    }

    public ArrayList<Integer> getLabelCp() {
        return labelCp;
    }

    public void setLabelCp(ArrayList<Integer> labelCp) {
        this.labelCp = labelCp;
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
    public void setUnvisited() {
        this.unvisited = new boolean[gr.getNumVertices()];
    }

    public boolean[] getUnvisited() {
        return this.unvisited;
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
//                if (!clusterID.get(nei).equals(clusterID.get(neighbor.get(i))) && (j != 0)) {
//                    return true;
//                }
                j++;
            }
        }
        return false;
    }

    public void labelCP(ArrayList<Integer> lp) {
        for (int v : lp) {
            getLabelCp().add(v);
        }
    }

    public ArrayList<Integer> convertToArrayList(HashSet<Integer> set) {
        ArrayList<Integer> converted = new ArrayList<>(set);

        return converted;
    }

    public ArrayList<Integer> intersectionDTAR(int u, int v) {
        ArrayList<Integer> set1 = convertToArrayList(getEpsilonNeighbor(u));
        ArrayList<Integer> set2 = convertToArrayList(gr.neighborhood(v));
        set1.retainAll(set2);
        return set1;
    }

    /*
     ** Directly Two-hop-away Reachable (DTAR)
     ** T[u] = {v ∈ V : v ∈/ Neps[u] and Neps[u] ∩ N[v] /= ∅}.
     */
    public ArrayList<Integer> DTAR(int u, ArrayList<Integer> V) {
        System.out.println("U " + u);
        ArrayList<Integer> dtar = new ArrayList<>();
        for (int v : V) {
            if (!getEpsilonNeighbor(u).contains(v) && !intersectionDTAR(u, v).isEmpty() && !getNeighbors(u).contains(v)) {
                dtar.add(v);
                unvisited[u] = true;
            }
        }
        return dtar;
    }

    //(CONVERGED TAR).
    public ArrayList<Integer> convergedTAR(int v, ArrayList<Integer> dtar) {
        ArrayList<Integer> condtar = new ArrayList<>();
        for (int i : dtar) {
            condtar = DTAR(i, gr.getVertices());
            if (checkDiscoverd(condtar)) {
                ArrayList<Integer> expDtar = DTAR(getUndiscoveredVertex(condtar), gr.getVertices());
                if (checkDiscoverd(expDtar)) {
                    ArrayList<Integer> expDta = convergedTAR(getUndiscoveredVertex(expDtar), gr.getVertices());
                }

            }
        }
        dtar.add(v);
        ArrayList<Integer> convergedTar = dtar;
        return convergedTar;
    }

    public ArrayList<Integer> conTar(ArrayList<Integer> dtar,ArrayList<Integer> retu) {
//        = new ArrayList<>();
        for (int v : dtar) {
            System.out.println("V " + v);

            System.out.println(v + " " + unvisited[v]);
            ArrayList<Integer> expDtar = DTAR(v, gr.getVertices());
            System.out.println(expDtar);
            printArray(unvisited);

            if (checkDiscoverd(expDtar)) {
                System.out.println("checkDiscoverd(expDtar) " + checkDiscoverd(expDtar));
                ArrayList<Integer> expDta = DTAR(getUndiscoveredVertex(expDtar), gr.getVertices());
                System.out.println("expDta " + expDta);
                System.out.println("getUndiscoveredVertex(expDtar) " + getUndiscoveredVertex(expDtar));
//                unvisited[v]=true;
                conTar(expDta,retu);
            }
            retu.add(v);
        }
        return retu;
    }

    public boolean checkDiscoverd(ArrayList<Integer> discover) {
        for (int v : discover) {
            if (getUndiscoveredVertex(discover) != -1) {
                return true;
            }
        }
        return false;
    }

    public int getUndiscoveredVertex(ArrayList<Integer> discover) {
        for (int v :
                discover) {
            if (!getUnvisited()[v]) {
                return v;
            }
        }
        return -1;
    }
    /*
     ** This method returns th Vtu
     */

    public ArrayList<Integer> CandidateCluster(ArrayList<Integer> conTar) {
        HashSet<Integer> Neps = new HashSet<>();
        for (int v : conTar) {
            Neps.addAll(getEpsilonNeighbor(v));
        }
        Neps.addAll(conTar);

        ArrayList<Integer> candidateCluster = new ArrayList<>();
        candidateCluster.addAll(Neps);

        return candidateCluster;
    }

    public void setVisited(ArrayList<Integer> loc, boolean arr[]) {
        for (int v : loc) {
            arr[v] = true;
        }
    }

    public void printArray(boolean Array[]) {
        for (int i = 0; i < Array.length; i++) {
            System.out.print(Array[i] + " ");
        }
        System.out.println();
    }

    //    @Override
    public ArrayList<Integer> executeScanAlgorithm() {
        ArrayList<Integer> U = new ArrayList<>();
        U.addAll(gr.getVertices());
        ArrayList<Integer> B = new ArrayList<>(); // Bridge
        ArrayList<Integer> conv = new ArrayList<>();
        ArrayList<Integer> mitn = new ArrayList<>();

        int count = 0;
        ArrayList<Integer> Vtu = new ArrayList<>();
        while (!U.isEmpty()) {
            int v = U.get(count);
            convergedTAR.add(v);
            while (!unvisited[v]) { //  we have unvisited pivots in Tu
                System.out.println("nect "+ v);
                unvisited[v] = true;
                int p = convergedTAR.get(v);
                if (isCore(v)) { // Definition 9 (Local Cluster)
                    localCluster.addAll(getEpsilonNeighbor(v));
                } else
                    localCluster.add(v);
                labelCP(localCluster);
                System.out.println("localCluster " + localCluster);
                setVisited(localCluster, unvisited);
                printArray(unvisited);
                ArrayList<Integer> dtr = DTAR(v, gr.getVertices());
                setVisited(localCluster, unvisited);
                setVisited(dtr, unvisited);
                System.out.println("dtr " + dtr);
                printArray(unvisited);
                dtr.add(v);
                conv = conTar(dtr,mitn);
                System.out.println("CONV "+conv);
            }
            // Vtu Definition 12
            ArrayList<Integer> can = new ArrayList<>();
            can.addAll(convergedTAR);
            Vtu = CandidateCluster(can);
            U.removeAll(Vtu);

            /*
             ** U = U\Vtu , B = B U {Neps[p]\{p}}
             */
            B.addAll(getEpsilonNeighbor(v));
            B.remove(B.indexOf(v));
        }
        return Vtu;
    }
}