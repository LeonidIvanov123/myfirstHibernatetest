package ivan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "botusers")
public class Botuser {
    @Id
    @Column(name = "idmsg", nullable = false)
    private Integer id;

    @Column(name = "chatid")
    private Integer chatid;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "text", length = 200)
    private String text;

    @Column(name = "dateMsg")
    private Instant dateMsg;

    @Column(name = "acknowledge")
    private Integer acknowledge;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getChatid() {
        return chatid;
    }

    public void setChatid(Integer chatid) {
        this.chatid = chatid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getDateMsg() {
        return dateMsg;
    }

    public void setDateMsg(Instant dateMsg) {
        this.dateMsg = dateMsg;
    }

    public Integer getAcknowledge() {
        return acknowledge;
    }

    public void setAcknowledge(Integer acknowledge) {
        this.acknowledge = acknowledge;
    }

    @Override
    public String toString() {
        return "Botuser{" +
                "chatid=" + chatid +
                ", username='" + username + '\'' +
                ", text='" + text + '\'' +
                ", dateMsg=" + dateMsg +
                '}';
    }
}