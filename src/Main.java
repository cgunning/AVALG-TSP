public class Main {
	public static void main(String[] args) {

		double deadline = System.currentTimeMillis() + 1300;
		// deadline += 100000;
		TSP tsp = new TSP();
		tsp.readGraph();
		tsp.solve(deadline);
	}
}