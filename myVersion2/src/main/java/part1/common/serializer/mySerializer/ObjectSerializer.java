package part1.common.serializer.mySerializer;

import java.io.ByteArrayInputStream;
import java.io.*;

public class ObjectSerializer implements Serializer {
    @Override
    public byte[] serialize(Object object) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try{
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return bytes;
    }

    @Override
    public Object deserializer(byte[] bytes, int messageType) {
        Object obj = null;
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        try {
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return obj;
    }

    @Override
    public int getType() {
        return 0;
    }
}
