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

public class TipusAula extends JFrame {
	
	private static TipusAula tau;
	private ControladorPresentacio CtrlP = new ControladorPresentacio();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private String disparador;
	
	public static TipusAula getInstance() {
		if (tau == null) tau = new TipusAula();
		return tau;
	}
	/**
	 * Launch the application.
	 */
	public static void newTipusAula() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TipusAula frame = TipusAula.getInstance();
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
	public TipusAula() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(exitListener);
		setBounds(100, 100, 670, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGestiTipusAula = new JLabel("Tipus");
		lblGestiTipusAula.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGestiTipusAula.setBounds(10, 11, 289, 40);
		contentPane.add(lblGestiTipusAula);
		
		JButton button = new JButton("Endarrere");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GestioTipusAules gtau = GestioTipusAules.getInstance();
				gtau.setVisible(true);
				textField.setText(null);
			}
		});
		button.setBounds(10, 302, 152, 33);
		contentPane.add(button);		
		
		textField = new JTextField();
		textField.setName("nom");
		textField.setBounds(273, 73, 152, 33);
		contentPane.add(textField);
		textField.setColumns(10);	
		
		JButton btnCrea = new JButton("Accepta");
		btnCrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GestioTipusAules gtau = GestioTipusAules.getInstance();
				Aula au = Aula.getInstance();
				TipusSessio ts = TipusSessio.getInstance();
				ErrorTipusAulaExists etaue = ErrorTipusAulaExists.getInstance();
				Boolean t;				
				if (disparador == "crear") {
					t = CtrlP.crearTipusAula(textField.getText());
					if (t == false) {
						etaue = ErrorTipusAulaExists.getInstance();
						etaue.setVisible(true);
					}
					else {
						textField.setText(null);
						gtau.refreshPanel();
						setVisible(false);
						gtau.setVisible(true);
					}
				}
				else if (disparador == "cons") {
					textField.setText(null);
					setVisible(false);
					gtau.refreshPanel();
					gtau.setVisible(true);
				}
				else if (disparador == "SessioAssig") {
					t = CtrlP.crearTipusAula(textField.getText());
					if (t == false) {
						etaue = ErrorTipusAulaExists.getInstance();
						etaue.setVisible(true);
					}
					else {
						textField.setText(null);
						setVisible(false);
						gtau.refreshPanel();
						ts.refreshPanel();
						ts.setVisible(true);
					}
				}
				else if (disparador == "Aula") {
					t = CtrlP.crearTipusAula(textField.getText());
					if (t == false) {
						etaue = ErrorTipusAulaExists.getInstance();
						etaue.setVisible(true);
					}
					else {
						textField.setText(null);
						setVisible(false);
						gtau.refreshPanel();
						au.refreshPanel();
						au.setVisible(true);
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
	
	public void setDades(String nom) {
		Component[] comp = contentPane.getComponents();
		for (int i = 0; i<comp.length; i++) {
			if(comp[i].getName() == "nom") textField.setText(nom);
		}
		if (disparador == "crear") {
			textField.setEditable(true);
		}
		else if (disparador == "cons") {
			textField.setEditable(false);
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