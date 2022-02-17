import static org.junit.Assert.*;
import org.junit.Test;

public class Testing {

	@Test
	public void testAlgo() {
		RoseMapper graphWeighted = new RoseMapper();
		// Our addEdge method automatically adds Nodes as well.
		// The addNode method is only there for unconnected Nodes,
		// if we wish to add any
		graphWeighted.addNode(0, "zero");
		graphWeighted.addNode(1, "one");
		graphWeighted.addNode(2, "two");
		graphWeighted.addNode(3, "three");
		graphWeighted.addNode(4, "four");
		graphWeighted.addNode(5, "five");
		graphWeighted.addNode(6, "six");
		
		graphWeighted.addEdge("zero", "one", 8);
		graphWeighted.addEdge("zero", "two", 11);
		graphWeighted.addEdge("one", "three", 3);
		graphWeighted.addEdge("one", "four", 8);
		graphWeighted.addEdge("one", "two", 7);
		graphWeighted.addEdge("two", "four", 9);
		graphWeighted.addEdge("three", "four", 5);
		graphWeighted.addEdge("three", "five", 2);
		graphWeighted.addEdge("four", "six", 6);
		graphWeighted.addEdge("five", "four", 1);
		graphWeighted.addEdge("five", "six", 8);

		graphWeighted.DijkstraShortestPath("zero", "six",null);
	}
	
}