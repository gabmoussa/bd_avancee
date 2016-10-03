package display.jsp_servlet;

import controller.EmpruntController;
import controller.ItemSearcherController;
import controller.ReservationController;
import controller.TarifController;
import controller.UserController;
import entity.reservation.Emprunt;
import entity.reservation.Item;
import entity.reservation.Oeuvre;
import entity.reservation.Reservation;
import entity.tarif.TarifAgeManager;
import entity.tarif.TarifLieuManager;
import entity.tarif.TarificationAge;
import entity.tarif.TarificationLieuHabitation;
import entity.utilisateur.Adherent;
import entityManager.AdherentEntityManager;
import entityManager.EmpruntEntityManager;
import entityManager.ItemEntityManager;
import entityManager.OeuvreEntityManager;
import entityManager.ReservationManager;
import entityManager.TypeOeuvreEntityManager;
import entityManager.UserEntityManager;
import enumeration.*;
import enumeration.StatusItem;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
/**
 *
 * @author gsakho
 */
@WebServlet(name="TestServlet", urlPatterns={"/Test"})
public class testServlet extends HttpServlet{
    
    @Resource
    private UserTransaction utx;

    @PersistenceUnit
    private EntityManagerFactory emf;
    
    
    // Manager
    private EmpruntEntityManager empruntManager;
    private ItemEntityManager itemManager;
    private ReservationManager reservationManager;
    private AdherentEntityManager adherentManager;
    private TarifLieuManager tarifLieuManager;
    private TarifAgeManager tarifAgeManager;
    private EntityManager entityManager;
    private UserEntityManager userEntityManager;
    // Controller
    private TarifController tarifController;
    private ItemSearcherController itemController;
    private EmpruntController empruntController;
    private ReservationController reservationController;
    private UserController userController;
    
    //Objets for DB
    private Oeuvre assommoire;
    private Oeuvre gundam;
    private Oeuvre gundam2;
    private Item assomoire1;
    private Item assomoire2;
    private Item item;
    private Item item2;
    private Adherent adherentEmily;
    private Adherent james;
    private TarificationAge tarifA;
    private TarificationAge tarifA2;
    private TarificationLieuHabitation tarifL;
    
     /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        UserTransaction ut;
        try{
            Context c = new InitialContext();
            String transName = System.getProperty("jta.UserTransaction");
            utx = (UserTransaction) c.lookup(transName);
            int a = 34;
        }catch(Exception e){}
        
        // initialisation des managers
        initManagers();
        // initialisation des controller
        initControllers();
        
        tarifController.verifierCautionSuffisant(12, null);
        // initialisation des donnees de base de donnees
        initData();

        //testdataAndMethods();    
        
