package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;
import model.JavaBeans;

// TODO: Auto-generated Javadoc
/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = { "/Controller", "/main", "/insert", "/select", "/update", "/delete", "/report" })
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The Contato. */
	JavaBeans Contato = new JavaBeans();

	/**
	 * Instantiates a new controller.
	 */
	public Controller() {
		super();

	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);
		if (action.equals("/main")) {
			Contatos(request, response);
		} else if (action.equals("/insert")) {
			novoContato(request, response);
		} else if (action.equals("/select")) {
			listarContato(request, response);
		} else if (action.equals("/update")) {
			editarContato(request, response);
		} else if (action.equals("/delete")) {
			removerContato(request, response);
		} else if (action.equals("/report")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}

	}

	// Listar Contato

	/**
	 * Contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void Contatos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Criando um Objeto que vai receber os dados JavaBeans
		ArrayList<JavaBeans> lista = dao.listarContato();
		// encaminhar a lista ao documento agendaTelefonica.jsp
		request.setAttribute("contato", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agendaTelefonica.jsp");
		rd.forward(request, response);

	}

	// Novo Contato

	/**
	 * Novo contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// setar as variaveisJavabeanas
		Contato.setNome(request.getParameter("nome"));
		Contato.setIdade(request.getParameter("idade"));
		Contato.setTelefone(request.getParameter("telefone"));
		// invocar o método inserirContato passando o objeto contato
		dao.inserirContato(Contato);
		// redirecionar para o documento agenda.jsp
		response.sendRedirect("main");
	}

	/**
	 * Listar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Alterar contato
	protected void listarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebe o id do contato que será editado
		Contato.setId(request.getParameter("id"));
		// Executar o metodo selecionar contato (DAO)
		dao.selecionarContato(Contato);
		// setar os atributos do formulario com o conteudo JavaBeans
		request.setAttribute("id", Contato.getId());
		request.setAttribute("nome", Contato.getNome());
		request.setAttribute("idade", Contato.getIdade());
		request.setAttribute("telefone", Contato.getTelefone());
		// encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);

	}

	/**
	 * Editar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// setar as variáveis JavBeans

		Contato.setId(request.getParameter("id"));
		Contato.setNome(request.getParameter("nome"));
		Contato.setIdade(request.getParameter("idade"));
		Contato.setTelefone(request.getParameter("telefone"));

		// Executar o método alterar contato
		dao.alterarContato(Contato);
		// redireciona para o documento agendaTelefonica.jsp(atualizando as alterações)
		response.sendRedirect("main");

	}

	/**
	 * Remover contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	// Excluir contato
	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Recebimento do id do contato aser excluido(validador.js)
		Contato.setId(request.getParameter("id"));
		// Executar o método deletarContato (DAO) passando o objeto contato
		dao.deletarContato(Contato);
		// redireciona para o documento agendaTelefonica.jsp(atualizando as alterações)
		response.sendRedirect("main");

	}

	// Gerar Log (Relatório) em PDF

	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Document documento = new Document();
		try {
			// tipo de conteudo
			response.setContentType("apllication/pdf");
			// nome do documento
			response.addHeader("content-Disposition", "inline;filename=" + "contato.pdf");
			// Criar o documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			// abrir o documento para gerar o conteudo
			documento.open();
			documento.add(new Paragraph("Lista de contato:"));
			documento.add(new Paragraph(" ")); // aqui quebra uma linha no documento pdf
			// Criar uma tabela
			PdfPTable tabela = new PdfPTable(3);
			// cebeçalho
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Idade"));
			PdfPCell col3 = new PdfPCell(new Paragraph("Telefone"));
			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);
			// Popular a tabela,Reutilizando o metodo para trazer a listagem de tds os
			// contatos cadastrado no banco de dados
			ArrayList<JavaBeans> lista = dao.listarContato();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getIdade());
				tabela.addCell(lista.get(i).getTelefone());
			}
			documento.add(tabela);
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}

}
