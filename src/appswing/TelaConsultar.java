package appswing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import modelo.Video;
import regras_negocio.Fachada;

public class TelaConsultar {
    private JPanel panel;
    private JTable table;
    private DefaultTableModel tableModel;

    public TelaConsultar() {
        initialize();
    }

    private void initialize() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Configuração da tabela
        String[] columnNames = {"Consulta", "Resultado"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        realizarConsultas();
    }

    private void realizarConsultas() {
        try {
            Fachada.inicializar();
            List<Video> videos = Fachada.videosPorClassificacao(5);
            tableModel.setRowCount(0); // Limpar a tabela antes de adicionar novas linhas
            for (Video video : videos) {
                Object[] row = {"Classificação 5", video.getTitulo()};
                tableModel.addRow(row);
            }

            videos = Fachada.videosPorGenero("Suspense");
            for (Video video : videos) {
                Object[] row = {"Gênero Suspense", video.getTitulo()};
                tableModel.addRow(row);
            }

            videos = Fachada.videosPorTitulo("Coraline");
            for (Video video : videos) {
                Object[] row = {"Título Coraline", video.getTitulo()};
                tableModel.addRow(row);
            }

            videos = Fachada.videosPorLink("bladerunner.com");
            for (Video video : videos) {
                Object[] row = {"Link bladerunner.com", video.getTitulo()};
                tableModel.addRow(row);
            }
        }

        catch (Exception e) {
            JOptionPane.showMessageDialog(panel, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }

        finally {
            Fachada.finalizar();
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}