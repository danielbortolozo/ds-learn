package br.com.sisdb.dslearn.observers;

import br.com.sisdb.dslearn.entities.Deliver;

public interface DeliverRevisionObserver {

    void onSaveRevision(Deliver deliver);
}
