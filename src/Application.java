import Graph.Graph;
import Graph.Graph2;
import Graph.Graph3;

public class Application {
    public static void main(String[] args) {
        Graph theGraph = new Graph(); //For DFS and BFS
       // Graph2 theGraph = new Graph2(); //For Safest Path
        //Graph3 theGraph = new Graph3(); //For Most Dangerous Path
        theGraph.addVertex("Suriname"); // 0 (start)
        theGraph.addVertex("Aruba"); // 1
        theGraph.addVertex("Curacao"); // 2
        theGraph.addVertex("Barbados"); // 3
        theGraph.addVertex("Miami"); // 4
        theGraph.addVertex("Belize"); // 5
        theGraph.addVertex("Mexico"); // 6
        theGraph.addVertex("Panama"); // 7


        theGraph.addEdge(0, 1, 5);
        theGraph.addEdge(0, 3, 2);
        theGraph.addEdge(1, 2, 4);
        theGraph.addEdge(1, 3, 5);
        theGraph.addEdge(2, 4, 1);
        theGraph.addEdge(3, 2, 2);
        theGraph.addEdge(3, 4, 1);
        theGraph.addEdge(4, 1, 5);
        theGraph.addEdge(4, 5, 3);
        theGraph.addEdge(5, 6, 5);
        theGraph.addEdge(5, 7, 2);
        theGraph.addEdge(7, 6, 1);


        //System.out.print("All the countries available are:"); theGraph.DepthFirstSearch("Suriname"); System.out.println();

       // System.out.println("Your neighbours are:");theGraph.BreadthFirstSearch("Mexico");System.out.println();

        //System.out.println("The safest flightpath from your selected country "); theGraph.SafestPath("Suriname");

       // System.out.println("The most dangerous flightpath from your selected country "); theGraph.MostDangerousPath("Suriname"); System.out.println("But why would you?, Please don't"); System.out.println();

       // theGraph.MinimumSpanningTree("Miami");
    }
}

