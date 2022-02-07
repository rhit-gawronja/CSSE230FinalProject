import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
public class RoseMapper{
    public void createMap(){
        JFrame map = new JFrame();
    }
    class Map{
        HashMap<String,T> nodeMap;
        Map(){
            this.nodeMap=new HashMap();
        }

    }
    Class Location{
       public  int vert;
         public int horiztonatl;
    }
    //TODO: Add image to the jframe of Rose-hulman map
    //TODO: Determine if we are using A* of Djikstras algo
    //TODO: Create buttons:
    class LocationNode{
        ArrayList<LocationNode> connections;
        Location location;
        LocationNode(T element){
            this.connections=new ArrayList<LocationNode>();
            this.location=element;
        }
        int findDistance(LocationNode target){
            int y =math.pow(this.location.vert,2);
            int x=MATH.pow(this.location.horizontal,2);
        }
    }
}
// This is a test comment.