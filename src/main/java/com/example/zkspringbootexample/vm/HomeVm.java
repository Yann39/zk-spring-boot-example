package com.example.zkspringbootexample.vm;

import com.example.zkspringbootexample.entities.Course;
import com.example.zkspringbootexample.service.CourseManager;
import com.example.zkspringbootexample.service.LanguageManager;
import com.example.zkspringbootexample.utils.ApplicationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.List;

/**
 * @author Yann39
 * @since nov 2018
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class HomeVm {

    //region Variables
    private final Logger logger = LoggerFactory.getLogger(HomeVm.class);

    // the current course list
    private List<Course> courses;

    // the current filter value
    private String filter;

    @WireVariable
    private CourseManager courseMgr;

    @WireVariable
    private LanguageManager languageMgr;
    //endregion

    //region Getters and setters
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
    //endregion

    //region Init
    @Init
    public void init() {
        logger.info("Init HomeVM");

        // load course list from the database
        loadCourses();

        // initialize filter to null (no value)
        setFilter(null);
    }
    //endregion

    //region Methods

    /**
     * Get the registrable course list from the database
     * Registrable courses are courses with a valid status and valid registration dates range
     *
     * @return A list of Course elements representing the courses
     * @see Course
     */
    private List<Course> getRegistrableCourseListFromDatabase() {
        return getFilter() != null ? courseMgr.getFiltered(getFilter()) : courseMgr.getAll();
    }

    /**
     * Reload the course list from the database
     *
     * @see Course
     */
    @Command
    @NotifyChange("courses")
    public void loadCourses() {
        setCourses(getRegistrableCourseListFromDatabase());
    }

    /**
     * Get the registrable course list from the database
     * Registrable courses are courses with a valid status and valid registration dates range
     *
     * @return A list of Course elements representing the courses
     * @see Course
     */
    @Command
    public void goToRegisterCourse(@BindingParam("course") Course course) {
        // show notification
        ApplicationUtils.showWarning("message.notImplemented");
    }

    //endregion
}