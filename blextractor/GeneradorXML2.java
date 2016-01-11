/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blextractor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author MS2
 */
public class GeneradorXML2 {

    final String ruta = "C:\\Users\\MS2\\Documents\\NetBeansProjects\\";

    public GeneradorXML2() {
    }

    public void generarXML2(String viajeID) {
        DataBase db = new DataBase();
        db.conectar();
        Viaje viaje = db.obtenerViajeCompleto2(viajeID);
        String numeroContenedores = this.calcularNumeroContenedores2(viaje);
        FileWriter fw;
        try {
            fw = new FileWriter(new File(this.ruta + "XML\\" + viajeID + ".xml"));
            fw.write("<?xml version=\"1.0\" standalone=\"yes\" ?>");
            fw.write("\n<MdsParts>");

            fw.write("\n\t<MDS1>");
            fw.write("\n\t\t<NumberOfManifests>1</NumberOfManifests>");
            fw.write("\n\t</MDS1>");

//            fw.write("\n\t<MDS2>");
//            fw.write("\n\t\t<lengthOfMDM>0</lengthOfMDM>");
//            fw.write("\n\t</MDS2>");
            fw.write("\n\t<MDS3>");
            fw.write("\n\t\t<i1>1</i1>");
            fw.write("\n\t\t<i2>" + viaje.getViajeBLS().size() + "</i2>");
            fw.write("\n\t\t<i3>0</i3>");
            fw.write("\n\t</MDS3>");

            fw.write("\n\t<MDS4>");
            fw.write("\n\t\t<KEY_CUO>" + viaje.getCustomOfficeCode() + "</KEY_CUO>");
            fw.write("\n\t\t<KEY_VOY_NBER>" + viaje.getNumber() + "</KEY_VOY_NBER>");
            fw.write("\n\t\t<KEY_DEP_DATE>" + this.procesarFecha(viaje.getDateOut()) + "</KEY_DEP_DATE>");
            fw.write("\n\t</MDS4>");

            fw.write("\n\t<MDS5>");
            fw.write("\n\t\t<CAR_ARR_DATE>" + this.procesarFecha(viaje.getDateIn()) + "</CAR_ARR_DATE>");
            fw.write("\n\t\t<CAR_DEP_COD>" + viaje.getPlaceOut() + "</CAR_DEP_COD>");
            fw.write("\n\t\t<CAR_DEST_COD>" + viaje.getPlaceDestiny() + "</CAR_DEST_COD>");
            fw.write("\n\t\t<CAR_CAR_COD>" + viaje.getTransporter() + "</CAR_CAR_COD>");

            fw.write("\n\t\t<CAR_CAR_NAM>GLOBAL SHIPPING AGENTES NAVIEROS</CAR_CAR_NAM>");
            fw.write("\n\t\t<CAR_CAR_ADR1>CALLE LOS BA¥OS CC CARIBE VARGAS</CAR_CAR_ADR1>");
            fw.write("\n\t\t<CAR_CAR_ADR2>NIVEL 7 OFIC 17 SECTOR MAIQUETIA</CAR_CAR_ADR2>");
            fw.write("\n\t\t<CAR_CAR_ADR3>ZONA POSTAL 1160</CAR_CAR_ADR3>");

            fw.write("\n\t\t<CAR_MOT_COD>" + viaje.getTransportMode() + "</CAR_MOT_COD>");
            fw.write("\n\t\t<CAR_ID_TRP>" + viaje.getVesselName() + "</CAR_ID_TRP>");
            fw.write("\n\t\t<CAR_NAT_COD>" + viaje.getCountryTransport() + "</CAR_NAT_COD>");
            fw.write("\n\t\t<CAR_NET_TON>" + viaje.getNetWeight() + "</CAR_NET_TON>");

            fw.write("\n\t\t<CAR_GROS_TON>" + viaje.getGrossWeight() + "</CAR_GROS_TON>");
            fw.write("\n\t\t<CAR_BL_NBER>" + viaje.getDocumentsQty() + "</CAR_BL_NBER>");
            fw.write("\n\t\t<CAR_PAC_NBER>" + viaje.getPackagesQty() + "</CAR_PAC_NBER>");
            fw.write("\n\t\t<CAR_GROS_MASS>" + viaje.getTotalWeight() + "</CAR_GROS_MASS>");
            fw.write("\n\t\t<CAR_CTNR_NBR>" + viaje.getContainerQty() + "</CAR_CTNR_NBR>");
            fw.write("\n\t\t<CAR_DIS_DATE>" + this.procesarFecha(viaje.getDateDischarge()) + "</CAR_DIS_DATE>");

            fw.write("\n\t</MDS5>");

            fw.write("\n\t<Z>");
            fw.write("\n\t\t<NumberOfContainers>" + numeroContenedores + "</NumberOfContainers>");
            fw.write("\n\t</Z>");

            int posicion = 0;
            for (ArrayList<Contenedor> grupoContenedor : viaje.getViajeContenedores()) {
                posicion++;
                int secuenciaCont = 0;
                for (Contenedor contenedor : grupoContenedor) {
                    secuenciaCont++;
                    fw.write("\n\t<MDS7>");
                    fw.write("\n\t\t<KEY_LIN_NBR>" + viaje.getViajeBLS().get(posicion - 1).getKeyLineNumber() + "</KEY_LIN_NBR>");
                    fw.write("\n\t\t<CARBOL_SLINE_NBER>" + "0" + "</CARBOL_SLINE_NBER>");
                    fw.write("\n\t\t<CAR_CTN_NBR>" + secuenciaCont + "</CAR_CTN_NBR>");
                    fw.write("\n\t\t<CAR_CTN_IDENT>" + contenedor.getNumber() + "</CAR_CTN_IDENT>");
                    fw.write("\n\t\t<CAR_CTN_TYP>" + contenedor.getType() + "</CAR_CTN_TYP>");
                    fw.write("\n\t\t<CAR_CTN_FUL>" + "005" + "</CAR_CTN_FUL>");
                    fw.write("\n\t\t<CAR_CTN_SEAL1>" + contenedor.getSeal() + "</CAR_CTN_SEAL1>");
                    if (!(contenedor.getSeal2().equals("NA"))) {
                        fw.write("\n\t\t<CAR_CTN_SEAL2>" + contenedor.getSeal2() + "</CAR_CTN_SEAL2>");
                    }
                    if (!(contenedor.getSeal3().equals("NA"))) {
                        fw.write("\n\t\t<CAR_CTN_SEAL3>" + contenedor.getSeal3() + "</CAR_CTN_SEAL3>");
                    }
                    fw.write("\n\t\t<CAR_CTN_SEAL>" + "CR" + "</CAR_CTN_SEAL>");
                    fw.write("\n\t</MDS7>");
                }
            }
            posicion = 0;
            for (BL bl : viaje.getViajeBLS()) {
                posicion++;
                fw.write("\n\t<MDS6>");
                fw.write("\n\t\t<KEY_CUO>" + viaje.getCustomOfficeCode() + "</KEY_CUO>");
                fw.write("\n\t\t<KEY_VOY_NBER>" + viaje.getNumber() + "</KEY_VOY_NBER>");
                fw.write("\n\t\t<KEY_DEP_DATE>" + this.procesarFecha(viaje.getDateOut()) + "</KEY_DEP_DATE>");
                fw.write("\n\t\t<KEY_BOL_REF>COSU" + bl.getBillOfLading() + "</KEY_BOL_REF>");
                fw.write("\n\t\t<KEY_LIN_NBR>" + bl.getKeyLineNumber() + "</KEY_LIN_NBR>");
                fw.write("\n\t\t<CARBOL_SLINE_NBER>" + "0" + "</CARBOL_SLINE_NBER>");

                switch (bl.getCarbolTypeCode()) {
                    case "B/L":
                        fw.write("\n\t\t<CARBOL_STATUS>" + "4" + "</CARBOL_STATUS>");
                        break;
                    case "MBL":
                        fw.write("\n\t\t<CARBOL_STATUS>" + "0" + "</CARBOL_STATUS>");
                        break;
                }

                fw.write("\n\t\t<CARBOL_TYP_COD>" + bl.getCarbolTypeCode() + "</CARBOL_TYP_COD>");
                fw.write("\n\t\t<CARBOL_NAT_COD>" + bl.getCarbolNatCode() + "</CARBOL_NAT_COD>");
                if (bl.getShipper().length() > 35) {
                    fw.write("\n\t\t<CARBOL_EXP_NAM>" + bl.getShipper().substring(0, 35) + "</CARBOL_EXP_NAM>");
                } else {
                    fw.write("\n\t\t<CARBOL_EXP_NAM>" + bl.getShipper() + "</CARBOL_EXP_NAM>");
                }
                //fw.write("\n\t\t<CARBOL_EXP_ADR1>NA</CARBOL_EXP_ADR1>");
                String[] consignatario = bl.getConsignee().split("/");
                consignatario[consignatario.length - 1] = consignatario[consignatario.length - 1].replace("-", "");
                fw.write("\n\t\t<CARBOL_CONS_COD>" + consignatario[consignatario.length - 1] + "</CARBOL_CONS_COD>");
                //fw.write("\n\t\t<CARBOL_CONS_NAM>" + consignatario[0] + "</CARBOL_CONS_NAM>");
                //fw.write("\n\t\t<CARBOL_CONS_ADR1>NA</CARBOL_CONS_ADR1>");
                String[] notifyParty = bl.getNotifyParty().split("/");
                notifyParty[notifyParty.length - 1] = notifyParty[notifyParty.length - 1].replace("-", "");
                fw.write("\n\t\t<CARBOL_NTFY_COD>" + notifyParty[notifyParty.length - 1] + "</CARBOL_NTFY_COD>");
                //fw.write("\n\t\t<CARBOL_NTFY_NAM>" + notifyParty[0] + "</CARBOL_NTFY_NAM>");
                //fw.write("\n\t\t<CARBOL_NTFY_ADR1>NA</CARBOL_NTFY_ADR1>");
                fw.write("\n\t\t<CARBOL_DEP_COD>" + viaje.getPlaceOut() + "</CARBOL_DEP_COD>");
                fw.write("\n\t\t<CARBOL_DEST_COD>" + viaje.getPlaceDestiny() + "</CARBOL_DEST_COD>");
                fw.write("\n\t\t<CARBOL_CONT_NBER>" + viaje.getViajeContenedores().get(posicion - 1).size() + "</CARBOL_CONT_NBER>");
                fw.write("\n\t\t<CARBOL_PACK_COD>" + bl.getPackagesUnit() + "</CARBOL_PACK_COD>");
                fw.write("\n\t\t<CARBOL_PACK_NBER>" + bl.getPackagesTotal() + "</CARBOL_PACK_NBER>");
                fw.write("\n\t\t<CARBOL_GROS_MAS>" + bl.getGrossWeight() + "</CARBOL_GROS_MAS>");
                String descAux = bl.getDescriptionOfGoods();
                int descLen = descAux.length();
                if (descLen <= 35) {
                    fw.write("\n\t\t<CARBOL_GOODS1>" + descAux.substring(0, descLen) + "</CARBOL_GOODS1>");
                }
                if (descLen > 35 && descLen <= 70) {
                    fw.write("\n\t\t<CARBOL_GOODS1>" + descAux.substring(0, 35) + "</CARBOL_GOODS1>");
                    fw.write("\n\t\t<CARBOL_GOODS2>" + descAux.substring(35, descLen) + "</CARBOL_GOODS2>");
                }
                if (descLen > 70 && descLen <= 105) {
                    fw.write("\n\t\t<CARBOL_GOODS1>" + descAux.substring(0, 35) + "</CARBOL_GOODS1>");
                    fw.write("\n\t\t<CARBOL_GOODS2>" + descAux.substring(35, 70) + "</CARBOL_GOODS2>");
                    fw.write("\n\t\t<CARBOL_GOODS3>" + descAux.substring(70, descLen) + "</CARBOL_GOODS3>");
                }
                if (descLen > 105 && descLen <= 140) {
                    fw.write("\n\t\t<CARBOL_GOODS1>" + descAux.substring(0, 35) + "</CARBOL_GOODS1>");
                    fw.write("\n\t\t<CARBOL_GOODS2>" + descAux.substring(35, 70) + "</CARBOL_GOODS2>");
                    fw.write("\n\t\t<CARBOL_GOODS3>" + descAux.substring(70, 105) + "</CARBOL_GOODS3>");
                    fw.write("\n\t\t<CARBOL_GOODS4>" + descAux.substring(105, descLen) + "</CARBOL_GOODS4>");
                }
                if (descLen > 140 && descLen <= 175) {
                    fw.write("\n\t\t<CARBOL_GOODS1>" + descAux.substring(0, 35) + "</CARBOL_GOODS1>");
                    fw.write("\n\t\t<CARBOL_GOODS2>" + descAux.substring(35, 70) + "</CARBOL_GOODS2>");
                    fw.write("\n\t\t<CARBOL_GOODS3>" + descAux.substring(70, 105) + "</CARBOL_GOODS3>");
                    fw.write("\n\t\t<CARBOL_GOODS4>" + descAux.substring(105, 140) + "</CARBOL_GOODS4>");
                    fw.write("\n\t\t<CARBOL_GOODS5>" + descAux.substring(140, descLen) + "</CARBOL_GOODS5>");
                }
                if (descLen > 175) {
                    fw.write("\n\t\t<CARBOL_GOODS1>" + descAux.substring(0, 35) + "</CARBOL_GOODS1>");
                    fw.write("\n\t\t<CARBOL_GOODS2>" + descAux.substring(35, 70) + "</CARBOL_GOODS2>");
                    fw.write("\n\t\t<CARBOL_GOODS3>" + descAux.substring(70, 105) + "</CARBOL_GOODS3>");
                    fw.write("\n\t\t<CARBOL_GOODS4>" + descAux.substring(105, 140) + "</CARBOL_GOODS4>");
                    fw.write("\n\t\t<CARBOL_GOODS5>" + descAux.substring(140, 175) + "</CARBOL_GOODS5>");
                }
                fw.write("\n\t\t<CARBOL_FRT_PREP>" + "P" + "</CARBOL_FRT_PREP>");
                fw.write("\n\t\t<CARBOL_FRT_CUR>" + "USD" + "</CARBOL_FRT_CUR>");
                fw.write("\n\t\t<CARBOL_SUBBOL_NBR>" + "0" + "</CARBOL_SUBBOL_NBR>");
                fw.write("\n\t\t<BOL_LIN_NBR>" + bl.getKeyLineNumber() + "</BOL_LIN_NBR>");
                fw.write("\n\t\t<CAR_PKG_AVL>" + bl.getPackagesTotal() + "</CAR_PKG_AVL>");
                fw.write("\n\t\t<CAR_WGT_AVL>" + bl.getGrossWeight() + "</CAR_WGT_AVL>");
                fw.write("\n\t\t<CAR_LOC_COD>" + viaje.getWareCode() + "</CAR_LOC_COD>");
                fw.write("\n\t</MDS6>");
            }
            fw.write("\n</MdsParts>");
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String calcularNumeroContenedores2(Viaje viaje) {
        String res = "";
        int suma = 0;
        for (ArrayList<Contenedor> grupoContenedor : viaje.getViajeContenedores()) {
            suma = suma + grupoContenedor.size();
        }
        res = String.valueOf(suma);
        return res;
    }

    public String procesarFecha(String fecha) {
        String anio = fecha.substring(6, 10); //01/02/2015
        String mes = fecha.substring(3, 5);
        String dia = fecha.substring(0, 2);
        String res = anio + mes + dia;
        return res;
    }
}
