package Distance;

public class DistanceParent {
    // distance and parent
    // items stored in sPath array
    public int distance; // distance from start to this vertex
    public int parentVert; // current parent of this vertex
    // -------------------------------------------------------------
    public DistanceParent(int pv, int d) // constructor
    {
        distance = d;
        parentVert = pv;
    }
} // end class DistPar


