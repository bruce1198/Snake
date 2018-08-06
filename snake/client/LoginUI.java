package snake.client;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class LoginUI extends JPanel {
	private static final long serialVersionUID = 1L;
	
    int WIDTH;
    int HEIGHT;
    Image login;
    MyTextField IPField;
    MyTextField portField;
    MyButton go;
    
    LoginUI(){
        setPreferredSize(new Dimension(1000, 600));
    	WIDTH = 1000;
        HEIGHT = 600;
        try {
            login = ImageIO.read(new File(".\\source\\picture\\login.png"));
        } catch (Exception e) {
            System.out.println("Picture Error");
        }
        IPField = new MyTextField("IP");
        portField = new MyTextField("Port");
        go = new MyButton("GO");
        go.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Client.IP = IPField.getText();
					Client.port = Integer.parseInt(portField.getText());
					Client.window = 2;
				} catch(NumberFormatException ex) {
					System.out.println("Input type Error");
				}
			}
        });
        setLayout(null);
        IPField.setBounds(275, 250, 450, 70);
        IPField.setFont(new Font("Consolas", Font.PLAIN, 40));
        portField.setBounds(275, 350, 450, 70);
        portField.setFont(new Font("Consolas", Font.PLAIN, 40));
        go.setBounds(400, 450, 200, 100);
        go.setFont(new Font("Consolas", Font.PLAIN, 60));
        add(IPField);
        add(portField);
        add(go);
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(login, 0, 0, 1000, 600, null);
        repaint();
    }
}
