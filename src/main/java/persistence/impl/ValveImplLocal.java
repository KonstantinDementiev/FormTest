package persistence.impl;

import com.form.ControllerMain;
import entity.Valve;

import java.util.HashSet;
import java.util.Set;

public class ValveImplLocal {

    private ControllerMain cm = new ControllerMain();

    public Set<Valve> findValveByComboBoxLocal(Double desiredKvs, Integer desiredDn, Integer desiredPorts, String desiredPn, String desiredConn, String desiredType) {

        Set<Valve> listValveStart = cm.getAllValves();
        Set<Valve> listValveSorted = new HashSet<>();

        boolean b1, b2, b3, b4, b5, b6;

            for (Valve valveIterate : listValveStart) {

                if (desiredKvs != null) {
                    b1 = desiredKvs.equals(valveIterate.getKvs());
                } else {
                    b1 = true;
                }
                if (desiredDn != null) {
                    b2 = desiredDn.equals(valveIterate.getDn());
                } else {
                    b2 = true;
                }
                if (desiredPorts != null) {
                    b3 = desiredPorts.equals(valveIterate.getPorts());
                } else {
                    b3 = true;
                }
                if (desiredPn != null) {
                    b4 = desiredPn.equals(valveIterate.getPn());
                } else {
                    b4 = true;
                }
                if (desiredConn != null) {
                    b5 = desiredConn.equals(valveIterate.getConnection());
                } else {
                    b5 = true;
                }
                if (desiredType != null) {
                    b6 = desiredType.equals(valveIterate.getType());
                } else {
                    b6 = true;
                }

                if (b1 && b2 && b3 && b4 && b5 && b6){
                    listValveSorted.add(valveIterate);
                }
            }
        return listValveSorted;
    }
}
