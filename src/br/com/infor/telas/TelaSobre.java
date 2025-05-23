/*
 * 
 */
package br.com.infor.telas;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

// TODO: Auto-generated Javadoc
/**
 * Classe responsavel pela tela sobre do sistema.
 *
 * @author Ricardo Rosendo
 * @version 1.1 The Class TelaSobre.
 */
public class TelaSobre extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The content pane. */
	private JPanel contentPane;

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSobre frame = new TelaSobre();
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
	public TelaSobre() {
		setTitle("Sobre");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 380, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		setResizable(false);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Sistema para controle de Ordem de Serviços");
		lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblNewLabel.setBounds(20, 41, 304, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Desenvolvido - Ricardo Rosendo");
		lblNewLabel_1.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(20, 66, 232, 24);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Sob a Licensa MIT");
		lblNewLabel_2.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblNewLabel_2.setBounds(20, 101, 139, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(TelaSobre.class.getResource("/br/com/infor/icones/mit.png")));
		lblNewLabel_3.setBounds(260, 101, 64, 64);
		contentPane.add(lblNewLabel_3);
	}
}
