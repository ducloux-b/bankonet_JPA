package com.bankonet.test;

import java.util.List;

import javax.persistence.*;

import com.bankonet.model.*;



public class Test3 {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Employes");
    EntityManager em = emf.createEntityManager();
	
    //récupère des entités
    //upper() met en majuscules
    String textQuery1 = "select e from Employe as e where upper(e.departement.nom) = :nomDept";
    Query query1 = em.createQuery(textQuery1);
    query1.setParameter("nomDept", "Direction");
    List<Employe> listeEmployes = (List<Employe>)query1.getResultList();
    for (Employe employe : listeEmployes) {
		System.out.println("Employés du département Direction: "+employe.getNom());
	}
    
    //directement les noms et salaires
    String textQuery2 = "select e.nom, e.salaire from Employe as e";
    Query query2 = em.createQuery(textQuery2);
    List<Object[]> listeNomsEtSalaires = (List<Object[]>)query2.getResultList();
    for (Object[] nomEtSalaire : listeNomsEtSalaires) {
		System.out.println("nom et salaire: "+nomEtSalaire[0]+"|"+nomEtSalaire[1]);
	}
    
    Employe employe = new Employe();
    List<String> noms = employe.findNomsEmployes(em);
    for (String nom : noms) {
		System.out.println("noms: "+nom);
	}
    
    EntityTransaction tx = null;
    try {
      tx = em.getTransaction();
      tx.begin();
      
      //augmentation
      String textQuery3 = "update Employe e set e.salaire=e.salaire*1.05";
      Query query3 = em.createQuery(textQuery3);
      int nbUpdates = query3.executeUpdate();
      System.out.println("nbUpdates: "+nbUpdates);
      
      
      
      
      tx.commit();
    }
    catch(Exception e) {
      // En "vrai" il faudrait affiner un peu plus...
      e.printStackTrace();
      if (tx != null) {
        tx.rollback();
      }
    }
    finally {
      if (em != null) {
        em.close();
      }
      if (emf != null) {
        emf.close();
      }
    }
  }
  
  
}

