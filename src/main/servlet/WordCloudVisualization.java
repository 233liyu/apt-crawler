package main.servlet;

import com.google.gson.JsonObject;
import main.Computering.DataCal.DataManager;
import main.Computering.DataCal.wordCloud.wordCloudHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Map;

@WebServlet(name = "DataVisualization")
public class WordCloudVisualization extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        DataManager manager = DataManager.getInstance();
        Map<String, Integer> map = manager.wordCloudHandler.getResult();

        JsonObject object = new JsonObject();
        object.addProperty("status", "success");
        object.add("data", wordCloudHandler.toJsonArray(map));

        Writer out = response.getWriter();
        out.write(object.toString());
        out.flush();
        out.close();
        response.flushBuffer();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(404);
    }
}
