package files.pe.headers;

import files.pe.types.AsciiString;
import files.pe.types.HeaderDefinition;
import files.pe.ByteArray;
import files.pe.types.DWORD;
import files.pe.types.SectionCharacteristics;
import files.pe.types.WORD;

public class SectionTableEntry extends Header {
    public final static int ENTRY_SIZE = 40;

    public final AsciiString NAME;

    public final DWORD VIRTUAL_SIZE;

    public final DWORD VIRTUAL_ADDRESS;

    public final DWORD SIZE_OF_RAW_DATA;

    public final DWORD POINTER_TO_RAW_DATA;

    public final DWORD POINTER_TO_RELOCATIONS;

    public final DWORD POINTER_TO_LINE_NUMBERS;

    public final WORD NUMBER_OF_RELOCATIONS;

    public final WORD NUMBER_OF_LINE_NUMBERS;

    public final SectionCharacteristics CHARACTERISTICS;

    public SectionTableEntry(ByteArray bytes, int entryNumber, int offset, int size) {

        h(new HeaderDefinition("Section table entry: " + entryNumber));

        this.NAME = h(new AsciiString(bytes, 8, "name"));
        this.VIRTUAL_SIZE = h(new DWORD(bytes.readUInt(4), "virtual size"));
        this.VIRTUAL_ADDRESS = h(new DWORD(bytes.readUInt(4), "virtual address"));

        this.SIZE_OF_RAW_DATA = h(new DWORD(bytes.readUInt(4), "size of raw data"));
        this.POINTER_TO_RAW_DATA = h(new DWORD(bytes.readUInt(4), "pointer to raw data"));
        this.POINTER_TO_RELOCATIONS = h(new DWORD(bytes.readUInt(4), "pointer to relocations"));
        this.POINTER_TO_LINE_NUMBERS = h(new DWORD(bytes.readUInt(4), "pointer to line numbers"));

        this.NUMBER_OF_RELOCATIONS = h(new WORD(bytes.readUShort(2), "number of relocations"));
        this.NUMBER_OF_LINE_NUMBERS = h(new WORD(bytes.readUShort(2), "number of line numbers"));
        this.CHARACTERISTICS = h(new SectionCharacteristics(bytes.readUInt(4), "characteristics"));
    }
}
