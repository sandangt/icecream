package sanlab.icecream.sharedlib.converter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ByteArrayConverter {
    public static byte[] toByteArray(Object obj) throws IOException {
        ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutStream = new ObjectOutputStream(byteOutStream);
        objectOutStream.writeObject(obj);
        objectOutStream.flush();
        return byteOutStream.toByteArray();
    }

    public static Object toObject(byte[] byteArray) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteInStream = new ByteArrayInputStream(byteArray);
        ObjectInputStream objectInStream = new ObjectInputStream(byteInStream);
        return objectInStream.readObject();
    }
}
