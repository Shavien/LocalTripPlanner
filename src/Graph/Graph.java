package Graph;

import Distance.DistanceParent;
import Queue.Queuex;
import Stack.PathStack;
import Stack.Stackx;
import Vertex.Vertex;

public class Graph {
    private final int MAX_VERTS = 20;
    private int INFINITY = 1000000;
    private int INFINITY2 = 0;
    private Vertex vertexList[];
    private int adjMat[][];
    private int adjMatx[][];
    private int adjMatrix[][];
    private int nVerts;
    private int nTree;
    private DistanceParent sPath[];
    private int currentVert;
    private int startToCurrent;
    private Stackx theStack;
    private Queuex theQueue;



    // -------------------------------------------------------------
    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        adjMatx = new int[MAX_VERTS][MAX_VERTS];
        adjMatrix = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int j = 0; j < MAX_VERTS; j++) {
            for (int k = 0; k < MAX_VERTS; k++) {

                adjMat[j][k] = INFINITY2;// for dfs and bsf
                adjMatrix[j][k]= INFINITY2;// for longest
                adjMatx[j][k] = INFINITY;// for shortestPath
                sPath = new DistanceParent[MAX_VERTS];

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
    public void addEdge(int start, int end, int weight) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
        adjMatx[start][end] = weight; // shortest
        adjMatrix[start][end] = weight; // longest
    }



    public void displayVertex(int v) {
        System.out.print(vertexList[v].label + " ");
    }


    // ------------------------------------------------------------------------------------------
    public void DepthFirstSearch(String Land) {

        int gevondenLand = findIndexOf(Land);
        if (gevondenLand == -1) {
            System.out.println("This country is not included in the network!");
            System.out.println();
            DepthFirstSearch("Paramaribo");

        } else {
            System.out.println("All the tour-destinations available are:");
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
            System.out.println("No neighbours available because this tour-destination is not yet included in the network!");
            System.out.println();

        } else {
            System.out.println("The tour-destinations nearby are:");
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

        DepthFirstSearch("Paramaribo");
    }

    public int getAdjUnvisitedVertex(int v) {

        for (int landIndex = 0; landIndex < nVerts; landIndex++)
            if (adjMat[v][landIndex] == 1 && vertexList[landIndex].wasVisited == false)
                return landIndex;
        return -1;
    }

    //------------------------------------------SHORTEST PATH-----------------------------------------------------------

    public void ShortestPath(String Land) {
        int gevondenLand = findIndexOf(Land);
        int startTree = gevondenLand;
        vertexList[startTree].isInTree = true;
        nTree = 1;

        for (int j = 0; j < nVerts; j++) {
            int tempDist = adjMatx[startTree][j];
            sPath[j] = new DistanceParent(startTree, tempDist);
        }

        // until all vertices are in the tree
        while (nTree < nVerts) {
            int indexMin = getMin();
            int minDist = sPath[indexMin].distance;

            if (minDist == INFINITY) {
                System.out.println();
                System.out.println("There are unreachable vertices");
                break;
            } else {
                currentVert = indexMin;
                startToCurrent = sPath[indexMin].distance;

            }

            vertexList[currentVert].isInTree = true;
            nTree++;
            adjust_sPath();
        }

        displayPaths();
        nTree = 0;
        for (int j = 0; j < nVerts; j++)
            vertexList[j].isInTree = false;
    }

    // -------------------------------------------------------------
    public int getMin() {
        int minDist = INFINITY;
        int indexMin = 0;
        for (int j = 1; j < nVerts; j++) {
            if (!vertexList[j].isInTree &&
                    sPath[j].distance < minDist) {
                minDist = sPath[j].distance;
                indexMin = j;
            }
        }
        return indexMin;
    }

    public void adjust_sPath() {

        int column = 1;
        while (column < nVerts) {

            if (vertexList[column].isInTree) {
                column++;
                continue;
            }
            //calculating happens here
            int currentToFringe = adjMatx[currentVert][column];

            int startToFringe = startToCurrent + currentToFringe;

            int sPathDist = sPath[column].distance;

            if (startToFringe < sPathDist) {
                sPath[column].parentVert = currentVert;
                sPath[column].distance = startToFringe;
            }
            column++;
        }
    }

    public void displayPaths() {

        Stack.PathStack MyStack = new Stack.PathStack();

        System.out.println("The cheapest flightpath to every other tour-destinations is:");
        System.out.println();
        for (int j = 0; j < nVerts; j++) {
            System.out.print(" to ");
            MyStack.push(vertexList[j].label);
            System.out.print(vertexList[j].label + " --> ");
            System.out.print(" with a cost of $ ");
            if (sPath[j].distance == INFINITY) {
                System.out.print(" 0 ");
            } else {
                System.out.print(sPath[j].distance);
            }
            System.out.print(" travel via ");
            int parentVert = sPath[j].parentVert;
            String parent = vertexList[parentVert].label;
            MyStack.push(parent);
            System.out.print(parent);
            int parentOf = parentVert;
            boolean notA = true;
            while (notA) {
                System.out.print(" after traveling via ");
                int temp = sPath[parentOf].parentVert;
                String parentOfParent = vertexList[sPath[parentOf].parentVert].label;
                MyStack.push(parentOfParent);
                System.out.print(parentOfParent + " ");
                parentOf = temp;
                if (parentOfParent.equals(parentOfParent))
                    notA = false;
            }
            System.out.print("| ");
            while (!MyStack.isEmpty()) {
                System.out.print((String) MyStack.pop() + ", ");
            }
            System.out.println();

        }
    }

    //---------------------------------LongestPath--------------------

    public void LongestPath(String Land) {
        int gevondenLand = findIndexOf(Land);
        int startTree = gevondenLand;
        vertexList[startTree].isInTree = true;
        nTree = 1;

        for (int j = 0; j < nVerts; j++) {
            int tempDist = adjMatrix[startTree][j];
            sPath[j] = new DistanceParent(startTree, tempDist);
        }

        while (nTree < nVerts) {
            int indexMax = getMax();
            int maxDist = sPath[indexMax].distance;

            if (maxDist == INFINITY2) {
                System.out.println("There are unreachable vertices");
                break;
            } else {
                currentVert = indexMax;
                startToCurrent = sPath[indexMax].distance;
            }

            vertexList[currentVert].isInTree = true;
            nTree++;
            adjust_sPath_for_longestPath();
        }

        displayPaths2();

        nTree = 0;
        for (int j = 0; j < nVerts; j++)
            vertexList[j].isInTree = false;
    }


    public int getMax() {
        int maxDist = INFINITY2;
        int indexMax = 0;
        for (int j = 1; j < nVerts; j++) {
            if (!vertexList[j].isInTree &&
                    sPath[j].distance > maxDist) {
                maxDist = sPath[j].distance;
                indexMax = j;
            }
        }
        return indexMax;
    }

    public void adjust_sPath_for_longestPath() {
        int column = 1;
        while (column < nVerts) {
            if (vertexList[column].isInTree) {
                column++;
                continue;
            }

            int currentToFringe = adjMatrix[currentVert][column];

            int startToFringe = startToCurrent + currentToFringe;

            int sPathDist = sPath[column].distance;


            if (startToFringe > sPathDist && currentToFringe != 0)  // hier veranderd!!
            {
                sPath[column].parentVert = currentVert;
                sPath[column].distance = startToFringe;
            }
            column++;
        }
    }

    public void displayPaths2() {
        PathStack MyStack = new PathStack();
        System.out.println();
        System.out.println(" The most expensive flightpath to every other Tour-destination: ");
        System.out.println();
        for (int j = 0; j < nVerts; j++) {
            System.out.print(" to ");
            MyStack.push(vertexList[j].label);
            System.out.print(vertexList[j].label + " --> ");
            System.out.print(" with a cost of $ ");
            if (sPath[j].distance == INFINITY2) {
                System.out.print(" 0 ");
            } else {
                System.out.print(sPath[j].distance);
            }
            System.out.print(" travel via ");
            int parentVert = sPath[j].parentVert;
            String parent = vertexList[parentVert].label;
            MyStack.push(parent);
            System.out.print(parent);
            int parentOf = parentVert;
            boolean notA = true;
            while (notA) {
                System.out.print(" after traveling via ");
                int temp = sPath[parentOf].parentVert;
                String parentOfParent = vertexList[sPath[parentOf].parentVert].label;
                MyStack.push(parentOfParent);
                System.out.print(parentOfParent + " ");
                parentOf = temp;
                if (parentOfParent.equals(parentOfParent))
                    notA = false;
            }
            System.out.print("| ");
            while (!MyStack.isEmpty()) {
                System.out.print((String) MyStack.pop() + ", ");
            }
            System.out.println();
        }
    }

    public void addTourDestination(String label){
        this.vertexList[this.nVerts++] = new Vertex(label);
    }


    public void addEdgeByLabel(String startLabel, String destinationLabel, int weight) {
        int start = this.findIndexOf(startLabel);
        int end = this.findIndexOf(destinationLabel);
        this.adjMatx[start][end] = weight;
        this.adjMatrix[start][end] = weight;
    }

}



