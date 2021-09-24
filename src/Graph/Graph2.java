package Graph;

import Distance.DistanceParent;
import Vertex.Vertex;

public class Graph2 {
    private final int MAX_VERTS = 20;
    private int INFINITY = 1000000;
    private Vertex vertexList[];
    private int adjMat[][];
    private int nVerts;
    private int nTree;
    private DistanceParent sPath[];
    private int currentVert;
    private int startToCurrent;

    public Graph2()
    {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        nTree = 0;
        for (int j = 0; j < MAX_VERTS; j++) {
            for (int k = 0; k < MAX_VERTS; k++) {

                adjMat[j][k] = INFINITY;
                sPath = new DistanceParent[MAX_VERTS];

            }
        }
    }

    public int findIndexOf(String Land){

        for(int item = 0; item<nVerts; item++){
            if( vertexList[item].label.equalsIgnoreCase(Land))
                return item;
        }
        return -1;
    }

    public void addVertex(String lab) {
        vertexList[nVerts++] = new Vertex(lab);
    }

    // -------------------------------------------------------------
    public void addEdge(int start, int end, int weight) {

        adjMat[start][end] = weight; // (directed), used for safest Path
    }

    public void displayVertex(int v) {
        System.out.print(vertexList[v].label + " ");
    }

    //------------------------------------------SAFEST PATH-----------------------------------------------------------

    public void SafestPath(String Land)
    {
        int gevondenLand = findIndexOf(Land);
        int startTree = gevondenLand;
        vertexList[startTree].isInTree = true;
        nTree = 1;

        for (int j = 0; j < nVerts; j++) {
            int tempDist = adjMat[startTree][j];
            sPath[j] = new DistanceParent(startTree, tempDist);
        }

        // until all vertices are in the tree
        while (nTree < nVerts) {
            int indexMin = getMin();
            int minDist = sPath[indexMin].distance;

            if (minDist == INFINITY)
            {
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
    public int getMin()
    {
        int minDist = INFINITY;
        int indexMin = 0;
        for (int j = 1; j < nVerts; j++)
        {
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
        while (column < nVerts)
        {

            if (vertexList[column].isInTree) {
                column++;
                continue;
            }
            //calculating happens here
            int currentToFringe = adjMat[currentVert][column];

            int startToFringe = startToCurrent + currentToFringe;

            int sPathDist = sPath[column].distance;

            if (startToFringe < sPathDist)
            {
                sPath[column].parentVert = currentVert;
                sPath[column].distance = startToFringe;
            }
            column++;
        }
    }

    public void displayPaths() {
        Stack.PathStack MyStack = new Stack.PathStack();
        for(int j = 0; j< nVerts; j++) {
            System.out.print(" to " );
            MyStack.push(vertexList[j].label);
            System.out.print(vertexList[j].label);
            System.out.print(" with a cost of ");
            if(sPath[j].distance == INFINITY) {
                System.out.print("inf");
            }
            else {
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
                System.out.print((String)MyStack.pop() + ", ");
            }
            System.out.println("");
        }
    }
}
