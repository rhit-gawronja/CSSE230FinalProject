import javax.swing.*;
public class RoseMapper{
    public void createMap(){
        Jframe map = new JFrame();
    }
    class Map{
        HashMap<String,T> nodeMap;
        Map(){
            this.nodeMap=new HashMap();
        }
    }
    //TODO: Add image to the jframe of Rose-hulman map
    //TODO: Determine if we are using A* of Djikstras algo
    //TODO: Create buttons:
    class LocationNode{
        ArrayList<LocationNode> connections;
        T location;
        LocationNode(T element){
            this.connections=new ArrayList<LocationNode>();
            this.location=element
        }
    }
}
// This is a test comment.