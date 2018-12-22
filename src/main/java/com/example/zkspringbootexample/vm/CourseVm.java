package com.example.zkspringbootexample.vm;

import com.example.zkspringbootexample.entities.Course;
import com.example.zkspringbootexample.entities.Instructor;
import com.example.zkspringbootexample.entities.Language;
import com.example.zkspringbootexample.service.CourseManager;
import com.example.zkspringbootexample.service.InstructorManager;
import com.example.zkspringbootexample.service.LanguageManager;
import com.example.zkspringbootexample.utils.ApplicationConstants;
import com.example.zkspringbootexample.utils.ApplicationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Messagebox;

import java.util.List;

/**
 * @author Yann39
 * @since nov 2018
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CourseVm {

    //region Variables
    private final Logger logger = LoggerFactory.getLogger(CourseVm.class);

    // the current course list
    private List<Course> courses;

    // the current course
    private Course course;

    // the current page content to be displayed
    private String centerPage;

    // the current filter value
    private String filter;

    @WireVariable
    private CourseManager courseMgr;

    @WireVariable
    private LanguageManager languageMgr;

    @WireVariable
    private InstructorManager instructorMgr;
    //endregion

    //region Getters and setters
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getCenterPage() {
        return centerPage;
    }

    public void setCenterPage(String centerPage) {
        this.centerPage = centerPage;
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
        logger.info("Init CourseVM");

        // set course list as current content page
        setCenterPage(ApplicationConstants.COURSE_LIST_PAGE);

        // load course list from the database
        loadCourses();

        // initialize filter to null (no value)
        setFilter(null);
    }
    //endregion

    //region Methods

    /**
     * Get the language list from the database
     *
     * @return A list of Language elements representing the languages
     * @see Language
     */
    public List<Language> getLanguageList() {
        return languageMgr.getAll();
    }

    /**
     * Get the instructor list from the database
     *
     * @return A list of Instructor elements representing the instructors
     * @see Instructor
     */
    public List<Instructor> getInstructorList() {
        return instructorMgr.getAll();
    }

    /**
     * Get the course list from the database
     * Can be filtered with the current filter value
     *
     * @return A list of Course elements representing the courses
     * @see Course
     */
    private List<Course> getCourseListFromDatabase() {
        return getFilter() != null ? courseMgr.getFiltered(getFilter()) : courseMgr.getAll();
    }

    /**
     * Reload the course list
     *
     * @see Course
     */
    @Command
    @NotifyChange("courses")
    public void loadCourses() {
        setCourses(getCourseListFromDatabase());
    }

    /**
     * Save a new course to the database
     *
     * @see Course
     */
    @Command
    @NotifyChange({"course", "courses", "centerPage"})
    public void createCourse() {
        try {

            // save the course
            courseMgr.saveOrUpdate(getCourse());

            // back to course list
            setCenterPage(ApplicationConstants.COURSE_LIST_PAGE);

            // show notification
            ApplicationUtils.showInfo("message.courseCreated");

        } catch (Exception e) {
            logger.error("Error when creating course : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Update the current course into the database
     *
     * @see Course
     */
    @Command
    @NotifyChange({"course", "courses", "centerPage"})
    public void updateCourse() {
        try {

            // update the course
            courseMgr.saveOrUpdate(getCourse());

            // back to course list
            setCenterPage(ApplicationConstants.COURSE_LIST_PAGE);

            // show notification
            ApplicationUtils.showInfo("message.courseUpdated");

        } catch (Exception e) {
            logger.error("Error when updating course : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Remove the specified course from the database
     *
     * @param crs The course to be removed
     * @see Course
     */
    @Command
    @NotifyChange({"course", "courses"})
    public void deleteCourse(@BindingParam("course") Course crs) {
        try {

            // set the selected course as current
            setCourse(crs);

            // confirmation dialog
            EventListener<Messagebox.ClickEvent> clickListener = new EventListener<>() {
                public void onEvent(Messagebox.ClickEvent event) {
                    if (Messagebox.Button.YES.equals(event.getButton())) {

                        // delete course
                        courseMgr.delete(getCourse());

                        // show success message
                        ApplicationUtils.showInfo("message.courseDeleted");
                    }
                }
            };
            Messagebox.show(Labels.getLabel("message.deleteCourseConfirmation", new Object[]{getCourse().getTitle()}), "Confirmation", new Messagebox.Button[]{Messagebox.Button.YES, Messagebox.Button.NO}, Messagebox.QUESTION, clickListener);

        } catch (Exception e) {
            logger.error("Error when deleting course : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Load the create course content
     */
    @Command
    @NotifyChange({"course", "centerPage"})
    public void goToCreateCourse() {

        // initialize a new course
        if (getCourse() == null) {
            setCourse(new Course());
        }

        // go to course creation form
        setCenterPage(ApplicationConstants.COURSE_CREATE_PAGE);
    }

    /**
     * Load the edit course content
     *
     * @param crs The course to edit
     */
    @Command
    @NotifyChange({"course", "centerPage"})
    public void goToEditCourse(@BindingParam("course") Course crs) {

        // set the selected course as the current course to be edited
        setCourse(crs);

        // go to course creation form
        setCenterPage(ApplicationConstants.COURSE_CREATE_PAGE);
    }

    /**
     * Load the course list content
     */
    @Command
    @NotifyChange("centerPage")
    public void goToCourseList() {
        setCenterPage(ApplicationConstants.COURSE_LIST_PAGE);
    }
    //endregion
}