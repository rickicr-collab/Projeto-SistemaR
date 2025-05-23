/*
 * 
 */
package br.com.infor.telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import br.com.infor.dal.ModuloConexao;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

// TODO: Auto-generated Javadoc
/**
 * Classe responsavel pela tela principal do sistema.
 *
 * @author Ricardo Rosendo
 * @version 1.1
 * The Class TelaPrincipal.
 */

public class TelaPrincipal extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The content pane. */
	private JPanel contentPane;

	/** The men cad cli. */
	private JMenuItem menCadCli;

	/** The menu. */
	private JMenuBar menu;

	/** The men cad usu. */
	public static JMenuItem menCadUsu;

	/** The men rel. */
	public static JMenu menRel;

	/** The lbl usuario. */
	public static JLabel lblUsuario;

	/** The lbl hora. */
	public static JLabel lblHora;

	/** The lbl data. */
	public static JLabel lblData;

	/** The desktop. */
	public static JDesktopPane desktop;

	/** The conexao. */
	Connection conexao = null;

	/**
	 * Launch the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
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

	public void trocarUsuario() {
		this.setVisible(false);
		TelaLogin login = new TelaLogin();
		login.setVisible(true);

	}

	/**
	 * Instantiates a new tela principal.
	 */
	public TelaPrincipal() {
		setResizable(false);
		setTitle("R - System Controle de OS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 947, 555);
		setLocationRelativeTo(null);

		menu = new JMenuBar();
		setJMenuBar(menu);

		JMenu MenCad = new JMenu("Cadastro");
		menu.add(MenCad);

		menCadCli = new JMenuItem("Cliente");
		menCadCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCliente cliente = new TelaCliente();
				cliente.setVisible(true);
				desktop.add(cliente);
			}
		});
		menCadCli.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_DOWN_MASK));
		MenCad.add(menCadCli);

		JMenuItem MenCadOS = new JMenuItem("OS");
		MenCadOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaOs telaOs = new TelaOs();
				telaOs.setVisible(true);
				desktop.add(telaOs);
			}
		});
		MenCadOS.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_DOWN_MASK));
		MenCad.add(MenCadOS);

		menCadUsu = new JMenuItem("Usuários");
		menCadUsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaUsuario usuario = new TelaUsuario();
				usuario.setVisible(true);
				desktop.add(usuario);
			}
		});
		menCadUsu.setEnabled(false);
		menCadUsu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
		MenCad.add(menCadUsu);

		menRel = new JMenu("Relatório");
		menRel.setEnabled(false);
		menu.add(menRel);

		JMenuItem MenRelSer = new JMenuItem("Serviços");
		MenRelSer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmacao = JOptionPane.showConfirmDialog(null, "Confirma a Emissão deste Relatório", "Atenção",
						JOptionPane.YES_NO_OPTION);
				if (confirmacao == JOptionPane.YES_OPTION) {
					try (Connection conexao = new ModuloConexao().conectar()) {
						JasperPrint print = JasperFillManager.fillReport(
								"C:\\Users\\Ricardo\\eclipse-workspace\\Projetos-Treinamento\\Projeto - InfoR\\src\\relatorios\\Servicos.jasper",
								null, conexao);
						JasperViewer.viewReport(print, false);
					} catch (Exception e3) {
						JOptionPane.showMessageDialog(null, e3);
					}
				}
			}
		});
		MenRelSer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
		menRel.add(MenRelSer);

		JMenuItem MenRelCli = new JMenuItem("Clientes");
		MenRelCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmacao = JOptionPane.showConfirmDialog(null, "Confirma a Emissão deste relatório", "Atenção",
						JOptionPane.YES_NO_OPTION);
				if (confirmacao == JOptionPane.YES_OPTION) {
					try (Connection conexao = new ModuloConexao().conectar()) {
						JasperPrint print = JasperFillManager.fillReport(
								"C:\\Users\\Ricardo\\eclipse-workspace\\Projetos-Treinamento\\Projeto - InfoR\\src\\relatorios\\Clientes.jasper",
								null, conexao);
						JasperViewer.viewReport(print, false);
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2);

					}
				}
			}
		});
		MenRelCli.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.ALT_DOWN_MASK));
		menRel.add(MenRelCli);

		JMenu MenAju = new JMenu("Ajuda");
		menu.add(MenAju);

		JMenuItem menAjuSob = new JMenuItem("Sobre");
		menAjuSob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaSobre sobre = new TelaSobre();
				sobre.setVisible(true);
			}
		});
		menAjuSob.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.ALT_DOWN_MASK));
		MenAju.add(menAjuSob);

		JMenu menOpc = new JMenu("Opções");
		menu.add(menOpc);

		JMenuItem menOpcSair = new JMenuItem("Sair");
		menOpcSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sair = JOptionPane.showConfirmDialog(null, "Deseja realmente sair?", "Atenção",
						JOptionPane.YES_NO_OPTION);
				if (sair == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		menOpcSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		menOpc.add(menOpcSair);

		JMenuItem manOpcTrocar = new JMenuItem("Trocar Usuário");
		manOpcTrocar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int trocaUsu = JOptionPane.showConfirmDialog(null, "Deseja trocar de Usuário?", "Atenção",
						JOptionPane.YES_NO_OPTION);
				if (trocaUsu == JOptionPane.YES_OPTION) {
					trocarUsuario();
				}
			}
		});
		manOpcTrocar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F6, InputEvent.ALT_DOWN_MASK));
		menOpc.add(manOpcTrocar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		desktop = new JDesktopPane();
		desktop.setBackground(new Color(142, 185, 181));
		desktop.setBounds(0, 11, 640, 480);
		contentPane.add(desktop);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/com/infor/icones/R4.png")));
		lblNewLabel.setBounds(650, 193, 281, 298);
		contentPane.add(lblNewLabel);

		lblUsuario = new JLabel("Usuário");
		lblUsuario.setVisible(false);
		lblUsuario.setForeground(new Color(255, 255, 255));
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsuario.setBounds(650, 33, 271, 22);
		contentPane.add(lblUsuario);

		lblData = new JLabel("Data");
		lblData.setVisible(false);
		lblData.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblData.setBounds(650, 86, 271, 29);
		contentPane.add(lblData);

		lblHora = new JLabel("Hora");
		lblHora.setVisible(false);
		lblHora.setEnabled(false);
		lblHora.setFont(new Font("Monospaced", Font.BOLD, 20));
		lblHora.setBounds(650, 136, 271, 22);
		contentPane.add(lblHora);
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date data = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				lblHora.setText("Hora " + sdf.format(data));
			}
		});
		timer.start();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				Date data = new Date();
				SimpleDateFormat sdf = (SimpleDateFormat) SimpleDateFormat.getDateInstance(SimpleDateFormat.SHORT);
				lblData.setText("Data " + sdf.format(data));
			}
		});
	}
}
