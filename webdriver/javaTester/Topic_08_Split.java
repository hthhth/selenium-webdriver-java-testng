package javaTester;

public class Topic_08_Split {
    public static void main(String[] args) {
//        String oldUrl = "http://the-internet.herokuapp.com/basic_auth";
//        String[] urlValues = oldUrl.split("//");
//        for (String urlValue : urlValues) {
//            System.out.println(urlValue);
//        }
        String likesString = "2,172 likes";
        String likesStringSplitted = likesString.split(" ")[0];
        String likesStringReplaced = likesStringSplitted.replace(",", "");
        int likes = Integer.parseInt(likesStringReplaced);
        System.out.println(likes>1000);
    }
}
