package service;

import persistence.impl.ActuatorImpl;
import persistence.impl.ValveImpl;

public class Main {

    public static void main(String[] args) {

        ActuatorImpl actuatorImpl = new ActuatorImpl();
        ValveImpl valveImpl = new ValveImpl();

        valveImpl.findAllValve();

    }
}