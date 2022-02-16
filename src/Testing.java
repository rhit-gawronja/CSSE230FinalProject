import static org.junit.Assert.*;
import org.junit.Test;

public class Testing {
	
	@Test
	public void testAlgo() {	
		RoseMapper rmap = new RoseMapper();
		rmap.addNode("a");
		rmap.addNode("b");
		rmap.addNode("c");
		rmap.addEdge("a", "b", 1);
		rmap.addEdge("b", "c", 1);
		rmap.addEdge("a", "c", 4);
		
	}
}