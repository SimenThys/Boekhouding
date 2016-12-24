/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import databaseBeans.Kredieten;
import databaseBeans.Onkosten;
import databaseBeans.Werknemer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author student
 */
@Stateless
public class SessionBeanLocal implements SessionBeanLocalInterface {

    @PersistenceContext private EntityManager em;
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    public int OpvragenType(int wnr){
        Werknemer w = em.find(Werknemer.class, new BigDecimal(wnr));
        return w.getTyp().intValue();
    }
    
    public List OpvragenWerknemer(int wnr){
        Werknemer w = em.find(Werknemer.class, new BigDecimal(wnr));
        return em.createNamedQuery("Onkosten.findByWerknemer").setParameter("wnr", w).getResultList();
    }
    
    public List OpvragenBoekhouder(){
        return em.createNamedQuery("Kredieten.findAll").getResultList();
    }
    public Map <Kredieten, Integer> OpvragenOnkostAanvragen(int wnr,int saldo){
        Map <Kredieten, Integer> items = new HashMap<>();
        Werknemer w = em.find(Werknemer.class, new BigDecimal(wnr));
        int bnr = w.getBnr().intValue();
        if( bnr != wnr){
            items = GetKredieten(items,bnr,saldo);
        }
        int type = w.getTyp().intValue();
        if (type == 2 || type == 3){
            items = GetKredieten(items,wnr,saldo);
        }
        return items;
    }
    public Map <Kredieten, Integer> GetKredieten(Map<Kredieten, Integer> items,int wnr,int saldo){
        Werknemer w = em.find(Werknemer.class, new BigDecimal(wnr));
        Collection<Kredieten> kredieten = w.getKredietenCollection();
        for(Kredieten krediet : kredieten){
            if(krediet.getTyp().intValue() == 0){
                if(krediet.getSaldo().intValue() - saldo >= 0)
                    items.put(krediet,0);
            }
            else{
                if(krediet.getSaldo().intValue() - saldo >= 0)
                    items.put(krediet,0);
                else
                    items.put(krediet,1);
            }
        }
        return items;
    }
    public Kredieten OpvragenKrediet(int knr){
        return (Kredieten)em.createNamedQuery("Kredieten.findByKnr").setParameter("knr", new BigDecimal(knr)).getSingleResult();
    }
    
    public Onkosten OpvragenOnkost(int onr){
        return (Onkosten)em.createNamedQuery("Onkosten.findByOnr").setParameter("onr", new BigDecimal(onr)).getSingleResult();
    }
    
    public Onkosten tempOnkost(int wnr,int onkostnummer,int bedrag,String omschr,String datum){
        Onkosten o = new Onkosten();
        BigDecimal onr;
        if(onkostnummer == -1)
        {
            int lastNummer = ((BigDecimal)em.createNamedQuery("Onkosten.findMax").getSingleResult()).intValue();
            lastNummer += 1;
            onr =new BigDecimal(lastNummer);
        }
        else
            onr = new BigDecimal(onkostnummer);
        o.setOnr(onr);
        Werknemer werknemer = (Werknemer)em.createNamedQuery("Werknemer.findByWnr").setParameter("wnr", new BigDecimal(wnr)).getSingleResult();
        o.setWnr(werknemer);
        o.setOmschrijving(omschr);
        o.setBedrag(new BigInteger(String.valueOf(bedrag)));
        if(datum == null)
            o.setDatum(new Date());
        else
        {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            Date date;
            try {
                date = df.parse(datum);
            } catch (ParseException ex) {
                System.out.print("datum veld leeg/ of iets anders");
                date = null;
            }
            o.setDatum(date);
        }
        o.setStatus(new BigInteger(String.valueOf(0)));
        return o;
    }
    
    public void OnkostToevoegen(int wnr, int bedrag, String omschrijving,int status, String datum){
        Onkosten o = new Onkosten();
        int lastNummer = ((BigDecimal)em.createNamedQuery("Onkosten.findMax").getSingleResult()).intValue();
        lastNummer += 1;
        BigDecimal onr =new BigDecimal(lastNummer);
        o.setOnr(onr);
        Werknemer werknemer = (Werknemer)em.createNamedQuery("Werknemer.findByWnr").setParameter("wnr", new BigDecimal(wnr)).getSingleResult();
        o.setWnr(werknemer);
        o.setBedrag(new BigInteger(String.valueOf(bedrag)));
        o.setOmschrijving(omschrijving);
        o.setStatus(new BigInteger(String.valueOf(status)));
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date date;
        try {
            date = df.parse(datum);
        } catch (ParseException ex) {
            System.out.print("datum veld leeg/ of iets anders");
            date = null;
        }
        o.setDatum(date);
        em.persist(o);
    }
    
    public void OnkostVerwijderen(int onr){
        em.createNamedQuery("Onkosten.removeOnkost").setParameter("onr", new BigDecimal(onr)).executeUpdate();
    }
    
    public void editOnkost(int onr,int knr,int bedrag, String omschrijving, int status, String datum){
        Onkosten onkost = OpvragenOnkost(onr);
        onkost.setBedrag(new BigInteger(String.valueOf(bedrag)));
        onkost.setOmschrijving(omschrijving);
        onkost.setStatus(new BigInteger(String.valueOf(status)));
        if(knr > -1)
            onkost.setKnr(OpvragenKrediet(knr));
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        Date date;
        try {
            date = df.parse(datum);
        } catch (ParseException ex) {
            System.out.print("datum veld leeg/ of iets anders");
            date = null;
        }
        onkost.setDatum(date);
        em.persist(onkost);
    }
    public void editKrediet(int knr,int bedrag)
    {
        Kredieten krediet = OpvragenKrediet(knr);
        krediet.setSaldo(new BigInteger(String.valueOf(krediet.getSaldo().intValue() - bedrag)));
        em.persist(krediet);
    }

}
