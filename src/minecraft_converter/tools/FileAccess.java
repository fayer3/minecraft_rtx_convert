package minecraft_converter.tools;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import minecraft_converter.exceptions.ExitNow;

import java.io.*;
import java.util.Enumeration;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import static java.lang.Math.toIntExact;
import static minecraft_converter.tools.Messages.showException;

/**
 * manages access to the filesystem, and distinguishes if the source is a ZipFile of a folder
 */
public class FileAccess {
    private boolean isZip = false;
    private String zipOffsetPath = "";
    private ZipFile zipSource = null;

    private File sourceFile = null;

    private ZipOutputStream zipOut = null;
    private ByteArrayOutputStream zipOutData = null;
    private File target = null;

    private ZipWriter zipper;
    private Thread zipperThread;

    public FileAccess() {
        zipOutData = new ByteArrayOutputStream();
        zipOut = new ZipOutputStream(zipOutData);
        zipper = new ZipWriter(zipOut, this);
        zipperThread = new Thread(zipper);
        zipperThread.setDaemon(true);
        zipperThread.start();
    }

    public boolean isZip() {
        return isZip;
    }

    public void setZip(boolean zip) {
        isZip = zip;
    }

    public String getZipOffsetPath() {
        return zipOffsetPath;
    }

    public ZipFile getZipSource() {
        return zipSource;
    }

    /**
     * loads the ZipFile object, if the source is zip file
     * @throws IOException if an error occurs on creating the ZipFile object
     */
    public void loadZip() throws IOException {
        if (isZip) {
            zipSource = new ZipFile(sourceFile);
        }
    }

    public File getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(File sourceFile) throws IOException{
        this.sourceFile = sourceFile;
    }

    public void setTarget(File target) {
        this.target = target;
    }

    /**
     * reads the file content of the file at path and returns it
     * @param path path to the file
     * @param nullOnMissing if true, null is returned if the file is missing
     * @return content of the file, or null if the file is missing and nullOnMissing is true
     * @throws ExitNow if an error occurs
     */
    public byte[] getFileBytes(String path, boolean nullOnMissing) throws ExitNow {
        try {
            InputStream fileIn = null;
            long size = 0;
            if (isZip) {
                if (!zipOffsetPath.isEmpty())
                    path = zipOffsetPath + path;
                ZipEntry entry = zipSource.getEntry(path);
                if (entry == null)
                    if (nullOnMissing)
                        return null;
                    else
                        throw new ExitNow("'"+path+"' not in zip File");
                size = entry.getSize();
                fileIn = zipSource.getInputStream(entry);
            } else {
                File file = new File(sourceFile, path);
                if (!file.exists()) {
                    if (nullOnMissing)
                        return null;
                    else
                        throw new ExitNow("'"+path+"' not found");
                }
                size = file.length();
                fileIn = new FileInputStream(file);
            }
            byte[] buffer = new byte[toIntExact(size)];
            int count = 0;
            int read = 1;
            while (read > 0 && count < size) {
                read = fileIn.read(buffer, count, buffer.length-count);
                if (read != -1) {
                    count += read;
                }
            }
            return  buffer;
        } catch (Exception e) {
            showException(e, "error reading file: '"+ path +"'");
            throw new ExitNow("getFileBytes");
        }
    }

    /**
     * reads the file content of the file at path and returns it
     * @param stream InputStream to get data from
     * @return content of the file, or null if the file is missing and nullOnMissing is true
     * @throws ExitNow if an error occurs
     */
    public byte[] getStreamBytes(InputStream stream) throws ExitNow {
        try {
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            int read = 1;
            byte[] temp = new byte[1024];
            while (read > 0) {
                read = stream.read(temp, 0, 1024);
                if (read != -1) {
                    buffer.write(temp, 0, read);
                }
            }
            return  buffer.toByteArray();
        } catch (Exception e) {
            showException(e, "error reading data from stream");
            throw new ExitNow("getFileBytes");
        }
    }

    // reading file data from https://www.baeldung.com/convert-input-stream-to-a-file #2. Convert Using Plain Java

    /**
     * reads a file from the source path an stores it inside the zip file at the location target
     * @param source path of the file to add
     * @param target path of the file to add inside the zip file
     * @throws ExitNow if an error occurs
     */
    public void zipAddFilePath(String source, String target) throws ExitNow{
        zipper.addOperation(new ZipOperation(source, target));
        /*try {
            byte[] buffer = getFileBytes(source, false);
            ZipEntry entry = new ZipEntry(target);
            zipOut.putNextEntry(entry);
            zipOut.write(buffer);
            zipOut.closeEntry();
        } catch(IOException e) {
            showException(e, "Error on adding File: '" + target + "' to pack");
            throw new ExitNow("zipAddFilePath");
        }*/
    }

