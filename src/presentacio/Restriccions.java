package presentacio;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class Restriccions extends JDialog {
	private static Restriccions etse;
	private ControladorPresentacio CtrlP = new ControladorPresentacio();
	private JPanel contentPanel;

	/**
	 * Launch the application.
	 */
	
	public static Restriccions getInstance() {
		if (etse == null) etse = new Restriccions();
		return etse;		
	}
	public static void newErrorTotsElsCampsPerModificar() {
		try {
			Restriccions dialog = new Restriccions();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Restriccions() {
		inicialitzaPanel();
		
	}
	
	private void inicialitzaPanel() {
		setBounds(100, 100, 516, 393);
		getContentPane().setLayout(new BorderLayout());
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblGrupJaExistent = new JLabel("Restriccions");
			lblGrupJaExistent.setHorizontalAlignment(SwingConstants.CENTER);
			lblGrupJaExistent.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblGrupJaExistent.setBounds(10, 0, 96, 33);
			contentPanel.add(lblGrupJaExistent);
		}
		{
			JCheckBox chckbxUnaAulaHa = new JCheckBox("Una aula ha d'estar buida per fer-hi una sessi\u00F3");
			chckbxUnaAulaHa.setName("RestriccioAulaBuida");
			chckbxUnaAulaHa.setSelected(CtrlP.getInfoRestriccio("RestriccioAulaBuida"));
			chckbxUnaAulaHa.setBounds(20, 40, 378, 23);
			contentPanel.add(chckbxUnaAulaHa);
		}
		{
			JCheckBox chckbxLaulaTSuficient = new JCheckBox("L'aula t\u00E9 suficient capacitat per encabir-hi el tamany del grup");
			chckbxLaulaTSuficient.setName("RestriccioCapacitat");
			chckbxLaulaTSuficient.setSelected(CtrlP.getInfoRestriccio("RestriccioCapacitat"));
			chckbxLaulaTSuficient.setBounds(20, 66, 378, 23);
			contentPanel.add(chckbxLaulaTSuficient);
		}
		{
			JCheckBox chckbxNoSolapamentDe = new JCheckBox("No solapament de dues assignatures que s\u00F3n corequisits");
			chckbxNoSolapamentDe.setName("RestriccioCorequisit");
			chckbxNoSolapamentDe.setSelected(CtrlP.getInfoRestriccio("RestriccioCorequisit"));
			chckbxNoSolapamentDe.setBounds(20, 92, 378, 23);
			contentPanel.add(chckbxNoSolapamentDe);
		}
		{
			JCheckBox chckbxLaSessiEst = new JCheckBox("No es realitzen sessions a les hores marcades com a \"Bloquejades\"");
			chckbxLaSessiEst.setName("RestriccioHoresImpossibles");
			chckbxLaSessiEst.setSelected(CtrlP.getInfoRestriccio("RestriccioHoresImpossibles"));
			chckbxLaSessiEst.setBounds(20, 118, 378, 23);
			contentPanel.add(chckbxLaSessiEst);
		}
		{
			JCheckBox chckbxLesSessionsDe = new JCheckBox("Les sessions de laboratori d'una assignatura es realitzen despr\u00E9s d'alguna de teoria");
			chckbxLesSessionsDe.setName("RestriccioLaboratoriPostTeoria");
			chckbxLesSessionsDe.setSelected(CtrlP.getInfoRestriccio("RestriccioLaboratoriPostTeoria"));
			chckbxLesSessionsDe.setBounds(20, 144, 443, 23);
			contentPanel.add(chckbxLesSessionsDe);
		}
		{
			JCheckBox chckbxUnGrupDuna = new JCheckBox("Un grup d'una assignatura nom\u00E9s fa sessions a la seva franja hor\u00E0ria");
			chckbxUnGrupDuna.setName("RestriccioMatiTarda");
			chckbxUnGrupDuna.setSelected(CtrlP.getInfoRestriccio("RestriccioMatiTarda"));
			chckbxUnGrupDuna.setBounds(20, 170, 443, 23);
			contentPanel.add(chckbxUnGrupDuna);
		}
		{
			JCheckBox chckbxUnGrupRealitzar = new JCheckBox("Un grup d'una assignatura realitzar\u00E0 m\u00E0xim 2 hores del mateix tipus de sessi\u00F3 en un dia");
			chckbxUnGrupRealitzar.setName("RestriccioMaximHoresDiaMateixAssigTipus");
			chckbxUnGrupRealitzar.setSelected(CtrlP.getInfoRestriccio("RestriccioMaximHoresDiaMateixAssigTipus"));
			chckbxUnGrupRealitzar.setBounds(20, 196, 443, 23);
			contentPanel.add(chckbxUnGrupRealitzar);
		}
		{
			JCheckBox chckbxUnGrupRealitzar_1 = new JCheckBox("Un grup realitzar\u00E0 m\u00E0xim 4 hores de la mateixa assignatura en un dia");
			chckbxUnGrupRealitzar_1.setName("RestriccioMaximSessionsAssigDia");
			chckbxUnGrupRealitzar_1.setSelected(CtrlP.getInfoRestriccio("RestriccioMaximSessionsAssigDia"));
			chckbxUnGrupRealitzar_1.setBounds(20, 222, 443, 23);
			contentPanel.add(chckbxUnGrupRealitzar_1);
		}
		{
			JCheckBox chckbxUnGrupRealitzar_2 = new JCheckBox("Un grup realitzar\u00E0 m\u00E0xim 2 hores seguides de la mateixa assignatura");
			chckbxUnGrupRealitzar_2.setName("RestriccioMaximSessionsAssigSeguides");
			chckbxUnGrupRealitzar_2.setSelected(CtrlP.getInfoRestriccio("RestriccioMaximSessionsAssigSeguides"));
			chckbxUnGrupRealitzar_2.setBounds(20, 248, 443, 23);
			contentPanel.add(chckbxUnGrupRealitzar_2);
		}
		{
			JCheckBox chckbxNoEsPot = new JCheckBox("No es pot solapar el mateix grup de 2 assignatures del mateix nivell");
			chckbxNoEsPot.setName("RestriccioNivell");
			chckbxNoEsPot.setSelected(CtrlP.getInfoRestriccio("RestriccioNivell"));
			chckbxNoEsPot.setBounds(20, 274, 443, 23);
			contentPanel.add(chckbxNoEsPot);
		}
		{
			JCheckBox chckbxElTipusDe = new JCheckBox("El tipus de l'aula concorda amb el tipus de sessi\u00F3");
			chckbxElTipusDe.setName("RestriccioTipusAulaPerTipusSessio");
			chckbxElTipusDe.setSelected(CtrlP.getInfoRestriccio("RestriccioTipusAulaPerTipusSessio"));
			chckbxElTipusDe.setBounds(20, 300, 443, 23);
			contentPanel.add(chckbxElTipusDe);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						setVisible(false);
						Component[] components = contentPanel.getComponents();

					    for (Component component : components) {
					        if (component instanceof JCheckBox) {
					            if (((JCheckBox) component).isSelected()) {
					            	CtrlP.toogleRestriccio(((JCheckBox) component).getName(), true);
					            }
					            else {
					            	CtrlP.toogleRestriccio(((JCheckBox) component).getName(), false);
					            }
					        }
					        ;
					    }
						
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
	public void refreshPanel() {
		contentPanel.removeAll();
		inicialitzaPanel();
		contentPanel.revalidate();
		contentPanel.repaint();
	}
}
