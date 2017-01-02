/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseBeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author student
 */
@Entity
@Table(name = "KREDIETEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kredieten.findAll", query = "SELECT k FROM Kredieten k"),
    @NamedQuery(name = "Kredieten.findByKnr", query = "SELECT k FROM Kredieten k WHERE k.knr = :knr"),
    @NamedQuery(name = "Kredieten.findBySaldo", query = "SELECT k FROM Kredieten k WHERE k.saldo = :saldo"),
    @NamedQuery(name = "Kredieten.findByTyp", query = "SELECT k FROM Kredieten k WHERE k.typ = :typ")})
public class Kredieten implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "KNR")
    private BigDecimal knr;
    @Column(name = "SALDO")
    private BigInteger saldo;
    @Column(name = "TYP")
    private BigInteger typ;
    @ManyToMany(mappedBy = "kredietenCollection")
    private Collection<Werknemer> werknemerCollection;
    @JoinColumn(name = "MANAGER", referencedColumnName = "WNR")
    @ManyToOne
    private Werknemer manager;
    @OneToMany(mappedBy = "knr")
    private Collection<Onkosten> onkostenCollection;

    public Kredieten() {
    }

    public Kredieten(BigDecimal knr) {
        this.knr = knr;
    }

    public BigDecimal getKnr() {
        return knr;
    }

    public void setKnr(BigDecimal knr) {
        this.knr = knr;
    }

    public BigInteger getSaldo() {
        return saldo;
    }

    public void setSaldo(BigInteger saldo) {
        this.saldo = saldo;
    }

    public BigInteger getTyp() {
        return typ;
    }

    public void setTyp(BigInteger typ) {
        this.typ = typ;
    }

    @XmlTransient
    public Collection<Werknemer> getWerknemerCollection() {
        return werknemerCollection;
    }

    public void setWerknemerCollection(Collection<Werknemer> werknemerCollection) {
        this.werknemerCollection = werknemerCollection;
    }

    public Werknemer getManager() {
        return manager;
    }

    public void setManager(Werknemer manager) {
        this.manager = manager;
    }

    @XmlTransient
    public Collection<Onkosten> getOnkostenCollection() {
        return onkostenCollection;
    }

    public void setOnkostenCollection(Collection<Onkosten> onkostenCollection) {
        this.onkostenCollection = onkostenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (knr != null ? knr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kredieten)) {
            return false;
        }
        Kredieten other = (Kredieten) object;
        if ((this.knr == null && other.knr != null) || (this.knr != null && !this.knr.equals(other.knr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Krediet nr " + knr + " heeft een saldo van €" + saldo;
    }
    
}
