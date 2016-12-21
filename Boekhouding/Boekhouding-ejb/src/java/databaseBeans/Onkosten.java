/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseBeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author student
 */
@Entity
@Table(name = "ONKOSTEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Onkosten.findAll", query = "SELECT o FROM Onkosten o"),
    @NamedQuery(name = "Onkosten.findByOnr", query = "SELECT o FROM Onkosten o WHERE o.onr = :onr"),
    @NamedQuery(name = "Onkosten.findByDatum", query = "SELECT o FROM Onkosten o WHERE o.datum = :datum"),
    @NamedQuery(name = "Onkosten.findByBedrag", query = "SELECT o FROM Onkosten o WHERE o.bedrag = :bedrag"),
    @NamedQuery(name = "Onkosten.findByWerknemer", query = "SELECT o FROM Onkosten o WHERE o.wnr = :wnr"),
    @NamedQuery(name = "Onkosten.findByStatus", query = "SELECT o FROM Onkosten o WHERE o.status = :status"),
    @NamedQuery(name = "Onkosten.findMax", query = "SELECT max(o.onr) from Onkosten o"),
    @NamedQuery(name = "Onkosten.removeOnkost", query = "DELETE FROM Onkosten o WHERE o.onr = :onr")})
public class Onkosten implements Serializable {

    @Size(max = 100)
    @Column(name = "OMSCHRIJVING")
    private String omschrijving;

    @JoinColumn(name = "WNR", referencedColumnName = "WNR")
    @ManyToOne
    private Werknemer wnr;

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ONR")
    private BigDecimal onr;
    @Column(name = "DATUM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datum;
    @Column(name = "BEDRAG")
    private BigInteger bedrag;
    @Column(name = "STATUS")
    private BigInteger status;
    @JoinColumn(name = "KNR", referencedColumnName = "KNR")
    @ManyToOne
    private Kredieten knr;

    public Onkosten() {
    }

    public Onkosten(BigDecimal onr) {
        this.onr = onr;
    }

    public BigDecimal getOnr() {
        return onr;
    }

    public void setOnr(BigDecimal onr) {
        this.onr = onr;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public BigInteger getBedrag() {
        return bedrag;
    }

    public void setBedrag(BigInteger bedrag) {
        this.bedrag = bedrag;
    }

    public BigInteger getStatus() {
        return status;
    }
    
    public String getNaamStatus() {
        int status2 = status.intValue();
        if(status2 == 0)
            return "In aanmaak";
        if(status2 == 1)
            return "Doorgestuurd";
        if(status2 == 2)
            return "Betaald";
        if(status2 == 3)
            return "Afgekeurd";
        else
            return "Geen status";
    }

    public void setStatus(BigInteger status) {
        this.status = status;
    }

    public Kredieten getKnr() {
        return knr;
    }

    public void setKnr(Kredieten knr) {
        this.knr = knr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (onr != null ? onr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Onkosten)) {
            return false;
        }
        Onkosten other = (Onkosten) object;
        if ((this.onr == null && other.onr != null) || (this.onr != null && !this.onr.equals(other.onr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "databaseBeans.Onkosten[ onr=" + onr + " ]";
    }

    public Werknemer getWnr() {
        return wnr;
    }

    public void setWnr(Werknemer wnr) {
        this.wnr = wnr;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }
    
}
