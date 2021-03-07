package br.com.sisdb.dslearn.services;


import br.com.sisdb.dslearn.dto.NotificationDTO;
import br.com.sisdb.dslearn.entities.Notification;
import br.com.sisdb.dslearn.entities.User;
import br.com.sisdb.dslearn.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class NotificationService implements Serializable {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private AuthService authService;

    public Page<NotificationDTO> notificationsForCurrentUser(boolean unreadOnly, Pageable pageable) {

        User user = authService.authenticated();

        Page<Notification> page = notificationRepository.find(user, unreadOnly, pageable);

        return page.map(x -> new NotificationDTO(x));
    }
}
