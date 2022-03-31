package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveConvocatGrupo;
import ec.edu.uasb.investigacion.entities.InveRubroGrupo;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveGrupo.class)
public class InveGrupo_ { 

    public static volatile CollectionAttribute<InveGrupo, InveConvocatGrupo> inveConvocatGrupoCollection;
    public static volatile SingularAttribute<InveGrupo, String> grpNombre;
    public static volatile SingularAttribute<InveGrupo, Short> grpCodigo;
    public static volatile CollectionAttribute<InveGrupo, InveRubroGrupo> inveRubroGrupoCollection;

}