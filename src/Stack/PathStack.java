package Stack;

public class PathStack {
    private final int SIZE = 100;
    private String[] stack;
    private int top;

    public PathStack(){
        stack = new String[SIZE];
        top =-1;
    }
    public void push( String item){
        stack[++top]=item;
    }
    public String pop(){
        return stack[top--];
    }
    public String peek(){
        return stack[top];
    }
    public boolean isEmpty(){
        return ( top==-1);
    }
}
