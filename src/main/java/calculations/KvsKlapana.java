package calculations;

public class KvsKlapana {

    private Kvs kvs = new Kvs();

    public double calcDensityMix(Integer glikol, Integer temperature) {

        switch (glikol) {
            case 0:
                kvs.setDensityMix(-0.0000001214 * Math.pow(temperature, 4) + 0.0000392894 * Math.pow(temperature, 3) - 0.0073475269 * Math.pow(temperature, 2) + 0.047537727 * temperature + 999.9216355614);
                break;
            case 10:
                kvs.setDensityMix(0.0000000758 * Math.pow(temperature, 3) - 0.0024561688 * Math.pow(temperature, 2) - 0.2367380952 * temperature + 1013.8504545461);
                break;
            case 15:
                kvs.setDensityMix(-0.0000001263 * Math.pow(temperature, 3) - 0.0024659091 * Math.pow(temperature, 2) - 0.2646269841 * temperature + 1019.8509090911);
                break;
            case 20:
                kvs.setDensityMix(-0.0000000253 * Math.pow(temperature, 3) - 0.0025146104 * Math.pow(temperature, 2) - 0.2912777778 * temperature + 1025.8415151517);
                break;
            case 25:
                kvs.setDensityMix(-0.0000000182 * Math.pow(temperature, 3) - 0.0025304518 * Math.pow(temperature, 2) - 0.3175370222 * temperature + 1031.0440617697);
                break;
            case 30:
                kvs.setDensityMix(-0.0000000561 * Math.pow(temperature, 3) - 0.0025491952 * Math.pow(temperature, 2) - 0.3434324199 * temperature + 1036.2390913939);
                break;
            case 35:
                kvs.setDensityMix(-0.0000000503 * Math.pow(temperature, 3) - 0.0025609736 * Math.pow(temperature, 2) - 0.3679953897 * temperature + 1040.6841937194);
                break;
            case 40:
                kvs.setDensityMix(0.000000003 * Math.pow(temperature, 3) - 0.0025747521 * Math.pow(temperature, 2) - 0.3925268367 * temperature + 1045.1215485216);
                break;
            case 45:
                kvs.setDensityMix(0.0000000696 * Math.pow(temperature, 3) - 0.0025771956 * Math.pow(temperature, 2) - 0.4163110067 * temperature + 1048.9151150378);
                break;
            case 50:
                kvs.setDensityMix(-0.0000000049 * Math.pow(temperature, 3) - 0.0025656121 * Math.pow(temperature, 2) - 0.4404328879 * temperature + 1052.7037463769);
                break;
            default:
                System.out.println("Incorrect glycol content!");
                break;
        }
        return kvs.getDensityMix();
    }

    public double calcKv(Integer pressureDrop, double flow, double densityMix) {

        kvs.setKv(flow / Math.pow((pressureDrop * 10 / densityMix), -2));
        return kvs.getKv();
    }

}
