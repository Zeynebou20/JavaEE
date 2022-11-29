package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Utilisateur;

public class UtilisateurDao 
{
	private static ArrayList<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
	
	static {
		Connection conn = DbConnection.openConnexion();
		Statement stm;
		if( conn != null) {
			
			try {
				String q = "select * from user";
				stm = conn.createStatement();
				ResultSet res = stm.executeQuery(q);
				while(res.next()) {
					Utilisateur utilisateur = new Utilisateur(res.getInt("id"), res.getString("nom"), res.getString("prenom"), res.getString("login"), res.getString("password"));
					utilisateurs.add(utilisateur);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public static ArrayList<Utilisateur> utilisateurs()
	{	
		return utilisateurs;
	}
	
	public static Utilisateur get(int id)
	{
		for(Utilisateur user : utilisateurs)
		{
			if (user.getId() == id)
				return user;
		}
		
		return null;
	}
	
	public static Utilisateur getUtilisateur(String login, String password)
	{
		for(Utilisateur utilisateur : utilisateurs)
		{
			if (utilisateur.getLogin().equals(login) && utilisateur.getPassword().equals(password))
				return utilisateur;
		}
		
		return null;
	}
	
	public static boolean add(Utilisateur utilisateur)
	{
		Connection conn = DbConnection.openConnexion();
		try{
			String q = "INSERT INTO user(nom, prenom, login, password) VALUES (?, ?, ?, ?);";
			PreparedStatement ps = conn.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, utilisateur.getNom());
			ps.setString(2, utilisateur.getPrenom());
			ps.setString(3, utilisateur.getLogin());
			ps.setString(4, utilisateur.getPassword());
			if(ps.executeUpdate()!=0) {
				ResultSet keys = ps.getGeneratedKeys();
				if(keys.next()) {
					utilisateur.setId(keys.getInt(1));
					utilisateurs.add(utilisateur);
				}
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DbConnection.closeConnexion(conn);
		}
		return true;
	}
	
	public static Boolean edit(Utilisateur user)
	{
		
		for (Utilisateur utilisateur : utilisateurs)
		{
			if (user.getId() == utilisateur.getId())
			{
				utilisateur.setNom(user.getNom());
				utilisateur.setPrenom(user.getPrenom());
				utilisateur.setLogin(user.getLogin());
				utilisateur.setPassword(user.getPassword());
				// update on the DB
				String q = "UPDATE user SET nom=?, prenom=?, login=?, password=? WHERE id = ?;";
				Connection conn = DbConnection.openConnexion();
				try{
					PreparedStatement ps = conn.prepareStatement(q);
					ps.setString(1, user.getNom());
					ps.setString(2, user.getPrenom());
					ps.setString(3, user.getLogin());
					ps.setString(4, user.getPassword());
					ps.setInt(5, user.getId());
					if(ps.executeUpdate()!=0) {
						return true;
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					DbConnection.closeConnexion(conn);
				}
			}
		}

		return false;
	}
	
	public static Boolean delete(int id)
	{
		for (Utilisateur utilisateur : utilisateurs)
		{
			if (utilisateur.getId() == id)
			{
				String q = "DELETE FROM user WHERE id = ?;";
				Connection conn = DbConnection.openConnexion();
				try{
					PreparedStatement ps = conn.prepareStatement(q);
					ps.setInt(1, id);
					if(ps.executeUpdate()!=0) {
						utilisateurs.remove(utilisateur);
						return true;
					}
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					DbConnection.closeConnexion(conn);
				}
			}
		}

		return false;
	}
	public static boolean login(String login, String pass) {
		int isReg = 0;
		Connection  conn = DbConnection.openConnexion();
		if( conn != null) {
			String req = "SELECT * FROM user WHERE login = '"+login+"' AND password ='"+pass+"'";
			try {
				Statement ps = conn.createStatement();
				
				ResultSet res = ps.executeQuery(req);
				while(res.next()) {
					isReg++;
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	   return isReg == 1 ? true : false;
	}
}
