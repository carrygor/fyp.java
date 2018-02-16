package org.weixin4j.pay;

import java.io.Serializable;

/**
 * Created by pan on 2016/8/29.
 */
public enum PayTradeStateEnum implements Serializable {
    SUCCESS("SUCCESS"),
    REFUND("REFUND"),
    NOTPAY("NOTPAY"),
    CLOSED("CLOSED"),
    REVOKED("REVOKED"),
    USERPAYING("USERPAYING"),
    PAYERROR("PAYERROR")
    ;

    private String state;

    PayTradeStateEnum(String sate) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
