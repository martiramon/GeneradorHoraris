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

public class ErrorFaltenCamps extends JDialog {
	private static ErrorFaltenCamps etse;
	private final JPanel contentPanel;

	/**
	 * Launch the application.
	 */
	
	public static ErrorFaltenCamps getInstance() {
		if (etse == null) etse = new ErrorFaltenCamps();
		return etse;		
	}
	public static void newErrorAssigExists() {
		try {
			ErrorFaltenCamps dialog = new ErrorFaltenCamps();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ErrorFaltenCamps() {
		setBounds(100, 100, 232, 164);
		getContentPane().setLayout(new BorderLayout());
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblGrupJaExistent = new JLabel("Falten camps per omplir!");
			lblGrupJaExistent.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblGrupJaExistent.setBounds(24, 26, 178, 31);
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
