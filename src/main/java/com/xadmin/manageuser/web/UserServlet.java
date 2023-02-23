package com.xadmin.manageuser.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.xadmin.manageuser.bean.User;
import com.xadmin.manageuser.dao.UserDao;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private UserDao userDao;

    /**
     * @throws jakarta.servlet.ServletException
     * @see Servlet#init(ServletConfig)
     */
    @Override
    public void init() throws ServletException {
        userDao = new UserDao();
    }

    /**
     * @param request
     * @param response
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws jakarta.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch(action) {
            case "/new":
                try {
                showNewForm(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;

            case "/insert":
                try {
                insertUser(request, response);
            } catch (IOException | SQLException ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
            case "/delete":
                try {
                deleteUser(request, response);
            } catch (IOException | SQLException ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;

            case "/edit":
                try {
                showEditForm(request, response);
            } catch (ServletException | IOException | SQLException ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;

            case "/update":
                try {
                updateUser(request, response);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;

            default:
                try {
                listUser(request, response);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            break;
        }
        
    }
    
        private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
        {
            jakarta.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
            dispatcher.forward(request, response);
        }
        //add new user

        private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
            String name = request.getParameter("name");
            String number = request.getParameter("number");

            User newUser = new User(name, number);

            userDao.insertUser(newUser);

            response.sendRedirect("list");
        }
    //delete a user.

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            userDao.deleteUser(id);
        } catch (SQLException e) {
            System.out.println(e);
        }

        response.sendRedirect("list");
    }

    //edit user.
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));

        User existUser;

        try {
            existUser = userDao.getUserById(id);
            jakarta.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
            request.setAttribute("user", existUser);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException | SQLException e) {
            System.out.println(e);
        }

    }
    //update user.

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        
        int id = Integer.parseInt(request.getParameter("id"));

        String name = request.getParameter("name");

        String number = request.getParameter("number");

        User user = new User(id, name, number);

        userDao.updateUser(user);

        response.sendRedirect("list");
    }
    //show all user details.

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        try {
            List<User> listUser = userDao.allUsers();

            request.setAttribute("listUser", listUser);

            jakarta.servlet.RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");

            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            System.out.println(e);
        }
    }
}
