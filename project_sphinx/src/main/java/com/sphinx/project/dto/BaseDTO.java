package com.sphinx.project.dto;

import lombok.Data;

import java.sql.Date;
import java.util.Objects;
@Data
public class BaseDTO {

	private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BaseDTO that = (BaseDTO) o;
        return Objects.equals(createdBy, that.createdBy) &&
                Objects.equals(updatedBy, that.updatedBy) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(updatedDate, that.updatedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), createdBy, updatedBy, createdDate, updatedDate);
    }

    @Override
    public String toString() {
        return "BaseEntityAudit{" +
                "createdBy='" + createdBy + '\'' +
                ", updatedBy='" + updatedBy + '\'' +
                ", createdAt=" + createdDate +
                ", updatedAt=" + updatedDate +
                "} " + super.toString();
    }
}
