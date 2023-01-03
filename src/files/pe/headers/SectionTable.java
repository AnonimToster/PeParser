package files.pe.headers;

import java.util.ArrayList;
import java.util.List;

import files.pe.ByteArray;

public class SectionTable extends Header {

    public List<SectionTableEntry> sections;

    public SectionTable(ByteArray bytes, int numberOfEntries) {

        this.sections = new ArrayList<>(numberOfEntries);

        bytes.mark();
        for (int i=0;i<numberOfEntries;i++) {
            int offset = i*SectionTableEntry.ENTRY_SIZE;
            bytes.skip(offset);
            SectionTableEntry sectionTableEntry = new SectionTableEntry(bytes, i+1, offset, SectionTableEntry.ENTRY_SIZE);
            this.sections.add(sectionTableEntry);
            bytes.reset();
        }
    }
}
