import java.util.HashMap;
import java.util.LinkedList;

//Project by Derek Inskeep and Joey Gawron
public class RoseMapper {
	private HashMap<String, LocationNode> nodes;
	public String outStr;

	enum Mode {
		TIME, DISTANCE
	}

	Mode m;

	RoseMapper() {
		HashMap<String, LocationNode> nodes = new HashMap<String, LocationNode>();
		this.nodes = nodes;
		this.m = Mode.TIME;
	}

	public void modeSwitch(String mString) {
		if (mString == "Time") {
			this.m = Mode.TIME;
		} else if (mString == "Distance") {
			this.m = Mode.DISTANCE;
		}
	}

	public void addNode(int type, String name) {
		LocationNode temp = new LocationNode(type, name);
		nodes.put(name, temp);
	}

	public void printNodes() {
		String output = "";
		for (String key : nodes.keySet()) {
			output += key + ", ";
		}
		
		setStr(output);
	}

	public void addEdge(String startname, String destname, double weight) {
		LocationNode source = nodes.get(startname);
		LocationNode destination = nodes.get(destname);
		source.edges.add(new Path(source, destination, weight));
		destination.edges.add(new Path(destination, source, weight));
	}

	public void printEdges() {
		for (String key : nodes.keySet()) {
			LinkedList<Path> edges = nodes.get(key).edges;
			if (edges.isEmpty()) {
				System.out.println("Node " + nodes.get(key).name + " has no edges.");
				continue;
			}
			System.out.print("Node " + nodes.get(key).name + " has edges to: ");

			for (Path edge : edges) {
				System.out.print(edge.destination.name + "(" + edge.weight + ") ");
			}
			System.out.println();
		}
	}

	public boolean hasEdge(LocationNode source, LocationNode destination) {
		LinkedList<Path> edges = source.edges;
		for (Path edge : edges) {
			if (edge.destination == destination) {
				return true;
			}
		}
		return false;
	}

	public void resetNodes() {
		for (String key : nodes.keySet()) {
			nodes.get(key).scratched = false;
		}
	}

	public double DijkstraShortestPath(String startName, String endName) {
		if ((!nodes.containsKey(startName)) || (!nodes.containsKey(endName))) {
			throw new NullPointerException("please put in valid location nodes");

		}
		resetNodes();
		LocationNode start = nodes.get(startName);
		LocationNode end = nodes.get(endName);
		HashMap<LocationNode, LocationNode> changedAt = new HashMap<>();
		changedAt.put(start, null);
		HashMap<LocationNode, Double> shortestPathMap = new HashMap<>();
		for (String key : nodes.keySet()) {
			if (nodes.get(key) == start)
				shortestPathMap.put(start, 0.0);
			else
				shortestPathMap.put(nodes.get(key), Double.POSITIVE_INFINITY);
		}
		for (Path edge : start.edges) {
			if (this.m == Mode.DISTANCE) {
				shortestPathMap.put(edge.destination, edge.weight);
			} else if (this.m == Mode.TIME) {
				shortestPathMap.put(edge.destination, edge.weight * edge.source.timeMult);
			}
			changedAt.put(edge.destination, start);
		}
		start.scratched = true;
		while (true) {
			LocationNode currentNode = nextNode(shortestPathMap);
			if (currentNode == null) {

				return 0.0;
			}
			if (currentNode == end) {

				LocationNode child = end;
				String path = end.name;
				while (true) {
					LocationNode parent = changedAt.get(child);
					if (parent == null) {
						break;
					}
					path = parent.name + "->" + path;
					child = parent;
				}

				if (this.m == Mode.TIME)
					setStr("The path is: " + path + " time in minutes: " + shortestPathMap.get(end));
				else
					setStr("The path is: " + path + " distance in elephant strides: " + shortestPathMap.get(end));
				return shortestPathMap.get(end);
			}
			currentNode.scratched = true;
			for (Path edge : currentNode.edges) {
				if (edge.destination.scratched)
					continue;

				if (shortestPathMap.get(currentNode) + edge.weight < shortestPathMap.get(edge.destination)) {
					shortestPathMap.put(edge.destination, shortestPathMap.get(currentNode) + edge.weight);
					changedAt.put(edge.destination, currentNode);
				}
			}
		}
	}

	private void setStr(String string) {
		outStr = string;
	}

	private LocationNode nextNode(HashMap<LocationNode, Double> shortestPathMap) {

		double shortestDistance = Double.POSITIVE_INFINITY;
		LocationNode closestReachableNode = null;
		for (String key : nodes.keySet()) {
			if (nodes.get(key).scratched)
				continue;
			double currentDistance = 0.0;
			if (this.m == Mode.DISTANCE) {
				currentDistance = shortestPathMap.get(nodes.get(key));
			} else if (this.m == Mode.TIME) {
				currentDistance = shortestPathMap.get(nodes.get(key)) * nodes.get(key).timeMult;
			}
			if (currentDistance == Double.POSITIVE_INFINITY)
				continue;

			if (currentDistance < shortestDistance) {
				shortestDistance = currentDistance;
				closestReachableNode = nodes.get(key);
			}
		}
		return closestReachableNode;
	}

	public class Path implements Comparable<Path> {

		LocationNode source;
		LocationNode destination;
		double weight;

		Path(LocationNode s, LocationNode d, double w) {
			source = s;
			destination = d;
			weight = w;
		}

		public String toString() {
			return String.format("(%s -> %s, %f)", source.name, destination.name, weight);
		}

		public int compareTo(Path otherEdge) {
			if (this.weight > otherEdge.weight) {
				return 1;
			} else
				return -1;
		}
	}

	class LocationNode {
		int timeMult;
		String name;
		private boolean scratched;
		LinkedList<Path> edges;

		LocationNode(int timeMult, String name) {
			this.timeMult = timeMult;
			this.name = name;
			scratched = false;
			edges = new LinkedList<>();
		}
	}

	public void tripByCost(String startNode, Double cost) {
		double shortestCurrentPath = Double.POSITIVE_INFINITY;
		String tempStr = "";
		String tempStrb = "";
		double shortShort = 0.0;
		for (String key : nodes.keySet()) {
			if (key == startNode)
				continue;

			double temp = DijkstraShortestPath(startNode, key);
			if (!(temp < cost)) {
				if (temp < shortestCurrentPath) {
					shortestCurrentPath = temp;
					tempStr = outStr;
				}
			} else {
				if (temp > shortShort) {
					shortShort = temp;
					tempStrb = outStr;
				}
			}

		}
		if ((cost - shortShort) < (shortestCurrentPath - cost)) {
			outStr = tempStrb;
		} else {
			outStr = tempStr;
		}
	}

}
