package presentacio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.SwingConstants;

import domini.ConjuntGrupAssig;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;

public class Principal {
	private static Principal pr;
	private ControladorPresentacio CtrlP = new ControladorPresentacio();
	private JFrame frame;


	/**
	 * Create the application.
	 */
	public Principal() {
		frame = new JFrame();
		initialize();
	}
	
	public static Principal getInstance() {
		if (pr == null) pr = new Principal();
		return pr;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame.setBounds(100, 100, 670, 385);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(exitListener);
		frame.getContentPane().setLayout(null);
		
		JLabel lblScheduleGenerator = new JLabel("Schedule Generator");
		lblScheduleGenerator.setBounds(0, 0, 654, 43);
		lblScheduleGenerator.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblScheduleGenerator.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(lblScheduleGenerator);
		
		JButton btnNewButton = new JButton("Gesti� Assignatures");
		btnNewButton.setBounds(26, 86, 182, 43);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				GestioAssigs wga = GestioAssigs.getInstance();
				wga.setVisible(true);
			}
		});
		frame.getContentPane().add(btnNewButton);
		
		JButton btnGestiAules = new JButton("Gesti\u00F3 Aules");
		btnGestiAules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GestioAules wgau = GestioAules.getInstance();
				wgau.setVisible(true);
			}
		});
		btnGestiAules.setBounds(232, 86, 182, 43);
		frame.getContentPane().add(btnGestiAules);
		
		JButton btnGestiGrups = new JButton("Gesti\u00F3 Grups");
		btnGestiGrups.setBounds(441, 86, 182, 43);
		btnGestiGrups.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GestioGrups wgg = GestioGrups.getInstance();
				wgg.setVisible(true);
			}
		});
		frame.getContentPane().add(btnGestiGrups);
		
		JButton btnGestiTipusAula = new JButton("Gesti\u00F3 Tipus Aules");
		btnGestiTipusAula.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GestioTipusAules wgtau = GestioTipusAules.getInstance();
				wgtau.setVisible(true);
			}
		});
		btnGestiTipusAula.setBounds(26, 160, 182, 43);
		frame.getContentPane().add(btnGestiTipusAula);
		
		JButton btnGestiTipusSessi = new JButton("Gesti\u00F3 Tipus Sessions");
		btnGestiTipusSessi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GestioTipusSessions gts = GestioTipusSessions.getInstance();
				gts.setVisible(true);
			}
		});
		btnGestiTipusSessi.setBounds(232, 160, 182, 43);
		frame.getContentPane().add(btnGestiTipusSessi);
		
		JButton btnGestiHoresBloquejades = new JButton("Gesti\u00F3 Hores Bloquejades");
		btnGestiHoresBloquejades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				GestioHoresBloquejades ghb = GestioHoresBloquejades.getInstance();
				ghb.setVisible(true);
			}
		});
		btnGestiHoresBloquejades.setBounds(441, 160, 182, 43);
		frame.getContentPane().add(btnGestiHoresBloquejades);
		
		JButton btnHorari = new JButton("HORARI");
		btnHorari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Horari h = Horari.getInstance();
				h.setVisible(true);
			}
		});
		btnHorari.setBounds(232, 239, 182, 58);
		frame.getContentPane().add(btnHorari);
	}
	public void setVisible(Boolean visible) {
		frame.setVisible(visible);
	}
	WindowListener exitListener = new WindowAdapter() {

	    @Override
	    public void windowClosing(WindowEvent e) {
	        int confirm = JOptionPane.showOptionDialog(
	             null, "Est�s segur que vols tancar l'aplicaci�?", 
	             "Confirmaci� de tancament", JOptionPane.YES_NO_OPTION, 
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
