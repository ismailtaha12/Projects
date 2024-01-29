/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecthms;

/**
 *
 * @author DELL_PC
 */
import java.io.Serializable;
public class Service implements Serializable{
    String ServiceID;
    String Service;
    int ServicePrice;

    public Service(String ServiceID, String Service,int ServicePrice) {
        this.ServiceID = ServiceID;
        this.Service = Service;
        this.ServicePrice=ServicePrice;
    }
    public String getServiceID() {
        return ServiceID;
    }
    public String getService() {
        return Service;
    }
    public int getServicePrice(){
        return ServicePrice;
    }
    
    @Override
    public String toString() {
        return "ID : " + ServiceID + ", Service : " + Service;
    }
}