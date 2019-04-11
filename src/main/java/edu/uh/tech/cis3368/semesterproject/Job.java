package edu.uh.tech.cis3368.semesterproject;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Job {
    private int id;
    private String jobName;
    private String description;
    private int productId;
    private int jobStageId;
    private int customerId;

    private Customer customer;
    private JobStage jobStage;
    private Product product;

    public Job() {

    }

    public Job(String name, String description, Customer customer, JobStage jobStage, Product product) {
        this.jobName = name;
        this.description = description;
        this.customer = customer;
        this.jobStage = jobStage;
        this.product = product;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "JOB_NAME", nullable = false, length = 24)
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = false, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne
    @JoinColumn(name = "JOB_STAGE_ID", referencedColumnName = "ID")
    public JobStage getJobStage() {
        return jobStage;
    }

    public void setJobStage(JobStage jobStage) {
        this.jobStage = jobStage;
    }

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "ID")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customerByCustomerId) {
        this.customer = customerByCustomerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return id == job.id &&
                productId == job.productId &&
                jobStageId == job.jobStageId &&
                customerId == job.customerId &&
                Objects.equals(jobName, job.jobName) &&
                Objects.equals(description, job.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, jobName, description, productId, jobStageId, customerId);
    }
}
