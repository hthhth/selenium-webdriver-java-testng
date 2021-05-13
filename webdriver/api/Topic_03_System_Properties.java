package api;

public class Topic_03_System_Properties {
    public static void main(String[] args) {
        String projectLocation = System.getProperty("user.dir");
        System.out.println(projectLocation);

    }
}
