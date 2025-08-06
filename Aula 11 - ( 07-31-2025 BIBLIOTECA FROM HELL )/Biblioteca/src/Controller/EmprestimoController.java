package Controller;


import Model.Aluno;
import Model.DAO.AlunoDAO;
import Model.DAO.EmprestimoDAO;
import Model.DAO.LivroDAO;
import Model.Emprestimo;
import Model.Livro;

import java.util.List;


public class EmprestimoController {
    private EmprestimoDAO emprestimoDAO;

    public EmprestimoController() {
        this.emprestimoDAO = new EmprestimoDAO();
    }

    public void cadastroEmprestimo(int fk_id_livro, int fk_id_aluno, String data_emprestimo, String data_devolucao) throws Exception {
        if (String.valueOf(fk_id_aluno).trim().isEmpty()) {
            throw new Exception("O ID do aluno deve ser anexado ao cadastrar!");
        }
        if (String.valueOf(fk_id_livro).trim().isEmpty()) {
            throw new Exception("O ID do livro deve ser anexado ao cadastrar!");
        }
        if (data_emprestimo == null || data_emprestimo.trim().isEmpty()) {
            throw new Exception("A data de emprestimo deve ser anexada ao cadastrar!");
        }
        if (data_devolucao == null || data_devolucao.trim().isEmpty()) {
            throw new Exception("A data de devolucao deve ser anexada ao cadastrar!");
        }

        Emprestimo emprestimo = new Emprestimo(fk_id_livro, fk_id_aluno, data_emprestimo, data_devolucao);
        emprestimoDAO.setEmprestimo(emprestimo);
    }

    public int getFkAluno(String nome, String contato) {
        return emprestimoDAO.getIdAluno(nome, contato);
    }

    public int getFkLivro(String isbn) {
        return emprestimoDAO.getIdLivro(isbn);
    }

    public Livro getInfosLivro(int id) {
        LivroDAO livro = new LivroDAO();
        return livro.buscarPorId(id);
    }

    public Aluno getInfosAluno(int id) {
        AlunoDAO aluno = new AlunoDAO();
        return aluno.buscarPorId(id);
    }

    public void atualizarEmprestimo(int id, int fk_id_livro, int fk_id_aluno, String data_emprestimo, String data_devolucao) throws Exception {
        if (String.valueOf(fk_id_aluno).trim().isEmpty() || String.valueOf(fk_id_livro).trim().isEmpty() || data_emprestimo == null || data_emprestimo.trim().isEmpty() || data_devolucao == null || data_devolucao.trim().isEmpty()) {
            throw new Exception("Todos os campos do emprestimo são obrigatórios e devem ser válidos.");
        }

        Emprestimo emprestimo = new Emprestimo(fk_id_livro, fk_id_aluno, data_emprestimo, data_devolucao);
        emprestimoDAO.updateEmprestimo(emprestimo, id);
    }

    public List<Emprestimo> listarEmprestimos() {
        return emprestimoDAO.getEmprestimos();
    }

    public Emprestimo getEmprestimoById(int id) {
        return emprestimoDAO.getEmprestimoByID(id);
    }

    public List<Emprestimo> getEmprestimosByAluno(String nome) {
        return emprestimoDAO.getEmprestimosByAluno(nome);
    }

    public List<Emprestimo> getEmprestimosByLivro(int id) {
        return emprestimoDAO.getEmprestimosByLivro(id);
    }

    public void removerEmprestimo(int id) {
        emprestimoDAO.deletarEmprestimo(id);
    }
}
