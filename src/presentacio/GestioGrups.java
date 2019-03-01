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

public class GestioGrups extends JFrame {
	private static GestioGrups gg;
	private ControladorPresentacio CtrlP = new ControladorPresentacio();
	private JPanel contentPane;
	
	
	public static GestioGrups getInstance() {
		if (gg == null) gg = new GestioGrups();
		return gg;
	}
	/**
	 * Launch the application.
	 */
	public static void newGestioGrups() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestioGrups frame = GestioGrups.getInstance();
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
	public GestioGrups() {
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
		Vector<String> grups = CtrlP.getAllGroupIds();
		for (int i=0; i<grups.size();i++)
		{
			((DefaultListModel)list.getModel()).addElement(grups.get(i));
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
				String grupS = list.getSelectedValue();
				String grup = CtrlP.getGroupById(Integer.parseInt(grupS));
				JsonObject jelement = new JsonParser().parse(grup).getAsJsonObject();
				String num = jelement.get("num").getAsString();
				String capacitat = jelement.get("capacitat").getAsString();
				Grup g = Grup.getInstance();
				g.botodisparador("modif");
				g.setDades(num, capacitat);				

				setVisible(false);
				g.setVisible(true);
			}
		});
		btnModificar.setBounds(451, 108, 152, 33);
		contentPane.add(btnModificar);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String grupS = list.getSelectedValue();
				String grup = CtrlP.getGroupById(Integer.parseInt(grupS));
				JsonObject jelement = new JsonParser().parse(grup).getAsJsonObject();
				String num = jelement.get("num").getAsString();
				String capacitat = jelement.get("capacitat").getAsString();
				Grup g = Grup.getInstance();
				g.botodisparador("cons");
				g.setDades(num, capacitat);				
				setVisible(false);
				g.setVisible(true);
			}
		});
		btnConsultar.setBounds(451, 62, 152, 33);
		contentPane.add(btnConsultar);
		
		JLabel lblGestiAssignatures = new JLabel("Gesti\u00F3 Grups");
		lblGestiAssignatures.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblGestiAssignatures.setBounds(10, 11, 289, 40);
		contentPane.add(lblGestiAssignatures);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Grup g = Grup.getInstance();
				g.botodisparador("crear");
				g.setDades("", "");
				g.setVisible(true);
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
			String grupS = list.getSelectedValue();
			CtrlP.eliminarGrup(grupS);
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
