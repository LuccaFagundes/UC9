package DAO;

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
        String sql = "INSERT INTO emprestimo (data_emprestimo, data_devolucao) VALUES (?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBiblioteca.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, emprestimo.getDataEmprestimo());
                stmt.setString(2, emprestimo.getDataDevolucao());
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

    public List<Emprestimo> getemprestimos() {
        String sql = "SELECT * FROM emprestimo ORDER BY data_devolucao";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Emprestimo> emprestimos = new ArrayList<>(); //lista para armazenar os objetos emprestimo
        try {
            conexao = ConexaoBiblioteca.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                rs = stmt.executeQuery();
                System.out.println("\n--- Emprestimos cadastrados no BD ---");
                boolean encontrouEmprestimo = false;
                while (rs.next()) {
                    encontrouEmprestimo = true;
                    int id_livro = rs.getInt("fk_id_livro");
                    int id_aluno = rs.getInt("fk_id_aluno");
                    String data_emprestimo = rs.getString("data_emprestimo");
                    String data_devolucao = rs.getString("data_devolucao");
                    emprestimos.add(new Emprestimo(id_livro, id_aluno, data_emprestimo, data_devolucao));
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
        return emprestimos;
    }

    public void atualizaremprestimo(Emprestimo emprestimo) {
        String sql = "UPDATE emprestimo SET data_emprestimo = ? WHERE fk_id_livro = ? AND fk_id_aluno = ? = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = ConexaoBiblioteca.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, emprestimo.getDataEmprestimo());
                stmt.setInt(2, emprestimo.getFk_id_aluno());
                stmt.setInt(3, emprestimo.getFk_id_livro());
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Emprestimo atualizado com sucesso!");
                } else {
                    System.out.println("Nenhum emprestimo foi encontrado");
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
