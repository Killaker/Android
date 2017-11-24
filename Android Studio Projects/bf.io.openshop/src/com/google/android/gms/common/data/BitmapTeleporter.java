package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.safeparcel.*;
import android.util.*;
import java.nio.*;
import android.os.*;
import java.io.*;
import android.graphics.*;

public class BitmapTeleporter implements SafeParcelable
{
    public static final Parcelable$Creator<BitmapTeleporter> CREATOR;
    final int mVersionCode;
    ParcelFileDescriptor zzIq;
    final int zzabB;
    private Bitmap zzaiY;
    private boolean zzaiZ;
    private File zzaja;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    BitmapTeleporter(final int mVersionCode, final ParcelFileDescriptor zzIq, final int zzabB) {
        this.mVersionCode = mVersionCode;
        this.zzIq = zzIq;
        this.zzabB = zzabB;
        this.zzaiY = null;
        this.zzaiZ = false;
    }
    
    public BitmapTeleporter(final Bitmap zzaiY) {
        this.mVersionCode = 1;
        this.zzIq = null;
        this.zzabB = 0;
        this.zzaiY = zzaiY;
        this.zzaiZ = true;
    }
    
    private void zza(final Closeable closeable) {
        try {
            closeable.close();
        }
        catch (IOException ex) {
            Log.w("BitmapTeleporter", "Could not close stream", (Throwable)ex);
        }
    }
    
    private FileOutputStream zzqb() {
        if (this.zzaja == null) {
            throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
        }
        File file;
        try {
            final File tempFile;
            file = (tempFile = File.createTempFile("teleporter", ".tmp", this.zzaja));
            final FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
            final BitmapTeleporter bitmapTeleporter = this;
            final File file2 = file;
            final int n = 268435456;
            final ParcelFileDescriptor parcelFileDescriptor = ParcelFileDescriptor.open(file2, n);
            bitmapTeleporter.zzIq = parcelFileDescriptor;
            final File file3 = file;
            file3.delete();
            return fileOutputStream;
        }
        catch (IOException ex) {
            throw new IllegalStateException("Could not create temporary file", ex);
        }
        try {
            final File tempFile = file;
            final FileOutputStream fileOutputStream = new FileOutputStream(tempFile);
            final BitmapTeleporter bitmapTeleporter = this;
            final File file2 = file;
            final int n = 268435456;
            final ParcelFileDescriptor parcelFileDescriptor = ParcelFileDescriptor.open(file2, n);
            bitmapTeleporter.zzIq = parcelFileDescriptor;
            final File file3 = file;
            file3.delete();
            return fileOutputStream;
        }
        catch (FileNotFoundException ex2) {
            throw new IllegalStateException("Temporary file is somehow already deleted");
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    public void release() {
        if (this.zzaiZ) {
            return;
        }
        try {
            this.zzIq.close();
        }
        catch (IOException ex) {
            Log.w("BitmapTeleporter", "Could not close PFD", (Throwable)ex);
        }
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        Label_0103: {
            if (this.zzIq != null) {
                break Label_0103;
            }
            final Bitmap zzaiY = this.zzaiY;
            final ByteBuffer allocate = ByteBuffer.allocate(zzaiY.getRowBytes() * zzaiY.getHeight());
            zzaiY.copyPixelsToBuffer((Buffer)allocate);
            final byte[] array = allocate.array();
            final DataOutputStream dataOutputStream = new DataOutputStream(this.zzqb());
            try {
                dataOutputStream.writeInt(array.length);
                dataOutputStream.writeInt(zzaiY.getWidth());
                dataOutputStream.writeInt(zzaiY.getHeight());
                dataOutputStream.writeUTF(zzaiY.getConfig().toString());
                dataOutputStream.write(array);
                this.zza(dataOutputStream);
                zza.zza(this, parcel, n | 0x1);
                this.zzIq = null;
            }
            catch (IOException ex) {
                throw new IllegalStateException("Could not write into unlinked file", ex);
            }
            finally {
                this.zza(dataOutputStream);
            }
        }
    }
    
    public void zzc(final File zzaja) {
        if (zzaja == null) {
            throw new NullPointerException("Cannot set null temp directory");
        }
        this.zzaja = zzaja;
    }
    
    public Bitmap zzqa() {
        Label_0103: {
            if (this.zzaiZ) {
                break Label_0103;
            }
            final DataInputStream dataInputStream = new DataInputStream((InputStream)new ParcelFileDescriptor$AutoCloseInputStream(this.zzIq));
            try {
                final byte[] array = new byte[dataInputStream.readInt()];
                final int int1 = dataInputStream.readInt();
                final int int2 = dataInputStream.readInt();
                final Bitmap$Config value = Bitmap$Config.valueOf(dataInputStream.readUTF());
                dataInputStream.read(array);
                this.zza(dataInputStream);
                final ByteBuffer wrap = ByteBuffer.wrap(array);
                final Bitmap bitmap = Bitmap.createBitmap(int1, int2, value);
                bitmap.copyPixelsFromBuffer((Buffer)wrap);
                this.zzaiY = bitmap;
                this.zzaiZ = true;
                return this.zzaiY;
            }
            catch (IOException ex) {
                throw new IllegalStateException("Could not read from parcel file descriptor", ex);
            }
            finally {
                this.zza(dataInputStream);
            }
        }
    }
}
