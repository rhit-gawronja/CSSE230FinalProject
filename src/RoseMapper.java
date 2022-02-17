import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class RoseMapper {
	private HashMap<String, LocationNode> nodes;
	private boolean directed;
	public String outStr;

	RoseMapper() {
		HashMap<String, LocationNode> nodes = new HashMap<String, LocationNode>();
		this.nodes = nodes;
	}
//	public RoseMapper(FileReader fin){
//		Scanner mapData= new Scanner(fin);
//		String e;
//		while(mapData.hasNextLine()){
//			e=mapData.nextLine();
//			StringTokenizer st = new StringTokenizer(e);
//
//                try
//                {
//                    if( st.countTokens( ) != 3 )
//                    {
//                        System.err.println( "Skipping ill-formatted line " + e );
//                        continue;
//                    }
//                    String source  = st.nextToken( );
//                    String dest    = st.nextToken( );
//                    int    cost    = Integer.parseInt( st.nextToken( ) );
//					System.out.println(source);
//                    this.addEdge( source, dest, cost );
//                }
//				catch(NumberFormatException f){
//					System.err.println( "Skipping ill-formatted line " + e );	
//				}
//		}
//		mapData.close();
//	}

	public void addNode(int type, String name) {
		LocationNode temp = new LocationNode(type, name);
		nodes.put(name, temp);
	}

	public void printNodes() {
		String output = "";
		for (String key : nodes.keySet()) {
			output += key + ", ";
		}
		System.out.println(output);
	}

	public void addEdge(String startname, String destname, double weight) {
		LocationNode source = nodes.get(startname);
		LocationNode destination = nodes.get(destname);
		addEdgeHelper(source, destination, weight);

		if (!directed && source != destination) {
			addEdgeHelper(destination, source, weight);
		}
	}

	private void addEdgeHelper(LocationNode a, LocationNode b, double weight) {
		for (Path edge : a.edges) {
			if (edge.source == a && edge.destination == b) {
				edge.weight = weight;
				return;
			}
		}
		a.edges.add(new Path(a, b, weight));
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

	public void resetNodesVisited() {
		for (String key : nodes.keySet()) {
			nodes.get(key).scratched = false;
		}
	}

	public void DijkstraShortestPath(String startName, String endName,String holder) {
		resetNodesVisited();
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
			shortestPathMap.put(edge.destination, edge.weight);
			changedAt.put(edge.destination, start);
		}
		start.scratched = true;
		while (true) {
			LocationNode currentNode = closestReachableUnvisited(shortestPathMap);
			if (currentNode == null) {
				System.out.println("There isn't a path between " + start.name + " and " + end.name);
				return;
			}
			if (currentNode == end) {
				System.out.println(
						"The path with the smallest weight between " + start.name + " and " + end.name + " is:");

				LocationNode child = end;
				String path = end.name;
				while (true) {
					LocationNode parent = changedAt.get(child);
					if (parent == null) {
						break;
					}
					path = parent.name + " " + path;
					child = parent;
				}
				System.out.println(path);
				setStr("The path costs: " + shortestPathMap.get(end));
				
				return;
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
		outStr=string;
	}

	private LocationNode closestReachableUnvisited(HashMap<LocationNode, Double> shortestPathMap) {

		double shortestDistance = Double.POSITIVE_INFINITY;
		LocationNode closestReachableNode = null;
		for (String key : nodes.keySet()) {
			if (nodes.get(key).scratched)
				continue;

			double currentDistance = shortestPathMap.get(nodes.get(key));
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
		int type;
		String name;
		private boolean scratched;
		LinkedList<Path> edges;

		LocationNode(int type, String name) {
			this.type = type;
			this.name = name;
			scratched = false;
			edges = new LinkedList<>();
		}
	}

}
