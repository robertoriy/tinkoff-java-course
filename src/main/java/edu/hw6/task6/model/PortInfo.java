package edu.hw6.task6.model;

public record PortInfo(int port, PortStatus portStatus, Protocol protocol, String service) {
}
