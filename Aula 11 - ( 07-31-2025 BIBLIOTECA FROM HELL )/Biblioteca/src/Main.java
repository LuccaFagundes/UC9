import Controller.AlunoController;
import Controller.EmprestimoController;
import Controller.LivroController;
import View.Cadastro.CadastroAlunoForm;
import View.Cadastro.CadastroEmprestimoForm;
import View.Cadastro.CadastroLivroForm;
import View.List.ListaAlunoForm;
import View.List.ListaEmprestimoForm;
import View.List.ListaLivroForm;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends JFrame {

    private JDesktopPane desktopPane;
    private AlunoController alunoController;
    private LivroController livroController;
    private EmprestimoController emprestimoController;

    public  Main() {
        super("Sistema de Gerenciamento Bibliotecario");
        this.alunoController = new AlunoController();
        this.livroController = new LivroController();
        this.emprestimoController = new EmprestimoController();

        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();
        setContentPane(desktopPane);

        createMenuBar();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // --- Menu Alunos (usará alunoController) ---
        JMenu menuAlunos = new JMenu("Alunos");
        JMenuItem itemCadastrarAluno = new JMenuItem("Cadastrar Aluno");
        JMenuItem itemListarAlunos = new JMenuItem("Listar Alunos");

        JMenu menuLivros = new  JMenu("Livros");
        JMenuItem itemCadastrarLivro = new JMenuItem("Cadastrar Livro");
        JMenuItem itemListarLivros = new JMenuItem("Listar Livros");

        JMenu menuEmprestimos = new  JMenu("Emprestimos");
        JMenuItem itemCadastrarEmprestimo = new JMenuItem("Cadastrar Emprestimo");
        JMenuItem itemListarEmprestimos = new JMenuItem("Listar Emprestimos");

        itemCadastrarAluno.addActionListener(e -> openAlunoForm(null));
        itemCadastrarLivro.addActionListener(e -> openLivroForm(null));
        itemCadastrarEmprestimo.addActionListener(e -> openEmprestimoForm(null));
        itemListarAlunos.addActionListener(e -> openListaAlunosForm());
        itemListarLivros.addActionListener(e -> openListaLivrosForm());
        itemListarEmprestimos.addActionListener(e -> openListaEmprestimosForm());

        menuAlunos.add(itemCadastrarAluno);
        menuAlunos.add(itemListarAlunos);
        menuLivros.add(itemCadastrarLivro);
        menuLivros.add(itemListarLivros);
        menuEmprestimos.add(itemCadastrarEmprestimo);
        menuEmprestimos.add(itemListarEmprestimos);

        menuBar.add(menuAlunos);
        menuBar.add(menuLivros);
        menuBar.add(menuEmprestimos);

        // --- Menu Sair (Existente) ---
        JMenu menuSair = new JMenu("Sair");
        JMenuItem itemSair = new JMenuItem("Sair do Sistema");
        itemSair.addActionListener(e -> System.exit(0));

        menuSair.add(itemSair);
        menuBar.add(menuSair);

        setJMenuBar(menuBar);
    }

    private void openAlunoForm(Integer idAluno) {
        CadastroAlunoForm alunoForm = new CadastroAlunoForm(alunoController, idAluno); // Passa o alunoController
        desktopPane.add(alunoForm);
        alunoForm.setVisible(true);
        alunoForm.toFront();
    }

    public void openLivroForm(Integer idLivro) {
        CadastroLivroForm livroForm = new CadastroLivroForm(livroController, idLivro);
        desktopPane.add(livroForm);
        livroForm.setVisible(true);
        livroForm.toFront();
    }

    public void openEmprestimoForm(Integer idEmprestimo) {
        CadastroEmprestimoForm emprestimoForm = new CadastroEmprestimoForm(emprestimoController,idEmprestimo);
        desktopPane.add(emprestimoForm);
        emprestimoForm.setVisible(true);
        emprestimoForm.toFront();
    }

    private void openListaAlunosForm() {
        ListaAlunoForm listaAluno = new ListaAlunoForm(alunoController); // Passa o alunoController
        desktopPane.add(listaAluno);
        listaAluno.setVisible(true);
        listaAluno.toFront();
    }

    private void openListaLivrosForm() {
        ListaLivroForm listaLivro = new ListaLivroForm(livroController);
        desktopPane.add(listaLivro);
        listaLivro.setVisible(true);
        listaLivro.toFront();
    }

    private void openListaEmprestimosForm() {
        ListaEmprestimoForm listaEmprestimo = new ListaEmprestimoForm(emprestimoController);
        desktopPane.add(listaEmprestimo);
        listaEmprestimo.setVisible(true);
        listaEmprestimo.toFront();
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}