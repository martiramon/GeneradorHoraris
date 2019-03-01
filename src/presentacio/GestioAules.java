package presentacio;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;


public class GestioAules extends JFrame {
	private static GestioAules ga;
	private ControladorPresentacio CtrlP = new ControladorPresentacio();
	private JPanel contentPane;
	
	
	public static GestioAules getInstance() {
		if (ga == null) ga = new GestioAules();
		return ga;
	}
	/**
	 * Launch the application.
	 */
	public static void newGestioAules() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestioAules frame = GestioAules.getInstance();
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
	public GestioAules() {
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
		Vector<String> aules = CtrlP.getAllAulesIds();
		for (int i=0; i<aules.size();i++)
		{
			((DefaultListModel)list.getModel()).addElement(aules.get(i));
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
				String aulaS = list.getSelectedValue();
				String aula = CtrlP.getAulaByName(aulaS);
				JsonObject jelement = new JsonParser().parse(aula).getAsJsonObject();
				String id = jelement.get("id").getAsString();
				String capacitat = jelement.get("capacitat").getAsString();
				String tipus = jelement.get("tipusA").getAsString();
				tipus = CtrlP.getNomTipusAula(tipus);
				Aula a = Aula.getInstance();
				a.botodisparador("modif");
				a.refreshPanel();
				a.setDades(id, capacitat, tipus);
				setVisible(false);
				a.setVisible(true);
			}
		});
		btnModificar.setBounds(451, 108, 152, 33);
		contentPane.add(btnModificar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String aulaS = list.getSelectedValue();
				String aula = CtrlP.getAulaByName(aulaS);
				JsonObject jelement = new JsonParser().parse(aula).getAsJsonObject();
				String id = jelement.get("id").getAsString();
				String capacitat = jelement.get("capacitat").getAsString();
				String tipus = jelement.get("tipusA").getAsString();
				tipus = CtrlP.getNomTipusAula(tipus);
				Aula a = Aula.getInstance();
				a.botodisparador("cons");
				a.refreshPanel();
				a.setDades(id, capacitat, tipus);
				setVisible(false);
				a.setVisible(true);
			}
		});
		btnConsultar.setBounds(451, 62, 152, 33);
		contentPane.add(btnConsultar);
		
		JLabel lblGestiAules = new JLabel("Gesti\u00F3 Aules");
		lblGestiAules.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGestiAules.setBounds(10, 11, 289, 40);
		contentPane.add(lblGestiAules);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Aula a = Aula.getInstance();
				a.botodisparador("crear");
				a.refreshPanel();
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
			String aulaS = list.getSelectedValue();
			CtrlP.eliminarAula(aulaS);
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
