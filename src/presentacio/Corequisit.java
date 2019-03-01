package presentacio;

import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import javafx.util.Pair;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class Corequisit extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2676129754368992206L;
	private static Corequisit c;
	private ControladorPresentacio CtrlP = new ControladorPresentacio();
	private JPanel contentPane;
	private String disparador;
	private String assignatura = null;
	
	public static Corequisit getInstance() {
		if (c == null) c = new Corequisit();
		return c;
	}
	/**
	 * Launch the application.
	 */
	public static void newCorequisit() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Corequisit frame = Corequisit.getInstance();
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
	public Corequisit() {
		inicialitzaPanel();
	}
	
	void inicialitzaPanel() {
		setBounds(100, 100, 670, 385);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		CheckBoxList list = new CheckBoxList();
		
		Vector<String> assigs = CtrlP.getAllAssigName();
		Vector<String> coreqs = null;
		if (assignatura != null && (disparador=="modif" || disparador == "cons")) {
			coreqs = CtrlP.getAllCoreq(assignatura);
		}
		
		JCheckBox[] checkcoreqs = {};
		for (int i=0; i<assigs.size();i++)	{
			JCheckBox temp = new JCheckBox();
			if (!assigs.get(i).equals(assignatura) ) {
				temp.setText(assigs.get(i));
				if (coreqs != null) {
					if (coreqs.contains(assigs.get(i))) temp.setSelected(true);
					else temp.setSelected(false);
				}
				checkcoreqs = addElement(checkcoreqs, temp);
			}
		}
		list.setListData(checkcoreqs);
		
			
			//((DefaultListModel)list.getModel()).addElement(grups.get(i));
		    //model.add(i, grups.get(i));
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setLocation(124, 68);
		scrollPane.setSize(242, 214);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
      

        //JOptionPane.showMessageDialog(null, scrollPane);
		contentPane.add(scrollPane);
		
		JLabel lblGestiAssignatures = new JLabel("Corequisits");
		lblGestiAssignatures.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGestiAssignatures.setBounds(10, 11, 289, 40);
		contentPane.add(lblGestiAssignatures);
		
		JButton btnCrea = new JButton("Accepta");
		btnCrea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Assignatura a = Assignatura.getInstance();		
				Vector<Pair<String,Boolean>> newcoreqs = new Vector<Pair<String,Boolean>>();
				for (int k = 0; k < list.getModel().getSize(); k++) {
					JCheckBox jc = (JCheckBox) list.getModel().getElementAt(k);
					if (jc.isSelected()) {
						Pair<String,Boolean> temp = new Pair<String,Boolean>(jc.getText(),true);
						newcoreqs.add(temp);
					}
					else {
						Pair<String,Boolean> temp = new Pair<String,Boolean>(jc.getText(),true);
						newcoreqs.add(temp);
					}
				}
				if (disparador == "modif") {
					CtrlP.afegirCorequisits(assignatura, newcoreqs);
						a.setVisible(true);
					
				}
				else if (disparador == "crear") {
					a.setCoreqs(newcoreqs);
					a.setVisible(true);
					
				}
				else if (disparador == "cons") {
					a.setVisible(true);
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
	
}