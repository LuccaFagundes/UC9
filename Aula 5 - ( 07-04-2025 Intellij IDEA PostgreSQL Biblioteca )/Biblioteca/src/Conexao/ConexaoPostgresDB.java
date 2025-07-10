package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConexaoPostgresDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/biblioteca";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";

    public static Connection conectar() {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco: " + e.getMessage());
        }
        return conexao;
    }

    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão com o banco de dados fechada!");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco: " + e.getMessage());
            }
        }
    }

    public static void setAluno(String nome, int idade, String telefone) {
        String sql = "INSERT INTO primary_user (nome_aluno,idade_aluno,contato_aluno) VALUES (?,?,?)";
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = ConexaoPostgresDB.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, nome);
                stmt.setInt(2, idade);
                stmt.setString(3, telefone);
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Aluno " + nome + " inserido no BD com sucesso!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir aluno no PostgreSQL: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) fecharConexao(conexao);
            } catch (SQLException e) {
                System.err.println("Erro ao fechar recursos após inserção " + e.getMessage());
            }
        }
    }

    public static void getAlunos() {
        String sql = "SELECT * FROM aluno ORDER BY id_aluno";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = ConexaoPostgresDB.conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                rs = stmt.executeQuery();
                System.out.println("\n--- Aluno(s) Cadastrados no BD ---");
                boolean encontrouAluno = false;
                while (rs.next()) {
                    encontrouAluno = true;
                    int id = rs.getInt("id_aluno");
                    String nome = rs.getString("nome_aluno");
                    int idade = rs.getInt("idade_aluno");
                    String telefone = rs.getString("contato_aluno");
                    System.out.println("ID: " + id + ", Nome: " + nome + ", Idade: " + idade + ", Telefone: " + telefone);
                }
                if (!encontrouAluno) {
                    System.out.println("Nenhum aluno encontrado!");
                }
                System.out.println("------------------------------\n");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar aluno(s): " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conexao != null) fecharConexao(conexao);

            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos após consulta: " + e.getMessage());
            }
        }
    }

    public static void updateAluno(int idAluno, String nome, int idade, String telefone) {
        String sql = "UPDATE aluno SET nome_aluno = ?, idade_aluno = ?, contato_aluno = ? WHERE id_aluno = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;
        try {
            conexao = conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setString(1, nome);
                stmt.setInt(2, idade);
                stmt.setString(3, telefone);
                stmt.setInt(4, idAluno);
                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Aluno com ID " + idAluno + " atualizado com sucesso!");
                } else {
                    System.out.println("Nenhum aluno encontrado com ID " + idAluno + " para atualização.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar aluno: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) fecharConexao(conexao);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos após atualização " + e.getMessage());
            }
        }
    }

    public static void deleteAluno(int idAluno) {
        String sql = "DELETE FROM aluno WHERE id_aluno = ?";
        Connection conexao = null;
        PreparedStatement stmt = null;

        try {
            conexao = conectar();
            if (conexao != null) {
                stmt = conexao.prepareStatement(sql);
                stmt.setInt(1, idAluno);

                int linhasAfetadas = stmt.executeUpdate();
                if (linhasAfetadas > 0) {
                    System.out.println("Aluno com ID " + idAluno + " removido com sucesso!");
                } else {
                    System.out.println("Nenhum aluno encontrado com ID " + idAluno + " para remoção.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover aluno: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conexao != null) fecharConexao(conexao);
            } catch (SQLException e) {
                System.out.println("Erro ao fechar recursos após remoção: " + e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        Connection testeConexao = ConexaoPostgresDB.conectar();
        if (testeConexao != null) {
            ConexaoPostgresDB.fecharConexao(testeConexao);
        }



        getAlunos();
    }
}
