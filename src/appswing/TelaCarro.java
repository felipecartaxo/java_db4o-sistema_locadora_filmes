/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package appswing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Carro;
import regras_negocio.Fachada;

public class TelaCarro {

	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel label;
	private JButton button;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton button_1;
	private JButton button_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCarro window = new TelaCarro();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCarro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setModal(true);

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
				listagem();
			}
			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});
		frame.setTitle("Carros");
		frame.setBounds(100, 100, 450, 322);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 11, 387, 119);
		frame.getContentPane().add(scrollPane);

		table = new JTable() {
			//proibir alteracao de celulas
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					if (table.getSelectedRow() >= 0) {
						String placa = (String) table.getValueAt( table.getSelectedRow(), 0);
						//	Carro carro = Fachada.localizarCarro(placa);
						//	textField.setText(placa);
						//	textField_1.setText(carro.getMotor().getNome());
						//	textField_2.setText(carro.getMotorista().getCnh();
						label.setText("falta implementar o requisito para localizar carro");
					}
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		scrollPane.setViewportView(table);
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);


		label = new JLabel("");
		label.setBounds(10, 258, 414, 14);
		frame.getContentPane().add(label);

		button = new JButton("Excluir");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (table.getSelectedRow() >= 0) {
						String placa = (String) table.getValueAt( table.getSelectedRow(), 0);
						//Fachada.exluirCarro(placa);
						label.setText("falta implementar o requisito excluir carro");
					}
					else
						label.setText("selecione um carro");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button.setBounds(310, 145, 98, 23);
		frame.getContentPane().add(button);

		label_1 = new JLabel("");
		label_1.setBounds(21, 130, 387, 14);
		frame.getContentPane().add(label_1);

		label_2 = new JLabel("placa:");
		label_2.setBounds(21, 173, 46, 14);
		frame.getContentPane().add(label_2);

		label_3 = new JLabel("nome motor:");
		label_3.setBounds(21, 198, 77, 14);
		frame.getContentPane().add(label_3);

		label_4 = new JLabel("cnh motorista");
		label_4.setBounds(209, 198, 86, 14);
		frame.getContentPane().add(label_4);

		textField = new JTextField();
		textField.setBounds(74, 170, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(101, 195, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(295, 195, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

		button_1 = new JButton("Alterar motorista");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String placa = textField.getText();
					String cnh = textField_2.getText();
					Fachada.alterarMotoristaDoCarro(placa, cnh);
					label.setText("motorista substituido");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button_1.setBounds(223, 224, 148, 23);
		frame.getContentPane().add(button_1);

		button_2 = new JButton("Criar");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String placa = textField.getText();
					String nomemotor = textField_1.getText();
					String cnh = textField_2.getText();
					Fachada.criarCarro(placa, nomemotor, cnh);
					label.setText("carro criado");
				}
				catch(Exception erro) {
					label.setText(erro.getMessage());
				}
			}
		});
		button_2.setBounds(111, 224, 89, 23);
		frame.getContentPane().add(button_2);

		frame.setVisible(true);
	}

	public void listagem() {
		List<Carro> lista = Fachada.listarCarros();	
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("placa");
		model.addColumn("motor");
		model.addColumn("motorista");

		for(Carro c : lista) {
			model.addRow(new Object[] {
					c.getPlaca(), 
					c.getMotor().getNome()+" "+c.getMotor().getPotencia(), 
					c.getMotorista().getCnh()+ "-"+c.getMotorista().getNome()
			});
		}

		table.setModel(model);
		label_1.setText("resultados: "+lista.size()+ " - selecione uma linha para editar");


	}
}
