package com.empleogestor.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainAdminFrame extends JFrame {
    public MainAdminFrame() {
        initComponents();
        setTitle("Sistema de Gestión de Empleo - Administrador");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Menú principal
        JMenuBar menuBar = new JMenuBar();

        JMenu gestionMenu = new JMenu("Gestión");
        JMenuItem empresasItem = new JMenuItem("Empresas");
        JMenuItem categoriasItem = new JMenuItem("Categorías");
        JMenuItem usuariosItem = new JMenuItem("Usuarios");
        JMenuItem adminsItem = new JMenuItem("Administradores");

        empresasItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showEmpresaFrame();
            }
        });

        categoriasItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCategoriaFrame();
            }
        });

        usuariosItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showUsuarioFrame();
            }
        });

        gestionMenu.add(empresasItem);
        gestionMenu.add(categoriasItem);
        gestionMenu.add(usuariosItem);
        gestionMenu.add(adminsItem);

        JMenu ayudaMenu = new JMenu("Ayuda");
        JMenuItem acercaItem = new JMenuItem("Acerca de");
        ayudaMenu.add(acercaItem);

        menuBar.add(gestionMenu);
        menuBar.add(ayudaMenu);

        setJMenuBar(menuBar);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Bienvenido al Sistema de Gestión de Empleo", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(welcomeLabel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private void showEmpresaFrame() {
        EmpresaFrame empresaFrame = new EmpresaFrame();
        empresaFrame.setVisible(true);
    }

    private void showCategoriaFrame() {
        CategoriaFrame categoriaFrame = new CategoriaFrame();
        categoriaFrame.setVisible(true);
    }

    private void showUsuarioFrame() {
        UsuarioFrame usuarioFrame = new UsuarioFrame();
        usuarioFrame.setVisible(true);
    }
}