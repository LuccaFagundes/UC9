package Model.DAO;

import Model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Conexao.ConexaoBiblioteca;

import static Conexao.ConexaoBiblioteca.fecharConexao;

public class LivroDAO {

    public static void setLivro(Livro livro) {
        String sql = "INSERT INTO livro (titulo, autor, genero, isbn) VALUES (?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBiblioteca.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, livro.getTitulo());
                stmt.setString(2, livro.getAutor());
                stmt.setString(3, livro.getGenero());
                stmt.setString(4, livro.getIsbn());
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Livro " + livro.getTitulo() + " foi adicionado com sucesso!");
                }
            }
        } catch (SQLException error) {
            System.err.println("Erro ao inserir o livro: " + error.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) fecharConexao(conexao);
            } catch (SQLException error) {
                System.err.println("Erro ao fechar conexao: " + error.getMessage());
            }
        }
    }

    public List<Livro> getLivro() {
        List<Livro> livros = new ArrayList<>(); //lista para armazenar os objetos livro
        String sql = "SELECT * FROM livro ORDER BY id_livro";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = ConexaoBiblioteca.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                rs = stmt.executeQuery();
                System.out.println("\n--- livros cadastrados no BD ---");
                boolean encontroulivro = false;
                while (rs.next()) {
                    encontroulivro = true;
                    int id = rs.getInt("id_livro");
                    String titulo = rs.getString("titulo");
                    String autor = rs.getString("autor");
                    String genero = rs.getString("genero");
                    String isbn = rs.getString("isbn");
                    livros.add(new Livro(id, titulo, autor, genero, isbn));
                }
            }
        } catch (SQLException error) {
            System.out.println("Erro ao conectar com o banco de dados: " + error.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) fecharConexao(conexao);
            } catch (SQLException error) {
                System.err.println("Erro ao fechar recursos após pesquisa: " + error.getMessage());
            }
        }
        return livros;
    }

    public Livro buscarPorId(int id) {
        String sql = "SELECT id_livro, titulo, autor, genero, isbn FROM livro WHERE id_livro = ? ";
        try (Connection conn = ConexaoBiblioteca.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Livro(
                            rs.getInt("id_livro"),
                            rs.getString("titulo"),
                            rs.getString("autor"),
                            rs.getString("genero"),
                            rs.getString("isbn")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno por ID: " + e.getMessage());
            ;
        }
        return null;
    }

    public void atualizarlivro(Livro livro) {
        String sql = "UPDATE livro SET titulo = ?, autor = ?, genero = ?, isbn = ? WHERE id_livro = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = ConexaoBiblioteca.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, livro.getTitulo());
                stmt.setString(2, livro.getAutor());
                stmt.setString(3, livro.getGenero());
                stmt.setString(4, livro.getIsbn());
                stmt.setInt(5, livro.getId());
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Livro com ID " + livro.getId() + " atualizado com sucesso!");
                } else {
                    System.out.println("Nenhum livro com o ID " + livro.getId() + " foi encontrado");
                }
            }
        } catch (SQLException error) {
            System.err.println("Erro ao inserir o livro: " + error.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) fecharConexao(conexao);
            } catch (SQLException error) {
                System.err.println("Erro ao fechar conexao: " + error.getMessage());
            }
        }
    }

    public void removerLivro(int idlivro) {
        String sql = "DELETE FROM livro WHERE id_livro = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = ConexaoBiblioteca.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, idlivro);
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("livro com ID " + idlivro + " deletado com sucesso!");
                } else {
                    System.out.println("Nenhum livro encontrado com ID " + idlivro);
                }
            }
        } catch (SQLException error) {
            System.err.println("Erro ao inserir o livro: " + error.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) fecharConexao(conexao);
            } catch (SQLException error) {
                System.out.println("Erro ao fechar conexao: " + error.getMessage());
            }
        }
    }

    public List<Livro> buscarPorTitulo(String tituloBusca) {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT id_livro, titulo, autor, genero, isbn FROM livro WHERE titulo ILIKE ? ORDER BY titulo DESC";
        try (Connection conn = ConexaoBiblioteca.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + tituloBusca + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Livro livro = new Livro(
                            rs.getInt("id_livro"),
                            rs.getString("titulo"),
                            rs.getString("autor"),
                            rs.getString("genero"),
                            rs.getString("isbn")
                    );
                    livros.add(livro);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar livros por nome: " + e.getMessage());
        }
        return livros;
    }
}
