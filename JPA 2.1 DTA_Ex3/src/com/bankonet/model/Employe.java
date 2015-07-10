package com.bankonet.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Un employ� de l'entreprise.
 */
@Entity
@DiscriminatorValue("E")
@NamedQuery(name = "findNomsEmployes",query = "select e.nom from Employe as e")
public class Employe extends Personne implements Serializable{
	private BigDecimal salaire;
	@ManyToOne
	private Employe superieur;
	@ManyToOne
	private Departement departement;
	@ManyToMany
	private List<Projet> projets = new ArrayList<Projet>();
	@OneToMany(cascade=CascadeType.PERSIST)
	private List<Participation> participations = new ArrayList<Participation>();
	
	public Employe() {
	}

	public Employe(String nom) {
		super(nom);
	}

	public Employe(String nom, Departement departement, Employe superieur) {
		super(nom);
		departement.addEmploye(this);
		this.superieur = superieur;
	}

	public BigDecimal getSalaire() {
		return salaire;
	}

	public void setSalaire(BigDecimal salaire) {
		this.salaire = salaire;
	}

	public Employe getSuperieur() {
		return superieur;
	}

	public void setSuperieur(Employe employe) {
		this.superieur = employe;
	}

	public Departement getDepartement() {
		return departement;
	}

	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	
	public void addProjet(Projet p) {
		this.projets.add(p);
		p.addEmploye(this);
	}
	
	public void addParticipation(Participation participation)
	{
		this.participations.add(participation);
	}

	public List<Participation> getParticipations() {
		return this.participations;
	}
	
	public List<String> findNomsEmployes(EntityManager em)
	{
		return (List<String>)em.createNamedQuery("findNomsEmployes").getResultList();
	}
}
