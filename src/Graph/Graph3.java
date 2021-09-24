package Graph;

import Distance.DistanceParent;
import Stack.PathStack;
import Vertex.Vertex;

public class Graph3 {

    private final int MAX_VERTS = 20;
    private int INFINITY = 0;
    private Vertex vertexList[];
    private int adjMat[][];
    private int nVerts;
    private int nTree;
    private DistanceParent sPath[];
    private int currentVert;
    private int startToCurrent;

    public Graph3()
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

    public void MostDangerousPath(String Land)
    {
        int gevondenLand = findIndexOf(Land);
        int startTree = gevondenLand;
        vertexList[startTree].isInTree = true;
        nTree = 1;

        for(int j=0; j<nVerts; j++)
        {
            int tempDist = adjMat[startTree][j];
            sPath[j] = new DistanceParent(startTree, tempDist);
        }

        while(nTree < nVerts)

        {
            int indexMax = getMax();
            int maxDist = sPath[indexMax].distance;

            if(maxDist == INFINITY)
            {
                System.out.println("There are unreachable vertices");
                break;
            }
            else
            {
                currentVert = indexMax;
                startToCurrent = sPath[indexMax].distance;
            }

            vertexList[currentVert].isInTree = true;
            nTree++;
            adjust_sPath_for_longestPath();
        }

        displayPaths2();

        nTree = 0;
        for(int j=0; j<nVerts; j++)
            vertexList[j].isInTree = false;
    }


    public int getMax()
    {
        int maxDist = INFINITY;
        int indexMax = 0;
        for(int j=1; j<nVerts; j++)
        {
            if( !vertexList[j].isInTree &&
                    sPath[j].distance > maxDist )
            {
                maxDist = sPath[j].distance;
                indexMax = j;
            }
        }
        return indexMax;
    }

    public void adjust_sPath_for_longestPath()
    {
        int column = 1;
        while(column < nVerts)
        {
            if( vertexList[column].isInTree )
            {
                column++;
                continue;
            }

            int currentToFringe = adjMat[currentVert][column];

            int startToFringe = startToCurrent + currentToFringe;

            int sPathDist = sPath[column].distance;


            if(startToFringe > sPathDist && currentToFringe!=0 )  // hier veranderd!!
            {
                sPath[column].parentVert = currentVert;
                sPath[column].distance = startToFringe;
            }
            column++;
        }
    }

    public void displayPaths2() {
        PathStack MyStack = new PathStack();
        for(int j = 0; j< nVerts; j++) {
            System.out.print(" To travel from current country to ");
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
                System.out.print((String) MyStack.pop() + ", ");
            }
            System.out.println("");
        }
    }
}
