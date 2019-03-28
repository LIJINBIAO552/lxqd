package zqu.lxqd.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Procedure {
    private Integer id;

    @JSONField(format="yyyy-MM-dd")
    private Date eventtime;

    private String event;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEventtime() {
        return eventtime;
    }

    public void setEventtime(Date eventtime) {
        this.eventtime = eventtime;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event == null ? null : event.trim();
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "id=" + id +
                ", eventtime=" + eventtime +
                ", event='" + event + '\'' +
                '}';
    }
}