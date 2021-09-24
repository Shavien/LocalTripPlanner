package Vertex;

public class Vertex {

        public String label; // label (e.g. ‘A’)
        public boolean isInTree;
        public boolean wasVisited;
        // -------------------------------------------------------------
        public Vertex(String lab) // constructor
        {
            label = lab;
            wasVisited = false;
            isInTree = false;
        }
// -------------------------------------------------------------
    } // end class Vertex


