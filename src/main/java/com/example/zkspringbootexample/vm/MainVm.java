package com.example.zkspringbootexample.vm;

import com.example.zkspringbootexample.utils.ApplicationConstants;
import com.example.zkspringbootexample.utils.ApplicationUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.lang.Library;
import org.zkoss.util.resource.Labels;
import org.zkoss.web.Attributes;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;

import java.util.Locale;
import java.util.Map;

import static java.util.Map.entry;

/**
 * @author Yann39
 * @since nov 2018
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MainVm {

    //region Variables
    private static final Logger logger = LoggerFactory.getLogger(MainVm.class);

    // the current page displayed in the body
    private String centerPage;

    // the current logged user
    private String authenticatedAs;
    //endregion

    //region Getters and setters
    public String getCenterPage() {
        return centerPage;
    }

    public void setCenterPage(String centerPage) {
        this.centerPage = centerPage;
    }

    public String getAuthenticatedAs() {
        return authenticatedAs;
    }

    public void setAuthenticatedAs(String authenticatedAs) {
        this.authenticatedAs = authenticatedAs;
    }
    //endregion

    //region Init

    /**
     * Initialization method
     * Central method called each time a user request a page
     * Manage authentication and redirects
     */
    @Init
    @NotifyChange("centerPage")
    public void init() {
        logger.info("Init MainVm");

        // reload labels (not reloaded automatically when file is modified)
        Labels.reset();

        // set MDC context (for logging)
        final String appName = "ZK Spring Boot Test";
        MDC.put("application.name", appName);

        // set page title
        final Page p = Executions.getCurrent().getDesktop().getFirstPage();
        p.setTitle(appName);

        // set locale
        Library.setProperty(Attributes.PREFERRED_LOCALE, "en");

        // get session attributes (set if user has been authenticated)
        final Session session = Sessions.getCurrent();
        final String firstName = (String) session.getAttribute("firstName");
        final String lastName = (String) session.getAttribute("lastName");
        final String mail = (String) session.getAttribute("mail");
        final String login = (String) session.getAttribute("login");

        boolean isAuthValid = false;
        setAuthenticatedAs(Labels.getLabel("label.unknownUser"));

        // if required attributes are set
        if (firstName != null && lastName != null && mail != null && login != null) {

                isAuthValid = true;
                setAuthenticatedAs(firstName + " " + lastName);
                logger.info("User authenticated as {} {}", firstName, lastName);

        }

        // get URL parameters
        final String target = Executions.getCurrent().getParameter("target");

        // redirects
        if (target != null) {

            // only authenticated users
            if (getAuthenticatedAs() != null && isAuthValid) {

                // administration
                if (target.compareTo("admin") == 0) {
                    setCenterPage(ApplicationConstants.ADMINISTRATION_TEMPLATE_PAGE);
                }

            }
            // user not logged, redirect to home page with a message
            else {
                setCenterPage(ApplicationConstants.HOME_PAGE);
                ApplicationUtils.showError("error.pageAccessDenied");
            }

        } else {
            setCenterPage(ApplicationConstants.HOME_PAGE);
        }

    }
    //endregion

    //region Methods

    /**
     * Change the current application language to French and do the redirect
     */
    @Command
    public void langFr() {
        final Locale prefer_locale = new Locale("fr");
        Sessions.getCurrent().setAttribute(Attributes.PREFERRED_LOCALE, prefer_locale);
        Executions.getCurrent().sendRedirect("");
    }

    /**
     * Change the current application language to English and do the redirect
     */
    @Command
    public void langEn() {
        final Locale prefer_locale = new Locale("en");
        Sessions.getCurrent().setAttribute(Attributes.PREFERRED_LOCALE, prefer_locale);
        Executions.getCurrent().sendRedirect("");
    }
    //endregion

}