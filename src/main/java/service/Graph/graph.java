package service.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

public class graph {
    // No. of vertices
    private int V;
    // Adjacency List
    private ArrayList<Integer> vertices;
    private HashSet[] edges;
    private int edge;

    /*
     * Constructor
     */
    public graph(int v) {
        this.V = v;
        this.edges = new HashSet[V];

        for (int i = 0; i < V; ++i)
            edges[i] = new HashSet<>();

        // this had no relation with Edges.
        this.vertices = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            vertices.add(i);
        }
    }

    /*
     ** This Method returns the number of the vertices.
     */
    public int getNumVertices() {
        return this.V;
    }

    /*
     ** This Method returns all Vertices
     */
    public ArrayList<Integer> getVertices() {
        return this.vertices;
    }

    /*
     ** This Method sets number of Edges to the Graph
     * It is used in ReadGraph
     */

    public void setEdge(int a) {
        this.edge = a;
    }

    /*
     ** This Method gets the number of Edges of a Graph
     */
    public int getEdge() {
        return this.edge;
    }

    /*
     * This Method adds Edges between two Vertices
     */
    public void addEdge(int from, int to) {
        if (to > getNumVertices() || from > getNumVertices())
            System.out.println("The vertices does not exists");
        else {
            if (from != to) {
                neighborhood(from).add(to);
                neighborhood(to).add(from);
            }
        }
    }

    /*
     ** This Method returns all the Neighbors of a Vertex
     */
    public HashSet<Integer> neighborhood(int v) {
        if (edges[v].isEmpty()) {
            return edges[v];
        } else {
            edges[v].add(v);
            return this.edges[v];
        }

    }

    public HashSet<Integer> getNeighbor(int v) {
        edges[v].remove(v);
        return this.edges[v];
    }
//    getExistsVertices()

    /*
     ** This Method returns the Intersection between two Sets of Neighbor Vertices
     */
    public Set<Integer> getNeighborsIntersection(int v1, int v2) {
        Set<Integer> set1 = neighborhood(v1);
        Set<Integer> set2 = neighborhood(v2);

        return set1.stream().filter(set2::contains).collect(Collectors.toSet());
    }

    /*
     ** This Method returns the Norm of a Result Intersection Set
     */
    public int getIntersectionNorm(int v1, int v2) {
        Set<Integer> set3 = getNeighborsIntersection(v1, v2);

        return set3.size();
    }

    /*
     ** This Method returns the Norm of a Neighbors Vertex
     */
    public int getNormNeighborSet(int v1) {
        return neighborhood(v1).size();
    }

    /*
     ** This method checks whether there is an Edge between two vertices.
     ** It checks whether a v1 and v 2 are neighbors
     */
    public boolean isNeighbor(int v1, int v2) {
        Iterator<Integer> it = neighborhood(v1).iterator();
        while (it.hasNext()) {
            int v = it.next();
            if (v == v2)
                return true;
        }
        return false;
    }

    /*
     * ----------------------- print methods ------------------------------/
     */
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("+++++++++ This is the representation of the Graph as Linked List: +++++++++ \n").append("\n");
        for (int v = 0; v < V; v++) {
            string.append(" Adjacency list of vertex ").append(v).append(" Edges of Vertex: ").append(":  ");

            for (Integer pCrawl : neighborhood(v)) {

                string.append(" -> ").append(pCrawl);
            }
            string.append("\n");
        }
        return string.toString();
    }

    public void deleteItems(HashSet<Integer> convertToHash) {
        for (int v : convertToHash) {
            deleteVertex(v);
        }

    }

    public void deleteVertex(int v) {
        HashSet<Integer> eg = new HashSet<>();
        eg = getNeighbor(v);
        for (int i : eg) {
            edges[i].remove(v);

        }
        edges[v].clear();

    }
}