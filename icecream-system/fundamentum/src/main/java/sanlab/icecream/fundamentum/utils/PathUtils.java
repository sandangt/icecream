package sanlab.icecream.fundamentum.utils;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class PathUtils {

    private PathUtils() {}

    public static String getLastPathSegment(URI uri) {
        Path path = Paths.get(uri.getPath());
        return path.getName(path.getNameCount() - 1).toString();
    }

    public static String getLastPathSegment(String uriStr) {
        URI uri = URI.create(uriStr);
        return getLastPathSegment(uri);
    }

}
