package entity;

import service.UserSessionServiceImpl;

import javax.persistence.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * Created by eriol4ik on 19.02.2017.
 */

@Entity
@Table(name = "Session")
public class UserSession {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private Long userId;

    @Column(name = "SESSION_ID")
    private Integer sessionId;

    public UserSession() {}

    private void setEmployeeId(Long userId) {
        this.userId = userId;
    }

    private void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public Long getUserId() {
        return userId;
    }

    public static UserSession readFromResource() {
        InputStream stream = UserSession.class.getResourceAsStream("/session.properties");
        Scanner scanner = new Scanner(stream);

        UserSession userSession = new UserSession();
        if (scanner.hasNextLong()) userSession.setEmployeeId(scanner.nextLong());
        else return null;

        if (scanner.hasNextInt()) userSession.setSessionId(scanner.nextInt());
        else return null;

        return userSession;
    }

    public static UserSession writeToResource(Long userId) {
        if (userId != null && userId.equals(-1L)) return null;

        UserSession userSession = new UserSession();
        userSession.userId = userId;
        userSession.sessionId = LocalDateTime.now().toString().hashCode();

        UserSession from = readFromResource();

        String path = UserSession.class.getResource("/session.properties").getPath();

        File file = new File(path);

        try (PrintWriter out = new PrintWriter(file.getAbsoluteFile())) {
            if (userId == null) {
                if (from == null) return null;
                UserSessionServiceImpl.getInstance().delete(from);
                out.print(""); return null; }
            out.println(userId);
            out.println(userSession.sessionId);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        UserSessionServiceImpl.getInstance().createOrUpdate(userSession);
        return userSession;
    }
}
