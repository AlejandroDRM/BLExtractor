/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blextractor;

import java.util.ArrayList;

/**
 *
 * @author Alejandro
 */
public class Viaje {

    private String number;
    private String dateOut;
    private String dateIn;
    private String hourIn;
    private String placeOut;
    private String placeDestiny;
    private String transporter;
    private String transportMode;
    private String vesselName;
    private String CountryTransport;
    private String netWeight;
    private String grossWeight;
    private String documentsQty;
    private String packagesQty;
    private String totalWeight;
    private String containerQty;
    private String dateDischarge;
    private String wareCode;
    private String customOfficeCode;
    private ArrayList<BL> viajeBLS;
    private ArrayList<ArrayList<Contenedor>> viajeContenedores;

    public Viaje() {
        this.viajeBLS = new ArrayList<>();
        this.viajeContenedores = new ArrayList<>();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public String getHourIn() {
        return hourIn;
    }

    public void setHourIn(String hourIn) {
        this.hourIn = hourIn;
    }

    public String getPlaceOut() {
        return placeOut;
    }

    public void setPlaceOut(String placeOut) {
        this.placeOut = placeOut;
    }

    public String getTransporter() {
        return transporter;
    }

    public void setTransporter(String transporter) {
        this.transporter = transporter;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    public String getDocumentsQty() {
        return documentsQty;
    }

    public void setDocumentsQty(String documentsQty) {
        this.documentsQty = documentsQty;
    }

    public String getPackagesQty() {
        return packagesQty;
    }

    public void setPackagesQty(String packagesQty) {
        this.packagesQty = packagesQty;
    }

    public String getContainerQty() {
        return containerQty;
    }

    public void setContainerQty(String containerQty) {
        this.containerQty = containerQty;
    }

    public String getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(String totalWeight) {
        this.totalWeight = totalWeight;
    }

    public String getDateDischarge() {
        return dateDischarge;
    }

    public void setDateDischarge(String dateDischarge) {
        this.dateDischarge = dateDischarge;
    }

    public String getPlaceDestiny() {
        return placeDestiny;
    }

    public void setPlaceDestiny(String placeDestiny) {
        this.placeDestiny = placeDestiny;
    }

    public String getCountryTransport() {
        return CountryTransport;
    }

    public void setCountryTransport(String CountryTransport) {
        this.CountryTransport = CountryTransport;
    }

    public String getNetWeight() {
        return netWeight;
    }

    public void setNetWeight(String netWeight) {
        this.netWeight = netWeight;
    }

    public String getGrossWeight() {
        return grossWeight;
    }

    public void setGrossWeight(String grossWeight) {
        this.grossWeight = grossWeight;
    }

    public ArrayList<BL> getViajeBLS() {
        return viajeBLS;
    }

    public void setViajeBLS(ArrayList<BL> viajeBLS) {
        this.viajeBLS = viajeBLS;
    }

    public ArrayList<ArrayList<Contenedor>> getViajeContenedores() {
        return viajeContenedores;
    }

    public void setViajeContenedores(ArrayList<ArrayList<Contenedor>> viajeContenedores) {
        this.viajeContenedores = viajeContenedores;
    }

    public String getWareCode() {
        return wareCode;
    }

    public void setWareCode(String wareCode) {
        this.wareCode = wareCode;
    }

    public String getCustomOfficeCode() {
        return customOfficeCode;
    }

    public void setCustomOfficeCode(String customOfficeCode) {
        this.customOfficeCode = customOfficeCode;
    }

}
