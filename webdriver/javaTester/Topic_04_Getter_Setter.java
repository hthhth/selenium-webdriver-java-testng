package javaTester;

public class Topic_04_Getter_Setter {
    static String carName;
    public static void main(String[] args) {

        setCarName("Camry12");
        System.out.println(getCarName());
    }
    public static String getCarName(){
        return carName;
    }
    public static void setCarName(String car){
        carName = car;
    }
}
