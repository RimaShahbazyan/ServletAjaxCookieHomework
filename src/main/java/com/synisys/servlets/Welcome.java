package com.synisys.servlets;

import com.synisys.data.bulkData.DummyUserData;
import com.synisys.dataManager.UserManager;
import com.synisys.dataManager.UserTokenManager;
import com.synisys.entities.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/welcome")
public class Welcome extends HttpServlet {

    @Override
    public void init() {

    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out;
        User user;
        DummyUserData data = DummyUserData.getInstance();
        UserManager usMan = new UserManager(data);
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        out = resp.getWriter();
        user = usMan.getUserByEmail(email);
        if (user.getPassword().equals(password)) {
            out.println("<h1>" +
                    "Welcome " + user.getName() + " " +
                    user.getSurname() +
                    "</h1>" +
                    "<a href=\"http://localhost:8010/Search.html\">Search Friends</a>"
            );
            UserTokenManager tokenManager = new UserTokenManager();
            String tokenId = Integer.toString(tokenManager.addToken(user.getId()).getTokenId());
            Cookie tCookie = new Cookie("tokenId",tokenId);
            tCookie.setMaxAge(10*60*60);
            Cookie uCookie = new Cookie("userId",Integer.toString(user.getId()));
            uCookie.setMaxAge(10*60*60);
            resp.addCookie(tCookie);
            resp.addCookie(uCookie);
        } else {
            resp.sendError(401);
        }

    }
}
