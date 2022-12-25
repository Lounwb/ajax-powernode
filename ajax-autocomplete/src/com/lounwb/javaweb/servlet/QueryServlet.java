package com.lounwb.javaweb.servlet;

import com.alibaba.fastjson.JSON;
import com.lounwb.javaweb.beans.Column;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.geom.Area;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/query")
public class QueryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keywords = request.getParameter("keywords");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Column> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/bjpowernode";
            String user = "root";
            String password = "litianyu2002";
            conn = DriverManager.getConnection(url, user, password);

            String sql = "select content from t_ajax where content like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,keywords + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String content = rs.getString("content");
                Column column = new Column(content);
                list.add(column);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        response.setContentType("text/html;charset=utf-8");

        //使用fastjson将对象转换成json字符串
        String json = JSON.toJSONString(list);

        PrintWriter out = response.getWriter();
        out.print(json);
    }
}
