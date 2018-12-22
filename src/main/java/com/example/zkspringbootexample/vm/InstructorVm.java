package com.example.zkspringbootexample.vm;

import com.example.zkspringbootexample.entities.Instructor;
import com.example.zkspringbootexample.model.InstructorStatus;
import com.example.zkspringbootexample.service.InstructorManager;
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

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yann39
 * @since nov 2018
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class InstructorVm {

    //region Variables
    private final Logger logger = LoggerFactory.getLogger(InstructorVm.class);

    // the current instructor list
    private List<InstructorStatus> instructors;

    // initialize filter to null (no value)
    private String filter;

    @WireVariable
    private InstructorManager instructorMgr;
    //endregion

    //region Getters and setters
    public List<InstructorStatus> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<InstructorStatus> instructors) {
        this.instructors = instructors;
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
        logger.info("Init InstructorVM");

        // load instructor list from the database
        loadInstructors();

        // initialize filter to null (no value)
        setFilter(null);
    }
    //endregion

    //region Methods

    /**
     * Get the instructor list from the database
     *
     * @return A list of Instructor elements representing the instructors
     * @see Instructor
     */
    public List<Instructor> getInstructorListFromDatabase() {
        return getFilter() != null ? instructorMgr.getFiltered(getFilter()) : instructorMgr.getAll();
    }

    /**
     * Fill a list of instructors with their edit status for inline row editing
     * Used to identity if the row is in edit mode
     *
     * @return A list of InstructorStatus elements representing the instructors with their associated row editing status
     * @see Instructor
     * @see InstructorStatus
     */
    public List<InstructorStatus> buildInstructorStatusList() {
        final List<InstructorStatus> instructorStatuses = new ArrayList<>();
        for (Instructor instr : getInstructorListFromDatabase()) {
            instructorStatuses.add(new InstructorStatus(instr, false));
        }
        return instructorStatuses;
    }

    /**
     * Reload the instructor list from the database
     *
     * @see Instructor
     */
    @Command
    @NotifyChange("instructors")
    public void loadInstructors() {
        setInstructors(buildInstructorStatusList());
    }

    /**
     * Add an instructor to the current instructor list
     * Used to add a row in the GUI
     *
     * @see Instructor
     */
    @Command
    @NotifyChange("instructors")
    public void addInstructor() {
        final Instructor instr = new Instructor();
        instr.setFirstName(Labels.getLabel("label.pleaseEditMe"));
        instr.setLastName(Labels.getLabel("label.pleaseEditMe"));
        instr.setEmail("Please@edit.me");
        final InstructorStatus is = new InstructorStatus(instr, false);
        getInstructors().add(0, is);
    }

    /**
     * Remove an instructor from the current instructor list
     * Used to remove a row from the GUI
     *
     * @param instructor The instructor to be removed
     * @see Instructor
     */
    @Command
    @NotifyChange("instructors")
    public void removeInstructor(@BindingParam("instructor") InstructorStatus instructor) {
        getInstructors().remove(instructor);
    }

    /**
     * Save the current instructor list to the database
     *
     * @see Instructor
     */
    @Command
    @NotifyChange("instructors")
    public void saveInstructors() {
        try {
            // confirmation dialog
            EventListener<Messagebox.ClickEvent> clickListener = new EventListener<>() {
                public void onEvent(Messagebox.ClickEvent event) throws Exception {
                    if (Messagebox.Button.YES.equals(event.getButton())) {

                        // store ids of the new edited list
                        final List<Long> instructorIds = new ArrayList<>();
                        for (InstructorStatus is : getInstructors()) {
                            instructorIds.add(is.getInstructor().getId());
                        }

                        // loop through all database values and remove the ones that or not in the new list
                        for (Instructor i : instructorMgr.getAll()) {
                            if (!instructorIds.contains(i.getId())) {
                                // if instructor is still used somewhere
                                if (instructorMgr.isUsed(i.getId())) {
                                    // show notification
                                    ApplicationUtils.showError("message.instructorStillUsed", i.getFullName());
                                } else {
                                    instructorMgr.delete(i);
                                }
                            }
                        }

                        // update the database with the new values
                        for (InstructorStatus is : getInstructors()) {
                            instructorMgr.saveOrUpdate(is.getInstructor());
                        }

                        // show notification
                        ApplicationUtils.showInfo("message.instructorsSaved");
                    }
                }
            };
            Messagebox.show(Labels.getLabel("message.saveModificationsConfirmation"), "Confirmation", new Messagebox.Button[]{Messagebox.Button.YES, Messagebox.Button.NO}, Messagebox.QUESTION, clickListener);
        } catch (Exception e) {
            logger.error("Error when saving instructor list : " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Change row editing status
     *
     * @param instructor The instructor corresponding to the row to be changed
     */
    @Command
    @NotifyChange("instructors")
    public void changeEditableStatus(@BindingParam("instructor") InstructorStatus instructor) {
        instructor.setDisplayEdit(!instructor.isDisplayEdit());
    }

    /**
     * Confirm row edition
     * It simply change row editing status but data is saved to the object only with this method (see zul)
     *
     * @param instructor The instructor corresponding to the row to be confirmed
     */
    @Command
    @NotifyChange("instructors")
    public void confirmEdit(@BindingParam("instructor") InstructorStatus instructor) {
        changeEditableStatus(instructor);
    }
    //endregion

}