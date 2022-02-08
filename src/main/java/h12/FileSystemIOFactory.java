package h12;

import java.io.*;

public class FileSystemIOFactory implements IOFactory
{
    /**
     * Creates a {@link BufferedReader} for reading from the provided resource.
     *
     * @param resourceName The resource to read from
     * @return A {@link BufferedReader} to read from the resource
     * @throws IOException                   If an I/O error occurs
     * @throws UnsupportedOperationException If the implementation does not {@link #supportsReader() support reading}
     */
    @Override
    public BufferedReader createReader(String resourceName) throws IOException
    {
        FileReader fr = new FileReader(resourceName);
        BufferedReader br = new BufferedReader(fr);

        return br;
    }

    /**
     * Creates a {@link BufferedReader} for writing to the provided resource.
     *
     * @param resourceName The resource to write to
     * @return A {@link BufferedWriter} to write to the resource
     * @throws IOException                   If an I/O error occurs
     * @throws UnsupportedOperationException If the implementation does not {@link #supportsWriter() support writing}
     */
    @Override
    public BufferedWriter createWriter(String resourceName) throws IOException
    {
        FileWriter fw = new FileWriter(resourceName);
        BufferedWriter bw = new BufferedWriter(fw);
        return bw;
    }

    /**
     * Whether the implementation supports reading.
     *
     * @return Whether the implementation supports reading
     */
    @Override
    public boolean supportsReader()
    {
        return true;
    }

    /**
     * Whether the implementation supports writing.
     *
     * @return Whether the implementation supports writing
     */
    @Override
    public boolean supportsWriter()
    {
        return true;
    }
}
