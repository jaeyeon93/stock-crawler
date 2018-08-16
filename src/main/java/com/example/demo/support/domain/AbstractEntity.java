package com.example.demo.support.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Objects;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity {
    private static final Logger logger =  LoggerFactory.getLogger(AbstractEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    public AbstractEntity() {
    }

    public AbstractEntity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @JsonIgnore
    public String getCreateDate() {
        return getFormattedDate(createDate, "yyyy.MM.dd");
    }

    @JsonIgnore
    public String getLocalDateTime() {
        return getFormattedDate(modifiedDate, "yyyy.MM.dd");
    }

    @JsonIgnore
    public String getRequestTime() {
        return getFormattedDate(LocalDateTime.now(),"yyyy.MM.dd");
    }

    public LocalDate changeDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }

    public Integer getDiffday() {
        int createDate = changeDate(getLocalDateTime()).getDayOfYear();
        int current = changeDate(getRequestTime()).getDayOfYear();
        return current - createDate;
    }

    private String getFormattedDate(LocalDateTime dateTime, String format) {
        if (dateTime == null)
            return "";
        return dateTime.format(DateTimeFormatter.ofPattern(format));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
