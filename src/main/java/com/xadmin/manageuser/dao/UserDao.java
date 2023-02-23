package com.xadmin.manageuser.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.manageuser.bean.User;

public class UserDao {
	private final String jdbcUrl = "jdbc:mysql://localhost:3306/practice";
	private final String jdbcUser = "root";
        private final String jdbcPassword = "zero";
	
	private static final String INSERT_INTO_TABL = "INSERT INTO users" + " (name,number) VALUES " + " (?,?);";
	
	private static final String SELECT_ALL_USER = "SELECT * FROM users";
	
	private static final String SEL_USER_BY_ID = "select id, name, number from users where id = ?;";
	
	private static final String DEL_USER = "delete from users where id = ?;";
	
	private static final String UPDATE_USER = "update users set name = ?, number = ? where id = ?;";
        
        public UserDao(){
        }
	
	protected Connection getConnection(){
		
		try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(jdbcUrl, jdbcUser,jdbcPassword);
        }
        catch ( ClassNotFoundException | SQLException e){
            System.out.println("Connection failed " + e);
            return null;
        }
		
	}

        
        //inserting user details
	public void insertUser(User user) throws SQLException{
		try {
			Connection connection = getConnection();

			PreparedStatement pst = connection.prepareStatement(INSERT_INTO_TABL);

			pst.setString(1, user.getName());
                        
                        pst.setString(2, user.getNumber());

			System.out.println(pst);

			pst.executeUpdate();

		} catch (SQLException se) {
			System.out.println(se);
		}
	}


	//get a user by id.
	public User getUserById(int id)throws SQLException{
		User user = null;

		try (Connection connection = getConnection();
		PreparedStatement pst = connection.prepareStatement(SEL_USER_BY_ID);) {
			pst.setInt(1, id);

			System.out.println(pst);

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
                                String number = rs.getString("number");
				user = new User(id,name,number);
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		return user;
	}


	//getting all users.
	public List<User> allUsers(){
		List<User> users = new ArrayList<>();
		
		try (Connection connection = getConnection();
		PreparedStatement pst = connection.prepareStatement(SELECT_ALL_USER);) {

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
                                String number = rs.getString("number");
				users.add(new User(id,name,number));
			}

		} catch (SQLException e) {
			System.out.println(e);
		}

		return users;
	}

	//update users
	public boolean updateUser(User user)throws SQLException{
		boolean update = false;
		try (Connection connection = getConnection();
		PreparedStatement pst = connection.prepareStatement(UPDATE_USER);) {

			System.out.println("Updated! " + pst);

			
			pst.setString(1, user.getName());
                        pst.setString(2, user.getNumber());
                        pst.setInt(3, user.getId());

			update = pst.executeUpdate() > 0;

		} catch (SQLException e) {
			System.out.println(e);
		}
		return update;
	}

	//delete user by id.
	public boolean deleteUser(int id)throws SQLException{
		boolean deleted = false;
		try (Connection connection = getConnection();
		PreparedStatement pst = connection.prepareStatement(DEL_USER);) {

			pst.setInt(1, id);

			deleted = pst.executeUpdate() > 0;

		} catch (SQLException e) {
			System.out.println(e);
		}
		return deleted;
	}
}
