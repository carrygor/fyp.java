package cn.com.carry.tenants.server.form.aop;

import cn.com.carry.common.validation.ParameterChecker;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


/**
 * Created by 严汉羽 on 2017/7/6.
 */

@Component
public class TenantsParameterCheckOption extends ParameterChecker {

    private final static Logger logger = Logger.getLogger(TenantsParameterCheckOption.class);

    @Override
    public String validateToken(String token) {
        //TODO
        return null;
    }

    @Override
    public String validatemodule(String token) {
        //TODO
        return null;
    }
}
