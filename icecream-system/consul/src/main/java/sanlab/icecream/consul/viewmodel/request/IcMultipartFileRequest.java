package sanlab.icecream.consul.viewmodel.request;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

public class IcMultipartFileRequest implements MultipartFile {

    private final File file;

    public IcMultipartFileRequest(File file) {
        this.file = file;
    }

    @Override
    public @NotNull String getName() {
        return file.getName();
    }

    @Override
    public String getOriginalFilename() {
        return file.getName();
    }

    @Override
    public String getContentType() {
        return "application/octet-stream";
    }

    @Override
    public boolean isEmpty() {
        return file.length() == 0;
    }

    @Override
    public long getSize() {
        return file.length();
    }

    @Override
    public byte @NotNull [] getBytes() throws IOException {
        return Files.readAllBytes(file.toPath());
    }

    @Override
    public @NotNull InputStream getInputStream() throws IOException {
        return new FileInputStream(file);
    }

    @Override
    public void transferTo(@NotNull File dest) throws IOException, IllegalStateException {
        try (InputStream is = getInputStream();
            OutputStream os = new FileOutputStream(dest)) {
            is.transferTo(os);
        }
    }

}
