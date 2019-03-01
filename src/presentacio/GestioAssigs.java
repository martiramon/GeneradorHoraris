package presentacio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import FONTS.esaus.ConjuntGrupAssig;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class GestioAssigs extends JFrame {
	private static GestioAssigs ga;
	private ControladorPresentacio CtrlP = new ControladorPresentacio();
	private JPanel contentPane;
	
	
	public static GestioAssigs getInstance() {
		if (ga == null) ga = new GestioAssigs();
		return ga;
	}
	/**
	 * Launch the application.
	 */
	public static void newGestioAssigs() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestioAssigs frame = GestioAssigs.getInstance();
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
	public GestioAssigs() {
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
		
		
		JList<String> list = new JList<String>(new DefaultListModel<String>());
		//list.setBounds(75, 84, 200, 200);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		Vector<String> assigs = CtrlP.getAllAssigName();
		for (int i=0; i<assigs.size();i++)
		{
			((DefaultListModel)list.getModel()).addElement(assigs.get(i));
		    //model.add(i, assigs.get(i));
		}
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setLocation(124, 68);
		scrollPane.setSize(242, 214);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //JOptionPane.showMessageDialog(null, scrollPane);
		contentPane.add(scrollPane);
		JButton btnModificar= new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nomS = list.getSelectedValue();
				String assig = CtrlP.getAssigByName(nomS);
				JsonObject jelement = new JsonParser().parse(assig).getAsJsonObject();
				String nom = jelement.get("nom").getAsString();
				String acronim = jelement.get("acronim").getAsString();
				String nivell = jelement.get("nivell").getAsString();
				//JsonArray grup[] = jelement.get("grups").getAsJsonArray()();
				Assignatura a = Assignatura.getInstance();
				a.botodisparador("modif");
				a.setDades(nom, acronim, nivell);				//a.revalidate();
				//a.repaint();
				
				setVisible(false);
				a.setVisible(true);
			}
		});
		btnModificar.setBounds(451, 108, 152, 33);
		contentPane.add(btnModificar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nomS = list.getSelectedValue();
				String assig = CtrlP.getAssigByName(nomS);
				JsonObject jelement = new JsonParser().parse(assig).getAsJsonObject();
				String nom = jelement.get("nom").getAsString();
				String acronim = jelement.get("acronim").getAsString();
				String nivell = jelement.get("nivell").getAsString();
				//JsonArray grup[] = jelement.get("grups").getAsJsonArray()();
				Assignatura a = Assignatura.getInstance();
				a.botodisparador("cons");
				a.setDades(nom, acronim, nivell);
				//a.revalidate();
				//a.repaint();				
				setVisible(false);
				a.setVisible(true);
			}
		});
		btnConsultar.setBounds(451, 62, 152, 33);
		contentPane.add(btnConsultar);
		
		JLabel lblGestiAssignatures = new JLabel("Gesti\u00F3 Assignatures");
		lblGestiAssignatures.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGestiAssignatures.setBounds(10, 11, 289, 40);
		contentPane.add(lblGestiAssignatures);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Assignatura a = Assignatura.getInstance();
				a.botodisparador("crear");
				a.setDades("", "", "");
				a.setVisible(true);
			}
		});
		btnCrear.setBounds(451, 292, 152, 33);
	
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

		
		JButton button_2 = new JButton("Esborrar");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nomS = list.getSelectedValue();
				CtrlP.eliminarAssignatura(nomS);
				refreshPanel();
			}
		});
		button_2.setBounds(451, 154, 152, 33);
		contentPane.add(button_2);
		
		
		
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
