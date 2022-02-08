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
	public static void main(String[] args){
			new VisualMapper();	
			
	}		

			
	
	public VisualMapper(){
		//add things to the window here
		window=new JFrame();
		JButton starJButton=new JButton("Start");
		JPanel pannel= new JPanel();
		pannel.setBorder(BorderFactory.createEmptyBorder());
		pannel.setLayout(new GridLayout(2,2));
		pannel.add(starJButton);
		window.add(pannel,BorderLayout.CENTER);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setTitle("RoseMapper!!!!");
		window.pack();
		window.setVisible(true);
		}
	
}