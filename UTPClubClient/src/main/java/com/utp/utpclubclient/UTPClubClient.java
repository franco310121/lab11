package com.utp.utpclubclient;



public class UTPClubClient {
    public static void main(String[] args) {
        
        String idLocal1 = "Local_Surco";
        String idLocal2 = "Local_La_Molina";
        String idLocal3 = "Local_Ate";
        // Siempre lanzar Swing en el hilo de eventos de AWT
        javax.swing.SwingUtilities.invokeLater(() -> {
            UTPClubCliente_inicio formulario = new UTPClubCliente_inicio(idLocal1);
            formulario.setVisible(true);
            UTPClubCliente_inicio formulario2 = new UTPClubCliente_inicio(idLocal2);
            formulario2.setVisible(true);
            UTPClubCliente_inicio formulario3 = new UTPClubCliente_inicio(idLocal3);
            formulario3.setVisible(true);
        });
    }
}
