public class Main {
	public static void main(String[] args) {
		//
		// GUI ex = new GUI();
		// ex.setVisible(true);
		// while (true) {
		double deadline = System.currentTimeMillis() + 1000;
		// deadline += 20000;
		TSP tsp = new TSP(null);
		tsp.readGraph();
		Node[] tour = tsp.solve(deadline);
		// }
	}
}