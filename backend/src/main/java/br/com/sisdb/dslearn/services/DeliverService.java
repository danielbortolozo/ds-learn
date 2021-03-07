package br.com.sisdb.dslearn.services;

import br.com.sisdb.dslearn.dto.DeliverRevisionDTO;
import br.com.sisdb.dslearn.entities.Deliver;
import br.com.sisdb.dslearn.observers.DeliverRevisionObserver;
import br.com.sisdb.dslearn.repositories.DeliverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class DeliverService {

    @Autowired
    private DeliverRepository repository;

    private Set<DeliverRevisionObserver> deliverRevisionObserver = new LinkedHashSet<>();

    @Transactional
    public void saveRevision(Long id, DeliverRevisionDTO dto) {
        Deliver deliver = repository.getOne(id);
        deliver.setStatus(dto.getStatus());
        deliver.setFeedback(dto.getFeedback());
        deliver.setCorrectCount(dto.getCorrectCount());
        repository.save(deliver);
        for (DeliverRevisionObserver observer : deliverRevisionObserver) {
            observer.onSaveRevision(deliver);
        }
    }

    public void subscribeDeliverRevisionObserver(DeliverRevisionObserver observer) {
        deliverRevisionObserver.add(observer);
    }
}