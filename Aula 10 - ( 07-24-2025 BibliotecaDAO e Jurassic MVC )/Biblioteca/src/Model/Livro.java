package Model;

public class Livro {
    int id;
    String titulo;
    String autor;
    String genero;
    String isbn;

    public Livro(int idLivro, String tituloLivro, String autorLivro, String generoLivro, String isbn) {
        this.id = idLivro;
        this.titulo = tituloLivro;
        this.autor = autorLivro;
        this.genero = generoLivro;
        this.isbn = isbn;
    }

    public Livro(String tituloLivro, String autorLivro, String generoLivro, String isbn) {
        this.titulo = tituloLivro;
        this.autor = autorLivro;
        this.genero = generoLivro;
        this.isbn = isbn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
