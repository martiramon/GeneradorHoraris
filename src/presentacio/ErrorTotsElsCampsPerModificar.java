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
import javax.swing.SwingConstants;

public class ErrorTotsElsCampsPerModificar extends JDialog {
	private static ErrorTotsElsCampsPerModificar etse;
	private final JPanel contentPanel;

	/**
	 * Launch the application.
	 */
	
	public static ErrorTotsElsCampsPerModificar getInstance() {
		if (etse == null) etse = new ErrorTotsElsCampsPerModificar();
		return etse;		
	}
	public static void newErrorTotsElsCampsPerModificar() {
		try {
			ErrorTotsElsCampsPerModificar dialog = new ErrorTotsElsCampsPerModificar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ErrorTotsElsCampsPerModificar() {
		setBounds(100, 100, 231, 162);
		getContentPane().setLayout(new BorderLayout());
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblGrupJaExistent = new JLabel("<html><body>Per a modificar una<br>sessió fa falta omplir <br>      tots els camps</body></html>");
			lblGrupJaExistent.setHorizontalAlignment(SwingConstants.CENTER);
			lblGrupJaExistent.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblGrupJaExistent.setBounds(10, 11, 185, 56);
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
						setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
