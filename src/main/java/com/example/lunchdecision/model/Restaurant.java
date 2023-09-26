package com.example.lunchdecision.model;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "restaurants")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "varchar(255)")
    private String name;

    @Column(name = "address", columnDefinition = "varchar(255)")
    private String address;

    @Column(name = "cuisine", columnDefinition = "varchar(255)")
    private String cuisine;
    
    @ManyToMany(mappedBy = "restaurants")
    @JsonIgnore
    private List<Session> sessions;
    
   // @ElementCollection
   // @CollectionTable(name = "restaurant_restaurants", joinColumns = @JoinColumn(name = "restaurant_id"))
 ///   private List<Restaurant> restaurants;

}
