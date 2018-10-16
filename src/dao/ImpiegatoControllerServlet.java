package dao;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.Impiegato;

/**
 * Servlet implementation class ImpiegatoControllerServlet
 */
@WebServlet("/ImpiegatoControllerServlet")
public class ImpiegatoControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ImpiegatoDaoUtil impiegatoDaoUtil;
	
	@Resource(name="jdbc/proxima")	
	private DataSource dataSource;
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		//creiamo impiegatoDaoUtil e lo passiamo nella connection pool / datasource
		try {
			impiegatoDaoUtil = new ImpiegatoDaoUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
    /**
     * Default constructor. 
     */
    public ImpiegatoControllerServlet() {
        // TODO Auto-generated constructor stub
    }
    
    private void listImpiegati(HttpServletRequest request, HttpServletResponse response) 
    		throws Exception {

    		// riceve impiegati dal ImpiegatoDaoUtil
    		List<Impiegato> impiegati = impiegatoDaoUtil.getAllImpiegati();
    		
    		// inserisce impiegati alla richiesta
    		request.setAttribute("IMPIEGATO_LIST", impiegati);
    				
    		// manda alla pagina JSP
    		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-impiegati.jsp");
    		dispatcher.forward(request, response);
    	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// legge il parametro "command" 
			String theCommand = request.getParameter("command");
			
			// se il parametro command manca, allora di default prende la lista dei impiegati
			if (theCommand == null) {
				theCommand = "LIST";
			}
			
			// il percorso sul metodo appropriato
			switch (theCommand) {
			
			case "LIST":
				listImpiegati(request, response);
				break;
				
			case "ADD":
				addImpiegato(request, response);
				break;
				
			case "LOAD":
				loadImpiegato(request, response);
				break;
				
			case "UPDATE":
				updateImpiegato(request, response);
				break;
			
			case "DELETE":
				deleteImpiegato(request, response);
				break;
				
			default:
				listImpiegati(request, response);
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}
    
    private void addImpiegato(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// legge l'info dei impiegati info dalla form data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String fiscalCode = request.getParameter("fiscalCode");
		String email = request.getParameter("email");		
		
		// crea un nuovo oggetto Impiegato
		Impiegato theImpiegato = new Impiegato(firstName, lastName,fiscalCode, email);
		
		// aggiunge l'impiegato sul database
		impiegatoDaoUtil.addImpiegato(theImpiegato);
				
		//ritorna nella pagina principale(la lista dei impiegati)
		listImpiegati(request, response);
	}
    
    private void deleteImpiegato(HttpServletRequest request, HttpServletResponse response)
    		throws Exception {

    		// legge impiegato id dalla form data
    		String theImpiegatoId = request.getParameter("impiegatoId");
    		
    		// cancella l'impiegato dal database
    		impiegatoDaoUtil.deleteImpiegato(theImpiegatoId);
    		
    		// ritorna nella pagina principale(la lista dei impiegati)
    		listImpiegati(request, response);
    }
    

	private void loadImpiegato(HttpServletRequest request, HttpServletResponse response) 
		throws Exception {

		// legge impiegato id dalla form data
		String theImpiegatoId = request.getParameter("impiegatoId");
		
		//ritorna l'impiegato dal database
		Impiegato theImpiegato = impiegatoDaoUtil.getImpiegato(theImpiegatoId);
		
		// inserisce l'impiegato nell'attributo richiesto
		request.setAttribute("THE_IMPIEGATO", theImpiegato);
		
		// manda nella pagina jsp: update-impiegato-form.jsp
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("/update-impiegato-form.jsp");
		dispatcher.forward(request, response);		
	}
	
	private void updateImpiegato(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

			// legge l'impiegato dalla form data
			int id = Integer.parseInt(request.getParameter("impiegatoId"));
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String fiscalCode = request.getParameter("fiscalCode");
			String email = request.getParameter("email");
			
			// crea un nuovo oggetto impiegato
			Impiegato theImpiegato = new Impiegato(id, firstName, lastName,fiscalCode, email);
			
			// aggiorna il database
			impiegatoDaoUtil.updateImpiegato(theImpiegato);
			
			// mandali indietro sulla pagina "lista impiegati"
			listImpiegati(request, response);
			
		}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
