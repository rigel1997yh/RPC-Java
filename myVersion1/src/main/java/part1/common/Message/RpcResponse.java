package part1.common.Message;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RpcResponse implements Serializable {
    private int code;
    private String message;
    //具体数据
    private Object data;
    //success
    public static RpcResponse success(Object data){return RpcResponse.builder().code(200).data(data).build();}
    //fail
    public static RpcResponse fail(){return RpcResponse.builder().code(500).message("服务器发生错误").build();}
}
