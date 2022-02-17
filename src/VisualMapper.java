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
		
		rmap.addEdge("A", "B", 8);
		rmap.addEdge("A", "C", 10);
		rmap.addEdge("A", "D", 5);
		rmap.addEdge("B", "C", 1);
		rmap.addEdge("C", "D", 3);
		rmap.addEdge("B", "D", 4);

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
			this.setText("hello");

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
						}finally{

						}
					}
				});
			}
		}
	}

}