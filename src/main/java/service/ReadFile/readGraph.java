
 /*
  ** This Method compute the Structural similarity
  */


 /*
  ** This Method returns the Epsilon-Neighbourhood
  */

 /*
  ** This Method checks whether a Vertex is core or not
  */

 /*
  ** This Method computes the Direct Structure Reachability (DirREACH)
  */

 package service.ReadFile;

 import service.Graph.graph;
 import com.google.gson.Gson;

 import java.io.BufferedReader;
 import java.io.FileReader;
 import java.io.IOException;
 import java.nio.file.Path;
 import java.nio.file.Paths;
 import java.util.Iterator;

 public class readGraph {
     public int edges;

     public readGraph() {
     }

     /*
      * This method read a File and produce a Graph.
      */
     public graph dimacsToGraph(String filename) {

         Path path = Paths.get(filename);
         graph gr = null;

         try {
             BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(path)));
             String line = reader.readLine();
             while (line != null) {
                 String[] splited = line.split("\\s+");
                 if (splited[0].equals("p")) {
                     gr = new graph(Integer.parseInt(splited[2]));
                     gr.setEdge(Integer.parseInt(splited[3]));
//                    gr.computeDensity();
                 }

                 if (splited[0].equals("e")) {
                     assert gr != null;
                     gr.addEdge(Integer.parseInt(splited[1]) - 1, Integer.parseInt(splited[2]) - 1);
                 }

                 line = reader.readLine();
             }
             reader.close();

         } catch (IOException e) {
             e.printStackTrace();
         }
         return gr;
     }

     public graph selfGenerated(String filename) {

         Path path = Paths.get(filename);
         graph gr = null;

         try {
             BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(path)));
             String line = reader.readLine();
             while (line != null) {
                 String[] splited = line.split("\\s+");
                 if (splited[0].equals("p")) {
                     gr = new graph(Integer.parseInt(splited[1]));
                     gr.setEdge(Integer.parseInt(splited[2]));
//                    gr.computeDensity();
                 } else if (splited[0].equals("")) {
                     break;
                 } else {
                     assert gr != null;
                     gr.addEdge(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]));
                 }

                 line = reader.readLine();
             }
             reader.close();

         } catch (IOException e) {
             e.printStackTrace();
         }
         return gr;
     }

     /*
      * this method get a Json file and parse it to Java. It is parsed to Graph.
      */
     public graph jsonToGraph(String filename) {

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
