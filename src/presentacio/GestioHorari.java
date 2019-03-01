package presentacio;

import java.awt.BorderLayout;
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

public class GestioHorari extends JFrame {
	private static GestioHorari gh;
	private ControladorPresentacio CtrlP;
	private JPanel contentPane;
	
	public static GestioHorari getInstance() {
		if (gh == null) gh = new GestioHorari();
		return gh;
	}

	/**
	 * Launch the application.
	 */
	public static void newGestioHorari() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestioHorari frame = GestioHorari.getInstance();
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
	public GestioHorari() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(exitListener);
		setBounds(100, 100, 670, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnGenerar = new JButton("Generar");
		btnGenerar.setBounds(231, 79, 152, 33);
		contentPane.add(btnGenerar);
		
		JLabel lblGestiAssignatures = new JLabel("Gesti\u00F3 Horari");
		lblGestiAssignatures.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGestiAssignatures.setBounds(10, 11, 289, 40);
		contentPane.add(lblGestiAssignatures);
		
		JButton btnCrear = new JButton("Modificar");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrear.setBounds(231, 123, 152, 33);
		contentPane.add(btnCrear);
		
		JButton button = new JButton("Endarrere");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Principal pr = Principal.getInstance();
				pr.setVisible(true);
			}
		});
		button.setBounds(10, 295, 152, 33);
		contentPane.add(button);
		
		JButton btnImportar = new JButton("Importar");
		btnImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnImportar.setBounds(154, 180, 152, 33);
		contentPane.add(btnImportar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.setBounds(316, 180, 152, 33);
		contentPane.add(btnConsultar);
	}
	public void setVisible(Boolean visible) {
		contentPane.setVisible(visible);
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
