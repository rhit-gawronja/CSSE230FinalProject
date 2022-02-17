import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import java.awt.event.*;
import java.awt.geom.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class VisualMapper extends JFrame {
	private RoseMapper rmap;
	private JFrame window;
	private Console cs=new Console();
	private String out;

	public static void main(String[] args) throws FileNotFoundException {
		RoseMapper rmap = new RoseMapper();
		new VisualMapper(rmap);
//		String fileName="maps/rose.txt";
//		FileReader in=new FileReader(fileName);
//		rmap=new RoseMapper(in);
		rmap.addNode(1, "A");
		rmap.addNode(1, "B");
		rmap.addNode(1, "C");
		rmap.addNode(1, "D");
		rmap.addNode(1, "E");
		rmap.addNode(1, "F");
		rmap.addNode(1, "CC");
		rmap.addNode(1, "N");
		rmap.addNode(1, "EE");
		rmap.addNode(1, "W");
		rmap.addNode(1, "GG");
		rmap.addNode(1, "BB");
		rmap.addNode(1, "AA");
		rmap.addNode(1, "Q");
		rmap.addNode(1, "L");
		rmap.addNode(1, "K");
		rmap.addNode(1, "X");
		rmap.addNode(1, "V");
		rmap.addNode(1, "O");
		rmap.addNode(1, "S");
		rmap.addNode(1, "P");
		rmap.addNode(1, "I");
		rmap.addNode(1, "J");
		rmap.addNode(1, "F");
		rmap.addNode(1, "FF");
		rmap.addNode(1, "R");
		rmap.addNode(1, "M");
		
		rmap.addEdge("D", "B", 2);
		rmap.addEdge("D", "FF", 10);
		rmap.addEdge("B", "FF", 8);
		rmap.addEdge("C", "Z", 5);
		rmap.addEdge("C", "F", 7);
		rmap.addEdge("F", "Z", 2);
		rmap.addEdge("Z", "R", 2);
		rmap.addEdge("F", "R", 2);
		rmap.addEdge("M", "J", 10);
		rmap.addEdge("R", "M", 5);
		rmap.addEdge("FF", "T", 8);
		rmap.addEdge("T", "CC", 7);
		rmap.addEdge("CC", "E", 5);
		rmap.addEdge("E", "J", 8);
		rmap.addEdge("E", "V", 9);
		rmap.addEdge("J", "V", 7);
		rmap.addEdge("J", "P", 5);
		rmap.addEdge("V", "P", 2);
		rmap.addEdge("V", "I", 3);
		rmap.addEdge("P", "I", 2);
		rmap.addEdge("I", "N", 7);
		rmap.addEdge("I", "S", 4);
		rmap.addEdge("S", "Y", 2);
		rmap.addEdge("S", "K", 3);
		rmap.addEdge("V", "K", 4);
		
rmap.addEdge("N", "L" ,18);
rmap.addEdge("N", "A" ,13);
rmap.addEdge("N", "EE" ,17);
rmap.addEdge("N", "Q" ,10);
rmap.addEdge("N", "G" ,20);
rmap.addEdge("GG", "K" ,17);
rmap.addEdge("GG", "L", 5);
rmap.addEdge("K", "X" ,30);
rmap.addEdge("X", "D" ,60);
rmap.addEdge("L", "W" ,15);
rmap.addEdge("W", "BB" ,12);
rmap.addEdge("W", "G", 3);
rmap.addEdge("BB", "O", 2);
rmap.addEdge("O", "EE", 5);
rmap.addEdge("EE", "AA", 8);
rmap.addEdge("Q", "U" ,20);
rmap.addEdge("AA", "U" ,15);

		

		

	}

	public VisualMapper(RoseMapper rmap) {
		super("RoseMapper!!!!");
		this.rmap = rmap;
		// add things to the window here
		this.setSize(1800, 800);
		Container content = getContentPane();
		content.setLayout(new BorderLayout());
		content.add(new ControlPanel(), BorderLayout.SOUTH);
		content.add(this.cs,BorderLayout.NORTH);
		// pannel.add(starJButton);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel background = new JLabel(new ImageIcon( //TODO scale image with frame size?
				"img/newCampusMap.jpg"));
		background.setLayout(new FlowLayout());
		add(background);
		setVisible(true);

	}

	class InputField extends JTextField {
		public InputField() {
			this.setColumns(3);
			this.selectAll();
		}
	}

	class Console extends JTextArea {
		public Console(){
			
			this.setEditable(true);
			TitledBorder border = BorderFactory.createTitledBorder(
					BorderFactory.createLoweredBevelBorder(), "Output");
			border.setTitleJustification(TitledBorder.LEFT);
			this.setBorder(border);
			this.setText("Choose two of the black circle locations on the map to calculate the walking time!");

		}
	}

	class ControlPanel extends JPanel {
		InputField In = new InputField();
		InputField dIn = new InputField();
		StartButton start = new StartButton();
		DestinationButton dButton = new DestinationButton();

		public ControlPanel() {
			TitledBorder border = BorderFactory.createTitledBorder(
					BorderFactory.createLoweredBevelBorder(), "Control Panel");
			border.setTitleJustification(TitledBorder.LEFT);
			this.setBorder(border);
			this.setLayout(new FlowLayout());
			this.add(new JLabel("Put in a start location:"));
			this.add(this.In);
			this.add(this.start);
			this.add(new JLabel("Put in your final destination:"));
			this.add(this.dIn);
			this.add(this.dButton);
			this.add(new ResetButton());

		}

		class StartButton extends JButton {
			public StartButton() {
				super("Find Shortest Path");
				this.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						try {
							String a = ControlPanel.this.In.getText();
							String b = ControlPanel.this.dIn.getText();
							String holder=null;
							rmap.DijkstraShortestPath(a, b,holder);
							out="start test";
							System.out.println(holder);
							VisualMapper.this.cs.setText(rmap.outStr);
						} finally {

						}
					}
				});
			}

		}

		class DestinationButton extends JButton {
			public DestinationButton() {
				super("List Avialable Nodes");
				this.addMouseListener(new MouseAdapter() {
					
					public void mousePressed(MouseEvent e) {
						try {
							rmap.printNodes();

						} finally {

						}
					}
				});
			}
		}
		
		class ResetButton extends JButton{
			public ResetButton(){
				super("Reset the measurments");
				this.addMouseListener(new MouseAdapter(){
					public void mousePressed(MouseEvent e){
						try{
							//rmap.resetAll();
							VisualMapper.this.cs.setText("Choose two of the black circle locations on the map to calculate the walking time!");
						}finally{

						}
					}
				});
			}
		}
	}

}