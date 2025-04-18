package org.example.reto;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class GestorVacantes extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Sistema de Gestión de Vacantes - Administración");

        initRootLayout();
        showLoginView();
    }

    private void initRootLayout() {
        rootLayout = new BorderPane();
        Scene scene = new Scene(rootLayout, 1024, 768);
        primaryStage.setScene(scene);

        // Menú principal
        MenuBar menuBar = new MenuBar();

        Menu archivoMenu = new Menu("Archivo");
        MenuItem salirItem = new MenuItem("Salir");
        salirItem.setOnAction(e -> System.exit(0));
        archivoMenu.getItems().add(salirItem);

        Menu administracionMenu = new Menu("Administración");
        MenuItem empresasItem = new MenuItem("Empresas");
        empresasItem.setOnAction(e -> showEmpresasView());
        MenuItem categoriasItem = new MenuItem("Categorías");
        categoriasItem.setOnAction(e -> showCategoriasView());
        MenuItem usuariosItem = new MenuItem("Usuarios");
        usuariosItem.setOnAction(e -> showUsuariosView());
        MenuItem adminsItem = new MenuItem("Administradores");
        adminsItem.setOnAction(e -> showAdministradoresView());

        administracionMenu.getItems().addAll(empresasItem, categoriasItem, usuariosItem, adminsItem);

        menuBar.getMenus().addAll(archivoMenu, administracionMenu);
        rootLayout.setTop(menuBar);

        primaryStage.show();
    }

    private void showLoginView() {
        GridPane loginPane = new GridPane();
        loginPane.setPadding(new Insets(20));
        loginPane.setHgap(10);
        loginPane.setVgap(10);

        Label userLabel = new Label("Usuario:");
        TextField userField = new TextField();
        Label passLabel = new Label("Contraseña:");
        PasswordField passField = new PasswordField();
        Button loginButton = new Button("Iniciar Sesión");

        loginButton.setOnAction(e -> {
            // Validar credenciales
            showDashboardView();
        });

        loginPane.add(userLabel, 0, 0);
        loginPane.add(userField, 1, 0);
        loginPane.add(passLabel, 0, 1);
        loginPane.add(passField, 1, 1);
        loginPane.add(loginButton, 1, 2);

        rootLayout.setCenter(loginPane);
    }

    private void showDashboardView() {
        VBox dashboard = new VBox(20);
        dashboard.setPadding(new Insets(20));

        Label welcomeLabel = new Label("Bienvenido al Panel de Administración");
        welcomeLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        // Estadísticas rápidas
        GridPane statsPane = new GridPane();
        statsPane.setHgap(15);
        statsPane.setVgap(10);

        statsPane.add(new Label("Empresas registradas:"), 0, 0);
        statsPane.add(new Label("125"), 1, 0);
        statsPane.add(new Label("Vacantes activas:"), 0, 1);
        statsPane.add(new Label("342"), 1, 1);
        statsPane.add(new Label("Usuarios registrados:"), 0, 2);
        statsPane.add(new Label("1,245"), 1, 2);

        // Accesos rápidos
        HBox quickAccess = new HBox(15);
        Button empresasBtn = new Button("Empresas");
        empresasBtn.setOnAction(e -> showEmpresasView());
        Button categoriasBtn = new Button("Categorías");
        categoriasBtn.setOnAction(e -> showCategoriasView());
        Button usuariosBtn = new Button("Usuarios");
        usuariosBtn.setOnAction(e -> showUsuariosView());

        quickAccess.getChildren().addAll(empresasBtn, categoriasBtn, usuariosBtn);

        dashboard.getChildren().addAll(welcomeLabel, statsPane, quickAccess);
        rootLayout.setCenter(dashboard);
    }

    private void showEmpresasView() {
        VBox empresasView = new VBox(15);
        empresasView.setPadding(new Insets(20));

        Label titleLabel = new Label("Gestión de Empresas");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Barra de herramientas
        HBox toolbar = new HBox(10);
        Button addBtn = new Button("Agregar Empresa");
        Button editBtn = new Button("Editar");
        Button deleteBtn = new Button("Desactivar");
        TextField searchField = new TextField();
        searchField.setPromptText("Buscar empresa...");
        Button searchBtn = new Button("Buscar");

        toolbar.getChildren().addAll(addBtn, editBtn, deleteBtn, searchField, searchBtn);

        // Tabla de empresas
        TableView<Empresa> empresasTable = new TableView<>();

        TableColumn<Empresa, String> nombreCol = new TableColumn<>("Nombre");
        TableColumn<Empresa, String> rucCol = new TableColumn<>("RUC");
        TableColumn<Empresa, String> contactoCol = new TableColumn<>("Contacto");
        TableColumn<Empresa, Boolean> estadoCol = new TableColumn<>("Activa");

        empresasTable.getColumns().addAll(nombreCol, rucCol, contactoCol, estadoCol);

        // Formulario de detalles
        GridPane detailsPane = new GridPane();
        detailsPane.setHgap(10);
        detailsPane.setVgap(10);
        detailsPane.setPadding(new Insets(15));
        detailsPane.setStyle("-fx-border-color: #ccc; -fx-border-width: 1px;");

        detailsPane.add(new Label("Nombre:"), 0, 0);
        TextField nombreField = new TextField();
        detailsPane.add(nombreField, 1, 0);

        detailsPane.add(new Label("RUC:"), 0, 1);
        TextField rucField = new TextField();
        detailsPane.add(rucField, 1, 1);

        detailsPane.add(new Label("Dirección:"), 0, 2);
        TextField direccionField = new TextField();
        detailsPane.add(direccionField, 1, 2);

        detailsPane.add(new Label("Contacto:"), 0, 3);
        TextField contactoField = new TextField();
        detailsPane.add(contactoField, 1, 3);

        detailsPane.add(new Label("Email:"), 0, 4);
        TextField emailField = new TextField();
        detailsPane.add(emailField, 1, 4);

        detailsPane.add(new Label("Teléfono:"), 0, 5);
        TextField telefonoField = new TextField();
        detailsPane.add(telefonoField, 1, 5);

        CheckBox activaCheck = new CheckBox("Empresa activa");
        detailsPane.add(activaCheck, 1, 6);

        HBox buttonsBox = new HBox(10);
        Button saveBtn = new Button("Guardar");
        Button cancelBtn = new Button("Cancelar");
        buttonsBox.getChildren().addAll(saveBtn, cancelBtn);
        detailsPane.add(buttonsBox, 1, 7);

        empresasView.getChildren().addAll(titleLabel, toolbar, empresasTable, detailsPane);
        rootLayout.setCenter(empresasView);
    }

    private void showCategoriasView() {
        VBox categoriasView = new VBox(15);
        categoriasView.setPadding(new Insets(20));

        Label titleLabel = new Label("Gestión de Categorías");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Implementar interfaz similar a empresasView pero para categorías
        // ...

        rootLayout.setCenter(categoriasView);
    }

    private void showUsuariosView() {
        VBox usuariosView = new VBox(15);
        usuariosView.setPadding(new Insets(20));

        Label titleLabel = new Label("Gestión de Usuarios");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Implementar interfaz para gestión de usuarios
        // ...

        rootLayout.setCenter(usuariosView);
    }

    private void showAdministradoresView() {
        VBox adminsView = new VBox(15);
        adminsView.setPadding(new Insets(20));

        Label titleLabel = new Label("Gestión de Administradores");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // Implementar interfaz para gestión de administradores
        // ...

        rootLayout.setCenter(adminsView);
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Clase modelo para Empresa (simplificada)
    public static class Empresa {
        private String nombre;
        private String ruc;
        private String contacto;
        private boolean activa;

        // Getters y setters
    }
}