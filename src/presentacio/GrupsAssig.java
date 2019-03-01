package presentacio;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javafx.util.Pair;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JCheckBox;

public class GrupsAssig extends JDialog {
	
	private static GrupsAssig ga;
	private ControladorPresentacio CtrlP = new ControladorPresentacio();
	private JPanel contentPane;
	private String disparador;
	private String assignatura;
	private Vector<Pair<Boolean,String>> mati = new Vector<Pair<Boolean,String>>();
	
	public static GrupsAssig getInstance() {
		if (ga == null) ga = new GrupsAssig();
		return ga;
	}
	/**
	 * Launch the application.
	 */
	public static void newGrupsAssig() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GrupsAssig frame = GrupsAssig.getInstance();
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
	public GrupsAssig() {
		inicialitzaPanel();
	}
	
	void inicialitzaPanel() {
		setBounds(100, 100, 670, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CheckBoxList list = new CheckBoxList();
		Vector<String> grups = CtrlP.getAllGroupIds();
		Vector<String> grupsAssig = CtrlP.getAllGrups(assignatura);
		JCheckBox[] checkgrups = {};
		for (int i=0; i<grups.size(); i++)
		{
			JCheckBox temp = new JCheckBox();		        
		        temp.addItemListener(new ItemListener() {
		            public void itemStateChanged(ItemEvent e) {
		            	MatiTarda mt = new MatiTarda();
		                mt.botodisparador(disparador);
		                mt.setAssig(assignatura);		                
		                mt.setGrup(temp.getText());
		                if (disparador=="modif") {
		                	mt.refreshPanel();
		                }
		                if (disparador == "crear")  mt.setVisible(true);
		               
		            }
		        });
			temp.setText(grups.get(i));
			if (grupsAssig.contains(grups.get(i))) temp.setSelected(true);
			else temp.setSelected(false);
			checkgrups = addElement(checkgrups, temp);
		}
		list.setListData(checkgrups);
		
			
			//((DefaultListModel)list.getModel()).addElement(grups.get(i));
		    //model.add(i, grups.get(i));
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setLocation(124, 68);
		scrollPane.setSize(242, 214);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      

        //JOptionPane.showMessageDialog(null, scrollPane);
		contentPane.add(scrollPane);
		
		JLabel lblGestiAssignatures = new JLabel("Grups");
		lblGestiAssignatures.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGestiAssignatures.setBounds(10, 11, 289, 40);
		contentPane.add(lblGestiAssignatures);
		
		JButton button = new JButton("Nou Grup");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Grup g = Grup.getInstance();
				g.botodisparador("grupAssig");
				g.setVisible(true);
			}
		});
		button.setBounds(10, 302, 152, 33);
		contentPane.add(button);
		
		JButton btnCrea = new JButton("Accepta");
		btnCrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Assignatura a = Assignatura.getInstance();
				Integer capacitat;				
				Boolean t;
				Vector<Pair<String,String>> grups = new Vector<Pair<String,String>>();
				for (int k = 0; k < list.getModel().getSize(); k++) {
					JCheckBox jc = (JCheckBox) list.getModel().getElementAt(k);
					if (jc.isSelected()) {
						Pair <String,String> temp = new Pair<String,String>(assignatura,jc.getText());
						grups.add(temp);
					}
				}
				if (disparador == "modif" || disparador == "crear") a.setGrups(grups);
				for (int k = 0; k < list.getModel().getSize(); k++) {
					if (disparador == "modif") {
						JCheckBox jc = (JCheckBox) list.getModel().getElementAt(k);
						if (jc.isSelected()) {
							t = CtrlP.modificarGrupAssig(jc.getText(), assignatura, getMatiGrup(jc.getText()));
							a.setVisible(true);						
						}		
				} 
				if (disparador == "crear") {
					JCheckBox jc = (JCheckBox) list.getModel().getElementAt(k);
					if (jc.isSelected()) {
						t = CtrlP.crearGrupAssig(jc.getText(), assignatura, getMatiGrup(jc.getText()));
						a.setVisible(true);						
					}					
				}
				else if (disparador == "cons") {					
					a.setVisible(true);
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

	public void botodisparador(String disp) {
		disparador = disp;
		
	}
	static JCheckBox[] addElement(JCheckBox[] array, JCheckBox nou) {
	    array  = Arrays.copyOf(array, array.length + 1);
	    array[array.length - 1] = nou;
	    return array;
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
	public void setMati(Vector<Pair<Boolean,String>> mati) {
		this.mati = mati;
	}
	public Vector<Pair<Boolean,String>> getMati() {
		return this.mati;
	}
	
	private Boolean getMatiGrup(String grup) {
		for (Pair<Boolean, String> p : this.mati) {
			if (p.getValue().equals(grup)) return p.getKey();
		}
		return true;
	}
	
}