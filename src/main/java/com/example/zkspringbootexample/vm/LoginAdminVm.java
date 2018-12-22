package com.example.zkspringbootexample.vm;

import com.example.zkspringbootexample.utils.ApplicationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

import java.util.Date;

/**
 * @author Yann39
 * @since nov 2018
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class LoginAdminVm {

    //region Variables
    private static final Logger logger = LoggerFactory.getLogger(LoginAdminVm.class);
    //endregion

    //region Init
    @Init
    public void init() {
        logger.info("Init LoginUserVm");

        // manage authentication here (get SSO attributes...)
        final String firstName = "Admin";
        final String lastName = "Test";
        final String mail = "admin.test@mail.com";
        final String login = "atest";

        if (firstName != null && lastName != null && mail != null && login != null) {
            logger.info("SSO login as {} {}", firstName, lastName);

            // set user information in the session
            final Session session = Sessions.getCurrent();
            session.setAttribute("firstName", firstName);
            session.setAttribute("lastName", lastName);
            session.setAttribute("mail", mail);
            session.setAttribute("login", login);

            Executions.sendRedirect("/?target=admin");

        } else {
            // show notification
            ApplicationUtils.showInfo("message.missingAttributes");
        }
    }
    //endregion

}