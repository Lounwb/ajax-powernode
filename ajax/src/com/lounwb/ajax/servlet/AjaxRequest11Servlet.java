package com.lounwb.ajax.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;

@WebServlet("/ajaxrequest11")
public class AjaxRequest11Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        //{"username":"zhangsan"}
        response.getWriter().print("{\"username\":\""+username.toUpperCase(Locale.ROOT)+"\"}");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        String username = request.getParameter("username");
        //{"username":"zhangsan"}
        response.getWriter().print("{\"username\":\""+username.toLowerCase(Locale.ROOT)+"\"}");
    }
}
