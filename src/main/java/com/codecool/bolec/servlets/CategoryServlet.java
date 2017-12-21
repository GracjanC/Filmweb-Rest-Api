package com.codecool.bolec.servlets;

import com.codecool.bolec.model.Category;
import com.codecool.bolec.services.ServletService;
import com.codecool.bolec.utils.JSonParser;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/categories/*")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse)
                         throws ServletException, IOException {
        String idPath = httpServletRequest.getPathInfo();
        String json;

        try {
            JSonParser<Category> jsonParser = new JSonParser<>();
            ServletService<Category> service = new ServletService<>(Category.class);

            if (idPath == null) {
                json = jsonParser.listToJSon(service.getAll());
            } else {
                Long id = Long.valueOf(idPath.replace("/", ""));
                json = jsonParser.objectToJSon(service.getObject(id));
            }

            httpServletResponse.getWriter().write(json);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
                          throws IOException {

        String json = httpServletRequest.getReader().lines().collect(Collectors.joining());

        try {
            JSonParser<Category> jsonParser = new JSonParser<>();
            ServletService<Category> service = new ServletService<>(Category.class);
            Category category = jsonParser.jsonToObject(json, Category.class);

            service.post(category);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
                         throws ServletException, IOException {

        String json = httpServletRequest.getReader().lines().collect(Collectors.joining());

        try {
            JSonParser<Category> jsonParser = new JSonParser<>();
            ServletService<Category> service = new ServletService<>(Category.class);

            Long id = Long.valueOf(httpServletRequest.getPathInfo().replace("/", ""));
            Category newCategory = jsonParser.jsonToObject(json, Category.class);
            Category oldCategory = service.getObject(id);

            if (oldCategory == null) {
                httpServletResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            } else {
                service.put(newCategory, oldCategory);
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
                            throws ServletException, IOException {

        String idPath = httpServletRequest.getPathInfo();

        try {
            ServletService<Category> service = new ServletService<>(Category.class);

            if (idPath == null) {
                httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
            } else {
                Long id = Long.valueOf(idPath.replace("/", ""));
                service.delete(id);
                httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
