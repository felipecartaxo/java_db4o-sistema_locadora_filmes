
package appswing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import modelo.Video;
import regras_negocio.Fachada;

public class TelaConsulta {
    private JPanel panel;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<String> comboBoxConsultas;
    private JTextField textFieldPesquisa;
    private JButton buttonPesquisar;

    public TelaConsulta() {
        initialize();
    }

    private void initialize() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Painel para opções de consulta
        JPanel panelOpcoes = new JPanel();
        panelOpcoes.setLayout(new FlowLayout());

        // ComboBox para selecionar tipo de consulta
        String[] tiposConsultas = {"Classificação", "Gênero", "Título", "Link"};
        comboBoxConsultas = new JComboBox<>(tiposConsultas);
        panelOpcoes.add(comboBoxConsultas);

        // Campo de texto para pesquisa
        textFieldPesquisa = new JTextField(20);
        panelOpcoes.add(textFieldPesquisa);

        // Botão para realizar a pesquisa
        buttonPesquisar = new JButton("Pesquisar");
        buttonPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarConsulta();
            }
        });
        panelOpcoes.add(buttonPesquisar);

        panel.add(panelOpcoes, BorderLayout.NORTH);

        // Configuração da tabela
        String[] columnNames = {"Consulta", "Título", "Classificação", "Link"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
    }

    // Método para realizar consultas baseadas na seleção do usuário
    private void realizarConsulta() {
        try {
            Fachada.inicializar();
            String tipoConsulta = (String) comboBoxConsultas.getSelectedItem();
            String valorPesquisa = textFieldPesquisa.getText();
            List<Video> videos = null;

            tableModel.setRowCount(0); // Limpar a tabela antes de adicionar novas linhas

            switch (tipoConsulta) {
                case "Classificação":
                    try {
                        int classificacao = Integer.parseInt(valorPesquisa);
                        videos = Fachada.videosPorClassificacao(classificacao);
                        for (Video video : videos) {
                            Object[] row = {"Classificação " + classificacao, video.getTitulo(), video.getClassificacao(), video.getLink()};
                            tableModel.addRow(row);
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(panel, "Por favor, insira um valor numérico para classificação.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case "Gênero":
                    videos = Fachada.videosPorGenero(valorPesquisa);
                    for (Video video : videos) {
                        Object[] row = {"Gênero " + valorPesquisa, video.getTitulo(), video.getClassificacao(), video.getLink()};
                        tableModel.addRow(row);
                    }
                    break;
                case "Título":
                    videos = Fachada.videosPorTitulo(valorPesquisa);
                    for (Video video : videos) {
                        Object[] row = {"Título " + valorPesquisa, video.getTitulo(), video.getClassificacao(), video.getLink()};
                        tableModel.addRow(row);
                    }
                    break;
                case "Link":
                    videos = Fachada.videosPorLink(valorPesquisa);
                    for (Video video : videos) {
                        Object[] row = {"Link " + valorPesquisa, video.getTitulo(), video.getClassificacao(), video.getLink()};
                        tableModel.addRow(row);
                    }
                    break;
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



























/*
 
 //original

package appswing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import modelo.Video;
import regras_negocio.Fachada;

public class TelaConsultar {
	// Painel principal da tela de consulta
    private JPanel panel;
    // Tabela para exibir os resultados das consultas
    private JTable table;
    // Modelo da tabela para manipulação de dados
    private DefaultTableModel tableModel;

    // Construtor da classe
    public TelaConsultar() {
    	// Inicializa os componentes da interface
        initialize();
    }

    // Método para inicializar a interface
    private void initialize() {
    	// Cria o painel principal
        panel = new JPanel();
        // Define o layout do painel como BorderLayout
        panel.setLayout(new BorderLayout());

        // Configuração da tabela
        String[] columnNames = {"Consulta", "Resultado"}; // Define os nomes das colunas
        // Cria o modelo da tabela com as colunas
        tableModel = new DefaultTableModel(columnNames, 0);
        // Cria a tabela com o modelo
        table = new JTable(tableModel);
        // Adiciona a tabela a um scroll pane para permitir rolagem
        JScrollPane scrollPane = new JScrollPane(table);
        // Adiciona o scroll pane ao painel
        panel.add(scrollPane, BorderLayout.CENTER);

        // Realiza as consultas e preenche a tabela
        realizarConsultas();
    }

    // Método para realizar consultas e preencher a tabela
    private void realizarConsultas() {
        try {
        	// Inicializa a fachada (configura o acesso aos dados)
            Fachada.inicializar();
            // Consulta vídeos por classificação
            // Consulta vídeos com classificação 5
            List<Video> videos = Fachada.videosPorClassificacao(5);
            tableModel.setRowCount(0); // Limpa a tabela antes de adicionar novas linhas
            for (Video video : videos) {
            	// Cria uma linha com o resultado da consulta
                Object[] row = {"Classificação 5", video.getTitulo()};
                // Adiciona a linha ao modelo da tabela
                tableModel.addRow(row);
            }

            // Consulta vídeos por gênero
            // Consulta vídeos do gênero "Suspense"
            videos = Fachada.videosPorGenero("Suspense");
            for (Video video : videos) {
            	// Cria uma linha com o resultado da consulta
                Object[] row = {"Gênero Suspense", video.getTitulo()};
                // Adiciona a linha ao modelo da tabela
                tableModel.addRow(row);
            }

            // Consulta vídeos por título
            // Consulta vídeos com o título "Coraline"
            videos = Fachada.videosPorTitulo("Coraline");
            for (Video video : videos) {
            	// Cria uma linha com o resultado da consulta
                Object[] row = {"Título Coraline", video.getTitulo()};
                // Adiciona a linha ao modelo da tabela
                tableModel.addRow(row);
            }

            // Consulta vídeos por link
            // Consulta vídeos com o link "bladerunner.com"
            videos = Fachada.videosPorLink("bladerunner.com");
            for (Video video : videos) {
            	// Cria uma linha com o resultado da consulta
                Object[] row = {"Link bladerunner.com", video.getTitulo()};
                // Adiciona a linha ao modelo da tabela
                tableModel.addRow(row);
            }
        } catch (Exception e) {
        	// Exibe uma mensagem de erro se ocorrer uma exceção durante as consultas
            JOptionPane.showMessageDialog(panel, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
        	// Finaliza a fachada (fecha o acesso aos dados)
            Fachada.finalizar();
        }
    }

    // Método para obter o painel principal da tela de consulta
    public JPanel getPanel() {
        return panel;
    }
}

*/
