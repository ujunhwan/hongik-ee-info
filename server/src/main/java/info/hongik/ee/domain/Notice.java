package info.hongik.ee.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Notice {

    @Id
    private Long id;
    private String Title;
    private String Link;
    private String Date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
