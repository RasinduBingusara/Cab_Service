package org.megacity.cab_service.model;

public class UserAccount {

    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String contactNumber;
    private String password;
    private String userType;
    private String nic;
    private String address;
    private String driverLicense;
    private String createdAt;

    private UserAccount(UserCreator creator) {
        this.id = creator.id;
        this.firstname = creator.firstname;
        this.lastname = creator.lastname;
        this.email = creator.email;
        this.contactNumber = creator.contactNumber;
        this.password = creator.password;
        this.userType = creator.userType;
        this.nic = creator.nic;
        this.address = creator.address;
        this.driverLicense = creator.driverLicense;
        this.createdAt = creator.createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public static class UserCreator{
        private int id;
        private String firstname;
        private String lastname;
        private String email;
        private String contactNumber;
        private String password;
        private String userType;
        private String nic;
        private String address;
        private String driverLicense;
        private String createdAt;


        public UserCreator() {
        }

        public UserCreator(String firstname, String lastname, String email, String contactNumber, String password, String userType) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.email = email;
            this.contactNumber = contactNumber;
            this.password = password;
            this.userType = userType;
        }

        public UserAccount defaultUser(int id){
            this.id=id;
            return new UserAccount(this);
        }

        public UserAccount createNewCustomer(){
            return new UserAccount(this);
        }

        public UserAccount createNewDriver(String nic, String address, String driverLicense){
            this.nic = nic;
            this.address = address;
            this.driverLicense = driverLicense;
            return new UserAccount(this);
        }

        public UserAccount createExisttingCustomer(int id, String firstname, String lastname, String email, String password, String contactNumber){
            this.id = id;
            this.firstname = firstname;
            this.lastname = lastname;
            this.email = email;
            this.password = password;
            this.contactNumber = contactNumber;
            this.userType = "Customer";
            return new UserAccount(this);
        }

        public UserAccount createExistingDriver(int id, String firstname, String lastname, String email, String password, String contactNumber, String nic, String address, String driverLicense){
            this.id = id;
            this.firstname = firstname;
            this.lastname = lastname;
            this.email = email;
            this.password = password;
            this.contactNumber = contactNumber;
            this.nic = nic;
            this.address = address;
            this.driverLicense = driverLicense;
            this.userType = "Driver";
            return new UserAccount(this);
        }

        public UserAccount createExistingAdmin(int id, String email, String password, String contactNumber){
            this.id = id;
            this.email = email;
            this.password = password;
            this.contactNumber = contactNumber;
            this.userType = "Admin";
            return new UserAccount(this);
        }

    }
}
