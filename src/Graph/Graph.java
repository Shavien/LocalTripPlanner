package Graph;

import Queue.Queuex;
import Stack.Stackx;
import Vertex.Vertex;

public class Graph {
    private final int MAX_VERTS = 20;
    private Vertex vertexList[];
    private int adjMat[][];
    private int nVerts;
    private Stackx theStack;
    private Queuex theQueue;


    // -------------------------------------------------------------
    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int j = 0; j < MAX_VERTS; j++) {
            for (int k = 0; k < MAX_VERTS; k++) {

                adjMat[j][k] = 0;       // for dfs and bsf
                theStack = new Stackx();
                theQueue = new Queuex();

            }
        }
    }

    public int findIndexOf(String Land) {

        for (int IndexLand = 0; IndexLand < nVerts; IndexLand++) {
            if (vertexList[IndexLand].label.equalsIgnoreCase(Land))
                return IndexLand;
        }
        return -1;
    }


    public void addVertex(String lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    // -------------------------------------------------------------
    public void addEdge(int start, int end,int weight) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].label + " ");
    }

    public int getAdjUnvisitedVertex(int v) {

        for (int landIndex = 0; landIndex < nVerts; landIndex++)
            if (adjMat[v][landIndex] == 1 && vertexList[landIndex].wasVisited == false)
                return landIndex;
        return -1;
    }

    // ------------------------------------------------------------------------------------------

    public void DepthFirstSearch(String Land) {

        int gevondenLand = findIndexOf(Land);
        if (gevondenLand == -1) {
            System.out.println("This country is not included in the network");

        } else {
            vertexList[gevondenLand].wasVisited = true;

            displayVertex(gevondenLand);
            theStack.push(gevondenLand);
            while (!theStack.isEmpty()) {

                int vertex = getAdjUnvisitedVertex(theStack.peek());
                if (vertex == -1)
                    theStack.pop();
                else {
                    vertexList[vertex].wasVisited = true;
                    displayVertex(vertex);
                    theStack.push(vertex);
                }
            }

            for (int landIndex = 0; landIndex < nVerts; landIndex++)
                vertexList[landIndex].wasVisited = false;
        }
    }
    //---------BREADTH FIRST SEARCH--------------------------------------------------------------------

    public void BreadthFirstSearch(String Land) {
        int gevondenLand = findIndexOf(Land);
        if (gevondenLand == -1) {
            System.out.println("not visible because this country is not yet included in the network");

        } else {
            vertexList[gevondenLand].wasVisited = true;
            theQueue.insert(gevondenLand);
            int vertex2;
            while (!theQueue.isEmpty()) {
                while ((vertex2 = getAdjUnvisitedVertex(gevondenLand)) != -1) { //Hier veranderd v1 in gevondenLand
                    vertexList[vertex2].wasVisited = true;
                    displayVertex(vertex2);
                    theQueue.insert(vertex2);
                }
            }

            for (int landIndex = 0; landIndex < nVerts; landIndex++)
                vertexList[landIndex].wasVisited = false;
        }
    }

    public void MinimumSpanningTree(String Land)
    {
        int gevondenLand = findIndexOf(Land);
        if (gevondenLand == -1) {
            System.out.println("No edges can be displayed, because the given country is not in the network yet");
        }
        vertexList[gevondenLand].wasVisited = true;
        theStack.push(0);
        while( !theStack.isEmpty() )
        {
            int currentVertex = theStack.peek();

                int v = getAdjUnvisitedVertexForMST(currentVertex);
                if (v == -1)
                    theStack.pop();
                else
                {
                    vertexList[v].wasVisited = true;
                    theStack.push(v);

                    displayVertex(currentVertex);
                    displayVertex(v);
                    System.out.print(" ");
                }
            }
        for (int j = 0; j < nVerts; j++)
                vertexList[j].wasVisited = false;
        }

        public int getAdjUnvisitedVertexForMST ( int v)
        {
            for (int j = 0; j < nVerts; j++)
                if (adjMat[v][j] == 1 && vertexList[j].wasVisited == false)
                    return j;
            return -1;
        }
}




