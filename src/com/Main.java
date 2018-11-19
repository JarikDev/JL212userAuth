package com;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.PrivilegedAction;

public class Main {
    public static void main(String[] args) throws LoginException {
        System.setProperty("java.security.policy","src/myapp.policy");
        System.setProperty("java.security.auth.login.config", "src/jaas.config");
        System.setSecurityManager(new SecurityManager());

        LoginContext context=new LoginContext("Login1");
        context.login();
        Subject subject=context.getSubject();
        System.out.println("User is auth");
        Subject.doAsPrivileged(subject, new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                System.out.println(System.getProperty("os.name"));
                return null;
            }
        }, null);
        context.logout();
    }

}














