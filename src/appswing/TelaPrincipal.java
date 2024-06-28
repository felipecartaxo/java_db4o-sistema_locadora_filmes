package appswing;
/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class TelaPrincipal {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenu menu_1;
	private JMenu menu_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
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
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Tela principal");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		menu = new JMenu("Carro");
		menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaCarro tela = new TelaCarro();
			}
		});
		menuBar.add(menu);
		
		menu_1 = new JMenu("Motor");
		menu_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			//	TelaMotor tela = new TelaMotor();
			}
		});
		menuBar.add(menu_1);
		
		menu_2 = new JMenu("Motorista");
		menu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			//	TelaMotorista tela = new TelaMotorista();
			}
		});
		menuBar.add(menu_2);
		frame.getContentPane().setLayout(null);
		
	
	}

}
