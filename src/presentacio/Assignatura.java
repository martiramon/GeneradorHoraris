package presentacio;

import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javafx.util.Pair;
public class Assignatura extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Assignatura a;
	private ControladorPresentacio CtrlP = new ControladorPresentacio();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private String disparador;
	private Vector<String> coreqs;
	private Vector<Pair<String,String>> grups = new Vector<Pair<String,String>>(); 
	private Vector<String> tsessions;
	
	public static Assignatura getInstance() {
		if (a == null) a = new Assignatura();
		return a;
	}
	/**
	 * Launch the application.
	 */
	public static void newAssignatura() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Assignatura frame = Assignatura.getInstance();
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
	public Assignatura() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(exitListener);
		setBounds(100, 100, 670, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		JLabel lblGestiAssignatures = new JLabel("Assignatura");
		lblGestiAssignatures.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGestiAssignatures.setBounds(10, 11, 289, 40);
		contentPane.add(lblGestiAssignatures);
		
		JButton button = new JButton("Endarrere");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GestioAssigs ga = GestioAssigs.getInstance();
				ga.setVisible(true);
				textField.setText(null);
				textField_1.setText(null);
				textField_2.setText(null);
			}
		});
		button.setBounds(10, 302, 152, 33);
		contentPane.add(button);
		
		JLabel lblAcrnim = new JLabel("Acr\u00F2nim");
		lblAcrnim.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcrnim.setBounds(142, 96, 152, 33);
		contentPane.add(lblAcrnim);
		
		textField = new JTextField();
		textField.setName("Acr");
		textField.setBounds(272, 96, 152, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setName("Nom");
		textField_1.setColumns(10);
		textField_1.setBounds(272, 55, 152, 33);
		contentPane.add(textField_1);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setHorizontalAlignment(SwingConstants.CENTER);
		lblNom.setBounds(132, 55, 152, 33);
		contentPane.add(lblNom);
		
		JButton btnNewButton = new JButton("Tipus de Sessions");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SessionsAssig sa = SessionsAssig.getInstance();
				sa.setAssig(textField_1.getText());
				sa.botodisparador(disparador);
				sa.refreshPanel();
				sa.setVisible(true);
			}
		});
		btnNewButton.setBounds(272, 184, 152, 33);
		contentPane.add(btnNewButton);
		
		JButton btnAfegirGrups = new JButton("Grups");
		btnAfegirGrups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GrupsAssig gas = GrupsAssig.getInstance();
				gas.setAssig(textField_1.getText());
				gas.botodisparador(disparador);
				gas.refreshPanel();				
				gas.setVisible(true);
			}
		});
		btnAfegirGrups.setBounds(272, 223, 152, 33);
		contentPane.add(btnAfegirGrups);
		
		JButton btnCrea = new JButton("Accepta");
		btnCrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GestioAssigs ga = GestioAssigs.getInstance();
				ErrorAssigExists eae = ErrorAssigExists.getInstance();
				Integer nivell;
				
				//enviar a ctrlpresentacio el contingut de l'assig, grups i sessions
				try {
					nivell = Integer.parseInt(textField_2.getText());
				} catch (NumberFormatException numberE) {
					nivell = 1; 
					System.out.println(nivell);
				}
				Boolean t;
				if (disparador == "modif") {
					t = CtrlP.modificarAssignatura(textField.getText(),textField_1.getText(),  nivell, grups, coreqs, null, null);
					if (t == false) {
						eae = ErrorAssigExists.getInstance();
						eae.setVisible(true);
					}
					else {
						textField.setText(null);
						textField_1.setText(null);
						textField_2.setText(null);
						ga.refreshPanel();
						ga.setVisible(true);
					}
				}
				else if (disparador == "crear") {
 					t = CtrlP.crearAssignatura (textField.getText(), textField_1.getText(), nivell, grups, coreqs, tsessions);
					if (t == false) {
						eae = ErrorAssigExists.getInstance();
						eae.setVisible(true);
					}
					else {
						textField.setText(null);
						textField_1.setText(null);
						textField_2.setText(null);
						ga.refreshPanel();
						ga.setVisible(true);
					}
				}
				else if (disparador == "cons") {
					textField.setText(null);
					textField_1.setText(null);
					textField_2.setText(null);
					ga.refreshPanel();
					ga.setVisible(true);
				}
			}
		});
		btnCrea.setBounds(492, 302, 152, 33);
		contentPane.add(btnCrea);
		
		JLabel lblNivell = new JLabel("Nivell");
		lblNivell.setHorizontalAlignment(SwingConstants.CENTER);
		lblNivell.setBounds(132, 140, 152, 33);
		contentPane.add(lblNivell);
		
		textField_2 = new JTextField();
		textField_2.setName("Niv");
		textField_2.setColumns(10);
		textField_2.setBounds(272, 140, 152, 33);
		contentPane.add(textField_2);
		
		JButton btnCorequisits = new JButton("Corequisits");
		btnCorequisits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Corequisit wc = Corequisit.getInstance();
				wc.setAssig(textField_1.getText());
				wc.botodisparador(disparador);
				wc.refreshPanel();
				wc.setVisible(true);
			}
		});
		btnCorequisits.setBounds(272, 262, 152, 33);
		contentPane.add(btnCorequisits);
	}
	public void setVisible(Boolean visible) {
		contentPane.setVisible(visible);
	}
	public void setDades(String nom, String acronim, String nivell) {
		Component[] comp = contentPane.getComponents();
		for (int i = 0; i<comp.length; i++) {
			if(comp[i].getName() == "Nom") textField_1.setText(nom);
			if(comp[i].getName() == "Acr") textField.setText(acronim);
			if(comp[i].getName() == "Niv") textField_2.setText(nivell);		
		}
		if (disparador == "crear") {
			textField.setEditable(true);
			textField_1.setEditable(true);
			textField_2.setEditable(true);			
		}
		else if (disparador == "modif" ) {
			textField.setEditable(true);
			textField_1.setEditable(false);
			textField_2.setEditable(true);
		}
		else if (disparador == "cons") {
			textField.setEditable(false);
			textField_1.setEditable(false);
			textField_2.setEditable(false);
		}
	}
	public void botodisparador(String disp) {
		disparador = disp;
		
	}
	public void setCoreqs(Vector<Pair<String,Boolean>> corequisits) {
		Vector<String> cq = new Vector<String>();
		if (!corequisits.isEmpty()) {
			for (int i = 0; i<corequisits.size(); i++) {
				if (corequisits.get(i).getValue() == true) cq.add(corequisits.get(i).getKey());
			}		
		}
		this.coreqs = cq;
	}
	public void setGrups(Vector<Pair<String,String>> grups) {
		this.grups = grups;
	}
	public void setTSessions(Vector<String> tsessions) {
		this.tsessions = tsessions;
	}
	
	WindowListener exitListener = new WindowAdapter() {

	    @Override
	    public void windowClosing(WindowEvent e) {
	        int confirm = JOptionPane.showOptionDialog(
	             null, "Estàs segur que vols tancar l'aplicació?", 
	             "Confirmació de tancament", JOptionPane.YES_NO_OPTION, 
	             JOptionPane.QUESTION_MESSAGE, null, null, null);
	        try {
				CtrlP.guardarDades();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	        if (confirm == 0) {
	           System.exit(0);
	        }
	    }
	};
	
}

