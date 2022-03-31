package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveLineaInvestProyec;
import ec.edu.uasb.investigacion.entities.InveProgramaProyecto;
import ec.edu.uasb.investigacion.entities.InveProyecto;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveCarta.class)
public class InveCarta_ { 

    public static volatile SingularAttribute<InveCarta, String> carUsuarioCrea;
    public static volatile SingularAttribute<InveCarta, Short> carCapitulo;
    public static volatile SingularAttribute<InveCarta, Long> pryCodigo;
    public static volatile CollectionAttribute<InveCarta, InveProgramaProyecto> inveProgramaProyectoCollection;
    public static volatile SingularAttribute<InveCarta, Date> carFechaRecep;
    public static volatile SingularAttribute<InveCarta, InveLineaInvestProyec> inveLineaInvestProyec;
    public static volatile SingularAttribute<InveCarta, String> carEsPertinente;
    public static volatile SingularAttribute<InveCarta, Date> carFechaCrea;
    public static volatile SingularAttribute<InveCarta, String> carTipoCarta;
    public static volatile SingularAttribute<InveCarta, String> carTituloTesis;
    public static volatile SingularAttribute<InveCarta, String> carRazonPertinencia;
    public static volatile SingularAttribute<InveCarta, InveProyecto> inveProyecto;
    public static volatile SingularAttribute<InveCarta, String> carUsuarioRecep;
    public static volatile SingularAttribute<InveCarta, Date> carFechaPertinencia;

}