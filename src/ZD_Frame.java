import java.awt.event.*;

import javax.swing.*;
public class ZD_Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public ZD_Frame()
	
	{
		super("Zahlen Darstellungen");
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		});
		ZD_Model model = new ZD_Model();
		ZD_View view = new ZD_View(model);
		getContentPane().add(view);
		setSize(500,200);
		pack();

	}
	public static void main(String[] args)  {
		ZD_Frame ef = new ZD_Frame();
		ef.setVisible(true);
		
	}
}
