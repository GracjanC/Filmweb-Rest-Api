package com.codecool.bolec.servlets;


import com.codecool.bolec.model.Category;
import com.codecool.bolec.model.Movie;
import com.codecool.bolec.services.ServletService;
import com.codecool.bolec.utils.JSonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/movies/*")
public class MovieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idPath = request.getPathInfo();
        String json;

        try {
            JSonParser<Category> jsonParser = new JSonParser<>();
            ServletService<Category> service = new ServletService<>(Category.class);

            if (idPath == null) {
                if(request.getQueryString() != null) {
                    json = jsonParser.listToJSon(service.getAll());
                } else {
                    json = "";

                    String categoryId = request.getParameter("category");
                    String directorId = request.getParameter("director");

                    ServletService<Movie> moviesService = new ServletService<>(Movie.class);
                    List<Movie> categoryMovies = moviesService.getByCategoryId(categoryId);
                    JSonParser<Movie> parser = new JSonParser<>();

                    json += parser.listToJSon(categoryMovies);

                    List<Movie> directorMovies = moviesService.getByDirectorId(directorId);
                    json += parser.listToJSon(directorMovies);

                    if( json.length() == 0) {
                        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    } else {
                        response.getWriter().write(json);
                    }
                }

            } else {
                Long id = Long.valueOf(idPath.replace("/", ""));
                json = jsonParser.objectToJSon(service.getObject(id));
            }

            response.getWriter().write(json);
        } catch (ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { }

}

