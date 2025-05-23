/*
 * 
 */
package br.com.infor.telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.infor.dal.ModuloConexao;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// TODO: Auto-generated Javadoc
/**
 * Classe responsavel pela tela de login do sistema.
 *
 * @author Ricardo Rosendo
 * @version 1.1 The Class TelaLogin.
 */
public class TelaLogin extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The content pane. */
	private JPanel contentPane;

	/** The txt usuario. */
	private JTextField txtUsuario;

	/** The txt senha. */
	private JPasswordField txtSenha;

	/** The md. */
	ModuloConexao md = new ModuloConexao();

	/** The con. */
	Connection con = null;

	/** The pst. */
	PreparedStatement pst = null;

	/** The rs. */
	ResultSet rs = null;

	/**
	 * Logar.
	 */
	public void logar() {
		if (!validarCampos()) {
			return;
		} else {
			String sql = "select * from tbusuarios where login = ? and senha = ?";
			try (Connection con = md.conectar()) {
				pst = con.prepareStatement(sql);
				pst.setString(1, txtUsuario.getText());
				String senha = new String(txtSenha.getPassword());
				pst.setString(2, senha);
				rs = pst.executeQuery();
				if (rs.next()) {
					String perfil = rs.getString(6);
					String usuario = rs.getString(2);
					if (perfil.equals("admin")) {
						TelaPrincipal principal = new TelaPrincipal();
						principal.setVisible(true);
						TelaPrincipal.menRel.setEnabled(true);
						TelaPrincipal.menCadUsu.setEnabled(true);
						TelaPrincipal.lblUsuario.setText("Usuário: " + usuario);
						TelaPrincipal.lblUsuario.setForeground(Color.red);
						TelaPrincipal.lblHora.setEnabled(true);
						TelaPrincipal.lblHora.setVisible(true);
						TelaPrincipal.lblUsuario.setVisible(true);
						TelaPrincipal.lblData.setVisible(true);
						this.dispose();
					} else {
						TelaPrincipal principal = new TelaPrincipal();
						principal.setVisible(true);
						TelaPrincipal.lblUsuario.setText("Usuario: " + usuario);
						TelaPrincipal.lblUsuario.setForeground(Color.blue);
						TelaPrincipal.lblHora.setEnabled(true);
						TelaPrincipal.lblHora.setVisible(true);
						TelaPrincipal.lblUsuario.setVisible(true);
						TelaPrincipal.lblData.setVisible(true);
						this.dispose();
					}

				} else {
					JOptionPane.showMessageDialog(null, "Usuario ou senhas invalidos!");
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}

	/**
	 * Validar campos.
	 *
	 * @return true, if successful
	 */
	private boolean validarCampos() {
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Favor, Inserir um usuário válido!");
			txtUsuario.requestFocus();
			return false;
		} else if (new String(txtSenha.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(null, "Favor , Inserir uma senha válida!");
			txtSenha.requestFocus();
			return false;
		}
		return true;
	}

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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

	public TelaLogin() {
		con = md.conectar();
		setResizable(false);
		setTitle("Login Sistema R");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 344, 194);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuário");
		lblUsuario.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblUsuario.setBounds(10, 29, 62, 14);
		contentPane.add(lblUsuario);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblSenha.setBounds(10, 60, 46, 14);
		contentPane.add(lblSenha);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(82, 26, 200, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logar();
			}
		});
		btnLogin.setBounds(195, 112, 106, 23);
		contentPane.add(btnLogin);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(82, 57, 200, 20);
		contentPane.add(txtSenha);

		JLabel lblStatus = new JLabel("");
		lblStatus.setBackground(new Color(255, 255, 255));
		lblStatus.setIcon(new ImageIcon(TelaLogin.class.getResource("/br/com/infor/icones/dboff.png")));
		lblStatus.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblStatus.setBounds(10, 99, 46, 46);
		contentPane.add(lblStatus);
		if (con != null) {
			lblStatus.setIcon(new ImageIcon(getClass().getResource("/br/com/infor/icones/dbon.png")));
		} else {
			lblStatus.setIcon(new ImageIcon(getClass().getResource("/br/com/infor/icones/dboff.png")));
		}
	}

}
