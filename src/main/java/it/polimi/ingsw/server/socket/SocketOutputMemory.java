package it.polimi.ingsw.server.socket;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public final class SocketOutputMemory {
    private static Map<Long, ObjectOutputStream> objectOutputStreamMap;

    private SocketOutputMemory()  {
        throw new IllegalAccessError("Utility class");
    }

    public static void init() {
        objectOutputStreamMap = new HashMap<>();
    }

    public static void add(Long threadId, ObjectOutputStream objectOutputStream) {
        objectOutputStreamMap.put(threadId, objectOutputStream);
    }

    public static ObjectOutputStream getOutputStream(Long threadId) {
        return objectOutputStreamMap.get(threadId);
    }
}
