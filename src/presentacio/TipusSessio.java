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
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class TipusSessio extends JFrame {
	
	private static TipusSessio ts;
	private ControladorPresentacio CtrlP = new ControladorPresentacio();
	private JPanel contentPane;
	private JTextField textField;
	private JComboBox<String> comboBox;
	private JButton btnNoutTipusDaula;
	private String disparador;
	private JTextField textField_1;
	
	public static TipusSessio getInstance() {
		if (ts == null) ts = new TipusSessio();
		return ts;
	}
	/**
	 * Launch the application.
	 */
	public static void newTipusSessio() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TipusSessio frame = TipusSessio.getInstance();
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
	
	
	public TipusSessio() {
		inicialitzaPanel();
	}
	
	private void inicialitzaPanel() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(exitListener);
		setBounds(100, 100, 670, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGestiAssignatures = new JLabel("Tipus Sessió");
		lblGestiAssignatures.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGestiAssignatures.setBounds(10, 11, 289, 40);
		contentPane.add(lblGestiAssignatures);
		
		JButton button = new JButton("Endarrere");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (disparador == "SessioAssig") {
					SessionsAssig sa = SessionsAssig.getInstance();
					sa.setVisible(true);
				}
				else {
					GestioTipusSessions gts = GestioTipusSessions.getInstance();
				gts.setVisible(true);
				}
				setVisible(false);				
				textField.setText(null);
				comboBox.setSelectedIndex(-1);
			}
		});
		button.setBounds(10, 302, 152, 33);
		contentPane.add(button);
		
		JLabel lblAcrnim = new JLabel("Nom");
		lblAcrnim.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcrnim.setBounds(134, 74, 152, 33);
		contentPane.add(lblAcrnim);
		
		textField = new JTextField();
		textField.setName("id");
		textField.setBounds(274, 74, 152, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblTipusDaula = new JLabel("Tipus d'Aula");
		lblTipusDaula.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipusDaula.setBounds(134, 130, 152, 33);
		contentPane.add(lblTipusDaula);

		comboBox = new JComboBox<String>();
		comboBox.setName("tipus");
		comboBox.setBounds(274, 130, 152, 33);
		Vector<String> tipusA = CtrlP.getAllTipusAules();
		JLabel lbltemp = new JLabel();
		for (int i=0; i<tipusA.size(); i++) {
			/*lbltemp.setText(tipusA.get(i));
			comboBox.add(lbltemp);*/
			comboBox.addItem(tipusA.get(i));
		}
		contentPane.add(comboBox);
		
		JButton btnCrea = new JButton("Accepta");
		btnCrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GestioTipusSessions gts = GestioTipusSessions.getInstance();
				SessionsAssig sa = SessionsAssig.getInstance();
				ErrorTipusSessioExists etse = ErrorTipusSessioExists.getInstance();
				Boolean t;
				if (disparador == "modif") {
					t = CtrlP.modificarTipusSessio(textField.getText(), comboBox.getItemAt(comboBox.getSelectedIndex()));
					if (t == false) {
						etse = ErrorTipusSessioExists.getInstance();
						etse.setVisible(true);
					}
					else {
						textField.setText(null);
						comboBox.setSelectedIndex(-1);
						gts.refreshPanel();
						gts.setVisible(true);
					}
				}
				else if (disparador == "crear") {
					t = CtrlP.crearTipusSessio(textField.getText(), comboBox.getItemAt(comboBox.getSelectedIndex()), textField_1.getText());
					if (t == false) {
						etse = ErrorTipusSessioExists.getInstance();
						etse.setVisible(true);
					}
					else {
						textField.setText(null);
						comboBox.setSelectedIndex(-1);
						gts.refreshPanel();
						gts.setVisible(true);
					}
				}
				else if (disparador == "cons") {
					textField.setText(null);
					comboBox.setSelectedIndex(-1);
					gts.refreshPanel();
					gts.setVisible(true);
				}
				else if (disparador == "SessioAssig") {
					t = CtrlP.crearTipusSessio(textField.getText(), comboBox.getItemAt(comboBox.getSelectedIndex()), textField_1.getText());
					if (t == false) {
						etse = ErrorTipusSessioExists.getInstance();
						etse.setVisible(true);
					}
					else {
						textField.setText(null);
						comboBox.setSelectedIndex(-1);
						gts.refreshPanel();
						sa.refreshPanel();
						sa.setVisible(true);
					}
				}
			}
		});
		btnCrea.setBounds(492, 302, 152, 33);
		contentPane.add(btnCrea);
		
		JLabel lblHores = new JLabel("Hores");
		lblHores.setHorizontalAlignment(SwingConstants.CENTER);
		lblHores.setBounds(134, 184, 152, 33);
		contentPane.add(lblHores);
		
		textField_1 = new JTextField();
		textField_1.setName("hores");
		textField_1.setColumns(10);
		textField_1.setBounds(274, 184, 152, 33);
		contentPane.add(textField_1);
		
		btnNoutTipusDaula = new JButton("Nou Tipus d'Aula");
		btnNoutTipusDaula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				TipusAula ta = TipusAula.getInstance();
				ta.botodisparador("SessioAssig");
				ta.setVisible(true);
			}
		});
		btnNoutTipusDaula.setBounds(452, 130, 152, 33);
		contentPane.add(btnNoutTipusDaula);
	}
	
	public void refreshPanel() {
		contentPane.removeAll();
		inicialitzaPanel();
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	public void setVisible(Boolean visible) {
		contentPane.setVisible(visible);
	}
	public void setDades(String id, String hores, String tipusA) {
		textField.setText(id);
		comboBox.setSelectedItem(tipusA);
		int size = comboBox.getItemCount();
		for (int i = 0; i < size; i++) {		
			if (comboBox.getItemAt(i).toString ().contains (tipusA)) {
		    comboBox.setSelectedIndex(i);
			}
		}
		if (disparador == "crear") {
			textField.setEditable(true);
			textField_1.setEditable(true);
			comboBox.setEnabled(true);
			btnNoutTipusDaula.setEnabled(true);
			comboBox.setEnabled(true);
		
		}
		else if (disparador == "modif" ) {
			textField.setEditable(false);
			textField_1.setEditable(true);
			comboBox.setEnabled(true);
			btnNoutTipusDaula.setEnabled(true);
			comboBox.setEnabled(true);

		}
		else if (disparador == "cons") {
			textField.setEditable(false);
			textField_1.setEditable(false);
			comboBox.setEnabled(false);
			btnNoutTipusDaula.setEnabled(false);
			comboBox.setEnabled(false);
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
