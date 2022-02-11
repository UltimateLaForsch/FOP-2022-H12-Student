package h12;

import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ResourceIOFactory implements IOFactory {
    /**
     * Creates a BufferedReader for reading from the provided resource.
     * @param resourceName The resource to read from
     * @return a buffered reader object
     * @throws IOException
     */
    @Override
    public BufferedReader createReader(String resourceName) throws IOException {
        final @Nullable InputStream resourceStream = getClass().getResourceAsStream(resourceName);
        if (resourceStream == null) {
            throw new IOException(String.format("Could not find %s/%s",
                getClass().getPackageName().replace('.', '/'), resourceName));
        }
        return new BufferedReader(new InputStreamReader(resourceStream, StandardCharsets.UTF_8));
    }

    /**
     * Creates a BufferedReader for writing to the provided resource.
     * @param resourceName The resource to write to
     * @return a buffered writer object
     */
    @Override
    public BufferedWriter createWriter(String resourceName) {
        throw new UnsupportedOperationException(String.format("%s does not support writing!", getClass().getName()));
    }

    /**
     * Whether the implementation supports reading.
     * @return true or false
     */
    @Override
    public boolean supportsReader() {
        return true;
    }

    /**
     * Whether the implementation supports writing.
     * @return true or false
     */
    @Override
    public boolean supportsWriter() {
        return false;
    }
}
