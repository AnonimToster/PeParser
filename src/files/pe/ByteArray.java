package files.pe;

import java.io.ByteArrayInputStream;

import dorkbox.bytes.LittleEndian;
import dorkbox.bytes.UByte;
import dorkbox.bytes.UInteger;
import dorkbox.bytes.ULong;
import dorkbox.bytes.UShort;
import dorkbox.os.OS;


public class ByteArray extends ByteArrayInputStream {

    public ByteArray(byte[] bytes) {
        super(bytes);
    }

    public String readAsciiString(int length) {
        return new String(copyBytes(length), OS.US_ASCII).trim();
    }

    public
    ULong readULong(int length) {
        ULong result = LittleEndian.ULong_.from(this.buf, this.pos, length);
        this.pos += length;
        return result;
    }

    public
    UInteger readUInt(int length) {
        UInteger result = LittleEndian.UInt_.from(this.buf, this.pos, length);
        this.pos += length;
        return result;
    }

    public
    UShort readUShort(int length) {
        UShort result = LittleEndian.UShort_.from(this.buf, this.pos, length);
        this.pos += length;
        return result;
    }

    public
    UByte readUByte() {
        UByte b = UByte.valueOf(this.buf[this.pos]);
        this.pos++;
        return b;
    }

    public byte readRaw(int offset) {
        return this.buf[this.pos + offset];
    }

    public byte[] copyBytes(int length) {
        byte[] data = new byte[length];
        super.read(data, 0, length);
        return data;
    }

    public void mark() {
        super.mark(0);
    }

    public void seek(int position) {
        this.pos = position;
    }

    public int position() {
        return this.pos;
    }

    public int marked() {
        return this.mark;
    }
}
