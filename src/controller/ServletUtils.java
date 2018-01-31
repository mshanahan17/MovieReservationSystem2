package controller;

public class ServletUtils {


    public static String headWithTitle(String title) {
        return( "<!DOCTYPE html>\n" +
                "<HTML>\n" +
                "<HEAD><TITLE>" + title + "</TITLE></HEAD>\n");
    }

    public static String filter(String input) {
        if (!hasSpecialChars(input)) {
            return(input);
        }
        StringBuffer filtered = new StringBuffer(input.length());
        char c;
        for(int i=0; i<input.length(); i++) {
            c = input.charAt(i);
            switch(c) {
                case '<': filtered.append("&lt;"); break;
                case '>': filtered.append("&gt;"); break;
                case '"': filtered.append("&quot;"); break;
                case '&': filtered.append("&amp;"); break;
                default: filtered.append(c);
            }
        }
        return(filtered.toString());
    }

    private static boolean hasSpecialChars(String input) {
        boolean flag = false;
        if ((input != null) && (input.length() > 0)) {
            char c;
            for(int i=0; i<input.length(); i++) {
                c = input.charAt(i);
                switch(c) {
                    case '<': flag = true; break;
                    case '>': flag = true; break;
                    case '"': flag = true; break;
                    case '&': flag = true; break;
                }
            }
        }
        return(flag);
    }

    public static String validateInput(String str, String replacement){

        if(str == null || str.equals("")){
            str = replacement;
        }
        str = filter(str);

        return str;
    }

    public static void validateAllInput(String[] strArr){

        for(String str : strArr){
            str = filter(str);
        }
    }

    public static boolean validMatch(String str1, String str2){

        return str1.equals(str2);
    }
}
