
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        if(args.length<5){
            System.out.println("ERRO");
        }
        System.out.println(args[0]);
        //Scanner scanner = new Scanner(System.in);
        FileInputStream fin = new FileInputStream("candidatos.csv");
        InputStreamReader r = new InputStreamReader(fin, "ISO-8859-1");
        BufferedReader br = new BufferedReader(r);

    }
}