    /**
     * adds a file with the data data at the path target to the current zip file
     * @param target path of the file to add inside the zip file
     * @param data data of the file to add
     * @throws ExitNow if an error occurs
     */
    public void zipAddFileByte(String target, byte[] data) throws ExitNow{
        zipper.addOperation(new ZipOperation(target, data));
        /*try {
            ZipEntry entry = new ZipEntry(target);
            zipOut.putNextEntry(entry);
            zipOut.write(data);
            zipOut.closeEntry();
        } catch(IOException e) {
            showException(e, "Error on adding File: '" + target + "' to pack");
            throw new ExitNow("zipAddFileByte");
        }*/
    }

    /**
     * adds a file with the data from the specified stream to the current zip file
     * @param target path of the file to add inside the zip file
     * @param stream data of the file to add
     * @throws ExitNow if an error occurs
     */
    public void zipAddFileStream(String target, InputStream stream) throws ExitNow{
        zipper.addOperation(new ZipOperation(target, stream));
        /*try {
            ZipEntry entry = new ZipEntry(target);
            zipOut.putNextEntry(entry);
            zipOut.write(data);
            zipOut.closeEntry();
        } catch(IOException e) {
            showException(e, "Error on adding File: '" + target + "' to pack");
            throw new ExitNow("zipAddFileByte");
        }*/
    }

    /**
     * writes the zip file to the specified target of this object
     * @throws ExitNow if an error occurs
     */
    public void writeZip() throws ExitNow{
        try {
            zipper.setFinished();
            zipperThread.join();
            zipOut.close();
            OutputStream outStream = new FileOutputStream(target);
            outStream.write(zipOutData.toByteArray());
            outStream.close();
        } catch(InterruptedException|IOException e) {
            showException(e, "Error on writing final pack");
            throw new ExitNow("writeZip");
        }
    }

    /**
     * checks if the resourcepack files are in a folder inside the zip file
     */
    public void findZipOffset() {
        String offset = "";
        boolean start = true;
        Enumeration<? extends ZipEntry> entries = zipSource.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if (start) {
                offset = entry.getName();
                start = false;
            }
            else {
                offset = sameBeginning(offset,entry.getName());
            }
        }

        zipOffsetPath = offset;
    }

    /**
     * finds the matching part of first and second
     *
     * @param first first String
     * @param second second String
     * @return string that matches
     */
    private String sameBeginning(String first, String second) {
        String shorter = first.length() < second.length() ? first : second;
        String longer = first.length() >= second.length() ? first : second;

        if (longer.startsWith(shorter)) {
            return shorter;
        }
        int i = 0;
        int j = shorter.length()-1;
        while(i<=j){
            int mid = i + (j-i)/2;
            if(shorter.charAt(mid) == longer.charAt(mid)) {
                if(mid+1<shorter.length() && shorter.charAt(mid+1) != longer.charAt(mid+1)){
                    i = mid+1;
                    break;
                }
                i = mid+1;
            }else{
                j = mid-1;
            }
        }
        return shorter.substring(0, i);
    }

    /**
     * calls the appropriate count files Method for the current source file
     * @return number of files in the sourceFile
     */
    public int countFiles() {
        if (isZip)
            return countFilesZip();
        return countFiles(sourceFile);
    }

    /**
     * counts number of files in the specified folder
     * @param start folder to count files in
     * @return number of files
     */
    private int countFiles(File start) {
        int count = 0;
        File[] childs = start.listFiles();
        if (childs != null && start.isDirectory()) {
            for (File f : childs)
                if (f.isDirectory())
                    count += countFiles(f);
                else
                    count++;
        }
        return count;
    }

    /**
     * counts number of files in the zip file of this object
     * @return number of files
     */
    private int countFilesZip(){
        int count = 0;
        Enumeration<? extends ZipEntry> entries = zipSource.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();

            if (!entry.isDirectory()) {
                count++;
            }
        }
        return count;
    }

    /**
     * unreference files and close streams
     */
    public void close() {
        zipOutData = null;
        sourceFile = null;
        zipper.stop();
        try {
            zipperThread.join();
        } catch (InterruptedException e) {
            // nothing to do is closing
        }
        try {
            if (zipOut != null)
                zipOut.close();
            if (zipSource != null)
                zipSource.close();
        }catch (IOException e) {
            // nothing to do is closing
        }
        zipOut = null;
        zipSource = null;
        isZip = false;
        zipOffsetPath = "";
    }
}
