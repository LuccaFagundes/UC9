package Model.DAO;

import Model.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Conexao.ConexaoBiblioteca.fecharConexao;

import Conexao.ConexaoBiblioteca;


public class AlunoDAO {

    public static void setAluno(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, idade, contato ) VALUES (?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = ConexaoBiblioteca.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, aluno.getNome());
                stmt.setInt(2, aluno.getIdade());
                stmt.setString(3, aluno.getContato());
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Aluno " + aluno.getNome() + " foi adicionado com sucesso!");
                }
            }
        } catch (SQLException error) {
            System.err.println("Erro ao inserir o aluno: " + error.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) fecharConexao(conexao);
            } catch (SQLException error) {
                System.err.println("Erro ao fechar conexao: " + error.getMessage());
            }
        }
    }

    public List<Aluno> listarTodos() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT id_aluno, nome, idade, contato FROM aluno ORDER BY nome";
        try (Connection conn = ConexaoBiblioteca.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Aluno aluno = new Aluno(
                        rs.getInt("id_aluno"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getString("contato")
                );
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar alunos: " + e.getMessage());
        }
        return alunos;
    }

    public Aluno buscarPorId(int id) {
        String sql = "SELECT id_aluno, nome, idade, contato FROM aluno WHERE id_aluno = ? ";
        try (Connection conn = ConexaoBiblioteca.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Aluno(
                            rs.getInt("id_aluno"),
                            rs.getString("nome"),
                            rs.getInt("idade"),
                            rs.getString("contato")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno por ID: " + e.getMessage());
            ;
        }
        return null;
    }


    public void atualizar(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?, idade = ?, contato = ? WHERE id_aluno = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = ConexaoBiblioteca.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, aluno.getNome());
                stmt.setInt(2, aluno.getIdade());
                stmt.setString(3, aluno.getContato());
                stmt.setInt(4, aluno.getId());
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Aluno com ID " + aluno.getId() + " atualizado com sucesso!");
                } else {
                    System.out.println("Nenhum aluno com o ID " + aluno.getId() + " foi encontrado");
                }
            }
        } catch (SQLException error) {
            System.err.println("Erro ao inserir o aluno: " + error.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) fecharConexao(conexao);
            } catch (SQLException error) {
                System.err.println("Erro ao fechar conexao: " + error.getMessage());
            }
        }
    }

    public void remover(int idAluno) {
        String sql = "DELETE FROM aluno WHERE id_aluno = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = ConexaoBiblioteca.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, idAluno);
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Aluno com ID " + idAluno + " deletado com sucesso!");
                } else {
                    System.out.println("Nenhum aluno encontrado com ID " + idAluno);
                }
            }
        } catch (SQLException error) {
            System.err.println("Erro ao inserir o aluno: " + error.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) fecharConexao(conexao);
            } catch (SQLException error) {
                System.out.println("Erro ao fechar conexao: " + error.getMessage());
            }
        }
    }

    public List<Aluno> buscarPorNome(String nomeBusca) {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT id_aluno, nome, idade, contato FROM aluno WHERE nome ILIKE ? ORDER BY nome";
        try (Connection conn = ConexaoBiblioteca.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + nomeBusca + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Aluno aluno = new Aluno(
                            rs.getInt("id_aluno"),
                            rs.getString("nome"),
                            rs.getInt("idade"),
                            rs.getString("contato")
                    );
                    alunos.add(aluno);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar alunos por nome: " + e.getMessage());
        }
        return alunos;
    }
}


















