package ec.edu.uasb.principal.entities;

import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.principal.entities.PrinCiudad;
import ec.edu.uasb.principal.entities.PrinPersona;
import ec.edu.uasb.principal.entities.PrinTitulo;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(PrinPais.class)
public class PrinPais_ { 

    public static volatile SingularAttribute<PrinPais, Short> paiCodigoSniese;
    public static volatile SingularAttribute<PrinPais, String> paiNombre;
    public static volatile CollectionAttribute<PrinPais, PrinTitulo> prinTituloCollection;
    public static volatile SingularAttribute<PrinPais, Short> paiCodigo;
    public static volatile CollectionAttribute<PrinPais, InveProyecto> inveProyectoCollection;
    public static volatile SingularAttribute<PrinPais, String> paiCodInter;
    public static volatile CollectionAttribute<PrinPais, PrinPersona> prinPersonaCollection;
    public static volatile SingularAttribute<PrinPais, String> paiVocNacionalidad;
    public static volatile CollectionAttribute<PrinPais, PrinCiudad> prinCiudadCollection;
    public static volatile SingularAttribute<PrinPais, String> paiAbrev;

}