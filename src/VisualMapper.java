import java.awt.*;


import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.event.*;
import java.io.FileNotFoundException;

public class VisualMapper extends JFrame {
	private RoseMapper rmap;
	private Console cs = new Console();

	public static void main(String[] args) throws FileNotFoundException {
		RoseMapper rmap = new RoseMapper();
		new VisualMapper(rmap);
		rmap.addNode(2, "A");
		rmap.addNode(8, "B");
		rmap.addNode(1, "C");
		rmap.addNode(7, "D");
		rmap.addNode(4, "E");
		rmap.addNode(1, "F");
		rmap.addNode(2, "G");
		rmap.addNode(9, "H");
		rmap.addNode(4, "I");
		rmap.addNode(1, "J");
		rmap.addNode(3, "K");
		rmap.addNode(1, "L");
		rmap.addNode(3, "M");
		rmap.addNode(1, "N");
		rmap.addNode(2, "O");
		rmap.addNode(1, "P");
		rmap.addNode(3, "Q");
		rmap.addNode(2, "R");
		rmap.addNode(4, "S");
		rmap.addNode(2, "T");
		rmap.addNode(1, "U");
		rmap.addNode(4, "V");
		rmap.addNode(3, "W");
		rmap.addNode(1, "X");
		rmap.addNode(6, "Y");
		rmap.addNode(2, "Z");
		rmap.addNode(3, "AA");
		rmap.addNode(1, "BB");
		rmap.addNode(5, "CC");
		rmap.addNode(1, "DD");
		rmap.addNode(2, "EE");
		rmap.addNode(5, "FF");
		rmap.addNode(3, "GG");

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
		rmap.addEdge("N", "L", 18);
		rmap.addEdge("N", "A", 13);
		rmap.addEdge("N", "EE", 17);
		rmap.addEdge("N", "Q", 10);
		rmap.addEdge("N", "G", 20);
		rmap.addEdge("GG", "K", 17);
		rmap.addEdge("GG", "L", 5);
		rmap.addEdge("K", "X", 30);
		rmap.addEdge("X", "D", 60);
		rmap.addEdge("L", "W", 15);
		rmap.addEdge("W", "BB", 12);
		rmap.addEdge("W", "G", 3);
		rmap.addEdge("BB", "O", 2);
		rmap.addEdge("O", "EE", 5);
		rmap.addEdge("EE", "AA", 8);
		rmap.addEdge("Q", "U", 20);
		rmap.addEdge("AA", "U", 15);

	}

	public VisualMapper(RoseMapper rmap) {
		super("RoseMapper!!!!");
		this.rmap = rmap;
		// add things to the window here
		this.setSize(1800, 800);
		Container content = getContentPane();
		content.setLayout(new BorderLayout());
		content.add(new ControlPanel(), BorderLayout.SOUTH);
		content.add(this.cs, BorderLayout.NORTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel background = new JLabel(new ImageIcon( // TODO scale image with frame size?
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
		public Console() {
			this.setEditable(true);
			TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Output");
			border.setTitleJustification(TitledBorder.LEFT);
			this.setBorder(border);
			this.setText("Choose two of the black circle locations on the map to calculate the walking time!");
		}
	}

	class ControlPanel extends JPanel {
		InputField In = new InputField();
		InputField dIn = new InputField();
		InputField Cin = new InputField();
		StartButton start = new StartButton();
		DestinationButton dButton = new DestinationButton();
		TimeButton tButton = new TimeButton();
		DistanceButton disBut = new DistanceButton();

		public ControlPanel() {
			TitledBorder border = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(),
					"Control Panel");
			border.setTitleJustification(TitledBorder.LEFT);
			this.setBorder(border);

			this.setLayout(new FlowLayout());
			this.add(new JLabel("Put in a start location:"));
			this.add(this.In);
			this.add(new JLabel("Put in your final destination:"));
			this.add(this.dIn);
			this.add(this.dButton);

			this.add(this.start);
			this.add(new JLabel("Input cost for trip by cost"));
			this.add(this.Cin);
			this.add(new ResetButton());
			ButtonGroup modes = new ButtonGroup();
			modes.add(tButton);
			modes.add(disBut);
			this.add(tButton);
			this.add(disBut);

		}

		class StartButton extends JButton {
			public StartButton() {
				super("Find Shortest Path");
				this.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						try {
							String a = ControlPanel.this.In.getText();

							String b = ControlPanel.this.dIn.getText();

							rmap.DijkstraShortestPath(a, b);

							VisualMapper.this.cs.setText(rmap.outStr);
						} catch (NullPointerException err) {
							VisualMapper.this.cs.setText("please put in valid locations!");
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
							VisualMapper.this.cs.setText(rmap.outStr);

						} finally {

						}
					}
				});
			}
		}

		class ResetButton extends JButton {
			public ResetButton() {
				super("Trip by cost");
				this.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						try {

							String startNode = ControlPanel.this.In.getText();

							Double cost = Double.parseDouble(ControlPanel.this.Cin.getText());

							rmap.tripByCost(startNode, cost);

							VisualMapper.this.cs.setText(rmap.outStr);
						} catch (NullPointerException err) {
							VisualMapper.this.cs.setText("please put in valid locations and cost!");
						}
					}
				});
			}
		}

		class TimeButton extends JRadioButton {
			public TimeButton() {
				super("Time");
				this.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {

						try {
							rmap.modeSwitch("Time");
						} finally {

						}
					}
				});
			}
		}

		class DistanceButton extends JRadioButton {
			public DistanceButton() {
				super("Distance");
				this.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {

						try {
							rmap.modeSwitch("Distance");
						} finally {

						}
					}
				});
			}
		}
	}

}