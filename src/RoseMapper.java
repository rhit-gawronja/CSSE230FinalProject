import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.*;

public class RoseMapper {

	// TODO Write good test code so that we actually know if this works.
	private HashMap<String, Node> rmap = new HashMap<String, Node>();
	PriorityQueue<Path> path=new PriorityQueue<>();
	public RoseMapper(FileReader fin){
		Scanner mapData= new Scanner(fin);
		String e;
		while(mapData.hasNextLine()){
			e=mapData.nextLine();
			//test
			StringTokenizer st = new StringTokenizer(e);

                try
                {
                    if( st.countTokens( ) != 3 )
                    {
                        System.err.println( "Skipping ill-formatted line " + e );
                        continue;
                    }
                    String source  = st.nextToken( );
                    String dest    = st.nextToken( );
                    int    cost    = Integer.parseInt( st.nextToken( ) );
					System.out.println(source);
                    this.addEdge( source, dest, cost );
                }
				catch(NumberFormatException f){
					System.err.println( "Skipping ill-formatted line " + e );	
				}
		}
	}
	public void addNode(String name) {
		Node node = new Node(name);
		rmap.put(name, node);
	}

	public void addEdge(String start, String dest, double cost) {
		Node startNode = getNode(start);
		Node destNode = getNode(dest);
		startNode.adj.add(new Edge(destNode, cost));
	}

	private Node getNode(String name) {
		Node ans = rmap.get(name);
		if (ans == null) {
			this.addNode(name);
		} else {
			return ans;
		}
		ans = rmap.get(name);
		return ans;
	}
	public StringBuilder printPath(String destNode){
		StringBuilder sb=new StringBuilder();
		Node w=rmap.get(destNode);
		if(w==null)throw new NoSuchElementException();
		else if(w.dist==Double.MAX_VALUE)System.out.println("can't reach");
		else{
			printNodePath(sb,w);
		}
		printNodePath(sb,w);
		return sb;
	}
	public void printNodePath(StringBuilder sb,Node dest){
		if(dest.prev!=null){
			printNodePath(sb, dest.prev);
			sb.append(" to ");
		}
		sb.append(dest.name);
	}
	public void dijkstra(String start) {
		PriorityQueue<Path> pq = new PriorityQueue<>();
		Node startNode = rmap.get(start);
		if (startNode == null)
			throw new NoSuchElementException();
		int nodeSeen = 0;
		pq.add(new Path(startNode, 0));
		startNode.dist = 0;
		while (!pq.isEmpty() && nodeSeen < rmap.size()) {
			Path vrec = pq.remove();
			Node v = vrec.dest;
			if (v.scratch != 0) {
				continue;
			}
			v.scratch = 1;
			nodeSeen++;
			for (Edge e : v.adj) {
				Node w=e.dest;
				double cvw=e.cost;
				if(cvw<0);
				if(w.dist>v.dist+cvw){
					w.dist=v.dist+cvw;
					w.prev=v;
					System.out.println("im here");
					pq.add(new Path(w,w.dist));
				}
			}
		
		}
		System.out.println(pq.toString());
	}

	class Edge {
		public Node dest;
		public double cost;

		public Edge(Node d, double cost) {
			this.dest = d;
			this.cost = cost;
		}
	}

	class Path implements Comparable<Path> {
		public Node dest;
		public double cost;

		Path(Node d, double cost) {
			this.dest = d;
			this.cost = cost;
		}

		@Override
		public int compareTo(RoseMapper.Path o) {
			// TODO Auto-generated method stub
			return cost < o.cost ? -1 : cost > o.cost ? 1 : 0;
		}

	}

	class Node {
		public String name;
		public ArrayList<Edge> adj;
		public double dist;
		public Node prev;
		public int scratch;

		public Node(String n) {
			this.name = n;

			this.adj = new ArrayList<>();

		}

		public void reset() {
			this.dist = Double.MAX_VALUE;
			this.prev = null;
			scratch = 0;
		}
	}
}
