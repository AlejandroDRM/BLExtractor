/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blextractor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MS2
 */
public class DataBase {

    protected static Connection con = null;
    private static String dsn = "jdbc:ucanaccess://";
    private static String ruta = "C:\\Users\\MS2\\Documents\\NetBeansProjects\\BLExtractor.accdb";
//    private static String ruta = System.getProperty("user.dir") + "\\BLExtractor.accdb";
//    private static String ruta = "\\\\192.168.5.12\\documentacion\\BLExtractor.accdb";
    private static String user = null;
    private static String password = null;
    private static String driver = "sun.jdbc.odbc.JdbcOdbcDriver";

    public DataBase() {
    }

    public static void conectar() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(dsn + ruta, user, password);
            //con.setAutoCommit(false);
        } catch (Exception ex) {
            System.err.println((new StringBuilder()).append(
                    "Error al conectar la base de datos:\n").append(ex.toString()).append(".DBManager").toString());
            ex.printStackTrace();
        }
    }

    public String insertarBL(BL bl) {
        String result = "BL agregado correctamente";
        String sql = "";
        try {
            Statement stmt = con.createStatement();
            sql = "insert into BL " + "(Number, PortOfLoading, PlaceOfDelivery, "
                    + "Shipper, Consignee, NotifyParty,TotalContainers,GrossWeight,DescriptionOfGoods,"
                    + "PackagesUnit,PackagesTotal,TravelNumber,KeyLineNumber,CarbolTypeCode,CarbolNatCode) "
                    + "values "
                    + " ('" + bl.getBillOfLading()
                    + "','" + bl.getPortOfLoading()
                    + "','" + bl.getPlaceOfDelivery()
                    + "','" + bl.getShipper()
                    + "','" + bl.getConsignee()
                    + "','" + bl.getNotifyParty()
                    + "','" + bl.getTotalContainers()
                    + "','" + bl.getGrossWeight().replace(",", ".")
                    + "','" + bl.getDescriptionOfGoods()
                    + "','" + bl.getPackagesUnit()
                    + "','" + bl.getPackagesTotal()
                    + "','" + bl.getTravel()
                    + "','" + bl.getKeyLineNumber()
                    + "','" + bl.getCarbolTypeCode()
                    + "','" + bl.getCarbolNatCode()
                    + "')";
            stmt.executeUpdate(sql);
//            try {
//                Thread.sleep(2000);                 //1000 milliseconds is one second.
//            } catch (InterruptedException ex) {
//                Thread.currentThread().interrupt();
//                System.out.println(ex);
//            }
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("BL: " + bl.getBillOfLading());
            //Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getErrorCode() == -104) {
                result = "Error: Ya existe este número de BL en la base de datos " + bl.getBillOfLading();
            }
        }
        return result;
    }

    public BL obtenerBL(String blID) {
        BL blRes = new BL();
        try {
            Statement st = con.createStatement();
            ResultSet rs;
            String sqlQuery = "SELECT * FROM BL WHERE Number = '" + blID + "'";
            rs = st.executeQuery(sqlQuery);
            if (rs.next()) {
                blRes.setBillOfLading(rs.getString("Number"));
                blRes.setConsignee(rs.getString("Consignee"));
                blRes.setDescriptionOfGoods(rs.getString("DescriptionOfGoods"));
                blRes.setGrossWeight(rs.getString("GrossWeight"));
                blRes.setNotifyParty(rs.getString("NotifyParty"));
                blRes.setPlaceOfDelivery(rs.getString("PlaceOfDelivery"));
                blRes.setPortOfLoading(rs.getString("PortOfLoading"));
                blRes.setShipper(rs.getString("Shipper"));
                blRes.setTotalContainers(rs.getString("TotalContainers"));
                blRes.setPackagesTotal(rs.getString("PackagesTotal"));
                blRes.setPackagesUnit(rs.getString("PackagesUnit"));
                blRes.setKeyLineNumber(rs.getString("KeyLineNumber"));
                blRes.setCarbolTypeCode(rs.getString("CarbolTypeCode"));
                blRes.setCarbolNatCode(rs.getString("CarbolNatCode"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blRes;
    }

    public boolean existeBL(String blID) {
        boolean existe = false;
        try {
            Statement st = con.createStatement();
            ResultSet rs;
            String sqlQuery = "SELECT Number FROM BL WHERE Number = '" + blID + "'";
            rs = st.executeQuery(sqlQuery);
            if (rs.next()) {
                existe = true;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }

    public String modificarBL(BL bl) {
        String result = "BL modificado correctamente";
        try {
            Statement stmt = con.createStatement();
            String sql = "UPDATE BL SET Number="
                    + " '" + bl.getBillOfLading().toUpperCase()
                    + "',PortOfLoading='" + bl.getPortOfLoading()
                    + "',PlaceOfDelivery='" + bl.getPlaceOfDelivery()
                    + "',Shipper='" + bl.getShipper()
                    + "',Consignee='" + bl.getConsignee()
                    + "',NotifyParty='" + bl.getNotifyParty()
                    + "',TotalContainers='" + bl.getTotalContainers()
                    + "',GrossWeight='" + bl.getGrossWeight()
                    + "',DescriptionOfGoods='" + bl.getDescriptionOfGoods()
                    + "',PackagesUnit='" + bl.getPackagesUnit()
                    + "',PackagesTotal='" + bl.getPackagesTotal()
                    + "',TravelNumber='" + bl.getTravel()
                    + "',KeyLineNumber='" + bl.getKeyLineNumber()
                    + "',CarbolTypeCode='" + bl.getCarbolTypeCode()
                    + "',CarbolNatCode='" + bl.getCarbolNatCode()
                    + "' WHERE Number= '" + bl.getBillOfLading() + "'";
            stmt.executeUpdate(sql);
//            try {
//                Thread.sleep(2000);                 //1000 milliseconds is one second.
//            } catch (InterruptedException ex) {
//                Thread.currentThread().interrupt();
//                System.out.println(ex);
//            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getErrorCode() == -104) {
                result = "Error: Ya existe este número de BL en la base de datos " + bl.getBillOfLading();
            }
        }
        return result;
    }

    public void insertarContenedores(List<Contenedor> contenedores, String blID) {
        String sqlAux = "";
        try {
            for (Contenedor contenedor : contenedores) {
                Statement stmt = con.createStatement();
                String sql = "insert into Containers " + "(Number, Seal, Seal2, Seal3, Type, "
                        + "BL) "
                        + "values "
                        + " ('" + contenedor.getNumber().toUpperCase()
                        + "','" + contenedor.getSeal()
                        + "','" + contenedor.getSeal2()
                        + "','" + contenedor.getSeal3()
                        + "','" + contenedor.getType()
                        + "','" + blID.toUpperCase()
                        + "')";
                sqlAux = contenedor.getNumber() + " en BL: " + blID.toUpperCase();
                stmt.executeUpdate(sql);
                stmt.close();
            }

        } catch (SQLException ex) {
            //Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Contenedor: " + sqlAux);
        }
    }

    public void eliminarContenedores(String blID) {
        try {
            Statement stmt = con.createStatement();
            String sql = "delete from Containers where BL = '" + blID + "'";
            stmt.executeUpdate(sql);
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Contenedor> obtenerContenedoresBL(String blID) {
        ArrayList<Contenedor> blContenedores = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs;
            String sqlQuery = "SELECT * FROM Containers WHERE BL = '" + blID.toUpperCase() + "'";
            rs = st.executeQuery(sqlQuery);
            while (rs.next()) {
                Contenedor contenedor = new Contenedor();
                contenedor.setNumber(rs.getString("Number").toUpperCase());
                contenedor.setSeal(rs.getString("Seal"));
                contenedor.setSeal2(rs.getString("Seal2"));
                contenedor.setSeal3(rs.getString("Seal3"));
                contenedor.setType(rs.getString("Type"));
                contenedor.setBL(rs.getString("BL"));
                blContenedores.add(contenedor);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blContenedores;
    }

    public String insertarViaje(Viaje viaje) {
        String result = "Viaje agregado correctamente";
        try {
            Statement stmt = con.createStatement();
            String sql = "insert into Viaje " + "(Number,DateOut, DateIn, "
                    + "PlaceOut,PlaceDestiny,Transporter,TransportMode,VesselName,"
                    + "CountryTransport,NetWeight,GrossWeight,DocumentsQty,PackagesQty,"
                    + "TotalWeight,ContainerQty,DateDischarge,WareCode,CustomOfficeCode) "
                    + "values "
                    + " ('" + viaje.getNumber().toUpperCase()
                    + "','" + viaje.getDateOut()
                    + "','" + viaje.getDateIn()
                    + "','" + viaje.getPlaceOut()
                    + "','" + viaje.getPlaceDestiny()
                    + "','" + viaje.getTransporter()
                    + "','" + viaje.getTransportMode()
                    + "','" + viaje.getVesselName()
                    + "','" + viaje.getCountryTransport()
                    + "','" + viaje.getNetWeight()
                    + "','" + viaje.getGrossWeight()
                    + "','" + viaje.getDocumentsQty()
                    + "','" + viaje.getPackagesQty()
                    + "','" + viaje.getTotalWeight()
                    + "','" + viaje.getContainerQty()
                    + "','" + viaje.getDateDischarge()
                    + "','" + viaje.getWareCode()
                    + "','" + viaje.getCustomOfficeCode()
                    + "')";
            stmt.executeUpdate(sql);
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getErrorCode() == -104) {
                result = "Error: Ya existe este número de BL en la base de datos " + viaje.getNumber();
            }
        }
        return result;
    }

    public Viaje obtenerViaje(String viajeID) {
        Viaje viaje = new Viaje();
        try {
            Statement st = con.createStatement();
            ResultSet rs;
            String sqlQuery = "SELECT * FROM Viaje WHERE Number = '" + viajeID.toUpperCase() + "'";
            rs = st.executeQuery(sqlQuery);
            if (rs.next()) {
                viaje.setNumber(rs.getString("Number").toUpperCase());
                viaje.setDateOut(rs.getString("DateOut"));
                viaje.setDateIn(rs.getString("DateIn"));
                //viaje.setHourIn(rs.getString("HourIn"));
                viaje.setPlaceOut(rs.getString("PlaceOut"));
                viaje.setPlaceDestiny(rs.getString("PlaceDestiny"));
                viaje.setTransporter(rs.getString("Transporter"));
                viaje.setTransportMode(rs.getString("TransportMode"));
                viaje.setVesselName(rs.getString("VesselName"));
                viaje.setCountryTransport(rs.getString("CountryTransport"));
                viaje.setNetWeight(rs.getString("NetWeight"));
                viaje.setGrossWeight(rs.getString("GrossWeight"));
                viaje.setDocumentsQty(rs.getString("DocumentsQty"));
                viaje.setPackagesQty(rs.getString("PackagesQty"));
                viaje.setTotalWeight(rs.getString("TotalWeight"));
                viaje.setContainerQty(rs.getString("ContainerQty"));
                viaje.setDateDischarge(rs.getString("DateDischarge"));
                viaje.setWareCode(rs.getString("WareCode"));
                viaje.setCustomOfficeCode(rs.getString("CustomOfficeCode"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return viaje;
    }

    public boolean existeViaje(String viajeID) {
        boolean existe = false;
        try {
            Statement st = con.createStatement();
            ResultSet rs;
            String sqlQuery = "SELECT Number FROM Viaje WHERE Number = '" + viajeID.toUpperCase() + "'";
            rs = st.executeQuery(sqlQuery);
            if (rs.next()) {
                existe = true;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }

    public String modificarViaje(Viaje viaje) {
        String result = "Viaje modificado correctamente";
        try {
            Statement stmt = con.createStatement();
            String sql = "UPDATE Viaje SET Number="
                    + " '" + viaje.getNumber().toUpperCase()
                    + "',DateOut='" + viaje.getDateOut()
                    + "',DateIn='" + viaje.getDateIn()
                    //+ "',HourIn='" + viaje.getHourIn()
                    + "',PlaceOut='" + viaje.getPlaceOut()
                    + "',PlaceDestiny='" + viaje.getPlaceDestiny()
                    + "',Transporter='" + viaje.getTransporter()
                    + "',TransportMode='" + viaje.getTransportMode()
                    + "',VesselName='" + viaje.getVesselName()
                    + "',CountryTransport='" + viaje.getCountryTransport()
                    + "',NetWeight='" + viaje.getNetWeight()
                    + "',GrossWeight='" + viaje.getGrossWeight()
                    + "',DocumentsQty='" + viaje.getDocumentsQty()
                    + "',PackagesQty='" + viaje.getPackagesQty()
                    + "',TotalWeight='" + viaje.getTotalWeight()
                    + "',ContainerQty='" + viaje.getContainerQty()
                    + "',DateDischarge='" + viaje.getDateDischarge()
                    + "',WareCode='" + viaje.getWareCode()
                    + "',CustomOfficeCode='" + viaje.getCustomOfficeCode()
                    + "' WHERE Number= '" + viaje.getNumber() + "'";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
            if (ex.getErrorCode() == -104) {
                result = "Error: Ya existe este número de Viaje en la base de datos: " + viaje.getNumber();
            }
        }
        return result;
    }

    public ArrayList<String> obtenerBLSViaje(String viajeID) {
        ArrayList<String> blsViaje = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs;
            String sqlQuery = "SELECT Number FROM BL WHERE TravelNumber = '" + viajeID + "'";
            rs = st.executeQuery(sqlQuery);
            while (rs.next()) {
                blsViaje.add(rs.getString("Number"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blsViaje;
    }

    public ArrayList<String> obtenerBLSViajeSeleccionBLS(String viajeID, ArrayList<String> bls) {
        ArrayList<String> blsViaje = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs;
            String sqlQuery = "SELECT Number FROM BL WHERE TravelNumber = '" + viajeID + "'";
            rs = st.executeQuery(sqlQuery);
            while (rs.next()) {
                if (bls.contains(rs.getString("Number"))) {
                    blsViaje.add(rs.getString("Number"));
                }
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blsViaje;
    }

    public ArrayList<String> obtenerBLSGenerarXML(String viajeID) {
        ArrayList<String> blsViaje = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs;
            String sqlQuery = "SELECT Number, KeyLineNumber FROM BL WHERE TravelNumber = '" + viajeID + "'";
            rs = st.executeQuery(sqlQuery);
            while (rs.next()) {
                blsViaje.add(rs.getString("Number") + "-" + rs.getString("KeyLineNumber"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return blsViaje;
    }

    public Viaje obtenerViajeCompleto(String viajeID) {
        Viaje viaje = new Viaje();
        try {
            Statement st = con.createStatement();
            ResultSet rs;
            String sqlQuery = "SELECT * FROM Viaje WHERE Number = '" + viajeID.toUpperCase() + "'";
            rs = st.executeQuery(sqlQuery);
            if (rs.next()) {
                viaje.setNumber(rs.getString("Number").toUpperCase());
                viaje.setDateOut(rs.getString("DateOut"));
                viaje.setDateIn(rs.getString("DateIn"));
                //viaje.setHourIn(rs.getString("HourIn"));
                viaje.setPlaceOut(rs.getString("PlaceOut"));
                viaje.setPlaceDestiny(rs.getString("PlaceDestiny"));
                viaje.setTransporter(rs.getString("Transporter"));
                viaje.setTransportMode(rs.getString("TransportMode"));
                viaje.setVesselName(rs.getString("VesselName"));
                viaje.setCountryTransport(rs.getString("CountryTransport"));
                viaje.setNetWeight(rs.getString("NetWeight"));
                viaje.setGrossWeight(rs.getString("GrossWeight"));
                viaje.setDocumentsQty(rs.getString("DocumentsQty"));
                viaje.setPackagesQty(rs.getString("PackagesQty"));
                viaje.setTotalWeight(rs.getString("TotalWeight"));
                viaje.setContainerQty(rs.getString("ContainerQty"));
                viaje.setDateDischarge(rs.getString("DateDischarge"));
                viaje.setWareCode(rs.getString("WareCode"));
                viaje.setCustomOfficeCode(rs.getString("CustomOfficeCode"));
                ArrayList<String> idsBLSViaje = this.obtenerBLSViaje(viajeID);
                for (String idBLViaje : idsBLSViaje) {
                    viaje.getViajeBLS().add(this.obtenerBL(idBLViaje));
                    viaje.getViajeContenedores().add(this.obtenerContenedoresBL(idBLViaje));
                }
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return viaje;
    }

    public Viaje obtenerViajeCompleto2(String viajeID) {
        Viaje viaje = new Viaje();
        try {
            Statement st = con.createStatement();
            ResultSet rs;
            String sqlQuery = "SELECT * FROM Viaje WHERE Number = '" + viajeID.toUpperCase() + "'";
            rs = st.executeQuery(sqlQuery);
            if (rs.next()) {
                viaje.setNumber(rs.getString("Number").toUpperCase());
                viaje.setDateOut(rs.getString("DateOut"));
                viaje.setDateIn(rs.getString("DateIn"));
                //viaje.setHourIn(rs.getString("HourIn"));
                viaje.setPlaceOut(rs.getString("PlaceOut"));
                viaje.setPlaceDestiny(rs.getString("PlaceDestiny"));
                viaje.setTransporter(rs.getString("Transporter"));
                viaje.setTransportMode(rs.getString("TransportMode"));
                viaje.setVesselName(rs.getString("VesselName"));
                viaje.setCountryTransport(rs.getString("CountryTransport"));
                viaje.setNetWeight(rs.getString("NetWeight"));
                viaje.setGrossWeight(rs.getString("GrossWeight"));
                viaje.setDocumentsQty(rs.getString("DocumentsQty"));
                viaje.setPackagesQty(rs.getString("PackagesQty"));
                viaje.setTotalWeight(rs.getString("TotalWeight"));
                viaje.setContainerQty(rs.getString("ContainerQty"));
                viaje.setDateDischarge(rs.getString("DateDischarge"));
                viaje.setWareCode(rs.getString("WareCode"));
                viaje.setCustomOfficeCode(rs.getString("CustomOfficeCode"));
                ArrayList<String> idsBLSViaje = this.obtenerBLSViaje(viajeID);
                for (String idBLViaje : idsBLSViaje) {
                    viaje.getViajeBLS().add(this.obtenerBL(idBLViaje));
//                    viaje.getViajeContenedores().add(this.obtenerContenedoresBL(idBLViaje));
                }
                viaje.setViajeBLS(this.ordenarBLS(viaje.getViajeBLS()));
                for (BL idBLViaje : viaje.getViajeBLS()) {
                    viaje.getViajeContenedores().add(this.obtenerContenedoresBL(idBLViaje.getBillOfLading()));
                }
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return viaje;
    }

    public Viaje obtenerViajeCompletoSeleccionBLS(String viajeID, ArrayList<String> bls) {
        Viaje viaje = new Viaje();
        try {
            Statement st = con.createStatement();
            ResultSet rs;
            String sqlQuery = "SELECT * FROM Viaje WHERE Number = '" + viajeID.toUpperCase() + "'";
            rs = st.executeQuery(sqlQuery);
            if (rs.next()) {
                viaje.setNumber(rs.getString("Number").toUpperCase());
                viaje.setDateOut(rs.getString("DateOut"));
                viaje.setDateIn(rs.getString("DateIn"));
                //viaje.setHourIn(rs.getString("HourIn"));
                viaje.setPlaceOut(rs.getString("PlaceOut"));
                viaje.setPlaceDestiny(rs.getString("PlaceDestiny"));
                viaje.setTransporter(rs.getString("Transporter"));
                viaje.setTransportMode(rs.getString("TransportMode"));
                viaje.setVesselName(rs.getString("VesselName"));
                viaje.setCountryTransport(rs.getString("CountryTransport"));
                viaje.setNetWeight(rs.getString("NetWeight"));
                viaje.setGrossWeight(rs.getString("GrossWeight"));
                viaje.setDocumentsQty(rs.getString("DocumentsQty"));
                viaje.setPackagesQty(rs.getString("PackagesQty"));
                viaje.setTotalWeight(rs.getString("TotalWeight"));
                viaje.setContainerQty(rs.getString("ContainerQty"));
                viaje.setDateDischarge(rs.getString("DateDischarge"));
                viaje.setWareCode(rs.getString("WareCode"));
                viaje.setCustomOfficeCode(rs.getString("CustomOfficeCode"));
                ArrayList<String> idsBLSViaje = this.obtenerBLSViaje(viajeID);
                for (String idBLViaje : idsBLSViaje) {
                    if (bls.contains(idBLViaje)) {
                        viaje.getViajeBLS().add(this.obtenerBL(idBLViaje));
//                        viaje.getViajeContenedores().add(this.obtenerContenedoresBL(idBLViaje));
                    }
                }
                viaje.setViajeBLS(this.ordenarBLS(viaje.getViajeBLS()));
                for (BL idBLViaje : viaje.getViajeBLS()) {
                    viaje.getViajeContenedores().add(this.obtenerContenedoresBL(idBLViaje.getBillOfLading()));
                }
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return viaje;
    }

    public void eliminarBL(String blID) {
        try {
            Statement stmt = con.createStatement();
            String sql = "delete from BL where Number = '" + blID + "'";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminarViaje(String viajeID) {
        try {
            Statement stmt = con.createStatement();
            String sql = "delete from Viaje where Number = '" + viajeID + "'";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean existeKeyLineInBL(String keyLineNumber, String viajeID) {
        boolean existe = false;
        try {
            Statement st = con.createStatement();
            ResultSet rs;
            String sqlQuery = "SELECT Number FROM BL WHERE TravelNumber = '"
                    + viajeID + "' AND KeyLineNumber = '" + keyLineNumber + "'";
            rs = st.executeQuery(sqlQuery);
            if (rs.next()) {
                existe = true;
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }

    public String[] getClientes() {
        ArrayList<String> clientesLista = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs;
            String sqlQuery = "SELECT * FROM Clientes order by NOMBRE";
            rs = st.executeQuery(sqlQuery);
            while (rs.next()) {
                clientesLista.add(rs.getString("NOMBRE") + "/" + rs.getString("RIF"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientesLista.toArray(new String[clientesLista.size()]);
    }

    public void insertarCliente(String nombre, String rif) {
        String sql = "";
        try {
            Statement stmt = con.createStatement();
            sql = "insert into Clientes " + "(NOMBRE,RIF) "
                    + "values "
                    + " ('" + nombre.toUpperCase()
                    + "','" + rif.toUpperCase()
                    + "')";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private ArrayList<BL> ordenarBLS(ArrayList<BL> listaBLS) {
        ArrayList<BL> aux = new ArrayList<>();
        int i = 1;
        while (i <= listaBLS.size()) {
            for (BL listaBLS1 : listaBLS) {
                if (listaBLS1.getKeyLineNumber().equals(String.valueOf(i))) {
                    aux.add(listaBLS1);
                }
            }
            i++;
        }
        return aux;
    }
}
