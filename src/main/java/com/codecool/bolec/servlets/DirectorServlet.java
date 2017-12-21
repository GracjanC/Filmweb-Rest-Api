package com.codecool.bolec.servlets;

import com.codecool.bolec.model.Director;
import com.codecool.bolec.services.ServletService;
import com.codecool.bolec.utils.JSonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/director/*"})
public class DirectorServlet extends HttpServlet implements ServletInterface{

    @Override
    public void doPost(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String json = httpServletRequest.getReader().lines().collect(Collectors.joining());

        try {
            JSonParser<Director> jsonParser = new JSonParser<>();
            ServletService<Director> service = new ServletService<>(Director.class);
            Director director = jsonParser.jsonToObject(json, Director.class);

            service.post(director);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
