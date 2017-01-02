/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import databaseBeans.Kredieten;
import databaseBeans.Onkosten;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author student
 */
@Stateless
public class SessionBeanRemote implements SessionBeanRemoteInterface {
    @PersistenceContext private EntityManager em;
    public String OpvragenKrediet(int knr)
    {
        String overzicht = "";
        Kredieten k = (Kredieten)em.createNamedQuery("Kredieten.findByKnr").setParameter("knr", new BigDecimal(knr)).getSingleResult();
        overzicht += k.toString();
        overzicht+= "\n";
        List lijst = new ArrayList<Onkosten>();
        Collection<Onkosten> onkosten = k.getOnkostenCollection();
        for (Onkosten onkost : onkosten) {
            lijst.add(onkost);
        }
        for (Object o : lijst) 
        {
            Onkosten onk = (Onkosten)o;
            overzicht += onk.toString();
            overzicht += "\n";
        }
        return overzicht;
    }
}
