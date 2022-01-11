package com.weather.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weather.model.Usuario;

import bean.UsuarioBean;

/**
 * Servlet implementation class AgendaController
 */
@WebServlet(urlPatterns = { "/WeatherController", "/home", 
		"/validalogin", "/novousuario", "/selecionausuario", 
		"/editausuario", "/deletar" })
public class WeatherController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeatherController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String acao = request.getServletPath();
		System.out.println(acao);

		if (acao.equals("/home")) {
			usuarios(request, response);
		} else if (acao.equals("/validalogin")) {
			validaLogin(request, response);
		} else if (acao.equals("/novousuario")) {
			novoUsuario(request, response);
		} else if (acao.equals("/selecionausuario")) {
			selecionaUsuario(request, response);
		} else if (acao.equals("/editausuario")) {
			editaUsuario(request, response);
		} else if (acao.equals("/deletar")) {
			deletaUsuario(request, response);
		} else {
			response.sendRedirect("index.html");
		}

		// ConnectionFactory cf = new ConnectionFactory();
		// cf.testeConnection();
	}
	
	protected void validaLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		/*
		Contato contato = new Contato();
		
		ContatoDAO contatoDAO = new ContatoDAO();
		
		contato = contatoDAO.getContato(id);
		
		contatoDAO.fechar();
		*/
		
		UsuarioBean usuarioBean = new UsuarioBean();
		Usuario usuario = new Usuario();
		usuario = usuarioBean.validaUsuario(email);
		
		if (usuario != null) {
			if (usuario.getSenha().equals(senha)) {
				System.out.println(usuario.getNome());
				
				request.setAttribute("usuario", usuario);
				
				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				
				rd.forward(request, response);
			} else {
				response.sendRedirect("index.html");
			}
		} else {
			response.sendRedirect("index.html");
		}
		
	
		
	} 

	protected void usuarios(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//response.sendRedirect("agenda.jsp");
		/*
		ContatoDAO contatoDAO = new ContatoDAO();
		
		ArrayList<Contato> contatos = 
				contatoDAO.getContatos();
		
		contatoDAO.fechar();
		*/
		
		UsuarioBean usuarioBean = new UsuarioBean();
		ArrayList<Usuario> usuarios = usuarioBean.getUsuarios();
		
		//encaminhar para JSP
		
		request.setAttribute("usuarios", usuarios);
		
		//despachante vai levar os dados para uma jsp de maneira dinamica
		
		RequestDispatcher rd = 
				request.getRequestDispatcher("home.jsp");
		
		rd.forward(request, response);
			

	}

	protected void novoUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
		Contato contato = new Contato();
		
		//1. recuperando parametros do formulario
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setEmail(request.getParameter("email"));
		
		ContatoDAO contatoDAO = new ContatoDAO();
		
		contatoDAO.adiciona(contato);
		
		System.out.println("Contato foi gravado!");
		
		contatoDAO.fechar();
		response.sendRedirect("home");
		*/
		
		UsuarioBean usuarioBean = new UsuarioBean();
		usuarioBean.novoUsuario(request, response);

	}
	
	protected void selecionaUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		/*
		Contato contato = new Contato();
		
		ContatoDAO contatoDAO = new ContatoDAO();
		
		contato = contatoDAO.getContato(id);
		
		contatoDAO.fechar();
		*/
		
		UsuarioBean usuarioBean = new UsuarioBean();
		Usuario usuario = new Usuario();
		usuario = usuarioBean.selecionaUsuario(id);
		
		
		System.out.println(usuario.getNome());
		
		request.setAttribute("usuario", usuario);
		
		RequestDispatcher rd = request.getRequestDispatcher("editarUsuario.jsp");
		
		rd.forward(request, response);
		
	} 
	
	protected void editaUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		Contato contato = new Contato();
		
		int id = Integer.parseInt(request.getParameter("id"));
		contato.setId(id);
		
		contato.setNome(request.getParameter("nome"));
		contato.setTelefone(request.getParameter("telefone"));
		contato.setEmail(request.getParameter("email"));
		
		ContatoDAO contatoDAO = new ContatoDAO();
		
		contatoDAO.atualiza(contato);
		contatoDAO.fechar();
		
		response.sendRedirect("home");
		*/
		
		UsuarioBean usuarioBean = new UsuarioBean();
		usuarioBean.editaUsuario(request, response);
		
	}
	
	protected void deletaUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*
		Contato contato = new Contato();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		contato.setId(id);
		
		ContatoDAO contatoDAO = new ContatoDAO();
		
		contatoDAO.remove(contato);
		
		contatoDAO.fechar();
		
		response.sendRedirect("home");
		*/
		
		UsuarioBean usuarioBean = new UsuarioBean();
		usuarioBean.deletaUsuario(request, response);
		
		
		
	}
	
	
	

}