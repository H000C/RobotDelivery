package springboot.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

@Entity
@Table(name="history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    private String userId;

    @NotBlank
    private String orderId;

    /***
     * 0: waiting for conformate
     * 1: activate
     * 2: finished
     */
    @NotBlank
    private int status;

    public History() {

    }

    public History(String userId, String orderId, int status) {
        this.userId = userId;
        this.orderId = orderId;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
