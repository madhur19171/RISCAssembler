import java.io.*;
import java.util.Scanner;

public class Assemble {
    public static void main(String[] args) throws IOException {
//        RType inst1 = new RType("add, r2, r1, r0");
//        System.out.println(inst1.getDecoded());
//        IType inst2 = new IType("sub, r3, r1, -10");
//        System.out.println(inst2.getDecoded());
//        BType inst3 = new BType("beq, -5");
//        System.out.println(inst3.getDecoded());
//        LSType inst4 = new LSType("ld, r5, 34[r2]");
//        System.out.println(inst4.getDecoded());
        Scanner kb = new Scanner(System.in);
        System.out.print("Enter File Name: ");
        String fineName = kb.nextLine();
        File file = new File("src/Source/" + fineName);
        PrintWriter out = new PrintWriter("src/Output/" + fineName.split("\\.")[0] + ".mem");
        Scanner sc = new Scanner(file);
        String str = "";
        while (sc.hasNextLine()) {
            str = sc.nextLine();
            str = decode(str);
            System.out.println(str);
            out.println(str);
        }
        out.flush();
        out.close();
    }

    private static String decode(String instruction) throws FileNotFoundException {

        instruction = preprocess(instruction);
        String[] split = instruction.split(",[ ]*");
        int count = split.length - 1;

        switch (count) {
            case 1:
                return new BType(instruction).getDecoded();
            case 2:
                return new LSType(instruction).getDecoded();
            case 3:
                if (split[3].charAt(0) == 'r')
                    return new RType(instruction).getDecoded();
                else
                    return new IType(instruction).getDecoded();
            default:
                return "";
        }
    }

    private static String preprocess(String instruction) {
        String[] split = instruction.split(",[ ]*");

        if (split[0].equalsIgnoreCase("read") || split[0].equalsIgnoreCase("cmp")) {
            instruction = split[0] + ", r0" + instruction.substring(instruction.indexOf(","));
        } else if (split[0].equalsIgnoreCase("mov") || split[0].equalsIgnoreCase("not")) {
            instruction = split[0] + ", " + split[1] + ", r0, " + split[2];
        } else if (split[0].equalsIgnoreCase("bxlr"))
            instruction = split[0] + ", " + 0;
        return instruction;
    }
}
