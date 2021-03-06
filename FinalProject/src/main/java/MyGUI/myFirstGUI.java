package MyGUI;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.mortennobel.imagescaling.ResampleOp;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;
import javax.swing.DropMode;
import javax.swing.JTextArea;
import MyProject.*;

public class myFirstGUI {

	private JFrame frame;
	private static final String inputImage = "D:\\Download\\eclipse-java-2020-12-R-win32-x86_64\\CK\\MyMap.jpg"; // give image path here
    private JLabel label = null; 
    private double zoom = 1.0;  // zoom factor
    private int status;
    private BufferedImage image = null;
    private JLabel lblNewLabel;
    private String username, password, path;
    private int[][] adjacencyMatrix;
    private int startVertex = 0, endVertex = 0;
    private static String xemlichsu = "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myFirstGUI window = new myFirstGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void setXemLichSu(String ls) {
		xemlichsu += ls + "\n";
	}
	
	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public myFirstGUI() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(56, 41, 1134, 311);
		frame.getContentPane().add(scrollPane);
		
		image = ImageIO.read(new File(inputImage));
		
		JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
     // display image as icon 
        Icon imageIcon = new ImageIcon(inputImage);
        label = new JLabel( imageIcon );
        panel.add(label, BorderLayout.WEST);
        
        panel.addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();
                double temp = zoom - (notches * 0.2);
                // minimum zoom factor is 1.0
                temp = Math.max(temp, 1.0);
                if (temp != zoom) {
                    zoom = temp;
                    resizeImage();
                }
            }
        });
        scrollPane.setViewportView(panel);
        
        JLabel LabelDiemdi = new JLabel("Nh\u1EADp \u0111i\u1EC3m \u0111i");
        LabelDiemdi.setBounds(10, 382, 77, 53);
        frame.getContentPane().add(LabelDiemdi);
        
        final JTextPane textPaneStartPoint = new JTextPane();
        textPaneStartPoint.setBounds(97, 399, 142, 20);
        frame.getContentPane().add(textPaneStartPoint);
        
        JLabel LabelDiemden = new JLabel("Nh\u1EADp \u0111i\u1EC3m \u0111\u1EBFn");
        LabelDiemden.setBounds(10, 429, 93, 53);
        frame.getContentPane().add(LabelDiemden);
        
        final JTextPane textPaneEndPoint = new JTextPane();
        textPaneEndPoint.setBounds(113, 445, 142, 20);
        frame.getContentPane().add(textPaneEndPoint);
        
        final JRadioButton xeMay = new JRadioButton("B\u1EA3n \u0111\u1ED3 cho xe m\u00E1y");
        xeMay.setBounds(340, 397, 157, 23);
        frame.getContentPane().add(xeMay);
        
        final JRadioButton oTo = new JRadioButton("B\u1EA3n \u0111\u1ED3 cho xe \u00F4 t\u00F4");
        oTo.setBounds(340, 444, 157, 23);
        frame.getContentPane().add(oTo);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(xeMay);
        bg.add(oTo);
        
        xeMay.setSelected(true);
        
        JLabel showpath = new JLabel("\u0110\u01B0\u1EDDng \u0111i d\u00E0nh cho b\u1EA1n");
        showpath.setBounds(570, 399, 177, 53);
        frame.getContentPane().add(showpath);
        
        final JButton btnNewButton_1 = new JButton("\u0110\u0103ng nh\u1EADp");
        btnNewButton_1.setBounds(480, 580, 112, 23);
        frame.getContentPane().add(btnNewButton_1);
        /*
        JButton btnNewButton_2 = new JButton("\u0110\u0103ng xu\u1EA5t");
        btnNewButton_2.setBounds(480, 647, 112, 23);
        frame.getContentPane().add(btnNewButton_2);
        */
        /*JLabel*/ lblNewLabel = new JLabel("idle");
        lblNewLabel.setBounds(600, 601, 69, 14);
        frame.getContentPane().add(lblNewLabel);
        

        final JTextPane uPane = new JTextPane();
        uPane.setBounds(340, 522, 56, 20);
        frame.getContentPane().add(uPane);
        
        final JTextPane pPane = new JTextPane();
        pPane.setBounds(406, 522, 56, 20);
        frame.getContentPane().add(pPane);
        
        JButton xoaLichSuButton = new JButton("Xo\u00E1 l\u1ECBch s\u1EED");
        xoaLichSuButton.setBounds(472, 522, 175, 23);
        frame.getContentPane().add(xoaLichSuButton);
        xoaLichSuButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (status == 0) {
        			JOptionPane.showMessageDialog(frame, "Vui long dang nhap");
        		}
        		else {
	        		String type = "";
	        		if (xeMay.isSelected()) {
	        			type = "Xe may";
	        		}
	        		else if(oTo.isSelected()) {
	        			type = "O to";
	        		}
	        		int startVertex_ = 0, endVertex_ = 0;
	        		startVertex_ = Integer.parseInt(uPane.getText());
	        		endVertex_ = Integer.parseInt(pPane.getText());
	        		if (startVertex_ == 0 || endVertex_ == 0) {
	        			JOptionPane.showMessageDialog(frame, "Vui long nhap diem di va den");
	        		}
	        		modifyHistory mod = new modifyHistory();
	        		History his = mod.getType("xoa lich su");
	        		his.execute(username, startVertex_, endVertex_, type);
        		}
        	}
        });
        
        JLabel lblNhpThngTin = new JLabel("Nh\u1EADp th\u00F4ng tin c\u1EA7n xo\u00E1 theo th\u1EE9 t\u1EF1 \u0111i\u1EC3m \u0111\u1EBFn, \u0111i\u1EC3m \u0111i");
        lblNhpThngTin.setBounds(10, 506, 334, 53);
        frame.getContentPane().add(lblNhpThngTin);
                
        final JTextArea lichsu = new JTextArea();
        lichsu.setBounds(20, 548, 252, 122);
        frame.getContentPane().add(lichsu);
        
        JButton xemlichsuButton = new JButton("Xem l\u1ECBch s\u1EED c\u1EE7a b\u1EA1n");
        xemlichsuButton.setBounds(10, 486, 177, 23);
        frame.getContentPane().add(xemlichsuButton);
        xemlichsuButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (status == 0) {
        			JOptionPane.showMessageDialog(frame, "Vui long dang nhap");
        		}
        		else {
	        		modifyHistory mod = new modifyHistory();
	        		History his = mod.getType("xem lich su");
	        		his.execute(username, 0, 0, "");
	        		lichsu.setText(xemlichsu);
	        		xemlichsu = "";
        		}
        	}
        });
        
        final JTextArea duongdi = new JTextArea();
        duongdi.setBounds(726, 413, 492, 257);
        frame.getContentPane().add(duongdi);
        
        final JTextPane textPane_username = new JTextPane();
        textPane_username.setBounds(305, 595, 142, 20);
        frame.getContentPane().add(textPane_username);
        
        final JTextPane textPane_password = new JTextPane();
        textPane_password.setBounds(305, 650, 142, 20);
        frame.getContentPane().add(textPane_password);
        
        JLabel lblNewLabel_1 = new JLabel("Username");
        lblNewLabel_1.setBounds(305, 570, 77, 14);
        frame.getContentPane().add(lblNewLabel_1);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(305, 625, 77, 14);
        frame.getContentPane().add(lblPassword);
        
        JButton btnNewButton_3 = new JButton("T\u00ECm \u0111\u01B0\u1EDDng");
        btnNewButton_3.setBounds(570, 382, 112, 23);
        frame.getContentPane().add(btnNewButton_3);
        
        JButton themLichSuButton = new JButton("Th\u00EAm l\u1ECBch s\u1EED");      
        /*
        themLichSuButton.setBounds(472, 486, 175, 23);
        frame.getContentPane().add(themLichSuButton);
        themLichSuButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (status == 0) {
        			JOptionPane.showMessageDialog(frame, "Vui long dang nhap");
        		}
        		else {
	        		String type = "";
	        		if (xeMay.isSelected()) {
	        			type = "Xe may";
	        		}
	        		else if(oTo.isSelected()) {
	        			type = "O to";
	        		}
	        		if (startVertex == 0 || endVertex == 0) {
	        			JOptionPane.showMessageDialog(frame, "Vui long nhap diem di va den");
	        		}
	        		modifyHistory mod = new modifyHistory();
	        		History his = mod.getType("them lich su");
	        		his.execute(username, startVertex, endVertex, type);
        		}
        	}
        });
        */
        btnNewButton_3.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e) {
        		if (xeMay.isSelected()) {
        			ChooseMapType map = new xeMay();
        			adjacencyMatrix = map.read();
        		}
        		else if(oTo.isSelected()) {
        			ChooseMapType map = new oTo();
        			adjacencyMatrix = map.read();
        			
        		}
        		Dijkstras dis =  Dijkstras.getInstance();
        		
        		try {
        			startVertex = Integer.parseInt(textPaneStartPoint.getText());
        		}
        		catch(NumberFormatException ex) {
        			
        		}
        		
        		try {
            		endVertex = Integer.parseInt(textPaneEndPoint.getText());
        		}
        		catch(NumberFormatException ex) {
        			
        		}
        		dis.dijkstra(adjacencyMatrix, startVertex, endVertex);
        		dis.printPathStep();
        		path = dis.getPaths();
        		
        		
        		duongdi.setText(
                		"Diem di cua ban:" + startVertex + "\n" +
                        "Diem den cua ban: " + endVertex + "\n" +
                        "Duong di: " + dis.getPaths() +  "\n" +
                        "Khoang cach: " + dis.getDistance() + " meters" + "\n"
                        );
        		path = "";
        		dis.resetPaths();
        		duongdi.setLineWrap(true);
        		
        		if (status == 1) {
        			String type = "";
	        		if (xeMay.isSelected()) {
	        			type = "Xe may";
	        		}
	        		else if(oTo.isSelected()) {
	        			type = "O to";
	        		}
        			modifyHistory mod = new modifyHistory();
	        		History his = mod.getType("them lich su");
	        		his.execute(username, startVertex, endVertex, type);
        		}
        	}
        }
        );
        /*
        btnNewButton_1.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e) {
        		setUsername(textPane_username.getText());
        		setPassword(textPane_password.getText());
        		Account acc = new Account(getUsername(), getPassword());
        		LogIn in = new LogIn(acc);
        		Invoker ivk = new Invoker();
        		boolean Bool = ivk.active(in);
        		if (Bool) {
        			status = 1;
                	lblNewLabel.setText("logged in");
        		}
        		else {
        			JOptionPane.showMessageDialog(frame, "Sai tai khoan hoac mat khau");
        		}
        	}
        }
        );
        */
        btnNewButton_1.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e) {
        		doValidate val;
//        		Account acc = new Account(getUsername(), getPassword());
        		Invoker ivk = new Invoker();
        		if (status == 0) {
        			
	        		setUsername(textPane_username.getText());
	        		val = new doValidate(new UsernameValidation());
	        		if (!val.execute(getUsername())) {
	        			JOptionPane.showMessageDialog(frame, "Username khong hop le");
	        			return;
	        		}
	        		setPassword(textPane_password.getText());
	        		val = new doValidate(new PasswordValidation());
	        		if (!val.execute(getPassword())) {
	        			JOptionPane.showMessageDialog(frame, "Password khong hop le");
	        			return;
	        		}
	        		
	        		Account acc = new Account(getUsername(), getPassword());
	        		LogInOut in = new LogIn(acc);
//	        		Invoker ivk = new Invoker();
	        		boolean Bool = ivk.active(in);
	        		if (Bool) {
	        			status = 1;
	                	lblNewLabel.setText("logged in");
	                	btnNewButton_1.setText("Dang xuat");
	        		}
	        		else {
	        			JOptionPane.showMessageDialog(frame, "Sai tai khoan hoac mat khau");
	        		}
        		}
        		else {
        			Account acc = new Account(getUsername(), getPassword());
        			LogInOut out = new LogOut(acc);
//            		Invoker ivk = new Invoker();
            		boolean Bool = ivk.active(out);
            		
                	if (Bool) {
                		JOptionPane.showMessageDialog(frame, "Dang xuat thanh cong");
                		status = 0;
                    	lblNewLabel.setText("idle");
	                	btnNewButton_1.setText("Dang nhap");
            		}
            		else {
            			JOptionPane.showMessageDialog(frame, "Error");
            		}
        		}
        	}
        }
        );
        /*
        btnNewButton_2.addActionListener(new ActionListener()
        {
        	public void actionPerformed(ActionEvent e) {
        		Account acc = new Account(getUsername(), getPassword());
        		LogOut out = new LogOut(acc);
        		Invoker ivk = new Invoker();
        		boolean Bool = ivk.active(out);
        		
            	if (Bool) {
            		JOptionPane.showMessageDialog(frame, "Dang xuat thanh cong");
            		status = 0;
                	lblNewLabel.setText("idle");
        		}
        		else {
        			JOptionPane.showMessageDialog(frame, "Error");
        		}
        	}
        }		
        );
		*/
        
    }
    
    public void resizeImage() {
//           System.out.println(zoom);
           ResampleOp  resampleOp = new ResampleOp((int)(image.getWidth()*zoom), (int)(image.getHeight()*zoom));
               BufferedImage resizedIcon = resampleOp.filter(image, null);
           Icon imageIcon = new ImageIcon(resizedIcon);
           label.setIcon(imageIcon);
        }
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
