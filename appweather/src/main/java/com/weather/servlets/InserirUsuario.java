package com.weather.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weather.dao.UsuarioDAO;
import com.weather.model.Usuario;

/**
 * Servlet implementation class InserirUsuario
 */
@WebServlet("/inserirusuario")
public class InserirUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InserirUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//1. Instanciar e preencher os dados de contato
		
		Usuario usuario = new Usuario();
		
		usuario.setNome("Tyler Durden");
		usuario.setEmail("harrison@gmail.com");
		usuario.setSenha("h123456");
		usuario.setCidade("Natal");
		
//		//2. Instancia do objeto DAO
//		//2.1 instancia de DAO já estabelece a comunicação
		UsuarioDAO dao = new UsuarioDAO();
		
//		//2.2 método elegante de inserir dado
		dao.adiciona(usuario);
		
		System.out.println("Usuário cadastrado via DAO!");
		
		
		ArrayList<Usuario> usuarios = dao.getUsuarios();
		
		for(Usuario usuarioResultado : usuarios) {
			System.out.println("Nome: "+usuarioResultado.getNome());
			System.out.println("Email: "+usuarioResultado.getEmail());
		}
		
		Usuario usuarioParaAtualizar = usuarios.get(3);
		
		usuarioParaAtualizar.setNome("Harrison");
		
		//dao.altera(usuarioParaAtualizar);
		
		
		Usuario usuarioParaRemover = usuarios.get(2);
		
		dao.remove(usuarioParaRemover);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
