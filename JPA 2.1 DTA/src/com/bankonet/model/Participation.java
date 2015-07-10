package com.bankonet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="PARTICIPATION2"/*,
		uniqueConstraints=@UniqueConstraint(columnNames={"EMPLOYE_ID","PROJET_ID"})*/)
public class Participation {

	@Id
	@GeneratedValue
	int id;
	private String fonction = new String();
	@ManyToOne
	private Employe employe;
	@ManyToOne
	private Projet projet;
	
	public Participation(){}
	public Participation(Employe employe,Projet projet,String fonction){
		this.fonction = fonction;
		this.employe = employe;
		this.projet = projet;
	}
	
	public int getId() {
		return id;
	}
	public String getFonction() {
		return fonction;
	}
	
}
