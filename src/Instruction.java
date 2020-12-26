import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

abstract class Instruction {
    protected static HashMap<String, String> opcodeMap;

    public Instruction() throws FileNotFoundException {
        File file = new File("src/OpcodeMap");
        Scanner sc = new Scanner(file);
        String str = "";
        if (opcodeMap == null) {
            opcodeMap = new HashMap<>();
            while (sc.hasNextLine()) {
                str = sc.nextLine();
                opcodeMap.put(str.split(" ")[0], str.split(" ")[1]);
            }
        }
    }

    protected String fillStart(String str, int length) {
//        System.out.println(str + "\t" + str.length());
        int len = str.length();
        StringBuilder strBuilder = new StringBuilder(str);
        for (int i = 0; i < length - len; i++)
            strBuilder.insert(0, "0");
        str = strBuilder.toString();
//        System.out.println(str + "\n\n");
        return str;
    }

    protected abstract void fillRest();

    protected abstract boolean decode();
}
