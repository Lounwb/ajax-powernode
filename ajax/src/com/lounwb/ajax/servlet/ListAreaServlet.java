package com.lounwb.ajax.servlet;

import com.alibaba.fastjson.JSON;
import com.lounwb.ajax.beans.Area;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listArea")
public class ListAreaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pcode = request.getParameter("pcode");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Area> areaList = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/bjpowernode";
            String user = "root";
            String password = "litianyu2002";
            conn = DriverManager.getConnection(url, user, password);

            String sql = "";
            if (pcode == null) {
                sql = "select code,name from t_area where pcode is null";
                ps = conn.prepareStatement(sql);
            }else {
                sql = "select code,name from t_area where pcode = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1,pcode);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                Area a = new Area(code, name);
                areaList.add(a);
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
        String json = JSON.toJSONString(areaList);

        PrintWriter out = response.getWriter();
        out.print(json);
    }
}
