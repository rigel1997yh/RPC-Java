package part1.common.Message;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MessegeType {
    REQUEST(0),RESPONSE(1);
    private int code;
    public int getCode(){
        return code;
    }
}
