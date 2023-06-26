package project.users;

public enum UserRole {
    PATIENT("Patient"), DIETICIAN("Dietician");
    public String description;

     UserRole(String description) {
        this.description = description;
    }

    public static UserRole[] getUsers() {
         return UserRole.values();
    }
}
