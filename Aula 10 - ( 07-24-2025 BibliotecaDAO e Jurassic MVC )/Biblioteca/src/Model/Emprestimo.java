package Model;

public class Emprestimo {
    private int fk_id_aluno;
    private int fk_id_livro;
    private String dataEmprestimo;
    private String dataDevolucao;

    public Emprestimo(int fk_id_aluno, int fk_id_livro, String dataEmprestimo, String dataDevolucao) {
        this.fk_id_aluno = fk_id_aluno;
        this.fk_id_livro = fk_id_livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public int getFk_id_aluno() {
        return fk_id_aluno;
    }

    public void setFk_id_aluno(int fk_id_aluno) {
        this.fk_id_aluno = fk_id_aluno;
    }

    public int getFk_id_livro() {
        return fk_id_livro;
    }

    public void setFk_id_livro(int fk_id_livro) {
        this.fk_id_livro = fk_id_livro;
    }

    public String getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(String dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(String dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
