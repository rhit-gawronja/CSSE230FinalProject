import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

import javax.swing.*;

public class RoseMapper { // TODO Write good test code so that we actually know if this works.
	public void createMap() {
	}

	class Map {
		HashMap<String, LocationNode> nodeMap;

		Map() {
			this.nodeMap = new HashMap<String, LocationNode>();
		}

		private LocationNode getLocationNode(String name) {
			LocationNode x = nodeMap.get(name);
			return x;
		}

		public void addPath(String startName, String endName, double cost) {
			LocationNode x = getLocationNode(startName);
			LocationNode y = getLocationNode(endName);

			x.connections.add(new Path(y, cost));
			// TODO Should y also add? so that the Path is 2-way?
			// y.connections.dd(new Path(x, cost));

		}

		public void clearAll() {
			for (LocationNode x : nodeMap.values())
				x.reset();
		}

		public void printPath(LocationNode dest) { // for testing, will probably output to graphics instead of print
													// later
			if (dest.prev != null) {
				printPath(dest.prev);
				System.out.print(" to ");
			}
			System.out.print(dest.name);
		}

		public void dijkstra(String startName) {
			PriorityQueue<Path> pq = new PriorityQueue<Path>();

			LocationNode start = nodeMap.get(startName);
			if (start == null) // TODO Try to handle this better?
				throw new NoSuchElementException("Not Found");

			clearAll();
			pq.add(new Path(start, 0));
			start.cost = 0;
			int nodesSeen = 0;

			while (!pq.isEmpty() && nodesSeen < nodeMap.size()) {
				Path rec = pq.remove();
				LocationNode x = rec.destination;
				if (x.checked)
					continue;

				x.checked = true;
				nodesSeen++;

				for (Path path : x.connections) {
					LocationNode y = path.destination;
					double cvw = path.cost;

					if (cvw < 0) { // TODO: this could be big problem, need better solution than this?
						System.out.println("Graph has negative edges");
						break;
					}
					if (y.cost > x.cost + cvw) {
						y.cost = x.cost + cvw;
						y.prev = x;
						pq.add(new Path(y, y.cost));
					}
				}
			}
		}
	}

	class Location { // TODO What is this class? can we get rid of this? -Derek
		public int vert;
		public int horiztonatl;
	}

	class Path implements Comparable<Path> { //TODO edge of graph, book has separate edge and path classes, but they look near identical.
		public LocationNode destination;
		public double cost;

		public Path(LocationNode destination, double cost) {
			this.destination = destination;
			this.cost = cost;
		}
		public int compareTo(Path rhs) {
			double otherCost = rhs.cost;
			return cost < otherCost ? -1 : cost > otherCost ? 1: 0;
		}
	}

	class LocationNode { // vertex of graph
		public String name;

		ArrayList<Path> connections;
		public double cost;
		public LocationNode prev;
		public boolean checked;

		public LocationNode(String name) {
			this.name = name;
			this.connections = new ArrayList<Path>();
			reset();
		}

		public void reset() { // TODO not finished
			prev = null;
			checked = false;
			//cost = 0.0;
		}
	}
}