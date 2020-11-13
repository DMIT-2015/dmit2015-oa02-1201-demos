package dmit2015.service;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.security.enterprise.SecurityContext;
import java.util.logging.Logger;

public class LoginInterceptor {

    private final Logger logger = Logger.getLogger(LoginInterceptor.class.getName());

    @Inject
    private SecurityContext securityContext;

    @AroundInvoke
    public Object verifyAccess(InvocationContext context) throws Exception {
        String methodName = context.getMethod().getName();
        logger.info("Intercepting invoke to method: " + methodName);

        if (securityContext.getCallerPrincipal() == null) {
            throw new RuntimeException("You do not have permission to execute this method.");
        }

        Object result = context.proceed();
        logger.info("Return from invoking method: " + methodName);
        return result;
    }
}
