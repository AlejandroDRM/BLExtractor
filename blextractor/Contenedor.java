/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blextractor;

/**
 *
 * @author MS2
 */
public class Contenedor {

    private String number;
    private String seal;
    private String type;
    private String BL;
    private String seal2;
    private String seal3;

    public Contenedor() {
    }

    public Contenedor(String number, String seal, String type) {
        this.number = number;
        this.seal = seal;
        this.type = type;
    }

    public Contenedor(String number, String seal, String seal2, String seal3, String type) {
        this.number = number;
        this.seal = seal;
        this.seal2 = seal2;
        this.seal3 = seal3;
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSeal() {
        return seal;
    }

    public void setSeal(String seal) {
        this.seal = seal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBL() {
        return BL;
    }

    public void setBL(String BL) {
        this.BL = BL;
    }

    public String getSeal2() {
        return seal2;
    }

    public void setSeal2(String seal2) {
        this.seal2 = seal2;
    }

    public String getSeal3() {
        return seal3;
    }

    public void setSeal3(String seal3) {
        this.seal3 = seal3;
    }

}
