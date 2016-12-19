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
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author student
 */
@Local
public interface SessionBeanLocalInterface {
    
    public int OpvragenType(int wnr);
    public List OpvragenWerknemer(int wnr);
    public List OpvragenBoekhouder();
    public Map <Kredieten, Integer> OpvragenOnkostAanvragen(int wnr,int saldo);
    public Map <Kredieten, Integer> GetKredieten(Map<Kredieten, Integer> items,int wnr,int saldo);
    public Kredieten OpvragenKrediet(int knr);
    public Onkosten OpvragenOnkost(int onr);
    public void OnkostToevoegen(int wnr, int bedrag, String omschrijving);
    public void OnkostToevoegen(Onkosten o);
    public void OnkostVerwijderen(int onr);
    public void editOnkost(int bedrag,int onr,String omschrijving);
    public Onkosten tempOnkost(int wnr);
}
