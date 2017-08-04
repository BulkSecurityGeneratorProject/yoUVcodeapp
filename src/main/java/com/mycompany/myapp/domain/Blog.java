package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Blog.
 */
@Entity
@Table(name = "blog")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "blog")
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 4000)
    @Column(name = "blog_title", length = 4000, nullable = false)
    private String blogTitle;

    @NotNull
    @Size(max = 30000)
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "hits", nullable = false)
    private Integer hits;

    @NotNull
    @Column(name = "blog_url", nullable = false)
    private String blogUrl;

    @NotNull
    @Column(name = "active_ind", nullable = false)
    private Boolean active_ind;

    @Column(name = "publish_date")
    private ZonedDateTime publishDate;

    @NotNull
    @Column(name = "created_date", nullable = false)
    private ZonedDateTime created_date;

    @NotNull
    @Column(name = "last_chng_date", nullable = false)
    private ZonedDateTime last_chng_date;

    @ManyToOne
    @NotNull
    private Category category_id;

    @ManyToOne
    private SubCategory subCategory_id;

    @ManyToOne
    @NotNull
    private BlogStatus status_id;

    @ManyToOne
    @NotNull
    private User created_by;

    @ManyToOne
    @NotNull
    private User last_chng_by;

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

    public Boolean isActive_ind() {
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

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category) {
        this.category_id = category;
    }

    public SubCategory getSubCategory_id() {
        return subCategory_id;
    }

    public void setSubCategory_id(SubCategory subcategory) {
        this.subCategory_id = subcategory;
    }

    public BlogStatus getStatus_id() {
        return status_id;
    }

    public void setStatus_id(BlogStatus blogStatus) {
        this.status_id = blogStatus;
    }

    public User getCreated_by() {
        return created_by;
    }

    public void setCreated_by(User user) {
        this.created_by = user;
    }

    public User getLast_chng_by() {
        return last_chng_by;
    }

    public void setLast_chng_by(User user) {
        this.last_chng_by = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Blog blog = (Blog) o;
        if(blog.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, blog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Blog{" +
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
