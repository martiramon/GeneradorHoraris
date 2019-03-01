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

public class ErrorHorariNoPossible extends JDialog {
	private static ErrorHorariNoPossible etse;
	private final JPanel contentPanel;

	/**
	 * Launch the application.
	 */
	
	public static ErrorHorariNoPossible getInstance() {
		if (etse == null) etse = new ErrorHorariNoPossible();
		return etse;		
	}
	public static void newErrorFaltenClassesHorari() {
		try {
			ErrorHorariNoPossible dialog = new ErrorHorariNoPossible();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ErrorHorariNoPossible() {
		setBounds(100, 100, 231, 162);
		getContentPane().setLayout(new BorderLayout());
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblGrupJaExistent = new JLabel("<html><body>No és possible<br>generar l'horari</body></html>");
			lblGrupJaExistent.setHorizontalAlignment(SwingConstants.CENTER);
			lblGrupJaExistent.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblGrupJaExistent.setBounds(12, 0, 185, 56);
			contentPanel.add(lblGrupJaExistent);
		}
		
		JLabel lblGrupJaExistent2 = new JLabel("<html><body>Prova a desactivar alguna restricció</body></html>");
		lblGrupJaExistent2.setBounds(12, 64, 189, 15);
		contentPanel.add(lblGrupJaExistent2);
		lblGrupJaExistent2.setHorizontalAlignment(SwingConstants.CENTER);
		lblGrupJaExistent2.setFont(new Font("Tahoma", Font.PLAIN, 12));
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
