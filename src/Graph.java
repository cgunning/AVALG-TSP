public class Graph {
	Node[] nodes;
	double[][] distance;
	int size;

	public Graph(Node[] nodes) {
		size = nodes.length;
		distance = new double[size][size];
		this.nodes = nodes;

		// for (int i = 0; i < size; i++) {
		// for (int j = 0; j < size; j++) {
		// distance[i][j] = calculateEuclideanDistance(nodes[i], nodes[j]);
		// System.out.println("Distance " + i + ", " + j + ": "
		// + distance[i][j]);
		// }
		// }
	}

	@Override
	public String toString() {
		String returnString = nodes.length + "\n";

		for (Node node : nodes) {
			returnString += node.toString() + "\n";
		}

		return returnString;
	}

	public double getEuclideanDistance(int i, int j) {
		return Math.sqrt((nodes[i].getX() - nodes[j].getX())
				* (nodes[i].getX() - nodes[j].getX())
				+ (nodes[i].getY() - nodes[j].getY())
				* (nodes[i].getY() - nodes[j].getY()));
		// return distance[i][j];
	}

	public Node[] getNodes() {
		return nodes;
	}

	private double calculateEuclideanDistance(Node n1, Node n2) {
		return Math.sqrt((n1.getX() - n2.getX()) * (n1.getX() - n2.getX())
				+ (n1.getY() - n2.getY()) * (n1.getY() - n2.getY()));
	}
}
