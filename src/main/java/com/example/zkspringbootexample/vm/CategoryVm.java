package com.example.zkspringbootexample.vm;

import com.example.zkspringbootexample.entities.Category;
import com.example.zkspringbootexample.model.CategoryStatus;
import com.example.zkspringbootexample.service.CategoryManager;
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
public class CategoryVm {

    //region Variables
    private final Logger logger = LoggerFactory.getLogger(CategoryVm.class);

    // the current category list
    private List<CategoryStatus> categories;

    // the current filter value
    private String filter;

    @WireVariable
    private CategoryManager categoryMgr;
    //endregion

    //region Getters and setters
    public List<CategoryStatus> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryStatus> categories) {
        this.categories = categories;
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
        logger.info("Init CategoryVM");

        // load category list from the database
        loadCategories();

        // initialize filter to null (no value)
        setFilter(null);
    }
    //endregion

    //region Methods

    /**
     * Get the category list from the database
     * Can be filtered with the current filter content
     *
     * @return A list of Category elements representing the categories
     * @see Category
     */
    public List<Category> getCategoryListFromDatabase() {
        return getFilter() != null ? categoryMgr.getFiltered(getFilter()) : categoryMgr.getAll();
    }

    /**
     * Fill a list of categories with their edit status for inline row editing
     * Used to identity if the row is in edit mode
     *
     * @return A list of CategoryStatus elements representing the categories with their associated row editing status
     * @see Category
     * @see CategoryStatus
     */
    public List<CategoryStatus> buildCategoryStatusList() {
        final List<CategoryStatus> categoryStatuses = new ArrayList<>();
        for (Category instr : getCategoryListFromDatabase()) {
            categoryStatuses.add(new CategoryStatus(instr, false));
        }
        return categoryStatuses;
    }

    /**
     * Reload the category list from the database
     *
     * @see Category
     */
    @Command
    @NotifyChange("categories")
    public void loadCategories() {
        setCategories(buildCategoryStatusList());
    }

    /**
     * Add an category to the current category list
     * Used to add a row in the GUI
     *
     * @see Category
     */
    @Command
    @NotifyChange("categories")
    public void addCategory() {
        final Category instr = new Category();
        instr.setTitle(Labels.getLabel("label.pleaseEditMe"));
        final CategoryStatus is = new CategoryStatus(instr, false);
        getCategories().add(0, is);
    }

    /**
     * Remove an category from the current category list
     * Used to remove a row from the GUI
     *
     * @param category The category to be removed
     * @see Category
     */
    @Command
    @NotifyChange("categories")
    public void removeCategory(@BindingParam("category") CategoryStatus category) {
        getCategories().remove(category);
    }

    /**
     * Save the current category list to the database
     *
     * @see Category
     */
    @Command
    @NotifyChange("categories")
    public void saveCategories() {
        try {
            // confirmation dialog
            EventListener<Messagebox.ClickEvent> clickListener = new EventListener<>() {
                public void onEvent(Messagebox.ClickEvent event) throws Exception {
                    if (Messagebox.Button.YES.equals(event.getButton())) {

                        // store ids of the new edited list
                        final List<Long> categoryIds = new ArrayList<>();
                        for (CategoryStatus is : getCategories()) {
                            categoryIds.add(is.getCategory().getId());
                        }

                        // loop through all database values and remove the ones that or not in the new list
                        for (Category i : categoryMgr.getAll()) {
                            if (!categoryIds.contains(i.getId())) {
                                // if category is still used somewhere
                                if (categoryMgr.isUsed(i.getId())) {
                                    // show notification
                                    ApplicationUtils.showError("message.categoryStillUsed", i.getTitle());
                                } else {
                                    categoryMgr.delete(i);
                                }
                            }
                        }

                        // update the database with the new values
                        for (CategoryStatus is : getCategories()) {
                            categoryMgr.saveOrUpdate(is.getCategory());
                        }

                        // show notification
                        ApplicationUtils.showInfo("message.categoriesSaved");
                    }
                }
            };
            Messagebox.show(Labels.getLabel("message.saveModificationsConfirmation"), "Confirmation", new Messagebox.Button[]{Messagebox.Button.YES, Messagebox.Button.NO}, Messagebox.QUESTION, clickListener);
        } catch (Exception e) {
            logger.error("Error when saving category list", e);
        }
    }

    /**
     * Change row editing status
     *
     * @param category The category corresponding to the row to be changed
     */
    @Command
    @NotifyChange("categories")
    public void changeEditableStatus(@BindingParam("category") CategoryStatus category) {
        category.setDisplayEdit(!category.isDisplayEdit());
    }

    /**
     * Confirm row edition
     * It simply change row editing status but data is saved to the object only with this method (see zul)
     *
     * @param category The category corresponding to the row to be confirmed
     */
    @Command
    @NotifyChange("categories")
    public void confirmEdit(@BindingParam("category") CategoryStatus category) {
        changeEditableStatus(category);
    }
    //endregion

}