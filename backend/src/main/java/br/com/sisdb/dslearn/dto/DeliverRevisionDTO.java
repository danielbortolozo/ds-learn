package br.com.sisdb.dslearn.dto;

import br.com.sisdb.dslearn.entities.Deliver;
import br.com.sisdb.dslearn.entities.enums.DeliverStatus;

import java.io.Serializable;
import java.util.Objects;

public class DeliverRevisionDTO implements Serializable {
    private static final long serialVersionUID = 1L;


    private DeliverStatus status;
    private String feedback;
    private Integer correctCount;

    public DeliverRevisionDTO() {
    }

    public DeliverRevisionDTO(DeliverStatus status, String feedback, Integer correctCount) {
        this.status = status;
        this.feedback = feedback;
        this.correctCount = correctCount;
    }
    public DeliverRevisionDTO(Deliver entity) {
        status = entity.getStatus();
        feedback = entity.getFeedback();
        correctCount = entity.getCorrectCount();
    }

    public DeliverStatus getStatus() {
        return status;
    }

    public void setStatus(DeliverStatus status) {
        this.status = status;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Integer getCorrectCount() {
        return correctCount;
    }

    public void setCorrectCount(Integer correctCount) {
        this.correctCount = correctCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliverRevisionDTO that = (DeliverRevisionDTO) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }
}
