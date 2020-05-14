package Pages;



import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestClass {

    public static List<String> regextCheck(String regex, String str2check){


        List matchingNumber=new ArrayList();

        Pattern checkRegex=Pattern.compile(regex);
        Matcher regexMatcher=checkRegex.matcher(str2check);

        while(regexMatcher.find()){
            if(regexMatcher.group().length()!=0){
                matchingNumber.add(regexMatcher.group().trim());
            }
        }

        return matchingNumber;
    }

    public static void main(String[] args) {


       boolean result= Pattern.matches("d+" ,"Muammer 13453 Turan");

        System.out.println(regextCheck("\\d","Muammer 234 turan2 t85adsf"));

        System.out.println(result);

    }
}
