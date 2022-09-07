package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.jsp.tagext.TryCatchFinally;

import org.eclipse.jdt.internal.compiler.ast.TryStatement;
import org.eclipse.jdt.internal.compiler.flow.TryFlowContext;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {
	
	/**  Modulo de conexão *. */
	// parametros de conexão
	private String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private String url = "jdbc:mysql://127.0.0.1:3306/agenda?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private String user = "root";
	
	/** The password. */
	private String password = "root";
	// método de conexão

	
	/**
	 * Conectar.
	 *
	 * @return the connection
	 */
	private Connection conectar() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);

			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;

		}
	}

	/**
	 * Inserir contato.
	 *
	 * @param contato the contato
	 */
	// **CRUD CREATE **/
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contato (nome,idade, telefone) values (?,?,?)";
		try {
			// abrir a conexao
			Connection con = conectar();
			// preparar a query para execução no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			// Substituir os parametros (?) pelo conteudo das variaveis JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getIdade());
			pst.setString(3, contato.getTelefone());
			// Executar a query
			pst.executeUpdate();
			// Encerrar a conexao com o banco
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 *  CRUD CREATE *.
	 *
	 * @return the array list
	 */
	public ArrayList<JavaBeans> listarContato(){
		//Criando um objeto para acessar a classe JavaBeans
		ArrayList<JavaBeans> contato = new ArrayList<>();
		String read = "select * from contato order by nome";
		try {
			Connection con = conectar ();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			//o laço abaixo será executado enquanto tiver contatos
			while (rs.next()) { //usado para recuperar os contatos do banco de dados
				//variaveis de apoio que recebem os dados do banco
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String idade = rs.getString(3);
				String telefone = rs.getString(4);
				//populando o ArrayList
				contato.add(new JavaBeans(id,nome,idade,telefone));	
			}
			con.close();
			return contato;
		} catch (Exception e) {
			return null;
		}
		
	}
	
	/**
	 * CRUD UPDATE*.
	 *
	 * @param contato the contato
	 */
	//selecionar o contato
	public void selecionarContato(JavaBeans contato) {
		String read2 = "select * from contato where id = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, contato.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				//setar as variaveis JavaBeans
				contato.setId(rs.getString(1));
				contato.setNome(rs.getString(2));
				contato.setIdade(rs.getString(3));
				contato.setTelefone(rs.getString(4));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * Alterar contato.
	 *
	 * @param Contato the contato
	 */
	//Alterar o contato
	public void alterarContato(JavaBeans Contato) {
		String update = "update Contato set nome=?,idade=?,telefone=? where id=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, Contato.getNome());
			pst.setString(2, Contato.getIdade());
			pst.setString(3, Contato.getTelefone());
			pst.setString(4, Contato.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			
		}
	}
	
	/**
	 * CRUD DELET *.
	 *
	 * @param contato the contato
	 */
	public void deletarContato(JavaBeans contato) {
		String delete ="delete from contato where id =?";
		try {
			
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, contato.getId()); //setString 1 substitui a ? pela id do contato
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
