/*
 * 
 */
package br.com.infor.telas;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.infor.dal.ModuloConexao;

// TODO: Auto-generated Javadoc
/**
 * Classe responsavel pela tela de usuarios.
 *
 * @author Ricardo Rosendo
 * @version 1.1 The Class TelaUsuario.
 */
public class TelaUsuario extends JInternalFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The txt usu id. */
	private JTextField txtUsuId;

	/** The txt usu nome. */
	private JTextField txtUsuNome;

	/** The txt usu fone. */
	private JTextField txtUsuFone;

	/** The txt usu login. */
	private JTextField txtUsuLogin;

	/** The txt usu senha. */
	private JTextField txtUsuSenha;

	/** The con. */
	@SuppressWarnings("unused")
	private Connection con;

	/** The pst. */
	private PreparedStatement pst = null;

	/** The rs. */
	private ResultSet rs = null;

	/** The mc. */
	private ModuloConexao mc = new ModuloConexao();

	/** The btn usu create. */
	public static JButton btnUsuCreate;

	/** The btn usu update. */
	public static JButton btnUsuUpdate;

	/** The btn usu delete. */
	public static JButton btnUsuDelete;

	/** The btn usu read. */
	public static JButton btnUsuRead;

	/** The cbo usu perfil. */
	@SuppressWarnings("rawtypes")
	public static JComboBox cboUsuPerfil;

	/** The consultar chamada. */
	public boolean consultarChamada = false;

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUsuario frame = new TelaUsuario();
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

	private void consultar() {
		String read = "select * from tbusuarios where iduser = ?";
		try (Connection con = mc.conectar()) {
			pst = con.prepareStatement(read);
			pst.setString(1, txtUsuId.getText());
			rs = pst.executeQuery();
			consultarChamada = true;
			if (rs.next()) {
				txtUsuNome.setText(rs.getString(2));
				txtUsuFone.setText(rs.getString(3));
				txtUsuLogin.setText(rs.getString(4));
				txtUsuSenha.setText(rs.getString(5));
				cboUsuPerfil.setSelectedItem(rs.getString(6));
			} else {
				JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
				limparCampos(false);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	/**
	 * Cadastrar.
	 */
	private void cadastrar() {
		if (!validarCampos()) {
			return;
		} else {
			String insert = "insert into tbusuarios(iduser, usuario, fone, login, senha, perfil) values(?, ?, ?, ?, ?, ?)";
			try (Connection con = mc.conectar()) {
				pst = con.prepareStatement(insert);
				int valueId = Integer.parseInt(txtUsuId.getText());
				pst.setInt(1, valueId);
				pst.setString(2, txtUsuNome.getText());
				pst.setString(3, txtUsuFone.getText());
				pst.setString(4, txtUsuLogin.getText());
				pst.setString(5, txtUsuSenha.getText());
				pst.setString(6, cboUsuPerfil.getSelectedItem().toString());
				int rowsAffectc = pst.executeUpdate();
				if (rowsAffectc > 0) {
					JOptionPane.showMessageDialog(null, "Usuário adicionado com Sucesso !");
					limparCampos(true);
				}

			} catch (NumberFormatException nf) {
				JOptionPane.showMessageDialog(null, nf);
			} catch (SQLException sqle) {
				JOptionPane.showMessageDialog(null, sqle);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}

	/**
	 * Atualizar.
	 */
	private void atualizar() {
		if (!validarCampos()) {
			return;
		} else {
			String update = "update tbusuarios set usuario=?,fone=?,login=?,senha=? ,perfil=? where iduser = ?";
			try (Connection con = mc.conectar()) {
				pst = con.prepareStatement(update);
				pst.setString(1, txtUsuNome.getText());
				pst.setString(2, txtUsuFone.getText());
				pst.setString(3, txtUsuLogin.getText());
				pst.setString(4, txtUsuSenha.getText());
				pst.setString(5, cboUsuPerfil.getSelectedItem().toString());
				int valueId = Integer.parseInt(txtUsuId.getText());
				pst.setInt(6, valueId);
				int rowsUpdate = pst.executeUpdate();
				if (rowsUpdate > 0) {
					JOptionPane.showMessageDialog(null, "Dados alterados com sucesso.");
					limparCampos(true);
				}
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, nfe);
			} catch (SQLException sqle) {
				JOptionPane.showMessageDialog(null, sqle);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}

	/**
	 * Deletar.
	 */
	private void deletar() {
		int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este usuário", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirmar == JOptionPane.YES_OPTION) {
			String delete = "delete from tbusuarios where iduser = ?";
			try (Connection con = mc.conectar()) {
				pst = con.prepareStatement(delete);
				pst.setString(1, txtUsuId.getText());
				int confirmDelete = pst.executeUpdate();
				if (confirmDelete > 0) {
					JOptionPane.showMessageDialog(null, "Usuário deletado com sucesso !");
					limparCampos(true);
				}
			} catch (Exception e) {
				JOptionPane.showInternalMessageDialog(null, e);
			}
		}
	}

	/**
	 * Limpar campos.
	 *
	 * @param limparID the limpar ID
	 */
	private void limparCampos(boolean limparID) {
		if (limparID) {
			txtUsuId.setText(null);
		}
		txtUsuNome.setText(null);
		txtUsuFone.setText(null);
		txtUsuLogin.setText(null);
		txtUsuSenha.setText(null);
		cboUsuPerfil.setSelectedItem("Selecione o Perfil".toString());
		consultarChamada = false;
	}

	/**
	 * Validar campos.
	 *
	 * @return true, if successful
	 */
	private boolean validarCampos() {
		if (txtUsuId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Favor preencher o campo obrigatório ID");
			txtUsuId.requestFocus();
			return false;
		} else if (txtUsuNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Favor preencher o campo obrigatório Nome");
			txtUsuNome.requestFocus();
			return false;
		} else if (txtUsuLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Favor preencher o campo obrigatório Login");
			txtUsuLogin.requestFocus();
			return false;
		} else if (txtUsuSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Favor preencher o campo obrigatório Senha");
			txtUsuSenha.requestFocus();
			return false;
		} else {
			Object selectedItem = cboUsuPerfil.getSelectedItem();
			if (selectedItem == null || selectedItem.toString().trim().equals("Selecione o Perfil")) {
				JOptionPane.showMessageDialog(null, "Favor selecione um perfil");
				cboUsuPerfil.requestFocus();
				return false;
			}
		}
		return true;
	}

	/**
	 * Instantiates a new tela usuario.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public TelaUsuario() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Usuários");
		setBounds(0, 0, 640, 480);
		getContentPane().setLayout(null);

		JLabel lblId = new JLabel("*Id:");
		lblId.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblId.setBounds(24, 49, 28, 14);
		getContentPane().add(lblId);

		JLabel lblNome = new JLabel("*Nome:");
		lblNome.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblNome.setBounds(24, 100, 56, 14);
		getContentPane().add(lblNome);

		JLabel lblFone = new JLabel("Fone:");
		lblFone.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblFone.setBounds(31, 148, 49, 14);
		getContentPane().add(lblFone);

		JLabel lblLogin = new JLabel("*Login:");
		lblLogin.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblLogin.setBounds(24, 193, 56, 14);
		getContentPane().add(lblLogin);

		JLabel lblSenha = new JLabel("*Senha: ");
		lblSenha.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblSenha.setBounds(24, 238, 56, 14);
		getContentPane().add(lblSenha);

		JLabel lblPerfil = new JLabel("*Perfil:");
		lblPerfil.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblPerfil.setBounds(24, 288, 56, 14);
		getContentPane().add(lblPerfil);

		txtUsuId = new JTextField();
		txtUsuId.setBounds(58, 46, 64, 20);
		getContentPane().add(txtUsuId);
		txtUsuId.setColumns(10);

		txtUsuNome = new JTextField();
		txtUsuNome.setBounds(102, 97, 262, 20);
		getContentPane().add(txtUsuNome);
		txtUsuNome.setColumns(10);

		txtUsuFone = new JTextField();
		txtUsuFone.setBounds(102, 145, 138, 20);
		getContentPane().add(txtUsuFone);
		txtUsuFone.setColumns(10);

		txtUsuLogin = new JTextField();
		txtUsuLogin.setBounds(102, 190, 138, 20);
		getContentPane().add(txtUsuLogin);
		txtUsuLogin.setColumns(10);

		txtUsuSenha = new JTextField();
		txtUsuSenha.setBounds(102, 235, 138, 20);
		getContentPane().add(txtUsuSenha);
		txtUsuSenha.setColumns(10);

		cboUsuPerfil = new JComboBox();
		cboUsuPerfil.setModel(new DefaultComboBoxModel(new String[] { "Selecione o Perfil", "admin", "user" }));
		cboUsuPerfil.setBounds(102, 284, 138, 22);
		getContentPane().add(cboUsuPerfil);

		btnUsuCreate = new JButton("");
		btnUsuCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cadastrar();
			}
		});
		btnUsuCreate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuCreate.setToolTipText("Adicionar Usuário");
		btnUsuCreate.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/infor/icones/Create.png")));
		btnUsuCreate.setPreferredSize(new Dimension(80, 80));
		btnUsuCreate.setSize(new Dimension(64, 64));
		btnUsuCreate.setBounds(58, 344, 80, 80);
		getContentPane().add(btnUsuCreate);

		btnUsuUpdate = new JButton("");
		btnUsuUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();
			}
		});
		btnUsuUpdate.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/infor/icones/Update.png")));
		btnUsuUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuUpdate.setToolTipText("Atualizar Usuário");
		btnUsuUpdate.setPreferredSize(new Dimension(80, 80));
		btnUsuUpdate.setSize(new Dimension(64, 64));
		btnUsuUpdate.setBounds(197, 344, 80, 80);
		getContentPane().add(btnUsuUpdate);

		btnUsuDelete = new JButton("");
		btnUsuDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletar();
			}
		});
		btnUsuDelete.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/infor/icones/Delete.png")));
		btnUsuDelete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuDelete.setToolTipText("Excluir Usuário");
		btnUsuDelete.setPreferredSize(new Dimension(80, 80));
		btnUsuDelete.setSize(new Dimension(64, 64));
		btnUsuDelete.setBounds(343, 344, 80, 80);
		getContentPane().add(btnUsuDelete);

		btnUsuRead = new JButton("");
		btnUsuRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultar();
			}
		});
		btnUsuRead.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/infor/icones/Read.png")));
		btnUsuRead.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuRead.setToolTipText("Buscar Usuário");
		btnUsuRead.setPreferredSize(new Dimension(80, 80));
		btnUsuRead.setSize(new Dimension(64, 64));
		btnUsuRead.setBounds(487, 344, 80, 80);
		getContentPane().add(btnUsuRead);

		JLabel lblCampoObrigatorio = new JLabel("*Campos Obrigatorios ");
		lblCampoObrigatorio.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblCampoObrigatorio.setBounds(433, 46, 156, 20);
		getContentPane().add(lblCampoObrigatorio);

	}
}
