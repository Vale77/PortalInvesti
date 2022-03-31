package ec.edu.uasb.principal.entities;

import ec.edu.uasb.investigacion.entities.InveProyecto;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(PrinCategoriaInvest.class)
public class PrinCategoriaInvest_ { 

    public static volatile SingularAttribute<PrinCategoriaInvest, Short> caiCodigo;
    public static volatile SingularAttribute<PrinCategoriaInvest, String> caiDescripcion;
    public static volatile CollectionAttribute<PrinCategoriaInvest, InveProyecto> inveProyectoCollection;

}