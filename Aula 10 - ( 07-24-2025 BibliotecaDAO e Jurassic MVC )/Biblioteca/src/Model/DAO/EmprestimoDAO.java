package Model.DAO;

import Conexao.ConexaoBiblioteca;
import Model.Emprestimo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Conexao.ConexaoBiblioteca.fecharConexao;

public class EmprestimoDAO {
    public static void setemprestimo(Emprestimo emprestimo) {
        String sql = "INSERT INTO emprestimo (fk_id_livro, fk_id_aluno, data_emprestimo, data_devolucao) VALUES (?, ?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBiblioteca.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, emprestimo.getFk_id_livro());
                stmt.setInt(2, emprestimo.getFk_id_aluno());
                stmt.setString(3, emprestimo.getDataEmprestimo());
                stmt.setString(4, emprestimo.getDataDevolucao());
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Emprestimo foi registrado com sucesso!");
                }
            }
        } catch (SQLException error) {
            System.err.println("Erro ao inserir o emprestimo: " + error.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) fecharConexao(conexao);
            } catch (SQLException error) {
                System.err.println("Erro ao fechar conexao: " + error.getMessage());
            }
        }
    }

    public List<Emprestimo> listarEmprestimo() {
        List<Emprestimo> emprestimos = new ArrayList<>();
        String sql = "SELECT id_emprestimo, fk_id_livro, fk_id_aluno, data_emprestimo, data_devolucao FROM emprestimo ORDER BY id_emprestimo";
        try (Connection conn = ConexaoBiblioteca.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Emprestimo emprestimo = new Emprestimo(
                        rs.getInt("id_emprestimo"),
                        rs.getInt("fk_id_livro"),
                        rs.getInt("fk_id_aluno"),
                        rs.getString("data_emprestimo"),
                        rs.getString("data_devolucao")
                );
                emprestimos.add(emprestimo);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar emprestimos: " + e.getMessage());
        }
        return emprestimos;
    }

    public Emprestimo buscarPorId(int idEmprestimo) {
        String sql = "SELECT id_emprestimo, fk_id_livro, fk_id_aluno, data_emprestimo, data_devolucao FROM emprestimo WHERE id_emprestimo = ?";
        try (Connection conn = ConexaoBiblioteca.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEmprestimo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Emprestimo(
                            rs.getInt("id_emprestimo"),
                            rs.getInt("fk_id_livro"),
                            rs.getInt("fk_id_aluno"),
                            rs.getString("data_emprestimo"),
                            rs.getString("data_devolucao")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar dinossauro por ID: " + e.getMessage());
        }
        return null;
    }

    public void removeremprestimo(int fk_id_livro, int fk_id_aluno) {
        String sql = "DELETE FROM emprestimo WHERE fk_id_livro = ? AND fk_id_aluno = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = ConexaoBiblioteca.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, fk_id_livro);
                stmt.setInt(2, fk_id_aluno);
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("emprestimo com ID " + fk_id_livro + " deletado com sucesso!");
                } else {
                    System.out.println("Nenhum emprestimo encontrado com ID " + fk_id_livro);
                }
            }
        } catch (SQLException error) {
            System.err.println("Erro ao inserir o emprestimo: " + error.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) fecharConexao(conexao);
            } catch (SQLException error) {
                System.out.println("Erro ao fechar conexao: " + error.getMessage());
            }
        }
    }
}
