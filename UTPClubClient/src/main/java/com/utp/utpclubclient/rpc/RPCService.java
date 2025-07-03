package com.utp.utpclubclient.rpc;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RPCService {

    private XmlRpcClient client;

    public RPCService(String serverUrl) throws Exception {
        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setServerURL(new URL(serverUrl));
        client = new XmlRpcClient();
        client.setConfig(config);
    }

    public boolean registrarSocio(String id, String nombre, String tipo) throws Exception {
        Object[] params = new Object[]{id, nombre, tipo};
        return (Boolean) client.execute("registrarSocio", params);
    }

    public boolean registrarCabeceraConsumo(String idLocal, String idSocio, String fecha, double total) throws Exception {
        Object[] params = new Object[]{idLocal, idSocio, fecha, total};
        return (Boolean) client.execute("registrarCabeceraConsumo", params);
    }

    public List<Map<String, Object>> obtenerSocios() throws Exception {
        Object[] sociosRaw = (Object[]) client.execute("obtenerSocios", new Object[]{});
        List<Map<String, Object>> socios = new ArrayList<>();

        for (Object o : sociosRaw) {
            if (o instanceof Map) {
                socios.add((Map<String, Object>) o);
            }
        }

        return socios;
    }

    public List<Map<String, Object>> obtenerCabecerasPorLocal(String idLocal) throws Exception {
        return (List<Map<String, Object>>) client.execute("obtenerCabecerasConsumoPorLocal", new Object[]{idLocal});
    }
}
