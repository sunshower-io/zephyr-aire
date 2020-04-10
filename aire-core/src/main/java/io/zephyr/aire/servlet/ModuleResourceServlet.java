package io.zephyr.aire.servlet;

import io.zephyr.kernel.core.Framework;
import lombok.val;

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
      throws ServletException, IOException {
    val writer = response.getWriter();
    val kernel = Framework.getInstance();
    writer.write(".h1 {background-color: red}");
//    for (val plugin : kernel.getModuleManager().getModules()) {
//
//      writer.write("<h1>" + plugin.getCoordinate().toCanonicalForm() + "</h1>");
//    }

    //    defaultDispatcher.forward(request, response);
  }
}
