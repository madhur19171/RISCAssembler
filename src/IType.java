import java.io.FileNotFoundException;

public class IType extends Instruction {
    private final String opcode;
    private final int rd;
    private final int r1;
    private final int immx;
    private String decoded;

    public IType(String instruction) throws FileNotFoundException {
        super();
        String[] atoms = instruction.split(",[ ]*");
        opcode = atoms[0].toLowerCase();
        rd = Integer.parseInt(atoms[1].toLowerCase().substring(1));
        r1 = Integer.parseInt(atoms[2].toLowerCase().substring(1));
        immx = Integer.parseInt(atoms[3].toLowerCase());
        decoded = "";
        decode();
        fillRest();
    }

    @Override
    protected boolean decode() {
        if (opcodeMap.containsKey(opcode))
            decoded += opcodeMap.get(opcode) + "1";
        else
            return false;

        if (rd > 31)
            return false;
        else
            decoded += fillStart(Integer.toBinaryString(rd), 5);


        if (r1 > 31)
            return false;
        else
            decoded += fillStart(Integer.toBinaryString(r1), 5);

        if (immx >= 0)
            decoded += fillStart(Integer.toBinaryString(immx), 16);
        else
            decoded += Integer.toBinaryString(immx).substring(16);

        return true;
    }

    @Override
    protected void fillRest() {
        int len = decoded.length();
        for (int i = 0; i < 32 - len; i++)
            decoded += "0";
    }

    public String getDecoded() {
        return decoded;
    }
}
