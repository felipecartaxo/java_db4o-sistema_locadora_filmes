package appswing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import modelo.Video;
import regras_negocio.Fachada;

public class TelaVideo {
    private JPanel panel;
    private JTable table;
    private DefaultTableModel tableModel;

    public TelaVideo() {
        initialize();
    }

    private void initialize() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Configuração da tabela
        String[] columnNames = {"Título", "Link"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        listarVideos();
    }

    private void listarVideos() {
        try {
            Fachada.inicializar();
            List<Video> videos = Fachada.listarVideos();
            tableModel.setRowCount(0); // Limpar a tabela antes de adicionar novas linhas
            for (Video video : videos) {
                Object[] row = {video.getTitulo(), video.getLink()};
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            Fachada.finalizar();
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}



