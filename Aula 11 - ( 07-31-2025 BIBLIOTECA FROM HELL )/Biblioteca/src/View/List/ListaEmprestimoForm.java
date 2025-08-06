package View.List;

import Controller.AlunoController;
import Controller.EmprestimoController;
import Controller.LivroController;
import Model.Emprestimo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ListaEmprestimoForm extends JInternalFrame {

    private EmprestimoController controller;
    private JTable tableEmprestimo;
    private DefaultTableModel tableModel;
    private JButton btnAtualizar, btnRemover, btnBuscar;
    private JTextField txtAlunoBuscaNome;

    public ListaEmprestimoForm(EmprestimoController controller) {
        super("Lista de Emprestimos",true,true,true,true);
        this.controller = controller;

        setSize(850, 500);
        setLayout(new BorderLayout());

        String[] colunas = {"ID", "Aluno", "Livro", "Data Retirada", "Data A Devolver"};
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableEmprestimo = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableEmprestimo);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelAcoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtAlunoBuscaNome = new JTextField(20);
        btnBuscar = new JButton("Buscar por Aluno");
        btnAtualizar = new JButton("Atualizar Tabela");
        btnRemover = new JButton("Remover Selecionado");

        panelAcoes.add(new JLabel("Aluno:"));
        panelAcoes.add(txtAlunoBuscaNome);
        panelAcoes.add(btnBuscar);
        panelAcoes.add(btnAtualizar);
        panelAcoes.add(btnRemover);
        add(panelAcoes, BorderLayout.NORTH);

        btnAtualizar.addActionListener(e -> carregarEmprestimoNaTabela());
        btnRemover.addActionListener(e -> removerEmprestimoSelecionado());
        btnBuscar.addActionListener(e -> buscarEmprestimosPorAluno());

        carregarEmprestimoNaTabela();
    }

    private void carregarEmprestimoNaTabela() {
        tableModel.setRowCount(0); // Limpa as linhas existentes na tabela
        java.util.List<Emprestimo> emprestimos = controller.listarEmprestimos(); // Busca todos os emprestimos
        for (Emprestimo emprestimo : emprestimos) {
            // Adiciona cada livro como uma nova linha na tabela
            tableModel.addRow(new Object[]{
                    emprestimo.getId(),
                    emprestimo.getFk_livro(),
                    emprestimo.getFk_aluno(),
                    emprestimo.getData_emprestimo(),
                    emprestimo.getData_devolucao()
            });
        }
    }


    private void removerEmprestimoSelecionado() {
        int selectedRow = tableEmprestimo.getSelectedRow(); // Obtém a linha selecionada
        if (selectedRow >= 0) { // Verifica se alguma linha foi selecionada
            int idEmprestimo = (int) tableModel.getValueAt(selectedRow, 0); // Obtém o ID da célula da tabela

            // Confirmação antes de remover
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Tem certeza que deseja remover o emprestimo ID: " + idEmprestimo + "?",
                    "Confirmar Remoção", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    controller.removerEmprestimo(idEmprestimo); // Chama o controller para remover
                    JOptionPane.showMessageDialog(this, "Emprestimo removido com sucesso!");
                    carregarEmprestimoNaTabela(); // Atualiza a tabela após a remoção
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao remover emprestimo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um emprestimo para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }


    private void buscarEmprestimosPorAluno() {
        String alunoBusca = txtAlunoBuscaNome.getText().trim(); // Obtém o texto do campo de busca
        tableModel.setRowCount(0); // Limpa a tabela
        LivroController livroController = new LivroController();

        List<Emprestimo> emprestimos;
        if (alunoBusca.isEmpty()) {
            // Se o campo de busca estiver vazio, lista todos
            emprestimos = controller.listarEmprestimos();
        } else {
            // Caso contrário, busca por nome
            emprestimos = controller.getEmprestimosByAluno(alunoBusca);
        }

        if (emprestimos.isEmpty() && !alunoBusca.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhum livro encontrado com o nome: '" + alunoBusca + "'", "Busca", JOptionPane.INFORMATION_MESSAGE);
        }

        for (Emprestimo emprestimo : emprestimos) {
            tableModel.addRow(new Object[]{
                    emprestimo.getId(),
                    livroController.buscarLivroPorID(emprestimo.getFk_livro()).getTitulo(),
                    alunoBusca,
                    emprestimo.getData_emprestimo(),
                    emprestimo.getData_devolucao()
            });
        }
    }


}
