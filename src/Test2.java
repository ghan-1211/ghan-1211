import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Test2 {
    public static void main(String[] args)  {
        try {
            FileWriter fileWrite = new FileWriter("F:\\test1.txt");
            for (int i = 0;i<8190;i++){
                fileWrite.write(97);
        }
            fileWrite.flush();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
