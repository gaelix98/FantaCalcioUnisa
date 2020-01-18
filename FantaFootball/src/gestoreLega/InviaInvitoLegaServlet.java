package gestoreLega;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import gestoreLega.Asta;
import gestoreLega.AstaDAO;
import gestoreSquadra.SquadraDAO;
import gestoreUtente.Allenatore;
import gestoreUtente.AllenatoreDAO;


@WebServlet("/InviaInvitoLegaServlet")
public class InviaInvitoLegaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InviaInvitoLegaServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8"); 
		request.setCharacterEncoding("UTF-8");
		LegaDAO legad = new LegaDAO();
		InvitoDAO invitd = new InvitoDAO();
		AllenatoreDAO allenatord= new AllenatoreDAO();
		SquadraDAO sq = new SquadraDAO();
		Allenatore allenatore=(Allenatore) request.getSession().getAttribute("utente");
		String lega=request.getParameter("lega");
		String username = request.getParameter("userall");
		String email = request.getParameter("emailall");
		String redirect="index.jsp";
		Allenatore invitato = null;
		System.out.println(lega);



		if ((username!= null  || email!=null)) {
			System.out.println("fuori2");
			
				
					try {
						if(!username.equals("") && allenatord.getAllenatoreByUsername(username)!=null) {
							if( sq.getSquadraByUserELega(username, lega)==null) {
							invitato = allenatord.getAllenatoreByUsername(username);
							Lega legaa = legad.getLegaByNome(lega);
							System.out.println(legaa.getNome());
							try {
								invitd.addInvito(new Invito(invitato,legaa, false));
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					
					}
				
			
				else if(!email.equals("")) {
					System.out.println("fuori1");
					String mail = request.getParameter("emailall");
					String object =("Unisciti alla fantastica lega!");
					String message =("Fra partecipa a questa fantastica lega di " + allenatore.getUsername()) + " http://localhost:8080/FantaFootball/registrazione.jsp";
					System.out.println(mail + " " + object + " " + message);
					try
					{ System.out.println("fuori");
						sendMailFrmoUser(object, message,mail);
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						
					}
					catch(MessagingException e)
					{
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						
					}

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}






		RequestDispatcher requestDispatcher = request.getRequestDispatcher(redirect);
		requestDispatcher.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


	public static void sendMailFrmoUser(String objet,String msg,String email)throws MessagingException
	{
		final String usernameM = "professore6puntipls@outlook.com";
		final String password = "Esame1401";
		// Creazione di una mail session
		Properties props = new Properties();
		   
//         props.put("mail.smtp.socketFactory.port", "587");
//         props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//         props.put("mail.smtp.socketFactory.fallback", "true");
         props.put("mail.smtp.host", "smtp-mail.outlook.com");
         props.put("mail.smtp.port", "587");
         props.put("mail.smtp.starttls.enable","true");
         props.put("mail.smtp.auth", "true");
		 props.put("mail.debug", "true"); 
		 
		
		
		Session session = Session.getInstance(props, 
				new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(
						usernameM, password);// Specify the Username and the PassWord
			}
		});
		session.setDebug(true);

		// Creazione del messaggio da inviare
		MimeMessage message = new MimeMessage(session);
		message.setSubject(objet);
		message.setText(msg);

		// Aggiunta degli indirizzi del mittente e del destinatario
		InternetAddress fromAddress = new InternetAddress("professore6puntipls@outlook.com");
		
		message.setFrom(fromAddress);
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

		// Invio del messaggio
		Transport.send(message);
	}


}