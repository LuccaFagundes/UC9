package Model;

public class Aluno {
    private int id;
    private String nome;
    private int idade;
    private String contato;

    public Aluno(int id, String nome, int idade, String contato) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.contato = contato;
    }

    public Aluno(String contato, int idade, String nome) {
        this.contato = contato;
        this.idade = idade;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }
}
