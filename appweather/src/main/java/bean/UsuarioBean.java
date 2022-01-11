package bean;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.weather.dao.UsuarioDAO;
import com.weather.model.Usuario;

public class UsuarioBean {

	private Usuario usuario;
	private ArrayList<Usuario> usuarios;
	private UsuarioDAO usuarioDAO;

	public void deletaUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {

		usuario = new Usuario();

		int id = Integer.parseInt(request.getParameter("id"));

		usuario.setId(id);

		usuarioDAO = new UsuarioDAO();

		usuarioDAO.remove(usuario);

		usuarioDAO.fechar();

		response.sendRedirect("home");
	}

	public Usuario selecionaUsuario(int id) {

		usuario = new Usuario();

		usuarioDAO = new UsuarioDAO();

		usuario = usuarioDAO.getUsuario(id);

		usuarioDAO.fechar();

		return usuario;
	}

	public void editaUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {

		usuario = new Usuario();

		int id = Integer.parseInt(request.getParameter("id"));
		usuario.setId(id);
		usuario.setNome(request.getParameter("nome"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("senha"));
		usuario.setCidade(request.getParameter("cidade"));

		usuarioDAO = new UsuarioDAO();

		usuarioDAO.atualiza(usuario);
		usuarioDAO.fechar();

		response.sendRedirect("home");
	}

	public void novoUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		if(existeUsuario(request.getParameter("email"))) {
			System.out.println("Usuário já existe!");
			response.sendRedirect("signup.html");
		} else {
			usuario = new Usuario();

			// 1. recuperando parametros do formulario
			usuario.setNome(request.getParameter("nome"));
			usuario.setEmail(request.getParameter("email"));
			usuario.setSenha(request.getParameter("senha"));
			usuario.setCidade(request.getParameter("cidade"));

			usuarioDAO = new UsuarioDAO();
			usuarioDAO.adiciona(usuario);

			// System.out.println("Contato foi gravado!");

			usuarioDAO.fechar();

			response.sendRedirect("home");
		}

		
	}

	public ArrayList<Usuario> getUsuarios() {

		usuarioDAO = new UsuarioDAO();

		this.usuarios = usuarioDAO.getUsuarios();

		usuarioDAO.fechar();
		
		return usuarios;

	}
	
	public Usuario validaUsuario(String email) {
		
		usuario = new Usuario();
		usuarioDAO = new UsuarioDAO();
		
		usuario = usuarioDAO.getUsuarioPeloEmail(email);
		
		usuarioDAO.fechar();
		
		if(usuario != null) {
			return usuario;
		} else {
			return null;
		}
	}
	
	public boolean existeUsuario(String email) {
		
		usuario = new Usuario();
		usuarioDAO = new UsuarioDAO();
		
		usuario = usuarioDAO.getUsuarioPeloEmail(email);
		
		if(usuario != null) {
			return true;
		} else {
			return false;
		}
	}

}