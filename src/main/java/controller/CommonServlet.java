package controller;

import controller.command.Command;
import controller.command.CommandFactory;
import util.PathBuilder;
import util.exception.DaoException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loadPage(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loadPage(req, resp);
    }

    private void loadPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = executeCommand(request, response);
        String path = PathBuilder.buildPath(page);
        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    private String executeCommand(HttpServletRequest request, HttpServletResponse response){
        Command command = CommandFactory.getInstance().getCommand(request);

        String page = null;
        try {
            page = command.execute(request, response);
        } catch (Exception e){
        }

        return page;
    }
}
