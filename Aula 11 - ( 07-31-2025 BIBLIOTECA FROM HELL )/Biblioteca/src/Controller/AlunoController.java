package Controller;

import Model.Aluno;
import Model.DAO.AlunoDAO;

import java.time.LocalDate;
import java.util.List;

public class AlunoController {
    private AlunoDAO alunoDAO;

    public AlunoController(){
        this.alunoDAO = new AlunoDAO();
    }

    public AlunoDAO getAlunoDAO() {
        return alunoDAO = new AlunoDAO();
    }

    public void cadastrarAluno(String nome, int idade, String contato) throws Exception {

        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("O nome é obrigatório ao cadastrar um aluno.");
        }
        if (idade < 0 || idade > 120){
            throw new Exception("A idade inserida não está correta ou está nula");
        }
        if (contato == null || contato.trim().isEmpty() || !contato.contains("519") || contato.length() > 11 ) {
            throw new Exception("O contato inserido não está correto ou está nulo");
        }

        Aluno aluno = new Aluno(nome, idade, contato);
        alunoDAO.setAluno(aluno);
    }

    public Aluno buscarAlunoPorID(int id) {
        return alunoDAO.buscarPorId(id);
    }

    public void atualizarAluno(int id, String nome, String idade, String contato) throws Exception {

        if (nome == null || nome.trim().isEmpty()) {
            throw new Exception("O campo 'Nome' não pode estar em branco.");
        }

        if (idade == null || idade.trim().isEmpty()) {
            throw new Exception("O campo 'Idade' não pode estar em branco.");
        }

        if (contato == null || contato.trim().isEmpty()) {
            throw new Exception("O campo 'Contato' não pode estar em branco.");
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

    public List<Aluno> buscarAlunoPorNome(String nome) {
        return alunoDAO.buscarPorNome(nome);
    }
}
