package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.impl.ActuatorImpl;
import persistence.impl.ValveImpl;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        ActuatorImpl actuatorImpl = new ActuatorImpl();
        ValveImpl valveImpl = new ValveImpl();

        valveImpl.findAllValve();

    }
}