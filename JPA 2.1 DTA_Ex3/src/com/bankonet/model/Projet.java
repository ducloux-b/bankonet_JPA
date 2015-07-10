package com.bankonet.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Projet {
	@Id
	@GeneratedValue
	private int id;
	private String nom;
	@ManyToMany(mappedBy="projets")
	private List<Employe> employes = new ArrayList<Employe>();
	@OneToMany(cascade=CascadeType.PERSIST)
	private List<Participation> participations = new ArrayList<Participation>();

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public Projet(){}

	public List<Employe> getEmployes() {
		return employes;
	}
	
	public void addEmploye(Employe p){
		this.employes.add(p);
	}

	public void addParticipation(Employe employe,String fonction) {
		Participation participation = new Participation(employe,this,fonction); 
		this.participations.add(participation);
		employe.getParticipations().add(participation);
	}
}
