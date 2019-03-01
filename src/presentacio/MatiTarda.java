package presentacio;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import FONTS.esaus.GrupAssig;
import javafx.util.Pair;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;

public class MatiTarda extends JDialog {
	
	private static MatiTarda mt;
	private ControladorPresentacio CtrlP = new ControladorPresentacio();
	private JPanel contentPane;
	private String disparador;
	private String assignatura;
	private String grup;
	
	public static MatiTarda getInstance() {
		if (mt == null) mt = new MatiTarda();
		return mt;
	}
	/**
	 * Launch the application.
	 */
	public static void newMatiTarda() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MatiTarda frame = MatiTarda.getInstance();
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
	public MatiTarda() {
		inicialitzaPanel();
	}
	
	void inicialitzaPanel() {
		setBounds(100, 100, 456, 225);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblGestiAssignatures = new JLabel("Franja Hor\u00E0ria del Grup per l'Assignatura");
		lblGestiAssignatures.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGestiAssignatures.setBounds(10, 11, 440, 40);
		contentPane.add(lblGestiAssignatures);
		
		JRadioButton rdbtnMati = new JRadioButton("Mat\u00ED");
		
		
		rdbtnMati.setBounds(65, 81, 109, 23);
		contentPane.add(rdbtnMati);
		
		JRadioButton rdbtnTarda = new JRadioButton("Tarda");
		rdbtnTarda.setBounds(237, 81, 109, 23);
		contentPane.add(rdbtnTarda);
		
		rdbtnMati.setSelected(false);
		rdbtnTarda.setSelected(false);
		
		ButtonGroup bG = new ButtonGroup();
		bG.add(rdbtnMati);
		bG.add(rdbtnTarda);
		
		
		if (disparador =="modif" || disparador == "cons") {
			Pair<String,String> grupAssig = new Pair<String,String>(assignatura,grup);
			Boolean selected;
			selected = CtrlP.getMati(grupAssig); 
			if (selected != null) {
				rdbtnMati.setSelected(selected);
				rdbtnTarda.setSelected(!selected);
			}
		}
		JButton btnCrea = new JButton("Accepta");
		btnCrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GrupsAssig ga = GrupsAssig.getInstance();
				if (disparador == "modif" || disparador == "crear") {
					Vector<Pair<Boolean,String>> test = ga.getMati();
					
					if (rdbtnMati.isSelected()) {
						Pair<Boolean,String> pga = new Pair<Boolean,String>(true, grup);
						test.add(pga);
						ga.setMati(test);
					}
					else {
						Pair<Boolean,String> pga = new Pair<Boolean,String>(false, grup);
						test.add(pga);
						ga.setMati(test);
					}
				}
				else if (disparador == "cons") {					
					ga.setVisible(true);
				}
			}
		});
		btnCrea.setBounds(265, 141, 152, 33);
		contentPane.add(btnCrea);
		
		
	}
	public void setVisible(Boolean visible) {
		contentPane.setVisible(visible);
	}
	
	public void botodisparador(String disp) {
		disparador = disp;
		
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
	
	public void setGrup(String grup) {
		this.grup = grup;
	}
}