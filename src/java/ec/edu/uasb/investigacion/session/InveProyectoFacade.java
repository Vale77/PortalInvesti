/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.investigacion.session;

import ec.edu.uasb.exception.ProyectoException;
import ec.edu.uasb.external.entities.AreaAcademica;
import ec.edu.uasb.investigacion.entities.InveAmbitoGeografico;
import ec.edu.uasb.investigacion.entities.InveCarta;
import ec.edu.uasb.investigacion.entities.InveConvocatoria;
import ec.edu.uasb.investigacion.entities.InveDisciplinaCientifica;
import ec.edu.uasb.investigacion.entities.InveFuenteFinan;
import ec.edu.uasb.investigacion.entities.InveGrupo;
import ec.edu.uasb.investigacion.entities.InveGrupoInvestigacion;
import ec.edu.uasb.investigacion.entities.InveObjetivoSocioEconomico;
import ec.edu.uasb.investigacion.entities.InvePresupuesto;
import ec.edu.uasb.investigacion.entities.InveProyecto;
import ec.edu.uasb.investigacion.entities.InveSeguimiento;
import ec.edu.uasb.investigacion.entities.InveTipoProyecto;
import ec.edu.uasb.principal.session.PrinDocumentoFacadeLocal;
import ec.edu.uasb.principal.entities.PrinDocumento;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author victor.barba
 */
@Stateless
public class InveProyectoFacade extends AbstractFacade<InveProyecto> implements InveProyectoFacadeLocal {

    @EJB
    private InveCartaFacadeLocal inveCartaFacade;

    @EJB
    private PrinDocumentoFacadeLocal prinDocumentoFacade;

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InveProyectoFacade() {
        super(InveProyecto.class);
    }

//    @Override
//    public Integer findDocenteBy(String id, int antiguedad, int antesDe, String contratos) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT interfaseOcu.dbo.f_getContratoDocenteInvestigacion (?,?,?,?); ");
//        Query q = em.createNativeQuery(sb.toString()).setParameter(1, id).setParameter(2, antiguedad).setParameter(3, antesDe).setParameter(4, contratos);
//
//        return (Integer) q.getSingleResult();
//    }
    @Override
    public String findDePlantaBy(String id, String contratos, BigDecimal anios) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT interfaseOcu.dbo.f_invest_esDePlanta (?,?,?); ");
        Query q = em.createNativeQuery(sb.toString()).setParameter(1, id).setParameter(2, contratos).setParameter(3, anios);

