package com.example.ddashmanagement.Dto;

import com.example.ddashmanagement.Ennum.UniteFacilite;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ConfigPredéfiniesEnchere
{
    private Integer CoutClic ;
    private Integer CoutParticipation ;
    private List<Integer> ValeurMajoration ;
    private Integer Facilité ;
    private Boolean Remboursement ;
    private Integer ValeurRemboursement ;
    private Date datedeclenchement ;
    private Date datefermeture ;
    private UniteFacilite unité ;
    private Integer NombreParticipant ;
    private  Integer NombreMois ;
}
