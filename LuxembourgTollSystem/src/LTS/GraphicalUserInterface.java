package LTS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GraphicalUserInterface extends JFrame implements ActionListener {

	JTextField enterquery;
	JTextArea output;

	public GraphicalUserInterface() {
		setSize(850, 650);
		setTitle("LTS");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton bProcessQuery = new JButton("Process query!");
		bProcessQuery.setBounds(500, 500, 200, 30);
		add(bProcessQuery);
		
		JTextField aaa = new JTextField("Query:");
		aaa.setBounds(0, 0, 40, 30);
		aaa.setEditable(false);
		add(aaa);
		
		enterquery = new JTextField("SELECT()");
		enterquery.setBounds(40, 0, 770, 30);
		add(enterquery);


		
		output = new JTextArea("");
		output.setBounds(0, 0, 800, 1200);
		output.setEditable(false);
		//add(output);
		
		JScrollPane scrollpanel = new JScrollPane(output);
		scrollpanel.setBounds(0, 50, 800, 400);
		//scrollpanel.add(output);
		add(scrollpanel);

		bProcessQuery.addActionListener(this);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String res = new OurMagicGenericQuery().doQuery(enterquery.getText());
			output.setText(res);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}

}
