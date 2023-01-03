package files.pe.headers.resources;

import files.pe.ByteArray;
import files.pe.headers.Header;
import files.pe.headers.SectionTableEntry;
import files.pe.types.DWORD;
import files.pe.types.TimeDate;
import files.pe.types.WORD;

public class ResourceDirectoryHeader extends Header {

    public final DWORD RSRC_CHARACTERISTICS;
    public final TimeDate TIME_STAMP;
    public final WORD MAJOR_VERSION;
    public final WORD MINOR_VERSION;
    public final WORD NUM_NAME_ENTRIES;
    public final WORD NUM_ID_ENTRIES;

    public ResourceDirectoryEntry[] entries;

    public ResourceDirectoryHeader(ByteArray bytes, SectionTableEntry section, int level) {
        this.RSRC_CHARACTERISTICS = new DWORD(bytes.readUInt(4), "Resource Characteristics"); // not used.
        this.TIME_STAMP = new TimeDate(bytes.readUInt(4), "Date");  // The time that the resource data was created by the resource compiler.
        this.MAJOR_VERSION = new WORD(bytes.readUShort(2), "Major Version");
        this.MINOR_VERSION = new WORD(bytes.readUShort(2), "Minor Version");
        this.NUM_NAME_ENTRIES = new WORD(bytes.readUShort(2), "Number of Name Entries");
        this.NUM_ID_ENTRIES = new WORD(bytes.readUShort(2), "Number of ID Entries");


        int numberOfNamedEntires = this.NUM_NAME_ENTRIES.get().intValue();
        int numberOfIDEntires = this.NUM_ID_ENTRIES.get().intValue();

        int numberOfEntries = numberOfNamedEntires + numberOfIDEntires;

        this.entries = new ResourceDirectoryEntry[numberOfEntries];

        for (int i=0;i<numberOfEntries;i++) {
            this.entries[i] = new ResourceDirectoryEntry(bytes, section, level+1);
        }
    }
}
