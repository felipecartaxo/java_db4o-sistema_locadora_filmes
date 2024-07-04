package appswing;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal {

    private JFrame frame;
    private JPanel panelContainer;
    private TelaGenero telaGenero;
    private TelaVideo telaVideo;
    private TelaConsultar telaConsultar;

    public TelaPrincipal() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Tela Principal");
        frame.setBounds(100, 100, 800, 600); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        JPanel panelMenu = new JPanel();
        frame.getContentPane().add(panelMenu, BorderLayout.WEST);
        panelMenu.setLayout(new GridLayout(4, 1, 0, 0));

        JButton btnGeneros = new JButton("Gêneros");
        btnGeneros.addActionListener(e -> exibirTelaGenero());
        panelMenu.add(btnGeneros);

        JButton btnVideos = new JButton("Vídeos");
        btnVideos.addActionListener(e -> exibirTelaVideo());
        panelMenu.add(btnVideos);

        JButton btnConsultar = new JButton("Consultar");
        btnConsultar.addActionListener(e -> exibirTelaConsultar());
        panelMenu.add(btnConsultar);

        JButton btnSair = new JButton("Sair");
        btnSair.addActionListener(e -> System.exit(0));
        panelMenu.add(btnSair);

        panelContainer = new JPanel();
        frame.getContentPane().add(panelContainer, BorderLayout.CENTER);
        panelContainer.setLayout(new BorderLayout());

        frame.setVisible(true);
    }

    private void exibirTelaGenero() {
        panelContainer.removeAll();
        if (telaGenero == null) {
            telaGenero = new TelaGenero();
        }
        
        panelContainer.add(telaGenero.getPanel(), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void exibirTelaVideo() {
        panelContainer.removeAll();
        if (telaVideo == null) {
            telaVideo = new TelaVideo();
        }
        
        panelContainer.add(telaVideo.getPanel(), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private void exibirTelaConsultar() {
        panelContainer.removeAll();
        if (telaConsultar == null) {
            telaConsultar = new TelaConsultar();
        }
        
        panelContainer.add(telaConsultar.getPanel(), BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal());
    }
}