package edu.hw6.task6;

import edu.hw6.task6.model.PortInfo;
import edu.hw6.task6.model.PortStatus;
import edu.hw6.task6.model.Protocol;
import edu.hw6.task6.service.Services;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class PortScanner {
    private static final int MAX_PORT_VALUE_EXCLUSIVE = 49152;

    private PortScanner() {
    }

    public static List<PortInfo> scan() {
        List<PortInfo> ports = new ArrayList<>();
        for (int port = 0; port < MAX_PORT_VALUE_EXCLUSIVE; port++) {
            ports.add(getInfo(port, Protocol.UDP));
            ports.add(getInfo(port, Protocol.TCP));
        }
        return ports;
    }

    public static List<PortInfo> scanBusyPorts() {
        List<PortInfo> ports = new ArrayList<>();
        for (int port = 0; port < MAX_PORT_VALUE_EXCLUSIVE; port++) {
            Optional<PortInfo> infoUDP = getInfoIfBusy(port, Protocol.UDP);
            infoUDP.ifPresent(ports::add);
            Optional<PortInfo> infoTCP = getInfoIfBusy(port, Protocol.TCP);
            infoTCP.ifPresent(ports::add);
        }
        return ports;
    }

    private static PortInfo getInfo(int port, Protocol protocol) {
        if (isBusy(port, protocol)) {
            return new PortInfo(port, PortStatus.BUSY, protocol, Services.getName(port));
        } else {
            return new PortInfo(port, PortStatus.FREE, protocol, null);
        }
    }

    private static Optional<PortInfo> getInfoIfBusy(int port, Protocol protocol) {
        if (isBusy(port, protocol)) {
            return Optional.of(new PortInfo(port, PortStatus.BUSY, protocol, Services.getName(port)));
        }
        return Optional.empty();
    }

    private static boolean isBusy(int port, Protocol protocol) {
        return switch (protocol) {
            case UDP -> tryBindUDP(port);
            case TCP -> tryBindTCP(port);
        };
    }

    private static boolean tryBindUDP(int port) {
        try (DatagramSocket socket = new DatagramSocket(port)) {
            return false;
        } catch (Exception error) {
            return true;
        }
    }

    private static boolean tryBindTCP(int port) {
        try (ServerSocket socket = new ServerSocket(port)) {
            return false;
        } catch (Exception error) {
            return true;
        }
    }
}
