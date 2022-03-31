package ec.edu.uasb.principal.entities;

import ec.edu.uasb.investigacion.entities.InveInformeOper;
import ec.edu.uasb.investigacion.entities.InveRecursoHumano;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(PrinTipoDedicacion.class)
public class PrinTipoDedicacion_ { 

    public static volatile SingularAttribute<PrinTipoDedicacion, String> tdeDescripcion;
    public static volatile SingularAttribute<PrinTipoDedicacion, Short> tdeCodigo;
    public static volatile CollectionAttribute<PrinTipoDedicacion, InveInformeOper> inveInformeOperCollection;
    public static volatile CollectionAttribute<PrinTipoDedicacion, InveRecursoHumano> inveRecursoHumanoCollection;

}