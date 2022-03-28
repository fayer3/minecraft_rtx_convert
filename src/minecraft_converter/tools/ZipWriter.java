package minecraft_converter.tools;

import minecraft_converter.exceptions.ExitNow;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static minecraft_converter.tools.Messages.showException;

public class ZipWriter implements Runnable {

    private ZipOutputStream zipOut = null;
    private final BlockingQueue<ZipOperation> operations;
    private final FileAccess access;
    private boolean run = true;
    private boolean finished = false;
    private Set<String> files = new HashSet<>();

    public ZipWriter(ZipOutputStream zipOut, FileAccess access) {
        this.zipOut = zipOut;
        operations = new LinkedBlockingQueue<>();
        this.access = access;
    }

    public void addOperation(ZipOperation operation) throws ExitNow{
        try {
            operations.put(operation);
        } catch (InterruptedException e) {
            throw new ExitNow("Interrupted", e);
        }
    }

    public void stop() {
        run = false;
    }

    public void setFinished() {
        finished = true;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while(run) {
            try {
                ZipOperation z = operations.poll(100, TimeUnit.MILLISECONDS);
                if (z != null) {
                    run = doOperation(z);
                } else if (finished) {
                    run = false;
                }
                if (Thread.interrupted()) {
                    run = false;
                }
            } catch (InterruptedException e) {
                // interrupted, stop execution
                run = false;
            }
        }
    }

    private boolean doOperation(ZipOperation operation) {
        if (!files.contains(operation.target)) {
            files.add(operation.target);
            if (operation.isPath)
                return zipAddFilePath(operation.source, operation.target);
            else if (operation.isStream)
                return zipAddFileStream(operation.target, operation.stream);
            return zipAddFileByte(operation.target, operation.data);
        }
        return true;
    }

    /**
     * reads a file from the source path an stores it inside the zip file at the location target
     * @param source path of the file to add
     * @param target path of the file to add inside the zip file
     */
    private boolean zipAddFilePath(String source, String target){
        try {
            byte[] buffer = access.getFileBytes(source, false);
            ZipEntry entry = new ZipEntry(target);
            zipOut.putNextEntry(entry);
            zipOut.write(buffer);
            zipOut.closeEntry();
        } catch(IOException|ExitNow e) {
            showException(e, "Error on adding File: '" + target + "' to pack");
            return false;
        }
        return true;
    }

    /**
     * reads a file from the source path an stores it inside the zip file at the location target
     * @param target path of the file to add inside the zip file
     * @param stream data of the file to add
     */
    private boolean zipAddFileStream(String target, InputStream stream){
        try {
            byte[] buffer = access.getStreamBytes(stream);
            ZipEntry entry = new ZipEntry(target);
            zipOut.putNextEntry(entry);
            zipOut.write(buffer);
            zipOut.closeEntry();
        } catch(IOException|ExitNow e) {
            showException(e, "Error on adding File: '" + target + "' to pack");
            return false;
        }
        return true;
    }


    /**
     * adds a file with the data data at the path target to the current zip file
     * @param target path of the file to add inside the zip file
     * @param data data of the file to add
     */
    private boolean zipAddFileByte(String target, byte[] data){
        try {
            ZipEntry entry = new ZipEntry(target);
            zipOut.putNextEntry(entry);
            zipOut.write(data);
            zipOut.closeEntry();
        } catch(IOException e) {
            showException(e, "Error on adding File: '" + target + "' to pack");
            return false;
        }
        return true;
    }
}
