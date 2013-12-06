import java.io.DataInputStream;
import java.io.InputStream;

import javax.naming.TimeLimitExceededException;

public class TSP {

	Graph graph;
	GUI gui;
	boolean DRAW = false;

	public TSP(GUI gui) {
		this.gui = gui;
	}

	public void readGraph() {
		InputStream is = new DataInputStream(System.in);
		Kattio kattio = new Kattio(is);
		int n = kattio.getInt();
		Node[] nodes = new Node[n];
		for (int i = 0; i < n; i++) {
			nodes[i] = new Node(i, kattio.getDouble(), kattio.getDouble());
		}
		this.graph = new Graph(nodes);

	}

	public Node[] solve(double deadline) {
		// System.out.println(calculateTotalDistance(graph.getNodes()));
		Node[] tour = graph.getNodes();
		if (tour.length == 1) {
			System.out.println(tour[0].getIndex());
			return null;
		}

		Node[] greedyTour = null;
		try {
			greedyTour = greedyTour(deadline);
			tour = greedyTour;
		} catch (TimeLimitExceededException e) {
			tour = graph.getNodes();
		}

		while (System.currentTimeMillis() < deadline)
			tour = twoOpt(tour, deadline);

		for (int i = 0; i < tour.length; i++) {
			System.out.println(tour[i].getIndex());
		}
		return tour;
	}

	public Node[] greedyTour(double deadline) throws TimeLimitExceededException {
		Node[] nodes = graph.getNodes();

		Node[] tour = new Node[nodes.length];
		boolean[] used = new boolean[nodes.length];

		tour[0] = nodes[0];
		used[0] = true;

		for (int i = 1; i < nodes.length; i++) {
			Node bestNode = null;

			for (int j = 0; j < nodes.length; j++) {
				if (System.currentTimeMillis() >= deadline)
					throw new TimeLimitExceededException();
				if (!used[j]
						&& (bestNode == null || graph.getDistance(
								tour[i - 1].getIndex(), nodes[j].getIndex()) < graph
								.getDistance(tour[i - 1].getIndex(),
										bestNode.getIndex())))
					bestNode = nodes[j];
			}
			tour[i] = bestNode;
			used[bestNode.getIndex()] = true;
			if (DRAW)
				gui.drawTour(tour);
		}
		return tour;
	}

	private Node[] twoOptSwap(Node[] route, int i, int k, double deadline)
			throws TimeLimitExceededException {
		Node[] newRoute = route.clone();
		// 1. take route[0] to route[i-1] and add them in order to new_route

		// 2. take route[i] to route[k] and add them in reverse order to
		// new_route

		for (int j = i; j <= k; j++) {
			newRoute[j] = route[k - (j - i)];

			if (System.currentTimeMillis() >= deadline)
				throw new TimeLimitExceededException();
		}

		// 3. take route[k+1] to end and add them in order to new_route
		return newRoute;
	}

	private Node[] twoOpt(Node[] route, double deadline) {
		Node[] newRoute = new Node[route.length];
		boolean done = false;
		double lastDistance = 0;
		double bestDistance = 1;

		while (lastDistance != bestDistance) {

			bestDistance = calculateTotalDistance(route);
			for (int i = 1; i < route.length - 1; i++) {
				for (int k = i + 1; k < route.length; k++) {
					newRoute = route;
					try {
						newRoute = twoOptSwap(route, i, k, deadline);
					} catch (TimeLimitExceededException e) {
						return route;
					}
					double newDistance = calculateTotalDistance(newRoute);
					if (newDistance < bestDistance) {
						route = newRoute;
						if (DRAW)
							gui.drawTour(route);
						done = true;
						break;
					}
				}
				if (done) {
					done = false;
					break;
				}
			}
			lastDistance = bestDistance;
		}
		return newRoute;
	}

	private double calculateTotalDistance(Node[] route) {
		double distance = 0;

		for (int i = 1; i < route.length; i++) {
			distance += graph.getDistance(route[i - 1].getIndex(),
					route[i].getIndex());
		}
		distance += graph.getDistance(route[route.length - 1].getIndex(),
				route[0].getIndex());
		return distance;
	}
}
