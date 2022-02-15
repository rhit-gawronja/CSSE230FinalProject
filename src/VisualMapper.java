import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.w3c.dom.events.MouseEvent;

import java.awt.event.*;
import java.awt.geom.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class VisualMapper extends JFrame {
	static RoseMapper rmap;
	private JFrame window;

	public static void main(String[] args) {
		new VisualMapper();

	}

	public VisualMapper() {
		super("RoseMapper!!!!");
		// add things to the window here
		this.setSize(800,500);
		Container content=getContentPane();
		content.setLayout(new BorderLayout());
		content.add(new ControlPanel(), BorderLayout.SOUTH);
		// pannel.add(starJButton);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel background = new JLabel(new ImageIcon(
				"C:\\Users\\gawronja\\Documents\\HomeWork\\CSSE230\\CSSE230FinalProject\\img\\newCampusMap.jpg"));
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

	}

	class ControlPanel extends JPanel {
		InputField In = new InputField();
		InputField dIn= new InputField();
		StartButton start = new StartButton();
		DestinationButton dButton=new DestinationButton();
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

		}

		class StartButton extends JButton {
			public StartButton() {
				super("Start destination");
				this.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						try {
							String a = ControlPanel.this.In.getText();
							rmap.dijkstra(a);

						} finally {

						}
					}
				});
			}

		}

		class DestinationButton extends JButton {
			public DestinationButton() {
				super("Calculate Jurny destination");
				this.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						try {
							String a = ControlPanel.this.In.getText();
							rmap.dijkstra(a);

						} finally {

						}
					}
				});
			}
		}
	}

	

	

}