package service.ReadFile;

import service.Graph.graph;
import com.google.gson.Gson;
import service.Graph.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class jsonGraphCreator implements GraphCreator {

    /*
     * this method get a Json file and parse it to Java. It is parsed to Graph.
     */
    @Override
    public graph factoryGraph(String filename) {
        Gson gson = new Gson();
        graph gr = new graph(0);

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader(filename));

            //convert the json string back to object
            graph graph = gson.fromJson(br, graph.class);

            gr = new graph(graph.getNumVertices());

            for (int i = 0; i < graph.getNumVertices(); i++) {
                Iterator itr = graph.neighborhood(i).iterator();
                while (itr.hasNext()) {

                    double a = (double) itr.next();
                    int c = (int) a;
                    gr.addEdge(i, c);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return gr;
    }
}
