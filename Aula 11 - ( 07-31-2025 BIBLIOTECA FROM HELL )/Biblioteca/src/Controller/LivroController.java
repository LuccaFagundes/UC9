package Controller;

import Model.DAO.LivroDAO;
import Model.Livro;

import java.time.LocalDate;
import java.util.List;

public class LivroController {
    private LivroDAO livroDAO;

    public LivroController() {
        this.livroDAO = new LivroDAO();
    }

    public void cadastrarLivro(String titulo, String autor, String genero, String isbn) throws Exception {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new Exception("O titulo do livro é obrigatório.");
        }
        if (autor == null || autor.trim().isEmpty()) {
            throw new Exception("O autor do livro é obrigatório.");
        }
        if (genero == null || genero.trim().isEmpty()) {
            throw new Exception("O genero do livro é obrigatório");
        }
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new Exception("O isbn do livro é obrigatório");
        }
        Livro livro = new Livro(titulo, autor, genero, isbn);
        livroDAO.setLivro(livro);
    }

    public Livro buscarLivroPorID(int id) {
        return livroDAO.buscarPorId(id);
    }

    public void atualizarLivro(int id, String titulo, String autor, String genero, String isbn) throws Exception {
        if(titulo == null || titulo.trim().isEmpty() || autor == null || autor.trim().isEmpty() || genero == null || genero.trim().isEmpty() || isbn == null || isbn.trim().isEmpty()) {
            throw new Exception("Todos os campos são necessários serem completos.");
        }
        Livro livro =  new Livro(titulo, autor, genero, isbn);
        livroDAO.atualizarlivro(livro);
    }

    public List<Livro> listarLivros() {
        return livroDAO.getLivro();
    }

    public void removerLivro(int id) {
        livroDAO.removerlivro(id);
    }

    public List<Livro> buscarLivroPorTitulo(String titulo) {
        return livroDAO.buscarPorTitulo(titulo);
    }

}
