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

public class ErrorGrupExists extends JDialog {
	private static ErrorGrupExists eae;
	private final JPanel contentPanel;

	/**
	 * Launch the application.
	 */
	
	public static ErrorGrupExists getInstance() {
		if (eae == null) eae = new ErrorGrupExists();
		return eae;		
	}
	public static void newErrorAssigExists() {
		try {
			ErrorGrupExists dialog = new ErrorGrupExists();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ErrorGrupExists() {
		setBounds(100, 100, 231, 162);
		getContentPane().setLayout(new BorderLayout());
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblGrupJaExistent = new JLabel("Grup ja existent");
			lblGrupJaExistent.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblGrupJaExistent.setBounds(33, 26, 156, 28);
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
						Assignatura a = Assignatura.getInstance();
						setVisible(false);
						a.setVisible(true);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
