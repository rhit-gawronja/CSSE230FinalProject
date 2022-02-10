import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.event.*;
import java.awt.geom.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class VisualMapper extends JFrame {

	private JFrame window;

	public static void main(String[] args) {
		new VisualMapper();

	}

	public VisualMapper() {
		// add things to the window here
		InputField In = new InputField();
		//AddButton add = new AddButton();
		
		window = new JFrame();
		JButton starJButton = new JButton("Start");
		
		//pannel.add(starJButton);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("RoseMapper!!!!");
		JLabel background =new JLabel(new ImageIcon("C:\\Users\\gawronja\\Documents\\HomeWork\\CSSE230\\CSSE230FinalProject\\img\\newCampusMap.jpg"));
		background.setLayout(new FlowLayout());
		window.add(background);
		window.setVisible(true);
		
	
	}
	class InputField extends JTextField{
		public InputField(){
			this.setColumns(3);
			this.selectAll();
		}
	}
	class Console extends JTextArea{

	}

}