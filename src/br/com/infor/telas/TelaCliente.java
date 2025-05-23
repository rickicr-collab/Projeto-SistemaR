/*
 * 
 */
package br.com.infor.telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.infor.dal.ModuloConexao;
import net.proteanit.sql.DbUtils;
import java.awt.Cursor;

// TODO: Auto-generated Javadoc
/**
 * Classe responsavel pela tela do clienrte no sistema.
 *
 * @author Ricardo Rosendo
 * @version 1.1
 * The Class TelaCliente.
 */
public class TelaCliente extends JInternalFrame {

	/** The con. */
	@SuppressWarnings("unused")
	private Connection con = null;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The txt cli nome. */
	private JTextField txtCliNome;

	/** The txt cli end. */
	private JTextField txtCliEnd;

	/** The txt cli fone. */
	private JTextField txtCliFone;

	/** The txt cli email. */
	private JTextField txtCliEmail;

	/** The btn adc cliente. */
	private static JButton btnAdcCliente;

	/** The btn edit cliente. */
	private static JButton btnEditCliente;

	/** The btn del cliente. */
	private static JButton btnDelCliente;

	/** The lbl new label 1. */
	private JLabel lblNewLabel_1;

	/** The txt cli pesquisar. */
	private JTextField txtCliPesquisar;

	/** The pst. */
	private PreparedStatement pst = null;

	/** The rs. */
	private ResultSet rs = null;

	/** The mc. */
	private ModuloConexao mc = new ModuloConexao();

	/** The tbl clientes. */
	private static JTable tblClientes;

	/** The txt cli id. */
	private JTextField txtCliId;

