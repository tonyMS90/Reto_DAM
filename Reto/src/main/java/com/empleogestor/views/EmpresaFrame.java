package com.empleogestor.views;

import com.empleogestor.controllers.EmpresaController;
import com.empleogestor.models.Empresa;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmpresaFrame extends JFrame {
    private EmpresaController empresaController;
    private JTable empresasTable;
    private DefaultTableModel tableModel;

    public EmpresaFrame() {
        empresaController = new EmpresaController();
        initComponents();
        setTitle("Gestión de Empresas");
        setSize(800, 600);
        setLocationRelativeTo(null);
        loadEmpresas();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton addButton = new JButton("Agregar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddEmpresaDialog();
            }
        });

        JButton editButton = new JButton("Editar");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editEmpresa();
            }
        });

        JButton deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteEmpresa();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        // Tabla de empresas
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Email", "Teléfono", "Dirección"}, 0);
        empresasTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(empresasTable);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void loadEmpresas() {
        tableModel.setRowCount(0);
        List<Empresa> empresas = empresaController.getAllEmpresas();
        for (Empresa empresa : empresas) {
            tableModel.addRow(new Object[]{
                    empresa.getId(),
                    empresa.getNombre(),
                    empresa.getEmail(),
                    empresa.getTelefono(),
                    empresa.getDireccion()
            });
        }
    }

    private void showAddEmpresaDialog() {
        JDialog dialog = new JDialog(this, "Agregar Empresa", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(5, 2, 10, 10));

        JTextField nombreField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField telefonoField = new JTextField();
        JTextField direccionField = new JTextField();

        dialog.add(new JLabel("Nombre:"));
        dialog.add(nombreField);
        dialog.add(new JLabel("Email:"));
        dialog.add(emailField);
        dialog.add(new JLabel("Teléfono:"));
        dialog.add(telefonoField);
        dialog.add(new JLabel("Dirección:"));
        dialog.add(direccionField);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Empresa empresa = new Empresa();
                empresa.setNombre(nombreField.getText());
                empresa.setEmail(emailField.getText());
                empresa.setTelefono(telefonoField.getText());
                empresa.setDireccion(direccionField.getText());

                if (empresaController.addEmpresa(empresa)) {
                    JOptionPane.showMessageDialog(dialog, "Empresa agregada correctamente");
                    loadEmpresas();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Error al agregar empresa", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.add(saveButton);
        dialog.add(cancelButton);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void editEmpresa() {
        int selectedRow = empresasTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una empresa para editar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        Empresa empresa = empresaController.getEmpresaById(id);

        JDialog dialog = new JDialog(this, "Editar Empresa", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(5, 2, 10, 10));

        JTextField nombreField = new JTextField(empresa.getNombre());
        JTextField emailField = new JTextField(empresa.getEmail());
        JTextField telefonoField = new JTextField(empresa.getTelefono());
        JTextField direccionField = new JTextField(empresa.getDireccion());

        dialog.add(new JLabel("Nombre:"));
        dialog.add(nombreField);
        dialog.add(new JLabel("Email:"));
        dialog.add(emailField);
        dialog.add(new JLabel("Teléfono:"));
        dialog.add(telefonoField);
        dialog.add(new JLabel("Dirección:"));
        dialog.add(direccionField);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                empresa.setNombre(nombreField.getText());
                empresa.setEmail(emailField.getText());
                empresa.setTelefono(telefonoField.getText());
                empresa.setDireccion(direccionField.getText());

                if (empresaController.updateEmpresa(empresa)) {
                    JOptionPane.showMessageDialog(dialog, "Empresa actualizada correctamente");
                    loadEmpresas();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Error al actualizar empresa", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.add(saveButton);
        dialog.add(cancelButton);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void deleteEmpresa() {
        int selectedRow = empresasTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una empresa para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea eliminar esta empresa?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (empresaController.deleteEmpresa(id)) {
                JOptionPane.showMessageDialog(this, "Empresa eliminada correctamente");
                loadEmpresas();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar empresa", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}