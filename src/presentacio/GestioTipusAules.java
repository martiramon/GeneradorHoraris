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

public class GestioTipusAules extends JFrame {
	private static GestioTipusAules gtau;
	private ControladorPresentacio CtrlP = new ControladorPresentacio();
	private JPanel contentPane;
	
	
	public static GestioTipusAules getInstance() {
		if (gtau == null) gtau = new GestioTipusAules();
		return gtau;
	}
	/**
	 * Launch the application.
	 */
	public static void newGestioTipusAules() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestioTipusAules frame = GestioTipusAules.getInstance();
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
	public GestioTipusAules() {
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
		Vector<String> tipusau = CtrlP.getAllTipusAules();
		for (int i=0; i<tipusau.size();i++)
		{
			((DefaultListModel)list.getModel()).addElement(tipusau.get(i));
		    //model.add(i, assigs.get(i));
		}
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setLocation(124, 68);
		scrollPane.setSize(242, 214);

        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        //JOptionPane.showMessageDialog(null, scrollPane);
		contentPane.add(scrollPane);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nomS = list.getSelectedValue();
				String tipusaula = CtrlP.getTipusAulaByName(nomS);
				JsonObject jelement = new JsonParser().parse(tipusaula).getAsJsonObject();
				String nom = jelement.get("id").getAsString();				
				//JsonArray grup[] = jelement.get("grups").getAsJsonArray()();
				TipusAula tau = TipusAula.getInstance();
				tau.botodisparador("cons");
				tau.setDades(nom);				//a.revalidate();
				//a.repaint();
				
				setVisible(false);
				tau.setVisible(true);
			}
		});
		btnConsultar.setBounds(451, 62, 152, 33);
		contentPane.add(btnConsultar);
		
		JLabel lblGestiTipusAules = new JLabel("Gesti\u00F3 Tipus Aules");
		lblGestiTipusAules.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGestiTipusAules.setBounds(10, 11, 289, 40);
		contentPane.add(lblGestiTipusAules);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				TipusAula tau = TipusAula.getInstance();
				tau.botodisparador("crear");
				tau.setDades("");
				tau.setVisible(true);
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
				CtrlP.eliminarTipusAula(nomS);
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