package beans;

import jakarta.annotation.ManagedBean;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * Managed bean for Restaurant Landing Page Handles user registration and login
 * navigation
 *
 * @author TableTurn Development Team
 * @version 1.0
 */
@ManagedBean
@ViewScoped
public class LandingBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(LandingBean.class.getName());

    // Properties
    private String email;
    private String restaurantName;
    private String contactNumber;
    private boolean agreedToTerms;

    // EJB Dependencies
    // @EJB
    // private UserService userService;
    // @EJB 
    // private RestaurantService restaurantService;
    /**
     * Post construct initialization
     */
    @PostConstruct
    public void init() {
        LOGGER.info("LandingBean initialized");
        // Initialize any required data
        this.agreedToTerms = false;
    }

    /**
     * Handle restaurant registration
     */
    public void registerRestaurant() {
        try {
            LOGGER.info("Processing restaurant registration for email: " + email);

            // Validation
            if (email == null || email.trim().isEmpty()) {
                addErrorMessage("Email is required");
                return;
            }

            if (!isValidEmail(email)) {
                addErrorMessage("Please enter a valid email address");
                return;
            }

            // Business logic for registration
            // RestaurantRegistrationRequest request = new RestaurantRegistrationRequest();
            // request.setEmail(email);
            // request.setRestaurantName(restaurantName);
            // request.setContactNumber(contactNumber);
            // Call service layer
            // restaurantService.registerRestaurant(request);
            // Success message and navigation
            addSuccessMessage("Registration successful! Please check your email for further instructions.");

            // Navigate to registration form or dashboard
            // FacesContext.getCurrentInstance().getExternalContext().redirect("registration-form.xhtml");
        } catch (Exception e) {
            LOGGER.severe("Error during restaurant registration: " + e.getMessage());
            addErrorMessage("Registration failed. Please try again later.");
        }
    }

    /**
     * Handle admin login navigation
     */
    public void adminLogin() {
        try {
            LOGGER.info("Redirecting to admin login");

            // Navigate to admin login page
            FacesContext.getCurrentInstance().getExternalContext().redirect("admin/login.xhtml");

        } catch (Exception e) {
            LOGGER.severe("Error navigating to admin login: " + e.getMessage());
            addErrorMessage("Unable to access admin login. Please try again.");
        }
    }

    /**
     * Handle staff login navigation
     */
    public void staffLogin() {
        try {
            LOGGER.info("Redirecting to staff login");

            // Navigate to staff login page
            FacesContext.getCurrentInstance().getExternalContext().redirect("staff/login.xhtml");

        } catch (Exception e) {
            LOGGER.severe("Error navigating to staff login: " + e.getMessage());
            addErrorMessage("Unable to access staff login. Please try again.");
        }
    }

    /**
     * Navigate to features section
     */
    public void viewFeatures() {
        // This can be handled via JavaScript scroll or navigation
        LOGGER.info("User requested to view features");
    }

    /**
     * Navigate to pricing section
     */
    public void viewPricing() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("pricing.xhtml");
        } catch (Exception e) {
            LOGGER.severe("Error navigating to pricing: " + e.getMessage());
        }
    }

    /**
     * Contact us functionality
     */
    public void contactUs() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("contact.xhtml");
        } catch (Exception e) {
            LOGGER.severe("Error navigating to contact: " + e.getMessage());
        }
    }

    // Utility Methods
    /**
     * Validate email format
     */
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    /**
     * Add success message to FacesContext
     */
    private void addSuccessMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", message));
    }

    /**
     * Add error message to FacesContext
     */
    private void addErrorMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));
    }

    /**
     * Add warning message to FacesContext
     */
    private void addWarningMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning", message));
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public boolean isAgreedToTerms() {
        return agreedToTerms;
    }

    public void setAgreedToTerms(boolean agreedToTerms) {
        this.agreedToTerms = agreedToTerms;
    }
}
