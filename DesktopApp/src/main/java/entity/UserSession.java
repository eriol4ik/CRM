package entity;

import org.springframework.context.ApplicationContext;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "Session")
public class UserSession {
    private static ApplicationContext context;

    @Id
    private String userId;

    @Column(name = "SESSION_ID")
    private Integer sessionId;

    public UserSession() {}

    public UserSession(String userId) {
        this.userId = userId;
        sessionId = LocalDateTime.now().toString().hashCode();
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public Integer getSessionId() {
        return sessionId;
    }
    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "UserSession{userId = '" + userId + "', " +
                "sessionId = '" + sessionId + "'}";
    }
}
