package br.com.sisdb.dslearn.services;


import br.com.sisdb.dslearn.dto.NotificationDTO;
import br.com.sisdb.dslearn.entities.Deliver;
import br.com.sisdb.dslearn.entities.Notification;
import br.com.sisdb.dslearn.entities.User;
import br.com.sisdb.dslearn.observers.DeliverRevisionObserver;
import br.com.sisdb.dslearn.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Service
public class NotificationService implements DeliverRevisionObserver {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private DeliverService deliverService;

    @PostConstruct
    private void initialize() {
        deliverService.subscribeDeliverRevisionObserver(this);
    }


    @Transactional(readOnly = true)
    public Page<NotificationDTO> notificationsForCurrentUser(boolean unreadOnly, Pageable pageable) {
        User user = authService.authenticated();
        Page<Notification> page = notificationRepository.find(user, unreadOnly, pageable);
        return page.map(x -> new NotificationDTO(x));
    }

    @Transactional
    public void saveDeliverNotification(Deliver deliver) {
       Long sectionId = deliver.getLesson().getSection().getId();
       Long resourceId = deliver.getLesson().getSection().getResource().getId();
       Long offerId = deliver.getLesson().getSection().getResource().getOffer().getId();

       String route = "/offers/"+offerId+"/resources/"+resourceId+"/sections/"+sectionId;
       String text = deliver.getFeedback();
       Instant moment = Instant.now();
       User user = deliver.getEnrollment().getStudent();

       Notification notification = new Notification(null, text, moment, false, route, user);
       notificationRepository.save(notification);

    }

    @Override
    public void onSaveRevision(Deliver deliver) {
       saveDeliverNotification(deliver);
    }
}







