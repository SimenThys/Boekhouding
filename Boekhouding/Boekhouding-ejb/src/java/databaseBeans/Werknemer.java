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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author student
 */
@Entity
@Table(name = "WERKNEMER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Werknemer.findAll", query = "SELECT w FROM Werknemer w"),
    @NamedQuery(name = "Werknemer.findByWnr", query = "SELECT w FROM Werknemer w WHERE w.wnr = :wnr"),
    @NamedQuery(name = "Werknemer.findByBnr", query = "SELECT w FROM Werknemer w WHERE w.bnr = :bnr"),
    @NamedQuery(name = "Werknemer.findByPaswd", query = "SELECT w FROM Werknemer w WHERE w.paswd = :paswd"),
    @NamedQuery(name = "Werknemer.findByTyp", query = "SELECT w FROM Werknemer w WHERE w.typ = :typ")})
public class Werknemer implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "WNR")
    private BigDecimal wnr;
    @Column(name = "BNR")
    private BigInteger bnr;
    @Size(max = 50)
    @Column(name = "PASWD")
    private String paswd;
    @Column(name = "TYP")
    private BigInteger typ;
    @JoinTable(name = "REKENING", joinColumns = {
        @JoinColumn(name = "WNR", referencedColumnName = "WNR")}, inverseJoinColumns = {
        @JoinColumn(name = "KNR", referencedColumnName = "KNR")})
    @ManyToMany
    private Collection<Kredieten> kredietenCollection;
    @OneToMany(mappedBy = "manager")
    private Collection<Kredieten> kredietenCollection1;

    public Werknemer() {
    }

    public Werknemer(BigDecimal wnr) {
        this.wnr = wnr;
    }

    public BigDecimal getWnr() {
        return wnr;
    }

    public void setWnr(BigDecimal wnr) {
        this.wnr = wnr;
    }

    public BigInteger getBnr() {
        return bnr;
    }

    public void setBnr(BigInteger bnr) {
        this.bnr = bnr;
    }

    public String getPaswd() {
        return paswd;
    }

    public void setPaswd(String paswd) {
        this.paswd = paswd;
    }

    public BigInteger getTyp() {
        return typ;
    }

    public void setTyp(BigInteger typ) {
        this.typ = typ;
    }

    @XmlTransient
    public Collection<Kredieten> getKredietenCollection() {
        return kredietenCollection;
    }

    public void setKredietenCollection(Collection<Kredieten> kredietenCollection) {
        this.kredietenCollection = kredietenCollection;
    }

    @XmlTransient
    public Collection<Kredieten> getKredietenCollection1() {
        return kredietenCollection1;
    }

    public void setKredietenCollection1(Collection<Kredieten> kredietenCollection1) {
        this.kredietenCollection1 = kredietenCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wnr != null ? wnr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Werknemer)) {
            return false;
        }
        Werknemer other = (Werknemer) object;
        if ((this.wnr == null && other.wnr != null) || (this.wnr != null && !this.wnr.equals(other.wnr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "databaseBeans.Werknemer[ wnr=" + wnr + " ]";
    }
    
}
