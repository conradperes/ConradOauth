package br.edu.devmedia.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.devmedia.graph.GoogleGraph;
import br.edu.devmedia.helper.GoogleAuthHelper;

@WebServlet("/google_login")
public class GoogleAuthServlet extends HttpServlet {
	
	private static final long serialVersionUID = -2786220774420235979L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		final GoogleAuthHelper helper = new GoogleAuthHelper();
		
		GoogleGraph graph = new GoogleGraph();
		
		String json = helper.getInfoUsuarioJson(req.getParameter("code"));
		
		Map<String, String> perfil = graph.getGraphDados(json);
		
		req.getSession().setAttribute("perfil", perfil);
		
		req.getRequestDispatcher("resultado.jsp").forward(req, resp);
	}

}
