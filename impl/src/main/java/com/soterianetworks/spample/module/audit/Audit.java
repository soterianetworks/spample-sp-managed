package com.soterianetworks.spample.module.audit;

import com.soterianetworks.spase.context.RequestThreadLocal;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Audit implements Serializable {

    private static final long serialVersionUID = 100L;

    @Id
    private String id;

    private String timestamp;

    private String actor, action, type, recipient, result;

    public Audit() {
    }

    public Audit(final String action, final String type, final String recipient, final String result) {
        this.actor = RequestThreadLocal.getActorId();
        this.action = action;
        this.type = type;
        this.recipient = recipient;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    //Needed to map id
    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return actor + " " + action + " " + type + " " + recipient + " " + result;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getAction() {
        return action;
    }

    public String getType() {
        return type;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getResult() {
        return result;
    }
}