        setData();
        
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    
    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "ListPerson servlet";
    }
    // </editor-fold>
    
    /*
    Configure des donnees de base
    */
    public void setData() 
    {
        /*
        Emprunt manager
        */
        EmpruntEntityManager manager = new EmpruntEntityManager();
        manager.setEntityManager(emf.createEntityManager());
        manager.setUserTransaction(utx);      
        /*
        Item manager
        */
        ItemEntityManager managerItem = new ItemEntityManager();
        managerItem.setEntityManager(emf.createEntityManager());
        managerItem.setUserTransaction(utx);
        
       /*
        TypeOeuvre manager
        */
        TypeOeuvreEntityManager managerTypeOeuvre = new TypeOeuvreEntityManager();
        managerTypeOeuvre.setEntityManager(emf.createEntityManager());
        managerTypeOeuvre.setUserTransaction(utx);
        
        /*
        Oeuvre manager
        */
        OeuvreEntityManager managerOeuvre = new OeuvreEntityManager();
        managerOeuvre.setEntityManager(emf.createEntityManager());
        managerOeuvre.setUserTransaction(utx);
  
        //Item 1 basique
        Item item1 = new Item();
        item1.setNbEmprunt(26);
        item1.setStatus(StatusItem.DISPONIBLE);
        managerItem.create(item1);
        Item item3 = new Item();
        item3.setNbEmprunt(236);
        managerItem.create(item3);
        
    
    }

    private void initManagers() {
        this.empruntManager = new EmpruntEntityManager();
        this.itemManager = new ItemEntityManager();
        this.reservationManager = new ReservationManager();
        this.adherentManager = new AdherentEntityManager();
        this.tarifLieuManager = new TarifLieuManager();
        this.tarifAgeManager = new TarifAgeManager();
        this.userEntityManager = new UserEntityManager();
        
        this.entityManager = emf.createEntityManager();
        
                
        empruntManager.setEntityManager(emf.createEntityManager());
        empruntManager.setUserTransaction(utx);
        
        itemManager.setEntityManager(emf.createEntityManager());
        itemManager.setUtx(utx);
        
        reservationManager.setEntityManager(entityManager);
        reservationManager.setUtx(utx);
        
        adherentManager.setManager(entityManager);
        adherentManager.setUtx(utx);
        
        tarifLieuManager.setEntityManager(entityManager);
        tarifLieuManager.setUtx(utx);
        
        tarifAgeManager.setEntityManager(entityManager);
        tarifAgeManager.setUtx(utx);
        
        this.userEntityManager.setEntityManager(entityManager);
        this.userEntityManager.setUtx(utx);
    }

    private void initControllers() {
        this.tarifController = new TarifController(tarifLieuManager, tarifAgeManager);
        this.itemController = new ItemSearcherController(itemManager,reservationManager,empruntManager);
        this.empruntController = new EmpruntController(empruntManager);
        this.reservationController = new ReservationController(reservationManager);
        this.userController = new UserController(userEntityManager);
    }

    private void initData() {
        //   ---- OEUVRES ----
        this.assommoire = new Oeuvre();
        this.assommoire.setAuteur("E.Zola");
        this.assommoire.setDateCreation(new Date());
        this.assommoire.setGenreOeuvre(Genre.roman);
        this.assommoire.setTitre("L Assommoire");
        this.assommoire.setTypeOeuvre(TypePhysiqueOeuvre.LIVRE);
        
        this.gundam = new Oeuvre();
        this.gundam.setAuteur("Yoshiyuki Tomino");
        this.gundam.setDateCreation(new Date());
        this.gundam.setGenreOeuvre(Genre.mecha);
        this.gundam.setTitre("gundam");
        this.gundam.setTypeOeuvre(TypePhysiqueOeuvre.DVD);//c brouillon ... mais juste pour tester

        this.gundam2 = new Oeuvre();
        this.gundam2.setAuteur("katia");
        this.gundam2.setDateCreation(new Date());
        this.gundam2.setGenreOeuvre(Genre.mecha);
        this.gundam2.setTitre("livre");
        this.gundam2.setTypeOeuvre(TypePhysiqueOeuvre.DVD);
        
        
        //   ---- ITEMS ----
        this.assomoire1 = new Item();
        this.assomoire1.setOeuvre(this.assommoire);
        this.itemManager.create(assomoire1);
        
        this.assomoire2 = new Item();
        this.assomoire2.setOeuvre(this.assommoire);
        this.itemManager.create(assomoire2);
        
        this.item = new Item();
        this.item.setOeuvre(gundam);
        this.itemManager.create(item);
            
        this.item2 = new Item();
        this.item2.setStatus(StatusItem.EMPRUNTE);
        this.item2.setOeuvre(gundam2);
        this.itemManager.create(item2);
        
        
        //   ---- ADHERENTS ----
        Calendar d1 = Calendar.getInstance();
        d1.set(1992, 11, 25);
        this.adherentEmily = new Adherent("Best Street, NYC, US", "Emily", "Pope", d1.getTime(), "pope@emily.best", "pwd");
        this.adherentEmily.setaCotise(true);
        this.adherentManager.create(adherentEmily);
        
        Calendar d2 = Calendar.getInstance();
        d2.set(1992,00,27);
        this.james = new Adherent("120 dans un carton, sous un pont", "james", "cam", d2.getTime() , "james@gmail.com", "password");
        this.james.setaCotise(true);
        this.adherentManager.create(james);
        
        
        //   ---- TARIFICATION ----
        this.tarifA = new TarificationAge(18,5);
        this.tarifA2 = new TarificationAge(28,15);
        //TODO implementer le calcul pour la distance, valeur bidon pour tester
        this.tarifL = new TarificationLieuHabitation(1, 10);//si pas de nice paye juste un test, ajouter un hashmap ...

        tarifAgeManager.create(tarifA);
        tarifAgeManager.create(tarifA2);
        tarifLieuManager.create(tarifL);
        
        
       
    }

    private void testdataAndMethods() {
        int testTarif = tarifController.getTarif(null, null, 1992, 01, 27);//utilise que l annee, changer le proto
        Adherent a = userController.chercherAdherent("Pope", "Emily", 1992, 12, 25);
        //List<Item> testSearchItem = itemController.chercherOeuvre("gundam", "Yoshiyuki Tomino", "mecha", "00/00/00");
        List<Item> testSearchItem = itemController.chercherOeuvre("gundam", "Yoshiyuki Tomino", "DVD");
        
        //test Reserver Oeuvre
        List<Reservation> listResas = reservationController.chercherReservationParOeuvre(gundam);
        itemController.reserverOeuvre(james, gundam);
        itemController.reserverOeuvre(james, gundam);
        listResas = reservationController.chercherReservationParOeuvre(gundam);
        
        //test Emprunter Oeuvre
        itemController.emprunterOeuvre(james, gundam);
        itemController.emprunterOeuvre(adherentEmily, assommoire);
        itemController.emprunterOeuvre(adherentEmily, assommoire);
        itemController.emprunterOeuvre(james, gundam);
        List<Emprunt> emprunts;
        emprunts = empruntController.chercherEmpruntParAdherent(adherentEmily);
        emprunts = empruntController.chercherEmpruntParItem(assomoire1);
        emprunts = empruntController.chercherEmpruntParAdherent(james);
        emprunts = empruntController.chercherEmpruntParItem(item2);
        
        Emprunt emprunt = new Emprunt();
        //assert(manager.find(emprunt.getIdEmprunt())!= null);
    }
}
