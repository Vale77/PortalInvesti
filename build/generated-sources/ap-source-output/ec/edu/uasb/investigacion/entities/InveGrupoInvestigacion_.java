package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveProyecto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveGrupoInvestigacion.class)
public class InveGrupoInvestigacion_ { 

    public static volatile SingularAttribute<InveGrupoInvestigacion, Short> ginCodigo;
    public static volatile SingularAttribute<InveGrupoInvestigacion, String> ginNombre;
    public static volatile CollectionAttribute<InveGrupoInvestigacion, InveProyecto> inveProyectoCollection;

}