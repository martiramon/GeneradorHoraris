package presentacio;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ComboBoxModel;
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
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class Aula extends JFrame {
	
	private static Aula au;
	private ControladorPresentacio CtrlP = new ControladorPresentacio();
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox<String> comboBox;
	private JButton btnNoutTipusDaula;
	private String disparador;
	
	public static Aula getInstance() {
		if (au == null) au = new Aula();
		return au;
	}
	/**
	 * Launch the application.
	 */
	public static void newAula() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aula frame = Aula.getInstance();
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
	public Aula() {
		inicialitzaPanel();
	}
	public void setVisible(Boolean visible) {
		contentPane.setVisible(visible);
	}
	
	public void botodisparador(String disp) {
		disparador = disp;
		
	}
	private void inicialitzaPanel() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(exitListener);
		setBounds(100, 100, 670, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGestiAssignatures = new JLabel("Aula");
		lblGestiAssignatures.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGestiAssignatures.setBounds(10, 11, 289, 40);
		contentPane.add(lblGestiAssignatures);
		
		comboBox = new JComboBox<String>();
		comboBox.setName("tipus");
		comboBox.setBounds(273, 161, 152, 33);
		Vector<String> tipusA = CtrlP.getAllTipusAules();
		for (int i=0; i<tipusA.size(); i++) {
			comboBox.addItem(tipusA.get(i));
		}
		contentPane.add(comboBox);
		
		JButton button = new JButton("Endarrere");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GestioAules gau = GestioAules.getInstance();
				gau.setVisible(true);
				textField.setText(null);
				textField_1.setText(null);
				comboBox.setSelectedIndex(-1);
			}
		});
		button.setBounds(10, 302, 152, 33);
		contentPane.add(button);
		
		JLabel lblAcrnim = new JLabel("Nom");
		lblAcrnim.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcrnim.setBounds(133, 73, 152, 33);
		contentPane.add(lblAcrnim);
		
		textField = new JTextField();
		textField.setName("id");
		textField.setBounds(273, 73, 152, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setName("capac");
		textField_1.setColumns(10);
		textField_1.setBounds(273, 117, 152, 33);
		contentPane.add(textField_1);
		
		JLabel lblTipusDaula = new JLabel("Tipus d'Aula");
		lblTipusDaula.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipusDaula.setBounds(133, 161, 152, 33);
		contentPane.add(lblTipusDaula);

		
		
		JLabel lblNom = new JLabel("Capacitat");
		lblNom.setHorizontalAlignment(SwingConstants.CENTER);
		lblNom.setBounds(133, 117, 152, 33);
		contentPane.add(lblNom);
		
		JButton btnCrea = new JButton("Accepta");
		btnCrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GestioAules gau = GestioAules.getInstance();
				ErrorAulaExists eaue = ErrorAulaExists.getInstance();
				Integer capacitat;
				try {
					capacitat = Integer.parseInt(textField_1.getText());
				} catch (NumberFormatException numberE) {
					capacitat = 1; 
					System.out.println(capacitat);
				}
				Boolean t;
				if (disparador == "modif") {
					t = CtrlP.modificarAula(textField.getText(), Integer.getInteger(textField_1.getText()), comboBox.getItemAt(comboBox.getSelectedIndex()));
					if (t == false) {
						eaue = ErrorAulaExists.getInstance();
						eaue.setVisible(true);
					}
					else {
						textField.setText(null);
						textField_1.setText(null);
						comboBox.setSelectedIndex(-1);
						gau.refreshPanel();
						gau.setVisible(true);
					}
				}
				else if (disparador == "crear") {
					t = CtrlP.crearAula(textField.getText(), textField_1.getText(), comboBox.getItemAt(comboBox.getSelectedIndex()));
					if (t == false) {
						eaue = ErrorAulaExists.getInstance();
						eaue.setVisible(true);
					}
					else {
						textField.setText(null);
						textField_1.setText(null);
						comboBox.setSelectedIndex(-1);
						gau.refreshPanel();
						gau.setVisible(true);
					}
				}
				else if (disparador == "cons") {
					textField.setText(null);
					textField_1.setText(null);
					comboBox.setSelectedIndex(-1);
					gau.refreshPanel();
					gau.setVisible(true);
				}
			}
		});
		
		btnCrea.setBounds(492, 302, 152, 33);
		contentPane.add(btnCrea);
		btnNoutTipusDaula = new JButton("Nou Tipus d'Aula");
		btnNoutTipusDaula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				TipusAula ta = TipusAula.getInstance();
				ta.botodisparador("Aula");
				ta.setVisible(true);
			}
		});
		btnNoutTipusDaula.setBounds(437, 161, 152, 33);
		contentPane.add(btnNoutTipusDaula);
	}
	
	public void refreshPanel() {
		contentPane.removeAll();
		inicialitzaPanel();
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	public void setDades(String id, String capacitat, String tipusA) {
		textField.setText(id);
		textField_1.setText(capacitat);
		int size = comboBox.getItemCount();
		for (int i = 0; i < size; i++) {		
		if (comboBox.getItemAt(i).toString ().contains (tipusA)) {
		    comboBox.setSelectedIndex(i);
		}
		}
		//comboBox.setSelectedItem(tipusA);
		
		if (disparador == "crear") {
			textField.setEditable(true);
			textField_1.setEditable(true);
			btnNoutTipusDaula.setEnabled(true);
			comboBox.setEnabled(true);
		
		}
		else if (disparador == "modif" ) {
			textField.setEditable(false);
			textField_1.setEditable(true);
			btnNoutTipusDaula.setEnabled(true);
			comboBox.setEnabled(true);

		}
		else if (disparador == "cons") {
			textField.setEditable(false);
			textField_1.setEditable(false);
			btnNoutTipusDaula.setEnabled(false);
			comboBox.setEnabled(false);
		}
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
