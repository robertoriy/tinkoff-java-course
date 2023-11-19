package edu.hw6.task6.service;

import java.util.Map;

public final class Services {

    @SuppressWarnings({"checkstyle:LineLength", "checkstyle:MultipleStringLiterals"})
    private static final Map<Integer, String> SERVICES_MAP = Map.ofEntries(
        Map.entry(80, "HTTP (HyperText Transfer Protocol)"),
        Map.entry(21, "FTP (File Transfer Protocol)"),
        Map.entry(25, "SMTP (Simple Mail Transfer Protocol)"),
        Map.entry(22, "SSH (Secure Shell)"),
        Map.entry(443, "HTTPS (HyperText Transfer Protocol Secure)"),
        Map.entry(53, "DNS (Domain Name System)"),

        Map.entry(3306, "MySQL Database"),
        Map.entry(5432, "PostgreSQL Database"),
        Map.entry(3389, "Remote Desktop Protocol (RDP)"),
        Map.entry(27017, "MongoDB Database"),
        Map.entry(1521, "Oracle Database"),

        Map.entry(49152, "Windows RPC (Remote Procedure Call)"),
        Map.entry(5353, "mDNS (Multicast Domain Name System)"),
        Map.entry(5672, "AMQP (Advanced Message Queuing Protocol)"),
        Map.entry(5355, "LLMNR (Link-Local Multicast Name Resolution)"),
        Map.entry(49153, "Windows RPC (Remote Procedure Call)"),

        Map.entry(23, "Telnet protocol to ensure effective communication along with the remote server."),
        Map.entry(110, "POP3 - Post Office Protocol version 3 for email retrieval."),
        Map.entry(143, "IMAP - Internet Message Access Protocol for email retrieval."),
        Map.entry(67, "DHCP - Dynamic Host Configuration Protocol for IP address allocation."),
        Map.entry(68, "DHCP - Dynamic Host Configuration Protocol for IP address allocation."),
        Map.entry(123, "NTP - Network Time Protocol for time synchronization."),
        Map.entry(
            161,
            "SNMP - Simple Network Management Protocol to verify the functionality of the network and management of network."
        ),
        Map.entry(
            162,
            "SNMP - Simple Network Management Protocol to verify the functionality of the network and management of network."
        ),
        Map.entry(445, "SMB - Server Message Block protocol for file sharing and printer sharing."),
        Map.entry(548, "AFP - Apple Filing Protocol for file sharing between Macs."),
        Map.entry(137, "NetBIOS protocol for network communication between Windows devices."),
        Map.entry(138, "NetBIOS protocol for network communication between Windows devices."),
        Map.entry(139, "NetBIOS protocol for network communication between Windows devices."),
        Map.entry(8080, "HTTP proxy server."),
        Map.entry(1080, "SOCKS proxy server."),
        Map.entry(389, "LDAP - Lightweight Directory Access Protocol for directory services."),
        Map.entry(636, "LDAP - Lightweight Directory Access Protocol for directory services."),
        Map.entry(5722, "SMB version 2 protocol."),
        Map.entry(500, "IKE - Internet Key Exchange protocol for VPN connections."),
        Map.entry(1701, "L2TP- Layer 2 Tunneling Protocol for VPN connections.."),
        Map.entry(1723, "PPTP - Point-to-Point Tunneling Protocol for VPN connections."),
        Map.entry(5060, "SIP - Session Initiation Protocol for VoIP communication."),
        Map.entry(5061, "SIP - Session Initiation Protocol for VoIP communication."),
        Map.entry(16384, "RTP - Real-time Transport Protocol for audio and video transmission."),
        Map.entry(32767, "RTP - Real-time Transport Protocol for audio and video transmission."),
        Map.entry(3128, "HTTPS Proxy - HTTPS proxy server."),
        Map.entry(5900, "VNC - Virtual Network Computing for remote access."),
        Map.entry(6667, "IRC - Internet Relay Chat for real-time communication."),
        Map.entry(6697, "IRC - Internet Relay Chat for real-time communication."),
        Map.entry(2049, "NFS - Network File System for file sharing."),
        Map.entry(6379, "Redis - Redis key-value store."),
        Map.entry(11211, "Memcached - Memcached distributed memory caching system."),
        Map.entry(873, "Rsync - Remote synchronization for file transfers."),
        Map.entry(5222, "XMPP - Extensible Messaging and Presence Protocol for instant messaging."),
        Map.entry(5223, "XMPP - Extensible Messaging and Presence Protocol for instant messaging.")
    );

    private Services() {
    }

    public static String getName(int port) {
        return SERVICES_MAP.getOrDefault(port, "N/A");
    }
}
