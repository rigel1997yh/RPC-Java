package part1.common.serializer.mySerializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import part1.common.Message.RpcRequest;
import part1.common.Message.RpcResponse;

public class JSONSerializer implements Serializer{
    @Override
    public byte[] serialize(Object object) {
        return JSONObject.toJSONBytes(object);
    }

    @Override
    public Object deserializer(byte[] bytes, int messageType) {
        Object object = null;
        switch (messageType){
            case 0:
                RpcRequest request = JSON.parseObject(bytes,RpcRequest.class);
                Object[] objects = new Object[request.getParams().length];
                for (int i = 0 ;i<objects.length;i++){
                    Class<?> paramsType = request.getParamsType()[i];
                    if (!paramsType.isAssignableFrom(request.getParams()[i].getClass())){
                        objects[i]=JSONObject.toJavaObject((JSONObject)request.getParams()[i],request.getParamsType()[i]);
                    }else {
                        objects[i] = request.getParams()[i];
                    }
                }
                request.setParams(objects);
                object = request;
                break;
            case 1:
                RpcResponse response = JSON.parseObject(bytes,RpcResponse.class);
                Class<?> dataType = response.getDataType();
                if(!dataType.isAssignableFrom(response.getData().getClass())){
                    response.setData(JSONObject.toJavaObject((JSONObject)response.getData(),dataType));
                }
                object = response;
                break;
            default:
                System.out.println("暂时不支持此类消息");
                throw new RuntimeException();
        }
        return object;
    }

    @Override
    public int getType() {
        return 0;
    }
}
