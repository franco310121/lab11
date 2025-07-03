package com.utp.utpclubclient;



public class UTPClubClient {
    public static void main(String[] args) {
        // Siempre lanzar Swing en el hilo de eventos de AWT
        javax.swing.SwingUtilities.invokeLater(() -> {
            UTPClubCliente_inicio formulario = new UTPClubCliente_inicio();
            formulario.setVisible(true);
        });
    }
}
