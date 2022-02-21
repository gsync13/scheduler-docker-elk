package com.scheduler.scheduler.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table( name = "film" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {

//	private static final long serialVersionUID = -7224365809920916498L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="title")
	private String title;

	@Column(name = "director")
	private String director;

	@Column(name="producer")
	private String producer;

	@Column(name="url")
	private String url;

}
