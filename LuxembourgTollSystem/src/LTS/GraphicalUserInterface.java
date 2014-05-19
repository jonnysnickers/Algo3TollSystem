package LTS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GraphicalUserInterface extends JFrame implements ActionListener {

	JTextField enterquery;
	JTextArea output;
	static final String helpMessage = "Valid query is in form:\nSELECT(conditions)[GROUP(field)][command]\nwhere:\nfield is one from 'road/plate/gold/enterDate/exitDate'\ncommand is one from 'SUM/COUNT/MAX(field)/MIN(field)'\nconditions is string of restrictions connected by &. Each restriction is in form 'field(<>=)value'. Date format is 'yyyy-mm-ddTHH:mm:ss'\nParameters in [] are optional.\nExample query:\nSELECT()GROUP(plate)SUM\n";
	public GraphicalUserInterface() {
		// Window
		setSize(820, 620);
		setTitle("LTS");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Button
		JButton bProcessQuery = new JButton("Process query!");
		bProcessQuery.setBounds(500, 500, 200, 30);
		bProcessQuery.addActionListener(this);
		add(bProcessQuery);

		// Smal textfield with "Query:"
		JTextField aaa = new JTextField("Query:");
		aaa.setBounds(0, 0, 40, 30);
		aaa.setEditable(false);
		add(aaa);

		// TextField for query
		enterquery = new JTextField("HELP");
		enterquery.setBounds(40, 0, 770, 30);
		add(enterquery);

		// TextArea for resutls of query
		output = new JTextArea("");
		output.setBounds(0, 0, 800, 1200);
		output.setEditable(false);

		// ScrollPane to make TextArea srcollable
		JScrollPane scrollpanel = new JScrollPane(output);
		scrollpanel.setBounds(0, 50, 800, 400);
		// scrollpanel.add(output);
		add(scrollpanel);

		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			String tmp = enterquery.getText();
			if (tmp.equals("HELP")) {
				output.setText(helpMessage);
			} else {
				String res = new OurMagicGenericQuery().doQuery(tmp);
				output.setText(res);
			}
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	}

}
