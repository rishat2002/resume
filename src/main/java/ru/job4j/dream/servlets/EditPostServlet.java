package ru.job4j.dream.servlets;

import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.Store;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class EditPostServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id=req.getParameter("id");
        String n=req.getParameter("name");
        Store.instOf().postUpdate(
                new Post(
                        Integer.valueOf(id),
                        n
                )
        );
        resp.sendRedirect(req.getContextPath() + "/posts.do");
    }
}
