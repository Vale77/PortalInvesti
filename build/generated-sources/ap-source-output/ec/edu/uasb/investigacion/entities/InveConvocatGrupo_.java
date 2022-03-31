package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveConvocatGrupoPK;
import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.entities.InveGrupo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveConvocatGrupo.class)
public class InveConvocatGrupo_ { 

    public static volatile SingularAttribute<InveConvocatGrupo, InveConvocatoria> inveConvocatoria;
    public static volatile SingularAttribute<InveConvocatGrupo, Integer> cgrAnioConsulta;
    public static volatile SingularAttribute<InveConvocatGrupo, Integer> cgrAniosAntiguedad;
    public static volatile SingularAttribute<InveConvocatGrupo, InveConvocatGrupoPK> inveConvocatGrupoPK;
    public static volatile SingularAttribute<InveConvocatGrupo, InveGrupo> inveGrupo;

}