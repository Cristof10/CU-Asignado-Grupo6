package entities;

import java.io.Serializable;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Table;

/**
 * @author Carlos Iñiguez
 */
@Entity
@Table(name = "Cuenta")
public class Cuenta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCuenta")
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "total")
	private double total;

	/**
	 * Default constructor
	 */
	public Cuenta() {

	}

	/**
	 * Constructor con parámetros
	 */
	public Cuenta(String nombre, double total) {
		super();
		this.nombre = nombre;
		this.total = total;
	}

	/** Getters y Setters **/

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * @param nombre
	 */
	public void Cuenta(String nombre) {
		// TODO implement here
	}

	/**
	 * @param idCuenta
	 * @return
	 */
	public static Cuenta getById(int idCuenta) {
		EntityManager em =Persistence.createEntityManagerFactory("persistencia").createEntityManager();
		return em.find(Cuenta.class, idCuenta);
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Cuenta> getAll() {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Cuenta> getAllDestinos(int idCuentaOrigen) {
		EntityManager em = Persistence.createEntityManagerFactory("persistencia").createEntityManager();
		String consulta = "SELECT c FROM Cuenta c WHERE c.id <> :idCuentaOrigen";
		Query query = em.createQuery(consulta);
		query.setParameter("idCuentaOrigen", idCuentaOrigen);
		
		return (List<Cuenta>)query.getResultList();
	}

	/**
	 * @return
	 * */
	 
	@SuppressWarnings("unchecked")
	public static List<Cuenta> getSumarized() {
		EntityManager em=  Persistence.createEntityManagerFactory("persistencia").createEntityManager();
		
		//SELECT * FROm CUENTA;
		String consulta = "SELECT c FROM Cuenta c";
		Query query = em.createQuery(consulta);
		return (List<Cuenta>)query.getResultList();
		
		
	}

	public boolean persist() {
        EntityManager em = Persistence.createEntityManagerFactory("persistencia").createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        
        try {
            transaction.begin();
            em.merge(this); // Actualiza la entidad actual en la base de datos
            transaction.commit();
            return true; // La operación de persistencia fue exitosa
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback(); // Si hay un error, se hace rollback de la transacción
            }
            e.printStackTrace();
            return false; // La operación de persistencia falló
        } finally {
            em.close(); // Se cierra el EntityManager
        }
    }
	
	public static boolean update(Cuenta cuenta) {
		// TODO implement here
		return false;
	}

	/**
	 * @param idCuenta
	 * @return
	 */
	public boolean delete(int idCuenta) {
		// TODO implement here
		return false;
	}

	/**
	 * @param valor
	 */
	public void ajustarSaldo(double valor) {
		// TODO implement here
	}

}