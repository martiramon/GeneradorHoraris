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
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class GestioHoresBloquejades extends JFrame {
	
	private static GestioHoresBloquejades ghb;
	private ControladorPresentacio CtrlP = new ControladorPresentacio();
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	public static GestioHoresBloquejades getInstance() {
		if (ghb == null) ghb = new GestioHoresBloquejades();
		return ghb;
	}
	/**
	 * Launch the application.
	 */
	public static void newGestioHoresBloquejades() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestioHoresBloquejades frame = GestioHoresBloquejades.getInstance();
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
	public GestioHoresBloquejades() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(exitListener);
		setBounds(100, 100, 670, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGestiAssignatures = new JLabel("Gesti\u00F3 Hores Bloquejades");
		lblGestiAssignatures.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGestiAssignatures.setBounds(10, 11, 289, 40);
		contentPane.add(lblGestiAssignatures);
		
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
		
		String[] columnNames = {"Dilluns",
                "Dimarts",
                "Dimecres",
                "Dijous",
                "Divendres"};
		
		Object[][] data = {
			    {new Integer(8), new Integer(8), new Integer(8), new Integer(8), new Integer(8)},
			    {new Integer(9), new Integer(9), new Integer(9), new Integer(9), new Integer(9)},
			    {new Integer(10), new Integer(10), new Integer(10), new Integer(10), new Integer(10)},
			    {new Integer(11), new Integer(11), new Integer(11), new Integer(11), new Integer(11)},
			    {new Integer(12), new Integer(12), new Integer(12), new Integer(12), new Integer(12)},
			    {new Integer(13), new Integer(13), new Integer(13), new Integer(13), new Integer(13)},
			    {new Integer(14), new Integer(14), new Integer(14), new Integer(14), new Integer(14)},
			    {new Integer(15), new Integer(15), new Integer(15), new Integer(15), new Integer(15)},
			    {new Integer(16), new Integer(16), new Integer(16), new Integer(16), new Integer(16)},
			    {new Integer(17), new Integer(17), new Integer(17), new Integer(17), new Integer(17)},
			    {new Integer(18), new Integer(18), new Integer(18), new Integer(18), new Integer(18)},
			    {new Integer(19), new Integer(19), new Integer(19), new Integer(19), new Integer(19)}
		};
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(43, 56, 534, 221);
		contentPane.add(scrollPane);
		table_1 = new JTable(data,columnNames);
		table_1.setBackground(new Color(144, 238, 144));
		table_1.setFillsViewportHeight(true);
		table_1.setRowSelectionAllowed(false);
		table_1.setModel(new DefaultTableModel(data, columnNames) {
			
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table_1.getColumnModel().getColumn(0).setResizable(false);
		table_1.getColumnModel().getColumn(1).setResizable(false);
		table_1.getColumnModel().getColumn(2).setResizable(false);
		table_1.getColumnModel().getColumn(3).setResizable(false);
		table_1.getColumnModel().getColumn(4).setResizable(false);
		table_1.setEnabled(true);
		scrollPane.setViewportView(table_1);
		table_1.setCellSelectionEnabled(true);
		table_1.setSurrendersFocusOnKeystroke(true);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
	};
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
