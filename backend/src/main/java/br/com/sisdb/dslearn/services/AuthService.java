package br.com.sisdb.dslearn.services;


import br.com.sisdb.dslearn.entities.User;
import br.com.sisdb.dslearn.repositories.UserRepository;
import br.com.sisdb.dslearn.services.exceptions.ForbiddenException;
import br.com.sisdb.dslearn.services.exceptions.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;


    @Transactional(readOnly = true)
    public User authenticated() {
        try{
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return userRepository.findByEmail(username);
        }
        catch (Exception e) {
           throw new UnauthorizedException("Invalid user.");
        }
    }

    public void validateSelfOrAdmin(Long userId) {
        User user = authenticated();
        if (!user.getId().equals(userId) && !user.hasHole("ROLE_ADMIN")){
            throw new ForbiddenException("User not authorization. Access denied!");
        }
    }


}







