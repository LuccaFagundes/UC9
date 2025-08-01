package Controller;

import Model.Aluno;
import Model.DAO.AlunoDAO;

import java.time.LocalDate;
import java.util.List;

public class AlunoController {
    private AlunoDAO alunoDAO;

    public AlunoDAO getAlunoDAO() {
        return alunoDAO = new AlunoDAO();
    }

    public void cadastrarAluno(String nome, String idade, String contato) throws Exception {
        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("O nome é obrigatório ao cadastrar um aluno.");
        }
        if (idade == null || idade.trim().isEmpty()) {
            throw new Exception("A idade é obrigatório ao cadastrar um aluno.");
        }
        if (contato == null || contato.trim().isEmpty()) {
            throw new Exception("O contato é obrigatório ao cadastrar um aluno.");
        }
        Aluno aluno = new Aluno(nome, Integer.parseInt(idade), contato);
        alunoDAO.setAluno(aluno);
    }

    public Aluno buscarAlunoPorID(int id) {
        return alunoDAO.buscarPorId(id);
    }

    public void atualizarAluno(int id, String nome, String idade, String contato) throws Exception {
        if (nome == null || nome.trim().isEmpty() || idade == null || idade.trim().isEmpty() || contato == null || contato.trim().isEmpty()) {
            throw new Exception("Todos os campos do Aluno são obrigatórios e devem ser validos.");
        }
        Aluno aluno = new Aluno(id, nome, Integer.parseInt(idade), contato);
        alunoDAO.atualizar(aluno);
    }

    public List<Aluno> listarTodosAlunos() {
        return alunoDAO.listarTodos();
    }

    public void removerAluno(int id) {
        alunoDAO.remover(id);
    }

    public void buscarAlunoPorNome(String nome) {
        alunoDAO.buscarPorNome(nome);
    }
}
