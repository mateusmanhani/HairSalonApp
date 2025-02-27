package models;

public class Service {
    private Integer serviceId;
    private String serviceName;
    private double servicePrice;

    public Service(Integer serviceId, String serviceName, double servicePrice) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public double getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(double servicePrice) {
        this.servicePrice = servicePrice;
    }

    public void printService(){
        System.out.println(this.serviceId + ". " + this.serviceName + " , $ " + this.servicePrice);
    }
}
