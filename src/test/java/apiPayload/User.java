package apiPayload;
public class User {
    private final String userFirstName; 
    private final String userLastName;   
    private final String userContactNumber;
    private final String userEmail;
    private final String plotNumber;
    private final String street;
    private final String state;
    private final String country;
    private final String zipCode;

    private User(Builder builder) {
        this.userFirstName = builder.userFirstName;
        this.userLastName = builder.userLastName;
        this.userContactNumber = builder.userContactNumber;
        this.userEmail = builder.userEmail;
        this.plotNumber = builder.plotNumber;
        this.street = builder.street;
        this.state = builder.state;
        this.country = builder.country;
        this.zipCode = builder.zipCode;
    }

    // Getters
    public String getUserFirstName() {
        return userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public String getUserContactNumber() {
        return userContactNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getPlotNumber() {
        return plotNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }

    // Builder class
    public static class Builder {
        private String userFirstName; 
        private String userLastName;   
        private String userContactNumber;
        private String userEmail;
        private String plotNumber;
        private String street;
        private String state;
        private String country;
        private String zipCode;

        public Builder userFirstName(String userFirstName) {
            this.userFirstName = userFirstName;
            return this;
        }

        public Builder userLastName(String userLastName) {
            this.userLastName = userLastName;
            return this;
        }

        public Builder userContactNumber(String userContactNumber) {
            this.userContactNumber = userContactNumber;
            return this;
        }

        public Builder userEmail(String userEmail) {
            this.userEmail = userEmail;
            return this;
        }

        public Builder plotNumber(String plotNumber) {
            this.plotNumber = plotNumber;
            return this;
        }

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Builder zipCode(String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
