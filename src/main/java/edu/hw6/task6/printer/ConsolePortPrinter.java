package edu.hw6.task6.printer;

import edu.hw6.task6.model.PortInfo;
import java.util.List;

public final class ConsolePortPrinter implements PortPrinter {
    private static final String HEADER = "Порт  Протокол  Статус  Сервис";
    private static final String CONTENT = "%-6d%-10s%-8s%s";

    @SuppressWarnings("checkstyle:RegexpSinglelineJava")
    public void print(List<PortInfo> portsInfo) {
        System.out.println(HEADER);
        for (PortInfo portInfo : portsInfo) {
            System.out.println(CONTENT.formatted(
                portInfo.port(),
                portInfo.protocol(),
                portInfo.portStatus(),
                portInfo.service() == null ? "N/A" : portInfo.service()
            ));
        }
    }
}
