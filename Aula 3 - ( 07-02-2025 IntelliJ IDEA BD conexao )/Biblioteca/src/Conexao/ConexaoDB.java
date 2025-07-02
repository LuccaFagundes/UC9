package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection conectar() {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (SQLException e) {
            System.err.println("Errp ao conectar com o bd: " + e.getMessage());
        } return conexao;
    }

    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão com o bd fechada!");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o bd: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Connection testeConexao = ConexaoDB.conectar();
        if (testeConexao != null) {
                ConexaoDB.fecharConexao(testeConexao);
        }
    }
}
