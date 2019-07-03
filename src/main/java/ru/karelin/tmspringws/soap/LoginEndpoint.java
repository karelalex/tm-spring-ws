package ru.karelin.tmspringws.soap;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.karelin.tmspringws.entity.User;
import ru.karelin.tmspringws.service.UserService;


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Component
public class LoginEndpoint {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @WebMethod
    public String singIn(
            @WebParam(name = "login") String login,
            @WebParam(name = "password") String password
    ) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(login, password);
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            @Nullable final User user = userService.findByLogin(authentication.getName());
            if (user != null) {
                Message message = PhaseInterceptorChain.getCurrentMessage();
                HttpServletRequest request = (HttpServletRequest) message.get(AbstractHTTPDestination.HTTP_REQUEST);
                HttpSession session = request.getSession(true);
                session.setAttribute("userId", user.getId());

            }
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @WebMethod
    public boolean singOut() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return true;
    }


}
