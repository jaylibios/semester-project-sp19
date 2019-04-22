package edu.uh.tech.cis3368.semesterproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class JobService {

    private final JobRepository jobRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final JobStageRepository jobStageRepository;

    private Product jobProduct;
    private Component jobComponent;

    @Autowired
    public JobService(JobRepository jobRepository,
                      CustomerRepository customerRepository,
                      ProductRepository productRepository,
                      JobStageRepository jobStageRepository) {
        this.jobRepository = jobRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.jobStageRepository = jobStageRepository;
    }



    public void createJobWithCustomer(String jobName, String jobDescription, String customerName,
                                     String customerAddress, String customerPhone) {
        Customer customer = new Customer(customerName,customerAddress, customerPhone);
        customerRepository.save(customer);

        // dummy product
        Product product = new Product("Name","Description");
        productRepository.save(product);

        var jobStage = jobStageRepository.findByOrdinal(1);
        Job job = new Job(jobName, jobDescription, customer, jobStage, product);
        System.out.println(job.getJobStage().getName());

        jobRepository.save(job);
    }

    public void setJobProduct(Product product) {
        this.jobProduct = product;
        System.out.println(product);
    }

    public Product getJobProduct() {
        return jobProduct;
    }

    public void setJobComponent(Component component) {
        this.jobComponent = component;
    }

    public Component getJobComponent() {
        return jobComponent;
    }

}
