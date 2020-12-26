import java.io.FileNotFoundException;

public class BType extends Instruction {
    private final String opcode;
    private final int offset;
    private String decoded;

    public BType(String instruction) throws FileNotFoundException {
        super();
        String[] atoms = instruction.split(",[ ]*");
        opcode = atoms[0].toLowerCase();
        offset = Integer.parseInt(atoms[1].toLowerCase());
        decoded = "";
        decode();
        fillRest();
    }

    @Override
    protected boolean decode() {
        if (opcodeMap.containsKey(opcode))
            decoded += opcodeMap.get(opcode);
        else
            return false;

        if (offset >= 0)
            decoded += fillStart(Integer.toBinaryString(offset), 27);
        else
            decoded += Integer.toBinaryString(offset).substring(5);

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
