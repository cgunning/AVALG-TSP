
public class Node implements Comparable {
	private int index;
	private double x, y;
	
	Node(int index, double x, double y) {
		this.index = index;
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getIndex() {
		return index;
	}
	
	@Override
	public String toString() {
		return x + " " + y;
	}

	@Override
	public int compareTo(Object arg0) {
		Node node = (Node) arg0;
		return (int)(x - node.getX() + y - node.getY());
	} 
}
