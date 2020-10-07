import java.io.File;
import java.io.FileReader;
public class Main{
    private String keyWord[] = {"BEGIN","END","FOR","IF","THEN","ELSE"};
    private char ch;
    boolean isKey(String str)
    {
        for(int i = 0;i < keyWord.length;i++)
        {
            if(keyWord[i].equals(str))
                return true;
        }
        return false;
    }
    boolean isLetter(char letter)
    {
        if((letter >= 'a' && letter <= 'z')||(letter >= 'A' && letter <= 'Z'))
            return true;
        else
            return false;
    }
    boolean isDigit(char digit)
    {
        if(digit >= '0' && digit <= '9')
            return true;
        else
            return false;
    }
    void analyze(char[] chars)
    {
        String arr = "";
        for(int i = 0;i< chars.length-1;i++) {
            ch = chars[i];
            arr = "";
            if(ch == ' '||ch == '\t'||ch == '\n'||ch == '\r'){}
            else if(isLetter(ch)){
                while(isLetter(ch)||isDigit(ch)){
                    arr += ch;
                    ch = chars[++i];
                }
                i--;
                if(isKey(arr)){
                    System.out.println(arr.substring(0, 1).toUpperCase() + arr.substring(1).toLowerCase());
                }
                else{
                    System.out.println("Ident("+arr+")");
                }
            }
            else if(isDigit(ch))
            {
                while(isDigit(ch))
                {
                    arr = arr + ch;
                    ch = chars[++i];
                }
                i--;
                System.out.println("Int("+arr+")");
            }
            else switch(ch){
                    case '+':System.out.println("Plus");break;
                    case '*':System.out.println("Star");break;

                    case ',':System.out.println("Comma");break;
                    case '(':System.out.println("LParenthesis");break;
                    case ')':System.out.println("RParenthesis");break;
                    case ':':{
                        ch = chars[++i];
                        if(ch == '=')System.out.println("Assign");
                        else {
                            System.out.println("Colon");
                            i--;
                        }
                    }break;
                    default: {
                        System.out.println("Unknown");
                        return;
                    }
                }
        }
    }
    public static void main(String[] args) throws Exception {
        String filepath=args[0];
        File file = new File(filepath);
        FileReader reader = new FileReader(file);
        int length = (int) file.length();
        char buf[] = new char[length+1];
        reader.read(buf);
        reader.close();
        new Main().analyze(buf);

    }
}