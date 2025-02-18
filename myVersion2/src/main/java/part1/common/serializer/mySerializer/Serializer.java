package part1.common.serializer.mySerializer;



public interface Serializer {
    byte[] serialize(Object object);

    Object deserializer(byte[] bytes ,int messageType);

    int getType();

    static Serializer getSerializerByCode(int code){
        switch (code){
            case 0: return  new ObjectSerializer();
            case 1: return  new JSONSerializer();
            default: return null;
        }
    }
}
