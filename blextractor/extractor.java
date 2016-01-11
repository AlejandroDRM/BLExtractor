/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blextractor;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MS2
 */
public class extractor extends javax.swing.JFrame {

    /**
     * Creates new form extractor
     */
    String viajeActual;
    ArrayList<String> blsViajeActual;
    ArrayList<String> seleccionBLS;
    int blActual;
    String keyLineNumberControl;
    boolean seleccion;
    String[] clientes;
//    final String ruta = "\\\\192.168.5.12\\documentacion\\";
    final String ruta = "C:\\Users\\MS2\\Documents\\NetBeansProjects\\";

    public String[] getClientes() {
        return clientes;
    }

    public void setClientes(String[] clientes) {
        this.clientes = clientes;
    }

    public boolean isSeleccion() {
        return seleccion;
    }

    public void setSeleccion(boolean seleccion) {
        this.seleccion = seleccion;
    }

    public ArrayList<String> getSeleccionBLS() {
        return seleccionBLS;
    }

    public void setSeleccionBLS(ArrayList<String> seleccionBLS) {
        this.seleccionBLS = seleccionBLS;
    }

    public String getViajeActual() {
        return viajeActual;
    }

    public void setViajeActual(String viajeActual) {
        this.viajeActual = viajeActual;
    }

    public ArrayList<String> getBlsViajeActual() {
        return blsViajeActual;
    }

    public void setBlsViajeActual(ArrayList<String> blsViajeActual) {
        this.blsViajeActual = blsViajeActual;
    }

    public int getBlActual() {
        return blActual;
    }

    public void setBlActual(int blActual) {
        this.blActual = blActual;
    }

    public String getKeyLineNumberControl() {
        return keyLineNumberControl;
    }

    public void setKeyLineNumberControl(String keyLineNumberControl) {
        this.keyLineNumberControl = keyLineNumberControl;
    }

    public void limpiarVentana() {
        billOfLading.setText("");
        portOfLoading.setText("");
        placeOfDelivery.setText("");
        shipper.setText("");
//        consignee.setText("");
        consignee.setSelectedIndex(0);
//        notifyParty.setText("");
        notifyParty.setSelectedIndex(0);
        totalContainers.setText("");
        packagesUnit.setText("");
        packagesTotal.setText("");
        grossWeight.setText("");
        descriptionOfGoods.setText("");
        keyLineNumber.setText("");
        carbolTypeCode.setText("B/L");
        carbolNatCode.setText("23");
    }

    public void limpiarViaje() {
        travelNumber.setText("");
        dateOut.setText("");
        dateIn.setText("");
        placeOut.setText("");
        placeDestiny.setText("");
        transporter.setText("");
        transportMode.setText("1");
        vesselName.setText("");
        countryTransport.setText("");
        netWeight.setText("");
        grossWeight.setText("");
        documentsQty.setText("");
        packagesQty.setText("");
        totalWeight.setText("");
        containerQty.setText("");
        dischargeDate.setText("");
        grossWeightTotal.setText("");
        wareCode.setText("");
        customOfficeCode.setText("");
        this.setViajeActual("");
    }

