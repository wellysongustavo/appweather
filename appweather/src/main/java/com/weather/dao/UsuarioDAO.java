package com.weather.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.weather.model.Usuario;
import com.weather.utils.ConnectionFactory;

public class UsuarioDAO {
	
	private Connection conn;
	
	public UsuarioDAO() {
		ConnectionFactory cf = new ConnectionFactory();
		this.conn = cf.getConnection();
	}
	
	public void fechar() {
		try {
			this.conn.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void adiciona(Usuario usuario) {
		
		//1. string de inserção
		
		String sql = "insert into usuario" +
					"(nome,email,senha,cidade)" +
					"values(?,?,?,?)";
		
		try {
			//2. preparar a senteça
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			//3. passar dados que estão no objeto contatoBean
			
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.setString(4, usuario.getCidade());
			
			//4. executar a setença
			stmt.execute();
			stmt.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Usuario> getUsuarios(){
		
		String sql = "select * from usuarios";
		
		try {
			
			ArrayList<Usuario> usuarios = 
					new ArrayList<Usuario>();
			
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setCidade(rs.getString("cidade"));

				usuarios.add(usuario);
				
			}
			
			rs.close();
			stmt.close();
			
			return usuarios;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
		
		
		
	}
	
	public Usuario getUsuario(int id) {
		
		String sql = "select * from usuario where id = ?";
		
		try {
			
			Usuario usuario = new Usuario();
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setCidade(rs.getString("cidade"));
			}
			
			rs.close();
			stmt.close();
			return usuario;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}
	
	public void atualiza(Usuario usuario) {
		
		String sql = "update usuario set nome=?, email=?, "+
		"senha=?, cidade=? where id=?";
		
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			
			stmt.setString(1, usuario.getNome());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getSenha());
			stmt.setString(4, usuario.getCidade());
			
			stmt.setInt(5, usuario.getId());
			
			stmt.execute();
			stmt.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}
	
	
	public void remove(Usuario usuario) {
		
		String sql = "delete from usuario where id=?";
		
		try {
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			
			stmt.setInt(1, usuario.getId());
			
			stmt.execute();
			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		
	}
	
	public Usuario getUsuarioPeloEmail(String email) {
		
		String sql = "select * from usuario where email = ?";
		Usuario usuario = null;
		
		try {
			
			PreparedStatement stmt = this.conn.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				usuario = new Usuario();
				
				usuario.setId(rs.getInt("id"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setCidade(rs.getString("cidade"));
			}
			
			rs.close();
			stmt.close();
			
			return usuario;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
		
	}
	
	

}