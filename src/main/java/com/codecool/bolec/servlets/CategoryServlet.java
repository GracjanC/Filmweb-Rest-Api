package com.codecool.bolec.servlets;

import com.codecool.bolec.model.Category;
import com.codecool.bolec.services.CategoryService;
import com.codecool.bolec.utils.JSonParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/categories/*")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse)
                         throws ServletException, IOException {
        String idPath = httpServletRequest.getPathInfo();
        String json;

        if (idPath == null) {
            json = new Gson().toJson(new CategoryService().getAll(), new TypeToken<List<Category>>() {}.getType());
        } else {
            Long id = Long.valueOf(idPath.replace("/", ""));
            json = JSonParser.objectToJSon(new CategoryService().find(id));
        }

        httpServletResponse.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {


    }

    @Override
    protected void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    }
    @Override
    protected void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
                            throws ServletException, IOException {

        String idPath = httpServletRequest.getPathInfo();

        if (idPath == null) {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            Long id = Long.valueOf(idPath.replace("/", ""));
            new CategoryService().delete(id);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
