package ec.edu.uasb.vinculacion.entities;

import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.vinculacion.entities.VincCine;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(VincCine.class)
public class VincCine_ { 

    public static volatile SingularAttribute<VincCine, String> cinCodigo;
    public static volatile SingularAttribute<VincCine, String> cinCampo;
    public static volatile CollectionAttribute<VincCine, VincCine> vincCineCollection;
    public static volatile CollectionAttribute<VincCine, InveProyecto> inveProyectoCollection;
    public static volatile SingularAttribute<VincCine, VincCine> vincCine;

}