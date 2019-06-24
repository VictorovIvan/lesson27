package ru.inno.stc14.servlet;

import ru.inno.stc14.service.PersonService;
import ru.inno.stc14.service.PersonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "person",
        loadOnStartup = 1,
        urlPatterns = "/person")
public class PersonServlet extends HttpServlet {
    private PersonService person;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        person = new PersonServiceImpl(connection);
        person.addPerson("Smith Stewart", "05.02.1988");
        person.addPerson("Johnson Sanchez", "06.01.1978");
        person.addPerson("Davis Reed", "09.03.1982");
        person.addPerson("Thomas Morris", "09.02.1989");
        person.addPerson("Lopez Coleman", "13.09.1989");
        person.addPerson("Wright Henderson", "15.09.1988");
        person.addPerson("Turner Gonzales", "01.06.1987");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/form.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String birth = req.getParameter("birth");
        person.addPerson(name, birth);

        resp.sendRedirect(req.getContextPath() + "/person/list");
    }
}
