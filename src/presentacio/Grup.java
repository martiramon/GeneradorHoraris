package presentacio;

import java.awt.BorderLayout;
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
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Grup extends JFrame {
	
	private static Grup g;
	private ControladorPresentacio CtrlP = new ControladorPresentacio();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private String disparador;
	
	public static Grup getInstance() {
		if (g == null) g = new Grup();
		return g;
	}
	/**
	 * Launch the application.
	 */
	public static void newGrup() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grup frame = Grup.getInstance();
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
	public Grup() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(exitListener);
		setBounds(100, 100, 670, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGestiAssignatures = new JLabel("Grup");
		lblGestiAssignatures.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGestiAssignatures.setBounds(10, 11, 289, 40);
		contentPane.add(lblGestiAssignatures);
		
		JButton button = new JButton("Endarrere");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				if (disparador == "grupAssig") {
					GrupsAssig gas = GrupsAssig.getInstance();
					gas.setVisible(true);
				}
				else {
					GestioGrups gg = GestioGrups.getInstance();
				gg.setVisible(true);
				}				
				textField.setText(null);
				textField_1.setText(null);
			}
		});
		button.setBounds(10, 302, 152, 33);
		contentPane.add(button);
		
		JLabel lblAcrnim = new JLabel("Número");
		lblAcrnim.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcrnim.setBounds(133, 73, 152, 33);
		contentPane.add(lblAcrnim);
		
		textField = new JTextField();
		textField.setName("grup");
		textField.setBounds(273, 73, 152, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setName("capac");
		textField_1.setColumns(10);
		textField_1.setBounds(273, 117, 152, 33);
		contentPane.add(textField_1);
		
		JLabel lblNom = new JLabel("Capacitat");
		lblNom.setHorizontalAlignment(SwingConstants.CENTER);
		lblNom.setBounds(133, 117, 152, 33);
		contentPane.add(lblNom);
		
		JButton btnCrea = new JButton("Accepta");
		btnCrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GestioGrups gg = GestioGrups.getInstance();
				GrupsAssig gas = GrupsAssig.getInstance();
				ErrorGrupExists ege = ErrorGrupExists.getInstance();
				Integer capacitat, id;
				if (textField.getText().equals("") || textField_1.getText().equals("")) {
					ErrorFaltenCamps efc = ErrorFaltenCamps.getInstance();
					efc.setVisible(true);
				}
				else {
					try {
						capacitat = Integer.parseInt(textField_1.getText());
						id = Integer.parseInt(textField.getText());
					} catch (NumberFormatException numberE) {
						ErrorNombreEsperat ene = new ErrorNombreEsperat();
						ene.setVisible(true);
					}
					Boolean t;
					if (disparador == "modif") {
							t = CtrlP.modificarGrup(textField.getText(), textField_1.getText());
							if (t == false) {
								ege = ErrorGrupExists.getInstance();
								ege.setVisible(true);
							}
							else {
								textField.setText(null);
								textField_1.setText(null);
								gg.refreshPanel();
								gg.setVisible(true);
							}
						}
					else if (disparador == "crear") {
						t = CtrlP.crearGrup(textField.getText(), textField_1.getText());
						if (t == false) {
							ege = ErrorGrupExists.getInstance();
							ege.setVisible(true);
						}					
						else {
							textField.setText(null);
							textField_1.setText(null);
							gg.refreshPanel();
							gg.setVisible(true);
						}				
					}
					else if (disparador == "cons") {
						textField.setText(null);
						textField_1.setText(null);
						gg.refreshPanel();
						gg.setVisible(true);
					}
					else if (disparador == "grupAssig") {
						t = CtrlP.crearGrup(textField.getText(), textField_1.getText());
						if (t == false) {
							ege = ErrorGrupExists.getInstance();
							ege.setVisible(true);
						}					
						else {
							textField.setText(null);
							textField_1.setText(null);
							gg.refreshPanel();
							gas.setVisible(false);
							gas.refreshPanel();
							gas.setVisible(true);
						}
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
	public void setDades(String grup, String capacitat) {
		Component[] comp = contentPane.getComponents();
		for (int i = 0; i<comp.length; i++) {
			if(comp[i].getName() == "grup") textField.setText(grup);
			if(comp[i].getName() == "capac") textField_1.setText(capacitat);
		}
		if (disparador == "crear" || disparador == "grupAssig") {
			textField.setEditable(true);
			textField_1.setEditable(true);
		
		}
		else if (disparador == "modif" ) {
			textField.setEditable(false);
			textField_1.setEditable(true);

		}
		else if (disparador == "cons") {
			textField.setEditable(false);
			textField_1.setEditable(false);
		}
	}
	public void botodisparador(String disp) {
		disparador = disp;
		
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