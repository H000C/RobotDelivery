package springboot.model;


import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

public class latlonGroup {
    double[] start;
    double[] current;
    double[] finish;

    public double[] getStart() {
        return start;
    }

    public void setStart(double[] start) {
        this.start = start;
    }

    public double[] getCurrent() {
        return current;
    }

    public void setCurrent(double[] current) {
        this.current = current;
    }

    public double[] getFinish() {
        return finish;
    }

    public void setFinish(double[] finish) {
        this.finish = finish;
    }




}
