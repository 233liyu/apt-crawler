package main.Computering.MovingPart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class StartUpServlet extends HttpServlet{
    @Override
    public void init() throws ServletException {
        MovingManager manager = MovingManager.getInstance();
        manager.startThread();
        super.init();
    }
}
