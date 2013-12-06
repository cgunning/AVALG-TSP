public class Graph {
	Node[] nodes;
	double[][] distance;
	int size;

	public Graph(Node[] nodes) {
		size = nodes.length;
		distance = new double[size][size];
		this.nodes = nodes;

		for (int i = 1; i < size; i++) {
			for (int j = 0; j < i; j++) {
				double d = calculateDistance(i, j);
				distance[i][j] = d;
				distance[j][i] = d;
			}
		}
	}

	@Override
	public String toString() {
		String returnString = nodes.length + "\n";

		for (Node node : nodes) {
			returnString += node.toString() + "\n";
		}

		return returnString;
	}

	public double getDistance(int i, int j) {
		return distance[i][j];
	}

	public Node[] getNodes() {
		return nodes;
	}

	private double calculateDistance(int i, int j) {
		return Math.pow(nodes[i].getX() - nodes[j].getX(), 2)
				+ Math.pow(nodes[i].getY() - nodes[j].getY(), 2);
	}
}
