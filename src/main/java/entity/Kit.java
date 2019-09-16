package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Kit extends Model{
    @Id
    private String valve_art;
    private String actuator_art;
    private String adapter_art;

    public Kit(){

    }

    public String getValve_art() {
        return valve_art;
    }

    public void setValve_art(String valve_art) {
        this.valve_art = valve_art;
    }

    public String getActuator_art() {
        return actuator_art;
    }

    public void setActuator_art(String actuator_art) {
        this.actuator_art = actuator_art;
    }

    public String getAdapter_art() {
        return adapter_art;
    }

    public void setAdapter_art(String adapter_art) {
        this.adapter_art = adapter_art;
    }



}
