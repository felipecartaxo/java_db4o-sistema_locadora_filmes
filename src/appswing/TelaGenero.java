package appswing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import modelo.Genero;
import regras_negocio.Fachada;

public class TelaGenero {
    private JPanel panel;
    private JTable table;
    private DefaultTableModel tableModel;

    public TelaGenero() {
        initialize();
    }

    private void initialize() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Configuração da tabela
        String[] columnNames = {"Nome"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        listarGeneros();
    }

    private void listarGeneros() {
        try {
            Fachada.inicializar();
            List<Genero> generos = Fachada.listarGeneros();
            tableModel.setRowCount(0); // Limpar a tabela antes de adicionar novas linhas
            for (Genero genero : generos) {
                Object[] row = {genero.getNome()};
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