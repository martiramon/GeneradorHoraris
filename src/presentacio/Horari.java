package presentacio;

import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import domini.HorariExceptions;
import javafx.util.Pair;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;

public class Horari extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2676129754368992206L;
	private static Horari h;
	private ControladorPresentacio CtrlP = new ControladorPresentacio();
	private JPanel contentPane;
	private JTable table;
	private String estat = "inicial";
	private String[][] data = new String[12][5];
	private Integer augmentTamanyCell = 0;
	
	public static Horari getInstance() {
		if (h == null) h = new Horari();
		return h;
	}
	/**
	 * Launch the application.
	 */
	public static void newHorari() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Horari frame = Horari.getInstance();
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
	public Horari() {
		inicialitzaPanel();
	}
	
	void inicialitzaPanel() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(exitListener);
		setBounds(100, 100, 1034, 890);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblGestiAssignatures = new JLabel("Horari");
		lblGestiAssignatures.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGestiAssignatures.setBounds(10, 11, 71, 40);
		contentPane.add(lblGestiAssignatures);
		
		JButton btnCrea = new JButton("Generar");
		
		btnCrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<String> assigs = new Vector<String>();
				Vector<String> aules = new Vector<String>();
				assigs = CtrlP.getAllAssigName();
				aules = CtrlP.getAllAulesIds();
				if (assigs.size() > 0 && aules.size()>0) {
					Boolean correcte = false;
					try {
						correcte = CtrlP.generarHorari();
					} catch (HorariExceptions e1) {
						e1.printStackTrace();
					}
					if (!correcte) {
						ErrorHorariNoPossible ehnp = ErrorHorariNoPossible.getInstance();
						ehnp.setVisible(true);
					}
					else {
						estat = "generat";
						refreshPanel();
					}
				}
				else {
					ErrorFaltenClassesHorari efch = ErrorFaltenClassesHorari.getInstance();
					efch.setVisible(true);
				}
			}
				
		});
		btnCrea.setBounds(799, 54, 152, 33);
		contentPane.add(btnCrea);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 111, 954, 552);
		contentPane.add(scrollPane);

		String[] columnNames = {"Dilluns",
                "Dimarts",
                "Dimecres",
                "Dijous",
                "Divendres"};
		
		
		/*for (int i=0; i<12; i++) {
			for (int j=0; j<5; j++) {
				data[i][j] = new String("");
			}
		}*/
		table = new JTable(data,columnNames);
		//contentPane.add(table);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(data, columnNames) {
			
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.setEnabled(true);
		scrollPane.setViewportView(table);
		table.setCellSelectionEnabled(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		for (int i=0; i<12; i++) {
			table.setRowHeight(i, 44 + augmentTamanyCell);
		}
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(207, 18, 145, 33);
		Vector<String> aules= CtrlP.getAllAulesIds();
		for (int i=0; i<aules.size(); i++) {
			comboBox.addItem(aules.get(i));
		}
		comboBox.setSelectedIndex(-1);
		if (estat != "generat") comboBox.setEnabled(false);
		contentPane.add(comboBox);
		
		JLabel lblAula = new JLabel("Aula");
		lblAula.setBounds(369, 26, 56, 16);
		contentPane.add(lblAula);
		
		JLabel lblAssignatura = new JLabel("Assignatura");
		lblAssignatura.setBounds(360, 62, 81, 16);
		contentPane.add(lblAssignatura);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(207, 54, 145, 33);
		Vector<String> assigs = CtrlP.getAllAssigName();
		for (int i=0; i<assigs.size(); i++) {
			comboBox_1.addItem(assigs.get(i));
		}
		comboBox_1.setSelectedIndex(-1);
		if (estat != "generat") comboBox_1.setEnabled(false);
		contentPane.add(comboBox_1);
		
		JButton btnImportar = new JButton("Importar");
		btnImportar.setBounds(124, 797, 102, 33);
		contentPane.add(btnImportar);
		
		JButton btnExportar = new JButton("Exportar");
		if (estat != "generat") btnExportar.setEnabled(false);
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnExportar.setBounds(10, 797, 102, 33);
		contentPane.add(btnExportar);
		
		JLabel label = new JLabel("Aula");
		label.setBounds(469, 805, 33, 16);
		contentPane.add(label);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(295, 751, 145, 33);
		Vector<String> hora = new Vector<String>();
		for (int i=8; i<21; i++) {
			hora.add(String.valueOf(i));
		}
		for (int i=0; i<hora.size(); i++) {
			comboBox_2.addItem(hora.get(i));
		}
		comboBox_2.setSelectedIndex(-1);
		if (estat != "generat") comboBox_2.setEnabled(false);
		contentPane.add(comboBox_2);
		
		JButton btnVisualitza = new JButton("Visualitza");
		btnVisualitza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pair<String[][],Integer> horari = null;
				if ((comboBox.getSelectedIndex() == -1) && !(comboBox_1.getSelectedIndex() == -1)) {
					String as = (String) comboBox_1.getItemAt(comboBox_1.getSelectedIndex());
					try {
						horari = CtrlP.getInfoHorariAssig(as);
					} catch (HorariExceptions e1) {
						e1.printStackTrace();
					}
					data = horari.getKey();
					Integer nfiles = horari.getValue();
					recalculateTamany (nfiles); 
					refreshPanel();
				} else if (!(comboBox.getSelectedIndex() == -1) && (comboBox_1.getSelectedIndex() == -1)) {
					String au = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
							try {
								horari = CtrlP.getInfoHorariAula(au);
							} catch (HorariExceptions e1) {
								e1.printStackTrace();
							}
							data = horari.getKey();
							Integer nfiles = horari.getValue();
							recalculateTamany (nfiles);
							refreshPanel();
				} else if (!(comboBox.getSelectedIndex() == -1) && !(comboBox_1.getSelectedIndex() == -1)) {
					String au = (String) comboBox.getItemAt(comboBox.getSelectedIndex());
					String as = (String) comboBox_1.getItemAt(comboBox_1.getSelectedIndex());
					try {
						horari = CtrlP.getInfoHorariAssigAula(as,au);
					} catch (HorariExceptions e1) {
						e1.printStackTrace();
					}
					data = horari.getKey();
					Integer nfiles = horari.getValue();
					recalculateTamany (nfiles);
					refreshPanel();
				}
			}
		});
		btnVisualitza.setBounds(447, 39, 96, 25);
		if (estat != "generat") btnVisualitza.setEnabled(false);
		contentPane.add(btnVisualitza);
		
		JLabel lblSessi = new JLabel("Hora");
		lblSessi.setBounds(467, 759, 76, 16);
		contentPane.add(lblSessi);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(295, 797, 145, 33);
		for (int i=0; i<aules.size(); i++) {
			comboBox_3.addItem(aules.get(i));
		}
		comboBox_3.setSelectedIndex(-1);
		if (estat != "generat") comboBox_3.setEnabled(false);
		contentPane.add(comboBox_3);
		
		JButton btnEndarrere = new JButton("Endarrere");
		btnEndarrere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Principal pr = Principal.getInstance();
				pr.setVisible(true);
			}
		});
		btnEndarrere.setBounds(852, 797, 152, 33);
		contentPane.add(btnEndarrere);
		
		JButton btnRestriccions = new JButton("Restriccions");
		btnRestriccions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Restriccions r = Restriccions.getInstance();
				r.setVisible(true);
			}
		});
		btnRestriccions.setBounds(799, 18, 152, 33);
		contentPane.add(btnRestriccions);
		
		JLabel lblModificarSessi = new JLabel("Sessi\u00F3 original");
		lblModificarSessi.setBounds(328, 676, 96, 16);
		contentPane.add(lblModificarSessi);
		
		JLabel lblVisualitzarSessions = new JLabel("Visualitzar sessions");
		lblVisualitzarSessions.setBounds(79, 43, 123, 16);
		contentPane.add(lblVisualitzarSessions);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(295, 705, 145, 33);
		
		for (int i=0; i<columnNames.length; i++) {
			comboBox_4.addItem(columnNames[i]);
		}
		comboBox_4.setSelectedIndex(-1);
		if (estat != "generat") comboBox_4.setEnabled(false);
		contentPane.add(comboBox_4);
		
		JLabel lblDia = new JLabel("Dia");
		lblDia.setBounds(470, 713, 46, 16);
		contentPane.add(lblDia);
		
		JLabel lblSessiModificada = new JLabel("Sessi\u00F3 Modificada");
		lblSessiModificada.setBounds(561, 676, 96, 16);
		contentPane.add(lblSessiModificada);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(528, 705, 145, 33);
		for (int i=0; i<columnNames.length; i++) {
			comboBox_5.addItem(columnNames[i]);
		}
		comboBox_5.setSelectedIndex(-1);
		if (estat != "generat") comboBox_5.setEnabled(false);
		contentPane.add(comboBox_5);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setBounds(528, 751, 145, 33);
		for (int i=0; i<hora.size(); i++) {
			comboBox_6.addItem(hora.get(i));
		}
		comboBox_6.setSelectedIndex(-1);
		if (estat != "generat") comboBox_6.setEnabled(false);
		contentPane.add(comboBox_6);
		
		JComboBox comboBox_7 = new JComboBox();
		comboBox_7.setBounds(528, 797, 145, 33);
		for (int i=0; i<aules.size(); i++) {
			comboBox_7.addItem(aules.get(i));
		}
		comboBox_7.setSelectedIndex(-1);
		if (estat != "generat") comboBox_7.setEnabled(false);
		contentPane.add(comboBox_7);
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if ((comboBox_2.getSelectedIndex() == -1) || (comboBox_3.getSelectedIndex() == -1) ||
					(comboBox_3.getSelectedIndex() == -1) || (comboBox_4.getSelectedIndex() == -1) || 
					(comboBox_5.getSelectedIndex() == -1) || (comboBox_6.getSelectedIndex() == -1)) {
					ErrorTotsElsCampsPerModificar etcpm = ErrorTotsElsCampsPerModificar.getInstance();
					etcpm.setVisible(true);
				}
				else {
					String oday = null, ohour, oaula, nday = null, nhour,naula;
					if (((String)comboBox_4.getSelectedItem()).equals("Dilluns")) oday = "0";
					if (((String)comboBox_4.getSelectedItem()).equals("Dimarts")) oday = "1";
					if (((String)comboBox_4.getSelectedItem()).equals("Dimecres")) oday = "2";
					if (((String)comboBox_4.getSelectedItem()).equals("Dijous")) oday = "3";
					if (((String)comboBox_4.getSelectedItem()).equals("Divendres")) oday = "4";
					int hour = Integer.parseInt((String)comboBox_2.getSelectedItem());
					hour = hour - 8;
					ohour = String.valueOf(hour);
					oaula = (String)comboBox_3.getSelectedItem();
					if (((String)comboBox_5.getSelectedItem()).equals("Dilluns")) nday = "0";
					if (((String)comboBox_5.getSelectedItem()).equals("Dimarts")) nday = "1";
					if (((String)comboBox_5.getSelectedItem()).equals("Dimecres")) nday = "2";
					if (((String)comboBox_5.getSelectedItem()).equals("Dijous")) nday = "3";
					if (((String)comboBox_5.getSelectedItem()).equals("Divendres")) nday = "4";
					hour = Integer.parseInt((String)comboBox_6.getSelectedItem());
					hour = hour - 8;
					nhour = String.valueOf(hour);
					naula = (String)comboBox_7.getSelectedItem();
					Boolean b = false;;
					try {
						b = CtrlP.modificarHorari(oday, ohour, oaula, nday, nhour, naula);
					} catch (NumberFormatException | HorariExceptions e1) {
						e1.printStackTrace();
					}
					if (!b) {
						ErrorNoEsPotModificar enepm = ErrorNoEsPotModificar.getInstance();
						enepm.setVisible(true);
					} else {
						refreshPanel();			
					}
				}	
			}
		});
		btnModifica.setBounds(693, 756, 96, 25);
		contentPane.add(btnModifica);
	}
		
	public void setVisible(Boolean visible) {
		contentPane.setVisible(visible);
	}
	
	
	public void refreshPanel() {
		contentPane.removeAll();
		inicialitzaPanel();
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	private void recalculateTamany(Integer nfiles) {
		if (nfiles >2)  {
			nfiles = nfiles-2;
			augmentTamanyCell = nfiles*20;
		}
		else return;
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