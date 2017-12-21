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
import java.util.stream.Collectors;

@WebServlet("/movies/*")
public class MovieServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idPath = request.getPathInfo();
        String json;

        try {
            JSonParser<Movie> jsonParser = new JSonParser<>();
            ServletService<Movie> service = new ServletService<>(Movie.class);

            if (idPath == null) {
                // /movies
                if(request.getQueryString() == null) {
                    json = jsonParser.listToJSon(service.getAll());

                // /movies?{QUERYSTRING}
                } else {
                    json = "";

                    String categoryId = request.getParameter("category");
                    String directorId = request.getParameter("director");

                    ServletService<Movie> moviesService = new ServletService<>(Movie.class);
                    List<Movie> categoryMovies = moviesService.getByCategoryId(categoryId);
                    List<Movie> directorMovies = moviesService.getByDirectorId(directorId);
                    JSonParser<Movie> parser = new JSonParser<>();

                    String categoryMoviesJson = parser.listToJSon(categoryMovies);
                    String directorMoviesJson = parser.listToJSon(directorMovies);

                    if(!categoryMoviesJson.isEmpty()) {
                        json += parser.listToJSon(categoryMovies);
                    }
                    if(!directorMoviesJson.isEmpty()){
                        json += parser.listToJSon(directorMovies);
                    }
                }
            // /movies/{id}
            } else {
                Long id = Long.valueOf(idPath.replace("/", ""));
                json = jsonParser.objectToJSon(service.getObject(id));
            }
            //validate empty json
            if (json.length() == 0) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);

            } else response.getWriter().write(json);

        } catch (ClassNotFoundException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String json = request.getReader().lines().collect(Collectors.joining());

        try {
            JSonParser<Movie> jsonParser = new JSonParser<>();
            ServletService<Movie> service = new ServletService<>(Movie.class);
            Movie movie = jsonParser.jsonToObject(json, Movie.class);

            service.post(movie);
            response.setStatus(HttpServletResponse.SC_OK);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String json = request.getReader().lines().collect(Collectors.joining());

        try {
            JSonParser<Movie> jsonParser = new JSonParser<>();
            ServletService<Movie> service = new ServletService<>(Movie.class);

            Long id = Long.valueOf(request.getPathInfo().replace("/", ""));
            Movie newMovie = jsonParser.jsonToObject(json, Movie.class);
            Movie oldMovie = service.getObject(id);

            if (oldMovie == null) {
                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            } else {
                service.put(newMovie);
                response.setStatus(HttpServletResponse.SC_OK);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idPath = request.getPathInfo();

        try {
            ServletService<Movie> service = new ServletService<>(Movie.class);

            if (idPath == null) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } else {
                Long id = Long.valueOf(idPath.replace("/", ""));
                if (service.getObject(id) == null) {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                } else {
                    service.delete(id);
                    response.setStatus(HttpServletResponse.SC_OK);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

