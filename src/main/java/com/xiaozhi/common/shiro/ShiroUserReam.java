package com.xiaozhi.common.shiro;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class ShiroUserReam extends AuthorizingRealm {
    private static final Logger logger = Logger.getLogger(ShiroUserReam.class.getName());


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addStringPermission("/test/setShiro");
        authorizationInfo.addStringPermission("/test/getShiro");

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

        logger.info(JSON.toJSONString(token));

        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo("", token.getPassword(), getName());
        return authcInfo;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
