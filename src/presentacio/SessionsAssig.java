package presentacio;

import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javafx.util.Pair;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class SessionsAssig extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2676129754368992206L;
	private static SessionsAssig sa;
	private ControladorPresentacio CtrlP = new ControladorPresentacio();
	private JPanel contentPane;
	private String disparador;
	private String assignatura = null;
	
	public static SessionsAssig getInstance() {
		if (sa == null) sa = new SessionsAssig();
		return sa;
	}
	/**
	 * Launch the application.
	 */
	public static void newSessionsAssig() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SessionsAssig frame = SessionsAssig.getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SessionsAssig() {
		inicialitzaPanel();
	}
	
	void inicialitzaPanel() {
		setBounds(100, 100, 670, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CheckBoxList list = new CheckBoxList();
		
		Vector<String> sessions = CtrlP.getAllTipusSessions();
		Vector<String> sessionsAssig = null;
		if (assignatura != null) {
			sessionsAssig = CtrlP.getAllSessions(assignatura);
		}
		
		JCheckBox[] checksessions = {};
		for (int i=0; i<sessions.size();i++)
		{
			JCheckBox temp = new JCheckBox();
			temp.setText(sessions.get(i));
			if (sessionsAssig != null) {
				if (sessionsAssig.contains(sessions.get(i))) temp.setSelected(true);
				else temp.setSelected(false);
			}
			checksessions = addElement(checksessions, temp);
		}
		list.setListData(checksessions);
		
			
			//((DefaultListModel)list.getModel()).addElement(grups.get(i));
		    //model.add(i, grups.get(i));
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setLocation(124, 68);
		scrollPane.setSize(242, 214);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      

        //JOptionPane.showMessageDialog(null, scrollPane);
		contentPane.add(scrollPane);
		
		JLabel lblGestiAssignatures = new JLabel("Sessions");
		lblGestiAssignatures.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGestiAssignatures.setBounds(10, 11, 289, 40);
		contentPane.add(lblGestiAssignatures);
		
		JButton button = new JButton("Nou Tipus de Sessió");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				TipusSessio ts = TipusSessio.getInstance();
				ts.botodisparador("SessioAssig");
				ts.setVisible(true);
			}
		});
		button.setBounds(10, 302, 152, 33);
		contentPane.add(button);
		
		JButton btnCrea = new JButton("Accepta");
		btnCrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Assignatura a = Assignatura.getInstance();
				Boolean t;
				Vector<String> sessions = new Vector<String>();
				for (int k = 0; k < list.getModel().getSize(); k++) {
					JCheckBox jc = (JCheckBox) list.getModel().getElementAt(k);
					if (jc.isSelected()) {
						sessions.add(jc.getText());
					}
				}
				for (int k = 0; k < list.getModel().getSize(); k++) {
					if (disparador == "modif") {
						JCheckBox jc = (JCheckBox) list.getModel().getElementAt(k);
						if (jc.isSelected()) {
							a.setTSessions(sessions);
							a.setVisible(true);						
						}		
				} 
					if (disparador == "crear") {
						JCheckBox jc = (JCheckBox) list.getModel().getElementAt(k);
						if (jc.isSelected()) {
							a.setTSessions(sessions);
							a.setVisible(true);						
						}					
					}
				else if (disparador == "cons") {					
					a.setVisible(true);
				}
			}
				}
				
		});
		btnCrea.setBounds(492, 302, 152, 33);
		contentPane.add(btnCrea);
	}
	public void setVisible(Boolean visible) {
		contentPane.setVisible(visible);
	}
	
	public void botodisparador(String disp) {
		disparador = disp;
		
	}
	static JCheckBox[] addElement(JCheckBox[] array, JCheckBox nou) {
	    array  = Arrays.copyOf(array, array.length + 1);
	    array[array.length - 1] = nou;
	    return array;
	}
	
	public void refreshPanel() {
		contentPane.removeAll();
		inicialitzaPanel();
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	public void setAssig(String assig) {
		this.assignatura = assig;		
	}
	
}