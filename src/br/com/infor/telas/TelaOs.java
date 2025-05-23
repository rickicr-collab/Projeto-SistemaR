/*
 * 
 */
package br.com.infor.telas;

import java.awt.Color;
import java.awt.Cursor;
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
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.collections.map.HashedMap;

import br.com.infor.dal.ModuloConexao;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

// TODO: Auto-generated Javadoc
/**
 * Classe responsavevel pela tela de servicos.
 *
 * @author Ricardo Rosendo
 * @version 1.1 The Class TelaOs.
 */
@SuppressWarnings({ "rawtypes", "unused", "unchecked", "null" })
public class TelaOs extends JInternalFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The txt os. */
	private JTextField txtOs;

	/** The txt os data. */
	private JTextField txtOsData;

	/** The btn group OS. */
	private final ButtonGroup btnGroupOS = new ButtonGroup();

	/** The txt cli pesquisar. */
	private JTextField txtCliPesquisar;

	/** The txt cli id. */
	private JTextField txtCliId;

	/** The tbl clientes. */
	private static JTable tblClientes;

	/** The txt os equip. */
	private JTextField txtOsEquip;

	/** The txt os defeito. */
	private JTextField txtOsDefeito;

	/** The txt os servico. */
	private JTextField txtOsServico;

	/** The txt os tecnico. */
	private JTextField txtOsTecnico;

	/** The txt os valor. */
	private JTextField txtOsValor;

	/** The rbt orc. */
	private JRadioButton rbtOrc;

	/** The rbt ods. */
	private JRadioButton rbtOds;

	/** The btn os add. */
	private static JButton btnOsAdd;

	/** The btn os edit. */
	private static JButton btnOsEdit;

	/** The btn os del. */
	private static JButton btnOsDel;

	/** The btn os search. */
	private static JButton btnOsSearch;

	/** The btn os print. */
	private static JButton btnOsPrint;

	/** The cbo os sit. */
	private static JComboBox cboOsSit;

	/** The mc. */
	private ModuloConexao mc = new ModuloConexao();

	/** The rs. */
	private ResultSet rs = null;

	/** The pst. */
	private PreparedStatement pst = null;

	/** The con. */
	private Connection con = null;

	/** The tipo. */
	private String tipo;

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaOs frame = new TelaOs();
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

	public TelaOs() {
		setTitle("OS");
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setBounds(0, 0, 640, 480);
		getContentPane().setLayout(null);

		JPanel pnOS = new JPanel();
		pnOS.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnOS.setBounds(7, 7, 234, 96);
		getContentPane().add(pnOS);
		pnOS.setLayout(null);

		JLabel lblNumOs = new JLabel("Nº OS");
		lblNumOs.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblNumOs.setBounds(10, 11, 46, 14);
		pnOS.add(lblNumOs);

		JLabel lblOsData = new JLabel("Data");
		lblOsData.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblOsData.setBounds(110, 11, 37, 14);
		pnOS.add(lblOsData);

		txtOs = new JTextField();
		txtOs.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtOs.setEditable(false);
		txtOs.setBounds(10, 36, 57, 20);
		pnOS.add(txtOs);
		txtOs.setColumns(10);

		txtOsData = new JTextField();
		txtOsData.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtOsData.setEditable(false);
		txtOsData.setBounds(100, 36, 122, 20);
		pnOS.add(txtOsData);
		txtOsData.setColumns(10);

		rbtOrc = new JRadioButton("Orçamento");
		rbtOrc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rbtOrc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Orçamento".trim();

			}
		});
		rbtOrc.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnGroupOS.add(rbtOrc);
		rbtOrc.setBounds(10, 63, 86, 23);
		pnOS.add(rbtOrc);

		rbtOds = new JRadioButton("Ordem de Serviço");
		rbtOds.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		rbtOds.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Ordem de Serviço".trim();

			}
		});
		rbtOds.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnGroupOS.add(rbtOds);
		rbtOds.setBounds(110, 63, 112, 23);
		pnOS.add(rbtOds);

		JLabel lblSituação = new JLabel("*Situação");
		lblSituação.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblSituação.setBounds(10, 127, 65, 14);
		getContentPane().add(lblSituação);

		cboOsSit = new JComboBox();
		cboOsSit.setModel(new DefaultComboBoxModel(new String[] { "Opções de situação", "Entrega Ok",
				"Orçamento Aguardando", "Orçamento Reprovado", "Orçamento Aprovado", "Aguardando Peças ",
				"Abandonado pelo cliente", "Na Bancada ", "Retornou" }));
		cboOsSit.setBounds(85, 123, 156, 22);
		getContentPane().add(cboOsSit);

		JPanel pnCliente = new JPanel();
		pnCliente.setBorder(new TitledBorder(
				new TitledBorder(
						new CompoundBorder(
								new BevelBorder(BevelBorder.RAISED, new Color(240, 240, 240), new Color(255, 255, 255),
										new Color(105, 105, 105), new Color(160, 160, 160)),
								new LineBorder(new Color(180, 180, 180))),
						"", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)),
				"Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnCliente.setBounds(246, 7, 378, 147);
		getContentPane().add(pnCliente);
		pnCliente.setLayout(null);

		txtCliPesquisar = new JTextField();
		txtCliPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisar_cliente();
			}
		});
		txtCliPesquisar.setBounds(10, 23, 162, 20);
		pnCliente.add(txtCliPesquisar);
		txtCliPesquisar.setColumns(10);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(TelaOs.class.getResource("/br/com/infor/icones/perquisar.png")));
		lblNewLabel.setBounds(182, 17, 35, 32);
		pnCliente.add(lblNewLabel);

		JLabel lblId = new JLabel("*Id");
		lblId.setBounds(238, 26, 26, 14);
		pnCliente.add(lblId);

		txtCliId = new JTextField();
		txtCliId.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtCliId.setEditable(false);
		txtCliId.setBounds(274, 23, 86, 20);
		pnCliente.add(txtCliId);
		txtCliId.setColumns(10);

		tblClientes = new JTable();
		tblClientes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setar_campos();
			}
		});
		tblClientes.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nome", "Fone" }));
		tblClientes.setBounds(10, 60, 340, 136);
		tblClientes.setFocusable(false);
		tblClientes.getTableHeader().setReorderingAllowed(false);
		tblClientes.setDefaultEditor(Object.class, null);
		pnCliente.add(tblClientes);

		JScrollPane scrollPane = new JScrollPane(tblClientes);
		scrollPane.setBounds(10, 54, 358, 82);
		pnCliente.add(scrollPane);

		JLabel lblEquipamento = new JLabel("*Equipamento");
		lblEquipamento.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblEquipamento.setBounds(7, 176, 101, 14);
		getContentPane().add(lblEquipamento);

		txtOsEquip = new JTextField();
		txtOsEquip.setBounds(103, 173, 511, 20);
		getContentPane().add(txtOsEquip);
		txtOsEquip.setColumns(10);

		JLabel lblDefeito = new JLabel("*Defeito");
		lblDefeito.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblDefeito.setBounds(35, 204, 58, 14);
		getContentPane().add(lblDefeito);

		txtOsDefeito = new JTextField();
		txtOsDefeito.setBounds(103, 201, 511, 20);
		getContentPane().add(txtOsDefeito);
		txtOsDefeito.setColumns(10);

		JLabel lblServiço = new JLabel("Serviço");
		lblServiço.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblServiço.setBounds(35, 235, 58, 14);
		getContentPane().add(lblServiço);

		txtOsServico = new JTextField();
		txtOsServico.setBounds(103, 232, 511, 20);
		getContentPane().add(txtOsServico);
		txtOsServico.setColumns(10);

		JLabel lblTecnico = new JLabel("Técnico");
		lblTecnico.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblTecnico.setBounds(35, 266, 58, 14);
		getContentPane().add(lblTecnico);

		txtOsTecnico = new JTextField();
		txtOsTecnico.setBounds(103, 263, 224, 20);
		getContentPane().add(txtOsTecnico);
		txtOsTecnico.setColumns(10);

		JLabel lblValorTotal = new JLabel("Valor Total R$");
		lblValorTotal.setFont(new Font("Monospaced", Font.PLAIN, 11));
		lblValorTotal.setBounds(337, 266, 106, 14);
		getContentPane().add(lblValorTotal);

		txtOsValor = new JTextField();
		txtOsValor.setText("0");
		txtOsValor.setBounds(453, 263, 161, 20);
		getContentPane().add(txtOsValor);
		txtOsValor.setColumns(10);

		btnOsAdd = new JButton("");
		btnOsAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdicionarOS();
			}
		});
		btnOsAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOsAdd.setToolTipText("Nova OS");
		btnOsAdd.setIcon(new ImageIcon(TelaOs.class.getResource("/br/com/infor/icones/New Os.png")));
		btnOsAdd.setBounds(35, 325, 80, 80);
		getContentPane().add(btnOsAdd);

		btnOsEdit = new JButton("");
		btnOsEdit.setEnabled(false);
		btnOsEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AtualizarOS();
			}
		});
		btnOsEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOsEdit.setToolTipText("Editar OS");
		btnOsEdit.setIcon(new ImageIcon(TelaOs.class.getResource("/br/com/infor/icones/Edit Os.png")));
		btnOsEdit.setBounds(262, 325, 80, 80);
		getContentPane().add(btnOsEdit);

		btnOsDel = new JButton("");
		btnOsDel.setEnabled(false);
		btnOsDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removerOS();
			}
		});
		btnOsDel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOsDel.setToolTipText("Remover OS");
		btnOsDel.setIcon(new ImageIcon(TelaOs.class.getResource("/br/com/infor/icones/Delete Os.png")));
		btnOsDel.setBounds(372, 325, 80, 80);
		getContentPane().add(btnOsDel);

		btnOsSearch = new JButton("");
		btnOsSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarOs();
			}
		});
		btnOsSearch.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOsSearch.setToolTipText("Buscar OS");
		btnOsSearch.setIcon(new ImageIcon(TelaOs.class.getResource("/br/com/infor/icones/Search OS.png")));
		btnOsSearch.setBounds(147, 325, 80, 80);
		getContentPane().add(btnOsSearch);

		btnOsPrint = new JButton("");
		btnOsPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirOs();
			}
		});
		btnOsPrint.setEnabled(false);
		btnOsPrint.setToolTipText("Imprimir OS");
		btnOsPrint.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnOsPrint.setIcon(new ImageIcon(TelaOs.class.getResource("/br/com/infor/icones/Print Os.png")));
		btnOsPrint.setBounds(488, 325, 80, 80);
		getContentPane().add(btnOsPrint);

	}

	/**
	 * Adicionar OS.
	 */
	private void AdicionarOS() {
		if (!validarCampos()) {
			return;
		} else {
			String creat = "insert into tbos (tipo, situacao, equipamento, defeito, servico, tecnico,valor,idcliente) values(?, ?, ?, ?, ?, ?, ?, ?)";
			try (Connection con = mc.conectar(); PreparedStatement pst = con.prepareStatement(creat)) {
				pst.setString(1, tipo);
				pst.setString(2, cboOsSit.getSelectedItem().toString());
				pst.setString(3, txtOsEquip.getText());
				pst.setString(4, txtOsDefeito.getText());
				pst.setString(5, txtOsServico.getText());
				pst.setString(6, txtOsTecnico.getText());
				pst.setString(7, txtOsValor.getText().replace(",", "."));
				pst.setString(8, txtCliId.getText());
				int confirmarAdicao = pst.executeUpdate();
				if (confirmarAdicao > 0) {
					JOptionPane.showMessageDialog(null, "Ordem de servico Emitida com sucesso!");
					limparCampos();
					btnOsAdd.setEnabled(false);
					btnOsSearch.setEnabled(false);
					btnOsPrint.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Erro ao emitir Ordem de serviço.");
				}
			} catch (SQLException sqle) {
				JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados" + sqle.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}

	/**
	 * Atualizar OS.
	 */
	private void AtualizarOS() {
		if (!validarCampos()) {
			return;
		} else {
			String update = "update tbos set tipo = ?, situacao = ?, equipamento = ?, defeito = ?, servico = ?, tecnico = ?, valor = ? where os = ?";
			try (Connection con = mc.conectar(); PreparedStatement pst = con.prepareStatement(update)) {
				pst.setString(1, tipo);
				pst.setString(2, cboOsSit.getSelectedItem().toString());
				pst.setString(3, txtOsEquip.getText());
				pst.setString(4, txtOsDefeito.getText());
				pst.setString(5, txtOsServico.getText());
				pst.setString(6, txtOsTecnico.getText());
				pst.setString(7, txtOsValor.getText().replace(",", "."));
				pst.setString(8, txtOs.getText());
				int confirmacao = pst.executeUpdate();
				if (confirmacao > 0) {
					JOptionPane.showMessageDialog(null, "Os atualizada com sucesso!");
					limparCampos();
					btnOsAdd.setEnabled(true);
					btnOsSearch.setEnabled(true);
					txtCliPesquisar.setEnabled(true);
					tblClientes.setVisible(true);
					btnOsDel.setEnabled(false);
					btnOsEdit.setEnabled(false);
					btnOsPrint.setEnabled(false);
				}

			} catch (SQLException sqle) {
				JOptionPane.showMessageDialog(null, "Erro ao acessar o banco de dados: " + sqle.getMessage());
				System.out.println(sqle);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
			}
		}
	}

	/**
	 * Pesquisar os.
	 */
	private void pesquisarOs() {
		String num_os;
		while (true) {
			num_os = JOptionPane.showInputDialog(null, "Digite o numero da OS para busca!");
			if (num_os == null) {
				return;
			}
			try {
				num_os = num_os.trim();
				Integer.parseInt(num_os);
				break;
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Por favor, digite apenas numeros!");
			}
		}
		String search = "select os, date_format(data_os, '%d/%m/%Y - %H:%i'),tipo,situacao,equipamento,defeito,servico,tecnico,valor,idcliente from tbos where os = "
				+ num_os;
		try (Connection con = mc.conectar(); PreparedStatement pst = con.prepareStatement(search)) {
			rs = pst.executeQuery();
			if (rs.next()) {
				txtOs.setText(rs.getString(1));
				txtOsData.setText(rs.getString(2));
				String rbtTipo = rs.getString(3);
				if (rbtTipo.equals("Ordem de Serviço")) {
					rbtOds.setSelected(true);
					tipo = "Ordem de Serviço";
				} else {
					rbtOrc.setSelected(true);
					tipo = "Orçamento";
				}
				cboOsSit.setSelectedItem(rs.getString(4));
				txtOsEquip.setText(rs.getString(5));
				txtOsDefeito.setText(rs.getString(6));
				txtOsServico.setText(rs.getString(7));
				txtOsTecnico.setText(rs.getString(8));
				txtOsValor.setText(rs.getString(9));
				txtCliId.setText(rs.getString(10));
				btnOsAdd.setEnabled(false);
				btnOsSearch.setEnabled(false);
				txtCliPesquisar.setEnabled(false);
				tblClientes.setVisible(false);
				btnOsDel.setEnabled(true);
				btnOsEdit.setEnabled(true);
				btnOsPrint.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "Os não cadastrada!");
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao consultar a base de dados: " + e.getMessage());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage());
		}

	}

	/**
	 * Remover OS.
	 */
	private void removerOS() {
		int confirmar = JOptionPane.showConfirmDialog(null, "Deseja Excluir a Ordem de Serviço?", "Atenção",
				JOptionPane.YES_NO_OPTION);
		if (confirmar == JOptionPane.YES_OPTION) {
			String remover = "Delete from tbos where os = ?";
			try (Connection con = mc.conectar(); PreparedStatement pst = con.prepareStatement(remover)) {
				pst.setString(1, txtOs.getText());
				int apagado = pst.executeUpdate();
				if (apagado > 0) {
					JOptionPane.showMessageDialog(null, "Os excluida com sucesso!");
					limparCampos();
					btnOsAdd.setEnabled(true);
					btnOsSearch.setEnabled(true);
					txtCliPesquisar.setEnabled(true);
					tblClientes.setVisible(true);
					btnOsDel.setEnabled(false);
					btnOsEdit.setEnabled(false);
					btnOsPrint.setEnabled(false);
				} else {
					JOptionPane.showConfirmDialog(null, "Erro ao excluir OS - favor verificar o erro!!");
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro inesperado:" + e.getMessage());
			}
		}
	}

	/**
	 * Pesquisar cliente.
	 */
	private void pesquisar_cliente() {
		String pesquisarCliente = "select idcliente as ID, nomecli as Nome, fonecli as Fone from tbclientes where nomecli like ?";
		try (Connection con = mc.conectar(); PreparedStatement pst = con.prepareStatement(pesquisarCliente)) {
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
	private void setar_campos() {
		int setar = tblClientes.getSelectedRow();
		if (setar != -1) {
			Object id = tblClientes.getModel().getValueAt(setar, 0);
			txtCliId.setText(id != null ? id.toString() : "");
		}
	}

	/**
	 * Validar campos.
	 *
	 * @return true, if successful
	 */
	private boolean validarCampos() {
		if (txtOsEquip.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Favor preencher campo obrigatório: Equipamento");
			txtOsEquip.requestFocus();
			return false;
		} else if (txtOsDefeito.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Favor preencher campo obrigatório: Defeito");
			txtOsDefeito.requestFocus();
			return false;
		} else if (txtCliId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Pesquise um cliente e selecione para obter: ID");
			return false;
		} else if (btnGroupOS.getSelection() == null) {
			JOptionPane.showMessageDialog(null, "Favor selecionar o tipo [Orçamento/Ordem de Serviço]");
			rbtOrc.requestFocus();
			return false;
		} else {
			Object selectedItem = cboOsSit.getSelectedItem();
			if (selectedItem != null && selectedItem.toString().trim().equals("Opções de situação")) {
				JOptionPane.showMessageDialog(null, "Favor preencher campo obrigatório: Situacao");
				cboOsSit.requestFocus();
				return false;
			}
		}
		return true;
	}

	/**
	 * Limpar campos.
	 */
	private void limparCampos() {
		txtOs.setText(null);
		txtOsData.setText(null);
		txtOsEquip.setText(null);
		txtOsServico.setText(null);
		txtOsDefeito.setText(null);
		txtCliPesquisar.setText(null);
		txtOsTecnico.setText(null);
		txtOsValor.setText(null);
		txtCliId.setText(null);
		((DefaultTableModel) tblClientes.getModel()).setRowCount(0);
		cboOsSit.setSelectedItem("Opções de situação");
		btnGroupOS.clearSelection();
	}

	/**
	 * Imprimir os.
	 */
	private void imprimirOs() {
		int confirmacao = JOptionPane.showConfirmDialog(null, "Confirma a Impressão desta Ordem de Serviço?",
				"Atenção !", JOptionPane.YES_NO_OPTION);
		if (confirmacao == JOptionPane.YES_OPTION) {
			try (Connection con = mc.conectar()) {
				HashMap filtro = new HashMap();
				filtro.put("os", Integer.parseInt(txtOs.getText()));
				JasperPrint print = JasperFillManager.fillReport(
						"C:\\Users\\Ricardo\\eclipse-workspace\\Projetos-Treinamento\\Projeto - InfoR\\src\\relatorios\\OS.jasper",
						filtro, con);
				JasperViewer.viewReport(print, false);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}

}
