
package com.mycompany.UrKitchen.model;

/**
 *
 * @author Leanne
 */
public class feedback {
    private String msg;
    private String err;

    public feedback() {
    }

    public feedback(String msg, String err) {
        this.msg = msg;
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
    
    
    
    
}
