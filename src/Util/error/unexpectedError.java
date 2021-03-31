package Util.error;

public class unexpectedError extends error{
    public unexpectedError(String _msg){
        super("UnexpectedError: " + _msg);
    }
    public String toString() {return super.msg;}
}
