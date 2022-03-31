package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveProyecto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveAmbitoGeografico.class)
public class InveAmbitoGeografico_ { 

    public static volatile SingularAttribute<InveAmbitoGeografico, String> ambNombre;
    public static volatile CollectionAttribute<InveAmbitoGeografico, InveProyecto> inveProyectoCollection;
    public static volatile SingularAttribute<InveAmbitoGeografico, Short> ambCodigo;

}