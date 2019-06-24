package ru.inno.stc14.servlet;

import ru.inno.stc14.entity.Person;
import ru.inno.stc14.service.PersonService;
import ru.inno.stc14.service.PersonServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "authorization",
        loadOnStartup = 1,
        urlPatterns = "/authorization")
public class AuthorizationServlet extends HttpServlet {
    private PersonService person;

    @Override
    public void init() throws ServletException {
        Connection connection = (Connection) getServletContext().getAttribute("DBConnection");
        person = new PersonServiceImpl(connection);
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String birthdayString = req.getParameter("birthday");
        List<Person> res = person.getList();

        try {
            person.authorizationPerson.setBirthDate(person.safeParseDate(birthdayString));
        } catch (Exception e) {
            person.authorizationPerson.setBirthDate(person.safeParseDate("00.00.0000"));
        }
        person.authorizationPerson.setName(name);

        if (containPerson(res)) {
            resp.sendRedirect(req.getContextPath() + "/content");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }

    /**
     * Check is contain user/password
     *
     * @param res result list
     * @return Check user/password
     */
    private boolean containPerson(List<Person> res) {
        boolean contained = false;
        for (Person listOneObject : res) {
            if (person.authorizationPerson.getName().equals(listOneObject.getName())) {
                if (person.authorizationPerson.getBirthDate().equals(listOneObject.getBirthDate())) {
                    contained = true;
                    break;
                }
            }
        }
        return contained;
    }
}
