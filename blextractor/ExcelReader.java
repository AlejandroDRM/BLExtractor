/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blextractor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellReference;

/**
 *
 * @author MS2
 */
public class ExcelReader {

    public ExcelReader() {
    }

    public String cambiarFormatoFecha(String fecha) {
        String newDateString = "";
        try {
            String OLD_FORMAT = "EEE MMM d HH:mm:ss zzz yyyy";
            String NEW_FORMAT = "dd/MM/yyyy";
            String oldDateString = fecha;
            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT, Locale.ENGLISH);
            Date d = sdf.parse(oldDateString);
            sdf.applyPattern(NEW_FORMAT);
            newDateString = sdf.format(d);
        } catch (ParseException ex) {
            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        String res = newDateString.substring(6, 10) + newDateString.substring(3, 5)
                + newDateString.substring(0, 2);
        return res;
    }

    public void procesarArchivoBLS(String nombreArchivo) {
        FileReader fr = null;
        String linea = "";
        try {
            File archivo = new File(nombreArchivo);
            fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            linea = br.readLine();
            DataBase db = new DataBase();
            db.conectar();
            System.out.println("Los siguientes elementos no fueron insertados por error de duplicidad:");
            while ((linea = br.readLine()) != null) {
                String[] blPartes = linea.split(";");
                BL bl = new BL();
                bl.setBillOfLading(blPartes[0]);
                bl.setPortOfLoading(blPartes[1]);
                bl.setPlaceOfDelivery(blPartes[2]);
                bl.setShipper(blPartes[3]);
                bl.setConsignee(blPartes[4]);
                bl.setNotifyParty(blPartes[5]);
                bl.setTotalContainers(blPartes[6]);
                bl.setGrossWeight(blPartes[7]);
                bl.setDescriptionOfGoods(blPartes[8]);
                bl.setPackagesUnit(blPartes[9]);
                bl.setPackagesTotal(blPartes[10]);
                bl.setTravel(blPartes[11]);
                bl.setKeyLineNumber("");
                bl.setCarbolTypeCode(blPartes[13]);
                bl.setCarbolNatCode(blPartes[14]);
//                if(bl.getBillOfLading().equals("6086509720")){
//                    System.out.println("pausa");
//                }
                db.insertarBL(bl);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void procesarArchivoContenedores(String nombreArchivo) {
        FileReader fr = null;
        String linea = "";
        List<Contenedor> listaContenedores = new ArrayList();
        try {
            File archivo = new File(nombreArchivo);
            fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            linea = br.readLine();
            DataBase db = new DataBase();
            db.conectar();
            while ((linea = br.readLine()) != null) {
                String[] contenedoresPartes = linea.split(";");
                Contenedor contenedor = new Contenedor();
                contenedor.setNumber(contenedoresPartes[0]);
                contenedor.setSeal(contenedoresPartes[1]);
                contenedor.setSeal2("NA");
                contenedor.setSeal3("NA");
                String tipo = contenedoresPartes[4];
                switch (tipo) {
                    case "20GP":
                        contenedor.setType("2000");
                        break;
                    case "40GP":
                        contenedor.setType("4000");
                        break;
                    case "40HQ":
                        contenedor.setType("4400");
                        break;
                    case "20OT":
                        contenedor.setType("2050");
                        break;
                    case "40OT":
                        contenedor.setType("4050");
                        break;
                    case "40FR":
                        contenedor.setType("4060");
                        break;
                    case "20TK":
                        contenedor.setType("2270");
                        break;
                }
                //contenedor.setType(contenedoresPartes[4]);
                contenedor.setBL(contenedoresPartes[5]);
                listaContenedores.add(contenedor);
                db.insertarContenedores(listaContenedores, contenedor.getBL());
                listaContenedores.clear();
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fr.close();
            } catch (IOException ex) {
                Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void generarCSV(String nombreArchivo, String viajeActual) throws ParseException {
        FileWriter fw;
        FileWriter fw2;
        FileWriter fw3;
        int modificador = 0;
        try {
            fw = new FileWriter(new File(nombreArchivo.substring(0, nombreArchivo.length() - 4) + ".txt"));
            FileInputStream file = new FileInputStream(new File(nombreArchivo));
            fw2 = new FileWriter(new File(nombreArchivo.substring(0, nombreArchivo.length() - 4) + "Contenedores" + ".txt"));
            fw3 = new FileWriter(new File(nombreArchivo.substring(0, nombreArchivo.length() - 4) + "Viajes" + ".txt"));

            //Get the workbook instance for XLS file 
            HSSFWorkbook workbook = new HSSFWorkbook(file);

            //Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);
            //Primera parte service            
//            CellReference cr = new CellReference("H5");
//            HSSFRow row = sheet.getRow(cr.getRow());
//            Cell cell = row.getCell(cr.getCol());
//            String service1 = cell.getStringCellValue();
//            //Segunda parte service
//            cr = new CellReference("I5");
//            row = sheet.getRow(cr.getRow());
//            cell = row.getCell(cr.getCol());
//            String service2 = cell.getStringCellValue();
//            fw.write("SERVICE: " + service1 + " " + service2);
            //Buque 1
            CellReference cr = new CellReference("H6");
            HSSFRow row = sheet.getRow(cr.getRow());
            Cell cell = row.getCell(cr.getCol());
            String buque1 = cell.getStringCellValue();
            //Buque 2
            cr = new CellReference("I6");
            row = sheet.getRow(cr.getRow());
            cell = row.getCell(cr.getCol());
            String buque2 = cell.getStringCellValue();
            if (buque2.equals("")) {
                //Buque 2
                cr = new CellReference("J6");
                row = sheet.getRow(cr.getRow());
                cell = row.getCell(cr.getCol());
                buque2 = cell.getStringCellValue();
                modificador = 1;
            }
            String buque = buque1 + " " + buque2;
            //fw.write("\nBUQUE: " + buque1 + " " + buque2);
            //Voyage
//            cr = new CellReference("H7");
//            row = sheet.getRow(cr.getRow());
//            cell = row.getCell(cr.getCol());
            String voyage = viajeActual;//cell.getStringCellValue();

            //Arrival Date
//            cr = new CellReference("H8");
//            row = sheet.getRow(cr.getRow());
//            cell = row.getCell(cr.getCol());
//            String arrivalDate = cambiarFormatoFecha(cell.getDateCellValue().toString());
//            fw.write("\nARRIVAL DATE: " + arrivalDate);
//            //PLACE OF REPCEIPT
//            cr = new CellReference("H10");
//            row = sheet.getRow(cr.getRow());
//            cell = row.getCell(cr.getCol());
//            String placeOfReceipt = cell.getStringCellValue();
//            fw.write("\nPLACE OF REPCEIPT: " + placeOfReceipt);
            //PORT OF LOAD
            cr = new CellReference("H11");
            row = sheet.getRow(cr.getRow());
            cell = row.getCell(cr.getCol());
            String portOfLoad = cell.getStringCellValue();
            //fw.write("\nPORT OF LOAD: " + portOfLoad);
//            //PORT OF DISCHARGE
//            cr = new CellReference("H12");
//            row = sheet.getRow(cr.getRow());
//            cell = row.getCell(cr.getCol());
//            String portOfDischarge = cell.getStringCellValue();
//            fw.write("\nPORT OF DISCHARGE: " + portOfDischarge);
            //PLACE OF DELIVERY
            cr = new CellReference("H13");
            row = sheet.getRow(cr.getRow());
            cell = row.getCell(cr.getCol());
            String placeOfDelivery = cell.getStringCellValue();
            //fw.write("\nPLACE OF DELIVERY: " + placeOfDelivery);
            boolean existeBL = true;
            boolean ignorar = false;
            int i = 20;
            String letra = "";
            String contenido;
            String bl = "";
            String shipper = "";
            String consignee = "";
            String notifyParty = "";
//            String placeOfDelivery = "";
//            String portOfLoad = "";
            String grossWeight = "";
            String packagesUnit = "";
            String description = "";
            int numeroPaquetes = 0;
            int numeroContenedores = 0;
            fw.write("Number;PortOfLoading;PlaceOfDelivery;Shipper;Consignee;"
                    + "NotifyParty;TotalContainers;GrossWeight;DescriptionOfGoods;PackagesUnit;"
                    + "PackagesTotal;TravelNumber;KeyLineNumber;CarbolTypeCode;CarbolNatCode");
            fw.write("\n");
            fw2.write("Number;Seal;Seal2;Seal3;Type;BL\n");
            fw3.write("Number;DateOut;PlaceOut;PlaceDestiny;Transporter;TransportMode;VesselName;"
                    + "CountryTransport;NetWeight;GrossWeight;DocumentsQty;PackagesQty;TotalWeight"
                    + ";ContainerQty;DateDischarge;WareCode;CustomOfficeCode\n");
            fw3.write(voyage + ";;;;;1;;"
                    + ";0;0;0;0;0"
                    + ";0;;;\n");
            while (existeBL) {
                cr = new CellReference("A" + i);
                row = sheet.getRow(cr.getRow());
                if (row != null) {
                    cell = row.getCell(cr.getCol());
                    if (cell != null) {
                        cell.setCellType(1);
                        contenido = cell.getStringCellValue();
                        switch (contenido.trim()) {
                            case "B/LNUMBER:":
                                ignorar = false;
                                cr = new CellReference("C" + i);
                                row = sheet.getRow(cr.getRow());
                                cell = row.getCell(cr.getCol());
                                cell.setCellType(1);
                                bl = String.valueOf(cell.getStringCellValue());
//                            fw.write("\nB/LNUMBER: " + bl);
                                break;
                            case "SHIPPER:":
                                cr = new CellReference("A" + (i + 1));
                                row = sheet.getRow(cr.getRow());
                                cell = row.getCell(cr.getCol());
                                cell.setCellType(1);
                                shipper = cell.getStringCellValue();
                                shipper = shipper.replace("&", " AND ");
                                shipper = shipper.replace(";", ",");
                                shipper = shipper.replace("\'", "");
                                //Gross Weight
                                letra = Character.toString((char) (81 + modificador));
                                cr = new CellReference(letra + i);
                                row = sheet.getRow(cr.getRow());
                                cell = row.getCell(cr.getCol());
                                cell.setCellType(1);
                                grossWeight = cell.getStringCellValue();
                                //Description
                                boolean sigueDescripcion = true;
                                int iAux = i;
                                letra = Character.toString((char) (77 + modificador));
                                cr = new CellReference(letra + i);
                                row = sheet.getRow(cr.getRow());
                                cell = row.getCell(cr.getCol());
                                cell.setCellType(1);
                                description = cell.getStringCellValue();
                                while (sigueDescripcion) {
                                    iAux++;
                                    letra = Character.toString((char) (77 + modificador));
                                    cr = new CellReference(letra + iAux);
                                    row = sheet.getRow(cr.getRow());
                                    cell = row.getCell(cr.getCol());
                                    cell.setCellType(1);
                                    if (cell.getStringCellValue().length() > 0) {
                                        description = description + " " + cell.getStringCellValue();
                                    } else {
                                        sigueDescripcion = false;
                                    }
                                }
                                description = description.replace("&", " AND ");
                                description = description.replace(";", ",");
//                            fw.write("\nSHIPPER: " + shipper);
                                break;
                            case "CONSIGNEE:":
                                cr = new CellReference("A" + (i + 1));
                                row = sheet.getRow(cr.getRow());
                                cell = row.getCell(cr.getCol());
                                cell.setCellType(1);
                                consignee = cell.getStringCellValue();
                                consignee = consignee.replace("&", " AND ");
                                consignee = consignee.replace(";", ",");
                                consignee = consignee.replace("\'", "");

//                            fw.write("\nCONSIGNEE: " + consignee);
                                break;
                            case "NOTIFY PARTY:":
                                cr = new CellReference("A" + (i + 1));
                                row = sheet.getRow(cr.getRow());
                                cell = row.getCell(cr.getCol());
                                cell.setCellType(1);
                                notifyParty = cell.getStringCellValue();
                                notifyParty = notifyParty.replace("&", " AND ");
                                notifyParty = notifyParty.replace(";", ",");
                                notifyParty = notifyParty.replace("\'", "");
                                if (notifyParty.equals("SAME AS CONSIGNEE")) {
                                    notifyParty = consignee;
                                }
//                            fw.write("\nNOTIFY PARTY: " + notifyParty);
                                break;
//                            case "PLACE OF DELIVERY":
//                                if (ignorar == false) {
//                                    cr = new CellReference("H" + i);
//                                    row = sheet.getRow(cr.getRow());
//                                    cell = row.getCell(cr.getCol());
//                                    cell.setCellType(1);
//                                    placeOfDelivery = cell.getStringCellValue();
//                                    if (placeOfDelivery.equals("")) {
//                                        cr = new CellReference("D" + i);
//                                        row = sheet.getRow(cr.getRow());
//                                        cell = row.getCell(cr.getCol());
//                                        cell.setCellType(1);
//                                        placeOfDelivery = cell.getStringCellValue();
//                                    }
////                            fw.write("\nPLACE OF DELIVERY: " + placeOfDelivery);
//                                    //i = i + 6 + (modificador * 11);
//                                    i = i + 6;
//                                    cr = new CellReference("A" + i);
//                                    row = sheet.getRow(cr.getRow());
//                                    cell = row.getCell(cr.getCol());
//                                    if (cell==null) {
//                                        i = i + 26;
//                                    }
//                                    description = description.replace("\'", "");
//
//                                    fw.write(bl + ";" + portOfLoad + ";" + placeOfDelivery + ";" + shipper + ";"
//                                            + consignee + ";" + notifyParty + ";"
//                                            + numeroContenedores + ";" + grossWeight + ";" + description.replace("\"", "") + ";"
//                                            + packagesUnit + ";" + numeroPaquetes + ";"
//                                            + voyage + ";;B/L;23");
//                                    fw.write("\n");
//                                    numeroPaquetes = 0;
//                                    numeroContenedores = 0;
//                                    ignorar = true;
//                                }
//                                break;
//                            case "PORT OF LOAD":
//                                if (ignorar == false) {
//                                    cr = new CellReference("H" + i);
//                                    row = sheet.getRow(cr.getRow());
//                                    cell = row.getCell(cr.getCol());
//                                    cell.setCellType(1);
//                                    portOfLoad = cell.getStringCellValue();
//                                    if (portOfLoad.equals("")) {
//                                        cr = new CellReference("D" + i);
//                                        row = sheet.getRow(cr.getRow());
//                                        cell = row.getCell(cr.getCol());
//                                        cell.setCellType(1);
//                                        portOfLoad = cell.getStringCellValue();
//                                    }
//                                }
////                            fw.write("\nPORT OF LOAD: " + portOfLoad);
//                                break;
                            case "CONTAINER NUM":
                                boolean existeContenedor = true;
                                //Unidad
                                letra = Character.toString((char) (82 + modificador));
                                CellReference crP = new CellReference(letra + (i + 1));
                                HSSFRow rowP = sheet.getRow(crP.getRow());
                                Cell cellP = rowP.getCell(crP.getCol());
                                packagesUnit = cellP.getStringCellValue();
                                while (existeContenedor) {
                                    i++;
                                    //Paquetes qty
                                    cr = new CellReference("A" + i);
                                    row = sheet.getRow(cr.getRow());
                                    cell = row.getCell(cr.getCol());
                                    cell.setCellType(1);
                                    if (cell.getStringCellValue().length() == 11) {

                                        letra = Character.toString((char) (78 + modificador));
                                        cr = new CellReference(letra + i);
                                        row = sheet.getRow(cr.getRow());
                                        cell = row.getCell(cr.getCol());
                                        cell.setCellType(1);
                                        String aux = cell.getStringCellValue();
                                        int suma = 0;
                                        if (aux.charAt(aux.length() - 1) == ' ') {
                                            String aux2 = aux.substring(0, aux.length() - 2);
                                            suma = Integer.parseInt(aux2);
                                        } else {
                                            suma = Integer.parseInt(aux);
                                        }

                                        numeroPaquetes = numeroPaquetes + suma;
                                        //Contenedores
                                        cr = new CellReference("A" + i);
                                        row = sheet.getRow(cr.getRow());
                                        cell = row.getCell(cr.getCol());
                                        cell.setCellType(1);
                                        String codigoContenedor = cell.getStringCellValue();
                                        cr = new CellReference("G" + i);
                                        row = sheet.getRow(cr.getRow());
                                        cell = row.getCell(cr.getCol());
                                        cell.setCellType(1);
                                        String sealContenedor = cell.getStringCellValue();
                                        letra = Character.toString((char) (75 + modificador));
                                        cr = new CellReference(letra + i);
                                        row = sheet.getRow(cr.getRow());
                                        cell = row.getCell(cr.getCol());
                                        cell.setCellType(1);
                                        String tipoContenedor = cell.getStringCellValue();
                                        fw2.write(codigoContenedor + ";" + sealContenedor.trim() + ";;;" + tipoContenedor + ";" + bl + "\n");
                                        numeroContenedores++;
                                    } else {
                                        existeContenedor = false;
                                    }
                                }
                                description = description.replace("\'", "");

                                fw.write(bl + ";" + portOfLoad + ";" + placeOfDelivery + ";" + shipper + ";"
                                        + consignee + ";" + notifyParty + ";"
                                        + numeroContenedores + ";" + grossWeight + ";" + description.replace("\"", "") + ";"
                                        + packagesUnit + ";" + numeroPaquetes + ";"
                                        + voyage + ";;B/L;23");
                                fw.write("\n");
                                numeroPaquetes = 0;
                                numeroContenedores = 0;
                                break;
                            case "*******   END OF REPORT   *******":
                                existeBL = false;
                                break;
                        }
                    }
                }
                i++;
            }
            fw.close();
            fw2.close();
            fw3.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
