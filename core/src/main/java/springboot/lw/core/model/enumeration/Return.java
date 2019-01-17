package springboot.lw.core.model.enumeration;

/**
 * @author xiejiedun on 2019/1/17
 */
public enum Return{
    SUCCESS(0),FAIL(1),REPEAT_USERNAME(2),REPEAT_ACCOUNT(3);

    private int value;
    Return(int i){
        value = i;
    }

    public int getValue() {
        return value;
    }
}
