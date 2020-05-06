package service.Algorithm;

import service.Graph.graph;

import java.util.*;

public class plusPlus {
    /*
     ** The Constructor of the Class (Algorithm Scan) and their Attributes
     ** In the Constructor will initialise the Attributes for the Object
     */
    float epsilon;
    float mu;
    graph gr;

    Queue<Integer> U;
    boolean unvisited[];

    boolean discoverd[];
    HashSet[] convergedTAR;
    HashSet[] Lp;

    ArrayList<Integer> labelCp;

    // This Variable contains all Clusters
    HashSet[] outputCluster;

    HashSet<Integer> localCluster;
    int clusterSum;
    // This Variable will be used for Generate the ClusterID.
    int id;

    ArrayList<Integer> hubs;
    ArrayList<Integer> outliers;

    double timeComplexity;

    public plusPlus(graph gr, float eps, int mu) {

        this.epsilon = eps;
        this.mu = mu;
        this.gr = gr;
        this.U = new LinkedList<>();
        this.U.addAll(gr.getVertices());
        this.unvisited = new boolean[gr.getNumVertices()];
        this.discoverd = new boolean[gr.getNumVertices()];
        this.convergedTAR = new HashSet[gr.getNumVertices()];
        this.Lp = new HashSet[gr.getNumVertices()];
        for (int i = 0; i < gr.getNumVertices(); i++) {
            this.convergedTAR[i] = new HashSet();
            this.Lp[i] = new HashSet();
        }
        this.localCluster = new HashSet<>();
        this.labelCp = new ArrayList<>();
//        for (int i = 0; i < gr.getNumVertices(); i++) {
////            this.clusterID.add(id);
//            this.outputCluster[i] = new HashSet();
//        }
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

    public Queue<Integer> getU() {
        return U;
    }

    public boolean[] getDiscoverd() {
        return discoverd;
    }

    public void setDiscoverd(boolean[] discoverd) {
        this.discoverd = discoverd;
    }

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
     ** This method increments the ClusterID
     */
    public int generateNewClusterID() {
        return ++this.id;
    }

    public void labelCP(HashSet<Integer> lp) {
        for (int v : lp) {
            getLabelCp().add(v);
        }
    }

    public ArrayList<Integer> convertToArrayList(HashSet<Integer> set) {
        ArrayList<Integer> converted = new ArrayList<>(set);

        return converted;
    }

    public ArrayList<Integer> convertToArrayList(Queue<Integer> set) {
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
//        System.out.println("U " + u);
        ArrayList<Integer> dtar = new ArrayList<>();
        for (int v : V) {
            if (!getEpsilonNeighbor(u).contains(v) && !intersectionDTAR(u, v).isEmpty() && !getNeighbors(u).contains(v)) {
                dtar.add(v);
                discoverd[v] = true;
            }
        }
        return dtar;
    }

    public HashSet<Integer> convergedTar(ArrayList<Integer> dtar) {

        return null;
    }

    public HashSet<Integer> conTar(ArrayList<Integer> dtar, HashSet<Integer> retu) {

        for (int v : dtar) {
            System.out.println(v + " v");
            ArrayList<Integer> expDtar = DTAR(v, gr.getVertices());
            System.out.println("expDtar " + expDtar);

            if (checkDiscoverd(expDtar)) {
                System.out.println("checkDiscoverd(expDtar) " + checkDiscoverd(expDtar));
                ArrayList<Integer> expDta = DTAR(getUndiscoveredVertex(expDtar), convertToArrayList(getU()));
                System.out.println("expDta " + expDta);
                System.out.println("getUndiscoveredVertex(expDtar) " + getUndiscoveredVertex(expDtar));
//                unvisited[v]=true;
                setVisited(convertToHash(expDta), discoverd);
//                public void setVisited(HashSet<Integer> loc, boolean arr[]) {

                conTar(expDta, retu);
            } else if (!discoverd[v]) {
                retu.add(v);
            }
        }

        return convertToHash(dtar);
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
            if (!getDiscoverd()[v]) {
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

    public void setVisited(HashSet<Integer> loc, boolean arr[]) {
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

    public int choosePivot(HashSet<Integer> Tu) {
        if (Tu.size() > 0) {
            for (int v : Tu) {
                if (!unvisited[v]) {
                    return v;
                }
            }
        }
        return -1;
    }

    public HashSet<Integer> addToVtu(HashSet[] tu) {
        HashSet<Integer> vt = new HashSet<>();
        for (int i = 0; i < tu.length; i++) {
            if (tu[i].size() > 0) {
                vt.addAll(tu[i]);
            }
        }
        return vt;
    }

    //    @Override
    public ArrayList<Integer> executeScanAlgorithm() {
        ArrayList<Integer> B = new ArrayList<>(); // Bridge
        HashSet<Integer> conv = new HashSet<>();
        HashSet<Integer> mitn = new HashSet<>();

        int count = 0;
        ArrayList<Integer> Vtu = new ArrayList<>();
        int j = 0;
        while (!U.isEmpty()) {
            int v = U.poll();
            System.out.println("U " + v + "  U " + U);
            convergedTAR[v].add(v);

            int vert = 0;

            vert = choosePivot(convergedTAR[v]);
            System.out.println(" vert = choosePivot(convergedTAR[v]); " + vert);

            while (vert >= 0) { //  we have unvisited pivots in Tu

                if (!unvisited[vert]) {

                    unvisited[vert] = true;
                    if (isCore(vert)) { // Definition 9 (Local Cluster)

                        Lp[v].addAll(getEpsilonNeighbor(vert));
                        localCluster.addAll(getEpsilonNeighbor(vert));

                    } else {
                        Lp[v].add(vert);
                        localCluster.add(vert);
                    }

                    labelCP(localCluster);
                    setVisited(localCluster, discoverd);
//                    ArrayList<Integer> Uv = new ArrayList<>();
//                    Uv.add(vert);
//                    Uv.addAll(getU());
                    // get T[p] by Definition 6;
                    ArrayList<Integer> dtr = DTAR(vert, gr.getVertices());
                    setVisited(localCluster, unvisited);
                    dtr.add(vert);
                    System.out.println("DTRTT " + dtr);

                    conv = conTar(dtr, mitn);
                    System.out.println("conv " + conv);

                    dtr.clear();
                    convergedTAR[v].addAll(conv);
                    for (int i = 0; i < convergedTAR.length; i++) {
                        System.out.print(" convergedTARconvergedTAR" + convergedTAR[i]);
                    }
                    conv.clear();
                    j = convergedTAR.length;
                    System.out.println();

                    for (int i = 0; i < convergedTAR.length; i++) {
                        System.out.print(" " + convergedTAR[i]);
                    }
                    System.out.println();

                    vert = choosePivot(convergedTAR[v]);
                    System.out.println("vert " + vert);
                }

            }
            // Vtu Definition 12
            HashSet<Integer> can = new HashSet<>();
            can = addToVtu(convergedTAR);
            Vtu = CandidateCluster(convertToArrayList(can));
            U.removeAll(Vtu);

            /*
             ** U = U\Vtu , B = B U {Neps[p]\{p}}
             */
            B.addAll(getEpsilonNeighbor(v));
            B.remove(B.indexOf(v));
            gr.deleteItems(convertToHash(Vtu));
            Vtu.clear();

        }

        System.out.println("convergedTAReeee ");
        for (int i = 0; i < convergedTAR.length; i++) {
            System.out.print(" " + convergedTAR[i]);
        }
        System.out.println(" ");
        System.out.println(" lp ");

        for (int i = 0; i < Lp.length; i++) {
            System.out.print(" " + Lp[i]);
        }

        return Vtu;
    }

    public HashSet convertToHash(ArrayList<Integer> ar) {
        HashSet<Integer> hash = new HashSet<>();
        hash.addAll(ar);
        return hash;
    }

}
