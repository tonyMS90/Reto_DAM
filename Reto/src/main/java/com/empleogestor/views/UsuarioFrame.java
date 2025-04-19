package com.empleogestor.views;

import com.empleogestor.controllers.UsuarioController;
import com.empleogestor.models.Usuario;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UsuarioFrame extends JFrame {
    private UsuarioController usuarioController;
    private JTable usuariosTable;
    private DefaultTableModel tableModel;

    public UsuarioFrame() {
        usuarioController = new UsuarioController();
        initComponents();
        setTitle("Gestión de Usuarios");
        setSize(800, 600);
        setLocationRelativeTo(null);
        loadUsuarios();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton toggleButton = new JButton("Activar/Desactivar");
        toggleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleUsuarioStatus();
            }
        });

        buttonPanel.add(toggleButton);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        // Tabla de usuarios
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Email", "Teléfono", "Dirección", "Estado"}, 0);
        usuariosTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(usuariosTable);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void loadUsuarios() {
        tableModel.setRowCount(0);
        List<Usuario> usuarios = usuarioController.getAllUsuarios();
        for (Usuario usuario : usuarios) {
            tableModel.addRow(new Object[]{
                    usuario.getId(),
                    usuario.getNombre(),
                    usuario.getEmail(),
                    usuario.getTelefono(),
                    usuario.getDireccion(),
                    usuario.isEnabled() ? "Activo" : "Inactivo"
            });
        }
    }

    private void toggleUsuarioStatus() {
        int selectedRow = usuariosTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para cambiar su estado", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea cambiar el estado de este usuario?",
                "Confirmar cambio de estado",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (usuarioController.toggleUsuarioStatus(id)) {
                JOptionPane.showMessageDialog(this, "Estado del usuario actualizado correctamente");
                loadUsuarios();
            } else {
                JOptionPane.showMessageDialog(this, "Error al cambiar el estado del usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}