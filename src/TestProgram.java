

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TestProgram implements ActionListener{
JFrame J = new JFrame();
JPanel P = new JPanel();
JLabel JL = new JLabel();
JLabel JL2 = new JLabel();
JButton b1 = new JButton();
JButton b2 = new JButton();
JTextField T = new JTextField(30);
public static void main(String[] args) {
	TestProgram TP = new TestProgram();
	TP.showButton();
}
public void showButton() {
	J.add(P);
	J.setVisible(true);
	P.add(JL);
	P.add(JL2);
	P.add(T);
	P.add(b1);
	P.add(b2); P.add(JL2);
	b1.setText("Check Answer");
	b2.setText("Hint");
	b1.addActionListener(this);
	b2.addActionListener(this);
	JL.setText("What do tigers have that no other animal can have?");
	J.pack();
	
	
}
@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource().equals(b1)) {
		if(T.getText().equalsIgnoreCase("baby tigers")) {
			JL2.setText("Correct!!");
			J.pack();
		}
		else {
			JL2.setText("Wrong");
			J.pack();
			}
	}
	if(e.getSource().equals(b2)) {
		JL2.setText("B__y t_____");
		System.out.println("Works");
		J.pack();
	}
	
}

}
