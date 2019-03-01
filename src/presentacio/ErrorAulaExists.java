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

public class ErrorAulaExists extends JDialog {
	private static ErrorAulaExists eaue;
	private final JPanel contentPanel;

	/**
	 * Launch the application.
	 */
	
	public static ErrorAulaExists getInstance() {
		if (eaue == null) eaue = new ErrorAulaExists();
		return eaue;		
	}
	public static void newErrorAssigExists() {
		try {
			ErrorAulaExists dialog = new ErrorAulaExists();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ErrorAulaExists() {
		setBounds(100, 100, 231, 162);
		getContentPane().setLayout(new BorderLayout());
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblGrupJaExistent = new JLabel("Aula ja existent");
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
