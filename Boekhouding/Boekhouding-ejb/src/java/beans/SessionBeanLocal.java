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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
                    items.put(krediet, 0);
            }
            else{
                if(krediet.getSaldo().intValue() - saldo >= 0)
                    items.put(krediet, 0);
                else
                    items.put(krediet,1);
            }
        }
        return items;
    }
    public List OpvragenKrediet(int knr){
        return em.createNamedQuery("Kredieten.findByKnr").setParameter("knr", new BigDecimal(knr)).getResultList();
    }
    public List OpvragenOnkost(int onr){
        return em.createNamedQuery("Onkosten.findByOnr").setParameter("knr", new BigDecimal(onr)).getResultList();
    }
    
    public void OnkostToevoegen(int knr, Date datum, int bedrag, String omschrijving){
        Onkosten o = new Onkosten();
        int lastNummer = ((BigDecimal)em.createNamedQuery("Onkosten.findMax").getSingleResult()).intValue();
        lastNummer += 1;
        BigDecimal onr =new BigDecimal(lastNummer);
        o.setOnr(onr);
        Kredieten krediet = (Kredieten)em.createNamedQuery("Kredieten.findByKnr").setParameter("knr", new BigDecimal(knr)).getSingleResult();
        o.setKnr(krediet);
        o.setDatum(new Date());
        o.setBedrag(new BigInteger(String.valueOf(bedrag)));
        o.setOmschrijving(omschrijving);
        o.setStatus(new BigInteger(String.valueOf(0)));
        em.persist(o);
        return;
    }
}
