package appswing2;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Video;
import regras_negocio.Fachada;

public class TelaVideo extends JFrame {
	private JPanel panel;
    private JTextField tituloField;
    private JTextField linkField;
    private JTextField classificacaoField;
    private JTable videoTable;
    private DefaultTableModel tableModel;

    public TelaVideo() {
        setTitle("Gerenciamento de Vídeos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Modelo da tabela
        tableModel = new DefaultTableModel(new String[]{"ID", "Título", "Link", "Classificação"}, 0);
        videoTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(videoTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Painel de botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));

        JButton listarButton = new JButton("Listar");
        listarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarVideos();
            }
        });
        buttonPanel.add(listarButton);

        JButton criarButton = new JButton("Criar");
        criarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                criarVideo();
            }
        });
        buttonPanel.add(criarButton);

        JButton deletarButton = new JButton("Deletar");
        deletarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletarVideo();
            }
        });
        buttonPanel.add(deletarButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void listarVideos() {
        try {
            List<Video> videos = Fachada.listarVideos();
            tableModel.setRowCount(0);
            for (Video video : videos) {
                tableModel.addRow(new Object[]{video.getId(), video.getTitulo(), video.getLink(), video.getClassificacao()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void criarVideo() {
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        tituloField = new JTextField();
        linkField = new JTextField();
        classificacaoField = new JTextField();

        inputPanel.add(new JLabel("Título:"));
        inputPanel.add(tituloField);
        inputPanel.add(new JLabel("Link:"));
        inputPanel.add(linkField);
        inputPanel.add(new JLabel("Classificação:"));
        inputPanel.add(classificacaoField);

        int result = JOptionPane.showConfirmDialog(this, inputPanel, "Criar Vídeo", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String titulo = tituloField.getText();
                String link = linkField.getText();
                int classificacao = Integer.parseInt(classificacaoField.getText());
                Fachada.criarVideo(titulo, link, classificacao);
                listarVideos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deletarVideo() {
        int selectedRow = videoTable.getSelectedRow();
        if (selectedRow >= 0) {
            // Deletar vídeo selecionado na tabela
            String titulo = (String) tableModel.getValueAt(selectedRow, 1);
            try {
                Fachada.excluirVideo(titulo);
                listarVideos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Deletar vídeo pelo título digitado
            String titulo = JOptionPane.showInputDialog(this, "Digite o título do vídeo a ser deletado:");
            if (titulo != null && !titulo.isEmpty()) {
                try {
                    Fachada.excluirVideo(titulo);
                    listarVideos();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Fachada.inicializar();
                TelaVideo frame = new TelaVideo();
                frame.setVisible(true);
            }
        });
    }

	public Component getPanel() {
		// TODO Auto-generated method stub
		return panel;
	}
}