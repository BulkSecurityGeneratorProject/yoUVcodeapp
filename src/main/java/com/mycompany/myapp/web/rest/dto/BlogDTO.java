package com.mycompany.myapp.web.rest.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
import javax.persistence.Lob;


/**
 * A DTO for the Blog entity.
 */
public class BlogDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 4000)
    private String blogTitle;

    @NotNull
    @Max(value = 30000)
    @Lob
    private String description;

    @NotNull
    private Integer hits;

    @NotNull
    private String blogUrl;

    @NotNull
    private Boolean active_ind;

    private ZonedDateTime publishDate;

    private ZonedDateTime created_date;

    private ZonedDateTime last_chng_date;


    private Long category_idId;
    
    private Long subCategory_idId;
    
    private Long status_idId;
    
    private Long created_byId;
    

    private String created_byLogin;

    private Long last_chng_byId;
    

    private String last_chng_byLogin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }
    public String getBlogUrl() {
        return blogUrl;
    }

    public void setBlogUrl(String blogUrl) {
        this.blogUrl = blogUrl;
    }
    public Boolean getActive_ind() {
        return active_ind;
    }

    public void setActive_ind(Boolean active_ind) {
        this.active_ind = active_ind;
    }
    public ZonedDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(ZonedDateTime publishDate) {
        this.publishDate = publishDate;
    }
    public ZonedDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(ZonedDateTime created_date) {
        this.created_date = created_date;
    }
    public ZonedDateTime getLast_chng_date() {
        return last_chng_date;
    }

    public void setLast_chng_date(ZonedDateTime last_chng_date) {
        this.last_chng_date = last_chng_date;
    }

    public Long getCategory_idId() {
        return category_idId;
    }

    public void setCategory_idId(Long categoryId) {
        this.category_idId = categoryId;
    }

    public Long getSubCategory_idId() {
        return subCategory_idId;
    }

    public void setSubCategory_idId(Long subcategoryId) {
        this.subCategory_idId = subcategoryId;
    }

    public Long getStatus_idId() {
        return status_idId;
    }

    public void setStatus_idId(Long blogStatusId) {
        this.status_idId = blogStatusId;
    }

    public Long getCreated_byId() {
        return created_byId;
    }

    public void setCreated_byId(Long userId) {
        this.created_byId = userId;
    }


    public String getCreated_byLogin() {
        return created_byLogin;
    }

    public void setCreated_byLogin(String userLogin) {
        this.created_byLogin = userLogin;
    }

    public Long getLast_chng_byId() {
        return last_chng_byId;
    }

    public void setLast_chng_byId(Long userId) {
        this.last_chng_byId = userId;
    }


    public String getLast_chng_byLogin() {
        return last_chng_byLogin;
    }

    public void setLast_chng_byLogin(String userLogin) {
        this.last_chng_byLogin = userLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BlogDTO blogDTO = (BlogDTO) o;

        if ( ! Objects.equals(id, blogDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BlogDTO{" +
            "id=" + id +
            ", blogTitle='" + blogTitle + "'" +
            ", description='" + description + "'" +
            ", hits='" + hits + "'" +
            ", blogUrl='" + blogUrl + "'" +
            ", active_ind='" + active_ind + "'" +
            ", publishDate='" + publishDate + "'" +
            ", created_date='" + created_date + "'" +
            ", last_chng_date='" + last_chng_date + "'" +
            '}';
    }
}