    public extractor() {
        DataBase db = new DataBase();
        db.conectar();
        this.clientes = db.getClientes();
        initComponents();
        this.seleccion = false;
        billOfLading.setText("");
        portOfLoading.setText("");
        placeOfDelivery.setText("");
        shipper.setText("");
//        consignee.setText("");
        consignee.setSelectedIndex(0);
//        notifyParty.setText("");
        notifyParty.setSelectedIndex(0);
        totalContainers.setText("");
        packagesUnit.setText("");
        packagesTotal.setText("");
        grossWeight.setText("");
        descriptionOfGoods.setText("");
        blActual = 0;
        MainPanel.removeAll();
        MainPanel.add(panelViaje);
        MainPanel.repaint();
        MainPanel.revalidate();

        billOfLading.addMouseListener(new ContextMenuMouseListener());
        portOfLoading.addMouseListener(new ContextMenuMouseListener());;
        placeOfDelivery.addMouseListener(new ContextMenuMouseListener());
        shipper.addMouseListener(new ContextMenuMouseListener());
        consignee.addMouseListener(new ContextMenuMouseListener());
        notifyParty.addMouseListener(new ContextMenuMouseListener());
        totalContainers.addMouseListener(new ContextMenuMouseListener());
        packagesUnit.addMouseListener(new ContextMenuMouseListener());
        packagesTotal.addMouseListener(new ContextMenuMouseListener());
        grossWeight.addMouseListener(new ContextMenuMouseListener());
        descriptionOfGoods.addMouseListener(new ContextMenuMouseListener());
        keyLineNumber.addMouseListener(new ContextMenuMouseListener());
        campoBuscar.addMouseListener(new ContextMenuMouseListener());

        campoViajeBuscar.addMouseListener(new ContextMenuMouseListener());
        travelNumber.addMouseListener(new ContextMenuMouseListener());
        dateOut.addMouseListener(new ContextMenuMouseListener());
        dateIn.addMouseListener(new ContextMenuMouseListener());
        placeOut.addMouseListener(new ContextMenuMouseListener());
        placeDestiny.addMouseListener(new ContextMenuMouseListener());
        transporter.addMouseListener(new ContextMenuMouseListener());
        transportMode.addMouseListener(new ContextMenuMouseListener());
        vesselName.addMouseListener(new ContextMenuMouseListener());
        countryTransport.addMouseListener(new ContextMenuMouseListener());
        documentsQty.addMouseListener(new ContextMenuMouseListener());
        packagesQty.addMouseListener(new ContextMenuMouseListener());
        containerQty.addMouseListener(new ContextMenuMouseListener());
        totalWeight.addMouseListener(new ContextMenuMouseListener());
        netWeight.addMouseListener(new ContextMenuMouseListener());
        grossWeightTotal.addMouseListener(new ContextMenuMouseListener());
        dischargeDate.addMouseListener(new ContextMenuMouseListener());
        wareCode.addMouseListener(new ContextMenuMouseListener());
        customOfficeCode.addMouseListener(new ContextMenuMouseListener());

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem copiarItem = new JMenuItem("Copiar");
        JMenuItem pegarItem = new JMenuItem("Pegar");

        pegarItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Toolkit tk = Toolkit.getDefaultToolkit();
                Clipboard clipboard = tk.getSystemClipboard();
                DefaultTableModel tabla = (DefaultTableModel) tablaContenedores.getModel();
                int[] rows = tablaContenedores.getSelectedRows();
                int[] cols = tablaContenedores.getSelectedColumns();
                String result = "";
                try {
                    result = (String) clipboard.getData(DataFlavor.stringFlavor);
                } catch (UnsupportedFlavorException ex) {
                    Logger.getLogger(extractor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(extractor.class.getName()).log(Level.SEVERE, null, ex);
                }
                tabla.setValueAt(result, rows[0], cols[0]);
            }
        });

        copiarItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Toolkit tk = Toolkit.getDefaultToolkit();
                Clipboard clipboard = tk.getSystemClipboard();
                DefaultTableModel tabla = (DefaultTableModel) tablaContenedores.getModel();
                int[] rows = tablaContenedores.getSelectedRows();
                int[] cols = tablaContenedores.getSelectedColumns();
                String result = tabla.getValueAt(rows[0], cols[0]).toString();
                StringSelection stringSelection = new StringSelection(result);
                clipboard.setContents(stringSelection, null);
            }
        });

        popupMenu.add(copiarItem);
        popupMenu.add(pegarItem);
        tablaContenedores.setComponentPopupMenu(popupMenu);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablaContenedores.setDefaultRenderer(String.class, centerRenderer);
        //tablaSeleccionarBLS.setDefaultRenderer(String.class, centerRenderer);

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) tablaContenedores.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(JLabel.CENTER);
        panelCliente.setVisible(false);
        // DefaultTableCellRenderer renderer2 = (DefaultTableCellRenderer) tablaSeleccionarBLS.getTableHeader().getDefaultRenderer();
        //renderer2.setHorizontalAlignment(JLabel.CENTER);
    }

    public void obtenerBL() {
        DataBase db = new DataBase();
        db.conectar();
        String blID = campoBuscar.getText().toUpperCase();
        BL resultado = db.obtenerBL(blID);
        billOfLading.setText(resultado.getBillOfLading());
        portOfLoading.setText(resultado.getPortOfLoading());
        placeOfDelivery.setText(resultado.getPlaceOfDelivery());
        shipper.setText(resultado.getShipper());
//        consignee.setText(resultado.getConsignee());
//        notifyParty.setText(resultado.getNotifyParty());
        if (!resultado.getConsignee().contains("/")) {
            consignee.setSelectedItem("ELEGIR/J-00000000-0");
            notifyParty.setSelectedItem("ELEGIR/J-00000000-0");
        } else {
            consignee.setSelectedItem(resultado.getConsignee());
            notifyParty.setSelectedItem(resultado.getNotifyParty());
        }
        totalContainers.setText(resultado.getTotalContainers());
        packagesUnit.setText(resultado.getPackagesUnit());
        packagesTotal.setText(resultado.getPackagesTotal());
        grossWeight.setText(resultado.getGrossWeight());
        descriptionOfGoods.setText(resultado.getDescriptionOfGoods());
        keyLineNumber.setText(resultado.getKeyLineNumber());
        carbolTypeCode.setText(resultado.getCarbolTypeCode());
        carbolNatCode.setText(resultado.getCarbolNatCode());
        keyLineNumberControl = resultado.getKeyLineNumber();
        ArrayList<Contenedor> contenedores = db.obtenerContenedoresBL(blID);
        DefaultTableModel tabla = (DefaultTableModel) tablaContenedores.getModel();
        tabla.setRowCount(0);
        int i = 0;
        for (Contenedor contenedor : contenedores) {
            tabla.insertRow(i, new Object[]{contenedor.getNumber(),
                contenedor.getSeal(), contenedor.getSeal2(), contenedor.getSeal3(),
                contenedor.getType()});
            i++;
        }
    }

    public void obtenerBL(String blID) {
        DataBase db = new DataBase();
        db.conectar();
        BL resultado = db.obtenerBL(blID.toUpperCase());
        billOfLading.setText(resultado.getBillOfLading().toUpperCase());
        portOfLoading.setText(resultado.getPortOfLoading());
        placeOfDelivery.setText(resultado.getPlaceOfDelivery());
        shipper.setText(resultado.getShipper());
        //consignee.setText(resultado.getConsignee());  
        //notifyParty.setText(resultado.getNotifyParty());
        if (!resultado.getConsignee().contains("/")) {
            consignee.setSelectedItem("ELEGIR/J-00000000-0");
            notifyParty.setSelectedItem("ELEGIR/J-00000000-0");
        } else {
            consignee.setSelectedItem(resultado.getConsignee());
            notifyParty.setSelectedItem(resultado.getNotifyParty());
        }
        totalContainers.setText(resultado.getTotalContainers());
        packagesUnit.setText(resultado.getPackagesUnit());
        packagesTotal.setText(resultado.getPackagesTotal());
        grossWeight.setText(resultado.getGrossWeight());
        descriptionOfGoods.setText(resultado.getDescriptionOfGoods());
        keyLineNumber.setText(resultado.getKeyLineNumber());
        carbolTypeCode.setText(resultado.getCarbolTypeCode());
        carbolNatCode.setText(resultado.getCarbolNatCode());
        keyLineNumberControl = resultado.getKeyLineNumber();
        ArrayList<Contenedor> contenedores = db.obtenerContenedoresBL(blID);
        DefaultTableModel tabla = (DefaultTableModel) tablaContenedores.getModel();
        tabla.setRowCount(0);
        int i = 0;
        for (Contenedor contenedor : contenedores) {
            tabla.insertRow(i, new Object[]{contenedor.getNumber().toUpperCase(),
                contenedor.getSeal(), contenedor.getSeal2(),
                contenedor.getSeal3(),
                contenedor.getType()
            });
            i++;
        }
    }

    public void obtenerViaje() {
        DataBase db = new DataBase();
        db.conectar();
        String viajeID = this.getViajeActual();
        Viaje resultado = db.obtenerViajeCompleto(viajeID);
        travelNumber.setText(resultado.getNumber());
        dateOut.setText(resultado.getDateOut());
        dateIn.setText(resultado.getDateIn());
        placeOut.setText(resultado.getPlaceOut());
        placeDestiny.setText(resultado.getPlaceDestiny());
        transporter.setText(resultado.getTransporter());
        transportMode.setText(resultado.getTransportMode());
        vesselName.setText(resultado.getVesselName());
        countryTransport.setText(resultado.getCountryTransport());
        netWeight.setText(resultado.getNetWeight());
        totalWeight.setText(resultado.getGrossWeight());
        documentsQty.setText(resultado.getDocumentsQty());
        packagesQty.setText(resultado.getPackagesQty());
        grossWeightTotal.setText(resultado.getTotalWeight());
        containerQty.setText(resultado.getContainerQty());
        dischargeDate.setText(resultado.getDateDischarge());
        wareCode.setText(resultado.getWareCode());
        customOfficeCode.setText(resultado.getCustomOfficeCode());
        this.setViajeActual(viajeID);
        if (resultado.getNumber() == null) {
            JOptionPane.showMessageDialog(this, "No existe viaje con ese número");
        } else {
            documentsQty.setText(String.valueOf(resultado.getViajeBLS().size()));
            packagesQty.setText(String.valueOf(calcularPaquetes(resultado)));
            containerQty.setText(String.valueOf(calcularContenedores(resultado)));
            Double res = calcularPesos(resultado);
            int peso = res.intValue();
            int pesoTon = peso / 1000;
            netWeight.setText(String.valueOf(pesoTon));
            totalWeight.setText(String.valueOf(pesoTon));
            grossWeightTotal.setText("" + res);
        }
    }

    public void obtenerViajeSeleccionBLS(ArrayList<String> bls) {
        DataBase db = new DataBase();
        db.conectar();
        String viajeID = this.getViajeActual();
        Viaje resultado = db.obtenerViajeCompletoSeleccionBLS(viajeID, bls);
        travelNumber.setText(resultado.getNumber());
        dateOut.setText(resultado.getDateOut());
        dateIn.setText(resultado.getDateIn());
        placeOut.setText(resultado.getPlaceOut());
        placeDestiny.setText(resultado.getPlaceDestiny());
        transporter.setText(resultado.getTransporter());
        transportMode.setText(resultado.getTransportMode());
        vesselName.setText(resultado.getVesselName());
        countryTransport.setText(resultado.getCountryTransport());
        netWeight.setText(resultado.getNetWeight());
        totalWeight.setText(resultado.getGrossWeight());
        documentsQty.setText(resultado.getDocumentsQty());
        packagesQty.setText(resultado.getPackagesQty());
        grossWeightTotal.setText(resultado.getTotalWeight());
        containerQty.setText(resultado.getContainerQty());
        dischargeDate.setText(resultado.getDateDischarge());
        wareCode.setText(resultado.getWareCode());
        customOfficeCode.setText(resultado.getCustomOfficeCode());
        this.setViajeActual(viajeID);
        if (resultado.getNumber() == null) {
            JOptionPane.showMessageDialog(this, "No existe viaje con ese número");
        } else {
            documentsQty.setText(String.valueOf(resultado.getViajeBLS().size()));
            packagesQty.setText(String.valueOf(calcularPaquetes(resultado)));
            containerQty.setText(String.valueOf(calcularContenedores(resultado)));
            Double res = calcularPesos(resultado);
            int peso = res.intValue();
            int pesoTon = peso / 1000;
            netWeight.setText(String.valueOf(pesoTon));
            totalWeight.setText(String.valueOf(pesoTon));
            grossWeightTotal.setText("" + res);
        }
    }

    public int calcularPaquetes(Viaje viaje) {
        int cantPaquetes = 0;
        for (BL bl : viaje.getViajeBLS()) {
            if (bl.getPackagesTotal().equals("")) {
                cantPaquetes = cantPaquetes + 0;
            } else {
                cantPaquetes = cantPaquetes + Integer.parseInt(bl.getPackagesTotal());
            }
        }
        return cantPaquetes;
    }

    public int calcularContenedores(Viaje viaje) {
        int cantContenedores = 0;
        for (ArrayList<Contenedor> grupoContenedor : viaje.getViajeContenedores()) {
            cantContenedores = cantContenedores + grupoContenedor.size();
        }
        return cantContenedores;
    }

    public Double calcularPesos(Viaje viaje) {
        Double cantPesos = 0.0;
        for (BL bl : viaje.getViajeBLS()) {
            if (bl.getGrossWeight().equals("")) {
                cantPesos = cantPesos + 0.0;
            } else {
                cantPesos = cantPesos + Double.parseDouble(bl.getGrossWeight());
            }
        }
        return cantPesos;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new javax.swing.JPanel();
        panelSeleccionarBLS = new javax.swing.JPanel();
        labelSeleccionarBLS = new javax.swing.JLabel();
        btnVolverViajeSBL = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        panelBL = new javax.swing.JPanel();
        consigneeLabel = new javax.swing.JLabel();
        shipperLabel = new javax.swing.JLabel();
        placeOfDeliveryLabel = new javax.swing.JLabel();
        portOfLoadingLabel = new javax.swing.JLabel();
        grossWeightLabel = new javax.swing.JLabel();
        notifyPartyLabel = new javax.swing.JLabel();
        packagesTotalLabel = new javax.swing.JLabel();
        packagesUnitLabel = new javax.swing.JLabel();
        totalContainersLabel = new javax.swing.JLabel();
        descriptionOfGoodsLabel = new javax.swing.JLabel();
        billOfLadingLabel = new javax.swing.JLabel();
        packagesUnit = new javax.swing.JTextField();
        totalContainers = new javax.swing.JTextField();
        shipper = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionOfGoods = new javax.swing.JTextArea();
        grossWeight = new javax.swing.JTextField();
        packagesTotal = new javax.swing.JTextField();
        billOfLading = new javax.swing.JTextField();
        portOfLoading = new javax.swing.JTextField();
        placeOfDelivery = new javax.swing.JTextField();
        nombreBLLabel = new javax.swing.JLabel();
        campoBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnAgregarContenedor = new javax.swing.JButton();
        btnEliminarContenedor = new javax.swing.JButton();
        labelContainerTable = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaContenedores = new javax.swing.JTable();
        btnInsert = new javax.swing.JButton();
        btnVolverViaje = new javax.swing.JButton();
        btnSiguienteBL = new javax.swing.JButton();
        btnAnteriorBL = new javax.swing.JButton();
        btnNuevoBL = new javax.swing.JButton();
        btnEliminarBL = new javax.swing.JButton();
        labelKeyLineNumber = new javax.swing.JLabel();
        keyLineNumber = new javax.swing.JTextField();
        labelCarbolTypeCode = new javax.swing.JLabel();
        carbolTypeCode = new javax.swing.JTextField();
        labelCarbolNatCode = new javax.swing.JLabel();
        carbolNatCode = new javax.swing.JTextField();
        consignee = new javax.swing.JComboBox();
        notifyParty = new javax.swing.JComboBox();
        btnAgregarCliente = new javax.swing.JButton();
        panelViaje = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        panelViaje2 = new javax.swing.JPanel();
        labelTravelNumber = new javax.swing.JLabel();
        labelDateOut = new javax.swing.JLabel();
        labelDateIn = new javax.swing.JLabel();
        labelNetWeight = new javax.swing.JLabel();
        labelPlaceOut = new javax.swing.JLabel();
        labelTransporter = new javax.swing.JLabel();
        labelTransportMode = new javax.swing.JLabel();
        labelVesselName = new javax.swing.JLabel();
        labelDocumentsQty = new javax.swing.JLabel();
        labelPackagesQty = new javax.swing.JLabel();
        labelContainersQty = new javax.swing.JLabel();
        labelTotalWeight = new javax.swing.JLabel();
        travelNumber = new javax.swing.JTextField();
        dateOut = new javax.swing.JTextField();
        dateIn = new javax.swing.JTextField();
        netWeight = new javax.swing.JTextField();
        placeOut = new javax.swing.JTextField();
        transporter = new javax.swing.JTextField();
        transportMode = new javax.swing.JTextField();
        vesselName = new javax.swing.JTextField();
        documentsQty = new javax.swing.JTextField();
        packagesQty = new javax.swing.JTextField();
        containerQty = new javax.swing.JTextField();
        totalWeight = new javax.swing.JTextField();
        nombreViajeLabel = new javax.swing.JLabel();
        campoViajeBuscar = new javax.swing.JTextField();
        btnBuscarViaje = new javax.swing.JButton();
        labelPlaceDestiny = new javax.swing.JLabel();
        placeDestiny = new javax.swing.JTextField();
        labelGrossWeightTotal = new javax.swing.JLabel();
        grossWeightTotal = new javax.swing.JTextField();
        labelCountryTransport = new javax.swing.JLabel();
        countryTransport = new javax.swing.JTextField();
        labelDischargeDate = new javax.swing.JLabel();
        dischargeDate = new javax.swing.JTextField();
        labelWareCode = new javax.swing.JLabel();
        wareCode = new javax.swing.JTextField();
        btnEliminarViaje = new javax.swing.JButton();
        labelCustomOfficeCode = new javax.swing.JLabel();
        customOfficeCode = new javax.swing.JTextField();
        btnGenerarCSV = new javax.swing.JButton();
        btnSeleccionarBLS = new javax.swing.JButton();
        btnGenerarXML = new javax.swing.JButton();
        btnGenerarCAR = new javax.swing.JButton();
        btnLimpiarViaje = new javax.swing.JButton();
        btnModificarViaje = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        panelCliente = new javax.swing.JPanel();
        labelNombre = new javax.swing.JLabel();
        labelRIF = new javax.swing.JLabel();
        nombreCliente = new javax.swing.JTextField();
        rifCliente = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        MainPanel.setLayout(new java.awt.CardLayout());

        labelSeleccionarBLS.setText("Seleccione los BLS que desea utilizar");

        btnVolverViajeSBL.setText("Viaje");
        btnVolverViajeSBL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverViajeSBLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 362, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 609, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(jPanel1);

        javax.swing.GroupLayout panelSeleccionarBLSLayout = new javax.swing.GroupLayout(panelSeleccionarBLS);
        panelSeleccionarBLS.setLayout(panelSeleccionarBLSLayout);
        panelSeleccionarBLSLayout.setHorizontalGroup(
            panelSeleccionarBLSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeleccionarBLSLayout.createSequentialGroup()
                .addGap(276, 276, 276)
                .addGroup(panelSeleccionarBLSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSeleccionarBLS)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelSeleccionarBLSLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnVolverViajeSBL, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(310, Short.MAX_VALUE))
        );
        panelSeleccionarBLSLayout.setVerticalGroup(
            panelSeleccionarBLSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSeleccionarBLSLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelSeleccionarBLS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVolverViajeSBL)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        MainPanel.add(panelSeleccionarBLS, "card7");

        panelBL.setPreferredSize(new java.awt.Dimension(567, 700));

        consigneeLabel.setText("Consignee");

        shipperLabel.setText("Shipper");

        placeOfDeliveryLabel.setText("Place Of Delivery");

        portOfLoadingLabel.setText("Port Of Loading");

        grossWeightLabel.setText("Gross Weight");

        notifyPartyLabel.setText("Notify Party");

        packagesTotalLabel.setText("Packages Total");

        packagesUnitLabel.setText("Packages Unit");

        totalContainersLabel.setText("Total Containers");

        descriptionOfGoodsLabel.setText("Description Of Goods");

        billOfLadingLabel.setText("Bill Of Lading");

        packagesUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packagesUnitActionPerformed(evt);
            }
        });

        totalContainers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalContainersActionPerformed(evt);
            }
        });

        shipper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shipperActionPerformed(evt);
            }
        });

        descriptionOfGoods.setColumns(20);
        descriptionOfGoods.setRows(5);
        jScrollPane1.setViewportView(descriptionOfGoods);

        grossWeight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grossWeightActionPerformed(evt);
            }
        });

        packagesTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packagesTotalActionPerformed(evt);
            }
        });

        billOfLading.setToolTipText("Bill Of Lading");
        billOfLading.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billOfLadingActionPerformed(evt);
            }
        });

        portOfLoading.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portOfLoadingActionPerformed(evt);
            }
        });

        placeOfDelivery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placeOfDeliveryActionPerformed(evt);
            }
        });

        nombreBLLabel.setText("Bill Of Lading");
        nombreBLLabel.setToolTipText("Bill Of Lading");

        campoBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoBuscarActionPerformed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnAgregarContenedor.setText("+");
        btnAgregarContenedor.setToolTipText("Agregar Contenedor");
        btnAgregarContenedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarContenedorActionPerformed(evt);
            }
        });

        btnEliminarContenedor.setText("-");
        btnEliminarContenedor.setToolTipText("Eliminar Contenedor");
        btnEliminarContenedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarContenedorActionPerformed(evt);
            }
        });

        labelContainerTable.setText("Containers");

        tablaContenedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Number", "Seal 1", "Seal 2", "Seal 3", "Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tablaContenedores.setToolTipText("");
        tablaContenedores.setCellSelectionEnabled(true);
        jScrollPane2.setViewportView(tablaContenedores);

        btnInsert.setText("Guardar");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnVolverViaje.setText("Viaje");
        btnVolverViaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverViajeActionPerformed(evt);
            }
        });

        btnSiguienteBL.setText("Siguiente");
        btnSiguienteBL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteBLActionPerformed(evt);
            }
        });

        btnAnteriorBL.setText("Anterior");
        btnAnteriorBL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorBLActionPerformed(evt);
            }
        });

        btnNuevoBL.setText("Nuevo");
        btnNuevoBL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoBLActionPerformed(evt);
            }
        });

        btnEliminarBL.setText("X");
        btnEliminarBL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarBLActionPerformed(evt);
            }
        });

        labelKeyLineNumber.setText("No");

        labelCarbolTypeCode.setText("BL Type");

        carbolTypeCode.setText("B/L");

        labelCarbolNatCode.setText("BL Nature");

        carbolNatCode.setText("23");

        consignee.setModel(new DefaultComboBoxModel(this.clientes));
        consignee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consigneeActionPerformed(evt);
            }
        });

        notifyParty.setModel(new DefaultComboBoxModel(this.clientes));

        btnAgregarCliente.setText("+");
        btnAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBLLayout = new javax.swing.GroupLayout(panelBL);
        panelBL.setLayout(panelBLLayout);
        panelBLLayout.setHorizontalGroup(
            panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBLLayout.createSequentialGroup()
                .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBLLayout.createSequentialGroup()
                        .addComponent(labelKeyLineNumber)
                        .addGap(18, 18, 18)
                        .addComponent(keyLineNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(billOfLadingLabel)
                    .addGroup(panelBLLayout.createSequentialGroup()
                        .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCarbolNatCode)
                            .addComponent(portOfLoadingLabel)
                            .addComponent(placeOfDeliveryLabel)
                            .addComponent(totalContainersLabel)
                            .addComponent(consigneeLabel)
                            .addComponent(notifyPartyLabel)
                            .addComponent(packagesUnitLabel)
                            .addComponent(grossWeightLabel)
                            .addComponent(labelCarbolTypeCode)
                            .addComponent(descriptionOfGoodsLabel)
                            .addComponent(shipperLabel)
                            .addComponent(packagesTotalLabel))
                        .addGap(18, 18, 18)
                        .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(carbolNatCode, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 537, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBLLayout.createSequentialGroup()
                                .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelBLLayout.createSequentialGroup()
                                        .addGap(96, 96, 96)
                                        .addComponent(nombreBLLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(campoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnAgregarCliente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnEliminarBL))
                                    .addComponent(carbolTypeCode, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(grossWeight, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(packagesTotal, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(packagesUnit, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(totalContainers, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(billOfLading, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(portOfLoading, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(placeOfDelivery, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(shipper, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(consignee, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(notifyParty, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(49, 49, 49))))
                    .addGroup(panelBLLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregarContenedor)
                            .addComponent(btnEliminarContenedor))))
                .addGap(0, 32, Short.MAX_VALUE))
            .addGroup(panelBLLayout.createSequentialGroup()
                .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBLLayout.createSequentialGroup()
                        .addGap(241, 241, 241)
                        .addComponent(labelContainerTable))
                    .addGroup(panelBLLayout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addComponent(btnVolverViaje, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAnteriorBL)
                        .addGap(35, 35, 35)
                        .addComponent(btnNuevoBL)
                        .addGap(18, 18, 18)
                        .addComponent(btnSiguienteBL)
                        .addGap(38, 38, 38)
                        .addComponent(btnInsert)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBLLayout.setVerticalGroup(
            panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBLLayout.createSequentialGroup()
                .addContainerGap(102, Short.MAX_VALUE)
                .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBLLayout.createSequentialGroup()
                        .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelKeyLineNumber)
                            .addComponent(keyLineNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(billOfLadingLabel)
                        .addGap(9, 9, 9)
                        .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(portOfLoading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(portOfLoadingLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(placeOfDelivery, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(placeOfDeliveryLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(shipper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(shipperLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(consigneeLabel)
                            .addComponent(consignee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(notifyPartyLabel)
                            .addComponent(notifyParty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalContainersLabel)
                            .addComponent(totalContainers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(packagesUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(packagesUnitLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(packagesTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(packagesTotalLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(grossWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(grossWeightLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(carbolTypeCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCarbolTypeCode)))
                    .addGroup(panelBLLayout.createSequentialGroup()
                        .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBLLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nombreBLLabel)
                                    .addComponent(campoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnEliminarBL)
                                .addComponent(btnAgregarCliente)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(billOfLading, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(carbolNatCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCarbolNatCode))
                .addGap(5, 5, 5)
                .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionOfGoodsLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelContainerTable)
                .addGap(1, 1, 1)
                .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBLLayout.createSequentialGroup()
                        .addComponent(btnAgregarContenedor)
                        .addGap(10, 10, 10)
                        .addComponent(btnEliminarContenedor))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolverViaje)
                    .addComponent(btnAnteriorBL)
                    .addComponent(btnNuevoBL)
                    .addComponent(btnSiguienteBL)
                    .addComponent(btnInsert)))
        );

        MainPanel.add(panelBL, "card5");

        jScrollPane4.setBorder(null);

        labelTravelNumber.setText("Número de viaje");

        labelDateOut.setText("Fecha de salida");

        labelDateIn.setText("Fecha de llegada");

        labelNetWeight.setText("Peso neto");

        labelPlaceOut.setText("Lugar de salida");

        labelTransporter.setText("Transportista");

        labelTransportMode.setText("Modo de transporte");

        labelVesselName.setText("Nombre de buque");

        labelDocumentsQty.setText("Cantidad de documentos");

        labelPackagesQty.setText("Cantidad de bultos");

        labelContainersQty.setText("Cantidad de contenedores");

        labelTotalWeight.setText("Peso bruto");

        travelNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                travelNumberActionPerformed(evt);
            }
        });

        transportMode.setText("1");

        containerQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                containerQtyActionPerformed(evt);
            }
        });

        totalWeight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalWeightActionPerformed(evt);
            }
        });

        nombreViajeLabel.setText("Viaje");

        campoViajeBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoViajeBuscarActionPerformed(evt);
            }
        });

        btnBuscarViaje.setText("Buscar");
        btnBuscarViaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarViajeActionPerformed(evt);
            }
        });

        labelPlaceDestiny.setText("Lugar de destino");

        labelGrossWeightTotal.setText("Peso bruto total");

        labelCountryTransport.setText("País de transporte");

        labelDischargeDate.setText("Fecha de despacho");

        labelWareCode.setText("Código de almacén");

        wareCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wareCodeActionPerformed(evt);
            }
        });

        btnEliminarViaje.setText("X");
        btnEliminarViaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarViajeActionPerformed(evt);
            }
        });

        labelCustomOfficeCode.setText("Código de oficina");

        btnGenerarCSV.setText("I");
        btnGenerarCSV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarCSVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelViaje2Layout = new javax.swing.GroupLayout(panelViaje2);
        panelViaje2.setLayout(panelViaje2Layout);
        panelViaje2Layout.setHorizontalGroup(
            panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelViaje2Layout.createSequentialGroup()
                .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelViaje2Layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addComponent(nombreViajeLabel)
                        .addGap(60, 60, 60)
                        .addComponent(campoViajeBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBuscarViaje, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addComponent(btnGenerarCSV, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarViaje))
                    .addGroup(panelViaje2Layout.createSequentialGroup()
                        .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDischargeDate)
                            .addComponent(labelWareCode)
                            .addComponent(labelPlaceDestiny)
                            .addComponent(labelNetWeight)
                            .addComponent(labelGrossWeightTotal)
                            .addComponent(labelDocumentsQty)
                            .addComponent(labelPackagesQty)
                            .addComponent(labelTotalWeight)
                            .addComponent(labelContainersQty)
                            .addComponent(labelTravelNumber)
                            .addComponent(labelDateOut)
                            .addComponent(labelPlaceOut)
                            .addComponent(labelTransporter)
                            .addComponent(labelTransportMode)
                            .addComponent(labelVesselName)
                            .addComponent(labelDateIn)
                            .addComponent(labelCountryTransport)
                            .addComponent(labelCustomOfficeCode))
                        .addGap(18, 18, 18)
                        .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(placeOut)
                            .addComponent(placeDestiny)
                            .addComponent(travelNumber)
                            .addComponent(dateOut, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dateIn, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(transportMode)
                            .addComponent(transporter, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(countryTransport)
                            .addComponent(vesselName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(packagesQty)
                            .addComponent(documentsQty, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(totalWeight)
                            .addComponent(containerQty, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(grossWeightTotal)
                            .addComponent(netWeight, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(customOfficeCode)
                            .addComponent(wareCode, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dischargeDate, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(68, 68, 68))
        );
        panelViaje2Layout.setVerticalGroup(
            panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViaje2Layout.createSequentialGroup()
                .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoViajeBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarViaje, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreViajeLabel)
                    .addComponent(btnEliminarViaje)
                    .addComponent(btnGenerarCSV))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTravelNumber)
                    .addComponent(travelNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDateOut)
                    .addComponent(dateOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDateIn)
                    .addComponent(dateIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPlaceOut)
                    .addComponent(placeOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPlaceDestiny)
                    .addComponent(placeDestiny, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTransporter)
                    .addComponent(transporter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTransportMode)
                    .addComponent(transportMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelVesselName)
                    .addComponent(vesselName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCountryTransport)
                    .addComponent(countryTransport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDocumentsQty)
                    .addComponent(documentsQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPackagesQty)
                    .addComponent(packagesQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelContainersQty)
                    .addComponent(containerQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTotalWeight)
                    .addComponent(totalWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNetWeight)
                    .addComponent(netWeight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelGrossWeightTotal)
                    .addComponent(grossWeightTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDischargeDate)
                    .addComponent(dischargeDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelWareCode)
                    .addComponent(wareCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelViaje2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCustomOfficeCode)
                    .addComponent(customOfficeCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 69, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(panelViaje2);

        btnSeleccionarBLS.setText("Seleccionar BLS");
        btnSeleccionarBLS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarBLSActionPerformed(evt);
            }
        });

        btnGenerarXML.setText("XML");
        btnGenerarXML.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarXMLActionPerformed(evt);
            }
        });

        btnGenerarCAR.setText("CAR");
        btnGenerarCAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarCARActionPerformed(evt);
            }
        });

        btnLimpiarViaje.setText("Limpiar");
        btnLimpiarViaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarViajeActionPerformed(evt);
            }
        });

        btnModificarViaje.setText("Guardar");
        btnModificarViaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarViajeActionPerformed(evt);
            }
        });

        btnSiguiente.setText("Siguiente");
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelViajeLayout = new javax.swing.GroupLayout(panelViaje);
        panelViaje.setLayout(panelViajeLayout);
        panelViajeLayout.setHorizontalGroup(
            panelViajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
            .addGroup(panelViajeLayout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(btnSeleccionarBLS)
                .addGap(18, 18, 18)
                .addComponent(btnGenerarXML)
                .addGap(18, 18, 18)
                .addComponent(btnGenerarCAR)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiarViaje, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificarViaje, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSiguiente)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelViajeLayout.setVerticalGroup(
            panelViajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelViajeLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(panelViajeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarViaje)
                    .addComponent(btnLimpiarViaje)
                    .addComponent(btnSiguiente)
                    .addComponent(btnSeleccionarBLS)
                    .addComponent(btnGenerarXML)
                    .addComponent(btnGenerarCAR))
                .addContainerGap())
        );

        MainPanel.add(panelViaje, "card5");

        labelNombre.setText("Nombre");

        labelRIF.setText("RIF");

        javax.swing.GroupLayout panelClienteLayout = new javax.swing.GroupLayout(panelCliente);
        panelCliente.setLayout(panelClienteLayout);
        panelClienteLayout.setHorizontalGroup(
            panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClienteLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNombre)
                    .addComponent(labelRIF))
                .addGap(18, 18, 18)
                .addGroup(panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreCliente)
                    .addComponent(rifCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                .addGap(27, 27, 27))
        );
        panelClienteLayout.setVerticalGroup(
            panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClienteLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombre)
                    .addComponent(nombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRIF)
                    .addComponent(rifCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(MainPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 663, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void billOfLadingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billOfLadingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_billOfLadingActionPerformed

    private void shipperActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shipperActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_shipperActionPerformed

    private void totalContainersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalContainersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalContainersActionPerformed

    private void packagesUnitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packagesUnitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_packagesUnitActionPerformed

    private void grossWeightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grossWeightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grossWeightActionPerformed

    private void packagesTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packagesTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_packagesTotalActionPerformed

    private void portOfLoadingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portOfLoadingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_portOfLoadingActionPerformed

    private void placeOfDeliveryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placeOfDeliveryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_placeOfDeliveryActionPerformed

    private void campoBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoBuscarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        String blID = campoBuscar.getText().toUpperCase();
        if (this.blsViajeActual.contains(blID)) {
            this.obtenerBL();
        } else {
            JOptionPane.showMessageDialog(this, "BL no se encuentra en este viaje");
        }
        campoBuscar.setText("");
        billOfLading.setEditable(false);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarContenedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarContenedorActionPerformed
        DefaultTableModel tabla = (DefaultTableModel) tablaContenedores.getModel();
        int[] rows = tablaContenedores.getSelectedRows();
        for (int i = 0; i < rows.length; i++) {
            tabla.removeRow(rows[i] - i);
        }
    }//GEN-LAST:event_btnEliminarContenedorActionPerformed

    private void btnAgregarContenedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarContenedorActionPerformed
        DefaultTableModel tabla = (DefaultTableModel) tablaContenedores.getModel();
        tabla.addRow(new Object[]{});
    }//GEN-LAST:event_btnAgregarContenedorActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        DataBase db = new DataBase();
        db.conectar();
        String mensaje = "";