        return (String) q.getSingleResult();
    }

    @Override
    public Integer findGraduadoBy(String id, Short nivel) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT count(*) ")
                .append(" FROM interfaseOcu.GESTIONACADEMICA.INFO_GRADUADOS WITH (NOLOCK) ")
                .append(" WHERE INFO_GRADUADOS.IGR_COD_NIVACAD = ?   AND INFO_GRADUADOS.IGR_CED_PAS = ? ");
        Query q = em.createNativeQuery(sb.toString()).setParameter(1, nivel).setParameter(2, id);
        return (Integer) q.getSingleResult();
    }

    @Override
    public Integer findEstudianteDoctoradoBy(String id) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT count(*) ")
                .append(" FROM GESTIONACADEMICA.INFO_ESTUDIANTES WITH (NOLOCK) ")
                .append(" WHERE INFO_ESTUDIANTES.IES_COD_NIVACAD = 3   AND INFO_ESTUDIANTES.IES_CED_PAS = ? ")
                .append(" AND (SELECT COUNT(*) FROM GESTIONACADEMICA.INFO_GRADUADOS WITH (NOLOCK) WHERE  INFO_GRADUADOS.IGR_COD_NIVACAD =2 ")
                .append(" AND INFO_GRADUADOS.IGR_CED_PAS =  INFO_ESTUDIANTES.IES_CED_PAS) > 0  ");
        Query q = em.createNativeQuery(sb.toString()).setParameter(1, id);
        return (Integer) q.getSingleResult();
    }

    @Override
    public InveProyecto findProyectoBy(Long codPersona) {
        InveProyecto pry = null;
        StringBuilder sb = new StringBuilder();

        sb.append("select * from (SELECT INVE_PROYECTO.*, ROW_NUMBER ()OVER (PARTITION BY INVE_PROYECTO.PRY_CODIGO ORDER BY INVE_PROYECTO.PRY_CODIGO desc) NUM_FILA ")
                .append(" FROM INVE_PROYECTO WITH (NOLOCK)  WHERE (INVE_PROYECTO.PER_CODIGO = ?)) TABLA ")
                .append(" WHERE NUM_FILA = 1 ");
        Query q = em.createNativeQuery(sb.toString(), InveProyecto.class).setParameter(1, codPersona);
        try {
            pry = (InveProyecto) q.getSingleResult();
        } catch (NoResultException e) {
        }
        return pry;
    }

    @Override
    public InveProyecto getProyectoBy(String codigoComite) {
        InveProyecto pry = null;
        Query q = em.createNamedQuery("InveProyecto.findByPryCodigoCi").setParameter("pryCodigoCi", codigoComite);
        try {
            pry = (InveProyecto) q.getSingleResult();
        } catch (NoResultException e) {
        }
        return pry;
    }

    @Override
    public List<InveProyecto> getProyectoBy(InveGrupo inveGrupo) {
        Query q = em.createQuery("SELECT i FROM InveProyecto i WHERE i.inveGrupo = :inveGrupo");
        q.setParameter("inveGrupo", inveGrupo);
        return q.getResultList();
    }

    @Override
    public List<InveProyecto> getProyectoBy(InveAmbitoGeografico ambitoGeografico) {
        Query q = em.createQuery("SELECT i FROM InveProyecto i WHERE i.inveAmbitoGeografico = :ambitoGeografico");
        q.setParameter("ambitoGeografico", ambitoGeografico);
        return q.getResultList();
    }

    @Override
    public List<InveProyecto> getProyectoBy(InveGrupoInvestigacion grupoInvestigacion) {
        Query q = em.createQuery("SELECT i FROM InveProyecto i WHERE i.inveGrupoInvestigacion = :grupoInvestigacion");
        q.setParameter("grupoInvestigacion", grupoInvestigacion);
        return q.getResultList();
    }

    @Override
    public List<InveProyecto> getProyectoBy(InveDisciplinaCientifica disciplinaCientifica) {
        Query q = em.createQuery("SELECT i FROM InveProyecto i WHERE i.inveDisciplinaCientifica = :disciplinaCientifica");
        q.setParameter("disciplinaCientifica", disciplinaCientifica);
        return q.getResultList();
    }

    @Override
    public List<InveProyecto> getProyectoBy(InveObjetivoSocioEconomico osoe) {
        Query q = em.createQuery("SELECT i FROM InveProyecto i WHERE i.inveObjetivoSocioEconomico = :osoe");
        q.setParameter("osoe", osoe);
        return q.getResultList();
    }

    @Override
    public List<InveProyecto> getProyectoBy(InveFuenteFinan fuenteFinan) {
        Query q = em.createQuery("SELECT i FROM InveProyecto i WHERE i.inveFuenteFinan = :fuenteFinan");
        q.setParameter("fuenteFinan", fuenteFinan);
        return q.getResultList();
    }

    @Override
    public List<InveProyecto> getProyectoBy(InveConvocatoria inveConvocatoria) {
        Query q = em.createQuery("SELECT i FROM InveProyecto i WHERE i.inveConvocatoria = :inveConvocatoria");
        q.setParameter("inveConvocatoria", inveConvocatoria);
        return q.getResultList();
    }

    @Override
    public List<InveProyecto> findBy(Short anio, InveConvocatoria convoc, InveTipoProyecto inveTipoProyecto) {
        Query q = null;
        StringBuilder sb = new StringBuilder();
        List<InveProyecto> lista = new ArrayList<>();
        sb.append("SELECT i,a FROM InveProyecto i,AreaAcademica a WHERE i.pryAreaAcad = a.areaCodigo")
                .append(" and  i.pryAnioAcad = :pryAnioAcad and i.inveConvocatoria = :convoc ");
        // .append(" and i.inveConvocatoria = :convoc ");
        if (inveTipoProyecto == null) {
            q = em.createQuery(sb.toString() + " order by i.pryCodigoCi")
                    .setParameter("pryAnioAcad", anio).setParameter("convoc", convoc);
            //     .setParameter("convoc", convoc);
        } else {
            q = em.createQuery(sb.toString() + " and i.inveTipoProyecto = :inveTipoProyecto order by i.pryCodigoCi")
                    .setParameter("pryAnioAcad", anio).setParameter("convoc", convoc).setParameter("inveTipoProyecto", inveTipoProyecto);
        }
        List temp = q.getResultList();
        for (Object record : temp) {
            Object[] reg = (Object[]) record;
            InveProyecto proy = (InveProyecto) reg[0];
            AreaAcademica area = (AreaAcademica) reg[1];
            proy.setArea(area);
            lista.add(proy);
        }
        return lista;
    }

    @Override
    public List<InveProyecto> findConvocatoriaBy(InveConvocatoria convoc, InveTipoProyecto inveTipoProyecto) {
        Query q = null;
        StringBuilder sb = new StringBuilder();
        List<InveProyecto> lista = new ArrayList<>();
        sb.append("SELECT i,a FROM InveProyecto i,AreaAcademica a WHERE i.pryAreaAcad = a.areaCodigo")
                .append(" and i.inveConvocatoria = :convoc ");
        if (inveTipoProyecto == null) {
            q = em.createQuery(sb.toString() + " order by i.pryCodigoCi")
                    .setParameter("convoc", convoc);
        } else {
            q = em.createQuery(sb.toString() + " and i.inveTipoProyecto = :inveTipoProyecto order by i.pryCodigoCi")
                    .setParameter("convoc", convoc).setParameter("inveTipoProyecto", inveTipoProyecto);
        }
        List temp = q.getResultList();
        for (Object record : temp) {
            Object[] reg = (Object[]) record;
            InveProyecto proy = (InveProyecto) reg[0];
            AreaAcademica area = (AreaAcademica) reg[1];
            proy.setArea(area);
            lista.add(proy);
        }
        return lista;
    }

    @Override
    public List<InveProyecto> getInvesAprobados(Short anio) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", new Locale("es", "EC"));
        StringBuilder sb = new StringBuilder();
        List<InveProyecto> lista = new ArrayList<>();
        sb.append("SELECT INVE_PROYECTO.*,isnull(INVE_SEGUIMIENTO.SEG_CODIGO,(ROW_NUMBER() OVER(ORDER BY INVE_SEGUIMIENTO.SEG_CODIGO ASC))*-1) SEG_CODIGO,")
                .append(" INVE_SEGUIMIENTO.*,V_INVE_APROBADO.SEG_NRO_COMUNIC OFICIO_APROB,V_INVE_APROBADO.SEG_FECHA_COMUNIC FECHA_APROB ")
                .append(" FROM INVE_PROYECTO ")
                .append(" INNER JOIN V_INVE_APROBADO   ON (INVE_PROYECTO.PRY_CODIGO = V_INVE_APROBADO.PRY_CODIGO) ")
                .append(" LEFT OUTER JOIN INVE_SEGUIMIENTO ON ( INVE_PROYECTO.PRY_CODIGO = INVE_SEGUIMIENTO.PRY_CODIGO AND dbo.INVE_SEGUIMIENTO.TSE_CODIGO = 9) ")
                .append(" WHERE INVE_PROYECTO.PRY_ANIO_ACAD = ?");
        Query q = em.createNativeQuery(sb.toString(), "ProyAprobResults").setParameter(1, anio);
        List temp = q.getResultList();
        for (Object record : temp) {
            try {
                Object[] reg = (Object[]) record;
                InveProyecto proy = (InveProyecto) reg[0];
                InveSeguimiento seguim = (InveSeguimiento) reg[1];
                proy.setInveSeguimiento(seguim);
                proy.setNotificacionAprob((String) reg[2]);
                proy.setFechaNotifAprob(formatter.parse((String) reg[3]));
                lista.add(proy);
            } catch (ParseException ex) {
                Logger.getLogger(InveProyectoFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public List<InveProyecto> getConvocatoriaAprobados(InveConvocatoria convoc) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", new Locale("es", "EC"));
        StringBuilder sb = new StringBuilder();
        List<InveProyecto> lista = new ArrayList<>();
        sb.append("SELECT INVE_PROYECTO.*,isnull(INVE_SEGUIMIENTO.SEG_CODIGO,(ROW_NUMBER() OVER(ORDER BY INVE_SEGUIMIENTO.SEG_CODIGO ASC))*-1) SEG_CODIGO,")
                .append(" INVE_SEGUIMIENTO.*,V_INVE_APROBADO.SEG_NRO_COMUNIC OFICIO_APROB,V_INVE_APROBADO.SEG_FECHA_COMUNIC FECHA_APROB ")
                .append(" FROM INVE_PROYECTO")
                .append(" INNER JOIN V_INVE_APROBADO   ON (INVE_PROYECTO.PRY_CODIGO = V_INVE_APROBADO.PRY_CODIGO) ")
                .append(" LEFT OUTER JOIN INVE_SEGUIMIENTO ON ( INVE_PROYECTO.PRY_CODIGO = INVE_SEGUIMIENTO.PRY_CODIGO AND dbo.INVE_SEGUIMIENTO.TSE_CODIGO = 9) ")
                .append(" WHERE INVE_PROYECTO.CVO_CODIGO = ?");
        Query q = em.createNativeQuery(sb.toString(), "ProyAprobResults").setParameter(1, convoc.getCvoCodigo());
        System.out.println(sb.toString());
        List temp = q.getResultList();
        for (Object record : temp) {
            try {
                Object[] reg = (Object[]) record;
                InveProyecto proy = (InveProyecto) reg[0];
                InveSeguimiento seguim = (InveSeguimiento) reg[1];
                proy.setInveSeguimiento(seguim);
                proy.setNotificacionAprob((String) reg[2]);
                proy.setFechaNotifAprob(formatter.parse((String) reg[3]));
                lista.add(proy);
            } catch (ParseException ex) {
                Logger.getLogger(InveProyectoFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    //<editor-fold defaultstate="collapsed" desc="CRUD">
    @Override
    public void remove(InveProyecto entity, List<PrinDocumento> listaDocs) {
        for (PrinDocumento doc : listaDocs) {
            if (doc.getDocEntidadCodigo() == entity.getPryCodigo()) {
                prinDocumentoFacade.remove(doc);
            }
        }
        super.remove(entity);
    }

    @Override
    public void create(InveProyecto entity, List<PrinDocumento> listaDocs) throws ProyectoException {

        Collection<InvePresupuesto> listaPresup = entity.getInvePresupuestoCollection();
        entity.setInvePresupuestoCollection(null);
//        entity.setInveCarta(null);
        super.create(entity);
        em.flush();
        /*  PRESUPUESTO DEL PROYECTO*/
        if (listaPresup != null) {
            for (InvePresupuesto pre : listaPresup) {
                if (pre.getInvePresupuestoPK().getPryCodigo() == null) {
                    pre.setInveProyecto(entity);
                    pre.getInvePresupuestoPK().setPryCodigo(entity.getPryCodigo());
                    em.persist(pre);
                }
            }
            entity.setInvePresupuestoCollection(listaPresup);
        }
        /* DOCUMENTOS DEL PROYECTO*/
        if (listaDocs != null) {
            for (PrinDocumento doc : listaDocs) {
                doc.setDocEntidadCodigo(entity.getPryCodigo());
                doc.setDocDirDestino(doc.getDocDirGeneral() + "_" + doc.getDocEntidadCodigo());
                prinDocumentoFacade.create(doc);
            }
        }
        /* GENERAR LA CARTA DE PERTINENCIA*/
        if (entity.getPryEstadoReg().equals("A")) {
            // Carta de pertinencia.
            InveCarta inveCarta = new InveCarta();
//            StringBuilder mensaje = genMensaje();
            inveCarta.setInveProyecto(entity);
            inveCarta.setPryCodigo(entity.getPryCodigo());
            inveCarta.setCarFechaCrea(new Date());
            inveCarta.setCarUsuarioCrea(entity.getPryUsuarioCrea());
            inveCartaFacade.create(inveCarta);
            entity.setInveCarta(inveCarta);
////            this.sendEmail("Soporte Servicios",
////                    "selenia.larenas@uasb.edu.ec;fernando.andrade@uasb.edu.ec;",
////                    "", "soporteservicios@uasb.edu.ec;juancarlos.lozada@uasb.edu.ec;victor.barba@uasb.edu.ec;",
////                    "Asunto", mensaje.toString(), "HTML");
//            this.sendEmail("Soporte Servicios", "victor.barba@uasb.edu.ec",
//                    "", "", "Asunto", mensaje.toString(), "HTML");
//            mensaje = null;
        }
        /* DATOS PERSONALES */
        em.merge(entity.getPrinPersona());
    }

    @Override
    public void edit(InveProyecto entity, List<PrinDocumento> listaDocs) throws ProyectoException {
        if (listaDocs != null) {
            for (PrinDocumento doc : listaDocs) {
//            System.out.println(doc);
                if (doc.getDocArchivo() != null) {
                    doc.setDocFechaAct(new Date());
                    doc.setDocUsuarioAct(entity.getPryUsuarioAct());
                    doc.setDocEntidadCodigo(entity.getPryCodigo());
                    doc.setDocDirDestino(doc.getDocDirGeneral() + "_" + doc.getDocEntidadCodigo());
                    prinDocumentoFacade.edit(doc);
                }
            }
        }
//        System.out.println("Carta de pertinencia. " + entity.getInveCarta());
        if (entity.getPryEstadoReg().equals("A") && entity.getInveCarta() == null) {

            // Carta de pertinencia.
            InveCarta inveCarta = new InveCarta();
            inveCarta.setInveProyecto(entity);
            inveCarta.setPryCodigo(entity.getPryCodigo());
            inveCarta.setCarFechaCrea(new Date());
            inveCarta.setCarUsuarioCrea(entity.getPryUsuarioCrea());
            if (entity.getPrinCategoriaInvest().getCaiCodigo().equals(new Short("5"))) {// solo para doctorandos
                inveCarta.setCarTipoCarta("CONSTANCIA");
            } else {
                inveCarta.setCarTipoCarta("PERTINENCIA");
            }
            em.persist(inveCarta);
            entity.setInveCarta(inveCarta);
        }
//        System.out.println("entity.getPrinPersona() "+entity.getPrinPersona());
        em.merge(entity.getPrinPersona());
//        System.out.println("entity "+entity);
        super.edit(entity);
    }

    @Override
    public void edit(InveProyecto entity) {
        em.merge(entity.getPrinPersona());
//        System.out.println("entity "+entity);
        super.edit(entity);
    }

    @Override
    public void edit(InveProyecto entity, InveSeguimiento seguimiento) {
        super.edit(entity);
        if (entity.getInveSeguimiento().getSegCodigo() < 0) {
            em.persist(seguimiento);
        }
    }
//</editor-fold>

    private StringBuilder buildLogo(String archivo, String align, long width, long height) {
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletRequest o = (HttpServletRequest) fc.getExternalContext().getRequest();
        String s = o.getRequestURL().toString();
        StringBuilder sbHtml = new StringBuilder();
        sbHtml.append("<p align='").append(align).append("'>")
                .append("<img src='").append(s.substring(0, s.indexOf("/", 15))).append(fc.getExternalContext().getRequestContextPath())
                //                .append("<img src='cid:image' alt='logouasb' width='").append(width).append("' height='").append(height).append("'/>")
                //                                  .append("<img src='cid:").append(archivo).append("' alt='logouasb' width='").append(width).append("' height='").append(height).append("'/>")
                .append("/resources/images/")
                .append(archivo)
                .append("' alt='logouasb' width='").append(width).append("' height='").append(height).append("'/>")
                .append("</p>");
//        System.out.println(sbHtml.toString());
        return sbHtml;
    }

    private StringBuilder genMensaje() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMMM yyyy", new Locale("es", "EC"));
        StringBuilder sbHtml = new StringBuilder();
        String fechas = null;
        String area = null;

        sbHtml.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'><html xmlns='http://www.w3.org/1999/xhtml'>")
                .append("<head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title></title>")
                .append("<style type='text/css'>")
                .append(" table, th, td {font-family: Verdana, Arial, Helvetica, sans-serif;}")
                .append(" .col1 {padding-left:10px; font-weight: bold;color:#00444C;}")
                .append(" .col2 {padding:5px 10px;border: 1px dotted black;}")
                .append("</style>")
                .append("</head>")
                .append("<body></br></br>")
                //                fechas = formatter.format(actividad.getActFechaInicio());
                .append("<br><br>Atentamente,<br><br><br>").append(area).append(".<br>")
                .append("Quito, Ecuador<br>")
                .append(buildLogo("Logo-horizontal-color.jpg", "left", 225, 75))
                .append("</body>");
        return sbHtml;
    }

    @Override
    public List<InveProyecto> findAll(InveConvocatoria convocatoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
