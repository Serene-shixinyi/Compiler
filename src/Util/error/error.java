package Util.error;

public class error extends RuntimeException{
    public String msg;

    public error(String _msg) {
        this.msg = _msg;
    }

    public String toString() {return msg;}
}
