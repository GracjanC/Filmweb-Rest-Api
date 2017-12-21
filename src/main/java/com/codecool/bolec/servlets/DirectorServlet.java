package com.codecool.bolec.servlets;

import com.codecool.bolec.model.Director;
import com.codecool.bolec.utils.JSonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/director/*"})
public class DirectorServlet extends HttpServlet implements ServletInterface{
    @Override
    public void doPost(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }

    @Override
    public void doPut(HttpServletRequest httpServletRequest,
                      HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }

    @Override
    public void doDelete(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse) throws ServletException, IOException {

    }

    @Override
    public void doGet(HttpServletRequest httpServletRequest,
                      HttpServletResponse httpServletResponse) throws ServletException, IOException {

//        String idPath = httpServletRequest.getPathInfo();
//        String json;
//
//        if (idPath == null) {
//            List<Director> directorList = new DirectorService.getAll();
//            json = JSonParser.listToJSon(directorList);
//
//        } else {
//            Long id = Long.valueOf(idPath.replace("/", ""));
//            json = JSonParser.objectToJSon(new DirectorService().find(id));
//        }
//
//        httpServletResponse.getWriter().write(json);

    }
}
