import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**************************************************************
 *    ------------ 正誤判定ファイル ------------
 * /src/TestCases/にあるファイル「in<X>.txt」を入力にして
 * 全てのテストケースの出力が「out<X>.txt」と一致するか
 * 一斉に確認します
 **************************************************************/

public class CodeChecker {

    public static void main(String[] args) throws Exception {
        InputStream defaultSystemIn = System.in;
        PrintStream defaultSystemErr = System.err;
        PrintStream defaultSystemOut = System.out;
        String checkerFileName = ".checker";
        String errorFileName = ".error";
        String dir = "./src/TestCases/";

        System.out.println();

        for (int fileId = 1;; fileId++) {
            String file = String.format("in%d.txt", fileId);
            try (
                FileInputStream fio = new FileInputStream(String.format("%s%s", dir, file));
            ) {
                System.setIn(fio);
                System.out.printf("\033[7m<<< Test Case #%2d : %s >>>\033[0m\n", fileId, file);

                try (
                    PrintStream ps = new PrintStream(String.format("%s%s", dir, checkerFileName), "UTF-8");
                    PrintStream es = new PrintStream(String.format("%s%s", dir, errorFileName), "UTF-8");
                ) {
                    System.setOut(ps);
                    System.setErr(es);
                    try {
                        Main.main(args);
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                } catch (Exception e) {
                    System.err.println("Input System ERROR");
                    return;
                } finally {
                    System.setOut(defaultSystemOut);
                    System.setErr(defaultSystemErr);
                }

                String correctFileName = String.format("out%d.txt", fileId);
                File errorFile = new File(String.format("%s%s", dir, errorFileName));
                File correctFile = new File(String.format("%s%s", dir, correctFileName));
                File checkerFile = new File(String.format("%s%s", dir, checkerFileName));
                try (
                    FileInputStream correct = new FileInputStream(correctFile);
                    FileInputStream checker = new FileInputStream(checkerFile);
                    BufferedReader error = new BufferedReader(new FileReader(errorFile));
                ) {
                    String line = error.readLine();
                    boolean isError = line != null;
                    if(isError) {
                        System.out.print("\033[31m");
                        do {
                            System.out.println(line);
                        } while((line = error.readLine()) != null);
                        System.out.print("\033[0m");
                    }
                    else {
                        try (
                            Scanner s1 = new Scanner(correct, "UTF-8");
                            Scanner s2 = new Scanner(checker, "UTF-8");
                        ) {
                            boolean isCorrect = true;
                            while (s1.hasNextLine() && s2.hasNextLine()) {
                                if (!s1.nextLine().trim().equals(s2.nextLine().trim())) {
                                    isCorrect = false;
                                    break;
                                }
                            }
                            if (isCorrect) {
                                while (s1.hasNextLine()) {
                                    if (!s1.nextLine().trim().isEmpty()) {
                                        isCorrect = false;
                                        break;
                                    }
                                }
                                while (s2.hasNextLine()) {
                                    if (!s2.nextLine().trim().isEmpty()) {
                                        isCorrect = false;
                                        break;
                                    }
                                }
                            }

                            if (isCorrect) System.out.println("\033[46mPass\033[0m");
                            else System.out.println("\033[41mWrong Answer\033[0m");
                        }
                    }
                } catch(Exception e) {
                    System.err.println("Input System ERROR");
                    return;
                } finally {
                    errorFile.delete();
                    checkerFile.delete();
                }
            } catch (FileNotFoundException e) {
                break;
            } catch (Exception e) {
                System.err.println("Input System ERROR");
                return;
            } finally {
                System.setIn(defaultSystemIn);
            }

            System.out.println();
        }
        
    }

}
