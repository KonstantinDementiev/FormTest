package calculations;

public class Kvs {
    private double flow;
    private Integer pressureDrop;
    private Integer temperature;
    private Integer glikol;
    private double densityMix;
    private double kv;


    public Kvs(double flow, Integer pressureDrop, Integer temperature, Integer glikol) {
        this.flow = flow;
        this.pressureDrop = pressureDrop;
        this.temperature = temperature;
        this.glikol = glikol;
    }

    public Kvs() {
    }


    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow) {
        this.flow = flow;
    }

    public Integer getPressureDrop() {
        return pressureDrop;
    }

    public void setPressureDrop(Integer pressureDrop) {
        this.pressureDrop = pressureDrop;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getGlikol() {
        return glikol;
    }

    public void setGlikol(Integer glikol) {
        this.glikol = glikol;
    }

    public double getDensityMix() {
        return densityMix;
    }

    public void setDensityMix(double densityMix) {
        this.densityMix = densityMix;
    }

    public double getKv() {
        return kv;
    }

    public void setKv(double kv) {
        this.kv = kv;
    }
}
