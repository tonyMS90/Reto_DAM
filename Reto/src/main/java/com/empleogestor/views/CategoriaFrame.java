package com.empleogestor.views;

import com.empleogestor.controllers.CategoriaController;
import com.empleogestor.models.Categoria;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CategoriaFrame extends JFrame {
    private CategoriaController categoriaController;
    private JTable categoriasTable;
    private DefaultTableModel tableModel;

    public CategoriaFrame() {
        categoriaController = new CategoriaController();
        initComponents();
        setTitle("Gestión de Categorías");
        setSize(600, 400);
        setLocationRelativeTo(null);
        loadCategorias();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton addButton = new JButton("Agregar");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddCategoriaDialog();
            }
        });

        JButton editButton = new JButton("Editar");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editCategoria();
            }
        });

        JButton deleteButton = new JButton("Eliminar");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCategoria();
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        // Tabla de categorías
        tableModel = new DefaultTableModel(new Object[]{"ID", "Nombre", "Descripción"}, 0);
        categoriasTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(categoriasTable);

        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void loadCategorias() {
        tableModel.setRowCount(0);
        List<Categoria> categorias = categoriaController.getAllCategorias();
        for (Categoria categoria : categorias) {
            tableModel.addRow(new Object[]{
                    categoria.getId(),
                    categoria.getNombre(),
                    categoria.getDescripcion()
            });
        }
    }

    private void showAddCategoriaDialog() {
        JDialog dialog = new JDialog(this, "Agregar Categoría", true);
        dialog.setSize(400, 200);
        dialog.setLayout(new GridLayout(3, 2, 10, 10));

        JTextField nombreField = new JTextField();
        JTextField descripcionField = new JTextField();

        dialog.add(new JLabel("Nombre:"));
        dialog.add(nombreField);
        dialog.add(new JLabel("Descripción:"));
        dialog.add(descripcionField);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Categoria categoria = new Categoria();
                categoria.setNombre(nombreField.getText());
                categoria.setDescripcion(descripcionField.getText());

                if (categoriaController.addCategoria(categoria)) {
                    JOptionPane.showMessageDialog(dialog, "Categoría agregada correctamente");
                    loadCategorias();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Error al agregar categoría", "Error", JOptionPane.ERROR_MESSAGE);
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

    private void editCategoria() {
        int selectedRow = categoriasTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una categoría para editar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        Categoria categoria = categoriaController.getCategoriaById(id);

        JDialog dialog = new JDialog(this, "Editar Categoría", true);
        dialog.setSize(400, 200);
        dialog.setLayout(new GridLayout(3, 2, 10, 10));

        JTextField nombreField = new JTextField(categoria.getNombre());
        JTextField descripcionField = new JTextField(categoria.getDescripcion());

        dialog.add(new JLabel("Nombre:"));
        dialog.add(nombreField);
        dialog.add(new JLabel("Descripción:"));
        dialog.add(descripcionField);

        JButton saveButton = new JButton("Guardar");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                categoria.setNombre(nombreField.getText());
                categoria.setDescripcion(descripcionField.getText());

                if (categoriaController.updateCategoria(categoria)) {
                    JOptionPane.showMessageDialog(dialog, "Categoría actualizada correctamente");
                    loadCategorias();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Error al actualizar categoría", "Error", JOptionPane.ERROR_MESSAGE);
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

    private void deleteCategoria() {
        int selectedRow = categoriasTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una categoría para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea eliminar esta categoría?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (categoriaController.deleteCategoria(id)) {
                JOptionPane.showMessageDialog(this, "Categoría eliminada correctamente");
                loadCategorias();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar categoría", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}