	/** The lbl cliente id. */
	private JLabel lblClienteId;

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente();
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
	public TelaCliente() {
		setTitle("Cadastro de Clientes");
		setIconifiable(true);
		setClosable(true);
		setMaximizable(true);
		setBounds(0, 0, 640, 480);
		getContentPane().setLayout(null);
		setResizable(false);

		JLabel lblClienteNome = new JLabel("*Nome");
		lblClienteNome.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblClienteNome.setBounds(27, 212, 61, 14);
		getContentPane().add(lblClienteNome);

		JLabel lblClienteEnd = new JLabel("Endereço");
		lblClienteEnd.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblClienteEnd.setBounds(27, 237, 71, 14);
		getContentPane().add(lblClienteEnd);

		JLabel lblClienteFone = new JLabel("*Telefone");
		lblClienteFone.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblClienteFone.setBounds(27, 271, 71, 14);
		getContentPane().add(lblClienteFone);

		JLabel lblClienteEmail = new JLabel("Email");
		lblClienteEmail.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblClienteEmail.setBounds(27, 299, 45, 14);
		getContentPane().add(lblClienteEmail);

		JLabel lblNewLabel_4 = new JLabel("*Campos Obrigatórios");
		lblNewLabel_4.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(467, 26, 147, 14);
		getContentPane().add(lblNewLabel_4);

		txtCliNome = new JTextField();
		txtCliNome.setBounds(98, 209, 401, 20);
		getContentPane().add(txtCliNome);
		txtCliNome.setColumns(10);

		txtCliEnd = new JTextField();
		txtCliEnd.setBounds(98, 237, 401, 20);
		getContentPane().add(txtCliEnd);
		txtCliEnd.setColumns(10);

		txtCliFone = new JTextField();
		txtCliFone.setBounds(98, 268, 147, 20);
		getContentPane().add(txtCliFone);
		txtCliFone.setColumns(10);

		txtCliEmail = new JTextField();
		txtCliEmail.setBounds(98, 296, 401, 20);
		getContentPane().add(txtCliEmail);
		txtCliEmail.setColumns(10);

		btnAdcCliente = new JButton("");
		btnAdcCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdcCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdcCliente.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/infor/icones/Create.png")));
		btnAdcCliente.setToolTipText("Adicionar Cliente");
		btnAdcCliente.setBounds(98, 338, 80, 80);
		getContentPane().add(btnAdcCliente);

		btnEditCliente = new JButton("");
		btnEditCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizar();
			}
		});
		btnEditCliente.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/infor/icones/Update.png")));
		btnEditCliente.setToolTipText("Editar Cliente");
		btnEditCliente.setBounds(258, 338, 80, 80);
		getContentPane().add(btnEditCliente);

		btnDelCliente = new JButton("");
		btnDelCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDelCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletar();
			}
		});
		btnDelCliente.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/infor/icones/Delete.png")));
		btnDelCliente.setToolTipText("Excluir Cliente");
		btnDelCliente.setBounds(419, 338, 80, 80);
		getContentPane().add(btnDelCliente);

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/infor/icones/perquisar.png")));
		lblNewLabel_1.setBounds(301, 11, 32, 32);
		getContentPane().add(lblNewLabel_1);

		txtCliPesquisar = new JTextField();
		txtCliPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisar_cliente();
			}
		});
		txtCliPesquisar.setBounds(10, 23, 281, 20);
		getContentPane().add(txtCliPesquisar);
		txtCliPesquisar.setColumns(10);

		tblClientes = new JTable();
		tblClientes.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		tblClientes.setFocusable(false);
		tblClientes.getTableHeader().setReorderingAllowed(false);
		tblClientes.setDefaultEditor(Object.class, null);
		tblClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setar_campos();
			}
		});
		tblClientes.setModel(
				new DefaultTableModel(new Object[][] {}, new String[] { "id", "nome", "endereco", "fone", "email" }));
		JScrollPane scrollPane = new JScrollPane(tblClientes);
		scrollPane.setBounds(10, 67, 604, 105);
		getContentPane().add(scrollPane);

		lblClienteId = new JLabel("Id");
		lblClienteId.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblClienteId.setBounds(26, 187, 46, 14);
		getContentPane().add(lblClienteId);

		txtCliId = new JTextField();
		txtCliId.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtCliId.setForeground(new Color(0, 0, 0));
		txtCliId.setEditable(false);
		txtCliId.setEnabled(false);
		txtCliId.setBounds(98, 184, 86, 20);
		getContentPane().add(txtCliId);
		txtCliId.setColumns(10);
		tblClientes.revalidate();
		tblClientes.repaint();
	}

	/**
	 * Adicionar.
	 */
	public void adicionar() {
		if (!validarCampos()) {
			return;
		} else {
			String adicionar = "insert into tbclientes (nomecli, enderecocli, fonecli, email) values (?,?,?,?)";
			try (Connection con = mc.conectar()) {
				pst = con.prepareStatement(adicionar);
				pst.setString(1, txtCliNome.getText());
				pst.setString(2, txtCliEnd.getText());
				pst.setString(3, txtCliFone.getText());
				pst.setString(4, txtCliEmail.getText());
				int adicionado = pst.executeUpdate();
				if (adicionado > 0) {
					JOptionPane.showMessageDialog(null, "Cliente adiciondo com sucesso!");
					limparCampos(false);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}

	/**
	 * Atualizar.
	 */
	public void atualizar() {
		if (!validarCampos()) {
			return;
		} else {
			String update = "update tbclientes set nomecli=?, enderecocli=?, fonecli=?, email=? where idcliente=?";
			try (Connection con = mc.conectar()) {
				pst = con.prepareStatement(update);
				pst.setString(1, txtCliNome.getText());
				pst.setString(2, txtCliEnd.getText());
				pst.setString(3, txtCliFone.getText());
				pst.setString(4, txtCliEmail.getText());
				pst.setString(5, txtCliId.getText());
				int atualizado = pst.executeUpdate();
				if (atualizado > 0) {
					JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
					limparCampos(true);
					btnAdcCliente.setEnabled(true);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}

	/**
	 * Deletar.
	 */
	public void deletar() {
		int confirmacao = JOptionPane.showConfirmDialog(null, "Deseja realmente excuir este cliente?", "atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirmacao == JOptionPane.YES_OPTION) {
			String delete = " delete from tbclientes where idcliente= ?";
			try (Connection con = mc.conectar()) {
				pst = con.prepareStatement(delete);
				pst.setString(1, txtCliId.getText());
				int confirmacaoDeletar = pst.executeUpdate();
				if (confirmacaoDeletar > 0) {
					JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!");
					limparCampos(true);
					btnAdcCliente.setEnabled(true);
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}

	/**
	 * Pesquisar cliente.
	 */
	public void pesquisar_cliente() {
		String search = "select idcliente as id, nomecli as nome, enderecocli as endereco, fonecli as fone, email from tbclientes where nomecli like ?";
		try (Connection con = mc.conectar()) {
			pst = con.prepareStatement(search);
			pst.setString(1, txtCliPesquisar.getText() + "%");
			rs = pst.executeQuery();
			tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	/**
	 * Setar campos.
	 */
	public void setar_campos() {
		int setar = tblClientes.getSelectedRow();
		if (setar != -1) {
			Object id = tblClientes.getModel().getValueAt(setar, 0);
			Object nome = tblClientes.getModel().getValueAt(setar, 1);
			Object endereco = tblClientes.getModel().getValueAt(setar, 2);
			Object telefone = tblClientes.getModel().getValueAt(setar, 3);
			Object email = tblClientes.getModel().getValueAt(setar, 4);

			txtCliId.setText(id != null ? id.toString() : "");
			txtCliNome.setText(nome != null ? nome.toString() : "");
			txtCliEnd.setText(endereco != null ? endereco.toString() : "");
			txtCliFone.setText(telefone != null ? telefone.toString() : "");
			txtCliEmail.setText(email != null ? email.toString() : "");
			btnAdcCliente.setEnabled(false);
		} else {
			JOptionPane.showMessageDialog(null, "Por favor selecione um cliente para edição");
			limparCampos(false);
		}

	}

	/**
	 * Validar campos.
	 *
	 * @return true, if successful
	 */
	private boolean validarCampos() {
		if (txtCliNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Favor preencher campo obrigatório: Nome");
			txtCliNome.requestFocus();
			return false;
		} else if (txtCliFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Favor preencher campo obrigatório: Telefone");
			txtCliFone.requestFocus();
			return false;
		}
		return true;
	}

	/**
	 * Limpar campos.
	 *
	 * @param limparPesquisa the limpar pesquisa
	 */
	private void limparCampos(boolean limparPesquisa) {
		if (limparPesquisa) {
			txtCliPesquisar.setText(null);
		}
		txtCliNome.setText(null);
		txtCliEnd.setText(null);
		txtCliFone.setText(null);
		txtCliEmail.setText(null);
		txtCliId.setText(null);
		((DefaultTableModel) tblClientes.getModel()).setRowCount(0);
	}
}
