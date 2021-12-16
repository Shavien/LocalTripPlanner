import Graph.Graph; 

public class Application {
    public static void main(String[] args) {
        Graph theGraph = new Graph();
        theGraph.addVertex("Paramaribo"); // 0 (start)
        theGraph.addVertex("Albina"); // 1
        theGraph.addVertex("Djumu"); // 2
        theGraph.addVertex("Langatabbetje"); // 3
        theGraph.addVertex("Kwamalasamutu"); // 4
        //theGraph.addVertex("Pokigrong"); // 5

        theGraph.addEdge(0, 1, 50); // AB 50
        theGraph.addEdge(1, 0, 50); // AB 50
        theGraph.addEdge(0, 3, 80); // AD 80
        theGraph.addEdge(1, 2, 60); // BC 60
        theGraph.addEdge(1, 3, 90); // BD 90
        theGraph.addEdge(2, 4, 40); // CE 40
        theGraph.addEdge(3, 2, 20); // DC 20
        theGraph.addEdge(3, 4, 70); // DE 70
        theGraph.addEdge(4, 1, 50); // EB 50
        //theGraph.addEdge(4, 5, 20); // EB 50


      //  theGraph.DepthFirstSearch("Kwamalasamutu"); System.out.println();

        //theGraph.BreadthFirstSearch("Coronie");System.out.println();



        theGraph.addTourDestination("Pokigrong");
        theGraph.addTourDestination("Bigi Pan");
        theGraph.addTourDestination("Apoera");
        theGraph.addEdgeByLabel("Kwamalasamutu","Pokigrong",20);
        theGraph.addEdgeByLabel("Paramaribo","Bigi Pan",35);
        theGraph.addEdgeByLabel("Bigi Pan","Apoera",30);

        //theGraph.ShortestPath("Albina");
        theGraph.LongestPath("Paramaribo"); System.out.println();
    }
}