//        if (keyLineNumber.getText().equals("")) {
//            mensaje = "Debe introducir un número de línea";
//        } else 
//        if (db.existeKeyLineInBL(keyLineNumber.getText(), this.viajeActual)
//                && !(keyLineNumber.getText().equals(keyLineNumberControl))) {
//            mensaje = "Ya existe un BL con este número de línea: " + keyLineNumber.getText();
//            keyLineNumber.setText(keyLineNumberControl);
//        } else 
        if (billOfLading.getText().equals("")) {
            mensaje = "Debe introducir un número de BL";
        } else {
            //Información BLS
            BL bl = new BL();
            bl.setBillOfLading(billOfLading.getText().toUpperCase());
//            bl.setConsignee(consignee.getText() != null ? consignee.getText().toUpperCase() : "");
            bl.setConsignee((String) consignee.getSelectedItem());
            bl.setDescriptionOfGoods(descriptionOfGoods.getText() != null
                    ? descriptionOfGoods.getText().toUpperCase().replace("\'", " ") : "");
            bl.setGrossWeight(grossWeight.getText() != null ? grossWeight.getText().toUpperCase() : "");
//            bl.setNotifyParty(notifyParty.getText() != null ? notifyParty.getText().toUpperCase() : "");
            bl.setNotifyParty((String) notifyParty.getSelectedItem());
            bl.setPackagesTotal(packagesTotal.getText() != null ? packagesTotal.getText().toUpperCase() : "");
            bl.setPackagesUnit(packagesUnit.getText() != null ? packagesUnit.getText().toUpperCase() : "");
            bl.setPlaceOfDelivery(placeOfDelivery.getText() != null ? placeOfDelivery.getText().toUpperCase() : "");
            bl.setPortOfLoading(portOfLoading.getText() != null ? portOfLoading.getText().toUpperCase() : "");
            bl.setShipper(shipper.getText() != null ? shipper.getText().toUpperCase() : "");
            bl.setTotalContainers(totalContainers.getText() != null ? totalContainers.getText().toUpperCase() : "");
            bl.setKeyLineNumber(keyLineNumber.getText() != null ? keyLineNumber.getText().toUpperCase() : "");
            bl.setCarbolTypeCode(carbolTypeCode.getText() != null ? carbolTypeCode.getText().toUpperCase() : "");
            bl.setCarbolNatCode(carbolNatCode.getText() != null ? carbolNatCode.getText().toUpperCase() : "");
            bl.setTravel(this.viajeActual.toUpperCase());
            //Información Contenedores
            List<Contenedor> listaContenedores = new ArrayList();
            DefaultTableModel tabla = (DefaultTableModel) tablaContenedores.getModel();
            int nRow = tabla.getRowCount();
            for (int i = 0; i < nRow; i++) {
                String numero = (String) tabla.getValueAt(i, 0);
                String precinto = (String) tabla.getValueAt(i, 1);
                String precinto2 = (String) tabla.getValueAt(i, 2);
                String precinto3 = (String) tabla.getValueAt(i, 3);
                String tipo = (String) tabla.getValueAt(i, 4);
                if (precinto2 == null || precinto2.equals("")) {
                    precinto2 = "NA";
                }
                if (precinto3 == null || precinto3.equals("")) {
                    precinto3 = "NA";
                }
                Contenedor contenedor = new Contenedor(numero.toUpperCase(),
                        precinto.toUpperCase(), precinto2.toUpperCase(),
                        precinto3.toUpperCase(), tipo.toUpperCase());
                listaContenedores.add(contenedor);
            }
            //Conexion a base de datos
            if (db.existeBL(bl.getBillOfLading())) {
                mensaje = db.modificarBL(bl);
                db.eliminarContenedores(bl.getBillOfLading());
                db.insertarContenedores(listaContenedores, bl.getBillOfLading());
            } else {
                mensaje = db.insertarBL(bl);
                db.insertarContenedores(listaContenedores, bl.getBillOfLading());
                this.setBlsViajeActual(db.obtenerBLSViaje(this.getViajeActual()));
                this.blActual = this.blsViajeActual.size() - 1;
            }
        }

        JOptionPane.showMessageDialog(this,
                mensaje);
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnVolverViajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverViajeActionPerformed
        if (this.seleccion == false) {
            this.obtenerViaje();
        } else {
            this.obtenerViajeSeleccionBLS(this.seleccionBLS);
        }
        travelNumber.setEditable(false);
        MainPanel.removeAll();
        MainPanel.add(panelViaje);
        MainPanel.repaint();
        MainPanel.revalidate();

    }//GEN-LAST:event_btnVolverViajeActionPerformed

    private void btnSiguienteBLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteBLActionPerformed
        if (this.blActual < this.blsViajeActual.size() - 1) {
            this.obtenerBL(this.blsViajeActual.get(this.blActual + 1));
            this.blActual++;
        } else {
            JOptionPane.showMessageDialog(this, "No hay más BLS disponibles");
        }
        billOfLading.setEditable(false);
    }//GEN-LAST:event_btnSiguienteBLActionPerformed

    private void btnAnteriorBLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorBLActionPerformed
        if (this.blActual > 0) {
            this.obtenerBL(this.blsViajeActual.get(this.blActual - 1));
            this.blActual--;
        } else {
            JOptionPane.showMessageDialog(this, "Este es el primer BL");
        }
        billOfLading.setEditable(false);
    }//GEN-LAST:event_btnAnteriorBLActionPerformed

    private void btnNuevoBLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoBLActionPerformed
        this.limpiarVentana();
        DefaultTableModel tabla = (DefaultTableModel) tablaContenedores.getModel();
        tabla.setRowCount(0);
        billOfLading.setEditable(true);
    }//GEN-LAST:event_btnNuevoBLActionPerformed

    private void btnEliminarBLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarBLActionPerformed
        if (this.billOfLading.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe introducir un número de BL");
        } else {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "¿Está seguro(a) desea eliminar este BL: "
                    + this.billOfLading.getText(), "Confirmar", dialogButton);
            if (dialogResult == 0) {
                DataBase db = new DataBase();
                db.conectar();
                db.eliminarBL(this.billOfLading.getText());
                this.setBlsViajeActual(db.obtenerBLSViaje(this.getViajeActual()));
                this.limpiarVentana();
                DefaultTableModel tabla = (DefaultTableModel) tablaContenedores.getModel();
                tabla.setRowCount(0);
                if (this.getBlsViajeActual().size() > 0) {
                    if (this.blActual >= 1) {
                        this.setBlActual(this.blActual - 1);
                    } else if (this.blActual == 0) {
                        this.setBlActual(0);
                    }
                    this.obtenerBL(this.blsViajeActual.get(this.blActual));
                }
                billOfLading.setEditable(false);
                JOptionPane.showMessageDialog(this, "BL eliminado con éxito");
            }
        }
    }//GEN-LAST:event_btnEliminarBLActionPerformed

    private void btnGenerarCARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarCARActionPerformed
        String[] args = new String[2];
        args[0] = this.ruta + "XML\\" + this.viajeActual + ".xml";
        args[1] = this.ruta + "CAR_NEW\\" + this.viajeActual + ".car";
        seniat.app.Xml2Car.main(args);
        JOptionPane.showMessageDialog(this, "Archivo CAR generado con éxito");
    }//GEN-LAST:event_btnGenerarCARActionPerformed

    private void btnLimpiarViajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarViajeActionPerformed
        this.limpiarViaje();
        travelNumber.setEditable(true);
    }//GEN-LAST:event_btnLimpiarViajeActionPerformed

    private void btnModificarViajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarViajeActionPerformed
        String mensaje = "";
        if (travelNumber.getText().equals("")) {
            mensaje = "Debe introducir un número de viaje";
        } else {
            Viaje viaje = new Viaje();
            viaje.setNumber(travelNumber.getText().toUpperCase());
            viaje.setDateOut(dateOut.getText() != null ? dateOut.getText().toUpperCase() : "");
            viaje.setDateIn(dateIn.getText() != null ? dateIn.getText().toUpperCase() : "");
            //viaje.setHourIn(netWeight.getText() != null ? netWeight.getText().toUpperCase() : "");
            viaje.setPlaceOut(placeOut.getText() != null ? placeOut.getText().toUpperCase() : "");
            viaje.setPlaceDestiny(placeDestiny.getText() != null ? placeDestiny.getText().toUpperCase() : "");
            viaje.setTransporter(transporter.getText() != null ? transporter.getText().toUpperCase() : "");
            viaje.setTransportMode(transportMode.getText() != null ? transportMode.getText().toUpperCase() : "");
            viaje.setVesselName(vesselName.getText() != null ? vesselName.getText().toUpperCase() : "");
            viaje.setCountryTransport(countryTransport.getText() != null ? countryTransport.getText().toUpperCase() : "");
            viaje.setNetWeight(netWeight.getText() != null ? netWeight.getText().toUpperCase() : "");
            viaje.setGrossWeight(totalWeight.getText() != null ? totalWeight.getText().toUpperCase() : "");
            viaje.setDocumentsQty(documentsQty.getText() != null ? documentsQty.getText().toUpperCase() : "");
            viaje.setPackagesQty(packagesQty.getText() != null ? packagesQty.getText().toUpperCase() : "");
            viaje.setTotalWeight(grossWeightTotal.getText() != null ? grossWeightTotal.getText().toUpperCase() : "");
            viaje.setContainerQty(containerQty.getText() != null ? containerQty.getText().toUpperCase() : "");
            viaje.setDateDischarge(dischargeDate.getText() != null ? dischargeDate.getText().toUpperCase() : "");
            viaje.setWareCode(wareCode.getText() != null ? wareCode.getText().toUpperCase() : "");
            viaje.setCustomOfficeCode(customOfficeCode.getText() != null ? customOfficeCode.getText().toUpperCase() : "");
            this.setViajeActual(travelNumber.getText().toUpperCase());
            DataBase db = new DataBase();
            db.conectar();
            if (db.existeViaje(viajeActual.toUpperCase())) {
                mensaje = db.modificarViaje(viaje);
            } else {
                mensaje = db.insertarViaje(viaje);
            }
        }
        JOptionPane.showMessageDialog(this, mensaje);
    }//GEN-LAST:event_btnModificarViajeActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        // TODO add your handling code here:
        if (travelNumber.getText().equals("")) {
            String mensaje = "Debe introducir un número de viaje";
            JOptionPane.showMessageDialog(this,
                    mensaje);
        } else {
            MainPanel.removeAll();
            MainPanel.add(panelBL);
            MainPanel.repaint();
            MainPanel.revalidate();
            DataBase db = new DataBase();
            db.conectar();
            if (seleccion == false) {
                this.setBlsViajeActual(db.obtenerBLSViaje(this.getViajeActual()));
            } else {
                this.setBlsViajeActual(db.obtenerBLSViajeSeleccionBLS(this.getViajeActual(), this.seleccionBLS));
            }
            this.setBlActual(0);
            this.limpiarVentana();
            DefaultTableModel tabla = (DefaultTableModel) tablaContenedores.getModel();
            tabla.setRowCount(0);
            if (this.getBlsViajeActual().size() > 0) {
                this.obtenerBL(this.blsViajeActual.get(0));
            }
            billOfLading.setEditable(false);
        }
    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void btnBuscarViajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarViajeActionPerformed
        this.setViajeActual(campoViajeBuscar.getText().toUpperCase());
        this.obtenerViaje();
        campoViajeBuscar.setText("");
        travelNumber.setEditable(false);
        seleccion = false;
    }//GEN-LAST:event_btnBuscarViajeActionPerformed

    private void campoViajeBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoViajeBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoViajeBuscarActionPerformed

    private void travelNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_travelNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_travelNumberActionPerformed

    private void btnSeleccionarBLSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarBLSActionPerformed
        if (travelNumber.getText().equals("")) {
            String mensaje = "Debe introducir un número de viaje";
            JOptionPane.showMessageDialog(this,
                    mensaje);
        } else {
            DataBase db = new DataBase();
            db.conectar();
            this.setBlsViajeActual(db.obtenerBLSViaje(this.getViajeActual()));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = GridBagConstraints.RELATIVE;
            gbc.anchor = GridBagConstraints.NORTHWEST;
            gbc.fill = GridBagConstraints.VERTICAL;
            gbc.weighty = 1;
            jPanel1.setLayout(new GridBagLayout());
            for (String item : this.blsViajeActual) {
                JCheckBox checkBox = new JCheckBox(item);
                jPanel1.add(checkBox, gbc);
            }
            MainPanel.removeAll();
            MainPanel.add(panelSeleccionarBLS);
            MainPanel.repaint();
            MainPanel.revalidate();
        }
    }//GEN-LAST:event_btnSeleccionarBLSActionPerformed

    private void btnGenerarXMLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarXMLActionPerformed

        if (this.seleccion == false) {
            GeneradorXML2 gXML2 = new GeneradorXML2();
            gXML2.generarXML2(this.viajeActual);
        } else {
            GeneradorXML gXML = new GeneradorXML();
            gXML.generarXML(this.viajeActual, this.seleccionBLS,
                    packagesQty.getText(), containerQty.getText(),
                    netWeight.getText(), grossWeightTotal.getText());
        }
        JOptionPane.showMessageDialog(this, "Archivo XML generado con éxito");
    }//GEN-LAST:event_btnGenerarXMLActionPerformed

    private void btnVolverViajeSBLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverViajeSBLActionPerformed
        this.seleccionBLS = new ArrayList<>();
        for (int i = 0; i < jPanel1.getComponentCount(); i++) {
            JCheckBox ch = (JCheckBox) jPanel1.getComponent(i);
            if (ch.isSelected()) {
                this.seleccionBLS.add(ch.getLabel());
            }
        }
        this.obtenerViajeSeleccionBLS(this.seleccionBLS);
        this.seleccion = true;
        jPanel1.removeAll();
        travelNumber.setEditable(false);
        MainPanel.removeAll();
        MainPanel.add(panelViaje);
        MainPanel.repaint();
        MainPanel.revalidate();

    }//GEN-LAST:event_btnVolverViajeSBLActionPerformed

    private void totalWeightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalWeightActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalWeightActionPerformed

    private void btnGenerarCSVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarCSVActionPerformed
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "¿Está seguro(a) desea importar el archivo: "
                    + file.getName(), "Confirmar importación", dialogButton);
            if (dialogResult == 0) {
                try {
                    ExcelReader er = new ExcelReader();
                    er.generarCSV(file.getName(), this.viajeActual);
                    er.procesarArchivoBLS(file.getName().substring(0, file.getName().length() - 4) + ".txt");
                    er.procesarArchivoContenedores(file.getName().substring(0, file.getName().length() - 4) + "Contenedores.txt");
                    JOptionPane.showMessageDialog(this, "Importación exitosa");
                } catch (ParseException ex) {
                    Logger.getLogger(extractor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnGenerarCSVActionPerformed

    private void btnEliminarViajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarViajeActionPerformed
        if (this.travelNumber.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Debe introducir un número de Viaje");
        } else {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "¿Está seguro(a) desea eliminar este Viaje: "
                    + this.travelNumber.getText(), "Confirmar", dialogButton);
            if (dialogResult == 0) {
                DataBase db = new DataBase();
                db.conectar();
                db.eliminarViaje(this.travelNumber.getText());
                this.limpiarViaje();
                travelNumber.setEditable(true);
                JOptionPane.showMessageDialog(this, "Viaje eliminado con éxito");
            }
        }
    }//GEN-LAST:event_btnEliminarViajeActionPerformed
    public void agregarCliente() {
        JTextField field1 = new JTextField();
        JTextField field2 = new JTextField();
        field1.setText("");
        field2.setText("");
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Nombre:"));
        panel.add(field1);
        panel.add(new JLabel("RIF:"));
        panel.add(field2);
        int result = JOptionPane.showConfirmDialog(null, panel, "Nuevo Cliente",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if (field1.getText().equals("") || field2.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            } else {
                DataBase db = new DataBase();
                db.conectar();
                db.insertarCliente(field1.getText(), field2.getText());
                this.clientes = db.getClientes();
                consignee.setModel(new DefaultComboBoxModel(this.clientes));
                notifyParty.setModel(new DefaultComboBoxModel(this.clientes));
                consignee.updateUI();
                notifyParty.updateUI();
                JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente");
            }
        }
    }
    private void consigneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consigneeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_consigneeActionPerformed

    private void containerQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_containerQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_containerQtyActionPerformed

    private void wareCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wareCodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wareCodeActionPerformed

    private void btnAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarClienteActionPerformed
        this.agregarCliente();
    }//GEN-LAST:event_btnAgregarClienteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(extractor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(extractor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(extractor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(extractor.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new extractor().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    private javax.swing.JTextField billOfLading;
    private javax.swing.JLabel billOfLadingLabel;
    private javax.swing.JButton btnAgregarCliente;
    private javax.swing.JButton btnAgregarContenedor;
    private javax.swing.JButton btnAnteriorBL;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarViaje;
    private javax.swing.JButton btnEliminarBL;
    private javax.swing.JButton btnEliminarContenedor;
    private javax.swing.JButton btnEliminarViaje;
    private javax.swing.JButton btnGenerarCAR;
    private javax.swing.JButton btnGenerarCSV;
    private javax.swing.JButton btnGenerarXML;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLimpiarViaje;
    private javax.swing.JButton btnModificarViaje;
    private javax.swing.JButton btnNuevoBL;
    private javax.swing.JButton btnSeleccionarBLS;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnSiguienteBL;
    private javax.swing.JButton btnVolverViaje;
    private javax.swing.JButton btnVolverViajeSBL;
    private javax.swing.JTextField campoBuscar;
    private javax.swing.JTextField campoViajeBuscar;
    private javax.swing.JTextField carbolNatCode;
    private javax.swing.JTextField carbolTypeCode;
    private javax.swing.JComboBox consignee;
    private javax.swing.JLabel consigneeLabel;
    private javax.swing.JTextField containerQty;
    private javax.swing.JTextField countryTransport;
    private javax.swing.JTextField customOfficeCode;
    private javax.swing.JTextField dateIn;
    private javax.swing.JTextField dateOut;
    private javax.swing.JTextArea descriptionOfGoods;
    private javax.swing.JLabel descriptionOfGoodsLabel;
    private javax.swing.JTextField dischargeDate;
    private javax.swing.JTextField documentsQty;
    private javax.swing.JTextField grossWeight;
    private javax.swing.JLabel grossWeightLabel;
    private javax.swing.JTextField grossWeightTotal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField keyLineNumber;
    private javax.swing.JLabel labelCarbolNatCode;
    private javax.swing.JLabel labelCarbolTypeCode;
    private javax.swing.JLabel labelContainerTable;
    private javax.swing.JLabel labelContainersQty;
    private javax.swing.JLabel labelCountryTransport;
    private javax.swing.JLabel labelCustomOfficeCode;
    private javax.swing.JLabel labelDateIn;
    private javax.swing.JLabel labelDateOut;
    private javax.swing.JLabel labelDischargeDate;
    private javax.swing.JLabel labelDocumentsQty;
    private javax.swing.JLabel labelGrossWeightTotal;
    private javax.swing.JLabel labelKeyLineNumber;
    private javax.swing.JLabel labelNetWeight;
    private javax.swing.JLabel labelNombre;
    private javax.swing.JLabel labelPackagesQty;
    private javax.swing.JLabel labelPlaceDestiny;
    private javax.swing.JLabel labelPlaceOut;
    private javax.swing.JLabel labelRIF;
    private javax.swing.JLabel labelSeleccionarBLS;
    private javax.swing.JLabel labelTotalWeight;
    private javax.swing.JLabel labelTransportMode;
    private javax.swing.JLabel labelTransporter;
    private javax.swing.JLabel labelTravelNumber;
    private javax.swing.JLabel labelVesselName;
    private javax.swing.JLabel labelWareCode;
    private javax.swing.JTextField netWeight;
    private javax.swing.JLabel nombreBLLabel;
    private javax.swing.JTextField nombreCliente;
    private javax.swing.JLabel nombreViajeLabel;
    private javax.swing.JComboBox notifyParty;
    private javax.swing.JLabel notifyPartyLabel;
    private javax.swing.JTextField packagesQty;
    private javax.swing.JTextField packagesTotal;
    private javax.swing.JLabel packagesTotalLabel;
    private javax.swing.JTextField packagesUnit;
    private javax.swing.JLabel packagesUnitLabel;
    private javax.swing.JPanel panelBL;
    private javax.swing.JPanel panelCliente;
    private javax.swing.JPanel panelSeleccionarBLS;
    private javax.swing.JPanel panelViaje;
    private javax.swing.JPanel panelViaje2;
    private javax.swing.JTextField placeDestiny;
    private javax.swing.JTextField placeOfDelivery;
    private javax.swing.JLabel placeOfDeliveryLabel;
    private javax.swing.JTextField placeOut;
    private javax.swing.JTextField portOfLoading;
    private javax.swing.JLabel portOfLoadingLabel;
    private javax.swing.JTextField rifCliente;
    private javax.swing.JTextField shipper;
    private javax.swing.JLabel shipperLabel;
    private javax.swing.JTable tablaContenedores;
    private javax.swing.JTextField totalContainers;
    private javax.swing.JLabel totalContainersLabel;
    private javax.swing.JTextField totalWeight;
    private javax.swing.JTextField transportMode;
    private javax.swing.JTextField transporter;
    private javax.swing.JTextField travelNumber;
    private javax.swing.JTextField vesselName;
    private javax.swing.JTextField wareCode;
    // End of variables declaration//GEN-END:variables
}
