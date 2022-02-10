import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import javax.swing.*;
public class RoseMapper{
    public void createMap(){
        JFrame map = new JFrame();
    }
    class Map{
        HashMap<String,LocationNode> nodeMap;
        Map(){
            this.nodeMap=new HashMap();
        }

    }
    class Location{
       public  int vert;
         public int horiztonatl;
    }
    //TODO: Add image to the jframe of Rose-hulman map
    //TODO: Determine if we are using A* of Djikstras algo
    //TODO: Create buttons:
    //locationNode== vertex in djikstras algorithm
    class Path{
        public LocationNode destination;
        public double cost;
        public Path(LocationNode destination,double cost){
            this.destination=destination;
            this.cost=cost;
        }
    }
    private LocationNode getLocationNode(String name){

    }
    public void dijkstra(String start){
       PriorityQueue<Path> pq=new PriorityQueue<>();
       LocationNode start= new LocationNode();
    }
    class LocationNode{
        public String name;

        ArrayList<LocationNode> connections;
        public double cost;
        public LocationNode prev;
        public int temp;
        public LocationNode(String name){
            this.name=name;
        }
        
    }
}
// This is a test comment.