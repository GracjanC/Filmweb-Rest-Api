package com.codecool.bolec.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ServletInterface {

void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
void doDelete(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
}
