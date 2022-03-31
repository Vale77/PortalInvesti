/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.principal.session;

import ec.edu.uasb.principal.entities.PrinDocumento;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author victor.barba
 */
@Stateless
public class PrinDocumentoFacade extends AbstractFacade<PrinDocumento> implements PrinDocumentoFacadeLocal {

    @PersistenceContext(unitName = "PortalInvestPU")
    private EntityManager em;
    private static String OS = System.getProperty("os.name").toLowerCase();
    private static String separadorPath;
    private static String carpetaCompartida = "opt";

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrinDocumentoFacade() {
        super(PrinDocumento.class);
    }

    @Override
    public List<PrinDocumento> getDocumentos(String modulo, String entidad, Long codEntidad) {
        Query q = em.createQuery("SELECT p FROM PrinDocumento p WHERE p.docModOrigen = :docModOrigen and p.docEntidad = :docEntidad and p.docEntidadCodigo = :codEntidad");
        q.setParameter("docModOrigen", modulo).setParameter("docEntidad", entidad).setParameter("codEntidad", codEntidad);
        return q.getResultList();
    }

    //<editor-fold defaultstate="collapsed" desc="Manejo de archivo">
    private static boolean isWindows() {
        return (OS.contains("win"));
    }

    private static boolean isMac() {
        return (OS.contains("mac"));
    }

    private static boolean isUnix() {
        return (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0);
    }

    private static boolean isSolaris() {
        return (OS.contains("sunos"));
    }

    private static String separadorOS() {
        if (isWindows()) {
            return "\\";
        } else if (isUnix() || isSolaris() || isMac()) {
            return "//";
        } else {
            return "";
        }
    }

    private String getPathDoc() {
        separadorPath = separadorOS();
        return (isWindows() ? "\\\\MILTONVALENCIA" : "") + separadorPath + carpetaCompartida + separadorPath + "documentos";
    }

    public static boolean deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] children = dir.listFiles();
            for (File children1 : children) {
                boolean success = deleteDirectory(children1);
                if (!success) {
                    return false;
                }
            }
        }

        // either file or an empty directory
        System.out.println("removing file or directory : " + dir.getName());
        return dir.delete();
    }

    private void eliminarArchivo(PrinDocumento documento) {
        String carpetaDocumento = getPathDoc() + separadorPath + documento.getDocModOrigen()
                + separadorPath + documento.getDocDirGeneral()
                + separadorPath + documento.getDocDirDestino();
        File files = new File(carpetaDocumento);
        if (files.exists()) {
            try {
                Files.deleteIfExists(new File(carpetaDocumento + separadorPath + documento.getDocNombre()).toPath());
//                deleteDirectory(files);
            } catch (DirectoryNotEmptyException x) {
                Logger.getLogger(PrinDocumentoFacade.class.getName()).log(Level.SEVERE, null, x);
            } catch (IOException ex) {
                Logger.getLogger(PrinDocumentoFacade.class.getName()).log(Level.SEVERE, null, ex);
            }
//                System.err.format("%s not empty%n");

        }
    }

    private void almacenarArchivo(PrinDocumento entity) throws IOException {
        if (entity.getDocArchivo() != null) {
            String carpetaDocumento = getPathDoc() + separadorPath + entity.getDocModOrigen()
                    + separadorPath + entity.getDocDirGeneral()
                    + separadorPath + entity.getDocDirDestino();
//            System.out.println("almacenarArchivo " + carpetaDocumento);
            File files = new File(carpetaDocumento);
            if (!files.exists()) {
                files.mkdirs();
            }
//            System.out.println("Grabar Archivo " + carpetaDocumento + separadorPath + entity.getDocNombre());
            Files.copy(new ByteArrayInputStream(entity.getDocArchivo()), new File(carpetaDocumento + separadorPath + entity.getDocNombre()).toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

//</editor-fold>
    @Override
    public void create(PrinDocumento entity) {
        try {
            super.create(entity);
            em.flush();
            this.almacenarArchivo(entity);
        } catch (IOException ex) {
            Logger.getLogger(PrinDocumentoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void edit(PrinDocumento entity) {
        try {
//            System.out.println(entity);
            super.edit(entity);
            this.almacenarArchivo(entity);
        } catch (IOException ex) {
            Logger.getLogger(PrinDocumentoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void remove(PrinDocumento entity) {
        eliminarArchivo(entity);
        super.remove(entity); //To change body of generated methods, choose Tools | Templates.
    }

}
