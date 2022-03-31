package ec.edu.uasb.investigacion.entities;

import ec.edu.uasb.investigacion.entities.InveAmbitoGeografico;
import ec.edu.uasb.investigacion.entities.InveCarta;
import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.entities.InveCronogramaMensual;
import ec.edu.uasb.investigacion.entities.InveDisciplinaCientifica;
import ec.edu.uasb.investigacion.entities.InveEquipoMulti;
import ec.edu.uasb.investigacion.entities.InveFuenteFinan;
import ec.edu.uasb.investigacion.entities.InveGrupo;
import ec.edu.uasb.investigacion.entities.InveGrupoInvestigacion;
import ec.edu.uasb.investigacion.entities.InveInformeOper;
import ec.edu.uasb.investigacion.entities.InveLector;
import ec.edu.uasb.investigacion.entities.InveObjetivoSocioEconomico;
import ec.edu.uasb.investigacion.entities.InvePresupuesto;
import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.entities.InveRecursoHumano;
import ec.edu.uasb.investigacion.entities.InveSeguimiento;
import ec.edu.uasb.investigacion.entities.InveTipoProyecto;
import ec.edu.uasb.investigacion.entities.InveVersion;
import ec.edu.uasb.principal.entities.PrinCategoriaInvest;
import ec.edu.uasb.principal.entities.PrinPais;
import ec.edu.uasb.principal.entities.PrinPersona;
import ec.edu.uasb.vinculacion.entities.VincCine;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2022-03-30T15:26:59")
@StaticMetamodel(InveProyecto.class)
public class InveProyecto_ { 

    public static volatile SingularAttribute<InveProyecto, String> pryTitulo;
    public static volatile SingularAttribute<InveProyecto, String> pryPlanteamientoText;
    public static volatile SingularAttribute<InveProyecto, Long> pryCodigo;
    public static volatile SingularAttribute<InveProyecto, String> pryMetodologiaText;
    public static volatile SingularAttribute<InveProyecto, Short> pryAnioAcad;
    public static volatile CollectionAttribute<InveProyecto, InveCronogramaMensual> inveCronogramaMensualCollection;
    public static volatile SingularAttribute<InveProyecto, String> pryRelevancia;
    public static volatile SingularAttribute<InveProyecto, String> pryCelular;
    public static volatile SingularAttribute<InveProyecto, String> pryObjetivoGen;
    public static volatile SingularAttribute<InveProyecto, String> pryPlanInicial;
    public static volatile SingularAttribute<InveProyecto, String> pryEstadoReg;
    public static volatile SingularAttribute<InveProyecto, Date> pryFechaSolicContrato;
    public static volatile SingularAttribute<InveProyecto, InveDisciplinaCientifica> inveDisciplinaCientifica;
    public static volatile CollectionAttribute<InveProyecto, InveVersion> inveVersionCollection;
    public static volatile SingularAttribute<InveProyecto, InveConvocatoria> inveConvocatoria;
    public static volatile SingularAttribute<InveProyecto, String> pryUsuarioCrea;
    public static volatile SingularAttribute<InveProyecto, String> pryJustificacionText;
    public static volatile CollectionAttribute<InveProyecto, InveSeguimiento> inveSeguimientoCollection;
    public static volatile SingularAttribute<InveProyecto, String> pryUsuarioAct;
    public static volatile SingularAttribute<InveProyecto, String> pryRelevanciaText;
    public static volatile CollectionAttribute<InveProyecto, InveProyecto> inveProyectoCollection;
    public static volatile SingularAttribute<InveProyecto, InveTipoProyecto> inveTipoProyecto;
    public static volatile SingularAttribute<InveProyecto, Date> pryFechaAutorizaEnvio;
    public static volatile SingularAttribute<InveProyecto, String> pryObservacion;
    public static volatile CollectionAttribute<InveProyecto, InveRecursoHumano> inveRecursoHumanoCollection;
    public static volatile SingularAttribute<InveProyecto, String> pryObjetivoEsp;
    public static volatile SingularAttribute<InveProyecto, PrinCategoriaInvest> prinCategoriaInvest;
    public static volatile SingularAttribute<InveProyecto, Date> pryFechaAct;
    public static volatile SingularAttribute<InveProyecto, String> pryPlanteamiento;
    public static volatile SingularAttribute<InveProyecto, InveCarta> inveCarta;
    public static volatile SingularAttribute<InveProyecto, String> pryPlanInicialText;
    public static volatile SingularAttribute<InveProyecto, String> pryTipoInvestig;
    public static volatile SingularAttribute<InveProyecto, String> pryTituloFinal;
    public static volatile SingularAttribute<InveProyecto, Short> pryAreaAcad;
    public static volatile SingularAttribute<InveProyecto, Date> pryFechaEstado;
    public static volatile SingularAttribute<InveProyecto, PrinPersona> prinPersona;
    public static volatile CollectionAttribute<InveProyecto, InveLector> inveLectorCollection;
    public static volatile SingularAttribute<InveProyecto, Date> pryFechaRecepContrato;
    public static volatile CollectionAttribute<InveProyecto, InveEquipoMulti> inveEquipoMultiCollection;
    public static volatile SingularAttribute<InveProyecto, String> pryCronoMensual;
    public static volatile SingularAttribute<InveProyecto, InveGrupoInvestigacion> inveGrupoInvestigacion;
    public static volatile CollectionAttribute<InveProyecto, InvePresupuesto> invePresupuestoCollection;
    public static volatile SingularAttribute<InveProyecto, Date> pryFechaEnvioRepos;
    public static volatile SingularAttribute<InveProyecto, InveInformeOper> inveInformeOper;
    public static volatile SingularAttribute<InveProyecto, String> pryCorreo;
    public static volatile SingularAttribute<InveProyecto, InveAmbitoGeografico> inveAmbitoGeografico;
    public static volatile SingularAttribute<InveProyecto, Date> pryFechaRecepcion;
    public static volatile SingularAttribute<InveProyecto, InveProyecto> inveProyecto;
    public static volatile SingularAttribute<InveProyecto, PrinPais> prinPais;
    public static volatile SingularAttribute<InveProyecto, String> pryOrigenIngreso;
    public static volatile SingularAttribute<InveProyecto, InveObjetivoSocioEconomico> inveObjetivoSocioEconomico;
    public static volatile SingularAttribute<InveProyecto, String> pryMetodologia;
    public static volatile SingularAttribute<InveProyecto, Long> pryNumeroSolicContrato;
    public static volatile SingularAttribute<InveProyecto, String> pryCodigoCi;
    public static volatile SingularAttribute<InveProyecto, Date> pryFechaContrato;
    public static volatile SingularAttribute<InveProyecto, String> pryAutorizaEnvioRepos;
    public static volatile SingularAttribute<InveProyecto, Date> pryFechaEntregaFinal;
    public static volatile SingularAttribute<InveProyecto, InveGrupo> inveGrupo;
    public static volatile SingularAttribute<InveProyecto, VincCine> vincCine;
    public static volatile SingularAttribute<InveProyecto, Date> pryFechaCrea;
    public static volatile SingularAttribute<InveProyecto, String> pryContrato;
    public static volatile SingularAttribute<InveProyecto, String> pryCodigoRepos;
    public static volatile SingularAttribute<InveProyecto, InveFuenteFinan> inveFuenteFinan;
    public static volatile SingularAttribute<InveProyecto, String> pryJustificacion;
    public static volatile SingularAttribute<InveProyecto, Date> pryFechaCierre;

}