package io.zephyr.aire.servlet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/modules/*")
public class ModuleResourceServlet extends HttpServlet {

  private RequestDispatcher defaultDispatcher;

  @Override
  public void init() throws ServletException {
    super.init();
    defaultDispatcher = getServletContext().getNamedDispatcher("default");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {}
}
