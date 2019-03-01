package presentacio;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErrorNombreEsperat extends JDialog {
	private static ErrorNombreEsperat etse;
	private final JPanel contentPanel;

	/**
	 * Launch the application.
	 */
	
	public static ErrorNombreEsperat getInstance() {
		if (etse == null) etse = new ErrorNombreEsperat();
		return etse;		
	}
	public static void newErrorAssigExists() {
		try {
			ErrorNombreEsperat dialog = new ErrorNombreEsperat();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ErrorNombreEsperat() {
		setBounds(100, 100, 285, 162);
		getContentPane().setLayout(new BorderLayout());
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblGrupJaExistent = new JLabel("El camp ha de ser un nombre enter");
			lblGrupJaExistent.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblGrupJaExistent.setBounds(12, 26, 243, 28);
			contentPanel.add(lblGrupJaExistent);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Grup g = Grup.getInstance();
						setVisible(false);
						g.setVisible(true);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
