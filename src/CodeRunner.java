import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**************************************************************
 *    ------------ 一斉実行ファイル ------------
 * /src/TestCases/にあるファイル「in<X>.txt」を入力にして
 * 全てのテストケースを一斉に実行するようにします
 **************************************************************/

public class CodeRunner {

    public static void main(String[] args) throws Exception {
        InputStream defaultSystemIn = System.in;
        String dir = "./src/TestCases/";

        System.out.println();

        for(int fileId = 1; ; fileId++) {
            String fileName = String.format("in%d.txt", fileId);
            try (
                FileInputStream fio = new FileInputStream(String.format("%s%s", dir, fileName));
            ) {
                System.setIn(fio);
                System.out.printf("\033[7m<<< Test Case #%2d : %s >>>\033[0m\n", fileId, fileName);

                try {
                    Main.main(args);
                } catch (Exception e) {
                    System.err.println(e);
                }
            } catch(FileNotFoundException e) {
                break;
            } catch(Exception e) {
                System.err.println("Input System ERROR");
            } finally {
                System.setIn(defaultSystemIn);
            }

            System.out.println();
        }
        
    }

}
