package View.Cadastro;

import Controller.EmprestimoController;
import Model.Emprestimo;

import javax.swing.*;
import java.awt.*;


public class CadastroEmprestimoForm extends JInternalFrame {

    private EmprestimoController controller;
    private JTextField txtId, txtFkIdLivro, txtFkIdAluno, txtDataEmprestimo, txtDataDevolucao;
    private JButton btnBuscar, btnSalvar;
    private Integer emprestimoIdParaEdicao;

    public CadastroEmprestimoForm(EmprestimoController emprestimoController, Integer emprestimoId) {
        super("Cadastro de Emprestimo",true,true,true,true);
        this.controller = emprestimoController;
        this.emprestimoIdParaEdicao = emprestimoId;

        setSize(500, 350); // Tamanho da janela interna
        setLayout(new GridBagLayout()); // Layout para organizar os componentes
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 40, 5, 40); // Espaçamento entre os componentes
        gbc.fill = GridBagConstraints.HORIZONTAL; // Preenche o espaço horizontalmente

        int row = 0; // Contador de linhas para o layout

        // Campo ID
        gbc.gridx = 0; gbc.gridy = row;
        add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = row;
        txtId = new JTextField(10);
        txtId.setEditable(false); // ID não pode ser editado diretamente, apenas buscado
        add(txtId, gbc);
        gbc.gridx = 2; gbc.gridy = row;
        btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarEmprestimo()); // Adiciona ação ao botão Buscar
        add(btnBuscar, gbc);
        row++;

        // Campo FkIdLivro
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Livro sendo retirado:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2; // Ocupa 2 colunas
        txtFkIdLivro = new JTextField(20);
        add(txtFkIdLivro, gbc);
        row++;

        // Campo FkIdAluno
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Aluno retirando livro:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2; // Ocupa 2 colunas
        txtFkIdAluno = new JTextField(20);
        add(txtFkIdAluno, gbc);
        row++;

        // Campo DataRetirada
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Data de retirada:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        txtDataEmprestimo = new JTextField(20); // Preenche com a data atual por padrão
        add(txtDataEmprestimo, gbc);
        row++;

        // Campo DataDevolucao
        gbc.gridx = 0;
        gbc.gridy = row;
        add(new JLabel("Data a ser devolvida:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        txtDataDevolucao = new JTextField(20); // Preenche com a data atual por padrão
        add(txtDataDevolucao, gbc);
        row++;

        // Botão Salvar
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 3; // Ocupa 3 colunas
        btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(e -> salvarEmprestimo()); // Adiciona ação ao botão Salvar
        add(btnSalvar, gbc);

        // Se um ID foi passado, carrega o dinossauro para edição
        if (emprestimoIdParaEdicao != null) {
            carregarEmprestimoParaEdicao(emprestimoIdParaEdicao);
            txtId.setText(String.valueOf(emprestimoIdParaEdicao));
            btnBuscar.setEnabled(false); // Desabilita o botão buscar se já está editando
        }
    }

    private void buscarEmprestimo() {
        String idStr = JOptionPane.showInputDialog(this, "Digite o ID do livro para buscar:");
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int id = Integer.parseInt(idStr);
                carregarEmprestimoParaEdicao(id);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido. Por favor, digite um número.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void carregarEmprestimoParaEdicao(int id) {
        try {
            Emprestimo emprestimo = controller.getEmprestimoById(id);
            if (emprestimo != null) {
                txtId.setText(String.valueOf(emprestimo.getId()));
                txtFkIdLivro.setText(String.valueOf(emprestimo.getFk_livro()));
                txtFkIdAluno.setText(String.valueOf(emprestimo.getFk_aluno()));
                txtDataEmprestimo.setText(emprestimo.getData_emprestimo());
                txtDataDevolucao.setText(emprestimo.getData_devolucao());
                emprestimoIdParaEdicao = emprestimo.getId(); // Define o ID para indicar que é uma edição
            } else {
                JOptionPane.showMessageDialog(this, "Emprestimo com ID " + id + " não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                limparCampos(); // Limpa os campos se não encontrar
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao buscar aluno: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void salvarEmprestimo() {
        try {
            int fkIdLivro = Integer.parseInt(txtFkIdLivro.getText().trim());
            int fkIdAluno = Integer.parseInt(txtFkIdAluno.getText().trim());
            String dataEmprestimo = txtDataEmprestimo.getText().trim();
            String dataDevolucao = txtDataDevolucao.getText().trim();


            if (emprestimoIdParaEdicao == null) { // Se livroIdParaEdicao é null, é um novo cadastro
                controller.cadastroEmprestimo(fkIdLivro, fkIdAluno, dataEmprestimo, dataDevolucao);
                JOptionPane.showMessageDialog(this, "Emprestimo cadastrado com sucesso!");
            } else { // Caso contrário, é uma atualização
                controller.atualizarEmprestimo(emprestimoIdParaEdicao ,fkIdLivro, fkIdAluno, dataEmprestimo, dataDevolucao);
                JOptionPane.showMessageDialog(this, "Emprestimo atualizado com sucesso!");
            }
            this.dispose(); // Fecha a janela após a operação bem-sucedida
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar emprestimo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        txtId.setText("");
        txtFkIdLivro.setText("");
        txtFkIdAluno.setText("");
        txtDataEmprestimo.setText("");
        txtDataDevolucao.setText("");
        emprestimoIdParaEdicao = null; // Reseta para modo de novo cadastro
        btnBuscar.setEnabled(true); // Habilita o botão buscar novamente
    }
}
