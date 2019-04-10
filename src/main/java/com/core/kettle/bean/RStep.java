package com.core.kettle.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "R_STEP")
public class RStep {
    @Id
    @Column(name = "idStep")
    private int idStep;
    @Column(name = "idTransformation")
    private int idTransformation;
    @Column(name = "name;")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "idStepType")
    private int idStepType;
    @Column(name = "distribute")
    private int distribute;
    @Column(name = "copies")
    private int copies;
    @Column(name = "guiLocationX")
    private int guiLocationX;
    @Column(name = "guiLocationY")
    private int guiLocationY;
    @Column(name = "guiDraw")
    private int guiDraw;
    @Column(name = "copyString")
    private String copyString;
}
