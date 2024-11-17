/**
 * 
 */
package rentasad.library.tools.sevenZip;

import java.io.IOException;
import java.io.RandomAccessFile;

import net.sf.sevenzipjbinding.IOutCreateArchive7z;
import net.sf.sevenzipjbinding.IOutCreateCallback;
import net.sf.sevenzipjbinding.IOutItem7z;
import net.sf.sevenzipjbinding.ISequentialInStream;
import net.sf.sevenzipjbinding.SevenZip;
import net.sf.sevenzipjbinding.SevenZipException;
import net.sf.sevenzipjbinding.impl.OutItemFactory;
import net.sf.sevenzipjbinding.impl.RandomAccessFileOutStream;
import net.sf.sevenzipjbinding.util.ByteArrayStream;
import rentasad.library.tools.sevenZip.CompressArchiveStructure.Item;

public class CompressNonGeneric7z {
    /**
     * The callback provides information about archive items.
     */
    private final class MyCreateCallback 
            implements IOutCreateCallback<IOutItem7z> {

        public void setOperationResult(boolean operationResultOk)
                 {
            // Track each operation result here
        }

        public void setTotal(long total)
		{
            // Track operation progress here
        }

        public void setCompleted(long complete)
		{
            // Track operation progress here
        }

        /**
         * Retrieves information about an item at the specified index.
         *
         * @param index the index of the item.
         * @param outItemFactory the factory used to create the output item.
         * @return the output item containing either directory or file information based on the content.
         */
        public IOutItem7z getItemInformation(int index,
                OutItemFactory<IOutItem7z> outItemFactory) {
            IOutItem7z item = outItemFactory.createOutItem();

            if (items[index].getContent() == null) {
                // Directory
                item.setPropertyIsDir(true);
            } else {
                // File
                item.setDataSize((long) items[index].getContent().length);
            }

            item.setPropertyPath(items[index].getPath());

            return item;
        }

        /**
         * Retrieves a sequential input stream for the specified item index.
         *
         * @param i the index of the item to retrieve the stream from.
         * @return the input stream containing the item's content, or null if the content is null.
         */
        public ISequentialInStream getStream(int i)
		{
            if (items[i].getContent() == null) {
                return null;
            }
            return new ByteArrayStream(items[i].getContent(), true);
        }
    }

    private Item[] items;

    /**
     * Compresses an array of items into a 7z archive file.
     *
     * @param itemsArgument An array of items to be compressed, where each item contains a path and content.
     * @param filename The name of the output file where the compressed archive will be written.
     */
    public void compress(Item[] itemsArgument, String filename) {
        items = itemsArgument;

        boolean success = false;
        RandomAccessFile raf = null;
        IOutCreateArchive7z outArchive = null;
        try {
            raf = new RandomAccessFile(filename, "rw");

            // Open out-archive object
            outArchive = SevenZip.openOutArchive7z();

            // Configure archive
            outArchive.setLevel(5);
            outArchive.setSolid(true);

            // Create archive
            outArchive.createArchive(new RandomAccessFileOutStream(raf),
                    items.length, new MyCreateCallback());

            success = true;
        } catch (SevenZipException e) {
            System.err.println("7z-Error occurs:");
            // Get more information using extended method
            e.printStackTraceExtended();
        } catch (Exception e) {
            System.err.println("Error occurs: " + e);
        } finally {
            if (outArchive != null) {
                try {
                    outArchive.close();
                } catch (IOException e) {
                    System.err.println("Error closing archive: " + e);
                    success = false;
                }
            }
            if (raf != null) {
                try {
                    raf.close();
                } catch (IOException e) {
                    System.err.println("Error closing file: " + e);
                    success = false;
                }
            }
        }
        if (success) {
            System.out.println("Compression operation succeeded");
        }
    }
}

