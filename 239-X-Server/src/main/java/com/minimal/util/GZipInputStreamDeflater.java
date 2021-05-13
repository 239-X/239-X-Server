package com.minimal.util;

import java.io.ByteArrayInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.CRC32;
import java.util.zip.Deflater;

/**
 * @Author: linzhiqiang
 * @Date: 2019/8/28 9:33
 * @Description:
 */
public class GZipInputStreamDeflater extends FilterInputStream {

    private static enum Stage {
        HEADER,
        DATA,
        FINALIZATION,
        TRAILER,
        FINISH
    }

    private GZipInputStreamDeflater.Stage stage = Stage.HEADER;

    private final Deflater deflater = new Deflater( Deflater.DEFLATED, true );
    private final CRC32 crc = new CRC32();

    /* GZIP header magic number */
    private final static int GZIP_MAGIC = 0x8b1f;

    private ByteArrayInputStream trailer = null;
    private ByteArrayInputStream header = new ByteArrayInputStream( new byte[] {
            (byte) GZIP_MAGIC, // Magic number (short)
            (byte) ( GZIP_MAGIC >> 8 ), // Magic number (short)
            Deflater.DEFLATED, // Compression method (CM)
            0, // Flags (FLG)
            0, // Modification time MTIME (int)
            0, // Modification time MTIME (int)
            0, // Modification time MTIME (int)
            0, // Modification time MTIME (int)
            0, // Extra flags (XFLG)
            0, // Operating system (OS)
    } );

    public GZipInputStreamDeflater(InputStream in) {
        super( in );
        crc.reset();
    }

    @Override
    public int read( byte[] b, int off, int len ) throws IOException {
        int read = -1;

        switch( stage ) {
            case FINISH:
                return -1;
            case HEADER:
                read = header.read( b, off, len );
                if( header.available() == 0 ) {
                    stage = Stage.DATA;
                }
                return read;
            case DATA:
                byte[] b2 = new byte[len];
                read = super.read( b2, 0, len );
                if( read <= 0 ) {
                    stage = Stage.FINALIZATION;
                    deflater.finish();
                    return 0;
                }
                else {
                    deflater.setInput( b2, 0, read );
                    crc.update( b2, 0, read );
                    read = 0;
                    while( !deflater.needsInput() && len - read > 0 ) {
                        read += deflater.deflate( b, off + read, len - read, Deflater.NO_FLUSH );
                    }
                    return read;
                }
            case FINALIZATION:
                if( deflater.finished() ) {
                    stage = Stage.TRAILER;

                    int crcVaue = (int) crc.getValue();
                    int totalIn = deflater.getTotalIn();

                    trailer = new ByteArrayInputStream( new byte[] {
                            (byte) ( crcVaue >> 0 ),
                            (byte) ( crcVaue >> 8 ),
                            (byte) ( crcVaue >> 16 ),
                            (byte) ( crcVaue >> 24 ),

                            (byte) ( totalIn >> 0 ),
                            (byte) ( totalIn >> 8 ),
                            (byte) ( totalIn >> 16 ),
                            (byte) ( totalIn >> 24 ),
                    } );

                    return 0;
                }
                else {
                    read = deflater.deflate( b, off, len, Deflater.FULL_FLUSH );
                    return read;
                }
            case TRAILER:
                read = trailer.read( b, off, len );
                if( trailer.available() == 0 ) {
                    stage = Stage.FINISH;
                }
                return read;
        }
        return -1;
    }

    @Override
    public void close( ) throws IOException {
        super.close();
        deflater.end();
        if( trailer != null ) {
            trailer.close();
        }
        header.close();
    }
}