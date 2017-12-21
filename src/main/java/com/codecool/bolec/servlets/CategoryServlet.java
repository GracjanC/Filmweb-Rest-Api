package com.codecool.bolec.servlets;

import com.codecool.bolec.model.Category;
import com.codecool.bolec.services.CategoryService;
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
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {


    }

    @Override
    protected void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
                         throws ServletException, IOException {


        String json = httpServletRequest.getReader().lines().collect(Collectors.joining());
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bolecPU");
        EntityManager em = emf.createEntityManager();

        try {
            JSonParser<Category> jsonParser = new JSonParser<>();
            ServletService<Category> service = new ServletService<>(Category.class);

            Category newCategory = (Category) jsonParser.jsonToObject(json, Category.class);
            Category oldCategory = em.find(Category.class, Long.valueOf(httpServletRequest.getPathInfo().replace("/","")));

            if (oldCategory == null) {
                httpServletResponse.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            } else {
                String newName = newCategory.getName();
                oldCategory.setName(newName);
                em.getTransaction().begin();
                em.merge(oldCategory);
                em.getTransaction().commit();
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
