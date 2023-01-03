package files.pe.headers;

import files.pe.ByteArray;
import files.pe.types.CoffCharacteristics;
import files.pe.types.DWORD;
import files.pe.types.MachineType;
import files.pe.types.TimeDate;
import files.pe.types.WORD;

public class COFFFileHeader extends Header {

    public static final int HEADER_SIZE = 20;

    public final MachineType Machine;

    public final WORD NumberOfSections;

    public final TimeDate TimeDateStamp;

    public final DWORD PointerToSymbolTable;

    public final DWORD NumberOfSymbols;

    public final WORD SizeOfOptionalHeader;

    public final CoffCharacteristics Characteristics;

    public COFFFileHeader(ByteArray bytes) {
        this.Machine    = h(new MachineType(bytes.readUShort(2), "machine type"));
        this.NumberOfSections = h(new WORD(bytes.readUShort(2), "number of sections"));
        this.TimeDateStamp  = h(new TimeDate(bytes.readUInt(4), "time date stamp"));
        this.PointerToSymbolTable = h(new DWORD(bytes.readUInt(4), "pointer to symbol table"));
        this.NumberOfSymbols      = h(new DWORD(bytes.readUInt(4), "number of symbols"));
        this.SizeOfOptionalHeader = h(new WORD(bytes.readUShort(2), "size of optional header"));
        this.Characteristics    = h(new CoffCharacteristics(bytes.readUShort(2), "characteristics"));
    }
}
